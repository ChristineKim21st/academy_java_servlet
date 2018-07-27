package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.exception.NotFoundException;
import book.vo.Book;

/**
 * Servlet implementation class Deleteservlet
 */
@WebServlet({"/delete", "/main/delete"})
public class Deleteservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.모델생성
		//	(1)요청 파라미터 추출
		String bookId = request.getParameter("bookId");
		
		//	(2)객체로 포장
		Book book = new Book(bookId);
	
		//	(3)DB 조회에 사용할 객체 준비
		BookShelf bookshelf;
		bookshelf = (BookShelf)getServletContext().getAttribute("bookshelf");
	
		//2.(1)view관련 변수들 선언
		String view = null;
		String next = null;
		String message = null;
		
		//	(4)delete수행
		try {
			bookshelf.delete(book);
			
			//(5).1삭제에 성공했다는 메시지를 발생
			message = String.format("책 [%s] 삭제에 성공하였습니다", book.getBookId());

		} catch (NotFoundException e) {
			//(5).2 삭제하고자 하는 책이 없을 때
			message = e.getMessage();
			e.printStackTrace();
		}
		
		request.setAttribute("message", message);
		
		//2.(2)view 선택
		//(1)1차 뷰
		view = "/messageJsp";
		//(2)2차 뷰
		next="main/list";
		request.setAttribute("next", next);
		
		//3.해당 view로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
