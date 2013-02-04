package edu.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import edu.client.AlmacenService.AlmacenService;
import edu.client.AlmacenService.AlmacenServiceAsync;
import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.RemitoExternoDTO;
import edu.shared.DTO.RenglonOrdenCompraInsumoDTO;
import edu.shared.DTO.RenglonRemitoExternoDTO;

public class P_RemitoExterno extends PopupPanel {

	private Constantes constante = GWT.create(Constantes.class);
	
	private static final int COL_INSUMO = 0;
	private static final int COL_MARCA = 1;
	private static final int COL_CANT_PEDIDA = 2;
	private static final int COL_CANT_FALTANTE = 3;
	private static final int COL_CANT_RECIBIDA = 4;
	private static final int COL_CHECK = 5;
	private static final int COL_INSUMO_RESULT = 0;
	private static final int COL_MARCA_RESULT = 1;
	private static final int COL_CANT_RECIBIDA_RESULT = 2;
	
	private Label titulo;
	private Label pie;
	
	private Label nroRemitoEx;
	private Label nroOrdenCompra;
	private Label fechaIngreso;
	private Label proveedor;
	private Label observaciones;
	private Label empleado;
	
	private TextArea observacionesTa;
	
	private TextBox nroRemitoExTb;
	
	private Button cancelar;
	private Button aceptar;
	private Button salir;
	
	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	private FlexTable botones;
	
	private String usuario;	
	
	private OrdenCompraInsumoDTO ordenCompraInsumo;
	private RemitoExternoDTO remitoExterno;
		
	private int cantFaltante;
	
	
	
	
	
