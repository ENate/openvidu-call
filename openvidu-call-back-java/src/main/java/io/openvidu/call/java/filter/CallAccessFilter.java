package io.openvidu.call.java.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

public class CallAccessFilter extends OncePerRequestFilter {

	@Value("admin")
	private String CALL_USER;

	@Value("MY_SECRET")
	private String CALL_SECRET;

	@Value("${CALL_PRIVATE_ACCESS}")
	private String CALL_PRIVATE_ACCESS;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		filterChain.doFilter(request, response);
	}
}
