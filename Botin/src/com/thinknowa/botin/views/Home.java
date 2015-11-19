package com.thinknowa.botin.views;

import com.thinknowa.botin.R;
import com.thinknowa.botin.components.slidemenu.PagerAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Home extends FragmentActivity {
	// Properties
	private ViewPager viewpager;
	private EditText searchEdt;

	// --------------------------------------------------------------------
	// CycleLife Activity
	// --------------------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.com_scm_main);
		lookupViewElements();
		setupListeners();
		setupInitialValues();
		
	}

	// --------------------------------------------------------------------
	// CycleLife View
	// --------------------------------------------------------------------
	/**
	 * Recupera los componentes de la Vista que serán utilizados en este
	 * Activity
	 * 
	 * @param paramView
	 */
	protected void lookupViewElements() {
		viewpager = (ViewPager) findViewById(R.id.com_scm_pager);
		searchEdt = ((EditText) findViewById(R.id.zmb_edt_search));
	}
	
	/**
	 * Establece los Listener que van a interacturar con el usuario.
	 */
	private void setupListeners() {

	}
	
	/**
	 * Establece los valores iniciales en la Vista
	 */
	private void setupInitialValues(){
		PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
	    viewpager.setAdapter(padapter);
	}
	
}
