package com.ohh.model;

import javax.persistence.*;
//import java.io.Serializable;

@Entity  
@Table(name="Users") 
public class Users{
//	private static final long serialVersionUID = -8036219797322639507L;
	private String id;
	private String userName;
	private String password;
	private String email;
	private String description;
	private String profil;
	private String gender;
	
	@Id
	@Column(name = "id")
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	@Column(name = "userName")
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "email")  
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "profil")
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
