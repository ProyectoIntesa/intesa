package edu.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;

import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RemitoProvisionInsumoDTO;


public class P_PreguntaPorOrdenProvisionYRemito extends PopupPanel {

	private Constantes constante = GWT.create(Constantes.class);

	private Label titulo;
	private Label pie;
	
	private Label tipoOrdenProvision;
	private Label nroOrdenRemito;
	
	private ListBox tipoOrdenProvisionLb;
	private ListBox nroOrdenRemitoLb;

	private Button aceptar;
	private Button salir;
	
	private FlexTable panel;
	
	private List<OrdenProvisionInsumoDTO> ordenesInsumos;

	
	public P_PreguntaPorOrdenProvisionYRemito(String accionRealizar) {

		super(false);
		setStyleName("fondoPopup");
		final String accion = accionRealizar;

		
		ordenesInsumos  = new LinkedList<OrdenProvisionInsumoDTO>();
		
		titulo = new Label("SELECCIONAR TIPO DE ORDEN DE PROVISION Y NRO DEL REMITO");
		titulo.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		tipoOrdenProvision = new Label(constante.tipoOrden());
		tipoOrdenProvision.setStyleName("gwt-LabelFormulario");
		nroOrdenRemito = new Label(constante.nroDeRemito());
		nroOrdenRemito.setStyleName("gwt-LabelFormulario");
		
		tipoOrdenProvisionLb = new ListBox();
		tipoOrdenProvisionLb.setStyleName("gwt-TextArea");
		tipoOrdenProvisionLb.addItem("Seleccionar Tipo de Orden de Provision a Ingresar");
		tipoOrdenProvisionLb.addItem(constante.ordenDeProvisionDeInsumos());
		tipoOrdenProvisionLb.addItem(constante.ordenDeProvisionDeProductos());
		tipoOrdenProvisionLb.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				cargarSugerenciaRemitos(accion);
			}
		});
		
		nroOrdenRemitoLb = new ListBox();
		nroOrdenRemitoLb.setStyleName("gwt-TextArea");
				
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		aceptar = new Button(constante.aceptar());
		aceptar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscar(accion);
			}
		});
		
		
		panel = new FlexTable();
		panel.setSize("700px", "250px");
		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		panel.setWidget(1, 0, tipoOrdenProvision);
		panel.setWidget(1, 1, tipoOrdenProvisionLb);
		
		panel.setWidget(2, 0, nroOrdenRemito);
		panel.setWidget(2, 1, nroOrdenRemitoLb);
		
		panel.setWidget(3, 0, pie);
		panel.getFlexCellFormatter().setColSpan(3, 0, 2);
		
		panel.setWidget(4, 0, aceptar);
		panel.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		panel.setWidget(4, 1, salir);
		panel.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_CENTER);
		

		
		setWidget(panel);
		
		
	}
	
	protected void buscar(String accion) {
		
		final String accionRealizar = accion;
		
		if(tipoOrdenProvisionLb.getItemText(tipoOrdenProvisionLb.getSelectedIndex()).compareTo(constante.ordenDeProvisionDeInsumos()) == 0){
			
			Long idRemito = new Long (nroOrdenRemitoLb.getItemText(nroOrdenRemitoLb.getSelectedIndex()));
			
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.getOrdenRemitoInternoInsumoSegunId(idRemito,new AsyncCallback<RemitoProvisionInsumoDTO>() {
				@Override
				public void onSuccess(RemitoProvisionInsumoDTO result) {
					
					armarPantalla(result,accionRealizar);
					

					
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
				}
			});			
			}
		else if(tipoOrdenProvisionLb.getItemText(tipoOrdenProvisionLb.getSelectedIndex()).compareTo(constante.ordenDeProvisionDeProductos()) == 0){
			
		}		

		
	}

	public void armarPantalla(RemitoProvisionInsumoDTO orden, String accionRealizar){
		
		final String accion = accionRealizar;
				
		P_RemitoProvisionInsumo remito = new P_RemitoProvisionInsumo(orden, accion);
		remito.setGlassEnabled(true);
		remito.center();
		remito.show();
		remito.addCloseHandler(new CloseHandler<PopupPanel>() {
			
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
			
				cargarSugerenciaRemitos(accion);
				
			}
		});
		
		
		
		
	}
	
	
	
	protected void cargarSugerenciaRemitos(String accionRealizar) {
				
		if(tipoOrdenProvisionLb.getItemText(tipoOrdenProvisionLb.getSelectedIndex()).compareTo(constante.ordenDeProvisionDeInsumos()) == 0){
			nroOrdenRemitoLb.clear();
			
			if(accionRealizar.compareTo("cerrar")==0){
				ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
				produccionService.getRemitosInternosInsumosGenerados(new AsyncCallback<List<Long>>() {

					@Override
					public void onSuccess(List<Long> result) {
						cargarListaConIdsRemitos(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");
					}
				});
			}
			if(accionRealizar.compareTo("buscar")==0){
				ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
				produccionService.idsRemitosInternosInsumos(new AsyncCallback<List<Long>>() {

					@Override
					public void onSuccess(List<Long> result) {
						cargarListaConIdsRemitos(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");
					}
				});
			}

		}
		else if(tipoOrdenProvisionLb.getItemText(tipoOrdenProvisionLb.getSelectedIndex()).compareTo(constante.ordenDeProvisionDeProductos()) == 0){
			nroOrdenRemitoLb.clear();
		}		

		

		
	}

	protected void cargarListaConIdsRemitos(List<Long> result) {
		
		for (Long id : result) {
			nroOrdenRemitoLb.addItem(id+"");
		}
		
	}

	protected void salir() {
		this.hide();
	}
}
