package edu.client;

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

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.client.VentasService.VentasService;
import edu.client.VentasService.VentasServiceAsync;
import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.ProveedorDTO;

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
	private ProveedorDTO empSelec2;
	private ContactoDTO contSelec;
	private boolean modificarCliente = false;
	private boolean modificarProveedor = false;
	private String empresaContacto;
	
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
		
		
		
		for (int i = 0; i < empSelec.getContacto().size(); i++){
			
			tablaElementos.setText(i+1, COL_NOMBRE, empSelec.getContacto().get(i).getNombre());
			tablaElementos.setText(i+1, COL_CARGO, empSelec.getContacto().get(i).getCargo());
			tablaElementos.setText(i+1, COL_TELEMPRESA, empSelec.getContacto().get(i).getTelefonoEmpresa());
			tablaElementos.setText(i+1, COL_INTERNO, empSelec.getContacto().get(i).getInternoEmpresa());
			tablaElementos.setText(i+1, COL_TELPARTICULAR, empSelec.getContacto().get(i).getTelefonoParticular());
			tablaElementos.setText(i+1, COL_CELULAR, empSelec.getContacto().get(i).getCelular());
			tablaElementos.setText(i+1, COL_CORREO, empSelec.getContacto().get(i).getMail());
			tablaElementos.getRowFormatter().setStyleName(i+1, "tablaRenglon");
			
		}
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		modificar = new Button(constante.modificar());
		modificar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				modificarCliente();
			}
		});

		eliminar = new Button(constante.eliminarCliente());
		eliminar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eliminarCliente();
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

		panel.setText(5, 0, "DIRECCION: " + empSelec.getDireccion().getCalle() + "  N° " + empSelec.getDireccion().getAltura());
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
	
	public P_DatoEmpresa(ProveedorDTO empSelec) {

		super(false);
		
		this.empSelec2 = empSelec;
		
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
		
		
		
		for (int i = 0; i < empSelec.getContacto().size(); i++){
			
			tablaElementos.setText(i+1, COL_NOMBRE, empSelec2.getContacto().get(i).getNombre());
			tablaElementos.setText(i+1, COL_CARGO, empSelec2.getContacto().get(i).getCargo());
			tablaElementos.setText(i+1, COL_TELEMPRESA, empSelec2.getContacto().get(i).getTelefonoEmpresa());
			tablaElementos.setText(i+1, COL_INTERNO, empSelec2.getContacto().get(i).getInternoEmpresa());
			tablaElementos.setText(i+1, COL_TELPARTICULAR, empSelec2.getContacto().get(i).getTelefonoParticular());
			tablaElementos.setText(i+1, COL_CELULAR, empSelec2.getContacto().get(i).getCelular());
			tablaElementos.setText(i+1, COL_CORREO, empSelec2.getContacto().get(i).getMail());
			tablaElementos.getRowFormatter().setStyleName(i+1, "tablaRenglon");
			
		}
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		modificar = new Button(constante.modificar());
		modificar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				modificarProveedor();
			}
		});

		eliminar = new Button(constante.eliminarCliente());
		eliminar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eliminarProveedor();
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

		panel.setText(0, 0, "EMPRESA: " + empSelec2.getNombre());
		panel.setText(0, 1, "RUBRO: " + empSelec2.getRubro());
		panel.getRowFormatter().addStyleName(0, "textoPlano");

		panel.setText(1, 0, "CUIT: " + empSelec2.getCuit());
		panel.setText(1, 1, empSelec2.getResponsable());
		panel.getRowFormatter().addStyleName(1, "textoPlano");

		panel.setText(2, 0, "TELEFONO: " + empSelec2.getTelefono());
		panel.setText(2, 1, "FAX: " + empSelec2.getFax());
		panel.getRowFormatter().addStyleName(2, "textoPlano");

		panel.setText(3, 0, "MAIL: " + empSelec2.getMail());
		panel.getFlexCellFormatter().setColSpan(3, 0, 2);
		panel.getRowFormatter().addStyleName(3, "textoPlano");

		panel.setText(4, 0, "WEB: " + empSelec2.getPaginaWeb());
		panel.getFlexCellFormatter().setColSpan(4, 0, 2);
		panel.getRowFormatter().addStyleName(4, "textoPlano");

		panel.setText(5, 0, "DIRECCION: " + empSelec2.getDireccion().getCalle() + "  N° " + empSelec2.getDireccion().getAltura());
		panel.getFlexCellFormatter().setColSpan(5, 0, 2);
		panel.getRowFormatter().addStyleName(5, "textoPlano");

		panel.setText(6, 0, "CIUDAD: " + empSelec2.getDireccion().getLocalidad());
		panel.setText(6, 1, "CÓDIGO POSTAL: " + empSelec2.getDireccion().getCodigoLocalidad());
		panel.getRowFormatter().addStyleName(6, "textoPlano");

		panel.setText(7, 0, "PROVINCIA: " + empSelec2.getDireccion().getProvincia());
		panel.setText(7, 1, "PAÍS: " + empSelec2.getDireccion().getPais());
		panel.getRowFormatter().addStyleName(7, "textoPlano");
		
		panel.setText(8, 0, "CPA: " + empSelec2.getDireccion().getCpa());
		panel.getRowFormatter().addStyleName(8, "textoPlano");

		panel.setText(9, 0, "OBSERVACIONES: " + empSelec2.getObservaciones());
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

	public P_DatoEmpresa(ContactoDTO cont, String emp, String rubroEmp) {


		super(false);
		
		this.contSelec = cont;
		this.empresaContacto = emp;
		
		final String nombreEmpresa = emp;
		
		setStyleName("fondoPopup");
		panel = new FlexTable();
		
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		modificar = new Button(constante.modificar());
		modificar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				modificarContacto();
			}
		});

		eliminar = new Button(constante.eliminarContacto());
		eliminar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eliminarContacto(nombreEmpresa, contSelec.getNombre());
			}
		});

		pie = new Label();
		pie.setStyleName("labelTitulo");
		contactos = new Label("INFORMACIÓN DEL CONTACTO");
		contactos.setStyleName("labelTitulo");
		
		panel.setWidget(0, 0, contactos);
		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		panel.setText(1, 0, "EMPRESA: " + emp);
		panel.setText(1, 1, "RUBRO: " + rubroEmp);
		panel.getRowFormatter().addStyleName(1, "textoPlano");

		panel.setText(2, 0, "NOMBRE DE CONTACTO: " + contSelec.getNombre());
		panel.setText(2, 1, "CARGO: " + contSelec.getCargo());
		panel.getRowFormatter().addStyleName(2, "textoPlano");

		panel.setText(3, 0, "TELEFONO DE LA EMPRESA: " + contSelec.getTelefonoEmpresa());
		panel.setText(3, 1, "INTERNO: " + contSelec.getInternoEmpresa());
		panel.getRowFormatter().addStyleName(3, "textoPlano");

		panel.setText(4, 0, "TELEFONO PARTICULAR: " + contSelec.getTelefonoParticular());
		panel.getFlexCellFormatter().setColSpan(4, 0, 2);
		panel.getRowFormatter().addStyleName(4, "textoPlano");

		panel.setText(5, 0, "CELULAR: " + contSelec.getCelular());
		panel.getFlexCellFormatter().setColSpan(5, 0, 2);
		panel.getRowFormatter().addStyleName(5, "textoPlano");

		panel.setText(6, 0, "CORREO ELECTRÓNICO: " + contSelec.getMail());
		panel.getFlexCellFormatter().setColSpan(6, 0, 2);
		panel.getRowFormatter().addStyleName(6, "textoPlano");

		panel.setWidget(7, 0, pie);
		panel.getFlexCellFormatter().setColSpan(7, 0, 2);
		
		
		botones = new FlexTable();
		botones.setSize("100%", "100%");
		botones.setWidget(0, 0, modificar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);		
		botones.setWidget(0, 1, eliminar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 2, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);		
		
		
		panel.getFlexCellFormatter().setColSpan(8, 0, 2);
		panel.setWidget(8, 0, botones);
		
		
		
		
		
		setWidget(panel);
		panel.setSize("400px", "300px");

	}
		
	public P_DatoEmpresa(ContactoDTO cont, String emp, String rubroEmp, String bandera) {


		super(false);
		
		this.contSelec = cont;
		this.empresaContacto = emp;
		
		final String nombreEmpresa = emp;
		
		setStyleName("fondoPopup");
		panel = new FlexTable();
		
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		modificar = new Button(constante.modificar());
		modificar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				modificarContacto2();
			}
		});

		eliminar = new Button(constante.eliminarContacto());
		eliminar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eliminarContacto2(nombreEmpresa, contSelec.getNombre());
			}
		});

		pie = new Label();
		pie.setStyleName("labelTitulo");
		contactos = new Label("INFORMACIÓN DEL CONTACTO");
		contactos.setStyleName("labelTitulo");
		
		panel.setWidget(0, 0, contactos);
		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		panel.setText(1, 0, "EMPRESA: " + emp);
		panel.setText(1, 1, "RUBRO: " + rubroEmp);
		panel.getRowFormatter().addStyleName(1, "textoPlano");

		panel.setText(2, 0, "NOMBRE DE CONTACTO: " + contSelec.getNombre());
		panel.setText(2, 1, "CARGO: " + contSelec.getCargo());
		panel.getRowFormatter().addStyleName(2, "textoPlano");

		panel.setText(3, 0, "TELEFONO DE LA EMPRESA: " + contSelec.getTelefonoEmpresa());
		panel.setText(3, 1, "INTERNO: " + contSelec.getInternoEmpresa());
		panel.getRowFormatter().addStyleName(3, "textoPlano");

		panel.setText(4, 0, "TELEFONO PARTICULAR: " + contSelec.getTelefonoParticular());
		panel.getFlexCellFormatter().setColSpan(4, 0, 2);
		panel.getRowFormatter().addStyleName(4, "textoPlano");

		panel.setText(5, 0, "CELULAR: " + contSelec.getCelular());
		panel.getFlexCellFormatter().setColSpan(5, 0, 2);
		panel.getRowFormatter().addStyleName(5, "textoPlano");

		panel.setText(6, 0, "CORREO ELECTRÓNICO: " + contSelec.getMail());
		panel.getFlexCellFormatter().setColSpan(6, 0, 2);
		panel.getRowFormatter().addStyleName(6, "textoPlano");

		panel.setWidget(7, 0, pie);
		panel.getFlexCellFormatter().setColSpan(7, 0, 2);
		
		
		botones = new FlexTable();
		botones.setSize("100%", "100%");
		botones.setWidget(0, 0, modificar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);		
		botones.setWidget(0, 1, eliminar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 2, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);		
		
		
		panel.getFlexCellFormatter().setColSpan(8, 0, 2);
		panel.setWidget(8, 0, botones);
		
		
		
		
		
		setWidget(panel);
		panel.setSize("400px", "300px");

	}
	
	protected void modificarContacto() {
		
		
		ClienteDTO cliente = new ClienteDTO();
		cliente.setNombre(empresaContacto);
		contSelec.setCliente(cliente);
		P_AgregarContacto popUp = new P_AgregarContacto(contSelec);
		popUp.setGlassEnabled(true);
		popUp.center();
		popUp.show();
		salir();
		
		
		
	}

	protected void modificarContacto2() {
		
		
		ProveedorDTO proveedor = new ProveedorDTO();
		proveedor.setNombre(empresaContacto);
		contSelec.setProveedor(proveedor);
		P_AgregarContacto popUp = new P_AgregarContacto(contSelec,"");
		popUp.setGlassEnabled(true);
		popUp.center();
		popUp.show();
		salir();
		
		
		
	}

	protected void eliminarContacto2(String nombreEmpresa, String nombreContacto) {
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				
		comprasService.eliminarContacto(nombreEmpresa,nombreContacto, new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				Window.alert("El contacto ha sido eliminado");
				salir();
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR al eliminar el contacto");
			}
		});
	
	}

	protected void eliminarCliente() {
		
		VentasServiceAsync ventasService = GWT.create(VentasService.class);
		
		ventasService.eliminarEmpresa(empSelec.getNombre(), new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				Window.alert("El cliente ha sido eliminado de manera exitosa");
				salir();
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR al eliminar el cliente");
			}
		});
		
	}

	protected void eliminarProveedor() {
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		
		comprasService.eliminarEmpresa(empSelec.getNombre(), new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				Window.alert("El proveedor ha sido eliminado de manera exitosa");
				salir();
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR al eliminar el cliente");
			}
		});
		
	}
	
	protected void modificarCliente() {
		this.modificarCliente = true;
		this.hide();		
	}
	
	protected void modificarProveedor() {
		this.modificarProveedor = true;
		this.hide();		
	}

	protected void eliminarContacto(String nombreEmpresa, String nombreContacto) {
		
		VentasServiceAsync ventasService = GWT.create(VentasService.class);
				
		ventasService.eliminarContacto(nombreEmpresa,nombreContacto, new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				Window.alert("El contacto ha sido eliminado");
				salir();
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR al eliminar el contacto");
			}
		});
	
	}

	protected void salir() {
		this.hide();

	}
	
	public ClienteDTO getEmpleado(){
		return empSelec;
	}
	
	public boolean getModificarCliente(){
		return modificarCliente;
	}

	public boolean getModificarProveedor(){
		return modificarProveedor;
	}
}

