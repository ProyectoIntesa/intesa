package edu.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import edu.client.VentasService.VentasService;
import edu.client.VentasService.VentasServiceAsync;
import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.DireccionDTO;
import edu.shared.DTO.ProveedorDTO;

public class P_FormularioProveedor extends Composite {

	private static final int COL_NOMBRE = 0;
	private static final int COL_CARGO = 1;
	private static final int COL_TELEMPRESA = 2;
	private static final int COL_INTERNO = 3;
	private static final int COL_TELPARTICULAR = 4;
	private static final int COL_CELULAR = 5;
	private static final int COL_CORREO = 6;
	private static final int COL_ELIMINAR = 7;

	private ArrayList<String> elementos;
	private Constantes constante = GWT.create(Constantes.class);

	TabPanel padre;

	// etiquetas de organizacion
	private Label datosEmpresa;
	private Label datosDideccion;
	private Label datosContactos;
	private Label datosObse;
	private Label inferior;

	// formulario del cliente, contenedor
	private FlexTable formularioCliente;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;
	private FlexTable botones1;
	private FlexTable botones2;

	// etiquetas de los campos
	private Label nombreEmpresa;
	private Label nroCuit;
	private Label responsable;
	private Label rubro;
	private Label calle;
	private Label altura;
	private Label piso;
	private Label oficina;
	private Label cpa;
	private Label sugerenciaCPA;
	private Label localidad;
	private Label codigoPostal;
	private Label provincia;
	private Label pais;
	private Label telefono;
	private Label web;
	private Label fax;
	private Label email;
	private Label observacion;
	private Label tipoProveedor;

	// campos de texto
	private TextBox nombreEmpresaTb;
	private TextBox nroCuitTb;
	private TextBox responsableTb;
	private TextBox rubroTb;
	private TextBox calleTb;
	private TextBox alturaTb;
	private TextBox pisoTb;
	private TextBox oficinaTb;
	private TextBox cpaTb;
	private SuggestBox localidadSb;
	private TextBox codigoPostalTb;
	private SuggestBox provinciaSb;
	private SuggestBox paisSb;
	private TextBox telefonoTb;
	private TextBox webTb;
	private TextBox faxTb;
	private TextBox emailTb;
	private TextBox tipoProveedorTb;
	private TextArea observacionTb;

	private MultiWordSuggestOracle listaLocalidades;
	private MultiWordSuggestOracle listaProvincias;
	private MultiWordSuggestOracle listaPaises;
	
	// botones
	private Button btnAgregar;
	private Button btnCancelar;
	private Button btnNuevoContacto;
	
	ProveedorDTO proveedor;
	private String titulo;

