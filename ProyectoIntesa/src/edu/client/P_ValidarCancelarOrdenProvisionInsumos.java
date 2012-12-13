package edu.client;

import java.util.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.*;


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

	private Constantes constante = GWT.create(Constantes.class);
	
	private Label titulo;
	private Label pie;
	
	private Button salir;
	private Button validarOrden;
	
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
		
		validarOrden = new Button(constante.validarOrden());
		validarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//buscar();
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

		panel.setWidget(3, 1, salir);
		panel.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		panel.setWidget(3, 3, validarOrden);
		panel.getCellFormatter().setHorizontalAlignment(3, 3, HasHorizontalAlignment.ALIGN_CENTER);
	
		cargarTabla();
		
		setWidget(panel);
		
	}
	
	protected void salir() {
		this.hide();

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
						
			item++;
		}
		
		
	}
	
	
	
}
