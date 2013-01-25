
package edu.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import edu.client.AlmacenService.AlmacenService;
import edu.client.AlmacenService.AlmacenServiceAsync;
import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.RemitoExternoDTO;

public class P_PreguntaPorNroOrdenCompraYRemito extends PopupPanel {

	private Constantes constante = GWT.create(Constantes.class);

	private Label titulo;
	private Label pie;
	
	private Label tipoOrdenCompra;
	private Label nroOrdenCompra;
	private Label nroRemito;
	
	private ListBox tipoOrdenCompraLb;
	private ListBox nroOrdenCompraLb;
	private ListBox nroRemitoLb;

	private Button aceptar;
	private Button salir;
	
	private FlexTable panel;
	private FlexTable botones;
	
	private List<OrdenCompraInsumoDTO> ordenesInsumos;
	private List<Long> remitosExternosInsumos;
		
	public P_PreguntaPorNroOrdenCompraYRemito() {

		super(false);
		setStyleName("fondoPopup");
		
		
		
		ordenesInsumos  = new LinkedList<OrdenCompraInsumoDTO>();
		
		titulo = new Label("SELECCIONAR TIPO DE ORDEN Y NRO DE ORDEN Y REMITO EXTERNO BUSCADO");
		titulo.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		tipoOrdenCompra = new Label(constante.tipoOrden());
		tipoOrdenCompra.setStyleName("gwt-LabelFormulario");
		nroOrdenCompra = new Label(constante.nroOrdenDeCompra());
		nroOrdenCompra.setStyleName("gwt-LabelFormulario");
		nroRemito = new Label(constante.nroRemitoExterno());
		nroRemito.setStyleName("gwt-LabelFormulario");
		
		tipoOrdenCompraLb = new ListBox();
		tipoOrdenCompraLb.setStyleName("gwt-TextArea");
		tipoOrdenCompraLb.addItem("Seleccionar Tipo de Orden de Compra a Ingresar");
		tipoOrdenCompraLb.addItem(constante.ordenCompraDeInsumo());
		tipoOrdenCompraLb.addItem(constante.ordenCompraDeProducto());
		tipoOrdenCompraLb.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				cargarSugerenciaOrdenes();
			}
		});
		
		nroOrdenCompraLb = new ListBox();
		nroOrdenCompraLb.setStyleName("gwt-TextArea");
		nroOrdenCompraLb.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				cargarSugerenciaRemitos();
			}
		});
		
		nroRemitoLb = new ListBox();
		nroRemitoLb.setStyleName("gwt-TextArea");
				
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		aceptar = new Button(constante.aceptar());
		aceptar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscar();
			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, aceptar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		panel = new FlexTable();
		panel.setSize("700px", "250px");
		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		panel.setWidget(1, 0, tipoOrdenCompra);
		panel.setWidget(1, 1, tipoOrdenCompraLb);
		
		panel.setWidget(2, 0, nroOrdenCompra);
		panel.setWidget(2, 1, nroOrdenCompraLb);

		panel.setWidget(3, 0, nroRemito);
		panel.setWidget(3, 1, nroRemitoLb);
		
		panel.setWidget(4, 0, pie);
		panel.getFlexCellFormatter().setColSpan(4, 0, 2);
		
		panel.setWidget(5, 0, botones);
		panel.getFlexCellFormatter().setColSpan(5, 0, 2);
		panel.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		

		
		setWidget(panel);
		
		
	}
	
	protected void cargarSugerenciaRemitos() {
		
		if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCompraDeInsumo()) == 0){
			nroRemitoLb.clear();
			
			if(nroOrdenCompraLb.getSelectedIndex() != -1)
			{
				String nroOrdenCompraInsumo = this.nroOrdenCompraLb.getItemText(nroOrdenCompraLb.getSelectedIndex());
				
				Long id = Long.parseLong(nroOrdenCompraInsumo);

				AlmacenServiceAsync almacenService = GWT.create(AlmacenService.class);
				almacenService.getRemitosExternos(id, new AsyncCallback<List<Long>>() {

					@Override
					public void onSuccess(List<Long> result) {
						cargarListaConRemitosExternosDeInsumos(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("No se ha podido cargar la lista de sugerencias");
					}
				});
			}
			
		}
		else if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCompraDeProducto()) == 0){
			nroRemitoLb.clear();
		}		

	}

	protected void cargarListaConRemitosExternosDeInsumos(List<Long> result) {
		
		this.remitosExternosInsumos = result;
		
		for (Long remito : result) {
			nroRemitoLb.addItem(remito+"");
		}
		
	}

	protected void buscar() {
		
		if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCompraDeInsumo()) == 0){
			
			String nroOrden = this.nroOrdenCompraLb.getItemText(this.nroOrdenCompraLb.getSelectedIndex());
			for (OrdenCompraInsumoDTO orden : ordenesInsumos) {
				if(orden.getNroOrden().compareTo(nroOrden) == 0){
					
					final OrdenCompraInsumoDTO ordenEnviar = orden;
					
					AlmacenServiceAsync almacenService = GWT.create(AlmacenService.class);
					Long nroRemito = Long.parseLong(this.nroRemitoLb.getItemText(nroRemitoLb.getSelectedIndex()));
					
					almacenService.getRemitoExternoCompleto(orden, nroRemito, new AsyncCallback<RemitoExternoDTO>() {
	
						@Override
						public void onSuccess(RemitoExternoDTO result) {
								
							P_RemitoExterno popUp = new P_RemitoExterno(ordenEnviar,result);
							popUp.setGlassEnabled(true);
							popUp.center();
							popUp.show();
							salir();
							
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR DE SERVICIO");

						}
					});
					
					
				}
			}
			
		}
		else if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCompraDeProducto()) == 0){
			
		}		

		
	}

	protected void cargarSugerenciaOrdenes() {
		
		
		
		if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCompraDeInsumo()) == 0){
			nroOrdenCompraLb.clear();
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.getOrdenCompraInsumoEnviada(new AsyncCallback<List<OrdenCompraInsumoDTO>>() {

				@Override
				public void onSuccess(List<OrdenCompraInsumoDTO> result) {
					cargarListaConOrdenesDeInsumos(result);
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("No se ha podido cargar la lista de sugerencias");
				}
			});
		}
		else if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCompraDeProducto()) == 0){
			nroOrdenCompraLb.clear();
		}		

		

		
	}

	protected void cargarListaConOrdenesDeInsumos(List<OrdenCompraInsumoDTO> result) {
		
		this.ordenesInsumos = result;
		
		for (OrdenCompraInsumoDTO orden : result) {
			nroOrdenCompraLb.addItem(orden.getNroOrden());
		}
		
	}

	protected void salir() {
		this.hide();
	}
	
}
