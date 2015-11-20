package com.thinknowa.botin.sdk.model;

public class Movement {
	
	private Amount amount;
	private String description;
	private String id;
	private String tinId;
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTinId() {
		return tinId;
	}
	public void setTinId(String tinId) {
		this.tinId = tinId;
	}
	public String toString() {
		return "Movement [amount=" + amount + ", description=" + description + ", id=" + id + ", tinId=" + tinId + "]";
	}

}
