package edu.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.OrdenCompraInsumoDTO;

public class P_PreguntaPorNroOrdenCompra extends PopupPanel {

	private Constantes constante = GWT.create(Constantes.class);

	private Label titulo;
	private Label pie;
	
	private Label tipoOrdenCompra;
	private Label nroOrdenCompra;
	
	private ListBox tipoOrdenCompraLb;
	private ListBox nroOrdenCompraLb;

	private Button aceptar;
	private Button salir;
	
	private FlexTable panel;
	
	private List<OrdenCompraInsumoDTO> ordenesInsumos;
	
	private String usuario;
	
	public P_PreguntaPorNroOrdenCompra(String usuarioLogueado) {

		super(false);
		setStyleName("fondoPopup");
		
		this.usuario = usuarioLogueado;
		
		ordenesInsumos  = new LinkedList<OrdenCompraInsumoDTO>();
		
		titulo = new Label("SELECCIONAR ORDEN DE COMPRA A LA CUAL CORRESPONDE EL REMITO EXTERNO");
		titulo.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		tipoOrdenCompra = new Label(constante.tipoOrden());
		tipoOrdenCompra.setStyleName("gwt-LabelFormulario");
		nroOrdenCompra = new Label(constante.nroOrdenDeCompra());
		nroOrdenCompra.setStyleName("gwt-LabelFormulario");
		
		tipoOrdenCompraLb = new ListBox();
		tipoOrdenCompraLb.setStyleName("gwt-TextArea");
		tipoOrdenCompraLb.addItem("");
		tipoOrdenCompraLb.addItem(constante.ordenCompraDeInsumo());
		tipoOrdenCompraLb.addItem(constante.ordenCompraDeProducto());
		tipoOrdenCompraLb.addItem(constante.ordenCotizacionDeProducto());
		tipoOrdenCompraLb.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				cargarSugerenciaOrdenes();
			}
		});
		
		nroOrdenCompraLb = new ListBox();
		nroOrdenCompraLb.setStyleName("gwt-TextArea");
				
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
		
		
		panel = new FlexTable();
		panel.setSize("700px", "250px");
		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		panel.setWidget(1, 0, tipoOrdenCompra);
		panel.setWidget(1, 1, tipoOrdenCompraLb);
		
		panel.setWidget(2, 0, nroOrdenCompra);
		panel.setWidget(2, 1, nroOrdenCompraLb);
		
		panel.setWidget(3, 0, pie);
		panel.getFlexCellFormatter().setColSpan(3, 0, 2);
		
		panel.setWidget(4, 0, aceptar);
		panel.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		panel.setWidget(4, 1, salir);
		panel.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_CENTER);
		

		
		setWidget(panel);
		
		
	}
	
	protected void buscar() {
		
		if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCompraDeInsumo()) == 0){
			
			String nroOrden = this.nroOrdenCompraLb.getItemText(this.nroOrdenCompraLb.getSelectedIndex());
			for (OrdenCompraInsumoDTO orden : ordenesInsumos) {
				if(orden.getNroOrden().compareTo(nroOrden) == 0){
					P_RemitoExterno popUp = new P_RemitoExterno(orden,usuario);
					popUp.setGlassEnabled(true);
					popUp.center();
					popUp.show();
					salir();
					break;
				}
			}
			
		}
		else if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCompraDeProducto()) == 0){
			
		}		
		else if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCotizacionDeProducto()) == 0){
			
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
		else if(tipoOrdenCompraLb.getItemText(tipoOrdenCompraLb.getSelectedIndex()).compareTo(constante.ordenCotizacionDeProducto()) == 0){
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
