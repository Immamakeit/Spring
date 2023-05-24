package com.exam.spring01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
			GenericXmlApplicationContext ctx
			= new GenericXmlApplicationContext("classpath:com/exam/spring01/context.xml");
			
			HelloSpring helloSpring1 = (HelloSpring)ctx.getBean("helloSpring1");
			helloSpring1.execute();
			
			HelloSpring helloSpring2 = (HelloSpring)ctx.getBean("helloSpring2");
			helloSpring2.execute();
		
			HelloSpring helloSpring3 = (HelloSpring)ctx.getBean("helloSpring3");
			helloSpring3.execute();
			
			
			ctx.close();
					
	}

}
