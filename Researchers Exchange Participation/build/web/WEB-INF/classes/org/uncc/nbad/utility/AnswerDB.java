/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uncc.nbad.utility;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.uncc.nbad.bean.Answer;
import org.uncc.nbad.bean.Study;
import org.uncc.nbad.bean.User;

/**
 *
 * @author Rohit
 */
public class AnswerDB {
    
    public static boolean updateAnswer(Answer ans){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(ans);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }
    
    public static boolean AddAnswer(Answer ans, Study study, User participatingUser, User creatorUser){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(ans);
            boolean studyUpdatedCorrectly = StudyDB.updateStudy(study);
            if(studyUpdatedCorrectly){
                System.out.println("User Study Updated");
            }
            else{
                System.out.println("Error in Study Update");
            }
            
            boolean participationUpdated = UserDB.updateParticipations(participatingUser);
            if(participationUpdated){
                System.out.println("Participations and Coins Updated");
            }
            else{
                System.out.println("Error in Participations and Coins Update");
            }

            boolean participantsUpdate = UserDB.updateUserParticipants(creatorUser);
            if(participantsUpdate){
                System.out.println("Participants and Coins Updated");
            }
            else{
                System.out.println("Error in Participants and Coins Update");
            }
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }
    
    public static Answer getAnswerFor(String studyCode, String email){
        if (studyCode != null && !studyCode.trim().equals("") 
                && email != null && !email.trim().equals("")) {
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
            String query = "SELECT answer from Answer as answer WHERE answer.email = '"+email+"'";
            TypedQuery<Answer> userAns = em.createQuery(query, Answer.class);
            try {
                List<Answer> userAnswers = userAns.getResultList();
                for(Answer ans : userAnswers){
                    if(ans.getStudyCode()!=null && ans.getStudyCode()!=0
                            && ans.getStudyCode() == Long.parseLong(studyCode)){
                        return ans;
                    }
                }
            }catch(Exception e){
                return null;
            }
            finally {
                em.close();
            }
        }
        return null;
    }
}
