package com.product;

public class Product {
	
	private int cid;
	private int pid;
	private String pname;
	private int quantity;
	private int price;
	
	public Product(int pid, int cid, String name, int quantity, int price) {
		this.cid = cid;
		this.pid = pid;
		this.pname = name;
		this.quantity = quantity;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
