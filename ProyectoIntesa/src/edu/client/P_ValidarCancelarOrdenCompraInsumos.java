
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

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;


public class P_ValidarCancelarOrdenCompraInsumos extends PopupPanel{

	private static final int COL_NROORDEN = 0;
	private static final int COL_FECHAGENERACION = 1;
	private static final int COL_MAS_INFO = 2;
	private static final int COL_CHECK = 3;	
	
	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;

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
				cancelarOrdenes();
				for (int i = 1; i < tablaElementos.getRowCount(); i++) {
					tablaElementos.removeRow(i);
				}
				tablaElementos.clear();
				listaOrdenCompraInsumo.clear();
				cargarTabla();
			}
		});
		
		validarOrdenes = new Button(constante.validarOrdenes());
		validarOrdenes.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				validar();
				for (int i = 1; i < tablaElementos.getRowCount(); i++) {
					tablaElementos.removeRow(i);
				}
				tablaElementos.clear();
				listaOrdenCompraInsumo.clear();
				cargarTabla();
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
		tablaElementos.setText(0, COL_NROORDEN, constante.nroOrden());
		tablaElementos.getCellFormatter().setWidth(0, COL_NROORDEN, "35%");
		tablaElementos.setText(0, COL_FECHAGENERACION, constante.fechaGeneracion());
		tablaElementos.getCellFormatter().setWidth(0, COL_FECHAGENERACION, "35%");
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

		panel.setWidget(3, 1, salir);
		panel.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		panel.setWidget(3, 2, cancelarOrdenes);
		panel.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_CENTER);
		
		panel.setWidget(3, 3, validarOrdenes);
		panel.getCellFormatter().setHorizontalAlignment(3, 3, HasHorizontalAlignment.ALIGN_CENTER);
	
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
					if(result)
						Window.alert("Las ordenes seleccinadas han sido canceladas");
					else
						Window.alert("Las ordenes NO han sido canceladas");
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
					if(result)
						Window.alert("Las ordenes seleccinadas han sido validadas");
					else
						Window.alert("Las ordenes NO han sido validadas");
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
					cargarTablaFinal(result);
				}				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		
		});		
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
			tablaElementos.setWidget(item, COL_MAS_INFO, info);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(item, COL_MAS_INFO, HasHorizontalAlignment.ALIGN_CENTER);			
			tablaElementos.setWidget(item, COL_CHECK, check);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(item, COL_CHECK, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementos.getRowFormatter().setStyleName(item, "tablaRenglon");
							
			info.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					Cell celda = tablaElementos.getCellForEvent(event);
					OrdenCompraInsumoDTO or = obtenerElementoListaOrdenes(celda.getRowIndex()-1);
					P_DetalleOrdenCompraInsumo detalleOrden = new P_DetalleOrdenCompraInsumo(or,"GERENTE PRODUCCION");
					detalleOrden.setGlassEnabled(true);
					detalleOrden.center();
					detalleOrden.show();

				}
			});
			
			item++;
		}
		
		
	}
	
	public OrdenCompraInsumoDTO obtenerElementoListaOrdenes(int indice){
		return this.listaOrdenCompraInsumo.get(indice);
	}
	
}
