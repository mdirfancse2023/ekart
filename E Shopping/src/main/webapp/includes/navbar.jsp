
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">E Shopping</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="index.jsp">Home</a>
        </li>
        <%
        if(auth!=null){%>
          <li class="nav-item">
            <a class="nav-link" href="cart.jsp">Cart<span class="position-absolute translate-middle badge rounded-pill bg-danger"><%= cartlist.size() %></span></a>
          </li>
        	<li class="nav-item">
            <a class="nav-link" href="orders.jsp">Orders</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="userlogout">Logout</a>
          </li>
        <%}
        else{%>
        	<li class="nav-item">
            <a class="nav-link" href="userlogin">Login</a>
          </li>
        <%}
        %>
        
      </ul>
    </div>
  </div>
</nav>