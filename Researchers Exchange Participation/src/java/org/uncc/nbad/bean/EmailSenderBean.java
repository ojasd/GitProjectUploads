
package org.uncc.nbad.bean;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Stateless
public class EmailSenderBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void SendEmail(String name, String fromEmail, String toEmail, String subject, String message) {
        
        Properties props = System.getProperties();
        
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        String [] toEmails = {toEmail};
        String emailSubject = subject;
	String emailBody = message;
        
        Session session = Session.getDefaultInstance(props, null);
        Message mailMessage = new MimeMessage(session);
        
        try {
            String fromUser = "nbad.dummy@gmail.com";
            String fromUserEmailPassword = "Nbaddummy1";
            
            if(fromEmail!=null && !fromEmail.trim().equals("")){
                mailMessage.setFrom(new InternetAddress(fromEmail));
            }
            else{
                mailMessage.setFrom(new InternetAddress(fromUser));
            }
            for (String toEmail1 : toEmails) {
                mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail1));
            }
            mailMessage.setSubject(emailSubject);
            mailMessage.setContent(emailBody, "text/html");
            
            String emailHost = "smtp.gmail.com";
            Transport transport = session.getTransport("smtp");
            transport.connect(emailHost, fromUser, fromUserEmailPassword);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();
        }
        catch(AddressException ex){
            ex.printStackTrace();
            Logger.getLogger(EmailSenderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (MessagingException ex) {
            ex.printStackTrace();
            Logger.getLogger(EmailSenderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
