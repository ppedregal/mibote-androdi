package com.thinknowa.botin;

import com.thinknowa.botin.data.ItemManager;
import com.thinknowa.botin.data.MediaManager;

import android.app.Application;
import android.content.res.AssetManager;

public class Bottin extends Application{

	
	private static Bottin instance;
	
	private ItemManager itemMgr;
	private MediaManager mediaMgr;

	
	/**
	 * Constructor de la App
	 */
	public Bottin() {
		this.instance = this;
		this.mediaMgr = new MediaManager();
		this.itemMgr = new ItemManager();
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

	public MediaManager getMediaMgr() {
		return mediaMgr;
	}
	
	public AssetManager getAssets(){
		return getApplicationContext().getAssets();
	}
}



