	package edu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProyectoIntesa implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		
		P_Login log = new P_Login();
		RootPanel.get().add(log);
		
		
	}
}
