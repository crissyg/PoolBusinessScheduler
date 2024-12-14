package com.example.PoolBusinessScheduler;

public class Customer {
	private String id, cust_name, cust_address, cust_city, cust_state, cust_zip, 
	cust_email, cust_phone, cust_date, cust_time, cust_price, cust_info;
	public Customer( ) {
		
	}
	public Customer( Customer cm) {
		this.id = cm.id;
		this.cust_name = cm.cust_name;
		this.cust_address = cm.cust_address;
		this.cust_city = cm.cust_city;
		this.cust_state = cm.cust_state;
		this.cust_zip = cm.cust_zip;
		this.cust_email = cm.cust_email;
		this.cust_phone = cm.cust_phone;
		this.cust_date = cm.cust_date;
		this.cust_time = cm.cust_time;
		this.cust_price = cm.cust_price;
		this.cust_info = cm.cust_info;
	}
	public String getId() {
		return id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_address() {
		return cust_address;
	}

	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}

	public String getCust_city() {
		return cust_city;
	}

	public void setCust_city(String cust_city) {
		this.cust_city = cust_city;
	}

	public String getCust_state() {
		return cust_state;
	}

	public void setCust_state(String cust_state) {
		this.cust_state = cust_state;
	}

	public String getCust_zip() {
		return cust_zip;
	}

	public void setCust_zip(String cust_zip) {
		this.cust_zip = cust_zip;
	}

	public String getCust_email() {
		return cust_email;
	}

	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_date() {
		return cust_date;
	}

	public void setCust_date(String cust_date) {
		this.cust_date = cust_date;
	}

	public String getCust_time() {
		return cust_time;
	}

	public void setCust_time(String cust_time) {
		this.cust_time = cust_time;
	}

	public String getCust_price() {
		return cust_price;
	}

	public void setCust_price(String cust_price) {
		this.cust_price = cust_price;
	}

	public String getCust_info() {
		return cust_info;
	}

	public void setCust_info(String cust_info) {
		this.cust_info = cust_info;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}