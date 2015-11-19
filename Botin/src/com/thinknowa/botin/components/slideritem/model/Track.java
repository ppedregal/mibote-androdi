package com.thinknowa.botin.components.slideritem.model;

import java.io.File;

public class Track  { //extends FSobject

	private static final long serialVersionUID = -4679039819764441537L;
	private File cover;

	private String promo;
	private String artist;
	private String refItem;

	public Track(String name, String path, File cover) {
		//super(path, name);
		this.cover = cover;
	}

	public Track(String refItem, String promo, String name, String path,
			String artist, File cover) {
		//super(path, name);
		this.cover = cover;
		this.promo = promo;
		this.artist = artist;
		this.refItem = refItem;
	}

	/*
	public <R> R accept(InstanceVisitor<R> visitor) {
		return visitor.visit(this);
	}
	*/

	public File getCover() {
		return cover;
	}

	public String getArtist() {
		return artist;
	}

	public String getRefItem() {
		return refItem;
	}

	public String getPromo() {
		return promo;
	}

}