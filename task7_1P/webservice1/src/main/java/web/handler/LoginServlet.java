package web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.LoginService;

/**
 * HTTP end-point to handle login service.
 */

public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("[LoginServlet] GET");
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws IOException, ServletException {
	    System.out.println("[LoginServlet] POST");
	    String username = req.getParameter("username");
	    String password = req.getParameter("passwd");
	    String dob      = req.getParameter("dob");
	    System.out.println("username=" + username + ", password=" + password + ", dob=" + dob);
	    String loginStatus = LoginService.login(username, password, dob) ? "success" : "fail";
	    String statusMessage = loginStatus.equals("success")
	            ? "Welcome, " + username + "! You are now logged in."
	            : "Login failed. Please check your credentials.";
	    resp.setContentType("text/html");  
	    resp.setCharacterEncoding("UTF-8");
	    PrintWriter writer = resp.getWriter();
	    writer.println("<!DOCTYPE html>");
	    writer.println("<html>");
	    writer.println("<head>");
	    writer.println("  <meta charset=\"UTF-8\">");
	    writer.println("  <title>" + loginStatus + "</title>");  
	    writer.println("</head>");
	    writer.println("<body>");
	    writer.println("  <h2>Login status: " + loginStatus + "</h2>");
	    writer.println("  <span id=\"login-status\">"   + loginStatus    + "</span>");
	    writer.println("  <p    id=\"status-message\">" + statusMessage  + "</p>");
	    writer.println("  <a    id=\"back-link\" href=\"login.html\">Back to login</a>");
	    writer.println("</body>");
	    writer.println("</html>");
	    writer.flush(); 
	}

}
