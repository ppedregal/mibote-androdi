package com.thinknowa.botin.components.slideritem.util;

import java.util.Collections;
import java.util.List;

import com.thinknowa.botin.R;
import com.thinknowa.botin.components.slideritem.model.Track;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/*
import es.zalo.rumbapp.R;
import es.zalo.rumbapp.component.mediaplayer.model.FSobject;
import es.zalo.rumbapp.component.mediaplayer.model.Instance;
import es.zalo.rumbapp.component.mediaplayer.model.InstanceVisitor;
import es.zalo.rumbapp.component.mediaplayer.model.Track;
import es.zalo.rumbapp.component.mediaplayer.presentation.InstanceFormatter;
*/

public class DefaultAdapter<T extends Track> extends ArrayAdapter<T> {
	private final List<T> objects;
	private final Activity activity;
	private final boolean allowsDuplicates;
//	private final InstanceFormatter formatter;
	final Handler instanceAddedhandler = new Handler(Looper.getMainLooper());

	/**
	 * @param context
	 *            needed by super
	 * @param objects
	 *            has to be prepared according allowsDuplicates and has to be
	 *            sorted (if needed)
	 * @param startingActivity
	 *            to join UI Thread
	 * @param allowsDuplicates
	 *            remove duplicates by adding items
	 * @param formatter
	 */
	// InstanceFormatter formatter
	public DefaultAdapter(Context context, List<T> objects,
			Activity startingActivity, boolean allowsDuplicates
			) {
		super(context, R.layout.com_mp_file_list_entry, objects);
		this.activity = startingActivity;
		this.objects = objects;
		this.allowsDuplicates = allowsDuplicates;
//		this.formatter = formatter;
	}

	public void add(final T object) {
		if (allowsDuplicates || !objects.contains(object)) {
			activity.runOnUiThread(new Runnable() {
				public void run() {
					if (!instanceAddedhandler.hasMessages(0)) {
						instanceAddedhandler.postDelayed(new Runnable() {
							public void run() {
								if (allowsDuplicates
										|| !objects.contains(object)) {
									objects.add(object);
									/*
									Collections.sort(objects,
											new FormattedInstanceComparator(
													formatter));
								   */					
									notifyDataSetChanged();
								}
							}
						}, 2000);
					}
				}
			});
		}
	}

	/**
	 * @param object
	 *            objects has to be prepared according allowsDuplicates and has
	 *            to be sorted (if needed)
	 */
	public void replace(final List<? extends T> object) {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				objects.clear();
				objects.addAll(object);
				notifyDataSetChanged();
			}
		});
	}

	public void clear() {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				objects.clear();
				notifyDataSetChanged();
			}
		});
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.com_mp_file_list_entry,
				parent, false);

		T currObject = getItem(position);

		TextView textView = (TextView) rowView.findViewById(R.id.label);
		//textView.setText(currObject.accept(formatter));

		final ImageView icon = (ImageView) rowView.findViewById(R.id.icon);

		/*
		currObject.accept(new InstanceVisitor<Object>() {
			public Object visit(Track track) {
				icon.setImageResource(R.drawable.com_mp_icon_player_note_24);
				return null;
			}

			public Object visit(FSobject FSobject) {
				icon.setImageResource(R.drawable.com_mp_dir24);
				return null;
			}
		});
		*/

		return rowView;
	}
}
