package org.uncc.nbad.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uncc.nbad.bean.User;

public class UserDB {

    public static User getUser(String email) {
            if (email != null && !email.trim().equals("")) {
                EntityManager em = DBUtil.getEmFactory().createEntityManager();
                try {
                    User user = em.find(User.class, email);
                    return user;
                } finally {
                    em.close();
                }
            }
            return null;
    }

    public static User validateUser(String email, String password){
        if (email != null && !email.trim().equals("") && password != null
                && !password.trim().equals("")) {
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
                try {
                    User user = em.find(User.class, email);
                    if(user!=null && user.getPassword()!=null && !user.getPassword().trim().equals("")
                            && user.getSalt()!=null && !user.getSalt().trim().equals("")){
                        String salt = user.getSalt();
                        String passwordwithSaltCalc = PasswordUtil.hashPassword(password+salt);
                        if(passwordwithSaltCalc!=null && !passwordwithSaltCalc.trim().equals("")
                                && passwordwithSaltCalc.equals(user.getPassword())){
                            return user;
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                    return null;
                }
                finally {
                    em.close();
                }
        }
        return null;
    }

    public static boolean addUser(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(user);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public static boolean updateParticipations(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.merge(user);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public static boolean updateUserParticipants(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.merge(user);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }
    
    public static boolean updateUser(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.merge(user);
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