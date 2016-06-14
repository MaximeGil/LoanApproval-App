package com.google.loanapproval.webapp.servlet;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LA_Khettab_GilServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
