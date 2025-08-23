package model;

import java.io.Serializable;

public class Product implements Serializable{
	private int id;
	private String productname;
	private int productprice;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Product(int id, String productname, int productprice) {
		super();
		this.id = id;
		this.productname = productname;
		this.productprice = productprice;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getProductprice() {
		return productprice;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}
	
	
}
