<%@ page import="com.ekart.connect.Dbcon"%>
<%@ page import="com.ekart.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ekart.model.Cart"%>
<%@ page import="com.ekart.model.Product"%>
<%@ page import="com.ekart.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(Dbcon.getConnection());
List<Product> products = pd.getAllProducts();
String s = "Irfan";

ArrayList<Cart> cartlist = (ArrayList<Cart>) session.getAttribute("cart-list");
//out.print(cartlist);
List<Cart> cartProduct = null;
if (cartlist != null) {
	ProductDao pdao = new ProductDao(Dbcon.getConnection());
	cartProduct = pdao.getCartProducts(cartlist);
	request.setAttribute("cart-list", cartlist);
}
else if(cartlist==null){
	cartlist = new ArrayList<Cart>();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="includes/head.jsp"%>
</head>
<title>Home Page</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container my-2">
	<div class="card my-2">
	<div class="card-header">All Products</div>
	</div>
		<div class="row">
		<%
		if(!products.isEmpty()){
			for(Product p : products){%>
				<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img src="product-image/<%=p.getImage()%>" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price : Rs <%=p.getPrice() %></h6>
						<h6 class="category">Category : <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between"></div>
						<a href="addtocart?id=<%=p.getId() %>" class="btn btn-outline-dark">Add to cart</a>
						<a href="ordernow?quantity=1&id=<%=p.getId() %>" class="btn btn-outline-dark">Buy Now</a>
					</div>
				</div>
			</div>
			<%}
		}
		else{
			//out.print("No Product");
		}
		%>
		</div>
	</div>
	<%
	/* out.print(Dbcon.getConnection()); */
	%>
	<%@include file="includes/foot.jsp"%>
</body>
</html>