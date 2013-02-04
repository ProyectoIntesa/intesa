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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.OrdenCompraInsumoDTO;

public class P_BuscarOrdenesCompra extends PopupPanel {

	TabPanel padre;

	private Constantes constante = GWT.create(Constantes.class);
	
	private static final int COL_NRO_ORDEN = 0;
	private static final int COL_EMPLEADO_POR = 1;
	private static final int COL_PROVEEDOR = 2;
	private static final int COL_TIPO_ORDEN = 3;
	private static final int COL_ESTADO = 4;
	private static final int COL_MAS_INFO = 5;

	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	private FlexTable botones1;
	private FlexTable botones2;

	private Label proveedor;
	private Label tipo_orden;
	private Label estado;
	private Label fechaDesde;
	private Label fechaHasta;
	
	private ListBox proveedorLb;
	private ListBox tipo_ordenLb;
	private ListBox estadoLb;
	
	private Label buscarPor;
	private Label segunFechaEdicion;
	private Label pie;
	
	private Button buscar; 
	private Button salir;
	
	private CheckBox fechaCb;
	
	private DateBox fechaDesdeDb;
	private DateBox fechaHastaDb;
	private FlexTable impresion;
	List<OrdenCompraInsumoDTO> ordenes;

	private boolean CerrarOrden;
	private boolean SalirOrden;
	private boolean CancelarOrden;
	private boolean ImprimirOrden;
	
	
	
