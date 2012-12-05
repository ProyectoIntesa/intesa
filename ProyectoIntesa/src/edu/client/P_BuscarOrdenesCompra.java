package edu.client;

import java.util.LinkedList;
import java.util.List;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.sun.xml.internal.bind.v2.TODO;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.ContactoDTO;
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
	
	List<OrdenCompraInsumoDTO> ordenes;

	
	public P_BuscarOrdenesCompra() {

		super(false);

		setStyleName("fondoPopup");
		
			
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
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});
		
		
		tipo_ordenLb = new ListBox();
		tipo_ordenLb.setStyleName("gwt-TextArea");
		
		tipo_ordenLb.addItem(constante.todos());
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
				Window.alert("No se ha podido cargar la lista de sugerencias");
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
		
		panel.setWidget(6, 0, buscar);
		panel.getFlexCellFormatter().setColSpan(6, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
				
		panel.setWidget(7, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(7, 0, 6);
		
		panel.setWidget(8, 0, pie);
		panel.getFlexCellFormatter().setColSpan(8, 0, 6);

		panel.setWidget(9, 0, salir);
		panel.getFlexCellFormatter().setColSpan(9, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_CENTER);
	
		
		setWidget(panel);
		
	}
	
	protected void buscar() {
		
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		switch (tipo_ordenLb.getSelectedIndex()){
			case 0: {
				
				break;
			}
			case 1: {
				
				String unEstado = estadoLb.getItemText(estadoLb.getSelectedIndex());
				String unProv = proveedorLb.getItemText(proveedorLb.getSelectedIndex());
				
				String fecDesde = "";
				String fecHasta = "";
				
				if(fechaCb.getValue() == true){
					fecDesde = fechaDesdeDb.getTextBox().getText();
					fecHasta = fechaHastaDb.getTextBox().getText();					
				}
				
								
				
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
				break;
			}
			case 2: {
				
				break;
			}
			case 3: {
				
				break;
			}
			default: {
				Window.alert("La opción seleccionada no corresponde a ninguna funcion");
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
							
							P_DetalleOrdenCompraInsumo detalle = new P_DetalleOrdenCompraInsumo(result);		
							detalle.setGlassEnabled(true);
							detalle.center();
							detalle.show();
							
							
							
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("No se ha podido cargar la lista de sugerencias");
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
}
