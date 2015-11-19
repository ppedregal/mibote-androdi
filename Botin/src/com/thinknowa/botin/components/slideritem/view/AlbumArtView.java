package com.thinknowa.botin.components.slideritem.view;

import com.thinknowa.botin.Bottin;
import com.thinknowa.botin.R;
import com.thinknowa.botin.components.slideritem.controller.TouchHandler;
import com.thinknowa.botin.components.slideritem.model.Track;
import com.thinknowa.botin.components.slideritem.model.TrackBundle;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
/*
import es.zalo.rumbapp.component.mediaplayer.player.IPlayer;
import es.zalo.rumbapp.component.mediaplayer.player.ObservableOutput;
import es.zalo.rumbapp.component.mediaplayer.player.OutputCommand;
*/
public class AlbumArtView {

	private final View albumArtViewGroup;

	// AlbumArt
	private final AlbumArt albumArt;
	private final AlbumArt albumArtLeft;
	private final AlbumArt albumArtRight;

	private AsyncTask<Track, Void, TrackBundle> actualAsyncTask = null;

	public AlbumArtView(final Activity activity, final Bottin app,
			final FileChooser fileChooser, TrackBundle bundle) {
		albumArtViewGroup = activity.findViewById(R.id.relativeLayout_albumArt);

		albumArt = new AlbumArt(albumArtViewGroup, AlbumArt.Type.CENTER);
		albumArtLeft = new AlbumArt(albumArtViewGroup, AlbumArt.Type.LEFT);
		albumArtRight = new AlbumArt(albumArtViewGroup, AlbumArt.Type.RIGHT);

		if (bundle != null) {
			albumArt.setTrackDigest(bundle.getTrack());
			albumArtLeft.setTrackDigest(bundle.getTrackBefore());
			albumArtRight.setTrackDigest(bundle.getTrackAfter());
		}

		/*
		app.player.addObserver(new ObservableOutput.PlayerObserver() {
			public void trackChanged(final Track track, int lengthInMillis) {
				albumArt.setTrackDigest(track);
				albumArtLeft.setTrackDigest(null);
				albumArtRight.setTrackDigest(null);

				actualAsyncTask = new AsyncTask<Track, Void, TrackBundle>() {

					@Override
					protected TrackBundle doInBackground(Track... params) {
						TrackBundle bundle = null;
						Thread.currentThread().setName(
								Thread.currentThread().getName()
										+ ":albumArtUpdater");
						// if(actualAsyncTask == this)
						// {
						while (bundle == null) {
							bundle = fileChooser.enrich(params[0]);
						}
						// }
						return bundle;
					}

					@Override
					protected void onPostExecute(TrackBundle trackBundle) {
						if (actualAsyncTask == this && trackBundle != null) {
							albumArt.setTrack(trackBundle.getTrack());
							albumArtRight.setTrack(trackBundle.getTrackAfter());
							albumArtLeft.setTrack(trackBundle.getTrackBefore());
						}
					}
				}.execute(track);

			}

			public void started() {
				// ignore
			}

			public void stopped() {
				// ignore
			}

			public String getId() {
				return "AlbumArtUpdater";
			}
		});
		*/

		TouchHandler touchHandler = new TouchHandler(activity,
				albumArt.getAlbumArtView(), albumArtLeft.getAlbumArtView(),
				albumArtRight.getAlbumArtView()) {
			@Override
			protected void nextGestureRecognized() {
				if(fileChooser.next()){
					fileChooser.getCurrentTrack();
				}
				/*
				app.player.connectPlayer(new OutputCommand() {
					public void connected(IPlayer output) {
						if (fileChooser.next()) {
							output.play(fileChooser.getCurrentTrack());
						}
					}
				});
				*/
			}

			@Override
			protected void previousGestureRecognized() {
				if(fileChooser.back()){
					fileChooser.getCurrentTrack();
				}
				/*
				app.player.connectPlayer(new OutputCommand() {
					public void connected(IPlayer output) {
						if (fileChooser.back()) {
							output.play(fileChooser.getCurrentTrack());
						}
					}
				});
				*/
			}

			public String getId() {
				return "SwipeAndInstantFilterSelectionDetector";
			}
		};

		albumArt.getAlbumArtView().setOnTouchListener(touchHandler);
		// app.player.addObserver(touchHandler);
	}
}
