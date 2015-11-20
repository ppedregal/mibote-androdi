package com.thinknowa.botin.delegate;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thinknowa.botin.Bottin;
import com.thinknowa.botin.components.slideritem.model.Track;
import com.thinknowa.botin.sdk.Sdk;
import com.thinknowa.botin.sdk.interceptors.LoggingInterceptor;
import com.thinknowa.botin.sdk.model.Tin;

import android.os.AsyncTask;
import android.util.Log;

public class LoaderItems extends AsyncTask<Void, Void, ArrayList<Track>> {

	// Properties
	private ILoaderItems delegate;
	private Sdk sdk;

	/**
	 * Constructor
	 * 
	 * @param delegate
	 */
	public LoaderItems(ILoaderItems delegate) {
		this.delegate = delegate;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		delegate.loaderItemsPreExecute();
	}

	@Override
	protected ArrayList<Track> doInBackground(Void... params) {
		ArrayList<Track> result = new ArrayList<Track>();
		String pathRoot = Bottin.getInstance().getMediaMgr().getStorageRoot().getAbsolutePath();
			
		sdk = new Sdk("http://172.26.0.222:3000/api/");
	    sdk.logLevel(LoggingInterceptor.Level.BODY);
		sdk.login("email1@example.com", "test");
		List<Tin> account1BotesAfter = sdk.tins();
		if(account1BotesAfter != null){
			Log.d("LoaderItems: ", "Load Items from server("+account1BotesAfter.size()+")");
			Iterator<Tin> it = account1BotesAfter.iterator();
			boolean isCover = true;
			while(it.hasNext()){
				Tin item = it.next();
				String pathCover1 = pathRoot + ((isCover) ? "/bote_01" : "/bote_01");
				String plusTitle = ((isCover) ? "02" : " 01");
				isCover = !isCover;
				File fCover = getImageFile(pathCover1);
				result.add(new Track(item.getId(), (item.getName() + plusTitle), item.getAmount(), fCover));
			}
		}else{
			Log.d("LoaderItems: ", "Load Items from server(0)");
		}

		return result;
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

	@Override
	protected void onPostExecute(ArrayList<Track> items) {
		delegate.loaderItemsFinished(items);
	}
}
