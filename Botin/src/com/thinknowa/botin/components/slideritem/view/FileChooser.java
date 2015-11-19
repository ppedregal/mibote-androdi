package com.thinknowa.botin.components.slideritem.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thinknowa.botin.components.slideritem.model.InstanceVisitor;
import com.thinknowa.botin.components.slideritem.model.Track;
import com.thinknowa.botin.components.slideritem.model.TrackBundle;
import com.thinknowa.botin.components.slideritem.util.DefaultAdapter;
import com.thinknowa.botin.views.Home;

import android.util.Log;
import android.widget.ListView;

public abstract class FileChooser {

	private final Home listActivity;
//	final DefaultAdapter<Instance> listAdapter;
//	private List<Instance> tracks;
//	private Instance currentTrack;
//	final DefaultAdapter<Track> listAdapter;
	private List<Track> tracks;
	private Track currentTrack;

	ListView filterList = null;

	public FileChooser(Home listActivity, ArrayList<Track> list) {

		this.listActivity = listActivity;

		tracks = list;

		if (tracks.size() > 0) {
			currentTrack = tracks.get(0);
		}

		/*
		listAdapter = new DefaultAdapter<Track>(
				listActivity.getApplicationContext(), tracks, listActivity,
				false); // InstanceFormatter.SHORT
		*/
		/*
		listAdapter = new DefaultAdapter<Instance>(
				listActivity.getApplicationContext(), tracks, listActivity,
				false, InstanceFormatter.SHORT);
		 */
		// listActivity.setListAdapter(listAdapter);

		init();
	}

	private void init() {

	}

	/**
	 * @param selection
	 * @return null if no track was selected, track if trak was selected
	 */
	public Track choose(Track selection) { 	// Instance selection
		/*
		return selection.accept(new InstanceVisitor<Track>() {
			public Track visit(Track track) {
				return track;
			}

			
			public Track visit(FSobject FSobject) {
				return null;
			}
			
		});
		*/
		return selection;
	}

	public void update() {

	}

	public void updated(final Track instance) { // Instance instance
		/*
		if (!RumbappPlayer.Slides.PLAYLIST.equals(listActivity.getCurrSlide())) {
			return;
		}
		*/
//		listActivity.getCurrSlide();

	}

	public boolean back() {
		if (tracks != null && tracks.size() > 0 && currentTrack != null) {
			int position = tracks.indexOf(currentTrack);
			position--;
			if (position < 0) {
				position = tracks.size() - 1;
			}
			Log.d("FileChooser", currentTrack.toString());
			currentTrack = tracks.get(position);
			return true;
		}
		return false;
	}

	public boolean next() {
		if (tracks != null && tracks.size() > 0 && currentTrack != null) {
			int position = tracks.indexOf(currentTrack);
			position++;
			if (position >= tracks.size()) {
				position = 0;
			}
			currentTrack = tracks.get(position);
			Log.d("FileChooser", currentTrack.toString());
			return true;
		}
		return false;
	}

	public Track getNextTrack() {
		if (tracks != null && tracks.size() > 0 && currentTrack != null) {
			int position = tracks.indexOf(currentTrack);
			position++;
			if (position >= tracks.size()) {
				position = 0;
			}
			Log.d("FileChooser", currentTrack.toString());
			return (Track) tracks.get(position);
		}
		return (Track) currentTrack;
	}

	public Track getBackTrack() {
		if (tracks != null && tracks.size() > 0 && currentTrack != null) {
			int position = tracks.indexOf(currentTrack);
			position--;
			if (position < 0) {
				position = tracks.size() - 1;
			}
			return (Track) tracks.get(position);
		}
		return (Track) currentTrack;
	}

	public Track getNextTrack(Track track) {
		if (tracks != null && tracks.size() > 0 && track != null) {
			int position = tracks.indexOf(track);
			position++;
			if (position >= tracks.size()) {
				position = 0;
			}
			return (Track) tracks.get(position);
		}
		return (Track) track;
	}

	public Track getBackTrack(Track track) {
		if (tracks != null && tracks.size() > 0 && track != null) {
			int position = tracks.indexOf(track);
			position--;
			if (position < 0) {
				position = tracks.size() - 1;
			}
			return (Track) tracks.get(position);
		}
		return (Track) track;
	}

	public Track getTrackById(String itemRef, String promo) {
		boolean found = false;
		Track track = null;
//		Iterator<Instance> it = tracks.iterator();  
		Iterator<Track> it = tracks.iterator();
		while (!found && it.hasNext()) {
			Track t = (Track) it.next();
			if (itemRef.equals(t.getRefItem()) && promo.equals(t.getPromo())) {
				found = true;
				track = t;
			}
		}

		return track;
	}

	/**
	 * @ * param track
	 * 
	 * @return adds additional information to track
	 */
	public TrackBundle enrich(Track track) {
		return new TrackBundle(track, getNextTrack(track), getBackTrack(track));
	}

	public boolean isEmpty() {
		if (tracks == null || tracks.isEmpty()) {
			return true;
		}

		return false;
	}

	public void cleared() {
//		listAdapter.clear();
	}

	public String getId() {
		return "FileChooserUpdater";
	}

	public Track getCurrentTrack() {
		return (Track) currentTrack;
	}

	public void setCurrentTrack(Track currentTrack) {	// Instance currentTrack
		this.currentTrack = currentTrack;
	}
}
