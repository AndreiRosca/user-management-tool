package com.endava.user.management.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.endava.user.management.repository.UserRepository;

@WebServlet(urlPatterns = { "/users/*" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String[] parts = pathInfo.split("/");
		long userId = Long.valueOf(parts[1]);
		UserRepository repository = (UserRepository) request.getAttribute("repository");
		repository.delete(userId);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		String[] parts = pathInfo.split("/");
		long userId = Long.valueOf(parts[1]);
		if (parts.length > 2 && "update".equalsIgnoreCase(parts[2])) {
			System.out.println("edit user" + userId);
		} else {
			System.out.println("query user" + userId);
		}
	}
}