	public P_BuscarOrdenesCompra() {

		super(false);

		setStyleName("fondoPopup");
		
		CerrarOrden = false;
		SalirOrden = false;
		CancelarOrden = false;
		ImprimirOrden = false;
		
		
		
		fechaCb = new CheckBox(constante.fechaEdicion());
		fechaCb.setStyleName("check");
		fechaCb.setWordWrap(false);
		fechaCb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionCheck();
			}
		});
		
		proveedor = new Label(constante.proveedor());
		proveedor.setStyleName("gwt-LabelFormulario");
		tipo_orden = new Label(constante.tipoOrden());
		tipo_orden.setStyleName("gwt-LabelFormulario");
		estado = new Label(constante.estado());
		estado.setStyleName("gwt-LabelFormulario");
		fechaDesde = new Label(constante.desde());
		fechaDesde.setStyleName("gwt-LabelFormulario");
		fechaHasta = new Label(constante.hasta());
		fechaHasta.setStyleName("gwt-LabelFormulario");
		buscarPor = new Label(constante.buscarPor());
		buscarPor.setStyleName("labelTitulo");
		segunFechaEdicion = new Label(constante.segunFechaDeEdicion());
		segunFechaEdicion.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
				
		proveedorLb = new ListBox();
		proveedorLb.setStyleName("gwt-TextArea");
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

		comprasService.getNombresEmpresas(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaProveedores(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
		
		tipo_ordenLb = new ListBox();
		tipo_ordenLb.setStyleName("gwt-TextArea");
		
		tipo_ordenLb.addItem(constante.ordenCompraDeInsumo());
		tipo_ordenLb.addItem(constante.ordenCompraDeProducto());
		tipo_ordenLb.addItem(constante.ordenCotizacionDeProducto());
		
		
		estadoLb = new ListBox();
		estadoLb.setStyleName("gwt-TextArea");
		
		comprasService.getNombreEstados(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaEstados(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
		fechaDesdeDb = new DateBox();
		fechaDesdeDb.setEnabled(false);
		fechaDesdeDb.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		fechaHastaDb = new DateBox();
		fechaHastaDb.setEnabled(false);
		fechaHastaDb.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_NRO_ORDEN, constante.nroOrden());
		tablaElementos.getCellFormatter().setWidth(0, COL_NRO_ORDEN, "18%");
		tablaElementos.setText(0, COL_EMPLEADO_POR, constante.empleado());
		tablaElementos.getCellFormatter().setWidth(0, COL_EMPLEADO_POR, "18%");
		tablaElementos.setText(0, COL_PROVEEDOR, constante.proveedor());
		tablaElementos.getCellFormatter().setWidth(0, COL_PROVEEDOR, "18%");
		tablaElementos.setText(0, COL_TIPO_ORDEN, constante.tipoOrden());
		tablaElementos.getCellFormatter().setWidth(0, COL_TIPO_ORDEN, "18%");
		tablaElementos.setText(0, COL_ESTADO, constante.estado());
		tablaElementos.getCellFormatter().setWidth(0, COL_ESTADO, "18%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "10%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");

		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		buscar = new Button(constante.buscar());
		buscar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscar();
			}
		});
	
		botones1 = new FlexTable();
		botones1.setWidget(0, 0, buscar);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		botones2 = new FlexTable();
		botones2.setWidget(0, 0, salir);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		panel.setWidget(0, 0, buscarPor);
		panel.getFlexCellFormatter().setColSpan(0, 0, 6);

		panel.setWidget(1, 0, tipo_orden);
		panel.setWidget(1, 1, tipo_ordenLb);
		panel.setWidget(1, 2, estado);
		panel.setWidget(1, 3, estadoLb);
		panel.setWidget(1, 4, proveedor);
		panel.setWidget(1, 5, proveedorLb);
		
		panel.setWidget(2, 0, segunFechaEdicion);
		panel.getFlexCellFormatter().setColSpan(2, 0, 6);
		
		panel.setWidget(3, 0, fechaCb);
		panel.getFlexCellFormatter().setColSpan(3, 0, 1);
		panel.setWidget(3, 2, fechaDesde);
		panel.setWidget(3, 3, fechaDesdeDb);
		panel.setWidget(3, 4, fechaHasta);
		panel.setWidget(3, 5, fechaHastaDb);
		
		panel.setWidget(4, 0, botones1);
		panel.getFlexCellFormatter().setColSpan(4, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
						
		panel.setWidget(5, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(5, 0, 6);
		
		panel.setWidget(6, 0, pie);
		panel.getFlexCellFormatter().setColSpan(6, 0, 6);

		panel.setWidget(7, 0, botones2);
		panel.getFlexCellFormatter().setColSpan(7, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	
		
		setWidget(panel);
		
	}
	
	protected void buscar() {
		
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		switch (tipo_ordenLb.getSelectedIndex()){
		
			case 0: {
				
				String unEstado = estadoLb.getItemText(estadoLb.getSelectedIndex());
				String unProv = proveedorLb.getItemText(proveedorLb.getSelectedIndex());
							
				String fecDesde = "";
				String fecHasta = "";
				
				if(fechaCb.getValue() == true){
								
					fecDesde = fechaDesdeDb.getTextBox().getText();
					fecHasta = fechaHastaDb.getTextBox().getText();	
					
					if(fecDesde.compareTo("") == 0 || fecHasta.compareTo("") == 0){
						
						Window.alert("Al filtrar la búsqueda por fechas, es obligatorio el ingreso de ambas fechas");
					}
					else{
						
						Date fecDesdeDate = new Date();
						fecDesdeDate = fechaDesdeDb.getValue();
						Date fecHastaDate = new Date();
						fecHastaDate = fechaHastaDb.getValue();
						
						if(fecDesdeDate.before(fecHastaDate) == true){
							
							comprasService.getOrdenCompraInsumo(unEstado, unProv, fecDesde, fecHasta, new AsyncCallback<List<OrdenCompraInsumoDTO>>() {
								
								@Override
								public void onSuccess(List<OrdenCompraInsumoDTO> result) {
									cargarOrdenesCompraInsumo(result);
								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("ERROR EN EL SERVICIO");
								}
							});
						}
						else{
							Window.alert("La fecha \"desde\" debe ser menor a la fecha \"hasta\"");
						}
							
					}
				}
				else{
					comprasService.getOrdenCompraInsumo(unEstado, unProv, fecDesde, fecHasta, new AsyncCallback<List<OrdenCompraInsumoDTO>>() {
						
						@Override
						public void onSuccess(List<OrdenCompraInsumoDTO> result) {
							cargarOrdenesCompraInsumo(result);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");
						}
					});
				}
				break;
			}
			case 1: {
				tablaElementos.clear();
				Window.alert("Momentaneamente no se encuentra disponible");
				break;
			}
			case 2: {
				tablaElementos.clear();
				Window.alert("Momentaneamente no se encuentra disponible");
				break;
			}
			default: {
				Window.alert("La opción seleccionada no corresponde a ninguna función");
			}
			
		}
		
		
		
		
		
		
		
	}

	protected void cargarOrdenesCompraInsumo(List<OrdenCompraInsumoDTO> result) {
		
		this.tablaElementos.clear();
		ordenes = new LinkedList<OrdenCompraInsumoDTO>();
		ordenes = result;
		
		for (int i = 0; i < result.size(); i++) {

			
			Label infoC = new Label("");
			infoC.setSize("16px", "16px");
			infoC.setStyleName("labelInfo");
			
			tablaElementos.setWidget(i + 1, COL_NRO_ORDEN, new Label(result.get(i).getNroOrden()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_NRO_ORDEN, true);
			tablaElementos.setWidget(i + 1, COL_EMPLEADO_POR, new Label(result.get(i).getEmpleado()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_EMPLEADO_POR, true);
			tablaElementos.setWidget(i + 1, COL_PROVEEDOR, new Label(result.get(i).getProveedor()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_PROVEEDOR, false);
			tablaElementos.setWidget(i + 1, COL_TIPO_ORDEN, new Label(constante.ordenCompraDeInsumo()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_TIPO_ORDEN, true);
			tablaElementos.setWidget(i + 1, COL_ESTADO, new Label(result.get(i).getEstadoOrden()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_ESTADO, true);			
			tablaElementos.setWidget(i + 1, COL_MAS_INFO, infoC);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_MAS_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementos.getRowFormatter().setStyleName(i + 1, "tablaRenglon");
			infoC.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Cell celda = tablaElementos.getCellForEvent(event);
					long idOrden= ordenes.get(celda.getRowIndex() - 1).getIdOrden();
					
					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

					comprasService.getOrdenCompraInsumoSegunId(idOrden,new AsyncCallback<OrdenCompraInsumoDTO>() {
						@Override
						public void onSuccess(OrdenCompraInsumoDTO result) {
							
							final P_DetalleOrdenCompraInsumo detalle = new P_DetalleOrdenCompraInsumo(result, "COMPRAS");		
							detalle.setGlassEnabled(true);
							detalle.center();
							detalle.show();							
							detalle.addCloseHandler(new CloseHandler<PopupPanel>() {

								@Override
								public void onClose(CloseEvent<PopupPanel> event) {
										
									boolean cierreOrdenCerrar = detalle.getCierreOrdenCerrar();
									boolean cierreOrdenSalir = detalle.getCierreOrdenSalir();
									boolean cierreOrdenCancelar = detalle.getCierreOrdenCancelar();
									boolean cierreOrdenImprimir = detalle.getCierreOrdenImprimir();
									
									if (cierreOrdenSalir == true)
									{								
									}
									else if(cierreOrdenImprimir == true){
										impresion = detalle.armarImpresion();
										ImprimirOrden = true;
										salir();
									}
									else if (cierreOrdenCerrar == true){
										buscar();
									}
									else if (cierreOrdenCancelar == true){
										buscar();
									}
								}
							});
							
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");
						}
					});
					
					
					
					
					
					

					

				}
			});

		}
		
	}

	protected void cargarSugerenciaProveedores(List<String> result) {

		proveedorLb.addItem("TODOS");
		
		for (String sugerencia : result) {
			proveedorLb.addItem(sugerencia);
		}

	}

	protected void cargarSugerenciaEstados(List<String> result) {

		estadoLb.addItem("TODOS");
		
		for (String sugerencia : result) {
			if(sugerencia.compareTo("EDICION")!=0)
			estadoLb.addItem(sugerencia);
		}

	}
	
	protected void seleccionCheck() {
		if(fechaCb.getValue()==true){
			fechaDesdeDb.setEnabled(true);
			fechaHastaDb.setEnabled(true);
		}
			
		else{
			fechaDesdeDb.setEnabled(false);
			fechaDesdeDb.getTextBox().setText("");
			fechaHastaDb.setEnabled(false);
			fechaHastaDb.getTextBox().setText("");
		}
	}
	
	protected void salir() {
		this.hide();

	}
	
	public boolean getImprimirOrden(){
	 return this.ImprimirOrden;
	}
	
	public boolean getCerrarOrden(){
		 return this.CerrarOrden;
		}
	
	public boolean getCancelarOrden(){
		 return this.CancelarOrden;
		}
	
	public boolean getSalirOrden(){
		 return this.SalirOrden;
		}
	
	public FlexTable formulario(){
		return this.impresion;
	}
	

}
