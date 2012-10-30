package edu.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;


public class P_BuscarUsuario extends PopupPanel  {

	
	private static final int COL_USUARIO = 1;
	private static final int COL_CONTRASENIA = 2;
	private static final int COL_ROL = 3;
	private static final int COL_MAS_INFO = 4;
	
	private FlexTable panel;
	private FlexTable tablaElementos;

	private ScrollPanel contenedorTabla;

	private Constantes constante = GWT.create(Constantes.class);
	
	private Label usuarios;
	private Label pie;
	
	private Button salir;
	
	public P_BuscarUsuario(){
	
		super(false);
		
		setStyleName("fondoPopup");
		panel = new FlexTable();

		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("350px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_USUARIO, constante.usuario());
		tablaElementos.getCellFormatter().setWidth(0, COL_USUARIO, "30%");
		tablaElementos.setText(0, COL_CONTRASENIA, constante.contrasenia());
		tablaElementos.getCellFormatter().setWidth(0, COL_CONTRASENIA, "30%");
		tablaElementos.setText(0, COL_ROL, constante.rol());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL, "30%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "10%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		
		usuarios = new Label(constante.usuarios());
		usuarios.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");

		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		panel.setWidget(0, 0, usuarios);

		panel.getFlexCellFormatter().setColSpan(1, 0, 2);
		panel.setWidget(1, 0, contenedorTabla);

		panel.getFlexCellFormatter().setColSpan(2, 0, 2);
		panel.setWidget(2, 0, pie);

		panel.setWidget(3, 1, salir);
		panel.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_CENTER);

		setWidget(panel);
		panel.setSize("850px", "400px");
		
		
	}	
	

	


	
	
	
	protected void salir() {
		this.hide();

	}

}
