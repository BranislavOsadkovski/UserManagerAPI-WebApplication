package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * SERVLET implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			ServletContext servletContext = request.getServletContext();
			Cookie [] cookies = request.getCookies();
			Cookie JSESSIONID=null;
			Cookie userCookie =null;
			if(cookies!=null) {
				for(Cookie c : cookies) {
					//find remaining cookies in browser
					if(c.getName().equals("JSESSIONID"))JSESSIONID = c;
					if(c.getName().equals("user"))userCookie=c;
				}
			}
//			//Invalidate session if exists
			HttpSession session = request.getSession(false);
			System.out.println("User = " + session.getAttribute("user"));
			if(session!=null) {
				session.invalidate();
			}
			//Remove Attribute from ServletContext
			servletContext.removeAttribute("User");
			//Delete Cookies from browser by setting expiration time to zero
			JSESSIONID.setMaxAge(0);
			userCookie.setMaxAge(0);
			
			response.addCookie(JSESSIONID);
			response.addCookie(userCookie);
			response.sendRedirect("login.html");
	}

}