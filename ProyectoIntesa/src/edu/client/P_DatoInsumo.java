package edu.client;

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
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.InsumoDTO;

public class P_DatoInsumo extends PopupPanel {

	private static final int COL_NOMBRE_PROVEEDOR = 0;
	private static final int COL_PRECIO = 1;
	private static final int COL_OBSERVACIONES = 2;
	
	private FlexTable panel;
	private FlexTable tablaElementos;
	private FlexTable botones;
	private ScrollPanel contenedorTabla;
	private Constantes constante = GWT.create(Constantes.class);
	
	private Label insumo;
	private Label marca;
	private Label categoria;
	private Label stockSegurdidad;
	private Label loteCompra;
	private Label cant;
	private Label observacion;
	private Label titulo;
	private Label pie;
	
	private Button salir;
	private Button modificar;
	private Button eliminar;
	
	private boolean modificarInsumo = false;
	
	private InsumoDTO insumoSelec;
	
	public P_DatoInsumo(InsumoDTO insumoSelec) {
		
		super(false);
		
		this.insumoSelec = insumoSelec;
		
		setStyleName("fondoPopup");
		panel = new FlexTable();
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("200px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_NOMBRE_PROVEEDOR, constante.nombre());
		tablaElementos.getCellFormatter().setWidth(0, COL_NOMBRE_PROVEEDOR, "25%");
		tablaElementos.setText(0, COL_PRECIO, constante.precio());
		tablaElementos.getCellFormatter().setWidth(0, COL_PRECIO, "20%");
		tablaElementos.setText(0, COL_OBSERVACIONES, constante.observaciones());
		tablaElementos.getCellFormatter().setWidth(0, COL_OBSERVACIONES, "55%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");	
		
		
		for (int i = 0; i < insumoSelec.getProveedor().size(); i++){
			
			tablaElementos.setText(i+1, COL_NOMBRE_PROVEEDOR, insumoSelec.getProveedor().get(i).getNombre());
			tablaElementos.setText(i+1, COL_PRECIO, ""+insumoSelec.getProveedor().get(i).getPrecio());
			tablaElementos.setText(i+1, COL_OBSERVACIONES, insumoSelec.getProveedor().get(i).getObservaciones());
			tablaElementos.getRowFormatter().setStyleName(i+1, "tablaRenglon");
			
		}
		
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		modificar = new Button(constante.modificar());
		modificar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				modificarInsumo();
			}
		});

		eliminar = new Button(constante.eliminarInsumo());
		eliminar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eliminarInsumo();
			}
		});
		
		insumo = new Label();
		marca = new Label();
		categoria = new Label();
		stockSegurdidad = new Label();
		loteCompra = new Label();
		cant = new Label();
		observacion = new Label();
		titulo = new Label(constante.proveedores());
		titulo.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		panel.setText(0, 0, "INSUMO: " + insumoSelec.getNombre());
		panel.setText(0, 1, "MARCA: " + insumoSelec.getMarca());
		panel.setText(0, 2, "CATEGORIA: " + insumoSelec.getCategoria());
		panel.getRowFormatter().addStyleName(0, "textoPlano");
		
		panel.setText(1, 0, "LOTE COMPRA: " + insumoSelec.getLoteCompra());
		panel.setText(1, 1, "STOCK SEGURIDAD: " + insumoSelec.getStockSeguridad());
		panel.setText(1, 2, "CANTIDAD: " + insumoSelec.getCantidad());
		panel.getRowFormatter().addStyleName(1, "textoPlano");
		
		panel.setText(2, 0, "OBSERVACIONES: " + insumoSelec.getObservaciones());
		panel.getFlexCellFormatter().setColSpan(2, 0, 3);
		panel.getRowFormatter().addStyleName(2, "textoPlano");
		
		panel.setWidget(3, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(3, 0, 3);
		
		panel.setWidget(4, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(4, 0, 3);
		
		panel.setWidget(5, 0, pie);
		panel.getFlexCellFormatter().setColSpan(5, 0, 3);
		
		botones = new FlexTable();
		botones.setSize("100%", "100%");
		botones.setWidget(0, 0, modificar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);		
		botones.setWidget(0, 1, eliminar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 2, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);		
		
		
		panel.getFlexCellFormatter().setColSpan(6, 0, 3);
		panel.setWidget(6, 0, botones);
		
		
		
		
		
		setWidget(panel);
		panel.setSize("700px", "400px");
		
		
	}
	
	public InsumoDTO getInsumoDTO() {
		return this.insumoSelec;
	}
	
	protected void modificarInsumo(){
		this.modificarInsumo = true;
		this.hide();
	}
	
	protected void eliminarInsumo() {
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

		comprasService.eliminarInsumo(insumoSelec, new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if(result){
					Window.alert("El insumo ha sido eliminado correctamente");
					salir();
				}
				else{
					Window.alert("El insumo NO ha sido eliminado");
					salir();
				}
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
	
	public boolean getModificarInsumo(){
		return this.modificarInsumo;
	}
}
