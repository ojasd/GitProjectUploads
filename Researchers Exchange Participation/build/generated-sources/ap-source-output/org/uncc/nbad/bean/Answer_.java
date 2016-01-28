package org.uncc.nbad.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-01T11:41:02")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, Long> studyCode;
    public static volatile SingularAttribute<Answer, Integer> choice;
    public static volatile SingularAttribute<Answer, String> email;
    public static volatile SingularAttribute<Answer, Date> dateSubmitted;

}