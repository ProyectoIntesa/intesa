package edu.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import edu.client.VentasService.VentasService;
import edu.client.VentasService.VentasServiceAsync;
import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.DireccionDTO;

public class P_FormularioCliente extends Composite {

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
	private TextBox localidadTb;
	private TextBox codigoPostalTb;
	private TextBox provinciaTb;
	private TextBox paisTb;
	private TextBox telefonoTb;
	private TextBox webTb;
	private TextBox faxTb;
	private TextBox emailTb;
	private TextArea observacionTb;

	// botones
	private Button btnAgregar;
	private Button btnCancelar;
	private Button btnNuevoContacto;
	
	ClienteDTO cliente;
	private String titulo;

	public P_FormularioCliente(TabPanel padre) {

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

		nombreEmpresa = new Label(constante.empresa());
		nombreEmpresa.setStyleName("gwt-LabelFormulario");
		nroCuit = new Label(constante.cuit());
		nroCuit.setStyleName("gwt-LabelFormulario");
		responsable = new Label(constante.responsable());
		responsable.setStyleName("gwt-LabelFormulario");
		rubro = new Label(constante.rubro());
		rubro.setStyleName("gwt-LabelFormulario");
		calle = new Label(constante.calle());
		calle.setStyleName("gwt-LabelFormulario");
		altura = new Label(constante.altura());
		altura.setStyleName("gwt-LabelFormulario");
		piso = new Label(constante.piso());
		piso.setStyleName("gwt-LabelFormulario");
		oficina = new Label(constante.oficina());
		oficina.setStyleName("gwt-LabelFormulario");
		cpa = new Label(constante.cpa());
		cpa.setStyleName("gwt-LabelFormulario");
		localidad = new Label(constante.localidad());
		localidad.setStyleName("gwt-LabelFormulario");
		codigoPostal = new Label(constante.codigoPostal());
		codigoPostal.setStyleName("gwt-LabelFormulario");
		provincia = new Label(constante.provincia());
		provincia.setStyleName("gwt-LabelFormulario");
		pais = new Label(constante.pais());
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
		localidadTb = new TextBox();
		codigoPostalTb = new TextBox();
		provinciaTb = new TextBox();
		paisTb = new TextBox();
		telefonoTb = new TextBox();
		webTb = new TextBox();
		faxTb = new TextBox();
		emailTb = new TextBox();
		observacionTb = new TextArea();
		observacionTb.setDirectionEstimator(false);
		observacionTb.setWidth("100%");

		btnNuevoContacto = new Button(constante.agregarContacto());
		btnNuevoContacto.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarContacto(event);
			}
		});

		btnAgregar = new Button(constante.agregar());
		btnAgregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarCliente(event);
			}
		});

		btnCancelar = new Button(constante.cancelar());
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancela(event);
			}
		});

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

		formularioCliente.setWidget(2, 0, telefono);
		formularioCliente.setWidget(2, 1, telefonoTb);
		formularioCliente.setWidget(2, 2, fax);
		formularioCliente.setWidget(2, 3, faxTb);
		formularioCliente.setWidget(2, 4, email);
		formularioCliente.setWidget(2, 5, emailTb);
		formularioCliente.setWidget(2, 6, web);
		formularioCliente.setWidget(2, 7, webTb);

		formularioCliente.setWidget(3, 0, datosDideccion);
		formularioCliente.getFlexCellFormatter().setColSpan(3, 0, 10);

		formularioCliente.setWidget(4, 0, pais);
		formularioCliente.setWidget(4, 1, paisTb);
		formularioCliente.setWidget(4, 2, provincia);
		formularioCliente.setWidget(4, 3, provinciaTb);
		formularioCliente.setWidget(4, 4, localidad);
		formularioCliente.setWidget(4, 5, localidadTb);
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

		formularioCliente.setWidget(8, 4, btnNuevoContacto);

		formularioCliente.setWidget(9, 0, contenedorTabla);
		formularioCliente.getFlexCellFormatter().setColSpan(9, 0, 10);

		formularioCliente.setWidget(10, 0, datosObse);
		formularioCliente.getFlexCellFormatter().setColSpan(10, 0, 10);

		formularioCliente.setWidget(11, 0, observacion);
		formularioCliente.setWidget(11, 1, observacionTb);
		formularioCliente.getFlexCellFormatter().setColSpan(11, 1, 10);

		formularioCliente.setWidget(12, 0, inferior);
		formularioCliente.getFlexCellFormatter().setColSpan(12, 0, 10);

		formularioCliente.setWidget(13, 2, btnAgregar);

		formularioCliente.setWidget(13, 5, btnCancelar);

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
	
	public P_FormularioCliente(TabPanel padre, ClienteDTO clienteSeleccionado, String titulo) {

		this.padre = padre;
		this.cliente = clienteSeleccionado;
		this.titulo = titulo;
		
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
		nroCuit = new Label(constante.cuit());
		nroCuit.setStyleName("gwt-LabelFormulario");
		responsable = new Label(constante.responsable());
		responsable.setStyleName("gwt-LabelFormulario");
		rubro = new Label(constante.rubro());
		rubro.setStyleName("gwt-LabelFormulario");
		calle = new Label(constante.calle());
		calle.setStyleName("gwt-LabelFormulario");
		altura = new Label(constante.altura());
		altura.setStyleName("gwt-LabelFormulario");
		piso = new Label(constante.piso());
		piso.setStyleName("gwt-LabelFormulario");
		oficina = new Label(constante.oficina());
		oficina.setStyleName("gwt-LabelFormulario");
		cpa = new Label(constante.cpa());
		cpa.setStyleName("gwt-LabelFormulario");
		localidad = new Label(constante.localidad());
		localidad.setStyleName("gwt-LabelFormulario");
		codigoPostal = new Label(constante.codigoPostal());
		codigoPostal.setStyleName("gwt-LabelFormulario");
		provincia = new Label(constante.provincia());
		provincia.setStyleName("gwt-LabelFormulario");
		pais = new Label(constante.pais());
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

		nombreEmpresaTb = new TextBox();
		nombreEmpresaTb.setText(this.cliente.getNombre());
		nombreEmpresaTb.setEnabled(false);
		nroCuitTb = new TextBox();
		nroCuitTb.setText(this.cliente.getCuit());
		responsableTb = new TextBox();
		responsableTb.setText(this.cliente.getResponsable());
		rubroTb = new TextBox();
		rubroTb.setText(this.cliente.getRubro());
		calleTb = new TextBox();
		calleTb.setText(this.cliente.getDireccion().getCalle());
		alturaTb = new TextBox();
		alturaTb.setText(this.cliente.getDireccion().getAltura());
		pisoTb = new TextBox();
		pisoTb.setStyleName("textoCorto");
		pisoTb.setText(this.cliente.getDireccion().getPiso());
		oficinaTb = new TextBox();
		oficinaTb.setText(this.cliente.getDireccion().getOficina());
		cpaTb = new TextBox();
		cpaTb.setText(this.cliente.getDireccion().getCpa());
		localidadTb = new TextBox();
		localidadTb.setText(this.cliente.getDireccion().getLocalidad());
		codigoPostalTb = new TextBox();
		codigoPostalTb.setText(this.cliente.getDireccion().getCodigoLocalidad());
		provinciaTb = new TextBox();
		provinciaTb.setText(this.cliente.getDireccion().getProvincia());
		paisTb = new TextBox();
		paisTb.setText(this.cliente.getDireccion().getPais());
		telefonoTb = new TextBox();
		telefonoTb.setText(this.cliente.getTelefono());
		webTb = new TextBox();
		webTb.setText(this.cliente.getPaginaWeb());
		faxTb = new TextBox();
		faxTb.setText(this.cliente.getFax());
		emailTb = new TextBox();
		emailTb.setText(this.cliente.getMail());
		observacionTb = new TextArea();
		observacionTb.setDirectionEstimator(false);
		observacionTb.setWidth("100%");
		observacionTb.setText(this.cliente.getObservaciones());

		btnNuevoContacto = new Button(constante.agregarContacto());
		btnNuevoContacto.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarContacto(event);
			}
		});

		btnAgregar = new Button(constante.guardar());
		btnAgregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				guardarCambiosCliente(event);
			}
		});

		btnCancelar = new Button(constante.cancelar());
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelaModificacion(event);
			}
		});

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

		formularioCliente.setWidget(2, 0, telefono);
		formularioCliente.setWidget(2, 1, telefonoTb);
		formularioCliente.setWidget(2, 2, fax);
		formularioCliente.setWidget(2, 3, faxTb);
		formularioCliente.setWidget(2, 4, email);
		formularioCliente.setWidget(2, 5, emailTb);
		formularioCliente.setWidget(2, 6, web);
		formularioCliente.setWidget(2, 7, webTb);

		formularioCliente.setWidget(3, 0, datosDideccion);
		formularioCliente.getFlexCellFormatter().setColSpan(3, 0, 10);

		formularioCliente.setWidget(4, 0, pais);
		formularioCliente.setWidget(4, 1, paisTb);
		formularioCliente.setWidget(4, 2, provincia);
		formularioCliente.setWidget(4, 3, provinciaTb);
		formularioCliente.setWidget(4, 4, localidad);
		formularioCliente.setWidget(4, 5, localidadTb);
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

		formularioCliente.setWidget(8, 4, btnNuevoContacto);

		formularioCliente.setWidget(9, 0, contenedorTabla);
		formularioCliente.getFlexCellFormatter().setColSpan(9, 0, 10);

		formularioCliente.setWidget(10, 0, datosObse);
		formularioCliente.getFlexCellFormatter().setColSpan(10, 0, 10);

		formularioCliente.setWidget(11, 0, observacion);
		formularioCliente.setWidget(11, 1, observacionTb);
		formularioCliente.getFlexCellFormatter().setColSpan(11, 1, 10);

		formularioCliente.setWidget(12, 0, inferior);
		formularioCliente.getFlexCellFormatter().setColSpan(12, 0, 10);

		formularioCliente.setWidget(13, 2, btnAgregar);

		formularioCliente.setWidget(13, 5, btnCancelar);

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


		
		
		for (int i = 0; i < clienteSeleccionado.getContacto().size(); i++){
			
			final String nombreContacto = clienteSeleccionado.getContacto().get(i).getNombre();
			
			Label eliminar = new Label("");
			eliminar.setSize("16px", "16px");
			eliminar.setStyleName("labelBorrar");
			eliminar.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					eliminarContacto(nombreContacto);
				}
			});

			tablaElemento.setWidget(i+1, COL_NOMBRE, new Label(clienteSeleccionado.getContacto().get(i).getNombre()));
			tablaElemento.setWidget(i+1, COL_CARGO, new Label(clienteSeleccionado.getContacto().get(i).getCargo()));
			tablaElemento.setWidget(i+1, COL_TELEMPRESA, new Label(clienteSeleccionado.getContacto().get(i).getTelefonoEmpresa()));
			tablaElemento.setWidget(i+1, COL_INTERNO, new Label(clienteSeleccionado.getContacto().get(i).getInternoEmpresa()));
			tablaElemento.setWidget(i+1, COL_TELPARTICULAR, new Label(clienteSeleccionado.getContacto().get(i).getTelefonoParticular()));
			tablaElemento.setWidget(i+1, COL_CELULAR, new Label(clienteSeleccionado.getContacto().get(i).getCelular()));
			tablaElemento.setWidget(i+1, COL_CORREO, new Label(clienteSeleccionado.getContacto().get(i).getMail()));
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

	public void agregarCliente(ClickEvent event) {

		Validaciones validar = new Validaciones();
		
		boolean vNombreEmpresa = validar.textBoxVacio(this.nombreEmpresaTb.getText());
		boolean vCuit = validar.textBoxVacio(this.nroCuitTb.getText());
		boolean vResponsable = validar.textBoxVacio(this.responsableTb.getText());
		boolean vPais = validar.textBoxVacio(this.paisTb.getText());
		boolean vProv = validar.textBoxVacio(this.provinciaTb.getText());
		boolean vLocalidad = validar.textBoxVacio(this.localidadTb.getText());
		boolean vCalle = validar.textBoxVacio(this.calleTb.getText());
		boolean vAltura = validar.textBoxVacio(this.alturaTb.getText());
		
		if(!vNombreEmpresa && !vCuit && !vResponsable && !vPais && !vProv && !vLocalidad && !vCalle && !vAltura){
			DireccionDTO direccion = new DireccionDTO();
			direccion.setPais(this.paisTb.getText());
			direccion.setProvincia(this.provinciaTb.getText());
			direccion.setLocalidad(this.localidadTb.getText());
			direccion.setCodigoLocalidad(this.codigoPostalTb.getText());
			direccion.setCalle(this.calleTb.getText());
			direccion.setAltura(this.alturaTb.getText());
			direccion.setPiso(this.pisoTb.getText());
			direccion.setOficina(this.oficinaTb.getText());
			direccion.setCpa(this.cpaTb.getText());

			ClienteDTO cliente = new ClienteDTO();
			cliente.setNombre(this.nombreEmpresaTb.getText());
			cliente.setCuit(this.nroCuitTb.getText());
			cliente.setResponsable(this.responsableTb.getText());
			cliente.setRubro(this.rubroTb.getText());
			cliente.setTelefono(this.telefonoTb.getText());
			cliente.setFax(this.faxTb.getText());
			cliente.setMail(this.emailTb.getText());
			cliente.setPaginaWeb(this.webTb.getText());
			cliente.setDireccion(direccion);
			cliente.setObservaciones(this.observacionTb.getText());

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

					cliente.getContacto().add(contacto);
				}

			}

			VentasServiceAsync ventasService = GWT.create(VentasService.class);
			ventasService.registrarNuevoCliente(cliente, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						Window.alert("NUEVO CLIENTE REGISTRADO!!!");
						padre.remove(numeroElemento(constante.nuevoCliente()));
					} else
						Window.alert("NO SE PUDO REGISTRAR EL CLIENTE");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR DE SERVICIO");

				}
			});
		}
		else{
			if(vNombreEmpresa)
				Window.alert("El nombre de la empresa no puede ser nulo");
			else if (vCuit)
				Window.alert("El número de cuit no puede ser nulo");
			else if (vResponsable)
				Window.alert("El campo responsable no puede ser nulo");
			else if (vPais)
				Window.alert("El país no puede ser nulo");
			else if (vProv)
				Window.alert("La provincia no puede ser nula");
			else if (vLocalidad)
				Window.alert("La localidad no puede ser nula");
			else if (vCalle)
				Window.alert("La calle no puede ser nula");
			else if (vAltura)
				Window.alert("La altura no puede ser nula");
		}
		
		
		
		
	}
		
	public void guardarCambiosCliente(ClickEvent event) {
		
		
		Validaciones validar = new Validaciones();
		
		boolean vCuit = validar.textBoxVacio(this.nroCuitTb.getText());
		boolean vResponsable = validar.textBoxVacio(this.responsableTb.getText());
		boolean vPais = validar.textBoxVacio(this.paisTb.getText());
		boolean vProv = validar.textBoxVacio(this.provinciaTb.getText());
		boolean vLocalidad = validar.textBoxVacio(this.localidadTb.getText());
		boolean vCalle = validar.textBoxVacio(this.calleTb.getText());
		boolean vAltura = validar.textBoxVacio(this.alturaTb.getText());
		
		if(!vCuit && !vResponsable && !vPais && !vProv && !vLocalidad && !vCalle && !vAltura){
			DireccionDTO direccion = new DireccionDTO();
			direccion.setPais(this.paisTb.getText());
			direccion.setProvincia(this.provinciaTb.getText());
			direccion.setLocalidad(this.localidadTb.getText());
			direccion.setCodigoLocalidad(this.codigoPostalTb.getText());
			direccion.setCalle(this.calleTb.getText());
			direccion.setAltura(this.alturaTb.getText());
			direccion.setPiso(this.pisoTb.getText());
			direccion.setOficina(this.oficinaTb.getText());
			direccion.setCpa(this.cpaTb.getText());
			
			ClienteDTO clienteModificado = new ClienteDTO();
			clienteModificado.setNombre(this.nombreEmpresaTb.getText());
			clienteModificado.setCuit(this.nroCuitTb.getText());
			clienteModificado.setResponsable(this.responsableTb.getText());
			clienteModificado.setRubro(this.rubroTb.getText());
			clienteModificado.setTelefono(this.telefonoTb.getText());
			clienteModificado.setFax(this.faxTb.getText());
			clienteModificado.setMail(this.emailTb.getText());
			clienteModificado.setPaginaWeb(this.webTb.getText());
			clienteModificado.setDireccion(direccion);
			clienteModificado.setObservaciones(this.observacionTb.getText());
			
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

					clienteModificado.getContacto().add(contacto);
				}

			}

			
			VentasServiceAsync ventasService = GWT.create(VentasService.class);
			
			
			
			ventasService.registrarCambioCliente(clienteModificado, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						Window.alert("CAMBIOS DE CLIENTE REGISTRADO!!!");
						padre.remove(numeroElemento(constante.modificarCliente()));
					} else
						Window.alert("No se han podido registrar los cambios en el cliente");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR DE SERVICIO");

				}
			});
		}
		else{
			if (vCuit)
				Window.alert("El número de cuit no puede ser nulo");
			else if (vResponsable)
				Window.alert("El campo responsable no puede ser nulo");
			else if (vPais)
				Window.alert("El país no puede ser nulo");
			else if (vProv)
				Window.alert("La provincia no puede ser nula");
			else if (vLocalidad)
				Window.alert("La localidad no puede ser nula");
			else if (vCalle)
				Window.alert("La calle no puede ser nula");
			else if (vAltura)
				Window.alert("La altura no puede ser nula");
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
		
		cliente.getContacto().remove(fila);
		
	
	}
	
	protected void agregarContacto(ClickEvent event) {

		P_AgregarContacto nuevo = new P_AgregarContacto(this);
		nuevo.setGlassEnabled(true);
		nuevo.center();
		nuevo.show();
	}

	public void cancela(ClickEvent event) {
		padre.remove(numeroElemento(constante.nuevoCliente()));

	}
	
	public void cancelaModificacion(ClickEvent event) {
		padre.remove(numeroElemento(constante.modificarCliente()));

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

}
