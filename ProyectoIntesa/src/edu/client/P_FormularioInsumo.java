package edu.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.client.VentasService.VentasService;
import edu.client.VentasService.VentasServiceAsync;
import edu.server.repositorio.Proveedor;
import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.DireccionDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.ProveedorDTO;
import edu.shared.DTO.ProveedorDeInsumosDTO;

public class P_FormularioInsumo  extends Composite {
	
	
	private static final int COL_NOMBRE = 0;
	private static final int COL_PRECIO = 1;
	private static final int COL_OBSERVACIONES = 2;
	private static final int COL_BORRAR = 3;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	TabPanel padre;
	
	private Label insumo;
	private Label nombreInsumo;
	private Label categoriaInsumo;
	private Label marcaInsumo;
	private Label inventario;	
	private Label loteCompraInsumo;
	private Label stockSeguridadInsumo;
	private Label observaciones;
	private Label proveedor;
	private Label pie;
	
	private TextBox nombreInsumoTb;
	private TextBox categoriaInsumoTb;
	private TextBox marcaInsumoTb;
	private TextBox loteCompraInsumoTb;
	private TextBox stockSeguridadInsumoTb;
	
	private TextArea observacionesTb;
	
	private Button btnAgregarProveedor;
	private Button btnCargar;
	private Button btnCancelar;
	
	private FlexTable formularioProveedor;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;
	
