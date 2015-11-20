package com.thinknowa.botin.components.slideritem.view;

import java.io.File;

import com.thinknowa.botin.components.slideritem.model.Track;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public abstract class AlbumArtResolver extends AsyncTask<Track, Void, Bitmap> {

	protected AlbumArtResolver() {

	}

	@Override
	protected Bitmap doInBackground(Track... params) {
		Thread.currentThread().setName(
				Thread.currentThread().getName() + ":AlbumArtResolver");
		for (LookupStrategy lookupStrategy : new LookupStrategy[] {
				new CachedFsLookupStrategy(), new IdTagLookupStrategy(),
				new FsLookupStrategy() }) {
			try {
				Bitmap albumArt = lookupStrategy.lookup(params[0]);
				if (albumArt != null)
					return albumArt;
			} catch (Exception e) {
				Log.e("AlbumArtResolver", "Error reading albumArt for :"
						+ params[0].getName(), e);
			}
		}
		return null;
	}

	private interface LookupStrategy {
		Bitmap lookup(Track track);
	}

	private class CachedFsLookupStrategy implements LookupStrategy {

		public Bitmap lookup(Track track) {

			File file = (track != null) ? track.getCover() : null;

			if (file != null) {
				Bitmap myBitmap = BitmapFactory.decodeFile(file
						.getAbsolutePath());
				return myBitmap;
			}

			return null;
		}
	}

	private class FsLookupStrategy implements LookupStrategy {

		public Bitmap lookup(Track track) {

			File file = (track != null) ? track.getCover() : null;

			if (file != null) {
				Bitmap myBitmap = BitmapFactory.decodeFile(file
						.getAbsolutePath());
				return myBitmap;
			}

			return null;
		}
	}

	private class IdTagLookupStrategy implements LookupStrategy {
		public Bitmap lookup(Track track) {

			File file = (track != null) ? track.getCover() : null;

			if (file != null) {
				Bitmap myBitmap = BitmapFactory.decodeFile(file
						.getAbsolutePath());
				return myBitmap;
			}

			return null;
		}
	}
}
