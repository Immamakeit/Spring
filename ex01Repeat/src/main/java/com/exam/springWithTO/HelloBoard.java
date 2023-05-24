package com.exam.springWithTO;

public class HelloBoard {
	
	private BoardTO to;
	
	
	public HelloBoard(BoardTO to) {
		// TODO Auto-generated constructor stub
		System.out.println("hello board  with to 호출");
		this.to = to;
	
	}
	
	public void execute() {
		
		System.out.println("내용= " + to.getContent());
		System.out.println("메일= " +to.getMail());
		System.out.println("이름= " +to.getName());
		
	}
	
	

}
