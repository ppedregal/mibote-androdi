package com.thinknowa.botin.data;

import java.io.File;
import java.util.ArrayList;

import com.thinknowa.botin.Bottin;
import com.thinknowa.botin.components.slideritem.model.Track;
import com.thinknowa.botin.delegate.ILoaderItems;
import com.thinknowa.botin.delegate.LoaderItems;
import com.thinknowa.botin.sdk.Sdk;

import android.util.Log;



public class ItemManager implements ILoaderItems{
	
	private ArrayList<Track> tracks = new ArrayList<Track>();

	private Sdk sdk;
	
	/**
	 * Constructor
	 */
	public ItemManager() {
//		initManager();
//		initSDK();
		
	}
	
	private void initSDK(){
		/*
		sdk = new Sdk("http://172.26.0.222:3000/api/");
        sdk.logLevel(LoggingInterceptor.Level.BODY);
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sdk.login("email1@example.com", "test");
				List<Tin> account1BotesAfter = sdk.tins();
				
			}
		}).start();
		*/
//		new LoaderItems(this).execute();
	}
	
	private void initManager(){
		
		String pathRoot = Bottin.getInstance().getMediaMgr().getStorageRoot().getAbsolutePath();
		String pathCover1 = pathRoot + "/cover_1";
		String pathCover2 = pathRoot + "/cover_2";
		String pathCover3 = pathRoot + "/cover_1";
		File fCover1 = getImageFile(pathCover1);
		File fCover2 = getImageFile(pathCover2);
		File fCover3 = getImageFile(pathCover3);
		
		Log.d("ItemManager", " cover : "+pathCover1 );
		Log.d("ItemManager", " cover : "+pathCover2 );
		Log.d("ItemManager", " cover : "+pathCover3 );
		
		tracks.add(new Track("Bote 1", "Path 1", fCover1));
		tracks.add(new Track("Bote 2", "Path 2", fCover2));
		tracks.add(new Track("Bote 1", "Path 3", fCover3));
	}
	
	public static File getImageFile(String pathImg) {
		File file = new File(pathImg + ".jpg");
		if (file.exists()) {
			return file;
		}

		file = new File(pathImg + ".png");
		if (file.exists()) {
			return file;
		}

		return null;
	}
	
	// -------------------------------------------------------------------------------------
	// Getter && Setter
	// -------------------------------------------------------------------------------------
	public ArrayList<Track> getTracks() {
		return tracks;
	}
	
	public void setTracks(ArrayList<Track> tracks) {
		this.tracks = tracks;
	}

	// -------------------------------------------------------------------------------------
	// ILoaderItems
	// -------------------------------------------------------------------------------------
		
	@Override
	public void loaderItemsPreExecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loaderItemsFinished(ArrayList<Track> items) {
		this.tracks =items;	
	}

}
