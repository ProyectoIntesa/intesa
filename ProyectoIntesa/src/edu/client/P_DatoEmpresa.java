package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;

public class P_DatoEmpresa extends PopupPanel {

	private static final int COL_NOMBRE = 0;
	private static final int COL_CARGO = 1;
	private static final int COL_TELEMPRESA = 2;
	private static final int COL_INTERNO = 3;
	private static final int COL_TELPARTICULAR = 4;
	private static final int COL_CELULAR = 5;
	private static final int COL_CORREO = 6;
	
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
	
	private ClienteDTO empSelec;
	private ContactoDTO contSelec;

	public P_DatoEmpresa(ClienteDTO empSelec) {

		super(false);
		
		this.empSelec = empSelec;
		
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
		
		
		
//		for (int i = 0; i < empSelec.getContacto().size(); i++){
//		
//			Window.alert(""+empSelec.getContacto().size());
//			
//			tablaElementos.setText(i+1, COL_NOMBRE, empSelec.getContacto().get(i).getNombre());
//			tablaElementos.setText(i+1, COL_CARGO, empSelec.getContacto().get(i).getCargo());
//			tablaElementos.setText(i+1, COL_TELEMPRESA, empSelec.getContacto().get(i).getTelefonoEmpresa());
//			tablaElementos.setText(i+1, COL_INTERNO, empSelec.getContacto().get(i).getInternoEmpresa());
//			tablaElementos.setText(i+1, COL_TELPARTICULAR, empSelec.getContacto().get(i).getTelefonoParticular());
//			tablaElementos.setText(i+1, COL_CELULAR, empSelec.getContacto().get(i).getCelular());
//			tablaElementos.setText(i+1, COL_CORREO, empSelec.getContacto().get(i).getMail());
//			
//		}
		
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

		panel.setText(0, 0, "EMPRESA: " + empSelec.getNombre());
		panel.setText(0, 1, "RUBRO: " + empSelec.getRubro());
		panel.getRowFormatter().addStyleName(0, "textoPlano");

		panel.setText(1, 0, "CUIT: " + empSelec.getCuit());
		panel.setText(1, 1, empSelec.getResponsable());
		panel.getRowFormatter().addStyleName(1, "textoPlano");

		panel.setText(2, 0, "TELEFONO: " + empSelec.getTelefono());
		panel.setText(2, 1, "FAX: " + empSelec.getFax());
		panel.getRowFormatter().addStyleName(2, "textoPlano");

		panel.setText(3, 0, "MAIL: " + empSelec.getMail());
		panel.getFlexCellFormatter().setColSpan(3, 0, 2);
		panel.getRowFormatter().addStyleName(3, "textoPlano");

		panel.setText(4, 0, "WEB: " + empSelec.getPaginaWeb());
		panel.getFlexCellFormatter().setColSpan(4, 0, 2);
		panel.getRowFormatter().addStyleName(4, "textoPlano");

		panel.setText(5, 0, "DIRECCION: " + empSelec.getDireccion().getCalle() + " " + empSelec.getDireccion().getAltura());
		panel.getFlexCellFormatter().setColSpan(5, 0, 2);
		panel.getRowFormatter().addStyleName(5, "textoPlano");

		panel.setText(6, 0, "CIUDAD: " + empSelec.getDireccion().getLocalidad());
		panel.setText(6, 1, "CÓDIGO POSTAL: " + empSelec.getDireccion().getCodigoLocalidad());
		panel.getRowFormatter().addStyleName(6, "textoPlano");

		panel.setText(7, 0, "PROVINCIA: " + empSelec.getDireccion().getProvincia());
		panel.setText(7, 1, "PAÍS: " + empSelec.getDireccion().getPais());
		panel.getRowFormatter().addStyleName(7, "textoPlano");
		
		panel.setText(8, 0, "CPA: " + empSelec.getDireccion().getCpa());
		panel.getRowFormatter().addStyleName(8, "textoPlano");

		panel.setText(9, 0, "OBSERVACIONES: " + empSelec.getObservaciones());
		panel.getFlexCellFormatter().setColSpan(9, 0, 2);
		panel.getRowFormatter().addStyleName(9, "textoPlano");

		panel.setWidget(10, 0, contactos);
		panel.getFlexCellFormatter().setColSpan(10, 0, 2);
		
		panel.setWidget(11, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(11, 0, 2);
		
		panel.setWidget(12, 0, pie);
		panel.getFlexCellFormatter().setColSpan(12, 0, 2);
		
		
		botones = new FlexTable();
		botones.setSize("100%", "100%");
		botones.setWidget(0, 0, modificar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);		
		botones.setWidget(0, 1, eliminar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 2, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);		
		
		
		panel.getFlexCellFormatter().setColSpan(12, 0, 2);
		panel.setWidget(13, 0, botones);
		
		
		
		
		
		setWidget(panel);
		panel.setSize("700px", "400px");

	}

	
	
	protected void salir() {
		this.hide();

	}
	
	
}
