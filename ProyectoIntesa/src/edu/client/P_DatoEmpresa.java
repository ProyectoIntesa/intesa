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

public class P_DatoEmpresa extends PopupPanel {

	private static final int COL_NOMBRE = 1;
	private static final int COL_CARGO = 2;
	private static final int COL_TELEMPRESA = 3;
	private static final int COL_INTERNO = 4;
	private static final int COL_TELPARTICULAR = 5;
	private static final int COL_CELULAR = 6;
	private static final int COL_CORREO = 7;
	
	private FlexTable panel;
	private FlexTable tablaElementos;
	private FlexTable botones;
	private ScrollPanel contenedorTabla;
	private Constantes constante = GWT.create(Constantes.class);
	
	private Label empresa;
	private Label rubro;
	private Label cuit;
	private Label responsable;
	private Label telefono;
	private Label fax;
	private Label mail;
	private Label paginaWeb;
	private Label direccion;
	private Label ciudad;
	private Label codigoPostal;
	private Label provincia;
	private Label pais;
	private Label observacion;
	private Label contactos;
	private Label pie;
	
	private Button salir;
	private Button modificar;
	private Button eliminar;

	public P_DatoEmpresa() {

		super(false);

		setStyleName("fondoPopup");
		panel = new FlexTable();
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("200px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_NOMBRE, constante.nombre());
		tablaElementos.getCellFormatter().setWidth(0, COL_NOMBRE, "14%");
		tablaElementos.setText(0, COL_CARGO, constante.cargo());
		tablaElementos.getCellFormatter().setWidth(0, COL_CARGO, "14%");
		tablaElementos.setText(0, COL_TELEMPRESA, constante.telefono());
		tablaElementos.getCellFormatter().setWidth(0, COL_TELEMPRESA, "14%");
		tablaElementos.setText(0, COL_INTERNO, constante.interno());
		tablaElementos.getCellFormatter().setWidth(0, COL_INTERNO, "14%");
		tablaElementos.setText(0, COL_TELPARTICULAR, constante.telefonoParticular());
		tablaElementos.getCellFormatter().setWidth(0, COL_TELPARTICULAR, "14%");
		tablaElementos.setText(0, COL_CELULAR, constante.celular());
		tablaElementos.getCellFormatter().setWidth(0, COL_CELULAR, "14%");
		tablaElementos.setText(0, COL_CORREO, constante.eMail());
		tablaElementos.getCellFormatter().setWidth(0, COL_CORREO, "14%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");	
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		modificar = new Button(constante.modificar());
		modificar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//salir();
			}
		});

		eliminar = new Button(constante.eliminarCliente());
		eliminar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//salir();
			}
		});
		
		empresa = new Label();
		rubro = new Label();
		cuit = new Label();
		responsable = new Label();
		telefono = new Label();
		fax = new Label();
		mail = new Label();
		paginaWeb = new Label();
		direccion = new Label();
		ciudad = new Label();
		codigoPostal = new Label();
		provincia = new Label();
		pais = new Label();
		observacion = new Label();
		contactos = new Label(constante.constactos());
		contactos.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");

		panel.setText(0, 0, "EMPRESA: " + "FORESTAL");
		panel.setText(0, 1, "RUBRO: " + "MADERERA");
		panel.getRowFormatter().addStyleName(0, "textoPlano");

		panel.setText(1, 0, "CUIT: " + "03-45256987-2");
		panel.setText(1, 1, "RESPONSABLE INSCRIPTO");
		panel.getRowFormatter().addStyleName(1, "textoPlano");

		panel.setText(2, 0, "TELEFONO: " + "0342-4692895");
		panel.setText(2, 1, "FAX: " + "0342-4692987");
		panel.getRowFormatter().addStyleName(2, "textoPlano");

		panel.setText(3, 0, "MAIL: " + "forestal@hotmail.com");
		panel.getFlexCellFormatter().setColSpan(3, 0, 2);
		panel.getRowFormatter().addStyleName(3, "textoPlano");

		panel.setText(4, 0, "WEB: " + "www.forestalmaderas.com.ar");
		panel.getFlexCellFormatter().setColSpan(4, 0, 2);
		panel.getRowFormatter().addStyleName(4, "textoPlano");

		panel.setText(5, 0, "DIRECCION: " + "J.P.López 566");
		panel.getFlexCellFormatter().setColSpan(5, 0, 2);
		panel.getRowFormatter().addStyleName(5, "textoPlano");

		panel.setText(6, 0, "CIUDAD: " + "Santa Fe");
		panel.setText(6, 1, "CÓDIGO POSTAL: " + "3000");
		panel.getRowFormatter().addStyleName(6, "textoPlano");

		panel.setText(7, 0, "PROVINCIA: " + "Santa Fe");
		panel.setText(7, 1, "PAÍS: " + "Argentina");
		panel.getRowFormatter().addStyleName(7, "textoPlano");

		panel.setText(8, 0, "OBSERVACIONES: " + "ojo con este tipo");
		panel.getFlexCellFormatter().setColSpan(8, 0, 2);
		panel.getRowFormatter().addStyleName(8, "textoPlano");

		panel.setWidget(9, 0, contactos);
		panel.getFlexCellFormatter().setColSpan(9, 0, 2);
		
		panel.setWidget(10, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(10, 0, 2);
		
		panel.setWidget(11, 0, pie);
		panel.getFlexCellFormatter().setColSpan(11, 0, 2);
		
		
		botones = new FlexTable();
		botones.setSize("100%", "100%");
		botones.setWidget(0, 0, modificar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);		
		botones.setWidget(0, 1, eliminar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 2, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);		
		
		
		panel.getFlexCellFormatter().setColSpan(12, 0, 2);
		panel.setWidget(12, 0, botones);
		
		
		
		
		
		setWidget(panel);
		panel.setSize("700px", "400px");

	}

	
	protected void salir() {
		this.hide();

	}
	
	
}
