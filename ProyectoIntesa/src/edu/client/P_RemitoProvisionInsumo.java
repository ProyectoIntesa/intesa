package edu.client;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.tools.zip.AsiExtraField;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RemitoProvisionInsumoDTO;
import edu.shared.DTO.RenglonOrdenCompraInsumoDTO;
import edu.shared.DTO.RenglonOrdenProvisionInsumoDTO;
import edu.shared.DTO.RenglonRemitoProvisionInsumoDTO;

public class P_RemitoProvisionInsumo  extends PopupPanel{
	
	private static final int COL_ITEM = 0;
	private static final int COL_INSUMO = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_CANT = 3;
	private static final int COL_CANTFAL = 4;
	private static final int COL_CANTDIS = 5;
	private static final int COL_CANTENT = 6;
	private static final int COL_CHECK = 7;
	
	private static final int COL_INSUMO2 = 0;
	private static final int COL_MARCA2 = 1;
	private static final int COL_CANTENV2 = 2;
	
	private Constantes constante = GWT.create(Constantes.class);

	TabPanel padre;
	
	private Label tituloFormulario;
	private Label lineaTabla;
	private Label observacionesDeOrdenProvisionInsumo;
	private Label observacionesDelRemito;
	private Label pie;
	
	private Label nroOrden;
	private Label nroRemito;
	private Label empleadoPor;
	private Label empleadoPara;
	private Label fechaGeneracion;
	private Label fechaCierre;
	private Label estado;
	private Label observacion;
	
	private Button cancelar;
	private Button imprimirRemito;
	private Button salir;
	private Button salir1;
	private Button cerrarRemito;
	
	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	private FlexTable botones;
	private FlexTable botones1;
	private FlexTable ordenImprimir;
	private boolean imprimir;
	
	private TextArea observacionesDelRemitoTa;
	
	private OrdenProvisionInsumoDTO orden;
	private boolean accionSalir;
	
	//------------para el formulario de impresion
	
	private Label tituloFormularioImpresion;
	private Label observacionesImpresion;
	private Label lineaInfoImpresion;
	private Label pieImpresion;
		
	//-------------------------------------------
	
	private String usuario;
	private RemitoProvisionInsumoDTO remitoLocal;
	
