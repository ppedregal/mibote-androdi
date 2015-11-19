package com.thinknowa.botin.components.slidemenu.fragments;


import com.thinknowa.botin.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
//import es.zalo.zombies.vendor.confirmdialog.Confirm;
//import es.zalo.zombies.vendor.confirmdialog.DialogEventListener;
//import es.zalo.zombies.views.Books;
// import es.zalo.zombies.views.Home;
//import es.zalo.zombies.views.Settings;

public class SlideOne extends Fragment implements View.OnClickListener {
	
	// Properties
	private View view;
	
	private ImageView mBook;
	private ImageView mMovie;
	private ImageView mVideogame;
	private ImageView mSerie;
	
	private Toast message;

	private static String TAG = SlideOne.class.getName();
	
	// --------------------------------------------------------------------
	// CycleLife Activity
	// --------------------------------------------------------------------
	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (this.view == null) {
			this.view = inflater.inflate(R.layout.com_scm_slide_one,
					container, false);

			lookupViewElements(this.view);
			setupObserverListeners();

		} else {
			ViewGroup parent = (ViewGroup) this.view.getParent();
			if (parent != null) {
				parent.removeView(this.view);
			}
			// ((ViewGroup) this.v.getParent()).removeView(this.v);
		}

		return this.view;	
//		return inflater.inflate(R.layout.com_scm_slide_one,container,false);
		
		
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
	protected void lookupViewElements(View view) {
		/*
			this.mBook = (ImageView)view.findViewById(R.id.scm_imv_book);
			this.mMovie = (ImageView)view.findViewById(R.id.scm_imv_movie);
			this.mSerie =  (ImageView)view.findViewById(R.id.scm_imv_serie);
			this.mVideogame = (ImageView)view.findViewById(R.id.scm_imv_video);
		*/
	}
	
	/**
	 * Establece los Listener que van a interacturar con el usuario.
	 */
	private void setupListeners() {
		
	}
	
	/**
	 * Establece los valores iniciales en la Vista
	 */
	private void setupInitialValues() {
		
	}
	
	/**
	 * Establece los Listener que van a interacturar con el usuario.
	 */
	private void setupObserverListeners() {
		/*
		this.mBook.setOnClickListener(this);
		this.mMovie.setOnClickListener(this);
		this.mSerie.setOnClickListener(this);
		this.mVideogame.setOnClickListener(this);
		 */
	}
	
	// --------------------------------------------------------------------
	// View.OnClickListener
	// --------------------------------------------------------------------
	@Override
	public void onClick(View element) {
		
		/*
		if (element.equals(mBook)) {
			this.message = Toast.makeText(getActivity(), "Click Book",	Toast.LENGTH_SHORT);
			startActivity(new Intent(getActivity() , Books.class));
			getActivity().finish();
		}else if(element.equals(mMovie)){
			this.message = Toast.makeText(getActivity(), "Click Movie",	Toast.LENGTH_SHORT);
		}else if(element.equals(mVideogame)){
			this.message = Toast.makeText(getActivity(), "Click Videogame",	Toast.LENGTH_SHORT);
			startActivity(new Intent(getActivity() , Settings.class));
			getActivity().finish();
		}else if(element.equals(mSerie)){
			 message = Toast.makeText(getActivity(), "Launch Dialog Confirm ",	Toast.LENGTH_LONG);
			 Confirm.using(getActivity()).ask("Fire missles?").onPositive("Yes", new DialogEventListener.OnClickListener() {
                 @Override public void onClick(DialogEventListener dialog, int which) {
//                   launchMissles();
                	 message = Toast.makeText(getActivity(), "Click Yes!! ",	Toast.LENGTH_LONG);
//                	 String response = SendNetworkUpdateAppRequest();
                	 message.show();
                 }}).onNegative("No",  new DialogEventListener.OnClickListener() {
                 @Override public void onClick(DialogEventListener dialog, int which) {
//                   sendFalseAlarm();
                	 message = Toast.makeText(getActivity(), "Click NO!! ",	Toast.LENGTH_LONG);
                	 message.show();
                 }}).build().show();
		}
		*/
		
		this.message.show();
	}

}
