package org.uncc.nbad.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Answer implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String email;
    
    @Id
    private Long studyCode;
    private int choice;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSubmitted;

    public Answer() {
        this.email = "";
        this.choice = 0;
        this.studyCode = 0L;
        this.dateSubmitted  = null;
    }


    public String getEmail() {
            return email;
    }
    public void setEmail(String email) {
            this.email = email;
    }
    public int getChoice() {
            return choice;
    }
    public void setChoice(int choice) {
            this.choice = choice;
    }

    public Long getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(Long studyCode) {
        this.studyCode = studyCode;
    }
    
    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
