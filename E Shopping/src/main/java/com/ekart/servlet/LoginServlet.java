package com.ekart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ekart.connect.Dbcon;
import com.ekart.dao.UserDao;
import com.ekart.model.User;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/userlogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
//			out.print("Login Sucess");
			String email = request.getParameter("Login-email");
			String password = request.getParameter("Login-password");
			
			try {
				UserDao dao = new UserDao(Dbcon.getConnection());
				User user = dao.userLogin(email, password);
				
				if(user!=null) {
					out.print("Login Success");
					request.getSession().setAttribute("auth",user);
					response.sendRedirect("index.jsp");
				}
				else {
					out.print("Invalid Credentials");
					response.sendRedirect("login.jsp");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(email+password);
		}
	}

}
