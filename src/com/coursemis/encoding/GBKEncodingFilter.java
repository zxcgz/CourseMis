package com.coursemis.encoding;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GBKEncodingFilter implements Filter {
	private FilterConfig config = null;

	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (this.config == null) {
			return;
		}
		//request.setCharacterEncoding("gb2312");
		//response.setCharacterEncoding("gb2312");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}