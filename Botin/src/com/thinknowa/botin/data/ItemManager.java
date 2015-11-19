package com.thinknowa.botin.data;

import java.util.ArrayList;
import java.util.List;

import com.thinknowa.botin.components.slideritem.model.Track;


public class ItemManager {
	
	private List<Track> tracks = new ArrayList<Track>();

	/**
	 * Constructor
	 */
	public ItemManager() {
		
	}
	
	private void initManager(){
		tracks.add(new Track("Bote 1", "Path 1", null));
		tracks.add(new Track("Bote 2", "Path 2", null));
		tracks.add(new Track("Bote 1", "Path 3", null));
	}
	
	// -------------------------------------------------------------------------------------
	// Getter && Setter
	// -------------------------------------------------------------------------------------
	public List<Track> getTracks() {
		return tracks;
	}

}
