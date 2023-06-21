package com.ekart.dao;
import com.ekart.connect.Dbcon;
import com.ekart.model.Order;
import com.ekart.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public OrderDao(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrder(Order model) {
		boolean result = false;
		try {
			query = "insert into ekartorder values(ido.nextval,?,?,?,?)";
			ps = this.con.prepareStatement(query);
			ps.setInt(1, model.getId());
			ps.setInt(2, model.getIdu());
			ps.setInt(3, model.getQuantity());
			ps.setString(4, model.getOdate());
			ps.executeUpdate();
			result = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Order> userOrders(int id){
		List<Order> list = new ArrayList<>();
		try {
			query = "select * from ekartorder where idu=? order by ido desc";
			ps = this.con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				ProductDao pdao = new ProductDao(this.con);
				int pId = rs.getInt("idp");
				System.out.println(pId);
				Product product = pdao.getSingleProduct(pId);
				order.setId(rs.getInt("ido"));
				order.setIdp(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice()*rs.getInt("quantity"));
				order.setQuantity(rs.getInt("quantity"));
				order.setOdate(rs.getString("odate"));
				list.add(order);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	 public void cancelOrder(int id) {
	        //boolean result = false;
	        try {
	            query = "delete from ekartorder where ido=?";
	            ps = this.con.prepareStatement(query);
	            ps.setInt(1, id);
	            ps.execute();
	            //result = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.print(e.getMessage());
	        }
	        //return result;
	    }
}
