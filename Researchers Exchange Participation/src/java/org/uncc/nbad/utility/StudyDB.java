package org.uncc.nbad.utility;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.uncc.nbad.bean.Study;

public class StudyDB {

    public static Study getStudy(String studyCode) {
    	if(studyCode!=null && !studyCode.trim().equals("")){
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
                try {
                    Long studyCodeV = Long.parseLong(studyCode.trim());
                    Study study = em.find(Study.class, studyCodeV);
                    return study;
                } finally {
                    em.close();
                }
        }
    	return null;
    }

    public static List<Study> getStudiesFor(String email) {
        if(email!=null && !email.trim().equals("")){
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
            email = "'"+email+"'";
            String query = "SELECT study from Study as study WHERE study.email="+email;
            TypedQuery<Study> userStudies = em.createQuery(query, Study.class);
            try {
                List<Study> userStudy = userStudies.getResultList();
                if(userStudy == null || userStudy.isEmpty()){
                    userStudy = null;
                }
                return userStudy;
            } finally {
                em.close();
            }
        }
        return null;
    }
    
    public static List<Study> getStudiesToParticipate(String email) {
        if(email!=null && !email.trim().equals("")){
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
            email = "'"+email+"'";
            String query = "SELECT study from Study as study WHERE study.email <> "+email+" and study.status='START'";
            TypedQuery<Study> partipateStudies = em.createQuery(query, Study.class);
            try {
                List<Study> partStudy = partipateStudies.getResultList();
                if(partStudy == null || partStudy.isEmpty()){
                    partStudy = null;
                }
                return partStudy;
            } finally {
                em.close();
            }
        }
        return null;
    }
    
    public static boolean addStudy(Study study){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(study);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }
    
    public static boolean updateStudy(Study study){
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.merge(study);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
