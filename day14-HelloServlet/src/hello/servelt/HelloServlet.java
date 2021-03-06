package hello.servelt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 자바 서블릿으로 "안녕하세요, 서블릿" 메시지를 출력하는
 * 서블릿 클래스이다.
 * 접속 주소는 2가지를 제공한다.
 * HelloServlet, servlet
 * @author PC38215
 *
 */
@WebServlet({ "/HelloServlet", "/hello" })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * 상속받은 서블릿 LifeCycle관련 메소드
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//기본적으로 서블릿은 자바코드 안에 html이 들어있는 구조
		//1.응답 문서 세팅(한글 설정:UTF-8)
		response.setContentType("text/html;charset=utf-8");
		
		//2.응답을 위해서 출력이 필요
		//	출력: IO에서 Output에 해당
		//  output객체가 필요
		// (1)기본출력
		System.out.println("안녕하세요, 서블릿");
		
		// (2)브라우저 출력-->응답(객체)에 출력
		PrintWriter out = response.getWriter();
		
		//3.output객체에 익숙한 println으로 출력
		out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<BODY>");
				out.println("<H1>안녕하세요 서블릿</H1>");
				out.println("<br/>");
			out.println("</BODY>");
			out.println("</HEAD>");
		out.println("</HTML>");
		/**
		 * 사용한 out객체는 브라우져에서 사용된다.
		 */
		
		//4.사용한 객체 닫기
		out.close();
	}

}