	public P_RemitoExterno(OrdenCompraInsumoDTO ordenInsumo, String usuarioLogueado) {
	
		super(false);
		setStyleName("fondoPopup");
		
		this.ordenCompraInsumo = ordenInsumo;
		this.usuario = usuarioLogueado;
		this.cantFaltante = 0;
		
		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(new Date());
		
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.getOrdenCompraInsumoSegunId(ordenInsumo.getIdOrden(), new AsyncCallback<OrdenCompraInsumoDTO>() {

			@Override
			public void onSuccess(OrdenCompraInsumoDTO result) {
				cargarTabla(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");

			}
		});
		
		
		
		
		
		
		titulo = new Label("INGRESO REMITO EXTERNO");
		titulo.setStyleName("labelTitulo");
		nroOrdenCompra = new Label(constante.nroOrdenDeCompra() + ": " + ordenInsumo.getNroOrden());
		nroOrdenCompra.setStyleName("gwt-LabelFormulario");
		proveedor = new Label(constante.proveedor() + ": " + ordenInsumo.getProveedor());
		proveedor.setStyleName("gwt-LabelFormulario");
		fechaIngreso = new Label(constante.fechaIngreso() + ": " + fecha);
		fechaIngreso.setStyleName("gwt-LabelFormulario");
		nroRemitoEx = new Label(constante.nroRemitoExterno() + ": ");
		nroRemitoEx.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		observacionesTa = new TextArea();
		observacionesTa.setDirectionEstimator(false);
		observacionesTa.setWidth("99%");
		
		nroRemitoExTb = new TextBox();
		nroRemitoExTb.setStyleName("gwt-TextArea");
		
		
		panel = new FlexTable();
		panel.setSize("750px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_INSUMO, constante.insumo());
		tablaElementos.getCellFormatter().setWidth(0, COL_INSUMO, "19%");
		tablaElementos.setText(0, COL_MARCA, constante.marca());
		tablaElementos.getCellFormatter().setWidth(0, COL_MARCA, "19%");
		tablaElementos.setText(0, COL_CANT_PEDIDA, constante.cantPedida());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANT_PEDIDA, "19%");
		tablaElementos.setText(0, COL_CANT_FALTANTE, constante.cantFaltante());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANT_FALTANTE, "19%");
		tablaElementos.setText(0, COL_CANT_RECIBIDA, constante.cantRecibida());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANT_RECIBIDA, "19%");
		tablaElementos.setText(0, COL_CHECK, "");
		tablaElementos.getCellFormatter().setWidth(0, COL_CHECK, "5%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");

			
		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelar();
			}
		});
		
		
		aceptar = new Button(constante.aceptar());
		aceptar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				guardarRemitoExterno();
			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, aceptar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, cancelar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 2);

		panel.setWidget(1, 0, fechaIngreso);
		panel.getFlexCellFormatter().setColSpan(1, 0, 2);
		
		panel.setWidget(2, 0, nroOrdenCompra);
		panel.getFlexCellFormatter().setColSpan(2, 0, 2);
		
		panel.setWidget(3, 0, proveedor);
		panel.getFlexCellFormatter().setColSpan(3, 0, 2);		
		
		panel.setWidget(4, 0, nroRemitoEx);
		
		panel.setWidget(5, 0, nroRemitoExTb);
					
		panel.setWidget(6, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(6, 0, 2);
		
		panel.setWidget(7, 0, observaciones);
		panel.getFlexCellFormatter().setColSpan(7, 0, 2);
		
		panel.setWidget(8, 0, observacionesTa);
		panel.getFlexCellFormatter().setColSpan(8, 0, 2);
		
		panel.setWidget(9, 0, pie);
		panel.getFlexCellFormatter().setColSpan(9, 0, 2);

		panel.setWidget(10, 0, botones);
		panel.getFlexCellFormatter().setColSpan(10, 0, 2);
		panel.getCellFormatter().setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			
		setWidget(panel);
		

	}
	
	public P_RemitoExterno(OrdenCompraInsumoDTO ordenInsumo, RemitoExternoDTO remito) {
		
	
		super(false);
		setStyleName("fondoPopup");
				
		this.ordenCompraInsumo = ordenInsumo;
		this.remitoExterno = remito;
		this.cantFaltante = 0;
				
		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(remito.getFechaIngreso());		
		
		titulo = new Label("REMITO DE INSUMOS EXTERNO");
		titulo.setStyleName("labelTitulo");
		nroOrdenCompra = new Label(constante.nroOrdenDeCompra() + ": " + ordenInsumo.getNroOrden());
		nroOrdenCompra.setStyleName("gwt-LabelFormulario");
		proveedor = new Label(constante.proveedor() + ": " + ordenInsumo.getProveedor());
		proveedor.setStyleName("gwt-LabelFormulario");
		fechaIngreso = new Label(constante.fechaIngreso() + ": " + fecha);
		fechaIngreso.setStyleName("gwt-LabelFormulario");
		nroRemitoEx = new Label(constante.nroRemitoExterno() + ": " + remito.getIdRemitoEx());
		nroRemitoEx.setStyleName("gwt-LabelFormulario");
		empleado = new Label (constante.empleado() + ": " + remito.getEmpleado());
		empleado.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		observacionesTa = new TextArea();
		observacionesTa.setDirectionEstimator(false);
		observacionesTa.setWidth("99%");
		observacionesTa.setText(remito.getObservaciones());
			
		
		panel = new FlexTable();
		panel.setSize("550px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_INSUMO_RESULT, constante.insumo());
		tablaElementos.getCellFormatter().setWidth(0, COL_INSUMO_RESULT, "33%");
		tablaElementos.setText(0, COL_MARCA_RESULT, constante.marca());
		tablaElementos.getCellFormatter().setWidth(0, COL_MARCA_RESULT, "33%");
		tablaElementos.setText(0, COL_CANT_RECIBIDA_RESULT, constante.cantRecibida());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANT_RECIBIDA_RESULT, "33%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");

			
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
				
		botones = new FlexTable();
		botones.setWidget(0, 0, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 2);

		panel.setWidget(1, 0, fechaIngreso);
		panel.getFlexCellFormatter().setColSpan(1, 0, 2);
		
		panel.setWidget(2, 0, nroOrdenCompra);
		panel.getFlexCellFormatter().setColSpan(2, 0, 2);
		
		panel.setWidget(3, 0, nroRemitoEx);
		panel.getFlexCellFormatter().setColSpan(3, 0, 2);
		
		panel.setWidget(4, 0, proveedor);
		panel.getFlexCellFormatter().setColSpan(4, 0, 2);	
		
		panel.setWidget(5, 0, empleado);
		panel.getFlexCellFormatter().setColSpan(5, 0, 2);		
					
		panel.setWidget(6, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(6, 0, 2);
		
		panel.setWidget(7, 0, observaciones);
		panel.getFlexCellFormatter().setColSpan(7, 0, 2);
		
		panel.setWidget(8, 0, observacionesTa);
		panel.getFlexCellFormatter().setColSpan(8, 0, 2);
		
		panel.setWidget(9, 0, pie);
		panel.getFlexCellFormatter().setColSpan(9, 0, 2);

		panel.setWidget(10, 0, botones);
		panel.getFlexCellFormatter().setColSpan(10, 0, 2);
		panel.getCellFormatter().setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			
		cargarTabla(remito);
		
		setWidget(panel);
		
		
		
	}
		
	protected void guardarRemitoExterno() {
		
		
		Validaciones validar = new Validaciones();
		boolean entro = false;
		boolean vCantRecibida1 = false;
		boolean vCantRecibida2 = false;
		boolean vCantRecibida3 = false;
		boolean vNroRemito1 = false;
		boolean vNroRemito2 = false;
		
		
		String nroRemito = new String(this.nroRemitoExTb.getText());
		vNroRemito1 = validar.textBoxVacio(nroRemito);
		vNroRemito2 = validar.textBoxSoloNumeros(nroRemito);
		
		
		
		
		for (int i = 1; i < tablaElementos.getRowCount(); i++) {
			
			if(((CheckBox)tablaElementos.getWidget(i, COL_CHECK)).getValue() == true){

				entro = true;
				String cantRecibida = new String(((TextBox) tablaElementos.getWidget(i, COL_CANT_RECIBIDA)).getText());
				vCantRecibida1 = validar.textBoxVacio(cantRecibida);
				vCantRecibida2 = validar.textBoxSoloNumeros(cantRecibida);
				
				if(!vCantRecibida1 && vCantRecibida2){
					
					Float cantRe = Float.parseFloat(((TextBox)tablaElementos.getWidget(i, COL_CANT_RECIBIDA)).getText());
					Float cantFa = Float.parseFloat(((Label)tablaElementos.getWidget(i, COL_CANT_FALTANTE)).getText());
					
					
					if(cantRe > cantFa)
						vCantRecibida3 = true;

				}			
			}
		}	
		
		
		if(!vCantRecibida1 && vCantRecibida2 && !vCantRecibida3 && !vNroRemito1 && vNroRemito2){
			
			
			
			long nroRemitoEx = Long.parseLong(this.nroRemitoExTb.getText());
			
			AlmacenServiceAsync almacenService = GWT.create(AlmacenService.class);
			almacenService.getExistenciaRemitoExterno(this.ordenCompraInsumo.getIdOrden(),nroRemitoEx, new AsyncCallback<Boolean>() {
						
				@Override
				public void onSuccess(Boolean result) {
					if(result){
						Window.alert("El \"número de remito externo\" ingresado ya se encuentra cargado para esta orden de compra de insumos");
					}
					else{
						guardarRemitoExternoFinal();
					}
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
				}
			});
			

			
			
			
		}
		else{
			if(entro == true){
				if(vCantRecibida1)
					Window.alert("La cantidad recibida para un insumo seleccionado nunca puede ser nula");
				else if(!vCantRecibida2)
					Window.alert("La cantidad recibida debe de ser un número");
				else if(vCantRecibida3)
					Window.alert("La cantidad recibida NO puede ser mayor a la faltante");
				else if(vNroRemito1)
					Window.alert("Se debe agregar el número del remito");
				else if(!vNroRemito2)
					Window.alert("El número de remito no puede estar compuesto por letras");
			}
		}
		

	}

	protected void guardarRemitoExternoFinal() {
		
		
		
		RemitoExternoDTO remito = new RemitoExternoDTO();
	
		remito.setEmpleado(this.usuario);
		remito.setFechaIngreso(new Date());
		remito.setIdOrdenCompra(ordenCompraInsumo.getIdOrden());
		final Long idOrden = remito.getIdOrdenCompra();
		remito.setObservaciones(this.observacionesTa.getText());
		remito.setIdRemitoEx(new Long(this.nroRemitoExTb.getText()));
		
		for (int i = 1; i < tablaElementos.getRowCount(); i++) {
			
			if(((CheckBox)tablaElementos.getWidget(i, COL_CHECK)).getValue() == true){

				
				
				Integer cant_recibida = new Integer(((TextBox) tablaElementos.getWidget(i, COL_CANT_RECIBIDA)).getText());
				
				
				if(cant_recibida != 0){					
					InsumoDTO insu = new InsumoDTO();
					insu.setNombre(((Label) tablaElementos.getWidget(i, COL_INSUMO)).getText());
					insu.setMarca(((Label) tablaElementos.getWidget(i, COL_MARCA)).getText());
					
					RenglonRemitoExternoDTO renglon = new RenglonRemitoExternoDTO();
					renglon.setItem(i);
					renglon.setInsumo(insu);
					renglon.setCantIngresada(new Double(((TextBox) tablaElementos.getWidget(i, COL_CANT_RECIBIDA)).getText()));
					renglon.setCantFaltante(new Double(((Label) tablaElementos.getWidget(i, COL_CANT_FALTANTE)).getText()));

					remito.getRenglonRemitoExterno().add(renglon);				
				}
			}
		}	

		AlmacenServiceAsync almacenService = GWT.create(AlmacenService.class);
		almacenService.registrarRemitoExterno(remito, new AsyncCallback<Boolean>() {
					
			@Override
			public void onSuccess(Boolean result) {
				if(result){
					Window.alert("El Remito Externo ha sido guardado de manera exitosa");
					verificarSiEstaCompleta(idOrden);
					cancelar();
				}
				else{
					Window.alert("No se ha podido guardar el remito externo");
					cancelar();
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
	}
	
	protected void verificarSiEstaCompleta(Long idOrden){
		
		final Long idOrdenP = idOrden;
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.ordenDeComprasCompleta(idOrden, new AsyncCallback<Boolean>() {
	
			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					cambiarEstadoARecibidaCompleta(idOrdenP);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");

			}

		});		
	}
	
	protected void cambiarEstadoARecibidaCompleta(Long idOrden){
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.recibidaCompletaOrdenCompraInsumo(idOrden, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					Window.alert("La orden de compra de insumos correspondiente al remito externo ingresado a pasado al estado \"cerrada completa\"");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");

			}

		});	
		
	}
	
	private void cargarTabla(OrdenCompraInsumoDTO ordenInsumo){
				
		int item = 1;
		
		for (RenglonOrdenCompraInsumoDTO renglon : ordenInsumo.getRenglonOrdenCompraInsumos()) {
			
			final RenglonOrdenCompraInsumoDTO ren = renglon;
			final int itemInterno = item;
			
			AlmacenServiceAsync almacenService = GWT.create(AlmacenService.class);
			almacenService.getCantFaltanteInsumo(renglon.getInsumo(), ordenInsumo.getIdOrden(), new AsyncCallback<Double>() {
		
				@Override
				public void onSuccess(Double result) {
					setCantFaltante(result.intValue());
					TextBox cantRecibidaTb = new TextBox();
					CheckBox check = new CheckBox();			
			
					tablaElementos.setWidget(itemInterno, COL_INSUMO, new Label(ren.getInsumo().getNombre()));
					tablaElementos.setWidget(itemInterno, COL_MARCA, new Label(ren.getInsumo().getMarca()));
					tablaElementos.setWidget(itemInterno, COL_CANT_PEDIDA, new Label(""+ren.getCantidad()));		
					tablaElementos.setWidget(itemInterno, COL_CANT_FALTANTE, new Label(""+getCantFaltante()));
					tablaElementos.setWidget(itemInterno, COL_CANT_RECIBIDA, cantRecibidaTb);
					tablaElementos.setWidget(itemInterno, COL_CHECK, check);
					tablaElementos.getRowFormatter().setStyleName(itemInterno, "tablaRenglon");
				
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");

				}
			});
			
			item++;
			
		}
		
	}
	
	private void cargarTabla(RemitoExternoDTO remito){
		
		int item = 1;
		
		for (RenglonRemitoExternoDTO renglon : remito.getRenglonRemitoExterno()) {
			
			
			tablaElementos.setWidget(item, COL_INSUMO_RESULT, new Label(renglon.getInsumo().getNombre()));
			tablaElementos.setWidget(item, COL_MARCA_RESULT, new Label(renglon.getInsumo().getMarca()));
			tablaElementos.setWidget(item, COL_CANT_RECIBIDA_RESULT, new Label(renglon.getCantIngresada()+""));	
			tablaElementos.getRowFormatter().setStyleName(item, "tablaRenglon");
			
			item++;
			
		}
		
	}
	
	private void setCantFaltante(int cant){
		this.cantFaltante = cant;
	}
	
	private int getCantFaltante(){
		return this.cantFaltante;
	}
	
	protected void cancelar() {
		this.hide();
	}

	protected void salir() {
		this.hide();
	}






}
