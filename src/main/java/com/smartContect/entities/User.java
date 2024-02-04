package com.smartContect.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank()
	@Size(max = 15,min = 3)
	private String name;
	
	@Column(unique = true)
	@Email()
	private String email;
	
	@NotBlank()
	@Size(max = 100)
	//	@Size(max = 10,min = 6)
	private String password;
	
	private String role;
	private boolean enabled;
	private String imgUrl;
	
	@Size(max = 500,min = 0)
	private String About;
	
	@AssertTrue()
	private boolean agreed;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private List<Contact> ContectList = new ArrayList<>();
	
	
	
	public List<Contact> getContectList() {
		return ContectList;
	}
	public void setContectList(List<Contact> contectList) {
		ContectList = contectList;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getAbout() {
		return About;
	}
	public void setAbout(String about) {
		About = about;
	}
	
	
	public boolean isAgreed() {
		return agreed;
	}
	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}
	
	
	public User(int id, String name, String email, String password, String role, boolean enabled, String imgUrl,
			String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.imgUrl = imgUrl;
		About = about;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserClass [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", enabled=" + enabled + ", imgUrl=" + imgUrl + ", About=" + About + ", ContectList="
				+ ContectList + "]";
	}
	
	
	
	
	
	
	
	
}
