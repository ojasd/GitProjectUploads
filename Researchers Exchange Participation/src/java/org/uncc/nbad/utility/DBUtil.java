package org.uncc.nbad.utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("Ojas_Assignment3PU");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}