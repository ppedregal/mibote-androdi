package com.thinknowa.botin.data;

import java.io.File;

import android.os.Environment;
import android.util.Log;

public class MediaManager {

	// Properties
	private File storageRoot = null;


	/**
	 * constructor por defecto
	 */
	public MediaManager() {
		storageRoot = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/" + "Bottin/");
		

		isAvailableRoot();
	}

	public boolean isAvailableRoot() {
		if ((!storageRoot.exists()) && (!storageRoot.mkdirs())) {
			Log.e(MediaManager.class.getName(),
					"Not available to create content folder.");
			return false;
		}
		return true;
	}


	/*****************************************************************************************
	 * Getter && Setter
	 *****************************************************************************************/
	public File getStorageRoot() {
		return storageRoot;
	}

	}
