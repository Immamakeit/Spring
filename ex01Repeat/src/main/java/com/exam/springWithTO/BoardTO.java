package com.exam.springWithTO;

public class BoardTO {
	
	private String name;
	private String content;
	private String mail;
	
	
	public BoardTO(String content, String mail, String name) {
		// TODO Auto-generated constructor stub
		
		System.out.println("to데이터들 호출");
		this.name = name;
		this.mail = mail;
		this.content = content;
	}
	

	
	public String getName() {
		return name;
	}
	public String getContent() {
		return content;
	}
	public String getMail() {
		return mail;
	}
	
	
	
	
}
