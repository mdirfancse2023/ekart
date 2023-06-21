package com.ekart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.ekart.model.Cart;
import com.ekart.model.Product;

public class ProductDao {
	private Connection con;
	private String query;
	private Statement ps;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) {
		super();
		this.con = con;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			query = "select * from ekartproducts";
			ps = this.con.createStatement();
			rs = ps.executeQuery(query);
			while(rs.next()) {
				//System.out.println(1);
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getInt("price"));
				row.setImage(rs.getString("image"));
				products.add(row);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.print(products);
		return products;
		
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<>();
		try {
			if(cartList.size()>0) {
				for(Cart item : cartList) {
					query = "select * from ekartproducts where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					
					rs = pst.executeQuery();
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getInt("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	public int getTotalCartPrice(ArrayList<Cart> cartlist) {
		int sum = 0;
		try {
			if(cartlist.size()>0) {
				for(Cart c : cartlist) {
					query = "select * from ekartproducts where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, c.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						sum+=rs.getInt("price")*c.getQuantity();
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public Product getSingleProduct(int id) {
		Product row = null;
		try {
			query = "select * from ekartproducts where id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getInt("price"));
				row.setImage(rs.getString("image"));
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.print(id);
		System.out.print(row);
		return row;
	}
}
