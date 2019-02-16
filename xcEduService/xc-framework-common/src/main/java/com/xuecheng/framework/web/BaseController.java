package com.xuecheng.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by mrt on 2018/5/22.
 */
public class BaseController {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {

		this.request = request;

		this.response = response;

		this.session = request.getSession();

	}
}
