/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uncc.nbad.servlet;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.uncc.nbad.bean.EmailSenderBean;
import org.uncc.nbad.utility.DBUtil;

/**
 *
 * @author Ojas
 */
@WebServlet(name = "EmailListServlet", urlPatterns = {"/EmailListServlet"})
public class EmailListServlet extends HttpServlet {

    public EmailSenderBean sendMail;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url="";
        response.setContentType("text/html;charset=UTF-8");
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        String action  = request.getParameter("action");
        String name = request.getParameter("Name");
        String fromEmail = request.getParameter("User_Id");
        String toEmail = request.getParameter("Friend_Id");
        String message = request.getParameter("Message");
        
        
        sendMail = new EmailSenderBean();
        
        String subject ="";
        if(action.equals("contact")){
            subject = "Contact made via ORNSurveys";
            message = name+" wants to contact you via ORN SURVEYS"+message;
            sendMail.SendEmail(name, null, toEmail, subject, message);
            url = "/confirmc.jsp";
        }else if(action.equals("recommend")){
            subject = "Recommendation to join ORNSurveys";
            message = name+" wants to recommend you to join ORN SURVEYS"+message;
            sendMail.SendEmail(name, fromEmail, toEmail, subject, message);
            url = "/confirmr.jsp";
        }
        
        em.close();
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        
    }
}
