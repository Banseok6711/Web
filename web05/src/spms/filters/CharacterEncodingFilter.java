package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{

	FilterConfig config;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain nextFilter) throws IOException, ServletException {
		System.out.println("doFilter 실행!");
		req.setCharacterEncoding(config.getInitParameter("encoding"));
		resp.setCharacterEncoding(config.getInitParameter("encoding"));
		nextFilter.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config=config;
		
	}

}