	public P_RemitoProvisionInsumo(RemitoProvisionInsumoDTO remito, String accion){
		
		super(false);
		this.remitoLocal = remito;
		setStyleName("fondoPopup");
		accionSalir = false;
		String accionRealizar = accion;
		
		tituloFormulario = new Label("REMITO DE PROVISION DE INSUMOS BUSCADO");
		tituloFormulario.setStyleName("labelTitulo");
		lineaTabla = new Label();
		lineaTabla.setStyleName("labelTitulo");
		observacionesDelRemito = new Label(constante.observacionesDelRemito());
		observacionesDelRemito.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		observacionesDelRemitoTa = new TextArea();
		observacionesDelRemitoTa.setDirectionEstimator(false);
		observacionesDelRemitoTa.setWidth("99%");
		observacionesDelRemitoTa.setText(remitoLocal.getObservaciones());
		
		nroOrden = new Label(constante.nroOrden()+": "+remitoLocal.getIdOrdenProvisionInsumo());
		nroOrden.setStyleName("gwt-LabelFormulario");
		nroRemito = new Label(constante.nroRemito()+": "+remitoLocal.getIdRemitoInsumo());
		nroRemito.setStyleName("gwt-LabelFormulario");
		empleadoPor = new Label(constante.generadoPor()+": "+remitoLocal.getEmpleado());
		empleadoPor.setStyleName("gwt-LabelFormulario");
		
		DateTimeFormat fmtDate1 = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fechaG = fmtDate1.format(remitoLocal.getFechaGenaracion());
		
		fechaGeneracion = new Label(constante.fechaGeneracion()+": "+fechaG);
		fechaGeneracion.setStyleName("gwt-LabelFormulario");
		
		String fechaC = "";
		if(remitoLocal.getFechaCierre()!=null){

			DateTimeFormat fmtDate2 = DateTimeFormat.getFormat("dd/MM/yyyy");
			fechaC = fmtDate2.format(remitoLocal.getFechaCierre());			
		}

		fechaCierre = new Label(constante.fechaCierre()+": "+fechaC);
		fechaCierre.setStyleName("gwt-LabelFormulario");
		

		estado = new Label(constante.estado()+": "+remitoLocal.getEstadoOrden());
		estado.setStyleName("gwt-LabelFormulario");
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				accionSalir = true;
				cancelar();
			}
		});
		
		salir1 = new Button(constante.salir());
		salir1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				accionSalir = true;
				cancelar();
			}
		});
		
		cerrarRemito = new Button(constante.cerrarRemito());
		cerrarRemito.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cerrarRemitoProvisionInterno();
			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, cerrarRemito);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, salir1);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		botones1 = new FlexTable();
		botones1.setWidget(0, 0, salir);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_INSUMO2, constante.insumo());
		tablaElementos.getCellFormatter().setWidth(0, COL_INSUMO2, "30%");
		tablaElementos.setText(0, COL_MARCA2, constante.marca());
		tablaElementos.getCellFormatter().setWidth(0, COL_MARCA2, "30%");
		tablaElementos.setText(0, COL_CANTENV2, constante.cantEnviada());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANTENV2, "30%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		panel.setWidget(0, 0, tituloFormulario);
		panel.getFlexCellFormatter().setColSpan(0, 0, 4);

		panel.setWidget(1, 0, nroOrden);
		panel.setWidget(1, 1, nroRemito);
		panel.setWidget(1, 2, estado);
		
		panel.setWidget(2, 0, empleadoPor);
		panel.setWidget(2, 1, fechaGeneracion);
		if(accionRealizar.compareTo("buscar")==0){
			panel.setWidget(2, 2, fechaCierre);
		}
		
		
		panel.setWidget(3, 0, lineaTabla);
		panel.getFlexCellFormatter().setColSpan(3, 0, 3);
		
		panel.setWidget(4, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(4, 0, 3);
				
		panel.setWidget(5, 0, observacionesDelRemito);
		panel.getFlexCellFormatter().setColSpan(5, 0, 3);
		
		panel.setWidget(6, 0, observacionesDelRemitoTa);
		panel.getFlexCellFormatter().setColSpan(6, 0, 3);		
		
		panel.setWidget(9, 0, pie);
		panel.getFlexCellFormatter().setColSpan(9, 0, 3);
			
		if(accionRealizar.compareTo("buscar")==0){
			panel.setWidget(10, 0, botones1);
			panel.getFlexCellFormatter().setColSpan(10, 0, 3);
			panel.getCellFormatter().setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		}
		if(accionRealizar.compareTo("cerrar")==0){
			panel.setWidget(10, 0, botones);
			panel.getFlexCellFormatter().setColSpan(10, 0, 3);
			panel.getCellFormatter().setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		}
		

		

		


		cargarTablaRemito(remito);
		
		setWidget(panel);	
			
	}
	
	public P_RemitoProvisionInsumo(OrdenProvisionInsumoDTO orden, String usuarioLogueado){
		
		super(false);	
		this.orden = orden;
		this.usuario = usuarioLogueado;
		setStyleName("fondoPopup");
		accionSalir = false;
		imprimir = false;
		ordenImprimir = new FlexTable();
		
		//-----se inicializan objetos de impresion
		
		tituloFormularioImpresion = new Label("REMITO PROVISION DE INSUMO");
		tituloFormularioImpresion.setStyleName("labelTitulo");	
		
		lineaInfoImpresion = new Label("");
		lineaInfoImpresion.setStyleName("labelTitulo");
		
		observacionesImpresion = new Label("OBSERVACIONES");
		observacionesImpresion.setStyleName("labelTitulo");
		
		pieImpresion = new Label("");
		pieImpresion.setStyleName("labelTitulo");
		
		
		//----------------------------------------
		
		
		
		tituloFormulario = new Label("GENERAR REMITO PARA LA SIGUIENTE ORDEN DE PROVISION DE INSUMOS");
		tituloFormulario.setStyleName("labelTitulo");
		lineaTabla = new Label();
		lineaTabla.setStyleName("labelTitulo");
		observacionesDeOrdenProvisionInsumo = new Label(constante.observacionesDeLaOrdenDeProvisionDeInsumos());
		observacionesDeOrdenProvisionInsumo.setStyleName("labelTitulo");
		observacionesDelRemito = new Label(constante.observacionesDelRemito());
		observacionesDelRemito.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		observacionesDelRemitoTa = new TextArea();
		observacionesDelRemitoTa.setDirectionEstimator(false);
		observacionesDelRemitoTa.setWidth("99%");
		
		nroOrden = new Label(constante.nroOrden()+": "+orden.getIdOrdenProvisionInsumo());
		nroOrden.setStyleName("gwt-LabelFormulario");
		empleadoPor = new Label(constante.generadoPor()+": "+orden.getEmpleadoByIdPedidoPor().getApellido()+", "+orden.getEmpleadoByIdPedidoPor().getNombre());
		empleadoPor.setStyleName("gwt-LabelFormulario");
		empleadoPara = new Label(constante.generadoPara()+": "+orden.getEmpleadoByIdPedidoPara().getApellido()+", "+orden.getEmpleadoByIdPedidoPara().getNombre());
		empleadoPara.setStyleName("gwt-LabelFormulario");
		
		DateTimeFormat fmtDate1 = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fechaG = fmtDate1.format(orden.getFechaGeneracion());
		
		fechaGeneracion = new Label(constante.fechaGeneracion()+": "+fechaG);
		fechaGeneracion.setStyleName("gwt-LabelFormulario");
		
		String fechaC = "";
		if(orden.getFechaCierre()!=null){

			DateTimeFormat fmtDate2 = DateTimeFormat.getFormat("dd/MM/yyyy");
			fechaC = fmtDate2.format(orden.getFechaCierre());			
		}
	

		estado = new Label(constante.estado()+": "+orden.getEstadoOrden());
		estado.setStyleName("gwt-LabelFormulario");

		observacion = new Label(orden.getObservaciones());
		observacion.setStyleName("gwt-LabelFormulario");
		
				
		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				accionSalir = true;
				cancelar();
			}
		});
		
		imprimirRemito = new Button(constante.imprimir());
		imprimirRemito.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				guardarRemitoProvisionInterno();						
			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, imprimirRemito);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, cancelar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_ITEM, constante.item());
		tablaElementos.getCellFormatter().setWidth(0, COL_ITEM, "3%");
		tablaElementos.setText(0, COL_INSUMO, constante.insumo());
		tablaElementos.getCellFormatter().setWidth(0, COL_INSUMO, "12%");
		tablaElementos.setText(0, COL_MARCA, constante.marca());
		tablaElementos.getCellFormatter().setWidth(0, COL_MARCA, "12%");
		tablaElementos.setText(0, COL_CANT, constante.cantPedida());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANT, "10%");
		tablaElementos.setText(0, COL_CANTFAL, constante.cantFaltanteAEntregar());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANTFAL, "13%");
		tablaElementos.setText(0, COL_CANTDIS, constante.cantDisponible());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANTDIS, "10%");
		tablaElementos.setText(0, COL_CANTENT, constante.cantASerEnviada());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANTENT, "12%");
		tablaElementos.setText(0, COL_CHECK, "");
		tablaElementos.getCellFormatter().setWidth(0, COL_CHECK, "3%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		panel.setWidget(0, 0, tituloFormulario);
		panel.getFlexCellFormatter().setColSpan(0, 0, 4);

		panel.setWidget(1, 0, nroOrden);
		panel.setWidget(1, 1, fechaGeneracion);
		panel.setWidget(1, 2, estado);
		
		panel.setWidget(2, 0, empleadoPor);
		
		panel.setWidget(3, 0, empleadoPara);

		panel.setWidget(4, 0, lineaTabla);
		panel.getFlexCellFormatter().setColSpan(4, 0, 3);
		
		panel.setWidget(5, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(5, 0, 3);
				
		panel.setWidget(6, 0, observacionesDeOrdenProvisionInsumo);
		panel.getFlexCellFormatter().setColSpan(6, 0, 3);
		
		panel.setWidget(7, 0, observacion);
		panel.getFlexCellFormatter().setColSpan(7, 0, 3);
		
		panel.setWidget(8, 0, observacionesDelRemito);
		panel.getFlexCellFormatter().setColSpan(8, 0, 3);
		
		panel.setWidget(9, 0, observacionesDelRemitoTa);
		panel.getFlexCellFormatter().setColSpan(9, 0, 3);		
		
		panel.setWidget(10, 0, pie);
		panel.getFlexCellFormatter().setColSpan(10, 0, 3);
			
		panel.setWidget(11, 0, botones);
		panel.getFlexCellFormatter().setColSpan(11, 0, 3);
		panel.getCellFormatter().setHorizontalAlignment(11, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.getOrdenProvisionInsumoSegunId(orden.getIdOrdenProvisionInsumo(), new AsyncCallback<OrdenProvisionInsumoDTO>() {

			@Override
			public void onSuccess(OrdenProvisionInsumoDTO result) {
				cargarTabla(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");

			}
		});

		setWidget(panel);	
		
	}
	
	protected void cerrarRemitoProvisionInterno() {
			
		boolean confirm = Window.confirm("Está seguro que desea cerrar el remito?");
		if (confirm == true){
			
			DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
			String fecha = fmtDate.format(new Date());
			
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.cerrarRemitoProvisionInsumos(remitoLocal,fecha, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
						Window.alert("El remito ha sido cerrado");
						cerrarAutomaticamenteOrdenProvisionInsumo();
					}
					else
						Window.alert("El remito NO ha sido cerrado");
					cancelar();
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR DE SERVICIO");

				}
			});
		}

		
	}		
	
	protected FlexTable armarImpresion(){

		FlexTable formulario = new FlexTable();
		
		formulario.setWidget(1, 0, tituloFormularioImpresion);
		formulario.getFlexCellFormatter().setColSpan(1, 0, 3);
		
		formulario.setWidget(2, 0, new Label(constante.nroOrdenProvision()+": "+this.orden.getIdOrdenProvisionInsumo()));
		formulario.setWidget(2, 1, new Label(constante.nroDeRemito()+": "+this.remitoLocal.getIdRemitoInsumo()));
		
		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(this.orden.getFechaGeneracion());
		formulario.setWidget(2, 2, new Label(constante.fechaGeneracion()+": "+fecha));		
				
		formulario.setWidget(3, 0, new Label(constante.generadoPor()+": "+this.orden.getEmpleadoByIdPedidoPor().getNombre()+" "+this.orden.getEmpleadoByIdPedidoPor().getApellido()));
		
		formulario.setWidget(4, 0, new Label(constante.generadoPara()+": "+this.orden.getEmpleadoByIdPedidoPara().getNombre()+" "+this.orden.getEmpleadoByIdPedidoPara().getApellido()));
		
		formulario.setWidget(5, 0, lineaInfoImpresion);
		formulario.getFlexCellFormatter().setColSpan(5, 0, 3);

		
		
		formulario.setWidget(7, 0, observacionesImpresion);
		formulario.getFlexCellFormatter().setColSpan(7, 0, 3);

		formulario.setWidget(8, 0, new Label(this.remitoLocal.getObservaciones()));
		formulario.getFlexCellFormatter().setColSpan(8, 0, 3);
		
		formulario.setWidget(9, 0, pieImpresion);
		formulario.getFlexCellFormatter().setColSpan(9, 0, 3);
		
		int cantidad = orden.getRenglonOrdenProvisionInsumos().size();
		
		ScrollPanel tabla = new ScrollPanel();
		tabla.setStyleName("tabla");
		if (cantidad > 5)
			tabla.setHeight(cantidad*22+" px");
		else
			tabla.setHeight("400 px");
		
		FlexTable elementos = new FlexTable();
		tabla.setWidget(elementos);
		elementos.setSize("100%", "100%");
		elementos.setText(0, COL_ITEM, constante.item());
		elementos.getCellFormatter().setWidth(0, COL_ITEM, "16%");
		elementos.setText(0, COL_INSUMO, constante.insumo());
		elementos.getCellFormatter().setWidth(0, COL_INSUMO, "16%");
		elementos.setText(0, COL_MARCA, constante.marca());
		elementos.getCellFormatter().setWidth(0, COL_MARCA, "16%");
		elementos.setText(0, COL_CANT, constante.cantidad());
		elementos.getCellFormatter().setWidth(0, COL_CANT, "16%");
		elementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		int item = 1;
		
		for(RenglonRemitoProvisionInsumoDTO renglon : this.remitoLocal.getRenglonRemitoProvisionInsumos()) {
			
			elementos.setWidget(item, COL_ITEM, new Label("" + renglon.getItem()));
			elementos.setWidget(item, COL_INSUMO, new Label(renglon.getInsumo().getNombre()));
			elementos.setWidget(item, COL_MARCA, new Label(renglon.getInsumo().getMarca()));
			elementos.setWidget(item, COL_CANT, new Label(renglon.getCantidadEntregada() + ""));	
			elementos.getRowFormatter().setStyleName(item, "tablaRenglon2");
			item++;
		}
		
		formulario.setWidget(6, 0, tabla);
		formulario.getFlexCellFormatter().setColSpan(6, 0, 3);
		
		return formulario;
		
	}
	
	protected void guardarRemitoProvisionInterno() {
		
		Validaciones validar = new Validaciones();
		boolean vCantPedida1 = false;
		boolean vCantPedida2 = false;
		boolean vCantPedida3 = false;
		boolean vCantPedida4 = false;
		boolean entro = false;

		
		for(int i = 1; i < tablaElementos.getRowCount(); i++){
			
			if(((CheckBox)tablaElementos.getWidget(i, COL_CHECK)).getValue() == true){
			
				entro = true;
				String cantPedida = ((TextBox)tablaElementos.getWidget(i, COL_CANTENT)).getText();
				vCantPedida1 = validar.textBoxVacio(cantPedida);
				vCantPedida2 = validar.textBoxSoloNumeros(cantPedida);
				
				if(!vCantPedida1 && vCantPedida2){
					
					Float cantPe = Float.parseFloat(((TextBox)tablaElementos.getWidget(i, COL_CANTENT)).getText());
					Float cantFa = Float.parseFloat(((Label)tablaElementos.getWidget(i, COL_CANTFAL)).getText());
					Float cantDi = Float.parseFloat(((Label)tablaElementos.getWidget(i, COL_CANTDIS)).getText());
					
					if(cantPe > cantFa)
						vCantPedida3 = true;
					if(cantPe > cantDi)
						vCantPedida4 = true;
				}
				
			}
			
		}
		
		
		if(!vCantPedida1 && vCantPedida2 && !vCantPedida3 && !vCantPedida4){
			
			RemitoProvisionInsumoDTO remito = new RemitoProvisionInsumoDTO();
			remito.setEmpleado(usuario);
			remito.setEstadoOrden("GENERADA");
			remito.setFechaEdicion(new Date());
			remito.setFechaGenaracion(new Date());
			remito.setObservaciones(this.observacionesDelRemitoTa.getText());
			remito.setIdOrdenProvisionInsumo(orden.getIdOrdenProvisionInsumo());
			
			for(int i = 1; i < tablaElementos.getRowCount(); i++){
				
				if(((CheckBox)tablaElementos.getWidget(i, COL_CHECK)).getValue() == true){
					
					Integer cantAEnviar = new Integer(((TextBox) tablaElementos.getWidget(i, COL_CANTENT)).getText());
					
					if(cantAEnviar != 0){
						InsumoDTO insumo = new InsumoDTO();
						insumo.setNombre(((Label) tablaElementos.getWidget(i, COL_INSUMO)).getText());
						insumo.setMarca(((Label) tablaElementos.getWidget(i, COL_MARCA)).getText());
						
						RenglonRemitoProvisionInsumoDTO renglon = new RenglonRemitoProvisionInsumoDTO();
						renglon.setItem(i);
						renglon.setInsumo(insumo);
						renglon.setCantidadEntregada(new Double(((TextBox) tablaElementos.getWidget(i, COL_CANTENT)).getText()));
						
						remito.getRenglonRemitoProvisionInsumos().add(renglon);		
					}	
				}
			}
						
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.registrarRemitoProvisionInsumo(remito, "", new AsyncCallback<RemitoProvisionInsumoDTO>(){
				
				@Override
				public void onSuccess(RemitoProvisionInsumoDTO result) {
					
					if(result != null){
						remitoLocal = result;
						ordenImprimir = armarImpresion();
						imprimir = true;
					}
					else{
						Window.alert("El Remito de la Orden de Provision de Insumos NO ha sido generado");
					}
					cancelar();
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");					
				}
			});
		}
		else{
			if(entro == true){
				if(vCantPedida1)
					Window.alert("La cantidad pedida para un insumo seleccionado nunca puede ser nula");
				else if(!vCantPedida2)
					Window.alert("La cantidad pedida debe de ser un número");
				else if(vCantPedida3)
					Window.alert("La cantidad pedida NO debe ser mayor a la faltante");
				else if(vCantPedida4)
					Window.alert("La cantidad pedida NO debe ser mayor a la disponible");
			}
		}
				
	}
		
	protected void cargarTabla(OrdenProvisionInsumoDTO result) {
		
		int item = 1;
			
		for (RenglonOrdenProvisionInsumoDTO renglon : result.getRenglonOrdenProvisionInsumos()) {
		
			final RenglonOrdenProvisionInsumoDTO ren = renglon;
			final int itemInterno = item;
		
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			
			produccionService.getCantInsumo(renglon.getInsumo(), new AsyncCallback<Double>(){
				
				@Override
				public void onSuccess(Double result) {
					
					tablaElementos.setWidget(itemInterno,COL_CANTDIS,new Label(""+result));
					
					
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
					
				}
				
			});
			
			
			
			
			produccionService.getCantFaltanteInsumo(renglon.getInsumo(), result.getIdOrdenProvisionInsumo(), new AsyncCallback<Double>() {
		
				@Override
				public void onSuccess(Double result) {

					TextBox cantEntregadaTb = new TextBox();
					CheckBox check = new CheckBox();	
					if(result == 0)
						check.setEnabled(false);
					
					tablaElementos.setWidget(itemInterno, COL_ITEM, new Label("" + ren.getIdRenglon()));
					tablaElementos.setWidget(itemInterno, COL_INSUMO, new Label(ren.getInsumo().getNombre()));
					tablaElementos.setWidget(itemInterno, COL_MARCA, new Label(ren.getInsumo().getMarca()));
					tablaElementos.setWidget(itemInterno, COL_CANT, new Label(ren.getCantidadRequerida()+""));
					tablaElementos.setWidget(itemInterno, COL_CANTFAL, new Label(result+""));
					tablaElementos.setWidget(itemInterno, COL_CANTENT, cantEntregadaTb);
					tablaElementos.setWidget(itemInterno, COL_CHECK, check);
					tablaElementos.getRowFormatter().setStyleName(itemInterno, "tablaRenglon");
				
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR DE SERVICIO");

				}
			});
			
			item++;	
			
		}
		
	}

	protected void cargarTablaRemito(RemitoProvisionInsumoDTO remito){
		
		int item=1;	
		
		for (RenglonRemitoProvisionInsumoDTO renglon : remito.getRenglonRemitoProvisionInsumos()) {	
			tablaElementos.setWidget(item, COL_INSUMO2, new Label(renglon.getInsumo().getNombre()));
			tablaElementos.setWidget(item, COL_MARCA2, new Label(renglon.getInsumo().getMarca()));
			tablaElementos.setWidget(item, COL_CANTENV2, new Label(""+renglon.getCantidadEntregada()));	
			tablaElementos.getRowFormatter().setStyleName(item, "tablaRenglon");
			item++;
		}
	
	}
	
	protected void cancelar() {
		this.hide();

	}

	public boolean getAccionSalir(){
		return this.accionSalir;
	}
		
	protected void cerrarAutomaticamenteOrdenProvisionInsumo(){
		
		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(new Date());
		
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.cerrarOrdenesProvision(fecha, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result)
					Window.alert("Ha sido cerrada la orden de provisión de insumo correspondiente al remito recién cerrado");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
				
			}
		
		});
		
		
		
	}
	
	protected boolean getImprimir(){
		return this.imprimir;
	}
	
	protected FlexTable getOrdenImprimir(){
		return this.ordenImprimir;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
