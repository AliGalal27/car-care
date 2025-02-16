package org.example.carcare.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int user_id;
	private String full_name;
	private String email;
	private String password;
	private String phone_number;
	private String role;
	
	public int getUser_id() { 
		return user_id; 
	}
	
	public void setUser_id(int user_id) { 
		this.user_id = user_id; 
	}
	
	
	public String getFull_name() { 
		return full_name; 
	}
	
	public void setFull_name(String full_name) {
		this.full_name = full_name;
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
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
