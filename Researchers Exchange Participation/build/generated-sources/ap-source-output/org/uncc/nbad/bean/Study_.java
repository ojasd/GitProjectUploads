package org.uncc.nbad.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-01T11:41:02")
@StaticMetamodel(Study.class)
public class Study_ { 

    public static volatile SingularAttribute<Study, Date> dateCreated;
    public static volatile SingularAttribute<Study, String> question;
    public static volatile SingularAttribute<Study, Integer> requestedParticipants;
    public static volatile SingularAttribute<Study, String> imageURL;
    public static volatile SingularAttribute<Study, String> name;
    public static volatile SingularAttribute<Study, Integer> numOfParticipants;
    public static volatile SingularAttribute<Study, String> description;
    public static volatile SingularAttribute<Study, Long> studyCode;
    public static volatile SingularAttribute<Study, String> email;
    public static volatile SingularAttribute<Study, String> status;

}