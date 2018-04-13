package com.vmp.model.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Credential", schema = "User")
public class UserCredential {
	private Long id;
	private String login;
	private String password;
	private UserInformation information;

	public UserCredential() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "login", updatable = false, nullable = false, length = 200)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", updatable = false, nullable = false, length = 255)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "Credential", cascade = CascadeType.ALL)
	public UserInformation getInformation() {
		return information;
	}

	public void setInformation(UserInformation information) {
		this.information = information;
	}
}
