package com.review;

public class OrderModel {
	
	int oid;
	int pid;
	int uid;
	String pname;
	int quantity;
	String status;
	String name;
	int price;
	
	public OrderModel(int oid, int pid, int uid, String pname, int quantity, String status, String name, int price) {
		super();
		this.oid = oid;
		this.pid = pid;
		this.uid = uid;
		this.pname = pname;
		this.quantity = quantity;
		this.status = status;
		this.name = name;
		this.price = price;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	

}