	public P_FormularioProveedor(TabPanel padre) {

		this.padre = padre;
		elementos = new ArrayList<String>();
		datosEmpresa = new Label(constante.datosEmpresa());
		datosEmpresa.setStyleName("labelTitulo");
		datosDideccion = new Label(constante.domicilio());
		datosDideccion.setStyleName("labelTitulo");
		datosContactos = new Label(constante.constactos());
		datosContactos.setStyleName("labelTitulo");
		datosObse = new Label("");
		datosObse.setStyleName("labelTitulo");
		inferior = new Label("");
		inferior.setStyleName("labelTitulo");

		nombreEmpresa = new Label(constante.empresaAsterisco());
		nombreEmpresa.setStyleName("gwt-LabelFormulario");
		nroCuit = new Label(constante.cuitAsterisco());
		nroCuit.setStyleName("gwt-LabelFormulario");
		responsable = new Label(constante.responsableAsterisco());
		responsable.setStyleName("gwt-LabelFormulario");
		rubro = new Label(constante.rubro());
		rubro.setStyleName("gwt-LabelFormulario");
		calle = new Label(constante.calleAsterisco());
		calle.setStyleName("gwt-LabelFormulario");
		altura = new Label(constante.alturaAsterisco());
		altura.setStyleName("gwt-LabelFormulario");
		piso = new Label(constante.piso());
		piso.setStyleName("gwt-LabelFormulario");
		oficina = new Label(constante.oficina());
		oficina.setStyleName("gwt-LabelFormulario");
		cpa = new Label(constante.cpa());
		cpa.setStyleName("gwt-LabelFormulario");
		localidad = new Label(constante.localidadAsterisco());
		localidad.setStyleName("gwt-LabelFormulario");
		codigoPostal = new Label(constante.codigoPostal());
		codigoPostal.setStyleName("gwt-LabelFormulario");
		provincia = new Label(constante.provinciaAsterisco());
		provincia.setStyleName("gwt-LabelFormulario");
		pais = new Label(constante.paisAsterisco());
		pais.setStyleName("gwt-LabelFormulario");
		telefono = new Label(constante.telefono());
		telefono.setStyleName("gwt-LabelFormulario");
		web = new Label(constante.paginaWeb());
		web.setStyleName("gwt-LabelFormulario");
		fax = new Label(constante.fax());
		fax.setStyleName("gwt-LabelFormulario");
		email = new Label(constante.eMail());
		email.setStyleName("gwt-LabelFormulario");
		sugerenciaCPA = new Label("http://www.correoargentino.com.ar/cpa");
		sugerenciaCPA.setStyleName("gwt-LabelLink");
		observacion = new Label(constante.observaciones());
		observacion.setStyleName("gwt-LabelFormulario");
		tipoProveedor = new Label(constante.tipoProveedorAsterisco());
		tipoProveedor.setStyleName("gwt-LabelFormulario");

		nombreEmpresaTb = new TextBox();
		nroCuitTb = new TextBox();
		responsableTb = new TextBox();
		rubroTb = new TextBox();
		calleTb = new TextBox();
		alturaTb = new TextBox();
		pisoTb = new TextBox();
		pisoTb.setStyleName("textoCorto");
		oficinaTb = new TextBox();
		cpaTb = new TextBox();
		codigoPostalTb = new TextBox();
		telefonoTb = new TextBox();
		webTb = new TextBox();
		faxTb = new TextBox();
		emailTb = new TextBox();
		tipoProveedorTb = new TextBox();
		observacionTb = new TextArea();
		observacionTb.setDirectionEstimator(false);
		observacionTb.setWidth("100%");

		
		listaLocalidades = new MultiWordSuggestOracle();
		listaProvincias = new MultiWordSuggestOracle();
		listaPaises = new MultiWordSuggestOracle();
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.getNombresPaises(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaPaises(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
			
		paisSb = new SuggestBox(listaPaises);
		
		provinciaSb = new SuggestBox(listaProvincias);
		provinciaSb.addClickListener(new ClickListener() {
						
			@Override
			public void onClick(Widget sender) {
				
				final String pais = paisSb.getText();
				
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.getNombresProvincias(pais, new AsyncCallback<List<String>>() {
					@Override
					public void onSuccess(List<String> result) {
						cargarSugerenciaProvincias(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");
					}
				});
			}
		});

		localidadSb = new SuggestBox(listaLocalidades);
		localidadSb.addClickListener(new ClickListener() {
			
			@Override
			public void onClick(Widget sender) {
				
				final String prov = provinciaSb.getText();
				
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.getNombresLocalidades(prov, new AsyncCallback<List<String>>() {
					@Override
					public void onSuccess(List<String> result) {
						cargarSugerenciaLocalidades(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");
					}
				});
			}
		});
		
		
		
		btnNuevoContacto = new Button(constante.agregarContacto());
		btnNuevoContacto.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarContacto(event);
			}
		});

		btnAgregar = new Button(constante.agregar());
		btnAgregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarProveedor(event);
			}
		});

		btnCancelar = new Button(constante.cancelar());
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancela(event);
			}
		});

		botones1 = new FlexTable();
		botones1.setWidget(0, 0, btnNuevoContacto);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		botones2 = new FlexTable();
		botones2.setWidget(0, 0, btnAgregar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones2.setWidget(0, 1, btnCancelar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("200px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		formularioCliente = new FlexTable();
		formularioCliente.setStyleName("formatoFormulario");
		formularioCliente.setHeight("637px");

		formularioCliente.setWidget(0, 0, datosEmpresa);
		formularioCliente.getFlexCellFormatter().setColSpan(0, 0, 10);

		formularioCliente.setWidget(1, 0, nombreEmpresa);
		formularioCliente.setWidget(1, 1, nombreEmpresaTb);
		formularioCliente.setWidget(1, 2, nroCuit);
		formularioCliente.setWidget(1, 3, nroCuitTb);
		formularioCliente.setWidget(1, 4, responsable);
		formularioCliente.setWidget(1, 5, responsableTb);
		formularioCliente.setWidget(1, 6, rubro);
		formularioCliente.setWidget(1, 7, rubroTb);
		formularioCliente.setWidget(1, 8, tipoProveedor);
		formularioCliente.getFlexCellFormatter().setColSpan(1, 8, 10);		
		
		formularioCliente.setWidget(2, 0, telefono);
		formularioCliente.setWidget(2, 1, telefonoTb);
		formularioCliente.setWidget(2, 2, fax);
		formularioCliente.setWidget(2, 3, faxTb);
		formularioCliente.setWidget(2, 4, email);
		formularioCliente.setWidget(2, 5, emailTb);
		formularioCliente.setWidget(2, 6, web);
		formularioCliente.setWidget(2, 7, webTb);
		formularioCliente.setWidget(2, 8, tipoProveedorTb);
		formularioCliente.getFlexCellFormatter().setColSpan(2, 8, 10);

		formularioCliente.setWidget(3, 0, datosDideccion);
		formularioCliente.getFlexCellFormatter().setColSpan(3, 0, 10);

		formularioCliente.setWidget(4, 0, pais);
		formularioCliente.setWidget(4, 1, paisSb);
		formularioCliente.setWidget(4, 2, provincia);
		formularioCliente.setWidget(4, 3, provinciaSb);
		formularioCliente.setWidget(4, 4, localidad);
		formularioCliente.setWidget(4, 5, localidadSb);
		formularioCliente.setWidget(4, 6, codigoPostal);
		formularioCliente.setWidget(4, 7, codigoPostalTb);
		formularioCliente.setWidget(4, 8, sugerenciaCPA);
		formularioCliente.getFlexCellFormatter().setColSpan(4, 8, 10);
		formularioCliente.setWidget(5, 0, calle);
		formularioCliente.setWidget(5, 1, calleTb);
		formularioCliente.setWidget(5, 2, altura);
		formularioCliente.setWidget(5, 3, alturaTb);
		formularioCliente.setWidget(5, 4, piso);
		formularioCliente.setWidget(5, 5, pisoTb);
		formularioCliente.setWidget(5, 6, oficina);
		formularioCliente.setWidget(5, 7, oficinaTb);
		formularioCliente.setWidget(5, 8, cpa);
		formularioCliente.setWidget(5, 9, cpaTb);

		formularioCliente.setWidget(7, 0, datosContactos);
		formularioCliente.getFlexCellFormatter().setColSpan(7, 0, 10);

		formularioCliente.setWidget(8, 0, botones1);
		formularioCliente.getFlexCellFormatter().setColSpan(8, 0, 10);
		formularioCliente.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_CENTER);

		formularioCliente.setWidget(9, 0, contenedorTabla);
		formularioCliente.getFlexCellFormatter().setColSpan(9, 0, 10);

		formularioCliente.setWidget(10, 0, datosObse);
		formularioCliente.getFlexCellFormatter().setColSpan(10, 0, 10);

		formularioCliente.setWidget(11, 0, observacion);
		formularioCliente.setWidget(11, 1, observacionTb);
		formularioCliente.getFlexCellFormatter().setColSpan(11, 1, 10);

		formularioCliente.setWidget(12, 0, inferior);
		formularioCliente.getFlexCellFormatter().setColSpan(12, 0, 10);

		formularioCliente.setWidget(13, 0, botones2);
		formularioCliente.getFlexCellFormatter().setColSpan(13, 0, 10);
		formularioCliente.getCellFormatter().setHorizontalAlignment(13, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		tablaElemento.setText(0, COL_NOMBRE, constante.nombre());
		tablaElemento.getCellFormatter().setWidth(0, COL_NOMBRE, "14%");
		tablaElemento.setText(0, COL_CARGO, constante.cargo());
		tablaElemento.getCellFormatter().setWidth(0, COL_CARGO, "14%");
		tablaElemento.setText(0, COL_TELEMPRESA, constante.telefono());
		tablaElemento.getCellFormatter().setWidth(0, COL_TELEMPRESA, "14%");
		tablaElemento.setText(0, COL_INTERNO, constante.interno());
		tablaElemento.getCellFormatter().setWidth(0, COL_INTERNO, "14%");
		tablaElemento.setText(0, COL_TELPARTICULAR, constante.telefonoParticular());
		tablaElemento.getCellFormatter().setWidth(0, COL_TELPARTICULAR, "14%");
		tablaElemento.setText(0, COL_CELULAR, constante.celular());
		tablaElemento.getCellFormatter().setWidth(0, COL_CELULAR, "14%");
		tablaElemento.setText(0, COL_CORREO, constante.eMail());
		tablaElemento.getCellFormatter().setWidth(0, COL_CORREO, "14%");
		tablaElemento.setText(0, COL_ELIMINAR, "");
		tablaElemento.getCellFormatter().setWidth(0, COL_ELIMINAR, "2%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

		sugerenciaCPA.setTitle(constante.cpaInfo());
		sugerenciaCPA.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.open("http://www.correoargentino.com.ar/cpa", null, null);
			}
		});

		initWidget(formularioCliente);

	}
	
	public P_FormularioProveedor(TabPanel padre, ProveedorDTO proveedorSeleccionado, String titulo) {

		this.padre = padre;
		this.proveedor = proveedorSeleccionado;
		this.titulo = titulo;
		
		listaLocalidades = new MultiWordSuggestOracle();
		listaProvincias = new MultiWordSuggestOracle();
		listaPaises = new MultiWordSuggestOracle();
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.getNombresPaises(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaPaises(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
		elementos = new ArrayList<String>();
		datosEmpresa = new Label(constante.datosEmpresa());
		datosEmpresa.setStyleName("labelTitulo");
		datosDideccion = new Label(constante.domicilio());
		datosDideccion.setStyleName("labelTitulo");
		datosContactos = new Label(constante.constactos());
		datosContactos.setStyleName("labelTitulo");
		datosObse = new Label("");
		datosObse.setStyleName("labelTitulo");
		inferior = new Label("");
		inferior.setStyleName("labelTitulo");

		nombreEmpresa = new Label(constante.empresa());
		nombreEmpresa.setStyleName("gwt-LabelFormulario");
		nroCuit = new Label(constante.cuitAsterisco());
		nroCuit.setStyleName("gwt-LabelFormulario");
		responsable = new Label(constante.responsableAsterisco());
		responsable.setStyleName("gwt-LabelFormulario");
		rubro = new Label(constante.rubro());
		rubro.setStyleName("gwt-LabelFormulario");
		calle = new Label(constante.calleAsterisco());
		calle.setStyleName("gwt-LabelFormulario");
		altura = new Label(constante.alturaAsterisco());
		altura.setStyleName("gwt-LabelFormulario");
		piso = new Label(constante.piso());
		piso.setStyleName("gwt-LabelFormulario");
		oficina = new Label(constante.oficina());
		oficina.setStyleName("gwt-LabelFormulario");
		cpa = new Label(constante.cpa());
		cpa.setStyleName("gwt-LabelFormulario");
		localidad = new Label(constante.localidadAsterisco());
		localidad.setStyleName("gwt-LabelFormulario");
		codigoPostal = new Label(constante.codigoPostal());
		codigoPostal.setStyleName("gwt-LabelFormulario");
		provincia = new Label(constante.provinciaAsterisco());
		provincia.setStyleName("gwt-LabelFormulario");
		pais = new Label(constante.paisAsterisco());
		pais.setStyleName("gwt-LabelFormulario");
		telefono = new Label(constante.telefono());
		telefono.setStyleName("gwt-LabelFormulario");
		web = new Label(constante.paginaWeb());
		web.setStyleName("gwt-LabelFormulario");
		fax = new Label(constante.fax());
		fax.setStyleName("gwt-LabelFormulario");
		email = new Label(constante.eMail());
		email.setStyleName("gwt-LabelFormulario");
		sugerenciaCPA = new Label("http://www.correoargentino.com.ar/cpa");
		sugerenciaCPA.setStyleName("gwt-LabelLink");
		observacion = new Label(constante.observaciones());
		observacion.setStyleName("gwt-LabelFormulario");
		tipoProveedor = new Label(constante.tipoProveedorAsterisco());
		tipoProveedor.setStyleName("gwt-LabelFormulario");

		nombreEmpresaTb = new TextBox();
		nombreEmpresaTb.setText(this.proveedor.getNombre());
		nombreEmpresaTb.setEnabled(false);
		nroCuitTb = new TextBox();
		nroCuitTb.setText(this.proveedor.getCuit());
		responsableTb = new TextBox();
		responsableTb.setText(this.proveedor.getResponsable());
		rubroTb = new TextBox();
		rubroTb.setText(this.proveedor.getRubro());
		calleTb = new TextBox();
		calleTb.setText(this.proveedor.getDireccion().getCalle());
		alturaTb = new TextBox();
		alturaTb.setText(this.proveedor.getDireccion().getAltura());
		pisoTb = new TextBox();
		pisoTb.setStyleName("textoCorto");
		pisoTb.setText(this.proveedor.getDireccion().getPiso());
		oficinaTb = new TextBox();
		oficinaTb.setText(this.proveedor.getDireccion().getOficina());
		cpaTb = new TextBox();
		cpaTb.setText(this.proveedor.getDireccion().getCpa());
		localidadSb = new SuggestBox(listaLocalidades);
		localidadSb.setText(this.proveedor.getDireccion().getLocalidad());
		codigoPostalTb = new TextBox();
		codigoPostalTb.setText(this.proveedor.getDireccion().getCodigoLocalidad());
		provinciaSb = new SuggestBox(listaProvincias);
		provinciaSb.setText(this.proveedor.getDireccion().getProvincia());
		paisSb = new SuggestBox(listaPaises);
		paisSb.setText(this.proveedor.getDireccion().getPais());
		telefonoTb = new TextBox();
		telefonoTb.setText(this.proveedor.getTelefono());
		webTb = new TextBox();
		webTb.setText(this.proveedor.getPaginaWeb());
		faxTb = new TextBox();
		faxTb.setText(this.proveedor.getFax());
		emailTb = new TextBox();
		emailTb.setText(this.proveedor.getMail());
		observacionTb = new TextArea();
		observacionTb.setDirectionEstimator(false);
		observacionTb.setWidth("100%");
		observacionTb.setText(this.proveedor.getObservaciones());
		tipoProveedorTb = new TextBox();
		tipoProveedorTb.setText(this.proveedor.getTipoProveedor());

		provinciaSb.addClickListener(new ClickListener() {
			
			@Override
			public void onClick(Widget sender) {
				
				final String pais = paisSb.getText();
				
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.getNombresProvincias(pais, new AsyncCallback<List<String>>() {
					@Override
					public void onSuccess(List<String> result) {
						cargarSugerenciaProvincias(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");
					}
				});
			}
		});

		localidadSb.addClickListener(new ClickListener() {
			
			@Override
			public void onClick(Widget sender) {
				
				final String prov = provinciaSb.getText();
				
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.getNombresLocalidades(prov, new AsyncCallback<List<String>>() {
					@Override
					public void onSuccess(List<String> result) {
						cargarSugerenciaLocalidades(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");
					}
				});
			}
		});
		
		btnNuevoContacto = new Button(constante.agregarContacto());
		btnNuevoContacto.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarContacto(event);
			}
		});

		btnAgregar = new Button(constante.guardar());
		btnAgregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				guardarCambiosProveedor(event);
			}
		});

		btnCancelar = new Button(constante.cancelar());
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelaModificacion(event);
			}
		});

		botones1 = new FlexTable();
		botones1.setWidget(0, 0, btnNuevoContacto);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		botones2 = new FlexTable();
		botones2.setWidget(0, 0, btnAgregar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones2.setWidget(0, 1, btnCancelar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("200px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");
			

		formularioCliente = new FlexTable();
		formularioCliente.setStyleName("formatoFormulario");
		formularioCliente.setHeight("637px");

		formularioCliente.setWidget(0, 0, datosEmpresa);
		formularioCliente.getFlexCellFormatter().setColSpan(0, 0, 10);

		formularioCliente.setWidget(1, 0, nombreEmpresa);
		formularioCliente.setWidget(1, 1, nombreEmpresaTb);
		formularioCliente.setWidget(1, 2, nroCuit);
		formularioCliente.setWidget(1, 3, nroCuitTb);
		formularioCliente.setWidget(1, 4, responsable);
		formularioCliente.setWidget(1, 5, responsableTb);
		formularioCliente.setWidget(1, 6, rubro);
		formularioCliente.setWidget(1, 7, rubroTb);
		formularioCliente.setWidget(1, 8, tipoProveedor);
		formularioCliente.getFlexCellFormatter().setColSpan(1, 8, 10);		

		formularioCliente.setWidget(2, 0, telefono);
		formularioCliente.setWidget(2, 1, telefonoTb);
		formularioCliente.setWidget(2, 2, fax);
		formularioCliente.setWidget(2, 3, faxTb);
		formularioCliente.setWidget(2, 4, email);
		formularioCliente.setWidget(2, 5, emailTb);
		formularioCliente.setWidget(2, 6, web);
		formularioCliente.setWidget(2, 7, webTb);
		formularioCliente.setWidget(2, 8, tipoProveedorTb);
		formularioCliente.getFlexCellFormatter().setColSpan(2, 8, 10);

		formularioCliente.setWidget(3, 0, datosDideccion);
		formularioCliente.getFlexCellFormatter().setColSpan(3, 0, 10);

		formularioCliente.setWidget(4, 0, pais);
		formularioCliente.setWidget(4, 1, paisSb);
		formularioCliente.setWidget(4, 2, provincia);
		formularioCliente.setWidget(4, 3, provinciaSb);
		formularioCliente.setWidget(4, 4, localidad);
		formularioCliente.setWidget(4, 5, localidadSb);
		formularioCliente.setWidget(4, 6, codigoPostal);
		formularioCliente.setWidget(4, 7, codigoPostalTb);
		formularioCliente.setWidget(4, 8, sugerenciaCPA);
		formularioCliente.getFlexCellFormatter().setColSpan(4, 8, 10);
		formularioCliente.setWidget(5, 0, calle);
		formularioCliente.setWidget(5, 1, calleTb);
		formularioCliente.setWidget(5, 2, altura);
		formularioCliente.setWidget(5, 3, alturaTb);
		formularioCliente.setWidget(5, 4, piso);
		formularioCliente.setWidget(5, 5, pisoTb);
		formularioCliente.setWidget(5, 6, oficina);
		formularioCliente.setWidget(5, 7, oficinaTb);
		formularioCliente.setWidget(5, 8, cpa);
		formularioCliente.setWidget(5, 9, cpaTb);

		formularioCliente.setWidget(7, 0, datosContactos);
		formularioCliente.getFlexCellFormatter().setColSpan(7, 0, 10);

		formularioCliente.setWidget(8, 0, botones1);
		formularioCliente.getFlexCellFormatter().setColSpan(8, 0, 10);
		formularioCliente.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_CENTER);

		formularioCliente.setWidget(9, 0, contenedorTabla);
		formularioCliente.getFlexCellFormatter().setColSpan(9, 0, 10);

		formularioCliente.setWidget(10, 0, datosObse);
		formularioCliente.getFlexCellFormatter().setColSpan(10, 0, 10);

		formularioCliente.setWidget(11, 0, observacion);
		formularioCliente.setWidget(11, 1, observacionTb);
		formularioCliente.getFlexCellFormatter().setColSpan(11, 1, 10);

		formularioCliente.setWidget(12, 0, inferior);
		formularioCliente.getFlexCellFormatter().setColSpan(12, 0, 10);

		formularioCliente.setWidget(13, 0, botones2);
		formularioCliente.getFlexCellFormatter().setColSpan(13, 0, 10);
		formularioCliente.getCellFormatter().setHorizontalAlignment(13, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		tablaElemento.setText(0, COL_NOMBRE, constante.nombre());
		tablaElemento.getCellFormatter().setWidth(0, COL_NOMBRE, "14%");
		tablaElemento.setText(0, COL_CARGO, constante.cargo());
		tablaElemento.getCellFormatter().setWidth(0, COL_CARGO, "14%");
		tablaElemento.setText(0, COL_TELEMPRESA, constante.telefono());
		tablaElemento.getCellFormatter().setWidth(0, COL_TELEMPRESA, "14%");
		tablaElemento.setText(0, COL_INTERNO, constante.interno());
		tablaElemento.getCellFormatter().setWidth(0, COL_INTERNO, "14%");
		tablaElemento.setText(0, COL_TELPARTICULAR, constante.telefonoParticular());
		tablaElemento.getCellFormatter().setWidth(0, COL_TELPARTICULAR, "14%");
		tablaElemento.setText(0, COL_CELULAR, constante.celular());
		tablaElemento.getCellFormatter().setWidth(0, COL_CELULAR, "14%");
		tablaElemento.setText(0, COL_CORREO, constante.eMail());
		tablaElemento.getCellFormatter().setWidth(0, COL_CORREO, "14%");
		tablaElemento.setText(0, COL_ELIMINAR, "");
		tablaElemento.getCellFormatter().setWidth(0, COL_ELIMINAR, "2%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");


		
		
		for (int i = 0; i < proveedorSeleccionado.getContacto().size(); i++){
			
			final String nombreContacto = proveedorSeleccionado.getContacto().get(i).getNombre();
			
			Label eliminar = new Label("");
			eliminar.setSize("16px", "16px");
			eliminar.setStyleName("labelBorrar");
			eliminar.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					boolean confirm = Window.confirm("Está seguro de que desea eliminar el contacto?");
					if(confirm == true)
						eliminarContacto(nombreContacto);
				}
			});
			
			tablaElemento.setWidget(i+1, COL_NOMBRE, new Label(proveedorSeleccionado.getContacto().get(i).getNombre()));
			tablaElemento.setWidget(i+1, COL_CARGO, new Label(proveedorSeleccionado.getContacto().get(i).getCargo()));
			tablaElemento.setWidget(i+1, COL_TELEMPRESA, new Label(proveedorSeleccionado.getContacto().get(i).getTelefonoEmpresa()));
			tablaElemento.setWidget(i+1, COL_INTERNO, new Label(proveedorSeleccionado.getContacto().get(i).getInternoEmpresa()));
			tablaElemento.setWidget(i+1, COL_TELPARTICULAR, new Label(proveedorSeleccionado.getContacto().get(i).getTelefonoParticular()));
			tablaElemento.setWidget(i+1, COL_CELULAR, new Label(proveedorSeleccionado.getContacto().get(i).getCelular()));
			tablaElemento.setWidget(i+1, COL_CORREO, new Label(proveedorSeleccionado.getContacto().get(i).getMail()));
			tablaElemento.setWidget(i+1, COL_ELIMINAR, eliminar);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_ELIMINAR, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.getRowFormatter().setStyleName(i+1, "tablaRenglon");
			
			
			elementos.add(nombreContacto);
			
		}
		
		
		sugerenciaCPA.setTitle(constante.cpaInfo());
		sugerenciaCPA.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.open("http://www.correoargentino.com.ar/cpa", null, null);
			}
		});

		initWidget(formularioCliente);

	}

	public void agregarProveedor(ClickEvent event) {

		Validaciones validar = new Validaciones();
		
		boolean vNombreEmpresa = validar.textBoxVacio(this.nombreEmpresaTb.getText());
		boolean vCuit = validar.textBoxVacio(this.nroCuitTb.getText());
		boolean vResponsable = validar.textBoxVacio(this.responsableTb.getText());
		boolean vTipoProveedor = validar.textBoxVacio(this.tipoProveedorTb.getText());
		boolean vPais = validar.textBoxVacio(this.paisSb.getText());
		boolean vProv = validar.textBoxVacio(this.provinciaSb.getText());
		boolean vLocalidad = validar.textBoxVacio(this.localidadSb.getText());
		boolean vCalle = validar.textBoxVacio(this.calleTb.getText());
		boolean vAltura = validar.textBoxVacio(this.alturaTb.getText());
		
		if(!vNombreEmpresa && !vCuit && !vResponsable && !vTipoProveedor && !vPais && !vProv && !vLocalidad && !vCalle && !vAltura){
			
			
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.getExistenciaProveedor(this.nombreEmpresaTb.getText(), new AsyncCallback<Boolean>() {
				
				@Override
				public void onSuccess(Boolean result) {
					
					if(result == true)
						Window.alert("Ya existe un cliente con el nombre ingresado");
					else{
						registrarProveedor();
					}
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
					
				}
				
				
			});
			
			

			
			
		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");			
		}
	}
		
	public void registrarProveedor(){
		DireccionDTO direccion = new DireccionDTO();
		direccion.setPais(this.paisSb.getText());
		direccion.setProvincia(this.provinciaSb.getText());
		direccion.setLocalidad(this.localidadSb.getText());
		direccion.setCodigoLocalidad(this.codigoPostalTb.getText());
		direccion.setCalle(this.calleTb.getText());
		direccion.setAltura(this.alturaTb.getText());
		direccion.setPiso(this.pisoTb.getText());
		direccion.setOficina(this.oficinaTb.getText());
		direccion.setCpa(this.cpaTb.getText());

		ProveedorDTO proveedor = new ProveedorDTO();
		proveedor.setNombre(this.nombreEmpresaTb.getText());
		proveedor.setCuit(this.nroCuitTb.getText());
		proveedor.setResponsable(this.responsableTb.getText());
		proveedor.setRubro(this.rubroTb.getText());
		proveedor.setTelefono(this.telefonoTb.getText());
		proveedor.setFax(this.faxTb.getText());
		proveedor.setMail(this.emailTb.getText());
		proveedor.setPaginaWeb(this.webTb.getText());
		proveedor.setDireccion(direccion);
		proveedor.setObservaciones(this.observacionTb.getText());
		proveedor.setTipoProveedor(this.tipoProveedorTb.getText());

		if (tablaElemento.getRowCount() > 1) {

			for (int i = 1; i < tablaElemento.getRowCount(); i++) {

				ContactoDTO contacto = new ContactoDTO();
				contacto.setNombre(((Label) tablaElemento.getWidget(i, COL_NOMBRE)).getText());
				contacto.setCargo(((Label) tablaElemento.getWidget(i, COL_CARGO)).getText());
				contacto.setTelefonoEmpresa(((Label) tablaElemento.getWidget(i, COL_TELEMPRESA)).getText());
				contacto.setInternoEmpresa(((Label) tablaElemento.getWidget(i, COL_INTERNO)).getText());
				contacto.setTelefonoParticular(((Label) tablaElemento.getWidget(i, COL_TELPARTICULAR)).getText());
				contacto.setCelular(((Label) tablaElemento.getWidget(i, COL_CELULAR)).getText());
				contacto.setMail(((Label) tablaElemento.getWidget(i, COL_CORREO)).getText());

				proveedor.getContacto().add(contacto);
			}

		}

		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.registrarNuevoProveedor(proveedor, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					Window.alert("El proveedor ha sido registrado de manera exitosa");
					padre.remove(numeroElemento(constante.nuevoProveedor()));
				} else
					Window.alert("No se ha podido registrar el proveedor");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");

			}
		});
		
	}
		
	public void registrarCambiosProveedor(){
		
		
		
		DireccionDTO direccion = new DireccionDTO();
		direccion.setPais(this.paisSb.getText());
		direccion.setProvincia(this.provinciaSb.getText());
		direccion.setLocalidad(this.localidadSb.getText());
		direccion.setCodigoLocalidad(this.codigoPostalTb.getText());
		direccion.setCalle(this.calleTb.getText());
		direccion.setAltura(this.alturaTb.getText());
		direccion.setPiso(this.pisoTb.getText());
		direccion.setOficina(this.oficinaTb.getText());
		direccion.setCpa(this.cpaTb.getText());

		ProveedorDTO proveedorModificado = new ProveedorDTO();
		proveedorModificado.setNombre(this.nombreEmpresaTb.getText());
		proveedorModificado.setCuit(this.nroCuitTb.getText());
		proveedorModificado.setResponsable(this.responsableTb.getText());
		proveedorModificado.setRubro(this.rubroTb.getText());
		proveedorModificado.setTelefono(this.telefonoTb.getText());
		proveedorModificado.setFax(this.faxTb.getText());
		proveedorModificado.setMail(this.emailTb.getText());
		proveedorModificado.setPaginaWeb(this.webTb.getText());
		proveedorModificado.setDireccion(direccion);
		proveedorModificado.setObservaciones(this.observacionTb.getText());
		proveedorModificado.setTipoProveedor(this.tipoProveedorTb.getText());

		if (tablaElemento.getRowCount() > 1) {

			for (int i = 1; i < tablaElemento.getRowCount(); i++) {

				ContactoDTO contacto = new ContactoDTO();
				contacto.setNombre(((Label) tablaElemento.getWidget(i, COL_NOMBRE)).getText());
				contacto.setCargo(((Label) tablaElemento.getWidget(i, COL_CARGO)).getText());
				contacto.setTelefonoEmpresa(((Label) tablaElemento.getWidget(i, COL_TELEMPRESA)).getText());
				contacto.setInternoEmpresa(((Label) tablaElemento.getWidget(i, COL_INTERNO)).getText());
				contacto.setTelefonoParticular(((Label) tablaElemento.getWidget(i, COL_TELPARTICULAR)).getText());
				contacto.setCelular(((Label) tablaElemento.getWidget(i, COL_CELULAR)).getText());
				contacto.setMail(((Label) tablaElemento.getWidget(i, COL_CORREO)).getText());

				proveedorModificado.getContacto().add(contacto);
			}

		}

		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.registrarCambioProveedor(proveedorModificado, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					Window.alert("El proveedor ha sido modificado de manera exitosa");
					padre.remove(numeroElemento(constante.modificarProveedor()));
				} else
					Window.alert("No se ha podido modificar el proveedor");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");

			}
		});
		
	}
	
	public void guardarCambiosProveedor(ClickEvent event) {
		
		Validaciones validar = new Validaciones();
		
		boolean vCuit = validar.textBoxVacio(this.nroCuitTb.getText());
		boolean vResponsable = validar.textBoxVacio(this.responsableTb.getText());
		boolean vTipoProveedor = validar.textBoxVacio(this.tipoProveedorTb.getText());
		boolean vPais = validar.textBoxVacio(this.paisSb.getText());
		boolean vProv = validar.textBoxVacio(this.provinciaSb.getText());
		boolean vLocalidad = validar.textBoxVacio(this.localidadSb.getText());
		boolean vCalle = validar.textBoxVacio(this.calleTb.getText());
		boolean vAltura = validar.textBoxVacio(this.alturaTb.getText());
		
		if(!vCuit && !vResponsable && !vTipoProveedor && !vPais && !vProv && !vLocalidad && !vCalle && !vAltura){
			
			registrarCambiosProveedor();
			
			
//			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
//			comprasService.getExistenciaProveedor(this.nombreEmpresaTb.getText(), new AsyncCallback<Boolean>() {
//				
//				@Override
//				public void onSuccess(Boolean result) {
//					
//					if(result == true)
//						Window.alert("Ya existe un cliente con el nombre ingresado");
//					else{
//						registrarCambiosProveedor();
//					}
//					
//				}
//				
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("ERROR EN EL SERVICIO");
//					
//				}
//				
//				
//			});

		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}
		
		

	}
	
	public void capturarDatos(String nombre, String cargo, String telempresa, String interno, String telParticular, String celular, String correo) {

		final String nombreContacto = nombre;
		int fila = tablaElemento.getRowCount();

		Label eliminar = new Label("");
		eliminar.setSize("16px", "16px");
		eliminar.setStyleName("labelBorrar");
		eliminar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				boolean confirm = Window.confirm("Está seguro de que desea eliminar el contacto?");
				if(confirm == true)
					eliminar(nombreContacto);
			}
		});

		tablaElemento.setWidget(fila, COL_NOMBRE, new Label(nombreContacto));
		tablaElemento.setWidget(fila, COL_CARGO, new Label(cargo));
		tablaElemento.setWidget(fila, COL_TELEMPRESA, new Label(telempresa));
		tablaElemento.setWidget(fila, COL_INTERNO, new Label(interno));
		tablaElemento.setWidget(fila, COL_TELPARTICULAR, new Label(telParticular));
		tablaElemento.setWidget(fila, COL_CELULAR, new Label(celular));
		tablaElemento.setWidget(fila, COL_CORREO, new Label(correo));
		tablaElemento.setWidget(fila, COL_ELIMINAR, eliminar);
		tablaElemento.getFlexCellFormatter().setHorizontalAlignment(fila, COL_ELIMINAR, HasHorizontalAlignment.ALIGN_CENTER);

		tablaElemento.getRowFormatter().setStyleName(fila, "tablaRenglon");

		elementos.add(nombreContacto);
	}

	private void eliminar(String unContacto) {
		int fila = elementos.indexOf(unContacto);
		elementos.remove(fila);
		tablaElemento.removeRow(fila + 1);
	}

	private void eliminarContacto(String nombreContacto){
		
		int fila = elementos.indexOf(nombreContacto);
		elementos.remove(fila);
		tablaElemento.removeRow(fila + 1);
		
		proveedor.getContacto().remove(fila);
		
	
	}
	
	protected void agregarContacto(ClickEvent event) {

		P_AgregarContacto nuevo = new P_AgregarContacto(this);
		nuevo.setGlassEnabled(true);
		nuevo.center();
		nuevo.show();
	}

	public void cancela(ClickEvent event) {
		padre.remove(numeroElemento(constante.nuevoProveedor()));

	}
	
	public void cancelaModificacion(ClickEvent event) {
		padre.remove(numeroElemento(constante.modificarProveedor()));

	}

	private int numeroElemento(String titulo) {

		int elemento = -1;
		int contador = 0;

		while (elemento == -1 && contador < padre.getWidgetCount()) {

			if (padre.getWidget(contador).getTitle().compareTo(titulo) == 0)
				elemento = contador;
			else
				contador++;
		}

		return elemento;
	}

	protected void cargarSugerenciaPaises(List<String> result) {
		for (String sugerencia : result) {
			listaPaises.add(sugerencia);
		}
		
	}
	
	protected void cargarSugerenciaProvincias(List<String> result) {
		listaProvincias.clear();
		for (String sugerencia : result) {
			listaProvincias.add(sugerencia);
		}
		
	}
	
	protected void cargarSugerenciaLocalidades(List<String> result) {
		listaLocalidades.clear();
		for (String sugerencia : result) {
			listaLocalidades.add(sugerencia);
		}
		
	}
	
}

