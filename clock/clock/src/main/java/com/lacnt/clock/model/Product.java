package com.lacnt.clock.model;

public class Product {
	private int id_product;
	private String name;
	private String name_category;
	public String getName_category() {
		return name_category;
	}
	public void setName_category(String name_category) {
		this.name_category = name_category;
	}
	private int price;
	private int quantity;
	private int isdelete;
	private int id_category;
	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
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
		this.price =price ;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	public int getId_category() {
		return id_category;
	}
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}
	public Product(int id_product, String name, String name_category, int price, int quantity, int isdelete, int id_category) {
		super();
		this.id_product = id_product;
		this.name = name;
		this.name_category=name_category;
		this.price = price;
		this.quantity = quantity;
		this.isdelete = isdelete;
		this.id_category = id_category;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Product [id_product=" + id_product + ", name=" + name + ",name_category="+name_category+", price=" + price + ", quantity=" + quantity
				+ ", isdelete=" + isdelete + ", id_category=" + id_category + "]";
	}
	
}

