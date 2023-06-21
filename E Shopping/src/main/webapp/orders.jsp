<%@ page import="com.ekart.model.*"%>
<%@ page import="com.ekart.connect.Dbcon"%>
<%@ page import="com.ekart.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ekart.model.Cart"%>
<%@ page import="com.ekart.model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
User auth = (User)request.getSession().getAttribute("auth");
List<Order> orders = null;
if(auth!=null){
	request.setAttribute("auth",auth);
	orders = new OrderDao(Dbcon.getConnection()).userOrders(auth.getId());
}
else{
	response.sendRedirect("login.jsp");
}
ArrayList<Cart> cartlist = (ArrayList<Cart>) session.getAttribute("cart-list");
//out.print(cartlist);
List<Cart> cartProduct = null;
if (cartlist != null) {
	ProductDao pdao = new ProductDao(Dbcon.getConnection());
	cartProduct = pdao.getCartProducts(cartlist);
	request.setAttribute("cart-list", cartlist);
}
if(cartlist==null){
	cartlist = new ArrayList<Cart>();
}
if(cartProduct==null){
	cartProduct = new ArrayList<Cart>();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="includes/head.jsp"%>
</head>
<title>My Order</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container my-3">
		<%-- <div class="d-flex py-3">
			<h3>Total Price : Rs ${total==null?0:total}</h3>
			<a class="mx-3 btn btn-outline-dark" href="checkout">Check Out</a>
		</div> --%>
		<div class="card my-2">
		<div class="card-header">All Products</div></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Cateogry</th>
					<th scope="col">Price</th>
					<th scope="col">No. of Items</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody >
				<%
				if (orders != null) {
					for (Order c : orders) {
				%>
				<tr>
					<td><%=c.getOdate()%></td>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=c.getPrice()%></td>
					<td><%=c.getQuantity()%></td>
					<td><a class="btn btn-sm btn-outline-dark" href="cancelorder?id=<%=c.getId()%>">Cancel</a></td>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
		
	</div>

	<%@include file="includes/foot.jsp"%>
</body>
</html>