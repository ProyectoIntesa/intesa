
package edu.client;

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
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;


public class P_ValidarCancelarOrdenCompraInsumos extends PopupPanel{

	private static final int COL_NROORDEN = 0;
	private static final int COL_FECHAGENERACION = 1;
	private static final int COL_PROV = 2;
	private static final int COL_MAS_INFO = 3;
	private static final int COL_CHECK = 4;	
	
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
	
	private List<OrdenCompraInsumoDTO> listaOrdenCompraInsumo;
	
	public P_ValidarCancelarOrdenCompraInsumos(){
		
		super(false);
		setStyleName("fondoPopup");
		
		listaOrdenCompraInsumo = new LinkedList<OrdenCompraInsumoDTO>();
		
		titulo = new Label("VALIDACION DE ORDENES DE COMPRA DE INSUMOS");
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
					boolean confirm = Window.confirm("Está seguro de que desea \"cancelar\" las órdenes de provisión seleccionadas?");
					if(confirm==true){
						cancelarOrdenes();
//						for (int i = 1; i < tablaElementos.getRowCount(); i++) {
//							tablaElementos.removeRow(i);
//						}
//						tablaElementos.clear();
//						listaOrdenCompraInsumo.clear();
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
//					for (int i = 1; i < tablaElementos.getRowCount(); i++) {
//						tablaElementos.removeRow(i);
//					}
//					tablaElementos.clear();
//					listaOrdenCompraInsumo.clear();
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
		tablaElementos.getCellFormatter().setWidth(0, COL_NROORDEN, "25%");
		tablaElementos.setText(0, COL_FECHAGENERACION, constante.fechaGeneracion());
		tablaElementos.getCellFormatter().setWidth(0, COL_FECHAGENERACION, "25%");
		tablaElementos.setText(0, COL_PROV, constante.proveedor());
		tablaElementos.getCellFormatter().setWidth(0, COL_PROV, "25%");		
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
		

		ComprasServiceAsync compraService = GWT.create(ComprasService.class);
		compraService.cancelarOrdenCompraInsumo(ordenesACancelar, new AsyncCallback<Boolean>() {
		
				@Override
				public void onSuccess(Boolean result) {
					if(result){
						Window.alert("Las órdenes seleccionadas han sido canceladas de manera exitosa");
						cargarTabla();
					}
					else
						Window.alert("No se ha podido cancelar las órdenes seleccionadas");
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
		
		ComprasServiceAsync compraService = GWT.create(ComprasService.class);
		compraService.validarOrdenCompraInsumo(ordenesAValidar, new AsyncCallback<Boolean>() {
		
				@Override
				public void onSuccess(Boolean result) {
					if(result){
						Window.alert("Las órdenes seleccionadas han sido validadas de manera exitosa");
						cargarTabla();
					}
						
					else
						Window.alert("No se ha podido validar las órdenes seleccionadas");
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
				
				}
		
		});
			
		
	}
	
	public void cargarTabla(){
						
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.getOrdenCompraInsumo("GENERADA", "", "", "", new AsyncCallback<List<OrdenCompraInsumoDTO>>() {
			
			@Override
			public void onSuccess(List<OrdenCompraInsumoDTO> result) {
				
				if(!result.isEmpty()){	
					borrarTabla();
					cargarTablaFinal(result);
				}	
				else{
					borrarTabla();
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		
		});		
	}

	public void borrarTabla(){
		for (int i = 1; i < tablaElementos.getRowCount(); i++) {
			tablaElementos.removeRow(i);
		}
		tablaElementos.clear();
		listaOrdenCompraInsumo.clear();
	}
		
	public void cargarTablaFinal(List<OrdenCompraInsumoDTO> listaOrdenes){
				
		this.listaOrdenCompraInsumo = listaOrdenes;
		int item = 1;
				
		for (OrdenCompraInsumoDTO orden : listaOrdenes) {
		
			Label info = new Label("");
			info.setSize("16px", "16px");
			info.setStyleName("labelInfo");
			
			CheckBox check = new CheckBox();
			
			DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
			String fecha = fmtDate.format(orden.getFechaGeneracion());
			
			tablaElementos.setWidget(item, COL_NROORDEN, new Label(orden.getNroOrden()+""));
			tablaElementos.getCellFormatter().setWordWrap(item, COL_NROORDEN, true);
			tablaElementos.setWidget(item, COL_FECHAGENERACION, new Label(fecha));
			tablaElementos.getCellFormatter().setWordWrap(item, COL_FECHAGENERACION, true);
			tablaElementos.setWidget(item, COL_PROV, new Label(orden.getProveedor()+""));
			tablaElementos.getCellFormatter().setWordWrap(item, COL_PROV, true);
			tablaElementos.setWidget(item, COL_MAS_INFO, info);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(item, COL_MAS_INFO, HasHorizontalAlignment.ALIGN_CENTER);			
			tablaElementos.setWidget(item, COL_CHECK, check);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(item, COL_CHECK, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementos.getRowFormatter().setStyleName(item, "tablaRenglon");
							
			info.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					
					
					
					
					Cell celda = tablaElementos.getCellForEvent(event);
					OrdenCompraInsumoDTO or = obtenerElementoListaOrdenes(celda.getRowIndex()-1);
					
					
					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

					comprasService.getOrdenCompraInsumoSegunId(or.getIdOrden(),new AsyncCallback<OrdenCompraInsumoDTO>() {
						
						@Override
						public void onSuccess(OrdenCompraInsumoDTO result) {
							
							P_DetalleOrdenCompraInsumo detalleOrden = new P_DetalleOrdenCompraInsumo(result, "GERENTE PRODUCCION");
							detalleOrden.setGlassEnabled(true);
							detalleOrden.center();
							detalleOrden.show();						

							
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");
						}
					});
					
	
				}
			});
			
			item++;
		}
		
		
	}
	
	public OrdenCompraInsumoDTO obtenerElementoListaOrdenes(int indice){
		return this.listaOrdenCompraInsumo.get(indice);
	}
	
}
