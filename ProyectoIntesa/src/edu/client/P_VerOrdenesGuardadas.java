
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
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.OrdenCompraInsumoDTO;

public class P_VerOrdenesGuardadas extends PopupPanel {

	TabPanel padre;

	private Constantes constante = GWT.create(Constantes.class);
	
	private static final int COL_NRO_ORDEN = 0;
	private static final int COL_EMPLEADO_POR = 1;
	private static final int COL_PROVEEDOR = 2;
	private static final int COL_TIPO_ORDEN = 3;
	private static final int COL_MAS_INFO = 4;

	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	private FlexTable botones;

	private Label tipo_orden;
	
	private ListBox tipo_ordenLb;
	
	private Label ver;
	private Label pie;
	
	private Button verB; 
	private Button salirB;
	
	List<OrdenCompraInsumoDTO> ordenes;
	private boolean modificarOrden;
	private OrdenCompraInsumoDTO orden;
	
	public P_VerOrdenesGuardadas() {

		super(false);

		setStyleName("fondoPopup");
		modificarOrden = false;

		tipo_orden = new Label(constante.tipoOrden());
		tipo_orden.setStyleName("gwt-LabelFormulario");
		ver = new Label(constante.buscarPor());
		ver.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");

		tipo_ordenLb = new ListBox();
		tipo_ordenLb.setStyleName("gwt-TextArea");
		
		tipo_ordenLb.addItem(constante.ordenCompraDeInsumo());
		tipo_ordenLb.addItem(constante.ordenCompraDeProducto());
		tipo_ordenLb.addItem(constante.ordenCotizacionDeProducto());
		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_NRO_ORDEN, constante.nroOrden());
		tablaElementos.getCellFormatter().setWidth(0, COL_NRO_ORDEN, "21%");
		tablaElementos.setText(0, COL_EMPLEADO_POR, constante.empleado());
		tablaElementos.getCellFormatter().setWidth(0, COL_EMPLEADO_POR, "21%");
		tablaElementos.setText(0, COL_PROVEEDOR, constante.proveedor());
		tablaElementos.getCellFormatter().setWidth(0, COL_PROVEEDOR, "21%");
		tablaElementos.setText(0, COL_TIPO_ORDEN, constante.tipoOrden());
		tablaElementos.getCellFormatter().setWidth(0, COL_TIPO_ORDEN, "21%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "6%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");

		salirB = new Button(constante.salir());
		salirB.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		verB = new Button(constante.buscar());
		verB.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscar();
			}
		});
	
		botones = new FlexTable();
		botones.setWidget(0, 0, salirB);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		panel.setWidget(0, 0, ver);
		panel.getFlexCellFormatter().setColSpan(0, 0, 4);

		panel.setWidget(1, 0, tipo_orden);
		panel.setWidget(1, 1, tipo_ordenLb);
		
		panel.setWidget(1, 3, verB);
		panel.getCellFormatter().setHorizontalAlignment(1, 3, HasHorizontalAlignment.ALIGN_CENTER);
				
		panel.setWidget(2, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(2, 0, 4);
		
		panel.setWidget(3, 0, pie);
		panel.getFlexCellFormatter().setColSpan(3, 0, 4);

		panel.setWidget(4, 0, botones);
		panel.getFlexCellFormatter().setColSpan(4, 0, 4);
		panel.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	
		
		setWidget(panel);
		
	}
	
	protected void buscar() {
		
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		switch (tipo_ordenLb.getSelectedIndex()){

			case 0: {
					
				comprasService.getOrdenCompraInsumoGuardada(new AsyncCallback<List<OrdenCompraInsumoDTO>>() {
					
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
			case 1: {
				tablaElementos.clear();
				Window.alert("Momentaneamente no disponible");
				break;
			}
			case 2: {
				tablaElementos.clear();
				Window.alert("Momentaneamente no disponible");				
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
							modificarOrden = true;
							orden=result;
							salir();
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
	
	public boolean getModificarOrden(){
		return this.modificarOrden;
	}
	
	public OrdenCompraInsumoDTO getOrdenElegida(){
		return this.orden;
	}
	
}
