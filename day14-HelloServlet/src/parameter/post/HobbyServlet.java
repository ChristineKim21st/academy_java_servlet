package parameter.post;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/HobbyServlet", "/hobby" })
public class HobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.요청에 한글 입력이 존재하면 요청객체에도 한글 설정이 필요
		request.setCharacterEncoding("utf-8");
		//2.응답 객체 한글 설정
		response.setContentType("text/html;charset=utf-8");
		
		//3.<form>에서 넘어온 파라메터 추출
		String username = request.getParameter("username");
		String[] hobbies = request.getParameterValues("hobby");
		
		//4.출력
		// (1)기본 출력: sysout
		System.out.println("등록된 사용자 이름:"+username);
		if(hobbies != null) {
			for(String hobby : hobbies) {
				System.out.println("등록된 취미:"+hobby);
			}
		}else {
			System.out.println("등록된 취미가 없습니다");
		}
		// (2)브라우저 출력: printWrite 객체 출력
		PrintWriter out = response.getWriter();
		out.println("<HTML><BODY>");
		out.println("등록된 사용자 이름:"+username+"<br/>");
		if(hobbies != null) {
			for(String hobby : hobbies) {
			out.println("등록된 취미:"+hobby);
			}
		}else {
			System.out.println("등록된 취미가 없습니다");
		}
		out.println("</BODY>");	
	
		out.close();
	}
		
}
