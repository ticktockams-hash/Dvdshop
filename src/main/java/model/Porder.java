package model;

import java.io.Serializable;
import java.util.Date;

public class Porder implements Serializable{
	
	private int id;
	private int memberid;
	private String username; 
	private String productname;
	private int quantity;
	private int amount;
	private Date porderdate;
	public Porder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Porder(int id, int memberid, String username, String productname, int quantity, int amount,
			Date porderdate) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.username = username;
		this.productname = productname;
		this.quantity = quantity;
		this.amount = amount;
		this.porderdate = porderdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getPorderdate() {
		return porderdate;
	}
	public void setPorderdate(Date porderdate) {
		this.porderdate = porderdate;
	}
	
	

}