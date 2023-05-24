package com.exam.springWithTO;

import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GenericXmlApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:com/exam/springWithTO/context.xml");
		
		//System.out.println("generic 연결");
		
		HelloBoard helloboard1 = (HelloBoard)ctx.getBean("helloboard1");
		helloboard1.execute();
		
		ctx.close();
	}

}