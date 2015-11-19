package com.thinknowa.botin.components.slideritem.model;


public class TrackBundle
{
	private final Track track;
	private final Track trackAfter;
	private final Track trackBefore;

	
	public TrackBundle()
	{
		this.track = null;
		this.trackAfter = null;
		this.trackBefore = null;
	}

	public TrackBundle(Track track,
							 Track trackAfter,
							 Track trackBefore)
	{
		this.track = track;
		this.trackAfter = trackAfter;
		this.trackBefore = trackBefore;
	}

	public Track getTrackBefore()
	{
		return trackBefore;
	}

	public Track getTrack()
	{
		return track;
	}

	public Track getTrackAfter()
	{
		return trackAfter;
	
	}
}
