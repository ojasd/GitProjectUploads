package org.uncc.nbad.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Study implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
        @Id
	private Long studyCode;
        private String name;
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date dateCreated;
	private String email;
	private String question;
	private String imageURL;
	private int requestedParticipants;
	private int numOfParticipants;
	private String description;
	private String status;
	
        
        public Study() {
            this.name = "";
            this.studyCode = 0L;
            this.dateCreated = null;
            this.email = "";
            this.question = "";
            this.imageURL = "";
            this.requestedParticipants = 0;
            this.numOfParticipants = 0;
            this.description = "";
            this.status = "";
        }
        
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getStudyCode() {
		return studyCode;
	}
	public void setCode(Long studyCode) {
		this.studyCode = studyCode;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getRequestedParticipants() {
		return requestedParticipants;
	}
	public void setRequestedParticipants(int requestedParticipants) {
		this.requestedParticipants = requestedParticipants;
	}
	public int getNumOfParticipants() {
		return numOfParticipants;
	}
	public void setNumOfParticipants(int numOfParticipants) {
		this.numOfParticipants = numOfParticipants;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
        }
	
	public String getImageURL(){
		return imageURL;
		
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
        
        public int getAverage(){
		return 0;
	}
	
	public int getMinimum(){
		return 0;
	}
	
	public int getMaximum(){
		return 0;
	}
	
	public int getSD(){
		return 0;
	}
}
