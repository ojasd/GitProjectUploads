package org.uncc.nbad.servlet;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.uncc.nbad.bean.EmailSenderBean;
import org.uncc.nbad.bean.TempUser;
import org.uncc.nbad.bean.User;
import org.uncc.nbad.utility.PasswordUtil;
import org.uncc.nbad.utility.TempUserDB;
import org.uncc.nbad.utility.UserDB;

@WebServlet("/User")
public class UserController extends HttpServlet {

    /**
      * 
     **/
      private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
    	HttpSession session = request.getSession(true);
    	session.setMaxInactiveInterval(-1);
        
        String msg = "";
        String url = "/home.jsp";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            url = "/home.jsp";
        }
        // perform action and set URL to appropriate page
        else if (action.trim().equals("login")) {
        	
            // check if user exits
            String email = request.getParameter("User_Id");
            String password = request.getParameter("Password");
            User user = UserDB.validateUser(email, password);
            if(user!=null){
            	
            	msg="";
            	// set as a session attribute
            	ObjectMapper map = new ObjectMapper();
            	session.setAttribute("theUser",user);
            	session.setAttribute("theUserJSON", map.writeValueAsString(user));
                
                request.setAttribute("msg", msg);
                
                // forward to main.jsp
                url="/main.jsp";
            }
            else{
            	msg = "User Credentials not matched. Please provide correct details";
            	request.setAttribute("msg", msg);
            	
                // forward to login.jsp
            	url="/login.jsp";
            }
        }
        else if (action.trim().equals("create")) {
            // get user for specified email
            String name  = request.getParameter("Name");
            String email  = request.getParameter("User_Id");
            String password  = request.getParameter("Password");
            String confirmPassword  = request.getParameter("Conf_password");
            
            msg="";
            
            if(name==null || name.trim().equals("")){
            	msg = "Please enter name.<br/>";
            }
            
            if(email==null || email.trim().equals("")){
            	msg += "\nPlease enter email.<br/>";
            }
            
            if(password==null || password.trim().equals("")){
            	msg += "Please enter password.<br/>";
            }
            
            if(confirmPassword==null || confirmPassword.trim().equals("")){
            	msg += "Please enter Confirm password.<br/>";
            }
            
            if(password!= null && confirmPassword !=null && !(password.trim().equals(confirmPassword.trim()))){
            	msg += "Password and confirm password do not match. Please enter them correctly.<br/>";
            }
            
            if(email!= null && UserDB.getUser(email.trim()) != null){
            	msg += "User Already exists. Please use another email.<br/>";
            }
            
            if(!(msg.trim().equals(""))){
            	// set as request attribute
            	request.setAttribute("msg", msg);
            	url="/signup.jsp";
            }
            else{
            	//User user  = new User();
            	//user.setName(name);
            	//user.setEmail(email);
            	//user.setPassword(password);
            	//user.setParticipants(0);
            	//user.setParticipations(0);
            	//user.setCoins(0);
            	
            	//boolean added = UserDB.addUser(user);
            	//if(added){
                    
                //System.out.println("User Created!!!");
                try{
                    //Generate a Random number
                    /*
                    ArrayList<Integer> token = new ArrayList<Integer>();
                    for (int i=1; i<11; i++) {
                        token.add(new Integer(i));
                    }
                    Collections.shuffle(token);
                    TempUser tempUser = new TempUser();
                    tempUser.setToken(token.toString());
                    */ 
                    //Generating Random Character values                    
                    TempUser tempUser = new TempUser();
                    tempUser.setUName(name);
                    tempUser.setEmail(email);
                    tempUser.setPassword(PasswordUtil.hashAndSaltPassword(password, tempUser));
                    tempUser.setIssueDate(new Date());
                    tempUser.setToken(UUID.randomUUID().toString());
                    
                    boolean added = TempUserDB.addTempUser(tempUser);
                    if(added){
                        System.out.println("User Created!!!");
                        //Have to Write a logic for dispatching email
                        String activationUrl = request.getRequestURL().toString();
                        
                        EmailSenderBean sendMail = new EmailSenderBean();
                        String subject = "Activate you profile on ORNSurveys";
                        String message = "";
                        message = "Hello "+name+". Please activate your account of ORN Surveys using the below link. \n\n"
                                +activationUrl+"?tokenID="+tempUser.getToken()+"&action=activate";
                        sendMail.SendEmail(name, null, email, subject, message);
                        url = "/userActivate.jsp";
                    }
                    else{
                        System.out.println("Error while creating Temp User!!!");
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            	
            }
        }
        else if(action.trim().equals("activate")) {
            String tokenId = request.getParameter("tokenID");
            TempUser tempUser = TempUserDB.getUser(tokenId);
            if(tempUser!=null){
                if(new Date().getTime()<= tempUser.getIssueDate().getTime()+30000){
                    User user  = new User();
                    user.setName(tempUser.getUName());
                    user.setEmail(tempUser.getEmail());
                    user.setPassword(tempUser.getPassword());
                    user.setSalt(tempUser.getSalt());
                    user.setParticipants(0);
                    user.setParticipations(0);
                    user.setCoins(0);
                    
                    boolean added = UserDB.addUser(user);
                    if(added){
                        // set as session attribute
                        ObjectMapper map = new ObjectMapper();
                        session.setAttribute("theUser", user);
                        session.setAttribute("theUserJSON", map.writeValueAsString(user));
                        
                        // forward to main.jsp
                        TempUserDB.removeTempUser(tempUser);
                        url = "/main.jsp";
                    }
                    else{
                        System.out.println("Error while creating User!!!Please try signing up again");
                        url = "/signup.jsp";
                    }
                }
                else{
                    msg = "Token has expired. Please sign up again";
                    TempUserDB.removeTempUser(tempUser);
                    url="/signup.jsp";
                }
            }else{
                msg = "User does not exist";
                url="/signup.jsp";
            }
        }else if (action.trim().equals("forgotPassword")) {
            String email = request.getParameter("UserEmailForgot");
            User user = UserDB.getUser(email.trim());
            if(email== null || user == null){
            	msg += "User does not exist in database.<br/>";
                url="/forgotPasswordLink.jsp";
            }
            else{
                TempUser tempUser = new TempUser();
                tempUser.setUName(user.getName());
                tempUser.setEmail(email);
                try {
                    tempUser.setPassword(PasswordUtil.hashAndSaltPassword(user.getPassword(), tempUser));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tempUser.setIssueDate(new Date());
                tempUser.setToken(UUID.randomUUID().toString());
                boolean added = TempUserDB.addTempUser(tempUser);
                if(added){
                    String activationUrl = request.getRequestURL().toString();
                    EmailSenderBean sendMail = new EmailSenderBean();
                    String name = user.getName();
                    String subject = "Reset Password on your profile in ORNSurveys";
                    String message = "";
                    message = "Use the following link to reset password of your account in ORN Surveys. \n\n"
                             +activationUrl+"?action=resetPassword&tokenID="+tempUser.getToken()+"";
                    sendMail.SendEmail(name, null, email, subject, message);
                    msg="Email has been sent to your email address. Please click the link to reset password";
                    request.setAttribute("msg", msg);
                    url = "/login.jsp"; 
                }
            }
        }
        else if(action.trim().equals("resetPassword")){
            String tokenId = request.getParameter("tokenID");
            if(tokenId!=null && !"".equals(tokenId.trim()) 
                && TempUserDB.getUser(tokenId)!=null){
                TempUser tempUser = TempUserDB.getUser(tokenId);
                if(tempUser!=null){
                    if(new Date().getTime()<= tempUser.getIssueDate().getTime()+30000){
                        request.setAttribute("userEmailID", tempUser.getEmail().trim());
                        TempUserDB.removeTempUser(tempUser);
                        url="/resetPassword.jsp";
                    }
                    else{
                        msg = "Token has expired. Please sign up again";
                        TempUserDB.removeTempUser(tempUser);
                        url="/login.jsp";
                    }
                }
            }
            else{
                url="/login.jsp";
            }
        }
        else if(action.trim().equals("updatePassword")){
            String email = request.getParameter("userEmail");
            String password = request.getParameter("Password");
            String confirmPassword = request.getParameter("Conf_password");
            msg = "";

            if (password == null || password.trim().equals("")) {
                msg += "Enter password<br/>";
            }
            if (confirmPassword == null || confirmPassword.trim().equals("")) {
                msg += "Confirm password<br/>";
            }
            if (password != null && confirmPassword != null && !(password.trim().equals(confirmPassword.trim()))) {
                msg += "Password and confirm password do not match. Please enter them correctly.<br/>";
            }
            if (!(msg.trim().equals(""))) {
                request.setAttribute("msg", msg);
                request.setAttribute("userEmailID", email);
                url = "/resetPassword.jsp";
            } else {
                User user = UserDB.getUser(email.trim());
                if (user != null) {
                    String salt = user.getSalt();
                    String hashPassword = "";
                    try {
                        hashPassword = PasswordUtil.hashPassword(password + salt);
                    } catch (NoSuchAlgorithmException ex) {
                        hashPassword = ex.getMessage();
                    }
                    user.setPassword(hashPassword);
                    boolean updated = UserDB.updateUser(user);
                    if (updated) {
                        System.out.println("Password Updated");
                    } else {
                        System.out.println("Error updating password");
                    }
                    msg += "Password Updated!";
                    request.setAttribute("msg", msg);
                    ObjectMapper map = new ObjectMapper();
                    session.setAttribute("theUser",user);
                    session.setAttribute("theUserJSON", map.writeValueAsString(user));
                    url = "/main.jsp";
                } else {
                    msg += "User Doesn't exist. Please sign up!";
                    request.setAttribute("msg", msg);
                    url = "/login.jsp";
                }
                
            }
        }
        else if (action.trim().equals("how")) {
            
            // check if user is in session
        	if(null != session.getAttribute("theUser")){
        		// forward to main.jsp
        		url="/main.jsp";
        	}            
        	else{
        		// forward to how.jsp
        		url="/how.jsp";
        	}
        }
        else if (action.trim().equals("about")) {
            
        	// check if user is in session
        	if(null != session.getAttribute("theUser")){
        		// forward to aboutl.jsp
        		url="/aboutl.jsp";
        	}            
        	else{
        		// forward to about.jsp
        		url="/about.jsp";
        	}
        }
        else if (action.trim().equals("home")) {
            
        	// check if user is in session
        	if(null != session.getAttribute("theUser")){
        		// forward to main.jsp
        		url="/main.jsp";
        	}            
        	else{
                        Cookie [] cookies = request.getCookies();
                        boolean remoteHostFound = false;
                        boolean remotePortFound = false;
                        
                        String remoteHost = "Client Host";
                        String remotePort = "Client Port";
                        if(cookies!=null){
                            for (Cookie cookie: cookies){
                                if(remoteHost.equals(cookie.getName())){
                                    remoteHostFound  = true;
                                }
                                if(remotePort.equals(cookie.getName())){
                                    remotePortFound = false;
                                }
                            }
                        }
                        Cookie cookieHost = null;
                        if(!remoteHostFound){
                            cookieHost = new Cookie("Clhost",request.getRemoteHost());
                            cookieHost.setPath("/");
                            cookieHost.setMaxAge(60*60*24*365*2);
                            response.addCookie(cookieHost);
                        }
                        
                        Cookie cookiePort = null;
                        if(!remotePortFound){
                            cookiePort = new Cookie("ClPort",((Integer)request.getRemotePort()).toString());
                            cookiePort.setPath("/");
                            cookiePort.setMaxAge(60*60*24*365*2);
                            response.addCookie(cookiePort);
                        }
        		url="/home.jsp";
        	}
        }
        else if (action.trim().equals("main")) {
            
        	// check if user is in session
        	if(null != session.getAttribute("theUser")){
        		// forward to main.jsp
        		url="/main.jsp";
        	}            
        	else{
        		// forward to index.jsp
        		url="/login.jsp";
        	}
        }
        else if(action.trim().equals("logout")){
        	session.setAttribute("theUser", null);
        	url="/home.jsp";
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
}