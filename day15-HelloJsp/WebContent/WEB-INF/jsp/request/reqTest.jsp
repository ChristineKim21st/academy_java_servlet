<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 request 사용</title>
</head>
<body>
<%
	// _jspService()메소드 내부에 작성되는 부분
	List<String> fruits = new ArrayList<>();

	fruits.add("사과");
	fruits.add("메론");
	fruits.add("수박");
	fruits.add("복숭아");
	fruits.add("포도");
	fruits.add("참외");
	fruits.add("자두");
	
	//과일 목록을 request내장객체에 추가
	//내장 객체에 데이터를 추가할 때
	//attribute는 parameter와 다르다
	request.setAttribute("fruits", fruits);
	
	//추가된 attribute 추출
	List<String> fruitList = (List<String>)request.getAttribute("fruits");
%>	


	//브라우저에 출력
	<table>
	<tr>
		<td>여름 과일</td>
	</tr>
<%
	for(String fruit : fruitList){%>
	<tr>
		<td><%=fruit%></td>
	</tr>
	
<%	} //end for()
%>	
	</table>
</body>
</html>