package edu.client;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.RenglonOrdenCompraInsumoDTO;

public class P_FormularioOrdenCompraInsumo extends Composite {

	private static final int COL_ITEM = 0;
	private static final int COL_INSUMO = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_LOTE = 3;
	private static final int COL_CANTINVENTARIO = 4;
	private static final int COL_CANTCOMPRAR = 5;
	private static final int COL_PRECIOUNITARIO = 6;
	private static final int COL_SUBTOTAL = 7;
	
	private static final int COL_BORRAR_MOD = 0;
	private static final int COL_ITEM_MOD = 1;
	private static final int COL_INSUMO_MOD = 2;
	private static final int COL_MARCA_MOD = 3;
	private static final int COL_LOTE_MOD = 4;
	private static final int COL_CANTINVENTARIO_MOD = 5;
	private static final int COL_CANTCOMPRAR_MOD = 6;
	private static final int COL_PRECIOUNITARIO_MOD = 7;
	private static final int COL_SUBTOTAL_MOD = 8;

	private Constantes constante = GWT.create(Constantes.class);

	TabPanel padre;

	private Label tituloFormulario;
	private Label proveedor;
	private Label fechaEdicion;
	private Label modoEnvio;
	private Label formaDePago;
	private Label iva;
	private Label observaciones;
	private Label total;
	private Label pie;

	private ListBox modoEnvioLb;
	private TextBox formaDePagoTb;
	private TextBox ivaTb;
	private TextArea observacionesTa;
	private TextBox totalTb;

	private Button generar;
	private Button guardar;
	private Button cancelar;
	private Button cancelarOrden;
	private Button salir;
	private Button actualizarValores;
	private Button agregarInsumo;

	private FlexTable formulario;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;
	private FlexTable botones;

	private String titulo;
	private List<InsumoDTO> insumos;
	private List<Double[]> cantidadYsubTotal;
	private String prov;
	private List<String> envio;
	private String usuario;
	private OrdenCompraInsumoDTO ordenInsumo;
	private List<InsumoDTO> listaOrdenCompraInsumo;
	private String proveedorElegido;

