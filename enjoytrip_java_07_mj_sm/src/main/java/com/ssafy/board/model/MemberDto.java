package com.ssafy.board.model;

public class MemberDto {
	private String name;
	private String id;
	private String pw;
	private String email;
	private String domain;

	public MemberDto(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public MemberDto(String name, String id, String pw, String email, String domain) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
