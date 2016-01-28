package org.uncc.nbad.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
        @Id
	private String email;
	private String password;
        private String salt;
	private int Coins;
	private int participants;
	private int participations;
	
        public User(){
            this.name = "";
            this.email = "";
            this.password = "";
            this.salt = "";
            this.Coins = 0;
            this.participants = 0;
            this.participations = 0;
        }
        
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
            this.password = password;
	}
        public String getSalt() {
            return salt;
        }
        public void setSalt(String salt) {
            this.salt = salt;
        }
	public int getCoins() {
            return Coins;
	}
	public void setCoins(int coins) {
            this.Coins = coins;
	}
	public int getParticipants() {
            return participants;
	}
	public void setParticipants(int participants) {
            this.participants = participants;
	}
	public int getParticipations() {
            return participations;
	}
	public void setParticipations(int participations) {
            this.participations = participations;
	}
}
