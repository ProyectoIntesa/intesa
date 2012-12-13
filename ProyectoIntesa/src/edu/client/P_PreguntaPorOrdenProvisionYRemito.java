package edu.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

	
	public P_PreguntaPorOrdenProvisionYRemito() {

		super(false);
		setStyleName("fondoPopup");
		

		
		ordenesInsumos  = new LinkedList<OrdenProvisionInsumoDTO>();
		
		titulo = new Label("SELECCIONAR ORDEN DE PROVISION");
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
				cargarSugerenciaRemitos();
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
				buscar();
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
	
	protected void buscar() {
		
		if(tipoOrdenProvisionLb.getItemText(tipoOrdenProvisionLb.getSelectedIndex()).compareTo(constante.ordenDeProvisionDeInsumos()) == 0){
			
			Long idRemito = new Long (nroOrdenRemitoLb.getItemText(nroOrdenRemitoLb.getSelectedIndex()));
			
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.getOrdenRemitoInternoInsumoSegunId(idRemito,new AsyncCallback<RemitoProvisionInsumoDTO>() {
				@Override
				public void onSuccess(RemitoProvisionInsumoDTO result) {
									
					P_RemitoProvisionInsumo detalle = new P_RemitoProvisionInsumo(result);		
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
		else if(tipoOrdenProvisionLb.getItemText(tipoOrdenProvisionLb.getSelectedIndex()).compareTo(constante.ordenDeProvisionDeProductos()) == 0){
			
		}		

		
	}

	protected void cargarSugerenciaRemitos() {
				
		if(tipoOrdenProvisionLb.getItemText(tipoOrdenProvisionLb.getSelectedIndex()).compareTo(constante.ordenDeProvisionDeInsumos()) == 0){
			nroOrdenRemitoLb.clear();
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
