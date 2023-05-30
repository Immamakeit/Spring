package config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model1.BoardDAO;
import model1.BoardTO;

@Controller
public class BoardController {

	@RequestMapping("/board_list1.do")
	public ModelAndView board_list() {
		BoardDAO dao = new BoardDAO();
		
		ArrayList<BoardTO> lists = dao.boardList();
		
		System.out.println("board_list1() 호출");
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("board_list1");
		modelAndView.addObject("lists", lists);

		return modelAndView;
	}
	
	@RequestMapping("/board_write1.do")
	public ModelAndView board_write() {

		ModelAndView modelAndView = new ModelAndView();
		System.out.println("/board_write1.do호출");
	
		modelAndView.setViewName("board_write1");
		
		return modelAndView;
	}
	
	@RequestMapping("/board_write1_ok.do")
	public ModelAndView board_write_ok(HttpServletRequest request) {
		System.out.println("/board_write1_ok.do호출");
		BoardDAO dao = new BoardDAO();
		BoardTO to = new BoardTO();
		
		ModelAndView modelAndview = new ModelAndView();
		
		to.setSubject(request.getParameter("subject"));
		to.setWriter(request.getParameter("writer"));
	    to.setMail(request.getParameter("mail"));
	    to.setPassword(request.getParameter("password"));
	    to.setContent(request.getParameter("content"));
	    to.setWip(request.getRemoteAddr());
		
		int flag = dao.WriteOk(to);
		
		System.out.println("\n"+"flag => "+flag+"\n");
		modelAndview.setViewName("board_write1_ok");
		modelAndview.addObject("flag", flag);
		
	

		return modelAndview;
	}
	
	@RequestMapping("/board_view1.do")
	public ModelAndView board_view(HttpServletRequest request) {
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		
		BoardDAO dao = new BoardDAO();
		to = dao.view(to);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board_view1");
		modelAndView.addObject("to",to);
		

		return modelAndView;
	}
	
	@RequestMapping("/board_delete1.do")
	public ModelAndView board_delete(HttpServletRequest request) {
		  BoardTO to = new BoardTO();
		    to.setSeq(request.getParameter("seq"));
	

		    BoardDAO dao = new BoardDAO();
		    to = dao.delete(to);

		    System.out.println("\n" + to.getSubject() + "\n");
		    System.out.println("\n" + to.getWriter() + "\n");

		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("board_delete1");
		    modelAndView.addObject("to", to);

		    return modelAndView;
	}
	
	@RequestMapping("/board_delete1_ok.do")
	public ModelAndView board_delete_ok(HttpServletRequest request) {
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		to.setPassword(request.getParameter("password"));
		
		BoardDAO dao = new BoardDAO();
		int flag = dao.delete_ok(to);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board_delete1_ok");
		modelAndView.addObject("to",to);
		modelAndView.addObject("flag",flag);
		
		return modelAndView;
	}
	
	
	
	
	@RequestMapping("/board_modify1.do")
	public ModelAndView board_modify(HttpServletRequest request) {
		BoardTO to = new BoardTO();
		to.setSeq( request.getParameter( "seq" ) );

		BoardDAO dao = new BoardDAO();
		to = dao.modify( to );
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board_modify1");
		modelAndView.addObject("to",to);
		return modelAndView;
	}
	
	
	@RequestMapping("/board_modify1_ok.do")
	public ModelAndView board_modify_ok(HttpServletRequest request) {
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		to.setSubject( request.getParameter( "subject" ) );
		to.setMail( "" ) ;
		if( !request.getParameter("mail1").equals("") 
				&& !request.getParameter("mail2").equals("") ) {
			to.setMail( request.getParameter( "mail1" ) + "@" + request.getParameter( "mail2" ) );	
		}
		
		to.setPassword( request.getParameter( "password" ) );
		to.setContent( request.getParameter( "content" ) );
		
		BoardDAO dao = new BoardDAO();
		int flag = dao.modify_ok(to);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board_modify1_ok");
		modelAndView.addObject("to",to);
		modelAndView.addObject("flag",flag);
		return modelAndView;
	}

}
