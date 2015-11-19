package com.thinknowa.botin.views;

import com.thinknowa.botin.Bottin;
import com.thinknowa.botin.R;
import com.thinknowa.botin.components.slidemenu.PagerAdapter;
import com.thinknowa.botin.components.slideritem.model.Track;
import com.thinknowa.botin.components.slideritem.model.TrackBundle;
import com.thinknowa.botin.components.slideritem.view.AlbumArtView;
import com.thinknowa.botin.components.slideritem.view.FileChooser;

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

/* 
 
import es.zalo.rumbapp.component.mediaplayer.model.Instance;
import es.zalo.rumbapp.component.mediaplayer.model.Track;
import es.zalo.rumbapp.component.mediaplayer.player.IPlayer;
import es.zalo.rumbapp.component.mediaplayer.player.OutputCommand;
 */

public class Home extends Activity { // ListActivity
	// Properties
	private ActionBar ab;
//	private ViewPager viewpager;
//	private EditText searchEdt;
//	private ViewFlipper vf;
	
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
	
	private void setupApplication() {
		ab = getActionBar();
		app = (Bottin) getApplication();
		fileChooser = new FileChooser(this) {
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
		setupCurrentTrack(null, 0);
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
		/*
		viewpager = (ViewPager) findViewById(R.id.com_scm_pager);
		searchEdt = ((EditText) findViewById(R.id.zmb_edt_search));
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
	private void setupInitialValues(){
		/*
		PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
	    viewpager.setAdapter(padapter);
	    */
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
