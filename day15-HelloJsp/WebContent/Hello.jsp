<%--
1.jsp주석  : jsp가 servlet으로 변환될 떄 무시된다.
2.<%@    : Directive Tag : 현재 페이지에 대한 설정을 지정 할 때 사용
						 : 페이지 임포트에도 사용
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01 Hello JSP</title>
</head>
<body>
<%
	//3. <%....: Scriptlet Tag : jsp안에서 순수 자바 코드를 쓸수 있도록 하는 태그
	
	int age = 55;
	List<Integer> ages = new ArrayList<>();
	ages.add(12);
	ages.add(20);
	ages.add(37);
	ages.add(45);
	ages.add(age);
	
	for (int age2 : ages){
		System.out.println("구성원의 나이" + age2);
%>
<!-- 여기에는 화면에 반복 출력할 HTML을 구성한다 -->
<%-- <%=...: Expresion tag : 변수 값 하나를 출력할 때 사용 --%>
<%	
		
	}
%>
</body>
</html>