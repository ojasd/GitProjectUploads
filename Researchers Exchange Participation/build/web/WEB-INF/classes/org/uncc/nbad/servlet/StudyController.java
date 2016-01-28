package org.uncc.nbad.servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.codehaus.jackson.map.ObjectMapper;
import org.uncc.nbad.bean.Answer;
import org.uncc.nbad.bean.Study;
import org.uncc.nbad.bean.User;
import org.uncc.nbad.utility.StudyDB;
import org.uncc.nbad.utility.AnswerDB;
import org.uncc.nbad.utility.UserDB;

@WebServlet("/Study")
@MultipartConfig
public class StudyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int BUFFER_LENGTH = 4096;

        @Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
    	session.setMaxInactiveInterval(-1);
    	
    	//String msg = "";
        String url = "/home.jsp";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
        	if(null != session.getAttribute("theUser"))
        		url = "/main.jsp";
        	else
        		url="/home.jsp";
        }
        else if(action.trim().equals("studies")){
        	if(null != session.getAttribute("theUser")){
        		User user = (User)session.getAttribute("theUser");
        		String userEmail = user.getEmail();
        		List<Study> userStudies = StudyDB.getStudiesFor(userEmail);
        		request.setAttribute("userStudies", userStudies);
    			url="/studies.jsp";
        	}
        	else{
        		url="/login.jsp";
        	}
        }
        else if(action.trim().equals("participate")){
        	if(null != session.getAttribute("theUser")){
        		User user = (User)session.getAttribute("theUser");
        		String userEmail = user.getEmail();
        		String studyCode = request.getParameter("StudyCode");
        		if(studyCode == null || studyCode.trim().equals("")){
        			List<Study> studiesToPart = StudyDB.getStudiesToParticipate(userEmail);
        			request.setAttribute("participateStudies", studiesToPart);
        			url="/participate.jsp";
        		}
        		else{
        			Study study = StudyDB.getStudy(studyCode);
        			request.setAttribute("theQuestion", study);
        			url="/question.jsp";
        		}
        	}
        	else{
        		url="/login.jsp";
        	}
        }
        else if(action.trim().equals("edit")){
        	if(null != session.getAttribute("theUser")){
        		String studyCode = request.getParameter("StudyCode");
        		Study study = StudyDB.getStudy(studyCode);
        		request.setAttribute("editStudy", study);
        		url="/editstudy.jsp";
        	}
        	else{
        		url="/login.jsp";
        	}
        }
        else if(action.trim().equals("update")){
        	if(null != session.getAttribute("theUser")){
                    String studyCode = request.getParameter("StudyCode");
                    User user  = (User)session.getAttribute("theUser");
                    String userEmail = user.getEmail();
                    Study study = StudyDB.getStudy(studyCode);
                    if(request.getParameter("StudyName")!=null && !request.getParameter("StudyName").trim().equals("")){
                            study.setName(request.getParameter("StudyName"));
                    }
                    if(request.getParameter("QuestionText")!=null && !request.getParameter("QuestionText").trim().equals("")){
                            study.setQuestion(request.getParameter("QuestionText"));
                    }
                    
                    //file multipart
                    //put it and replace the url created
        	    for (Part part : request.getParts()) {
        	    	if(part.getName()!=null){
                            BufferedImage bufferedImage = ImageIO.read(request.getPart(part.getName()).getInputStream());
                            String fileName = getFileName(part);
                            if(fileName!=null && !fileName.equalsIgnoreCase("null") && !fileName.equalsIgnoreCase("")){
                                fileName = fileName.trim(); 
                                fileName = study.getImageURL().substring(0,study.getImageURL().lastIndexOf("."))+fileName.substring(fileName.lastIndexOf("."));
                                int type  = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
                                ImageIO.write(resizeImage(bufferedImage, type, 64, 64), fileName.substring(fileName.lastIndexOf(".")+1), new File(System.getenv("OPENSHIFT_DATA_DIR") + fileName));
                                study.setImageURL(fileName);
                            }
        	    	}
        	    }
                    
                    if(request.getParameter("Participants")!=null && !request.getParameter("Participants").trim().equals("")){
                            study.setRequestedParticipants(Integer.parseInt(request.getParameter("Participants")));
                    }

                    if(request.getParameter("Description")!=null && !request.getParameter("Description").trim().equals("")){
                            study.setDescription(request.getParameter("Description"));
                    }
                    
                    StudyDB.updateStudy(study);

                    request.setAttribute("userStudies", StudyDB.getStudiesFor(userEmail));
                    url="/studies.jsp";
        	}
        	else{
        		url="/login.jsp";
        	}
        }
        else if(action.trim().equals("add")){
        	if(null != session.getAttribute("theUser")){
                    User user  = (User)session.getAttribute("theUser");
                    String userEmail = user.getEmail();
                    Study study = new Study();
                    if(request.getParameter("StudyName")!=null && !request.getParameter("StudyName").trim().equals("")){
                            study.setName(request.getParameter("StudyName"));
                    }
                    if(request.getParameter("QuestionText")!=null && !request.getParameter("QuestionText").trim().equals("")){
                            study.setQuestion(request.getParameter("QuestionText"));
                    }

                    Date date= new java.util.Date();
                    study.setDateCreated(date);
                    
                    //file multipart
                    //put it and replace the url created
        	    for (Part part : request.getParts()) {
        	    	if(part.getName()!=null){
                            BufferedImage bufferedImage = ImageIO.read(request.getPart(part.getName()).getInputStream());
                            String fileName = getFileName(part);
                            if(fileName!=null && !fileName.equalsIgnoreCase("null") && !fileName.equalsIgnoreCase("")){
                                fileName = fileName.trim();
                                String studyName = study.getName();
                                studyName = studyName.trim();
                                studyName = studyName.replace(" ", "_");
                                fileName = studyName + date.getTime() + fileName.substring(fileName.lastIndexOf("."));
                                int type  = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
                                ImageIO.write(resizeImage(bufferedImage, type, 64, 64), 
                                        fileName.substring(fileName.lastIndexOf(".")+1), 
                                        new File(System.getenv("OPENSHIFT_DATA_DIR") + fileName));
                                study.setImageURL(fileName);
                            }
        	    	}
        	    }

                    if(request.getParameter("Participants")!=null && !request.getParameter("Participants").trim().equals("")){
                            study.setRequestedParticipants(Integer.parseInt(request.getParameter("Participants")));
                    }

                    if(request.getParameter("Description")!=null && !request.getParameter("Description").trim().equals("")){
                            study.setDescription(request.getParameter("Description"));
                    }

                    study.setNumOfParticipants(0);
                    study.setEmail(userEmail);
                    study.setStatus("Start");
                    
                    StudyDB.addStudy(study);
                    request.setAttribute("userStudies", StudyDB.getStudiesFor(userEmail));
                    url="/studies.jsp";
        	}
        	else{
        		url="/login.jsp";
        	}
        }
        else if(action.trim().equalsIgnoreCase("Start")){
        	if(null != session.getAttribute("theUser")){
        		User user = (User) session.getAttribute("theUser");
        		String email = user.getEmail();
        		String studyCode = request.getParameter("StudyCode");
        		Study study = StudyDB.getStudy(studyCode);
        		study.setStatus("Start");
        		
                        StudyDB.updateStudy(study);
                        
        		request.setAttribute("userStudies", StudyDB.getStudiesFor(email));
        		url="/studies.jsp";
        	}
        	else{
        		url="/login.jsp";
        	}
        }
        else if(action.trim().equalsIgnoreCase("Stop")){
        	if(null != session.getAttribute("theUser")){
        		User user = (User) session.getAttribute("theUser");
        		String email = user.getEmail();
        		String studyCode = request.getParameter("StudyCode");
        		Study study = StudyDB.getStudy(studyCode);
        		study.setStatus("Stop");
        		
                        StudyDB.updateStudy(study);
                        
        		request.setAttribute("userStudies", StudyDB.getStudiesFor(email));
        		url="/studies.jsp";
        	}
        	else{
        		url="/login.jsp";
        	}
        }
        else if(action.trim().equals("answer")){
        	User user = (User) session.getAttribute("theUser");
        	if(null != user){
        		String studyCode = request.getParameter("StudyCode");
        		String choice = request.getParameter("questionanswers");
        		
        		Study study = StudyDB.getStudy(studyCode);
        		String participantEmail = user.getEmail();
        		Answer answer = AnswerDB.getAnswerFor(studyCode, participantEmail);
        		boolean alreadyAnswerFound = false;
        		if(answer!=null){
                            alreadyAnswerFound = true;
                            answer.setChoice(Integer.parseInt(choice));
                            boolean answerUpdateComplete = AnswerDB.updateAnswer(answer);
                            if(answerUpdateComplete){
                                System.out.println("Answer Updation Completed");
                            }
                            else{
                                System.out.println("Answer Updation InComplete. Rollbacked!!");
                            }
        		}
                        User participatingUser = UserDB.getUser(participantEmail);
        		if(!alreadyAnswerFound){
                            answer = new Answer();
                            answer.setEmail(participantEmail);
                            answer.setStudyCode(Long.parseLong(studyCode));
                            answer.setDateSubmitted(new Date());
                            answer.setChoice(Integer.parseInt(choice));
                            
                            study.setNumOfParticipants(study.getNumOfParticipants()+1);
                            participatingUser.setCoins(participatingUser.getCoins()+1);
                            participatingUser.setParticipations(participatingUser.getParticipations()+1);
                            
                            User creatorUser = UserDB.getUser(study.getEmail());
                            if(creatorUser!=null){
	        		creatorUser.setCoins(creatorUser.getCoins()+1);
	        		creatorUser.setParticipants(creatorUser.getParticipants()+1);
                            }
                            
                            boolean answerTransactionComplete = AnswerDB.AddAnswer(answer,study,participatingUser,creatorUser);
                            if(answerTransactionComplete){
                                System.out.println("Answer Transaction Completed");
                            }
                            else{
                                System.out.println("Answer Transaction InComplete. Rollbacked!!");
                            }
        		}
        		List<Study> studiesToPart = StudyDB.getStudiesToParticipate(participantEmail);
    			
    			// set as session attribute
                        ObjectMapper map = new ObjectMapper();
    			session.setAttribute("theUser", participatingUser);
    			session.setAttribute("theUserJSON", map.writeValueAsString(participatingUser));
    			request.setAttribute("participateStudies", studiesToPart);
        		url="/participate.jsp";
        	}
        	else {
        		url="/login.jsp";
        	}
        }
        getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
	}
	
        @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
          if (cd.trim().startsWith("filename")) {
            return cd.substring(cd.indexOf('=') + 1).trim()
                    .replace("\"", "");
          }
        }
        return null;
      }

      private BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height){
          BufferedImage bufferedImage = new BufferedImage(width, height, type);
          Graphics2D graphics2D = bufferedImage.createGraphics();
          graphics2D.drawImage(originalImage, 0, 0, width, height, null);
          graphics2D.dispose();
          return bufferedImage;
      }
}