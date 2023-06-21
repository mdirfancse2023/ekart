package com.ekart.servlet;
import com.ekart.model.User;
import com.ekart.connect.Dbcon;
import com.ekart.model.Cart;
import com.ekart.model.Order;
import com.ekart.dao.OrderDao;
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

/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/ordernow")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			User user = (User)request.getSession().getAttribute("auth");
			if(user!=null) {
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity<=0) {
					productQuantity = 1;
				}
				Order order = new Order();
				order.setId(Integer.parseInt(productId));
				order.setIdu(user.getId());
				order.setQuantity(productQuantity);
				order.setOdate(formatter.format(date)); 
				
				OrderDao orderDao = new OrderDao(Dbcon.getConnection());
				boolean result = orderDao.insertOrder(order);
				if(result) {
					ArrayList<Cart> cartlist = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
					if(cartlist.size()>0) {
						for(Cart c : cartlist) {
							if(c.getId()==Integer.parseInt(productId)) {
								cartlist.remove(cartlist.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				}
				else {
					//response.sendRedirect("login.jsp");
				}
			}
			else {
				response.sendRedirect("login.jsp");
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
