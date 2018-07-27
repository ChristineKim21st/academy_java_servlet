package sample.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName="nextFilter", urlPatterns= {"/next/*"})
public class NextFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("next Filter init()");
	}
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//1.전처리
		System.out.println("===========next필터 시작=================");
		//2.다음필터 진행
		chain.doFilter(req, res);
		
		//3.후처리
		System.out.println("===========next필터 종료=================");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}



}
