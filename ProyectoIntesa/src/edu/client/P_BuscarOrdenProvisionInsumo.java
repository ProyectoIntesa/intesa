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
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;

public class P_BuscarOrdenProvisionInsumo extends PopupPanel {

	private static final int COL_NRO_ORDEN = 0;
	private static final int COL_GENERADO_POR = 1;
	private static final int COL_GENERADO_PARA = 2;
	private static final int COL_ESTADO = 3;
	private static final int COL_FECHAGENERACION = 4;
	private static final int COL_MAS_INFO = 5;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	private Label titulo;
	private Label generadaPor;
	private Label generadaPara;
	private Label estado;
	private Label fechaDesde;
	private Label fechaHasta;
	private Label pie;
	private Label segunFechaGeneracion;
	
	private ListBox generadaPorLb;
	private ListBox generadaParaLb;
	private ListBox estadoLb;
	
	private DateBox fechaDesdeDb;
	private DateBox fechaHastaDb;
	
	private CheckBox fechaCb;
	
	private Button buscar;
	private Button salir;
	
	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	
	private EmpleadoDTO empleado;
	private List<OrdenProvisionInsumoDTO> ordenesProvision;
	
	
	
	
	public P_BuscarOrdenProvisionInsumo(String responsable){
		
		super(false);
		setStyleName("fondoPopup");
						
		String nombre = responsable.split(", ")[1];
		String apellido = responsable.split(", ")[0];
		String rol = "PRODUCCION";
		
		fechaCb = new CheckBox(constante.fechaGeneracion());
		fechaCb.setStyleName("check");
		fechaCb.setWordWrap(false);
		fechaCb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionCheck();
			}
		});
		
		titulo = new Label(constante.buscarPor());
		titulo.setStyleName("labelTitulo");
		generadaPor = new Label(constante.generadaPor());
		generadaPor.setStyleName("gwt-LabelFormulario");
		generadaPara = new Label(constante.generadoPara());
		generadaPara.setStyleName("gwt-LabelFormulario");
		fechaDesde = new Label(constante.desde());
		fechaDesde.setStyleName("gwt-LabelFormulario");
		fechaHasta = new Label(constante.hasta());
		fechaHasta.setStyleName("gwt-LabelFormulario");
		estado = new Label(constante.estado());
		estado.setStyleName("gwt-LabelFormulario");
		segunFechaGeneracion = new Label(constante.segunFechaDeGeneracion());
		segunFechaGeneracion.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		generadaPorLb = new ListBox();
		generadaPorLb.setStyleName("gwt-TextArea");
		generadaPorLb.addItem(responsable);
		generadaPorLb.setEnabled(false);
		
		generadaParaLb = new ListBox();
		generadaParaLb.setStyleName("gwt-TextArea");	
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.getEmpleado(nombre, apellido, rol, new AsyncCallback<EmpleadoDTO>() {
			@Override
			public void onSuccess(EmpleadoDTO result) {
				empleado = result;
				cargarListaEmpleadosACargo(result);			
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de empleados");
			}
		});
		
		estadoLb = new ListBox();
		estadoLb.setStyleName("gwt-TextArea");
		
		produccionService.getNombreEstados(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaEstados(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias de estados");
			}
		});
		
		fechaDesdeDb = new DateBox();
		fechaDesdeDb.setEnabled(false);
		fechaDesdeDb.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		fechaHastaDb = new DateBox();
		fechaHastaDb.setEnabled(false);
		fechaHastaDb.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		
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
		tablaElementos.setText(0, COL_GENERADO_POR, constante.generadaPor());
		tablaElementos.getCellFormatter().setWidth(0, COL_GENERADO_POR, "18%");
		tablaElementos.setText(0, COL_GENERADO_PARA, constante.generadoPara());
		tablaElementos.getCellFormatter().setWidth(0, COL_GENERADO_PARA, "18%");
		tablaElementos.setText(0, COL_ESTADO, constante.estado());
		tablaElementos.getCellFormatter().setWidth(0, COL_ESTADO, "18%");
		tablaElementos.setText(0, COL_FECHAGENERACION, constante.fechaGeneracion());
		tablaElementos.getCellFormatter().setWidth(0, COL_FECHAGENERACION, "18%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "10%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 6);

		panel.setWidget(1, 0, generadaPor);
		panel.setWidget(1, 1, generadaPorLb);
		panel.setWidget(1, 2, generadaPara);
		panel.setWidget(1, 3, generadaParaLb);
		panel.setWidget(1, 4, estado);
		panel.setWidget(1, 5, estadoLb);
		
		panel.setWidget(2, 0, segunFechaGeneracion);
		panel.getFlexCellFormatter().setColSpan(2, 0, 6);
		
		panel.setWidget(3, 0, fechaCb);
		panel.getFlexCellFormatter().setColSpan(3, 0, 1);
		panel.setWidget(3, 2, fechaDesde);
		panel.setWidget(3, 3, fechaDesdeDb);
		panel.setWidget(3, 4, fechaHasta);
		panel.setWidget(3, 5, fechaHastaDb);
		
		panel.setWidget(4, 0, buscar);
		panel.getFlexCellFormatter().setColSpan(4, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
				
		panel.setWidget(5, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(5, 0, 6);
		
		panel.setWidget(6, 0, pie);
		panel.getFlexCellFormatter().setColSpan(6, 0, 6);

		panel.setWidget(7, 0, salir);
		panel.getFlexCellFormatter().setColSpan(7, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);
	
		
		setWidget(panel);

		
		
		
	}
	
	protected void buscar() {
		
		String unEstado = estadoLb.getItemText(estadoLb.getSelectedIndex());
		int pos = generadaParaLb.getSelectedIndex();
		int idEmpleadoPara;
		
		if(pos == 0)
			idEmpleadoPara = 0;
		else 
			idEmpleadoPara = this.empleado.getListaEmpACargo().get(pos-1).getIdEmpleado();
		
		
		
		String fecDesde = "";
		String fecHasta = "";
		
		if(fechaCb.getValue() == true){
			fecDesde = fechaDesdeDb.getTextBox().getText();
			fecHasta = fechaHastaDb.getTextBox().getText();					
		}
		
		
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.getOrdenProvisionInsumo(unEstado, this.empleado.getIdEmpleado(), idEmpleadoPara, fecDesde, fecHasta, new AsyncCallback<List<OrdenProvisionInsumoDTO>>() {
			@Override
			public void onSuccess(List<OrdenProvisionInsumoDTO> result) {	
				cargarTabla(result);			
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");
			}
		});
		
		
		
		
	}

	protected void cargarTabla(List<OrdenProvisionInsumoDTO> result) {
		
		this.tablaElementos.clear();
		ordenesProvision = new LinkedList<OrdenProvisionInsumoDTO>();
		ordenesProvision = result;
		
		for(int i = 0; i < result.size(); i++){
			
			Label infoC = new Label("");
			infoC.setSize("16px", "16px");
			infoC.setStyleName("labelInfo");
			
			DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
			String fecha = fmtDate.format(result.get(i).getFechaGeneracion());
			
			tablaElementos.setWidget(i + 1, COL_NRO_ORDEN, new Label(result.get(i).getIdOrdenProvisionInsumo()+""));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_NRO_ORDEN, true);
			tablaElementos.setWidget(i + 1, COL_GENERADO_POR, new Label(result.get(i).getEmpleadoByIdPedidoPor().getNombre()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_GENERADO_POR, true);
			tablaElementos.setWidget(i + 1, COL_GENERADO_PARA, new Label(result.get(i).getEmpleadoByIdPedidoPara().getNombre()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_GENERADO_PARA, false);
			tablaElementos.setWidget(i + 1, COL_ESTADO, new Label(result.get(i).getEstadoOrden()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_ESTADO, true);
			tablaElementos.setWidget(i + 1, COL_FECHAGENERACION, new Label(fecha));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_FECHAGENERACION, true);			
			tablaElementos.setWidget(i + 1, COL_MAS_INFO, infoC);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_MAS_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementos.getRowFormatter().setStyleName(i + 1, "tablaRenglon");
			
			infoC.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Cell celda = tablaElementos.getCellForEvent(event);
					long idOrden= ordenesProvision.get(celda.getRowIndex() - 1).getIdOrdenProvisionInsumo();
					
					ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
					produccionService.getOrdenProvisionInsumoSegunId(idOrden,new AsyncCallback<OrdenProvisionInsumoDTO>() {
						@Override
						public void onSuccess(OrdenProvisionInsumoDTO result) {

							P_DetalleOrdenProvisionInsumo detalle = new P_DetalleOrdenProvisionInsumo(result);		
							detalle.setGlassEnabled(true);
							detalle.center();
							detalle.show();							
							
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


	
	protected void salir() {
		this.hide();

	}

	protected void cargarListaEmpleadosACargo(EmpleadoDTO result) {
		
		generadaParaLb.addItem("TODOS");
		
		for (EmpleadoDTO empleado : result.getListaEmpACargo()) {
			String emp = empleado.getApellido()+", "+empleado.getNombre();
			this.generadaParaLb.addItem(emp);
		}
		
		
	}

	protected void cargarSugerenciaEstados(List<String> result) {

		estadoLb.addItem("TODOS");
		
		for (String sugerencia : result) {
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
	
}
