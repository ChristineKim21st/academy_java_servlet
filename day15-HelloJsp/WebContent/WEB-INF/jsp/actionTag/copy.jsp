<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 메인 화면 하단에 삽입되어서 저작권 표기(copyright)을 나타내는 푸터로 사용되는 페이지-->
<%
	//jsp의 내장객체request로 부터 main.jsp에서 설정된
	//요청 파라미터를 추출 할 수 있다.
	String color = request.getParameter("color");
	
%>
	<font color="<%=color%>">
		copyright &copy; gildonge.com
	</font>
</body>
</html>