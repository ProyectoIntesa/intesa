package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class P_SeleccionarUsuario extends PopupPanel {

	private static final int COL_ROL_VENTAS = 0;
	private static final int COL_ROL_GERENTE_COMPRAS = 1;
	private static final int COL_ROL_COMPRAS = 2;
	private static final int COL_ROL_INGENIERIA = 3;
	
	private static final int COL_ROL_GERENTE_PRODUCCION = 0;
	private static final int COL_ROL_JEFE_FABRICA = 1;
	private static final int COL_ROL_SUPERVISOR_PRODUCCION = 2;
	private static final int COL_ROL_ALMACEN = 3;

	private FlexTable panel;
	private FlexTable tablaElementos;

	private ScrollPanel contenedorTabla;

	private Constantes constante = GWT.create(Constantes.class);

	private Label rol;
	private Label pie;
	
	private Label ventas;
	private Label gerenteProduccion;
	private Label supervisor;
	private Label jefeFabrica;
	private Label gerenteCompras;
	private Label ingenieria;
	private Label compras;
	private Label almacen;

	private Button salir;

	
	public String rolSeleccionado;
	
	public P_SeleccionarUsuario() {

		super(false);


		ventas = new Label("");
		ventas.setSize("48px", "48px");
		ventas.addStyleName("labelVentas");

		gerenteProduccion = new Label("");
		gerenteProduccion.setSize("48px", "48px");
		gerenteProduccion.addStyleName("labelGerenteProduccion");
		
		jefeFabrica = new Label("");
		jefeFabrica.setSize("48px", "48px");
		jefeFabrica.addStyleName("labelGerenteProduccion");
		
		gerenteCompras = new Label("");
		gerenteCompras.setSize("48px", "48px");
		gerenteCompras.addStyleName("labelGerenteProduccion");
		
		supervisor = new Label("");
		supervisor.setSize("48px", "48px");
		supervisor.addStyleName("labelSupervisorProduccion");
		
		ingenieria = new Label("");
		ingenieria.setSize("48px", "48px");
		ingenieria.addStyleName("labelIngenieria1");
		
		compras = new Label("");
		compras.setSize("48px", "48px");
		compras.addStyleName("labelCompras");
		
		almacen = new Label("");
		almacen.setSize("48px", "48px");
		almacen.addStyleName("labelAlmacen");
		
		
		
		setStyleName("fondoPopup");
		panel = new FlexTable();
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tablaSeleccionarUsuario");
		contenedorTabla.setHeight("200px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		
		
		
		tablaElementos.setText(0, COL_ROL_VENTAS, constante.ventas());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_VENTAS, "16%");
		tablaElementos.setText(0, COL_ROL_GERENTE_COMPRAS, constante.gerenteDeCompras());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_GERENTE_COMPRAS, "16%");
		tablaElementos.setText(0, COL_ROL_COMPRAS, constante.compras());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_COMPRAS, "16%");
		tablaElementos.setText(0, COL_ROL_INGENIERIA, constante.ingenieria());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL_INGENIERIA, "16%");
		
		
		tablaElementos.setText(2, COL_ROL_GERENTE_PRODUCCION, constante.gerenteDeProduccion());
		tablaElementos.getCellFormatter().setWidth(2, COL_ROL_GERENTE_PRODUCCION, "16%");
		tablaElementos.setText(2, COL_ROL_JEFE_FABRICA, constante.jefeDeFabrica());
		tablaElementos.getCellFormatter().setWidth(2, COL_ROL_JEFE_FABRICA, "16%");
		tablaElementos.setText(2, COL_ROL_SUPERVISOR_PRODUCCION, constante.supervisor());
		tablaElementos.getCellFormatter().setWidth(2, COL_ROL_SUPERVISOR_PRODUCCION, "16%");
		tablaElementos.setText(2, COL_ROL_ALMACEN, constante.almacen());
		tablaElementos.getCellFormatter().setWidth(2, COL_ROL_ALMACEN, "16%");
		
		

		
		tablaElementos.setWidget(1, COL_ROL_VENTAS, ventas);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_VENTAS, HasHorizontalAlignment.ALIGN_CENTER);
		
		ventas.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rolSeleccionado("VENTAS");
			}
		});		
		tablaElementos.setWidget(1, COL_ROL_GERENTE_COMPRAS, gerenteCompras);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_COMPRAS, HasHorizontalAlignment.ALIGN_CENTER);
		
		gerenteCompras.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rolSeleccionado("GERENTE DE COMPRAS");
			}
		});
		tablaElementos.setWidget(1, COL_ROL_COMPRAS, compras);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_COMPRAS, HasHorizontalAlignment.ALIGN_CENTER);
		
		compras.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rolSeleccionado("COMPRAS");
			}
		});
		tablaElementos.setWidget(1, COL_ROL_INGENIERIA, ingenieria);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(1, COL_ROL_INGENIERIA, HasHorizontalAlignment.ALIGN_CENTER);
		
		ingenieria.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rolSeleccionado("INGENIERIA");
			}
		});
		
		
		
		
		tablaElementos.setWidget(3, COL_ROL_GERENTE_PRODUCCION, gerenteProduccion);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(3, COL_ROL_GERENTE_PRODUCCION, HasHorizontalAlignment.ALIGN_CENTER);
		
		gerenteProduccion.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rolSeleccionado("GERENTE DE PRODUCCION");
			}
		});
		tablaElementos.setWidget(3, COL_ROL_JEFE_FABRICA, jefeFabrica);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(3, COL_ROL_JEFE_FABRICA, HasHorizontalAlignment.ALIGN_CENTER);
		
		jefeFabrica.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rolSeleccionado("JEFE DE FABRICA");
			}
		});
		tablaElementos.setWidget(3, COL_ROL_SUPERVISOR_PRODUCCION, supervisor);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(3, COL_ROL_SUPERVISOR_PRODUCCION, HasHorizontalAlignment.ALIGN_CENTER);
		
		supervisor.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rolSeleccionado("SUPERVISOR");
			}
		});
		tablaElementos.setWidget(3, COL_ROL_ALMACEN, almacen);
		tablaElementos.getFlexCellFormatter().setHorizontalAlignment(3, COL_ROL_ALMACEN, HasHorizontalAlignment.ALIGN_CENTER);
		
		almacen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rolSeleccionado("ALMACEN");
				
			}
		});
		
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezadoSeleccionarUsuario");
		tablaElementos.getRowFormatter().addStyleName(2, "tablaEncabezadoSeleccionarUsuario");

		
		
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});

		rol = new Label("SELECCIONAR EL ROL CON EL QUE SE DESEA INGRESAR");
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
		panel.setSize("400px", "80px");

	}


	protected void salir() {
		rolSeleccionado("SALIR");
		this.hide();

	}

	
	public void rolSeleccionado(String rol){
		
		rolSeleccionado = rol;
		
		this.hide();
		
	}
	
	public String devolverRolSeleccionado(){
		return rolSeleccionado;
	}
	
}
