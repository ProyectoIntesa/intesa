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
	
	private FlexTable formularioInsumo;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;
	private FlexTable botones1;
	private FlexTable botones2;
	
	private ArrayList<String> elementos;
	private String titulo;
	private InsumoDTO insumoDTO;
	
	
	public P_FormularioInsumo(TabPanel padre) {
	
	
		this.padre = padre;
		
		elementos = new ArrayList<String>();
		
		insumo = new Label(constante.insumo());
		insumo.setStyleName("labelTitulo");
		nombreInsumo = new Label(constante.nombreAsterisco());
		nombreInsumo.setStyleName("gwt-LabelFormulario");
		categoriaInsumo = new Label(constante.categoriaAsterisco());
		categoriaInsumo.setStyleName("gwt-LabelFormulario");
		marcaInsumo = new Label(constante.marcaAsterisco());
		marcaInsumo.setStyleName("gwt-LabelFormulario");
		inventario = new Label(constante.inventariol());
		inventario.setStyleName("labelTitulo");
		loteCompraInsumo = new Label(constante.loteCompraAsterisco());
		loteCompraInsumo.setStyleName("gwt-LabelFormulario");
		stockSeguridadInsumo = new Label(constante.stockSeguridadAsterisco());
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
		
		btnCargar = new Button(constante.agregar());
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

		botones1 = new FlexTable();
		botones1.setWidget(0, 0, btnAgregarProveedor);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones2 = new FlexTable();
		botones2.setWidget(0, 0, btnCargar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones2.setWidget(0, 1, btnCancelar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		
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
		
		formularioInsumo = new FlexTable();
		formularioInsumo.setStyleName("formatoFormulario");
		formularioInsumo.setHeight("637px");
		formularioInsumo.setWidth("920px");
		
		formularioInsumo.setWidget(0, 0, insumo);
		formularioInsumo.getFlexCellFormatter().setColSpan(0, 0, 6);
		
		formularioInsumo.setWidget(1, 0, nombreInsumo);
		formularioInsumo.setWidget(1, 1, nombreInsumoTb);
		formularioInsumo.setWidget(1, 2, categoriaInsumo);
		formularioInsumo.setWidget(1, 3, categoriaInsumoTb);
		formularioInsumo.setWidget(1, 4, marcaInsumo);
		formularioInsumo.setWidget(1, 5, marcaInsumoTb);
		
		formularioInsumo.setWidget(2, 0, inventario);
		formularioInsumo.getFlexCellFormatter().setColSpan(2, 0, 6);
		
		formularioInsumo.setWidget(3, 0, loteCompraInsumo);
		formularioInsumo.setWidget(3, 1, loteCompraInsumoTb);
		formularioInsumo.setWidget(3, 2, stockSeguridadInsumo);
		formularioInsumo.setWidget(3, 3, stockSeguridadInsumoTb);
		
		formularioInsumo.setWidget(4, 0, observaciones);
		formularioInsumo.getFlexCellFormatter().setColSpan(4, 0, 6);
		
		formularioInsumo.setWidget(5, 0, observacionesTb);
		formularioInsumo.getFlexCellFormatter().setColSpan(5, 0, 6);

		formularioInsumo.setWidget(6, 0, proveedor);
		formularioInsumo.getFlexCellFormatter().setColSpan(6, 0, 6);
		
		
		formularioInsumo.setWidget(7, 0, botones1);
		formularioInsumo.getFlexCellFormatter().setColSpan(7, 0, 6);
		formularioInsumo.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		formularioInsumo.setWidget(8, 0, contenedorTabla);
		formularioInsumo.getFlexCellFormatter().setColSpan(8, 0, 6);
		
		formularioInsumo.setWidget(9, 0, pie);
		formularioInsumo.getFlexCellFormatter().setColSpan(9, 0, 6);
		
		formularioInsumo.setWidget(10, 0, botones2);
		formularioInsumo.getFlexCellFormatter().setColSpan(10, 0, 6);
		formularioInsumo.getCellFormatter().setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		initWidget(formularioInsumo);
	}

	public P_FormularioInsumo(TabPanel padre, InsumoDTO insumoDto, String titulo) {
		
		
		this.padre = padre;
		this.titulo = titulo;
		this.insumoDTO = insumoDto;
		
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
		loteCompraInsumo = new Label(constante.loteCompraAsterisco());
		loteCompraInsumo.setStyleName("gwt-LabelFormulario");
		stockSeguridadInsumo = new Label(constante.stockSeguridadAsterisco());
		stockSeguridadInsumo.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		proveedor = new Label(constante.proveedores());
		proveedor.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		nombreInsumoTb = new TextBox();
		nombreInsumoTb.setText(insumoDto.getNombre());
		nombreInsumoTb.setEnabled(false);
		categoriaInsumoTb = new TextBox();
		categoriaInsumoTb.setText(insumoDto.getCategoria());
		categoriaInsumoTb.setEnabled(false);
		marcaInsumoTb = new TextBox();
		marcaInsumoTb.setText(insumoDto.getMarca());
		marcaInsumoTb.setEnabled(false);
		loteCompraInsumoTb = new TextBox();
		loteCompraInsumoTb.setText(""+insumoDto.getLoteCompra());
		stockSeguridadInsumoTb = new TextBox();
		stockSeguridadInsumoTb.setText(""+insumoDto.getStockSeguridad());
		observacionesTb = new TextArea();
		observacionesTb.setText(insumoDto.getObservaciones());
		observacionesTb.setDirectionEstimator(false);
		observacionesTb.setWidth("100%");
		
		
		btnAgregarProveedor = new Button(constante.agregarProveedor());
		btnAgregarProveedor.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarProveedor(event);

			}
		});

		btnCargar = new Button(constante.guardar());
		btnCargar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cargarInsumoModificado(event);
			}
		});

		btnCancelar = new Button(constante.cancelar());
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelarModificacion(event);
			}
		});
		
		botones1 = new FlexTable();
		botones1.setWidget(0, 0, btnAgregarProveedor);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones2 = new FlexTable();
		botones2.setWidget(0, 0, btnCargar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones2.setWidget(0, 1, btnCancelar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
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
		
		formularioInsumo = new FlexTable();
		formularioInsumo.setStyleName("formatoFormulario");
		formularioInsumo.setHeight("637px");
		formularioInsumo.setWidth("920px");
		
		formularioInsumo.setWidget(0, 0, insumo);
		formularioInsumo.getFlexCellFormatter().setColSpan(0, 0, 6);
		
		formularioInsumo.setWidget(1, 0, nombreInsumo);
		formularioInsumo.setWidget(1, 1, nombreInsumoTb);
		formularioInsumo.setWidget(1, 2, categoriaInsumo);
		formularioInsumo.setWidget(1, 3, categoriaInsumoTb);
		formularioInsumo.setWidget(1, 4, marcaInsumo);
		formularioInsumo.setWidget(1, 5, marcaInsumoTb);
		
		formularioInsumo.setWidget(2, 0, inventario);
		formularioInsumo.getFlexCellFormatter().setColSpan(2, 0, 6);
		
		formularioInsumo.setWidget(3, 0, loteCompraInsumo);
		formularioInsumo.setWidget(3, 1, loteCompraInsumoTb);
		formularioInsumo.setWidget(3, 2, stockSeguridadInsumo);
		formularioInsumo.setWidget(3, 3, stockSeguridadInsumoTb);
		
		formularioInsumo.setWidget(4, 0, observaciones);
		formularioInsumo.getFlexCellFormatter().setColSpan(4, 0, 6);
		
		formularioInsumo.setWidget(5, 0, observacionesTb);
		formularioInsumo.getFlexCellFormatter().setColSpan(5, 0, 6);

		formularioInsumo.setWidget(6, 0, proveedor);
		formularioInsumo.getFlexCellFormatter().setColSpan(6, 0, 6);
		
		formularioInsumo.setWidget(7, 0, botones1);
		formularioInsumo.getFlexCellFormatter().setColSpan(7, 0, 6);
		formularioInsumo.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		formularioInsumo.setWidget(8, 0, contenedorTabla);
		formularioInsumo.getFlexCellFormatter().setColSpan(8, 0, 6);
		
		formularioInsumo.setWidget(9, 0, pie);
		formularioInsumo.getFlexCellFormatter().setColSpan(9, 0, 6);
		
		formularioInsumo.setWidget(10, 0, botones2);
		formularioInsumo.getFlexCellFormatter().setColSpan(10, 0, 6);
		formularioInsumo.getCellFormatter().setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		
		for (int i = 0; i < insumoDto.getProveedor().size(); i++){
			
			final String nombreContacto = insumoDto.getProveedor().get(i).getNombre();
			
			Label eliminar = new Label("");
			eliminar.setSize("16px", "16px");
			eliminar.setStyleName("labelBorrar");
			eliminar.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					eliminarProveedor(nombreContacto);
				}
			});
			
			tablaElemento.setWidget(i+1, COL_NOMBRE, new Label(insumoDto.getProveedor().get(i).getNombre()));
			if(insumoDto.getProveedor().get(i).getPrecio() != null)
				tablaElemento.setWidget(i+1, COL_PRECIO, new Label(""+insumoDto.getProveedor().get(i).getPrecio()));
			else
				tablaElemento.setWidget(i+1, COL_PRECIO, new Label(""));
			tablaElemento.setWidget(i+1, COL_OBSERVACIONES, new Label(insumoDto.getProveedor().get(i).getObservaciones()));
			tablaElemento.setWidget(i+1, COL_BORRAR, eliminar);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_BORRAR, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.getRowFormatter().setStyleName(i+1, "tablaRenglon");
			
			
			elementos.add(nombreContacto);
			
		}
		
		
		
		initWidget(formularioInsumo);
	}
	
	protected void eliminarProveedor(String nombreContacto) {
		
		int fila = elementos.indexOf(nombreContacto);
		elementos.remove(fila);
		tablaElemento.removeRow(fila + 1);
		
		insumoDTO.getProveedor().remove(fila);
		
	}

	protected void cargarInsumo(ClickEvent event) {
		
		Validaciones validar = new Validaciones();
		
		boolean vNombreInsumo = validar.textBoxVacio(this.nombreInsumoTb.getText());
		boolean vCategoriaInsumo = validar.textBoxVacio(this.categoriaInsumoTb.getText());
		boolean vMarcaInsumo = validar.textBoxVacio(this.marcaInsumoTb.getText());
		boolean vLote1 = validar.textBoxVacio(this.loteCompraInsumoTb.getText());
		boolean vLote2 = validar.textBoxSoloNumeros(this.loteCompraInsumoTb.getText());
		boolean vStock1 = validar.textBoxVacio(this.stockSeguridadInsumoTb.getText());
		boolean vStock2 = validar.textBoxSoloNumeros(this.stockSeguridadInsumoTb.getText());
		
		
		if(!vNombreInsumo && !vCategoriaInsumo && !vMarcaInsumo && !vLote1 && !vStock1 && vLote2 && vStock2){
			
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
					
					String pre = (((Label) tablaElemento.getWidget(i, COL_PRECIO)).getText());
					if(pre.compareTo("") != 0){
						double precio = Double.parseDouble(((Label) tablaElemento.getWidget(i, COL_PRECIO)).getText()); 
						prov.setPrecio(precio);						
					}
					
					insumo.getProveedor().add(prov);
					
				}
				
			}
		
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.registrarNuevoInsumo(insumo, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						Window.alert("El insumo ha sido registrado");
						padre.remove(numeroElemento(constante.nuevoInsumo()));
					} else
						Window.alert("No se ha podido registrar el insumo");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR DE SERVICIO");

				}
			});
			
			
		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}	
	}
	
	protected void cargarInsumoModificado(ClickEvent event){
		
		Validaciones validar = new Validaciones();
		
		boolean vLote1 = validar.textBoxVacio(this.loteCompraInsumoTb.getText());
		boolean vLote2 = validar.textBoxSoloNumeros(this.loteCompraInsumoTb.getText());
		boolean vStock1 = validar.textBoxVacio(this.stockSeguridadInsumoTb.getText());
		boolean vStock2 = validar.textBoxSoloNumeros(this.stockSeguridadInsumoTb.getText());
		
		
		if(!vLote1 && !vStock1 && vLote2 && vStock2){
			
			InsumoDTO insumo = new InsumoDTO();
			insumo.setIdInsumo(this.insumoDTO.getIdInsumo());
			insumo.setNombre(this.nombreInsumoTb.getText());
			insumo.setMarca(this.marcaInsumoTb.getText());
			insumo.setCategoria(this.categoriaInsumoTb.getText());
			insumo.setObservaciones(this.observacionesTb.getText());
			int lote = Integer.parseInt(this.loteCompraInsumoTb.getText());
			insumo.setLoteCompra(lote);
			int stock = Integer.parseInt(this.stockSeguridadInsumoTb.getText());
			insumo.setStockSeguridad(stock);
			insumo.setCantidad(this.insumoDTO.getCantidad());
			insumo.setNecesidadCompra(this.insumoDTO.isNecesidadCompra());
			
			
			if (tablaElemento.getRowCount() > 1){
				
				for (int i = 1; i < tablaElemento.getRowCount(); i++){
					
					ProveedorDeInsumosDTO prov = new ProveedorDeInsumosDTO();
					prov.setNombre(((Label) tablaElemento.getWidget(i, COL_NOMBRE)).getText());
					prov.setObservaciones(((Label) tablaElemento.getWidget(i, COL_OBSERVACIONES)).getText());
					
					String pre = (((Label) tablaElemento.getWidget(i, COL_PRECIO)).getText());
					if(pre.compareTo("") != 0){
						double precio = Double.parseDouble(((Label) tablaElemento.getWidget(i, COL_PRECIO)).getText()); 
						prov.setPrecio(precio);						
					}

					
					insumo.getProveedor().add(prov);
					
				}
				
			}
		
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.registrarCambioInsumo(insumo, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						Window.alert("El insumo ha sido modificado");
						padre.remove(numeroElemento(constante.modificarInsumo()));
					} else
						Window.alert("NO se ha podido modificar el insumo");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR DE SERVICIO");

				}
			});
		}
		

		
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");			
		}
	}

	protected void agregarProveedor(ClickEvent event) {
		P_AgregarProveedor nuevo = new P_AgregarProveedor(this);
		nuevo.setGlassEnabled(true);
		nuevo.center();
		nuevo.show();
		
	}
	
	public void capturarDatos(String nombre, String precio, String observaciones) {

		boolean bandera = false;
		
		for(int i = 1; i < tablaElemento.getRowCount(); i++){
			if(nombre.compareTo(((Label)tablaElemento.getWidget(i, COL_NOMBRE)).getText()) == 0)
				bandera = true;
		}
		
		if(bandera == false){
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
			if(precio != null)
				tablaElemento.setWidget(fila, COL_PRECIO, new Label(precio));
			else
				tablaElemento.setWidget(fila, COL_PRECIO, new Label(""));
			tablaElemento.setWidget(fila, COL_OBSERVACIONES, new Label(observaciones));
			tablaElemento.setWidget(fila, COL_BORRAR, eliminar);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(fila, COL_BORRAR, HasHorizontalAlignment.ALIGN_CENTER);

			tablaElemento.getRowFormatter().setStyleName(fila, "tablaRenglon");

			elementos.add(nombreProveedor);
		}
		else
			Window.alert("No es posible agregar 2 veces el mismo proveedor para el mismo insumo");
	}
		
	private void eliminar(String unProveedor) {
		int fila = elementos.indexOf(unProveedor);
		elementos.remove(fila);
		tablaElemento.removeRow(fila + 1);
	}
	
	public void cancelar(ClickEvent event) {
		padre.remove(numeroElemento(constante.nuevoInsumo()));

	}
	
	public void cancelarModificacion(ClickEvent event) {
		padre.remove(numeroElemento(constante.modificarInsumo()));

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
