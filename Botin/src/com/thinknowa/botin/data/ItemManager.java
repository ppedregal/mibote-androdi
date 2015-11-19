package com.thinknowa.botin.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.thinknowa.botin.Bottin;
import com.thinknowa.botin.components.slideritem.model.Track;


public class ItemManager {
	
	private ArrayList<Track> tracks = new ArrayList<Track>();

	/**
	 * Constructor
	 */
	public ItemManager() {
		initManager();
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

}
