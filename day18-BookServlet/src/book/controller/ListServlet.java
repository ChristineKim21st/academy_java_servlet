package book.controller;

import static book.factory.BookShelfFactory.getBookShelf;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.vo.Book;

/**
 * book 전체 목록을 조회하는 서블릿
 * @author PC38215
 *
 */
@WebServlet({"/list", "/main/list"})
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.조회에 필요한 객체 선언
		BookShelf bookshelf;
		bookshelf = getBookShelf("mybatis");
		
		//2.조회 결과를 request에 속성으로 추가
		List<Book> books = bookshelf.select();
		request.setAttribute("books", books);
		
		//3.조회 결과가 추가된 request를 적절한 목록 뷰(list.jsp)로 전달(페이지 이동)
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher("/listJsp");
		
		reqd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
