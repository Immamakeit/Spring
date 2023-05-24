package com.exam.spring01;

public class HelloSpring {
	public String name;
	
	public HelloSpring() {
		// TODO Auto-generated constructor stub
		System.out.println("Hello Spring 호출");
	
	}
	
	public HelloSpring(String name) {
		// TODO Auto-generated constructor stub
		System.out.println("Hello Spring(String name) 호출");
		this.name = name;
	
	}
	
	public HelloSpring(String firstname, String lastname) {
		// TODO Auto-generated constructor stub
		System.out.println("Hello Spring(String firstname, String lastname)  호출");
		this.name = lastname +" "+ firstname;
	
	}
	
	public void execute() {
		
		System.out.println("Hello" + this.name);

	}

}
