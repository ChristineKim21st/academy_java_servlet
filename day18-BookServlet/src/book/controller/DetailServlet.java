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


@WebServlet({"/detail", "/main/detail"})
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.모델 생성
		// (1) 화면에서 얻어온 파라미터 추출
		//     ==>조회할 책 아이디
		//	   ==>list.jsp에서 책명을 클릭했을 때 넘어온 값
		String bookId = request.getParameter("bookId");
		
		// (2) 추출한 책명을  Book 객체로 포장
		Book book = new Book(bookId);
		
		// (3)DB 조회에 사용할 객체 준비
		BookShelf bookshelf;
		bookshelf = (BookShelf)getServletContext().getAttribute("bookshelf");
		
		//2.(1)view관련 변수들 선언
		String view = null;
		String next = null;
		String message = null;
		
		try {
			//DB 에서 1건 상세 조회
			Book found = bookshelf.select(book);
			
			//조회된 상세 정보를 request 에 속성 추가
			request.setAttribute("book", found);
			
			//1차 뷰 선택
			view = "/detailJsp";
		} catch (NotFoundException e) {
			/// 조회하려는 제품이 없을 때 메시지 생성
			message = e.getMessage();
			
			// request 에 속성 추가
			request.setAttribute("message", message);
			
			// 3.(1) 1차 뷰 선택
			view = "/messageJsp";

			// 3.(2) 2차 뷰 선택 & 속성으로 추가
			next = "main/list";
			request.setAttribute("next", next);
			
			e.printStackTrace();
		}
		
		//모델에 맞는 뷰를 생성
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		//해당 view로 이동
		reqd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
