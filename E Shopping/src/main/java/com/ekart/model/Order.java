package com.ekart.model;

public class Order extends Product{
	private int Idp;
	private int Idu;
	private int Quantity;
	private String Odate;
	public Order() {
		super();
	}
	public Order( int idp, int idu, int quantity, String odate) {
		super();
		Idp = idp;
		Idu = idu;
		Quantity = quantity;
		Odate = odate;
	}
	public int getIdp() {
		return Idp;
	}
	public void setIdp(int idp) {
		Idp = idp;
	}
	public int getIdu() {
		return Idu;
	}
	public void setIdu(int idu) {
		Idu = idu;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getOdate() {
		return Odate;
	}
	public void setOdate(String odate) {
		Odate = odate;
	}
	@Override
	public String toString() {
		return "Order [Idp=" + Idp + ", Idu=" + Idu + ", Quantity=" + Quantity + ", Odate=" + Odate
				+ "]";
	}
}
