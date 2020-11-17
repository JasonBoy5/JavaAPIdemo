package com.jason.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class AFilter
 */
@WebFilter("/*")
public class AFilter implements Filter {
	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public AFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(config.getServletContext().getRealPath("/html"));
		System.out.println(config.getServletContext().getResourcePaths("/html"));
		System.out.println(config.getServletContext().getContextPath());
		request.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println(req.getContextPath());
		if(req.getMethod().equals("GET")) {
			EncodingRequest encodingReq = new EncodingRequest(req);
			chain.doFilter(encodingReq, response);
		}else if(req.getMethod().equals("POST")) {
			chain.doFilter(request, response);
		}
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}
