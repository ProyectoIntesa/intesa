	package edu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProyectoIntesa implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			
			@Override
			public void onUncaughtException(Throwable e) {
				
			Window.alert("ERROR: "+ e.getMessage()+" "+e.getCause());
				e.printStackTrace();
			}
		});
		
		P_Login log = new P_Login();
		RootPanel.get().add(log);
		
		
	}
}
