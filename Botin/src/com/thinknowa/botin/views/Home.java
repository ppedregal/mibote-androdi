package com.thinknowa.botin.views;

import java.util.ArrayList;
import java.util.List;

import com.thinknowa.botin.Bottin;
import com.thinknowa.botin.R;
import com.thinknowa.botin.components.slidemenu.PagerAdapter;
import com.thinknowa.botin.components.slideritem.model.Track;
import com.thinknowa.botin.components.slideritem.model.TrackBundle;
import com.thinknowa.botin.components.slideritem.view.AlbumArtView;
import com.thinknowa.botin.components.slideritem.view.FileChooser;
import com.thinknowa.botin.data.ItemManager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;


public class Home extends Activity { // ListActivity
	// Properties
	private ActionBar ab;
//	private ViewPager viewpager;
//	private EditText searchEdt;
	private ViewFlipper vf;
	
	// Slides
	private RelativeLayout nowPlayingSlide;


	private Bottin app;
	private FileChooser fileChooser;

	// --------------------------------------------------------------------
	// CycleLife Activity
	// --------------------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.com_mp_main);			// R.layout.com_scm_main
		setupApplication();
		lookupViewElements();
		setupListeners();		
		setupInitialValues();
		setupSlides();
		SetupInitialView();
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	private void setupApplication() {
		ab = getActionBar();
		app = (Bottin) getApplication();
		
		ArrayList<Track> list =  app.getItemMgr().getTracks();
		
		fileChooser = new FileChooser(this, list) {
	};

		this.ab.hide();
		// this.ab.setTitle(getString(R.string.com_mp_txv_title));
		// this.ab.setDisplayOptions(14);
	}
	
	// ========================================= //
	// Setup Slides - Part of Init()
	// ========================================= //

	private void setupSlides() {
		nowPlayingSlide = (RelativeLayout) findViewById(R.id.now_playing_slide);
//		SwitchToNowPlayingSlide();
	}
	
	private void SetupInitialView() {		
		setupCurrentTrack(fileChooser.getCurrentTrack(), 0);
	}
	
	
	private void setupCurrentTrack(Track track, int mills) {
		TrackBundle bundle = fileChooser.enrich(track);
		new AlbumArtView(this, app, fileChooser, bundle);

		if (track != null) {
			fileChooser.setCurrentTrack(track);
//			progressBar.setMax(mills);
//			duration.setText(ConvertToMinutes(mills));
//			playButton.setImageDrawable(getResources().getDrawable(
//					R.drawable.com_mp_icon_player_pause_64));
		}
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
		this.vf = ((ViewFlipper) findViewById(R.id.home_mainview));
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
		ItemManager itemMgr = ((Bottin) getApplication()).getItemMgr();
		if(itemMgr != null && itemMgr.getTracks().size() > 0 ){
			this.vf.setDisplayedChild(1);
		}else{
			this.vf.setDisplayedChild(0);
		}
	}
	
	
	/*
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		final Track trackSelected = fileChooser.choose((Track) l
				.getItemAtPosition(position));
		if (trackSelected != null) {

			
			app.player.connectPlayer(new OutputCommand() {
				public void connected(IPlayer output) {
					output.play(trackSelected);
				}
			});
			SwitchToNowPlayingSlide();
			
		}
	}
	*/
	
}
