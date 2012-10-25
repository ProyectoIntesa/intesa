package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import edu.client.Constantes;

public class P_BuscarEmpleado extends PopupPanel {

	
	private FlexTable panel;
	private FlexTable tablaElementos;
	
	private ScrollPanel contenedorTabla;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	private static final int COL_NROLEGAJO = 1;
	private static final int COL_NOMBRE = 2;
	private static final int COL_APELLIDO = 3;
	private static final int COL_PUESTO = 4;
	private static final int MAS_INFO = 5;
	
	
	
	public P_BuscarEmpleado(){
		
		super(false);

		setStyleName("fondoPopup");
		
		panel = new FlexTable();
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("350px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");	
		tablaElementos.setText(0, COL_NROLEGAJO, constante.nroOrdenDePedido());
		tablaElementos.getCellFormatter().setWidth(0, COL_NROLEGAJO, "23%");
		tablaElementos.setText(0, COL_NOMBRE, constante.nroOrdenDePedido());
		tablaElementos.getCellFormatter().setWidth(0, COL_NOMBRE, "23%");
		tablaElementos.setText(0, COL_APELLIDO, constante.nroOrdenDePedido());
		tablaElementos.getCellFormatter().setWidth(0, COL_APELLIDO, "23%");
		tablaElementos.setText(0, COL_PUESTO, constante.nroOrdenDePedido());
		tablaElementos.getCellFormatter().setWidth(0, COL_PUESTO, "23%");
		
		
		
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
	}
	
	
	
}
