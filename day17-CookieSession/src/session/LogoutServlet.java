package session;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({ "/logout", "/session/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.이미 존재하는 세션을 얻어냄
		HttpSession session;
		session = request.getSession(false);
		/**
		 * 로그아웃되기 위해서는 얻어진 세션이
		 * 이전에 로그인되었던 것이라는 보증이 필요함
		 * 따라서, false옵션으로 현재의 요청과
		 * 연결되어있던 세션을 얻어내야한다.
		 */
		
		if(session != null) {
			//2.로그인되어있던 세션이 있으면 해제
			session.invalidate();
			//invalidate를 한 후에는 세션 객체에 설정되어있던 모든 속성도 자동 삭제됨
		}
		//3.로그아웃처리 되었으므로 로그인 화면으로 이동
		ServletContext context = getServletContext();
		String path = context.getContextPath();
		String location = path + "/session/login";
		
		response.sendRedirect(location);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
