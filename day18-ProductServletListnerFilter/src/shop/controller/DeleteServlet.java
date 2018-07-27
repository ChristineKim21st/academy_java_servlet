package shop.controller;

import static shop.factory.WarehouseFactory.getWarehouse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GeneralWarehouse;
import shop.exception.NotFoundException;
import shop.vo.Product;

/**
 * detail.jsp에서 삭제 링크로 발생하는 요청 (delete?prodCode=xxx	)을 처리하는 서블릿
 * 
 * @author PC38215
 *
 */
@WebServlet({"/delete", "/main/delete"})
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 제품 상세 조회에서 삭제 링크를 통해 발생하는 
	 * Get요청을 처리
	 * 1. 요청파라미터 prodCode를 추출
	 * 2.삭제 delete쿼리 수행 
	 * 3 성공/ 실패 메시지
	 * 4.성공/ 실패 view선택 후 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글 처리 (요청, 응답) : 여기서는 필수는 아님
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//2.모델 생성
		//2.(1)요청 파라미터 추출
		String prodCode = request.getParameter("prodCode");
		
		//2.(2)객체로 포장
		Product product = new Product(prodCode);
		
		//2.(3)DB객체 얻기
		GeneralWarehouse warehouse;
		warehouse = getWarehouse("mybatis");
		
		//3.view관련 변수들 선언
		String view = null;
		String next = null;
		String message = null;
		
		//2.(4)delete 수행
		try {
			warehouse.remove(product);
			//2.(5)삭제 성공 메시지 발생
			message = String.format("제품 정보 [%s] 삭제에 성공하였습니다", product.getProdCode());
		} catch (NotFoundException e) {
			message = e.getMessage();
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		//3.view 선택
		//(1)1차 뷰
		view = "/messageJsp";
		//(2)2차 뷰
		next="main/list";
		request.setAttribute("next", next);
		
		//4.결정된 view로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
