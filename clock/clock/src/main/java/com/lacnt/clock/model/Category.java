package com.lacnt.clock.model;


public class Category  {
	
	private int id_category;
	private String name;
	private int isdelete;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(int id_category, String name, int isdelete) {
		super();
		this.id_category = id_category;
		this.name = name;
		this.isdelete=isdelete;
	}

	@Override
	public String toString() {
		return "Category [id_category=" + id_category + ", name=" + name + ", isdelete=" + isdelete + "]";
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

}