	public P_FormularioOrdenCompraInsumo(TabPanel padre, List<InsumoDTO> insumose, String prov, String titulo, String responsable) {

		this.usuario = responsable;
		this.padre = padre;
		this.insumos = insumose;
		this.prov = prov;
		this.titulo = titulo;

		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(new Date());

		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.completarValoresInsumos(this.insumos, this.prov, new AsyncCallback<List<InsumoDTO>>() {

			@Override
			public void onSuccess(List<InsumoDTO> result) {
				insumos = result;
				cargarRenglones();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");
			}
		});
		comprasService.getModoDeEnvio(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				envio = result;
				cargarListaenvios();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");

			}
		});

		tituloFormulario = new Label("ORDEN DE COMPRA DE INSUMOS");
		tituloFormulario.setStyleName("labelTitulo");
		proveedor = new Label(constante.proveedor() + ": " + prov);
		proveedor.setStyleName("gwt-LabelFormulario");
		fechaEdicion = new Label(constante.fechaEdicion() + ": " + fecha);
		fechaEdicion.setStyleName("gwt-LabelFormulario");
		modoEnvio = new Label(constante.modoEnvio() + ": ");
		modoEnvio.setStyleName("gwt-LabelFormulario");
		formaDePago = new Label(constante.formaDePago() + ": ");
		formaDePago.setStyleName("gwt-LabelFormulario");
		iva = new Label(constante.iva() + ": ");
		iva.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		total = new Label(constante.total());
		total.setStyleName("gwt-LabelFormulario");
		pie = new Label("");
		pie.setStyleName("labelTitulo");

		modoEnvioLb = new ListBox();
		modoEnvioLb.setStyleName("gwt-ListBox");
		formaDePagoTb = new TextBox();
		ivaTb = new TextBox();
		observacionesTa = new TextArea();
		observacionesTa.setDirectionEstimator(false);
		observacionesTa.setWidth("100%");
		totalTb = new TextBox();
		totalTb.setText("0");
		totalTb.setEnabled(false);

		generar = new Button(constante.generar());
		generar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				actualizaValores();
				registrarOrden("GENERADA");
			}
		});

		guardar = new Button(constante.guardar());
		guardar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				actualizaValores();
				registrarOrden("EDICION");
			}
		});

		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelar(event);
			}
		});

		botones = new FlexTable();
		botones.setWidget(0, 0, guardar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, generar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 2, cancelar);
		botones.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		
		actualizarValores = new Button(constante.actualizarTotal());
		actualizarValores.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				actualizaValores();
			}
		});

		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("400px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		tablaElemento.setText(0, COL_ITEM, constante.item());
		tablaElemento.getCellFormatter().setWidth(0, COL_ITEM, "10%");
		tablaElemento.setText(0, COL_INSUMO, constante.insumo());
		tablaElemento.getCellFormatter().setWidth(0, COL_INSUMO, "10%");
		tablaElemento.setText(0, COL_MARCA, constante.marca());
		tablaElemento.getCellFormatter().setWidth(0, COL_MARCA, "10%");
		tablaElemento.setText(0, COL_LOTE, constante.loteCompra());
		tablaElemento.getCellFormatter().setWidth(0, COL_LOTE, "10%");
		tablaElemento.setText(0, COL_CANTINVENTARIO, constante.cantInventario());
		tablaElemento.getCellFormatter().setWidth(0, COL_CANTINVENTARIO, "10%");
		tablaElemento.setText(0, COL_CANTCOMPRAR, constante.cantComprar());
		tablaElemento.getCellFormatter().setWidth(0, COL_CANTCOMPRAR, "10%");
		tablaElemento.setText(0, COL_PRECIOUNITARIO, constante.precio());
		tablaElemento.getCellFormatter().setWidth(0, COL_PRECIOUNITARIO, "10%");
		tablaElemento.setText(0, COL_SUBTOTAL, constante.subtotal());
		tablaElemento.getCellFormatter().setWidth(0, COL_SUBTOTAL, "10%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

		formulario = new FlexTable();
		formulario.setStyleName("formatoFormulario");
		formulario.setHeight("637px");
		formulario.setWidth("920px");

		formulario.setWidget(0, 0, tituloFormulario);
		formulario.getFlexCellFormatter().setColSpan(0, 0, 6);

		formulario.setWidget(1, 0, proveedor);
		formulario.setWidget(1, 5, fechaEdicion);

		formulario.setWidget(2, 0, modoEnvio);
		formulario.setWidget(2, 1, modoEnvioLb);
		formulario.getCellFormatter().setWidth(2, 1, "20%");
		formulario.setWidget(2, 2, formaDePago);
		formulario.setWidget(2, 3, formaDePagoTb);
		formulario.setWidget(2, 4, iva);
		formulario.setWidget(2, 5, ivaTb);

		formulario.setWidget(3, 0, contenedorTabla);
		formulario.getFlexCellFormatter().setColSpan(3, 0, 6);

		formulario.setWidget(4, 4, total);
		formulario.setWidget(4, 5, totalTb);
		formulario.setWidget(4, 3, actualizarValores);

		formulario.setWidget(5, 0, observaciones);
		formulario.getFlexCellFormatter().setColSpan(5, 0, 6);
		formulario.setWidget(6, 0, observacionesTa);
		formulario.getFlexCellFormatter().setColSpan(6, 0, 6);

		formulario.setWidget(7, 0, pie);
		formulario.getFlexCellFormatter().setColSpan(7, 0, 6);

		formulario.setWidget(8, 0, botones);
		formulario.getFlexCellFormatter().setColSpan(8, 0, 6);
		formulario.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		initWidget(formulario);

	}
	
	//constructor llamado a la hora de modificar la orden de compra de insumo que fue guardada y vuelta a abrir
	public P_FormularioOrdenCompraInsumo(TabPanel padre, String titulo, OrdenCompraInsumoDTO ordenInsumo) {

		this.padre = padre;
		this.titulo = titulo;
		this.ordenInsumo = ordenInsumo;
		final Date fechaEd = ordenInsumo.getFechaEdicion();
		final OrdenCompraInsumoDTO ordenVieja = this.ordenInsumo;
		this.prov = this.ordenInsumo.getProveedor();
		this.usuario = this.ordenInsumo.getEmpleado();
		insumos = new LinkedList<InsumoDTO>();
		cantidadYsubTotal =  new LinkedList<Double []>();
		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(this.ordenInsumo.getFechaEdicion());
		
		for (RenglonOrdenCompraInsumoDTO renglon : ordenInsumo.getRenglonOrdenCompraInsumos()) {
			insumos.add(renglon.getInsumo());
			
			Double subtotal;
			if(renglon.getSubtotal() == null)
				subtotal = null;
			else
				subtotal = renglon.getSubtotal();
			
			Double []contenido = {renglon.getCantidad(),subtotal};
			cantidadYsubTotal.add(contenido);
		}

		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.completarValoresInsumos(this.insumos, this.prov, new AsyncCallback<List<InsumoDTO>>() {

			@Override
			public void onSuccess(List<InsumoDTO> result) {
				insumos = result;
				cargarRenglonesModificada();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");
			}
		});
		
		comprasService.getModoDeEnvio(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				envio = result;
				cargarListaEnviosModificar();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");

			}
		});

		tituloFormulario = new Label("ORDEN DE COMPRA DE INSUMOS");
		tituloFormulario.setStyleName("labelTitulo");
		proveedor = new Label(constante.proveedor() + ": " + prov);
		proveedor.setStyleName("gwt-LabelFormulario");
		fechaEdicion = new Label(constante.fechaEdicion() + ": " + fecha);
		fechaEdicion.setStyleName("gwt-LabelFormulario");
		modoEnvio = new Label(constante.modoEnvio() + ": ");
		modoEnvio.setStyleName("gwt-LabelFormulario");
		formaDePago = new Label(constante.formaDePago() + ": ");
		formaDePago.setStyleName("gwt-LabelFormulario");
		iva = new Label(constante.iva() + ": ");
		iva.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		total = new Label(constante.total());
		total.setStyleName("gwt-LabelFormulario");
		pie = new Label("");
		pie.setStyleName("labelTitulo");

		modoEnvioLb = new ListBox();
		modoEnvioLb.setStyleName("gwt-ListBox");
		formaDePagoTb = new TextBox();
		formaDePagoTb.setText(this.ordenInsumo.getFormaPago());
		ivaTb = new TextBox();
		ivaTb.setText(this.ordenInsumo.getIva()+"");
		observacionesTa = new TextArea();
		observacionesTa.setText(this.ordenInsumo.getObservaciones());
		observacionesTa.setDirectionEstimator(false);
		observacionesTa.setWidth("100%");
		totalTb = new TextBox();
		totalTb.setText(ordenInsumo.getTotal()+"");
		totalTb.setEnabled(false);

		generar = new Button(constante.generar());
		generar.addClickHandler(new ClickHandler() {
						
			public void onClick(ClickEvent event) {
				actualizaValoresEnModificacion();
				registrarCambiosEnOrden("GENERADA",fechaEd,ordenVieja);
			}
		});

		guardar = new Button(constante.guardar());
		guardar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				actualizaValoresEnModificacion();
				registrarCambiosEnOrden("EDICION",fechaEd,ordenVieja);
			}
		});

		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				cancelarModificada(event);
				
			}
		});
		
		cancelarOrden = new Button(constante.cancelarOrden());
		cancelarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelarOrden(event,ordenVieja);
			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, guardar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, generar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 2, cancelarOrden);
		botones.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 3, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER);

		actualizarValores = new Button(constante.actualizarTotal());
		actualizarValores.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				actualizaValoresEnModificacion();
			}
		});
		
		agregarInsumo = new Button(constante.agregarInsumo());
		agregarInsumo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				final P_RequerimientoInsumo popUp = new P_RequerimientoInsumo("nada");
				popUp.setGlassEnabled(true);
				popUp.center();
				popUp.show();
				popUp.addCloseHandler(new CloseHandler<PopupPanel>() {

					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						
						listaOrdenCompraInsumo = popUp.getListaOrdenCompraInsumo();
						proveedorElegido = popUp.getProveedorElegido();
						boolean agregarOrden = popUp.getAgregarOrden();
						
						if (agregarOrden == true){
							if(prov.compareTo(proveedorElegido) != 0){
								Window.alert("El proveedor de los insumos que se desea cargar debe de ser el mismo que el de lo insumos que ya se encuentran en la orden");	
							}
							else{
								buscarInsumosCompletosYAgregarOrden();
							}		
						}
					}
				});
			}
		});
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("400px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		tablaElemento.setText(0, COL_BORRAR_MOD, "");
		tablaElemento.getCellFormatter().setWidth(0, COL_BORRAR_MOD, "10%");	
		tablaElemento.setText(0, COL_ITEM_MOD, constante.item());	
		tablaElemento.getCellFormatter().setWidth(0, COL_ITEM_MOD, "10%");
		tablaElemento.setText(0, COL_INSUMO_MOD, constante.insumo());
		tablaElemento.getCellFormatter().setWidth(0, COL_INSUMO_MOD, "10%");
		tablaElemento.setText(0, COL_MARCA_MOD, constante.marca());
		tablaElemento.getCellFormatter().setWidth(0, COL_MARCA_MOD, "10%");
		tablaElemento.setText(0, COL_LOTE_MOD, constante.loteCompra());
		tablaElemento.getCellFormatter().setWidth(0, COL_LOTE_MOD, "10%");
		tablaElemento.setText(0, COL_CANTINVENTARIO_MOD, constante.cantInventario());
		tablaElemento.getCellFormatter().setWidth(0, COL_CANTINVENTARIO_MOD, "10%");
		tablaElemento.setText(0, COL_CANTCOMPRAR_MOD, constante.cantComprar());
		tablaElemento.getCellFormatter().setWidth(0, COL_CANTCOMPRAR_MOD, "10%");
		tablaElemento.setText(0, COL_PRECIOUNITARIO_MOD, constante.precio());
		tablaElemento.getCellFormatter().setWidth(0, COL_PRECIOUNITARIO_MOD, "10%");
		tablaElemento.setText(0, COL_SUBTOTAL_MOD, constante.subtotal());
		tablaElemento.getCellFormatter().setWidth(0, COL_SUBTOTAL_MOD, "10%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

		formulario = new FlexTable();
		formulario.setStyleName("formatoFormulario");
		formulario.setHeight("637px");
		formulario.setWidth("920px");

		formulario.setWidget(0, 0, tituloFormulario);
		formulario.getFlexCellFormatter().setColSpan(0, 0, 6);

		formulario.setWidget(1, 0, proveedor);
		formulario.setWidget(1, 5, fechaEdicion);

		formulario.setWidget(2, 0, modoEnvio);
		formulario.setWidget(2, 1, modoEnvioLb);
		formulario.getCellFormatter().setWidth(2, 1, "20%");
		formulario.setWidget(2, 2, formaDePago);
		formulario.setWidget(2, 3, formaDePagoTb);
		formulario.setWidget(2, 4, iva);
		formulario.setWidget(2, 5, ivaTb);

		formulario.setWidget(3, 0, contenedorTabla);
		formulario.getFlexCellFormatter().setColSpan(3, 0, 6);

		formulario.setWidget(4, 4, total);
		formulario.setWidget(4, 5, totalTb);
		formulario.setWidget(4, 3, actualizarValores);
		formulario.setWidget(4, 2, agregarInsumo);

		formulario.setWidget(5, 0, observaciones);
		formulario.getFlexCellFormatter().setColSpan(5, 0, 6);
		formulario.setWidget(6, 0, observacionesTa);
		formulario.getFlexCellFormatter().setColSpan(6, 0, 6);

		formulario.setWidget(7, 0, pie);
		formulario.getFlexCellFormatter().setColSpan(7, 0, 6);

		formulario.setWidget(8, 0, botones);
		formulario.getFlexCellFormatter().setColSpan(8, 0, 6);
		formulario.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		initWidget(formulario);

	}
	
	protected void buscarInsumosCompletosYAgregarOrden() {
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.completarValoresInsumos(this.listaOrdenCompraInsumo, this.proveedorElegido, new AsyncCallback<List<InsumoDTO>>() {

			@Override
			public void onSuccess(List<InsumoDTO> result) {
				
				boolean bandera = true;
				
				for (InsumoDTO insumo : result) {
					
					for (InsumoDTO insu : insumos) {
						if(insu.getIdInsumo() == insumo.getIdInsumo()){
							Window.alert("No se pueden agregar a la orden insumos que ya se encuentren en la orden");
							bandera = false;
						}
					}
					
					if(bandera == true){
						insumos.add(insumo);
						Double []contenido = {0.0,0.0};
						cantidadYsubTotal.add(contenido);
					}
						
				}
								
				cargarRenglonesModificada();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");
			}
		});
		
	}
	
	protected void registrarOrden(final String estado) {
		OrdenCompraInsumoDTO nuevaOrden = new OrdenCompraInsumoDTO();
		nuevaOrden.setEstadoOrden(estado);
		nuevaOrden.setEmpleado(usuario);
		nuevaOrden.setFechaEdicion(new Date());
		boolean banderita = false;
		if (estado.compareTo("GENERADA") == 0) {
			nuevaOrden.setFechaGeneracion(new Date());
		}
		nuevaOrden.setProveedor(prov);
		nuevaOrden.setFormaPago(this.formaDePagoTb.getText());
		nuevaOrden.setObservaciones(this.observacionesTa.getText());
		nuevaOrden.setTotal(new Double(totalTb.getText()));
		String modo = modoEnvioLb.getItemText(modoEnvioLb.getSelectedIndex());
		nuevaOrden.setModoEnvio(modo);

		for (int i = 1; i < tablaElemento.getRowCount(); i++) {
			RenglonOrdenCompraInsumoDTO renglon = new RenglonOrdenCompraInsumoDTO();
			InsumoDTO insu = new InsumoDTO();
			insu.setMarca(((Label) tablaElemento.getWidget(i, COL_MARCA)).getText());
			insu.setNombre(((Label) tablaElemento.getWidget(i, COL_INSUMO)).getText());
			
			
			if(estado.compareTo("GENERADA") == 0){
				if(((TextBox) tablaElemento.getWidget(i, COL_CANTCOMPRAR)).getText().compareTo("") == 0){
					banderita = true;
					break;
				}					
				else
					renglon.setCantidad(new Double(((TextBox) tablaElemento.getWidget(i, COL_CANTCOMPRAR)).getText()));
			}
			else{
				if(((TextBox) tablaElemento.getWidget(i, COL_CANTCOMPRAR)).getText().compareTo("") == 0){
					banderita = true;
					break;
				}
				else
					renglon.setCantidad(new Double(((TextBox) tablaElemento.getWidget(i, COL_CANTCOMPRAR)).getText()));
			}
				
			renglon.setItem(i);
			
			if(((TextBox)tablaElemento.getWidget(i, COL_PRECIOUNITARIO)).getText().compareTo("") == 0)
				renglon.setPrecio();
			else
				renglon.setPrecio(new Double(((TextBox) tablaElemento.getWidget(i, COL_PRECIOUNITARIO)).getText()));
			
			if(((Label) tablaElemento.getWidget(i, COL_SUBTOTAL)).getText().compareTo("") == 0)
				renglon.setSubtotal();
			else
				renglon.setSubtotal(new Double(((Label) tablaElemento.getWidget(i, COL_SUBTOTAL)).getText()));
			
			
			
			
			renglon.setInsumo(insu);
			nuevaOrden.getRenglonOrdenCompraInsumos().add(renglon);
		}
		
		if(banderita == false){
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.registrarOrdenCompraInsumos(nuevaOrden, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						String accion = "";
						if (estado.compareTo("GENERADA") == 0)
							accion = "generado";
						else
							accion = "guardado";
						Window.alert("Se ha " + accion + " corectamente la orden");
						padre.remove(numeroElemento(constante.ordenDeCompraDeInsumos()));
					} else {
						Window.alert("No se pudo efectuar la acción");
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR DE SERVICIO");

				}
			});
		}
		else{
			Window.alert("No se puede generar o guardar una orden de compra de insumos sino se establecen todas las cantidades");
		}


	}
	
	protected void registrarCambiosEnOrden(final String estado, Date fechaEdicion, OrdenCompraInsumoDTO ordenVieja) {
		
		OrdenCompraInsumoDTO nuevaOrden = new OrdenCompraInsumoDTO();
		nuevaOrden.setEstadoOrden(estado);
		nuevaOrden.setEmpleado(usuario);
		nuevaOrden.setFechaEdicion(fechaEdicion);
		boolean banderita = false;
		if (estado.compareTo("GENERADA") == 0) {
			nuevaOrden.setFechaGeneracion(new Date());
		}
		nuevaOrden.setProveedor(prov);
		nuevaOrden.setFormaPago(this.formaDePagoTb.getText());
		nuevaOrden.setObservaciones(this.observacionesTa.getText());
		nuevaOrden.setTotal(new Double(totalTb.getText()));
		String modo = modoEnvioLb.getItemText(modoEnvioLb.getSelectedIndex());
		nuevaOrden.setModoEnvio(modo);

		for (int i = 1; i < tablaElemento.getRowCount(); i++) {
			RenglonOrdenCompraInsumoDTO renglon = new RenglonOrdenCompraInsumoDTO();
			InsumoDTO insu = new InsumoDTO();
			insu.setMarca(((Label) tablaElemento.getWidget(i, COL_MARCA_MOD)).getText());
			insu.setNombre(((Label) tablaElemento.getWidget(i, COL_INSUMO_MOD)).getText());
			
			if(estado.compareTo("GENERADA") == 0){
				if(((TextBox) tablaElemento.getWidget(i, COL_CANTCOMPRAR_MOD)).getText().compareTo("") == 0){
					banderita = true;
					break;
				}					
				else
					renglon.setCantidad(new Double(((TextBox) tablaElemento.getWidget(i, COL_CANTCOMPRAR_MOD)).getText()));
			}
			else{
				if(((TextBox) tablaElemento.getWidget(i, COL_CANTCOMPRAR_MOD)).getText().compareTo("") == 0)
					renglon.setCantidad();
				else
					renglon.setCantidad(new Double(((TextBox) tablaElemento.getWidget(i, COL_CANTCOMPRAR_MOD)).getText()));
			}
					
			
			renglon.setItem(i);
			
			if(((TextBox)tablaElemento.getWidget(i, COL_PRECIOUNITARIO_MOD)).getText().compareTo("") == 0)
				renglon.setPrecio();
			else
				renglon.setPrecio(new Double(((TextBox) tablaElemento.getWidget(i, COL_PRECIOUNITARIO_MOD)).getText()));
			
			if(((Label) tablaElemento.getWidget(i, COL_SUBTOTAL_MOD)).getText().compareTo("") == 0)
				renglon.setSubtotal();
			else
				renglon.setSubtotal(new Double(((Label) tablaElemento.getWidget(i, COL_SUBTOTAL_MOD)).getText()));
			
			renglon.setInsumo(insu);
			nuevaOrden.getRenglonOrdenCompraInsumos().add(renglon);
		}
		
		
		if(banderita == false){
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.registrarModificacionOrdenCompraInsumos(nuevaOrden, ordenVieja, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						String accion = "";
						if (estado.compareTo("GENERADA") == 0)
							accion = "generado";
						else
							accion = "guardado";
						Window.alert("Se ha " + accion + " corectamente la orden");
						padre.remove(numeroElemento(constante.modificarOrdenCompraDeInsumo()));
					} else {
						Window.alert("No se pudo efectuar la acción");
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR DE SERVICIO");

				}
			});
		}
		else{
			Window.alert("No se puede generar una orden de compra de insumos sino se establecen todas las cantidades");
		}


	}

	protected void cargarListaenvios() {
		for (String modo : envio) {
			modoEnvioLb.addItem(modo);
		}
			
	}
	
	protected void cargarListaEnviosModificar(){
		for(String modo : envio){
			modoEnvioLb.addItem(modo);
		}
		modoEnvioLb.setSelectedIndex(envio.indexOf(ordenInsumo.getModoEnvio()));
	}

	public void cargarRenglones() {
		int item = 1;

		for (InsumoDTO insumo : insumos) {
			TextBox precioTb = new TextBox();
			TextBox cantidadRequeridaTb = new TextBox();
			
			tablaElemento.setWidget(item, COL_ITEM, new Label("" + item));
			tablaElemento.setWidget(item, COL_INSUMO, new Label(insumo.getNombre()));
			tablaElemento.setWidget(item, COL_MARCA, new Label(insumo.getMarca()));
			tablaElemento.setWidget(item, COL_CANTCOMPRAR, cantidadRequeridaTb);
			tablaElemento.setWidget(item, COL_SUBTOTAL, new Label(""));
			tablaElemento.setWidget(item, COL_CANTINVENTARIO, new Label(insumo.getCantidad() + ""));
			tablaElemento.setWidget(item, COL_LOTE, new Label(insumo.getLoteCompra() + ""));
			if(insumo.getProveedor().get(0).getPrecio() == null)
				precioTb.setText("");
			else{
				Double precio = insumo.getProveedor().get(0).getPrecio();
				precioTb.setText("" + precio);
			}

			
			tablaElemento.setWidget(item, COL_PRECIOUNITARIO, precioTb);
			tablaElemento.getRowFormatter().setStyleName(item, "tablaRenglon");
			item++;
		}
	}
		
	public void cargarRenglonesModificada() {
		
		int item = 1;
				
		
		for (InsumoDTO insumo : insumos) {
			

			
			Label quitar = new Label("");
			quitar.setSize("16px", "16px");
			quitar.addStyleName("labelBorrar");
			quitar.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Cell celda = tablaElemento.getCellForEvent(event);
					insumos.remove(celda.getRowIndex()-1);
					tablaElemento.clear();
					cargarRenglonesModificada();
					
				}
			});
			
			TextBox precioTb = new TextBox();
			TextBox cantidadRequeridaTb = new TextBox();
			
			Double cantRec = cantidadYsubTotal.get(item-1)[0];
			
			cantidadRequeridaTb.setText(cantRec+"");

			
			tablaElemento.setWidget(item, COL_BORRAR_MOD, quitar);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(item, COL_BORRAR_MOD, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.setWidget(item, COL_ITEM_MOD, new Label("" + item));
			tablaElemento.setWidget(item, COL_INSUMO_MOD, new Label(insumo.getNombre()));
			tablaElemento.setWidget(item, COL_MARCA_MOD, new Label(insumo.getMarca()));
			tablaElemento.setWidget(item, COL_CANTCOMPRAR_MOD, cantidadRequeridaTb);
			
			String subTotal;
			if(cantidadYsubTotal.get(item-1)[1] == null)
				subTotal = "";
			else{
				Double sub = cantidadYsubTotal.get(item-1)[1];
				subTotal = "" + sub;
			}
			
			
			tablaElemento.setWidget(item, COL_SUBTOTAL_MOD, new Label(subTotal));
			tablaElemento.setWidget(item, COL_CANTINVENTARIO_MOD, new Label(insumo.getCantidad() + ""));
			tablaElemento.setWidget(item, COL_LOTE_MOD, new Label(insumo.getLoteCompra() + ""));
			
			if(insumo.getProveedor().get(0).getPrecio() == null)
				precioTb.setText("");
			else{
				Double precio = insumo.getProveedor().get(0).getPrecio();
				precioTb.setText("" + precio);
			}
			
			tablaElemento.setWidget(item, COL_PRECIOUNITARIO_MOD, precioTb);
			tablaElemento.getRowFormatter().setStyleName(item, "tablaRenglon");

			item++;
		}
	}

	public void cancelar(ClickEvent event) {
		padre.remove(numeroElemento(constante.ordenDeCompraDeInsumos()));

	}
	
	public void cancelarModificada(ClickEvent event) {
		padre.remove(numeroElemento(constante.modificarOrdenCompraDeInsumo()));

	}

	public void cancelarOrden(ClickEvent event, OrdenCompraInsumoDTO ordenVieja) {
				
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.eliminarOrdenCompraInsumos(ordenVieja, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				Window.alert("La orden de compra de insumo ha sido cancelada");
				padre.remove(numeroElemento(constante.modificarOrdenCompraDeInsumo()));
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");

			}
		});
		

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
	
	public void actualizaValores() {

		Double costoTotal = 0.0;
		Validaciones validar = new Validaciones();
		boolean vCantCompra = false;
		boolean vPrecio = false;
		
		for(int i = 1; i < tablaElemento.getRowCount(); i++){
			
			boolean banderola = false;
			Double costoInsumo = 0.0;
			
			String cantComprar = ((TextBox)tablaElemento.getWidget(i, COL_CANTCOMPRAR)).getText();
			String precio = ((TextBox)tablaElemento.getWidget(i, COL_PRECIOUNITARIO)).getText();
			
			if((cantComprar).compareTo("") != 0 && (precio).compareTo("") != 0){
				
				vCantCompra = validar.textBoxSoloNumeros(cantComprar);
				vPrecio = validar.textBoxSoloNumeros(precio);
				
				if(vCantCompra == true && vPrecio == true){
					
					Double cantComprarD = Double.parseDouble(((TextBox)tablaElemento.getWidget(i, COL_CANTCOMPRAR)).getText());
					Double precioD = Double.parseDouble(((TextBox)tablaElemento.getWidget(i, COL_PRECIOUNITARIO)).getText());
					Double costo = cantComprarD*precioD;
					costoInsumo = costo;
					costoTotal+= costo;
				}
				else{
					banderola = true;
				}
			}
			else {
				banderola = true;
			}
		
			if(banderola == false){
				Double subTotal = costoInsumo;
				((Label) tablaElemento.getWidget(i, COL_SUBTOTAL)).setText(""+subTotal);
			}
			
		}

			
		totalTb.setText(""+costoTotal);
	
}
	
	public void actualizaValoresEnModificacion() {

		Double costoTotal = 0.0;
		Validaciones validar = new Validaciones();
		boolean vCantCompra = false;
		boolean vPrecio = false;
		
		for(int i = 1; i < tablaElemento.getRowCount(); i++){
			
			boolean banderola = false;
			Double costoInsumo = 0.0;
			
			String cantComprar = ((TextBox)tablaElemento.getWidget(i, COL_CANTCOMPRAR_MOD)).getText();
			String precio = ((TextBox)tablaElemento.getWidget(i, COL_PRECIOUNITARIO_MOD)).getText();
			
			if((cantComprar).compareTo("") != 0 && (precio).compareTo("") != 0){
				
				vCantCompra = validar.textBoxSoloNumeros(cantComprar);
				vPrecio = validar.textBoxSoloNumeros(precio);
				
				if(vCantCompra == true && vPrecio == true){
					
					Double cantComprarD = Double.parseDouble(((TextBox)tablaElemento.getWidget(i, COL_CANTCOMPRAR_MOD)).getText());
					Double precioD = Double.parseDouble(((TextBox)tablaElemento.getWidget(i, COL_PRECIOUNITARIO_MOD)).getText());
					Double costo = cantComprarD*precioD;
					costoInsumo = costo;
					costoTotal+= costo;
				}
				else{
					banderola = true;
				}
			}
			else {
				banderola = true;
			}
		
			if(banderola == false){
				Double subTotal = costoInsumo;
				((Label) tablaElemento.getWidget(i, COL_SUBTOTAL_MOD)).setText(""+subTotal);
			}
			
		}

			
		totalTb.setText(""+costoTotal);
	
}
	
}
