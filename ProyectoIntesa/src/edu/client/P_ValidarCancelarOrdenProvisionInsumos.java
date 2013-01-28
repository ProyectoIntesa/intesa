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
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.OrdenProvisionInsumoDTO;


public class P_ValidarCancelarOrdenProvisionInsumos extends PopupPanel{

	private static final int COL_NROORDEN = 0;
	private static final int COL_PEDIDOPOR = 1;
	private static final int COL_PEDIDOPARA = 2;
	private static final int COL_FECHAGENERACION = 3;
	private static final int COL_MAS_INFO = 4;
	private static final int COL_CHECK = 5;	
	
	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	private FlexTable botones;

	private Constantes constante = GWT.create(Constantes.class);
	
	private Label titulo;
	private Label pie;
	
	private Button salir;
	private Button validarOrdenes;
	private Button cancelarOrdenes;
	
	private List<OrdenProvisionInsumoDTO> listaOrdenProvisionInsumo;
	
	public P_ValidarCancelarOrdenProvisionInsumos(){
		
		super(false);
		setStyleName("fondoPopup");
		
		listaOrdenProvisionInsumo = new LinkedList<OrdenProvisionInsumoDTO>();
		
		titulo = new Label("VALIDACION DE ORDENES DE PROVISION DE INSUMOS");
		titulo.setStyleName("labelTitulo");
		pie = new Label("");
		pie.setStyleName("labelTitulo");
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		cancelarOrdenes = new Button(constante.cancelarOrdenes());
		cancelarOrdenes.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				boolean bandera = false;

				for(int i = 1; i < tablaElementos.getRowCount(); i++){
					if(((CheckBox)tablaElementos.getWidget(i, COL_CHECK)).getValue() == true){
						bandera = true;
						break;
					}
				}
				if(bandera == true){
					boolean confirm = Window.confirm("Está seguro de que desea cancelar las órdenes de provisión seleccionadas?");
					if(confirm==true){
						cancelarOrdenes();
//						for(int i = 1; i < tablaElementos.getRowCount(); i++){
//							tablaElementos.removeRow(i);
//						}
//						listaOrdenProvisionInsumo.clear();
//						cargarTabla();
					}
				}
				

			}
		});
		
		validarOrdenes = new Button(constante.validarOrdenes());
		validarOrdenes.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				boolean bandera = false;

				for(int i = 1; i < tablaElementos.getRowCount(); i++){
					if(((CheckBox)tablaElementos.getWidget(i, COL_CHECK)).getValue() == true){
						bandera = true;
						break;
					}
				}
				if(bandera == true){
					validar();
//					for(int i = 1; i < tablaElementos.getRowCount(); i++){
//						tablaElementos.removeRow(i);
//					}
//					listaOrdenProvisionInsumo.clear();
//					cargarTabla();
				}
				
				

			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, cancelarOrdenes);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, validarOrdenes);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 2, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_NROORDEN, constante.nroOrden());
		tablaElementos.getCellFormatter().setWidth(0, COL_NROORDEN, "22%");
		tablaElementos.setText(0, COL_PEDIDOPOR, constante.generadaPor());
		tablaElementos.getCellFormatter().setWidth(0, COL_PEDIDOPOR, "22%");
		tablaElementos.setText(0, COL_PEDIDOPARA, constante.generadoPara());
		tablaElementos.getCellFormatter().setWidth(0, COL_PEDIDOPARA, "22%");
		tablaElementos.setText(0, COL_FECHAGENERACION, constante.fechaGeneracion());
		tablaElementos.getCellFormatter().setWidth(0, COL_FECHAGENERACION, "22%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "6%");
		tablaElementos.setText(0, COL_CHECK, "");
		tablaElementos.getCellFormatter().setWidth(0, COL_CHECK, "6%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 5);
	
		panel.setWidget(1, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(1, 0, 5);
		
		panel.setWidget(2, 0, pie);
		panel.getFlexCellFormatter().setColSpan(2, 0, 5);

		panel.setWidget(3, 0, botones);
		panel.getFlexCellFormatter().setColSpan(3, 0, 5);
		panel.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	
		cargarTabla();
		
		setWidget(panel);
		
	}
	
	protected void cancelarOrdenes() {
		List<Long> ordenesACancelar = new LinkedList<Long>();
		
		for(int i = 1; i < tablaElementos.getRowCount(); i++){
			
			if(((CheckBox)tablaElementos.getWidget(i, COL_CHECK)).getValue() == true){
				
				Long nroOrden = Long.parseLong(((Label)tablaElementos.getWidget(i, COL_NROORDEN)).getText());
				ordenesACancelar.add(nroOrden);
			}
		}
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.cancelarOrdenesProvisionInsumos(ordenesACancelar, new AsyncCallback<Boolean>() {
		
				@Override
				public void onSuccess(Boolean result) {
					if(result){
						Window.alert("Las Ordenes seleccionadas fueron canceladas");
						for(int i = 1; i < tablaElementos.getRowCount(); i++){
							tablaElementos.removeRow(i);
						}
						listaOrdenProvisionInsumo.clear();
						cargarTabla();
					}
					else
						Window.alert("Las Ordenes NO han sido canceladas");
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");				
				}
		
		});
		
	}

	protected void salir() {
		this.hide();

	}
	
	public void validar(){
		
		List<Long> ordenesAValidar = new LinkedList<Long>();
				
		for(int i = 1; i < tablaElementos.getRowCount(); i++){
			
			if(((CheckBox)tablaElementos.getWidget(i, COL_CHECK)).getValue() == true){
				
				Long nroOrden = Long.parseLong(((Label)tablaElementos.getWidget(i, COL_NROORDEN)).getText());
				ordenesAValidar.add(nroOrden);
			}
		}
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.validarOrdenesProvisionInsumos(ordenesAValidar, new AsyncCallback<Boolean>() {
		
				@Override
				public void onSuccess(Boolean result) {
					if(result){
						Window.alert("Las Ordenes seleccionadas fueron validadas");
						for(int i = 1; i < tablaElementos.getRowCount(); i++){
							tablaElementos.removeRow(i);
						}
						listaOrdenProvisionInsumo.clear();
						cargarTabla();
					}
					else
						Window.alert("Las Ordenes NO han sido validadas");
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");				
				}
		
		});
		
		
	}
	
	public void cargarTabla(){
				
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.getOrdenProvisionInsumoCompletos("GENERADA", 0, 0, "", "", new AsyncCallback<List<OrdenProvisionInsumoDTO>>() {
			@Override
			public void onSuccess(List<OrdenProvisionInsumoDTO> result) {	
				
				if(!result.isEmpty()){	
					cargarTablaFinal(result);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
	
		
	}

	public void cargarTablaFinal(List<OrdenProvisionInsumoDTO> listaOrdenes){
		
		this.listaOrdenProvisionInsumo = listaOrdenes;
		int item = 1;
		for (OrdenProvisionInsumoDTO orden : listaOrdenes) {
			
			Label info = new Label("");
			info.setSize("16px", "16px");
			info.setStyleName("labelInfo");
			CheckBox check = new CheckBox();
			DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
			String fecha = fmtDate.format(orden.getFechaGeneracion());
			
			tablaElementos.setWidget(item, COL_NROORDEN, new Label(orden.getIdOrdenProvisionInsumo()+""));
			tablaElementos.getCellFormatter().setWordWrap(item, COL_NROORDEN, true);
			tablaElementos.setWidget(item, COL_PEDIDOPOR, new Label(orden.getEmpleadoByIdPedidoPor().getApellido()+", "+orden.getEmpleadoByIdPedidoPor().getNombre()));
			tablaElementos.getCellFormatter().setWordWrap(item, COL_PEDIDOPOR, true);
			tablaElementos.setWidget(item, COL_PEDIDOPARA, new Label(orden.getEmpleadoByIdPedidoPara().getApellido()+", "+orden.getEmpleadoByIdPedidoPara().getNombre()));
			tablaElementos.getCellFormatter().setWordWrap(item, COL_PEDIDOPARA, false);
			tablaElementos.setWidget(item, COL_FECHAGENERACION, new Label(fecha));
			tablaElementos.getCellFormatter().setWordWrap(item, COL_FECHAGENERACION, true);
			tablaElementos.setWidget(item, COL_MAS_INFO, info);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(item, COL_MAS_INFO, HasHorizontalAlignment.ALIGN_CENTER);			
			tablaElementos.setWidget(item, COL_CHECK, check);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(item, COL_CHECK, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementos.getRowFormatter().setStyleName(item, "tablaRenglon");
							
			info.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					Cell celda = tablaElementos.getCellForEvent(event);
					OrdenProvisionInsumoDTO or = obtenerElementoListaOrdenes(celda.getRowIndex()-1);
					P_DetalleOrdenProvisionInsumo detalleOrden = new P_DetalleOrdenProvisionInsumo(or);
					detalleOrden.setGlassEnabled(true);
					detalleOrden.center();
					detalleOrden.show();

				}
			});
			
			item++;
		}
		
		
	}
	
	public OrdenProvisionInsumoDTO obtenerElementoListaOrdenes(int indice){
		return this.listaOrdenProvisionInsumo.get(indice);
	}
	
}
