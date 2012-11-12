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
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import edu.client.AdministradorService.AdministradorService;
import edu.client.AdministradorService.AdministradorServiceAsync;
import edu.shared.DTO.UsuarioCompDTO;

public class P_SeleccionarUsuario extends PopupPanel {

	private static final int COL_ROL_VENTAS = 1;
	private static final int COL_ROL_PRODUCCION = 2;
	private static final int COL_ROL_INGENIERIA = 3;
	private static final int COL_ROL_COMPRAS = 4;
	private static final int COL_ROL_ALMACEN = 5;

	private FlexTable panel;
	private FlexTable tablaElementos;

	private ScrollPanel contenedorTabla;

	private Constantes constante = GWT.create(Constantes.class);

	private Label rol;
	private Label pie;
	
	private Label ventas;
	private Label produccion;
	private Label ingenieria;
	private Label compras;
	private Label almacen;

	private Button salir;

	public P_SeleccionarUsuario() {

		super(false);


		Label ventas = new Label("");
		ventas.setSize("48px", "48px");
		ventas.addStyleName("labelVentas");

		Label produccion = new Label("");
		produccion.setSize("48px", "48px");
		produccion.addStyleName("labelProduccion");
		
		Label ingenieria = new Label("");
		ingenieria.setSize("48px", "48px");
		ingenieria.addStyleName("labelIngenieria1");
		
		Label compras = new Label("");
		compras.setSize("48px", "48px");
		compras.addStyleName("labelCompras");
		
		Label almacen = new Label("");
		almacen.setSize("48px", "48px");
		almacen.addStyleName("labelAlmacen");
		
		
		
		setStyleName("fondoPopup");
		panel = new FlexTable();

		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("350px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_ROL_VENTAS, constante.ventas());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_VENTAS, "20%");
		tablaElementos.setText(0, COL_ROL_PRODUCCION, constante.produccion());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_PRODUCCION, "20%");
		tablaElementos.setText(0, COL_ROL_INGENIERIA, constante.ingenieria());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_INGENIERIA, "20%");
		tablaElementos.setText(0, COL_ROL_COMPRAS, constante.compras());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_COMPRAS, "20%");
		tablaElementos.setText(0, COL_ROL_ALMACEN, constante.almacen());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_ALMACEN, "20%");
		
		
		tablaElementos.setWidget(1, COL_ROL_VENTAS, ventas);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_VENTAS, HasHorizontalAlignment.ALIGN_CENTER);
		
		tablaElementos.setWidget(1, COL_ROL_PRODUCCION, produccion);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_PRODUCCION, HasHorizontalAlignment.ALIGN_CENTER);
		
		tablaElementos.setWidget(1, COL_ROL_INGENIERIA, ingenieria);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_INGENIERIA, HasHorizontalAlignment.ALIGN_CENTER);
		
		tablaElementos.setWidget(1, COL_ROL_COMPRAS, compras);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_COMPRAS, HasHorizontalAlignment.ALIGN_CENTER);
		
		tablaElementos.setWidget(1, COL_ROL_ALMACEN, almacen);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_ALMACEN, HasHorizontalAlignment.ALIGN_CENTER);
		
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");

		
		
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});

		rol = new Label("ROL CON EL QUE SE DESEA INGRESAR");
		rol.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");

		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		panel.setWidget(0, 0, rol);

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
