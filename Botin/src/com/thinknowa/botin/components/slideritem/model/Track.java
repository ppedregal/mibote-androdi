package com.thinknowa.botin.components.slideritem.model;

import java.io.File;

import com.thinknowa.botin.sdk.model.Amount;

public class Track  { //extends FSobject

	private static final long serialVersionUID = -4679039819764441537L;
	private File cover;

	private String refItem;
	private String promo;
	private String name;
	private Amount amount;

	public Track(String name, String path, File cover) {
		//super(path, name);
		this.cover = cover;
	}

	public Track(String refItem, String name, Amount amount, File cover) {
		//super(path, name);
		this.refItem = refItem;
		this.name = name;
		this.amount = amount;
		this.cover = cover;
	}

	/*
	public <R> R accept(InstanceVisitor<R> visitor) {
		return visitor.visit(this);
	}
	*/

	public File getCover() {
		return cover;
	}

	public String getRefItem() {
		return refItem;
	}

	public String getPromo() {
		return promo;
	}

	public String getName() {
		return name;
	}

	public Amount getAmount() {
		return amount;
	}

}