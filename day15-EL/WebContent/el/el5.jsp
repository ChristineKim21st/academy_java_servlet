<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL (5) 내장 객체의 레벨</title>
</head>
<body>
	<h3>내장 객체의 레벨</h3>
	<pre>
	1. page : 현재 페이지에만 사용 가능한 범위를 가짐
	
	2. request : 응답(request)이 일어나기 전까지 유지되는 객체
				 응답이 일어나기 전까지는 동일한 request가 유지됨
				 지속적으로 여러 페이지를 통과하며 전달 될 수 있음
				 응답이 일어나기 전까지는 해당 request에 설정된 attribute가 유지된다
				 모든 요청은 응답이 발생하면 소멸된다.
				 
	3. session : 명시적으로 생성하는 객체. 명시적으로 제거할 때까지 유지.
				 세션은 요청에 연결되어 있어서 요청으로부터 추출하여 생성.
				 세션은 요청에 대한 응답이 일어나도 자동 소멸하지 않는다.
				 명시적으로 invalidate를 시켜야 소멸된다.
				 보통은 로그인 정보등을 유지할 때 사용한다.
				 
	4. application : 하나의 웹 어플리케이션당 1개씩만 생성되는 객체
					 웹 컨테이너에 하나의 서비스가 배포되어서 유지되는 동안	계속해서 유지된다.
					 즉 유지 범위가 가장 길다.
	*내장객체의 유지 범위
	page => request => session => application
	
	EL의 내장객체
	pageScope => requestScope => sessionScope => applicationScope				 		  
	</pre>
	
<%
	pageContext.setAttribute("id", "gidong-page");

	request.setAttribute("id", "gildong-req");
	request.setAttribute("name", "길동이-req");
	
	session.setAttribute("id", "gildong-session");
	session.setAttribute("name", "길동이-session");
	
	application.setAttribute("id", "gildong-app");
%>	

<h3>EL 의 내장객체에 설정된 속성(attribute) 추출</h3>
<pre>
1. pageScope : \${pageScope.id} =  ${pageScope.id} 
2. requestScope : \${requestScope.id} =  ${requestScope.id} 
3. sessionScope : \${sessionScope.id} =  ${sessionScope.id} 
4. applicationScope : \${applicationScope.id} =  ${applicationScope.id} 

5.범위를 명시하지 않고 속성 값만 명시할 때 어떤 범위가 선택되는가
\${id } = ${id}
\${name } = ${name}
</pre>

<h3>scriptlet, expressiont Tag를 사용하여 속성 추출</h3>
<%
	String pageId = (String) pageContext.getAttribute("id");
	String reqId = (String) request.getAttribute("id");
	String sessId  = (String) session.getAttribute("id");
	String appId = (String) application.getAttribute("id");
	
	String reqName = (String) request.getAttribute("name");
	String seesName = (String) session.getAttribute("name");
	
%>
1.page Id = <%=pageId %>
1.req Id = <%=reqId %>
1.sess Id = <%=sessId %>
1.app Id = <%=appId %>

</body>
</html>