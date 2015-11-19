package com.thinknowa.botin;

import com.thinknowa.botin.data.ItemManager;

import android.app.Application;

public class Bottin extends Application{

	
	private static Bottin instance;
	
	private ItemManager itemMgr;

	
	/**
	 * Constructor de la App
	 */
	public Bottin() {
		this.instance = this;
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	
	
	// -------------------------------------------------------------------------------------
	// Getter && Setter
	// -------------------------------------------------------------------------------------
	public static Bottin getInstance() {
		return instance;
	}


	public ItemManager getItemMgr() {
		return itemMgr;
	}
}



