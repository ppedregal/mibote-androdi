package com.thinknowa.botin.components.slideritem.view;

import java.io.File;

import com.thinknowa.botin.Bottin;
import com.thinknowa.botin.R;
import com.thinknowa.botin.components.slideritem.model.Track;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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

	private AsyncTask<Track, Void, Bitmap> actualAsyncTask = null;

	public AlbumArt(View albumArtViewGroup, Type type, Typeface font) {

		albumArtView = albumArtViewGroup.findViewById(type.getRId());
		this.type = type;
		
//		Typeface font = Typeface.createFromAsset(Bottin.getInstance().getAssets(), "Shadows Into Light Two.ttf");

		albumArt = (ImageView) albumArtView.findViewById(R.id.picture);
		title = (TextView) albumArtView.findViewById(R.id.trackTitle);
		
//		this.txtTitle.setTypeface(font);
		title.setTypeface(font);

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
						Log.v("AlbumArt", "albumart for " + track.getName()
								+ " resolved");
					}
				}
			}.execute(track);
		}
		
	}

	/**
	 * update View synchronous with attributes easy to resolve
	 * 
	 * @param track
	 */
	public void setTrackDigest(final Track track) {
		Log.d("AlbumArt", "setTrackDigest 1");
		if (track != null) {
			Log.d("AlbumArt", "setTrackDigest 2");
			//title.setText(track.accept(InstanceFormatter.SHORT_WITH_NUMBER));
			//txtTitle.setText(track.getName());
//			artist.setText(track.getName());
			title.setText(track.getName());
			// artist.setText(track.GetArtist().accept(InstanceFormatter.SHORT));
		} else {
			title.setText("");
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
