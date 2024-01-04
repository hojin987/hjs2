package com.spring5.mypro00.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

public class MyAccessDeniedHandlerImpl extends AccessDeniedHandlerImpl {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
//		톰캣이 만든 request, response를 이요한 리다이렉트
//		response.sendRedirect("/mypro00/");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter myPw = response.getWriter();
		
		String sendHTML = "<script>"
						+ "alert('허용되지 않는 접근입니다.');"
						+ "location.href='/mypro00/';"
						+ "</script>";
		
		myPw.write(sendHTML);
		
		myPw.flush();
		myPw.close();
		
//		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//		redirectStrategy.sendRedirect(request, response, "/accessForbiddenError");
//		
//		super.handle(request, response, accessDeniedException);
	}
	
	
	
	
}
