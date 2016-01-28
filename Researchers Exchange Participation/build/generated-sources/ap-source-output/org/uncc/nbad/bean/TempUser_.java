package org.uncc.nbad.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-01T11:41:02")
@StaticMetamodel(TempUser.class)
public class TempUser_ { 

    public static volatile SingularAttribute<TempUser, String> UName;
    public static volatile SingularAttribute<TempUser, String> password;
    public static volatile SingularAttribute<TempUser, String> Email;
    public static volatile SingularAttribute<TempUser, String> salt;
    public static volatile SingularAttribute<TempUser, Date> issueDate;
    public static volatile SingularAttribute<TempUser, String> token;

}