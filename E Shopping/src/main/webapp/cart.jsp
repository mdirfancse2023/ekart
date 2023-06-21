
<%@ page import="com.ekart.model.User"%>
<%@ page import="com.ekart.connect.Dbcon"%>
<%@ page import="com.ekart.dao.ProductDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ekart.model.Cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ArrayList<Cart> cartlist = (ArrayList<Cart>) session.getAttribute("cart-list");
//out.print(cartlist);
List<Cart> cartProduct = null;
if (cartlist != null) {
	ProductDao pdao = new ProductDao(Dbcon.getConnection());
	cartProduct = pdao.getCartProducts(cartlist);
	int sum = pdao.getTotalCartPrice(cartlist);
	request.setAttribute("cart-list", cartlist);
	request.setAttribute("total", sum);
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
<style type="text/css">
.table tbody td {vertical align :middle;
	
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
th, td {
    text-align: center;
    vertical-align: middle;
}
</style>
</head>
<title>My Cart</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container my-3">
		<div class="d-flex py-3">
			<h3>Total Price : Rs ${total==null?0:total}</h3>
			<a class="mx-3 btn btn-outline-dark" href="checkout">Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Cateogry</th>
					<th scope="col">Price</th>
					<th scope="col">No. of Items</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody >
				<%
				if (cartlist != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td>Rs <%=c.getPrice()%></td>
					<td>
						<form action="ordernow" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre" href="incdec?action=dec&id=<%=c.getId()%>"><i
									class="fa-solid fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control" value="<%=c.getQuantity() %>" readonly>
								<a class="btn btn-sm btn-incre" href="incdec?action=inc&id=<%=c.getId()%>"><i
									class="fa-solid fa-plus-square"></i></a>
								<button type="submit" class="btn btn-outline-dark">Buy</button>	
							</div>
						</form>
					</td>
					<td><a class="btn btn-outline-dark" href="removeproduct?id=<%=c.getId()%>">Remove</a></td>
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