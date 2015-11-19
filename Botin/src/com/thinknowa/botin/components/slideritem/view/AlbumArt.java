package com.thinknowa.botin.components.slideritem.view;

import java.io.File;

import com.thinknowa.botin.R;
import com.thinknowa.botin.components.slideritem.model.Track;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumArt {
	public enum Type {
		RIGHT(-1, R.id.albumArtRight), // FIXME: should be 1, if your know why
										// its inverted, change it
		LEFT(1, R.id.albumArtLeft), // FIXME: should be -1, your know why its
									// inverted, change it
		CENTER(0, R.id.albumArt);

		private final double horizontalShift;
		private final int rId;

		Type(double horizontalShift, int rId) {
			this.horizontalShift = horizontalShift;
			this.rId = rId;
		}

		public double getHorizontalShift() {
			return horizontalShift;
		}

		public int getRId() {
			return rId;
		}
	}

	private final View albumArtView;

	private final Type type;

	private final ImageView albumArt;
	private final TextView title;
	private final TextView artist;

	private AsyncTask<Track, Void, Bitmap> actualAsyncTask = null;

	public AlbumArt(View albumArtViewGroup, Type type) {

		albumArtView = albumArtViewGroup.findViewById(type.getRId());
		this.type = type;

		albumArt = (ImageView) albumArtView.findViewById(R.id.picture);
		title = (TextView) albumArtView.findViewById(R.id.trackTitle);
		artist = (TextView) albumArtView.findViewById(R.id.trackArtist);

		albumArtView.getViewTreeObserver().addOnPreDrawListener(
				new ViewTreeObserver.OnPreDrawListener() {
					public boolean onPreDraw() {
						setInitialPositions();
						albumArtView.getViewTreeObserver()
								.removeOnPreDrawListener(this);
						return true;
					}
				});

	}

	public View getAlbumArtView() {
		return albumArtView;
	}

	public void setTrack(final Track track) {
		setTrackDigest(track);

		/*
		if (track != null) {
			actualAsyncTask = new AlbumArtResolver() {

				@Override
				protected Bitmap doInBackground(Track... params) {
					if (actualAsyncTask == this) {
						return super.doInBackground(params);
					}
					return null;
				}

				@Override
				protected void onPostExecute(Bitmap bitmap) {
					if (actualAsyncTask == this && bitmap != null) {
						albumArt.setImageBitmap(bitmap);
						Log.v("AlbumArt", "albumart for " + track.getFullPath()
								+ " resolved");
					}
				}
			}.execute(track);
		}
		*/
	}

	/**
	 * update View synchronous with attributes easy to resolve
	 * 
	 * @param track
	 */
	public void setTrackDigest(final Track track) {
		if (track != null) {
			//title.setText(track.accept(InstanceFormatter.SHORT_WITH_NUMBER));
			artist.setText(track.getArtist());
			// artist.setText(track.GetArtist().accept(InstanceFormatter.SHORT));
		} else {
			title.setText("");
			artist.setText("");
		}

		File file = (track != null) ? track.getCover() : null;
		Drawable drawable = null;

		if (file != null) {
			Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			drawable = new BitmapDrawable(myBitmap);
		}

		if (drawable != null) {
			albumArt.setImageDrawable(drawable);
		} else {
			albumArt.setImageDrawable(albumArtView.getResources().getDrawable(
					R.drawable.item_cover));
		}
		albumArtView.invalidate();
	}

	public void setInitialPositions() {
		albumArtView.setVisibility(View.VISIBLE);
		albumArtView.scrollTo(
				(int) (type.getHorizontalShift() * albumArtView.getWidth()), 0);
	}
}
