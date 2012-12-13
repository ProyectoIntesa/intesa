package edu.client;

import java.util.Date;

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

import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RemitoProvisionInsumoDTO;
import edu.shared.DTO.RenglonOrdenProvisionInsumoDTO;
import edu.shared.DTO.RenglonRemitoProvisionInsumoDTO;

public class P_RemitoProvisionInsumo  extends PopupPanel{
	
	private static final int COL_ITEM = 0;
	private static final int COL_INSUMO = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_CANT = 3;
	private static final int COL_CANTFAL = 4;
	private static final int COL_CANTENT = 5;
	private static final int COL_CHECK = 6;
	
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
	private Button generarRemito;
	private Button salir;
	private Button cerrarRemito;
	
	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	
	private TextArea observacionesDelRemitoTa;
	
	private OrdenProvisionInsumoDTO orden;
	private boolean accionSalir;
	
	private String usuario;
	private RemitoProvisionInsumoDTO remitoLocal;
	
	public P_RemitoProvisionInsumo(RemitoProvisionInsumoDTO remito){
		
		super(false);
		this.remitoLocal = remito;
		setStyleName("fondoPopup");
		accionSalir = false;
		
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
		
		cerrarRemito = new Button(constante.cerrarRemito());
		cerrarRemito.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//guardarRemitoProvisionInterno();
			}
		});
		
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
		panel.setWidget(2, 2, fechaCierre);
		
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
			
		panel.setWidget(10, 1, this.cerrarRemito);
		panel.getCellFormatter().setHorizontalAlignment(10, 1, HasHorizontalAlignment.ALIGN_CENTER);
		panel.setWidget(10, 2, this.salir);
		panel.getCellFormatter().setHorizontalAlignment(10, 2, HasHorizontalAlignment.ALIGN_CENTER);

		cargarTablaRemito(remito);
		
		setWidget(panel);	
			
	}
	
	public P_RemitoProvisionInsumo(OrdenProvisionInsumoDTO orden, String usuarioLogueado){
		
		super(false);	
		this.orden = orden;
		this.usuario = usuarioLogueado;
		setStyleName("fondoPopup");
		accionSalir = false;
		
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
		
		generarRemito = new Button(constante.generarRemito());
		generarRemito.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				guardarRemitoProvisionInterno();
			}
		});
		
		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_ITEM, constante.item());
		tablaElementos.getCellFormatter().setWidth(0, COL_ITEM, "5%");
		tablaElementos.setText(0, COL_INSUMO, constante.insumo());
		tablaElementos.getCellFormatter().setWidth(0, COL_INSUMO, "13%");
		tablaElementos.setText(0, COL_MARCA, constante.marca());
		tablaElementos.getCellFormatter().setWidth(0, COL_MARCA, "13%");
		tablaElementos.setText(0, COL_CANT, constante.cantPedida());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANT, "10%");
		tablaElementos.setText(0, COL_CANTFAL, constante.cantFaltanteAEntregar());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANTFAL, "16%");
		tablaElementos.setText(0, COL_CANTENT, constante.cantASerEnviada());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANTENT, "13%");
		tablaElementos.setText(0, COL_CHECK, "");
		tablaElementos.getCellFormatter().setWidth(0, COL_CHECK, "5%");
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
			
		panel.setWidget(11, 1, cancelar);
		panel.getCellFormatter().setHorizontalAlignment(11, 1, HasHorizontalAlignment.ALIGN_CENTER);
		panel.setWidget(11, 2, generarRemito);
		panel.getCellFormatter().setHorizontalAlignment(11, 2, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		
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
		
	protected void guardarRemitoProvisionInterno() {
		
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
		produccionService.registrarRemitoProvisionInsumo(remito, new AsyncCallback<Boolean>(){
			
			@Override
			public void onSuccess(Boolean result) {
				if(result == true)
					Window.alert("El Remito de la Orden de Provision de Insumos ha sido generado de manera exitosa");
				else
					Window.alert("El Remito de la Orden de Provision de Insumos NO ha sido generado");
				cancelar();
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
				
			}
		});
		
	}

	protected void cargarTabla(OrdenProvisionInsumoDTO result) {
		
		int item = 1;
		
		System.out.println("comenzo el metodo de cargar la tabla");
		
		for (RenglonOrdenProvisionInsumoDTO renglon : result.getRenglonOrdenProvisionInsumos()) {
		
			final RenglonOrdenProvisionInsumoDTO ren = renglon;
			final int itemInterno = item;
			
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.getCantFaltanteInsumo(renglon.getInsumo(), result.getIdOrdenProvisionInsumo(), new AsyncCallback<Double>() {
		
				@Override
				public void onSuccess(Double result) {

					TextBox cantEntregadaTb = new TextBox();
					CheckBox check = new CheckBox();	
					
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
	

}
