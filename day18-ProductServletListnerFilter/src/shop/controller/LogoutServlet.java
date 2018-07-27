package shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 로그아웃을 처리하는 서블릿
 * @author PC38215
 *
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//현재 요청에(request)에 연결된 세션을 얻어냄
		HttpSession session = request.getSession();
		
		//유효한 세션에 추가되었을 userid속성을 추출
		String userid = (String)session.getAttribute("userid");
		
		if(userid != null) {
			session.invalidate();
			
			request.setAttribute("message", userid+"님, 로그아웃되었습니다.");
			
/*			request.setAttribute("next", "login");
*/			request.setAttribute("next", "login");
		}
		
		request.getRequestDispatcher("/messageJsp").forward(request, response);
	}

}
