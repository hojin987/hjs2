package com.spring5.mypro00.common.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyErrMsgController {
	
	@GetMapping (value = "/accesssForbiddenError")
	public String sendAccessForbiddenPage(Authentication authentication, HttpServletRequest request) {
		
		System.out.println("myName: " + request.getAttribute("myName"));
		
		return "common/err_msg/myAccessForbiddenMsg";
	}
	
}
