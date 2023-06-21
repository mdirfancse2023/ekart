package com.ekart.model;

public class Product {
	private int id;
	private String name;
	private int price;
	private String category;
	private String image;
	public Product() {
		super();
	}
	public Product(int id, String name, int price, String category, String image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", image="
				+ image + "]";
	}
	
	
}
