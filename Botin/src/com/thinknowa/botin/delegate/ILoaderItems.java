package com.thinknowa.botin.delegate;

import java.util.ArrayList;

import com.thinknowa.botin.components.slideritem.model.Track;


/**
 * Interfaz que implementa el Delegado de la Tarea Asincrona de Carga de items
 * 
 */
public interface ILoaderItems {

	/**
	 * Acci�n a realizar antes de empezar la carga de Items
	 */
	void loaderItemsPreExecute();

	/**
	 * Acci�n a realizar cuando finaliza la descarga
	 */
	void loaderItemsFinished(ArrayList<Track> items);

}
