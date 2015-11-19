package com.thinknowa.botin;

import android.app.Application;

public class Bottin extends Application{

	
	private static Bottin instance;

	
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


}



