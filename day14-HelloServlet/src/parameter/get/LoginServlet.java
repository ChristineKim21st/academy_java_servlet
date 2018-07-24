package parameter.get;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 마치로그인이 처리되는 것 같이
 * HTML에서 <form>에서 생성된 request를 처리하는 서블릿
 * @author PC38215
 *
 */
@WebServlet({"/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1.한글 세팅
		// (1)요청에 한글 입력이 존재하면 요청객체에도 한글 설정이 필요
		request.setCharacterEncoding("utf-8");
		// (2)응답 객체 한글 설정
		response.setContentType("text/html;charset=utf-8");

		//2.요청으로부터 파라미터 추출
		//requset로 <form>을 통앟ㄴ 값이 전달될 경우
		//요청주소? 키 = 값&키 = 값&키...
		//자바에서 요청 파라미터는 항상 string 으로 전달 됨
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//출력
		// (1)기본 출력: sysout
		System.out.println("=화면에서 전달 된 데이터=");
		System.out.println("username:"+ username);
		System.out.println("password:"+password);
		// (2)브라우저 출력: printWrite 객체 출력
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<BODY>");
			out.println("화면에서 전달 된 데이터");
			out.println("username:"+ username);
			out.println("password:"+password);
		out.println("</BODY>");
		out.println("</HEAD>");
		out.println("</HTML>");
		
		out.close();
	}

}
