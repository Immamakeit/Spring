<%@page import="model1.BoardTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	BoardTO to = (BoardTO)request.getAttribute("to");
	int flag = (Integer)request.getAttribute("flag");
	
	out.println("<script typr='text/javascript'>");
	if(flag == 0){
		out.println("alert('글삭제에 성공');");
		out.println("location.href='board_list1.do';");
	}else if(flag==1){
		out.println("alert('비밀번호 오류');");
		out.println("history.back();");
	}else{
		out.println("alert('글삭제에 실패');");
		out.println("history.back();");
	}
	out.println("</script>");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>