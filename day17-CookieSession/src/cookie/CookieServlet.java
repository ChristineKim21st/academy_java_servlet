package cookie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie/login.jsp에서 전송된 로그인 정보를 사용하여
 * ________________________________________
 * 
 * 1.가상의 로그인 성공처리를 한뒤
 * 2.로그인 성공 정보를 Cookie로 생성
 * 3.응답객체에 추가 후
 * 4.로그인 성공 페이지로 이동시키며 쿠키를 테스트
 * ________________________________________
 * 시키며 쿠키를 테스트하는 서블릿
 * 
 *
 */
@WebServlet({ "/CookieServlet", "/cookie/login" })
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Cookie/login의 주소로 Get요청 발생시
	 * 쿠키로 로그인 jsp로 페이지 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.뷰 결정 (web.xml에 등록된 url 매핑 주소)
		String view = "/views/cookie/login";
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글처리
		// (1) 요청 한글 처리
		request.setCharacterEncoding("utf-8");
		// (2) 응답 한글 처리
		response.setContentType("text/html;charset=utf-8");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		//3.로그인 성공을 가정함
		//아이디=java, 비밀번호가=jsp 인경우 로그인 성공
		if("java".equals(userId)&&"jsp".equals(password)) {
			Cookie ckUserId = new Cookie("userId", userId);
			Cookie ckPassword = new Cookie("password", password);
			
			ckUserId.setMaxAge(10);
			ckPassword.setMaxAge(10);
			
			response.addCookie(ckPassword);
			response.addCookie(ckUserId);
		}
		
		//4.로그인 성공 페이지로 이동
		ServletContext context = getServletContext();
		String view = context.getContextPath()
						+"/cookie/views/success";
		response.sendRedirect(view);
	}

}
