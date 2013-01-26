package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;

public class P_ImpresionCompras extends Composite {
	
	private Constantes constante = GWT.create(Constantes.class);
	FlexTable formulario;
	private FlexTable botones;
	String usuario;
	private Button imprimir;
	private Button salir;
	
	public P_ImpresionCompras(FlexTable form , String user) {
		
		formulario= form;
		usuario = user;
		imprimir = new Button(constante.imprimir());
		imprimir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				imprimir();
			}
		});
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, imprimir);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		formulario.setWidget(0, 0, botones);
		formulario.getFlexCellFormatter().setColSpan(0, 0, 0);
		formulario.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);	
		
		initWidget(formulario);
	}

	protected void salir() {
		
		 P_PantallaCompras compras = new P_PantallaCompras(usuario);
		 RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		 RootPanel.get().add(compras);
		 try {
		 this.finalize();
		 } catch (Throwable e) {
		 e.printStackTrace();
		 }
	}

	protected void imprimir() {
		salir.setVisible(false);
		imprimir.setVisible(false);
		Window.print();
		salir();
	}
}
