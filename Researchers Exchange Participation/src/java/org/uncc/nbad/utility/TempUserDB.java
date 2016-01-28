/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uncc.nbad.utility;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.uncc.nbad.bean.TempUser;

/**
 *
 * @author Ojas
 */
public class TempUserDB {
    public static TempUser getUser(String token) {
            if (token != null && !token.trim().equals("")) {
                EntityManager em = DBUtil.getEmFactory().createEntityManager();
                try {
                    String query = "select tempuser from TempUser as tempuser where tempuser.token='"+token+"'";
                    TypedQuery<TempUser> tempUserQuery = em.createQuery(query, TempUser.class);
                    try {
                        TempUser tempUser = (TempUser)tempUserQuery.getSingleResult();
                        return tempUser;
                    }catch(Exception e){
                        return null;
                    }
                } finally {
                    em.close();
                }
            }
            return null;
    }
    
    
    public static TempUser addTempUser(String UName, String Email, String pass, Date date, String token){     
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            
        }
        finally{
            em.close();
        }
        return null;
    }
    
    public static boolean addTempUser(TempUser tempUser){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(tempUser);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }
    
    public static boolean removeTempUser(TempUser tempUser){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            TempUser tempUserToBeRemoved = em.find(TempUser.class, tempUser.getEmail());
            em.remove(tempUserToBeRemoved);
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
