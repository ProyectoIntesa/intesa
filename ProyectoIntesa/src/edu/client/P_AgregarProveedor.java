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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;

public class P_AgregarProveedor extends PopupPanel {

	P_FormularioInsumo padre;
	
	private FlexTable contenedor;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	private Label nuevoProveedor;
	private Label proveedor;
	private Label precio;
	private Label observaciones;
	private Label pie;
	
	private TextBox precioTb;
	private TextArea observacionesTa;
	private ListBox proveedorLb;
	
	private Button btnAgregar;
	private Button btnCancelar;
	private List<String> listaProveedores;
	
	public P_AgregarProveedor(P_FormularioInsumo pantalla) {

		super(false);
		padre = pantalla;
		setStyleName("fondoPopup");
		
		contenedor = new FlexTable();
		
		nuevoProveedor = new Label(constante.nuevoProveedor());
		nuevoProveedor.setStyleName("labelTitulo");
		proveedor = new Label(constante.proveedor());
		proveedor.setStyleName("gwt-LabelFormularioDerecho");
		precio = new Label(constante.precio());
		precio.setStyleName("gwt-LabelFormularioDerecho");		
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		precioTb = new TextBox();
		precioTb.setStyleName("gwt-TextBox");
		
		observacionesTa = new TextArea();
		observacionesTa.setDirectionEstimator(false);
		observacionesTa.setWidth("100%");
		
		proveedorLb = new ListBox();
		proveedorLb.setWidth("100%");
		proveedorLb.setStyleName("gwt-TextBox");
		
		btnAgregar = new Button(constante.agregar());
		btnAgregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarProveedor();
			}
		});
		
		
		btnCancelar = new Button(constante.cancelar());
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelar();
			}
		});
		
		contenedor.setWidget(0, 0, nuevoProveedor);
		contenedor.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		contenedor.setWidget(1, 0, proveedor);
		contenedor.setWidget(1, 1, proveedorLb);
		
		contenedor.setWidget(2, 0, precio);
		contenedor.setWidget(2, 1, precioTb);
		
		contenedor.setWidget(3, 0, observaciones);
		contenedor.getFlexCellFormatter().setColSpan(3, 0, 2);	
		
		contenedor.setWidget(4, 0, observacionesTa);
		contenedor.getFlexCellFormatter().setColSpan(4, 0, 2);
		
		contenedor.setWidget(5, 0, pie);
		contenedor.getFlexCellFormatter().setColSpan(5, 0, 2);	
		
		contenedor.setWidget(6, 0, btnAgregar);
		contenedor.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_CENTER);
		contenedor.setWidget(6, 1, btnCancelar);
		contenedor.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_CENTER);
		setWidget(contenedor);

		contenedor.setSize("300px", "300px");
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.getNombresEmpresas( new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR AL CARGAR LA LISTA");
				
			}


			@Override
			public void onSuccess(List<String> result) {
				listaProveedores= result;
				cargaProveedores();
			}
		});
	}

	protected void cargaProveedores() {
		for (String nombre : listaProveedores) {
			proveedorLb.addItem(nombre);
		}
		
	}
	
	protected void cancelar() {
		this.hide();

	}
	
	protected void agregarProveedor(){
		
		Validaciones validar = new Validaciones();
		
		boolean vPrecio = validar.textBoxVacio(this.precioTb.getText());
		
		if(!vPrecio){
			this.padre.capturarDatos(this.proveedorLb.getItemText(this.proveedorLb.getSelectedIndex()), precioTb.getText(), observacionesTa.getText());
			this.hide();
		}
		else
			Window.alert("El precio no puede ser nulo");
		
	}
		
}