	private ArrayList<String> elementos;
	
	
	public P_FormularioInsumo(TabPanel padre) {
	
	
		this.padre = padre;
		
		elementos = new ArrayList<String>();
		
		insumo = new Label(constante.insumo());
		insumo.setStyleName("labelTitulo");
		nombreInsumo = new Label(constante.nombre());
		nombreInsumo.setStyleName("gwt-LabelFormulario");
		categoriaInsumo = new Label(constante.categoria());
		categoriaInsumo.setStyleName("gwt-LabelFormulario");
		marcaInsumo = new Label(constante.marca());
		marcaInsumo.setStyleName("gwt-LabelFormulario");
		inventario = new Label(constante.inventariol());
		inventario.setStyleName("labelTitulo");
		loteCompraInsumo = new Label(constante.loteCompra());
		loteCompraInsumo.setStyleName("gwt-LabelFormulario");
		stockSeguridadInsumo = new Label(constante.stockSeguridad());
		stockSeguridadInsumo.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		proveedor = new Label(constante.proveedores());
		proveedor.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		nombreInsumoTb = new TextBox();
		categoriaInsumoTb = new TextBox();
		marcaInsumoTb = new TextBox();
		loteCompraInsumoTb = new TextBox();
		stockSeguridadInsumoTb = new TextBox();
		observacionesTb = new TextArea();
		observacionesTb.setDirectionEstimator(false);
		observacionesTb.setWidth("100%");
		
		btnAgregarProveedor = new Button(constante.agregarProveedor());
		btnAgregarProveedor.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarProveedor(event);

			}
		});

		btnCargar = new Button(constante.cargar());
		btnCargar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cargarInsumo(event);
			}
		});

		btnCancelar = new Button(constante.cancelar());
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelar(event);
			}
		});
		
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("200px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		tablaElemento.setText(0, COL_NOMBRE, constante.nombre());
		tablaElemento.getCellFormatter().setWidth(0, COL_NOMBRE, "20%");
		tablaElemento.setText(0, COL_PRECIO, constante.precio());
		tablaElemento.getCellFormatter().setWidth(0, COL_PRECIO, "20%");
		tablaElemento.setText(0, COL_OBSERVACIONES, constante.observaciones());
		tablaElemento.getCellFormatter().setWidth(0, COL_OBSERVACIONES, "50%");
		tablaElemento.setText(0, COL_BORRAR, constante.eliminar());
		tablaElemento.getCellFormatter().setWidth(0, COL_BORRAR, "10%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		formularioProveedor = new FlexTable();
		formularioProveedor.setStyleName("formatoFormulario");
		formularioProveedor.setHeight("637px");
		formularioProveedor.setWidth("920px");
		
		formularioProveedor.setWidget(0, 0, insumo);
		formularioProveedor.getFlexCellFormatter().setColSpan(0, 0, 6);
		
		formularioProveedor.setWidget(1, 0, nombreInsumo);
		formularioProveedor.setWidget(1, 1, nombreInsumoTb);
		formularioProveedor.setWidget(1, 2, categoriaInsumo);
		formularioProveedor.setWidget(1, 3, categoriaInsumoTb);
		formularioProveedor.setWidget(1, 4, marcaInsumo);
		formularioProveedor.setWidget(1, 5, marcaInsumoTb);
		
		formularioProveedor.setWidget(2, 0, inventario);
		formularioProveedor.getFlexCellFormatter().setColSpan(2, 0, 6);
		
		formularioProveedor.setWidget(3, 0, loteCompraInsumo);
		formularioProveedor.setWidget(3, 1, loteCompraInsumoTb);
		formularioProveedor.setWidget(3, 2, stockSeguridadInsumo);
		formularioProveedor.setWidget(3, 3, stockSeguridadInsumoTb);
		
		formularioProveedor.setWidget(4, 0, observaciones);
		formularioProveedor.getFlexCellFormatter().setColSpan(4, 0, 6);
		
		formularioProveedor.setWidget(5, 0, observacionesTb);
		formularioProveedor.getFlexCellFormatter().setColSpan(5, 0, 6);

		formularioProveedor.setWidget(6, 0, proveedor);
		formularioProveedor.getFlexCellFormatter().setColSpan(6, 0, 6);
		
		formularioProveedor.setWidget(7, 2, btnAgregarProveedor);
		
		formularioProveedor.setWidget(8, 0, contenedorTabla);
		formularioProveedor.getFlexCellFormatter().setColSpan(8, 0, 6);
		
		formularioProveedor.setWidget(9, 0, pie);
		formularioProveedor.getFlexCellFormatter().setColSpan(9, 0, 6);
		
		formularioProveedor.setWidget(10, 2, btnCargar);
		formularioProveedor.setWidget(10, 4, btnCancelar);
		
		initWidget(formularioProveedor);
	}


	protected void cargarInsumo(ClickEvent event) {
		
		
		
		
		InsumoDTO insumo = new InsumoDTO();
		insumo.setNombre(this.nombreInsumoTb.getText());
		insumo.setMarca(this.marcaInsumoTb.getText());
		insumo.setCategoria(this.categoriaInsumoTb.getText());
		insumo.setObservaciones(this.observacionesTb.getText());
		int lote = Integer.parseInt(this.loteCompraInsumoTb.getText());
		insumo.setLoteCompra(lote);
		int stock = Integer.parseInt(this.stockSeguridadInsumoTb.getText());
		insumo.setStockSeguridad(stock);
		
		
		if (tablaElemento.getRowCount() > 1){
			
			for (int i = 1; i < tablaElemento.getRowCount(); i++){
				
				ProveedorDeInsumosDTO prov = new ProveedorDeInsumosDTO();
				prov.setNombre(((Label) tablaElemento.getWidget(i, COL_NOMBRE)).getText());
				prov.setObservaciones(((Label) tablaElemento.getWidget(i, COL_OBSERVACIONES)).getText());
				int precio = Integer.parseInt(((Label) tablaElemento.getWidget(i, COL_PRECIO)).getText()); 
				prov.setPrecio(precio);
				
				insumo.getProveedor().add(prov);
				
			}
			
		}
	
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.registrarNuevoInsumo(insumo, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					Window.alert("NUEVO INSUMO REGISTRADO!!!");
					padre.remove(numeroElemento(constante.nuevoInsumo()));
				} else
					Window.alert("NO SE PUDO REGISTRAR EL INSUMO");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR DE SERVICIO");

			}
		});
		
		
		
		
		
		
		
	}


	protected void agregarProveedor(ClickEvent event) {
		P_AgregarProveedor nuevo = new P_AgregarProveedor(this);
		nuevo.setGlassEnabled(true);
		nuevo.center();
		nuevo.show();
		
	}
	
	public void capturarDatos(String nombre, String precio, String observaciones) {

		final String nombreProveedor = nombre;
		int fila = tablaElemento.getRowCount();

		Label eliminar = new Label("");
		eliminar.setSize("16px", "16px");
		eliminar.setStyleName("labelBorrar");
		eliminar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eliminar(nombreProveedor);
			}
		});

		tablaElemento.setWidget(fila, COL_NOMBRE, new Label(nombreProveedor));
		tablaElemento.setWidget(fila, COL_PRECIO, new Label(precio));
		tablaElemento.setWidget(fila, COL_OBSERVACIONES, new Label(observaciones));
		tablaElemento.setWidget(fila, COL_BORRAR, eliminar);
		tablaElemento.getFlexCellFormatter().setHorizontalAlignment(fila, COL_BORRAR, HasHorizontalAlignment.ALIGN_CENTER);

		tablaElemento.getRowFormatter().setStyleName(fila, "tablaRenglon");

		elementos.add(nombreProveedor);
	}
	
	
	private void eliminar(String unProveedor) {
		int fila = elementos.indexOf(unProveedor);
		elementos.remove(fila);
		tablaElemento.removeRow(fila + 1);
	}
	
	public void cancelar(ClickEvent event) {
		padre.remove(numeroElemento(constante.nuevoInsumo()));

	}
	
	private int numeroElemento(String titulo) {

		int elemento = -1;
		int contador = 0;

		while (elemento == -1 && contador < padre.getWidgetCount()) {

			if (padre.getWidget(contador).getTitle().compareTo(titulo) == 0)
				elemento = contador;
			else
				contador++;
		}

		return elemento;
	}
}
