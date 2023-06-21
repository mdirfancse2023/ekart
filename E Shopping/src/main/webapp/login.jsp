<%@ page import="com.ekart.model.User" %>
<%@ page import="com.ekart.model.User"%>
<%@ page import="com.ekart.connect.Dbcon"%>
<%@ page import="com.ekart.dao.ProductDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ekart.model.Cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
User auth = (User)request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth",auth);
	response.sendRedirect("index.jsp");
}
ArrayList<Cart> cartlist = (ArrayList<Cart>) session.getAttribute("cart-list");
//out.print(cartlist);
List<Cart> cartProduct = null;
if (cartlist != null) {
	ProductDao pdao = new ProductDao(Dbcon.getConnection());
	cartProduct = pdao.getCartProducts(cartlist);
	request.setAttribute("cart-list", cartlist);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="includes/head.jsp"%>
</head>
<title>Login Here</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="userlogin" method="post">
	
					<div class="form-group mt-2">

						<label class="form-label">Email Address</label> <input
							type="email" class="form-control" name="Login-email"
							placeholder="Enter your email" required>
					</div>

					<div class="form-group mt-2">

						<label class="form-label">Password</label> <input type="password"
							class="form-control" name="Login-password" placeholder="*****"
							required>
					</div>



					<div class="text-center mt-4">
						<button type="submit" class="btn btn-outline-dark">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="includes/foot.jsp"%>
</body>
</html>