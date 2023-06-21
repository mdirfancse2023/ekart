package com.ekart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ekart.model.Cart;

/**
 * Servlet implementation class removeServlet
 */
@WebServlet("/removeproduct")
public class removeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			int id = Integer.parseInt(request.getParameter("id"));
			if(Integer.toString(id)!=null) {
				ArrayList<Cart> cartlist = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
				if(cartlist.size()>0) {
					for(Cart c : cartlist) {
						if(c.getId()==id) {
							cartlist.remove(cartlist.indexOf(c));
							break;
						}
					}
					response.sendRedirect("cart.jsp");
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
