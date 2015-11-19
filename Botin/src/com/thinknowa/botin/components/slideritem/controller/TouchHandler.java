package com.thinknowa.botin.components.slideritem.controller;

import com.thinknowa.botin.components.slideritem.model.Track;

import android.app.Activity;
import android.graphics.Point;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

// import es.zalo.rumbapp.component.mediaplayer.player.ObservableOutput;

public abstract class TouchHandler implements View.OnTouchListener { // ObservableOutput.PlayerObserver

	private enum Mode {
		MENU, TRACK
	}

	private final View[] scrollingViews;
	private final Point[] initalScrollingOfScrollingViews;
	private final Activity activity;

	private final GestureDetector gestureDetector;

	private Mode currMode = Mode.TRACK;

	// private Track currTrack = null;

	protected TouchHandler(final Activity activity, View... scrollingViews) {
		this.activity = activity;

		initalScrollingOfScrollingViews = new Point[scrollingViews.length];
		this.scrollingViews = scrollingViews;

		gestureDetector = new GestureDetector(activity, gestureListener);
		gestureDetector.setIsLongpressEnabled(false);
	}

	GestureDetector.OnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			switch (currMode) {
			case MENU:
				break;
			case TRACK:
				scrollScrollingViewsBy((int) (distanceX * 1.5), 0);
				break;
			}

			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			switch (currMode) {
			case MENU:
				break;
			case TRACK:
				if (velocityX < 0) {
					if (e1.getX() > e2.getX()) {
						nextGestureRecognized();
						return true;
					}
				} else {
					if (e1.getX() < e2.getX()) {
						previousGestureRecognized();
						return true;
					}
				}
				break;
			}
			return false;
		}
	};

	public boolean onTouch(View v, MotionEvent event) {
		boolean wasConsumed = gestureDetector.onTouchEvent(event);
		boolean consumed = false;

		if (MotionEvent.ACTION_DOWN == event.getAction()) {
			consumed = true;
		} else {

			if (MotionEvent.ACTION_UP == event.getAction()) {
				if (!wasConsumed) {
					if (Mode.TRACK.equals(currMode)) {
						if (scrollingViews[0].getScrollX() > scrollingViews[0]
								.getWidth() * 2 / 3) {
							nextGestureRecognized();
							consumed = true;
						} else if (-scrollingViews[0].getScrollX() > scrollingViews[0]
								.getWidth() * 2 / 3) {
							previousGestureRecognized();
							consumed = true;
						}
					}
				}

				resetScrollingViews();
				currMode = Mode.TRACK;
			}
		}
		return consumed;
	}

	private void resetScrollingViews() {
		saveInitalPositions();
		for (int i = 0; i < scrollingViews.length; i++) {
			scrollingViews[i].scrollTo(initalScrollingOfScrollingViews[i].x,
					initalScrollingOfScrollingViews[i].y);
		}
	}

	private void scrollScrollingViewsBy(int x, int y) {
		saveInitalPositions();
		for (View scrollingView : scrollingViews) {
			scrollingView.scrollBy(x, y);
		}
	}

	private void saveInitalPositions() {
		if (initalScrollingOfScrollingViews[0] == null) {
			for (int i = 0; i < scrollingViews.length; i++) {
				initalScrollingOfScrollingViews[i] = new Point(
						scrollingViews[i].getScrollX(),
						scrollingViews[i].getScrollY());
			}
		}
	}

	public void trackChanged(Track track, int lengthInMillis) {
		// this.currTrack = track;
	}

	public void started() {
		// do nothing
	}

	public void stopped() {
		// do nothing
	}

	protected abstract void nextGestureRecognized();

	protected abstract void previousGestureRecognized();
}
