package com.ekart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ekart.connect.Dbcon;
import com.ekart.dao.OrderDao;
import com.ekart.model.Cart;
import com.ekart.model.Order;
import com.ekart.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			ArrayList<Cart> cartlist = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			User user = (User)request.getSession().getAttribute("auth");
			
			if(cartlist!=null&&user!=null) {
				for(Cart c : cartlist) {
					Order order = new Order();
					order.setId(c.getId());
					order.setIdu(user.getId());
					order.setQuantity(c.getQuantity());
					order.setOdate(formatter.format(date)); 
					
					OrderDao orderDao = new OrderDao(Dbcon.getConnection());
					boolean result = orderDao.insertOrder(order);
					
					if(!result) break;
					
				}
				
				cartlist.clear();
				response.sendRedirect("orders.jsp");
			}
			else if(user==null) {
				response.sendRedirect("login.jsp");
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
