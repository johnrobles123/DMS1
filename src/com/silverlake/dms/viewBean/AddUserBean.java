package com.silverlake.dms.viewBean;

import java.util.Date;

public class AddUserBean {

	// private int id;
	private String fName;
	private String lName;
	private String email;
	private String pword;
	private Date dateCreated;
	private String admin;

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	public Date dateCreated() {
		return dateCreated;
	}

	public void setDateCrated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}