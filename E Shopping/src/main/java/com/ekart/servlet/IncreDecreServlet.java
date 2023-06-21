package com.ekart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.ekart.model.Cart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IncreDecreServlet
 */
@WebServlet("/incdec")
public class IncreDecreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncreDecreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter();){
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Cart> cartlist = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			if(action!=null&&id>=1) {
				if(action.equals("inc")) {
					for(Cart c : cartlist) {
						if(c.getId()==id) {
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
				else if(action.equals("dec")) {
					for(Cart c : cartlist) {
						if(c.getId()==id) {
							int quantity = c.getQuantity();
							if(quantity>1)
								quantity--;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
			}
			else {
				response.sendRedirect("cart.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
