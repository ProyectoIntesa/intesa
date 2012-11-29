package edu.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.ProveedorDeInsumosDTO;

public class P_RequerimientoInsumo extends PopupPanel {

	private static final int COL_INSUMO = 0;
	private static final int COL_MARCA = 1;
	private static final int COL_PROVEEDOR = 2;
	private static final int COL_BORRAR = 3;
	
	private static final int COL_CHECK = 0;
	private static final int COL_INSUMONEC = 1;
	private static final int COL_MARCANEC = 2;
	private static final int COL_PROVEEDORNEC = 3;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	private Label titulo;
	private Label tituloReqNec;
	private Label tituloReqAdic;
	private Label pie;
	private Label insumoAdic;
	private	Label marcaInsumoAdic;
	private Label provInsumoAdic;
	
	private Button salir;
	private Button armarOrden;
	private Button agregar;
	
	private ListBox insumo;
	private ListBox marca;
	private ListBox proveedor;
	
	private FlexTable contenedor;
	private ScrollPanel contenedorTablaReqNec;
	private FlexTable tablaElementoReqNec;
	private ScrollPanel contenedorTablaReqAdic;
	private FlexTable tablaElementoReqAdic;
	private FlexTable botones;
	
	private List<String> listaInsumos;
	private List<String> listaMarcas;
	private List<String> listaProveedores;
	
	private List<String[]> listaTablaInsumosAdic;
	
	
	public P_RequerimientoInsumo(){
		
		super(false);
		setStyleName("fondoPopup");
		
		listaTablaInsumosAdic = new LinkedList<String[]>();
		
		titulo = new Label(constante.requerimientosDeInsumo());
		titulo.setStyleName("labelTitulo");
		
		tituloReqNec = new Label(constante.requerimientosNecesarios());
		tituloReqNec.setStyleName("labelTitulo");
				
		contenedorTablaReqNec = new ScrollPanel();
		contenedorTablaReqNec.setStyleName("tabla");
		contenedorTablaReqNec.setHeight("200px");
		tablaElementoReqNec = new FlexTable();
		contenedorTablaReqNec.setWidget(tablaElementoReqNec);
		tablaElementoReqNec.setSize("100%", "100%");

		tablaElementoReqNec.setText(0, COL_CHECK, "");
		tablaElementoReqNec.getCellFormatter().setWidth(0, COL_CHECK, "5%");		
		tablaElementoReqNec.setText(0, COL_INSUMONEC, constante.insumo());
		tablaElementoReqNec.getCellFormatter().setWidth(0, COL_INSUMONEC, "31%");
		tablaElementoReqNec.setText(0, COL_MARCANEC, constante.marca());
		tablaElementoReqNec.getCellFormatter().setWidth(0, COL_MARCANEC, "31%");
		tablaElementoReqNec.setText(0, COL_PROVEEDORNEC, constante.proveedor());
		tablaElementoReqNec.getCellFormatter().setWidth(0, COL_PROVEEDORNEC, "31%");
		tablaElementoReqNec.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

		comprasService.getRequerimientosInsumosCompletos(new AsyncCallback<List<InsumoDTO>>() {
			@Override
			public void onSuccess(List<InsumoDTO> result) {
				cargarListaTablaInsumosNec(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de marcas");
			}
		});
				
		
		
		tituloReqAdic = new Label(constante.requerimientosAdicionales());
		tituloReqAdic.setStyleName("labelTitulo");
		
		listaInsumos = new LinkedList<String>();
		
		
		comprasService.getNombresInsumos("", new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarListaInsumos(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de insumos");
			}
		});
		
		
		listaMarcas = new LinkedList<String>();
		listaProveedores = new LinkedList<String>();
		
		insumoAdic = new Label(constante.insumo());
		insumoAdic.setStyleName("gwt-LabelFormulario");
		marcaInsumoAdic = new Label(constante.marca());
		marcaInsumoAdic.setStyleName("gwt-LabelFormulario");
		provInsumoAdic = new Label(constante.proveedor());
		provInsumoAdic.setStyleName("gwt-LabelFormulario");
		
		insumo = new ListBox();
		insumo.setStyleName("gwt-TextArea");
		insumo.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
								
				if(insumo.getSelectedIndex() != 0){
					marca.setEnabled(true);
					
					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

					comprasService.getNombresMarcasSegunInsumo(insumo.getItemText(insumo.getSelectedIndex()), new AsyncCallback<List<String>>() {
						@Override
						public void onSuccess(List<String> result) {
							cargarListaMarcas(result);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("No se pudo cargar la lista de marcas");
						}
					});
					
					
					
				}
				else
					marca.setEnabled(false);
				
			}
		});
		
		marca = new ListBox();
		marca.setStyleName("gwt-TextArea");
		marca.setEnabled(false);
		marca.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
							
				if(marca.getSelectedIndex() != 0){
					proveedor.setEnabled(true);
					
					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

					String nombreInsumo = insumo.getItemText(insumo.getSelectedIndex());
					String nombreMarca = marca.getItemText(marca.getSelectedIndex());
					
					comprasService.getNombresProvSegunInsumoYMarca(nombreInsumo,nombreMarca, new AsyncCallback<List<String>>() {
						@Override
						public void onSuccess(List<String> result) {
							cargarListaProveedores(result);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("No se pudo cargar la lista de proveedores");
						}
					});

					
				}
				else
					proveedor.setEnabled(false);
				
			}
		});
		
		proveedor = new ListBox();
		proveedor.setStyleName("gwt-TextArea");	
		proveedor.setEnabled(false);

		
		agregar = new Button(constante.agregar());
		agregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				agregarInsumoAdicional();			
				
			}
		});
		
		contenedorTablaReqAdic = new ScrollPanel();
		contenedorTablaReqAdic.setStyleName("tabla");
		contenedorTablaReqAdic.setHeight("200px");
		tablaElementoReqAdic = new FlexTable();
		contenedorTablaReqAdic.setWidget(tablaElementoReqAdic);
		tablaElementoReqAdic.setSize("100%", "100%");
		
		tablaElementoReqAdic.setText(0, COL_INSUMO, constante.insumo());
		tablaElementoReqAdic.getCellFormatter().setWidth(0, COL_INSUMO, "31%");
		tablaElementoReqAdic.setText(0, COL_MARCA, constante.marca());
		tablaElementoReqAdic.getCellFormatter().setWidth(0, COL_MARCA, "31%");
		tablaElementoReqAdic.setText(0, COL_PROVEEDOR, constante.proveedor());
		tablaElementoReqAdic.getCellFormatter().setWidth(0, COL_PROVEEDOR, "31%");
		tablaElementoReqAdic.setText(0, COL_BORRAR, "");
		tablaElementoReqAdic.getCellFormatter().setWidth(0, COL_BORRAR, "5%");
		tablaElementoReqAdic.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		pie = new Label();
		pie.setStyleName("labelTitulo");
			
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		armarOrden = new Button(constante.armarOrden());
		armarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//salir();
			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, armarOrden);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		contenedor = new FlexTable();
		contenedor.setSize("800px", "300px");
		
		contenedor.setWidget(0, 0, titulo);
		contenedor.getFlexCellFormatter().setColSpan(0, 0, 3);

		contenedor.setWidget(1, 0, tituloReqNec);
		contenedor.getFlexCellFormatter().setColSpan(1, 0, 3);
		
		contenedor.setWidget(2, 0, contenedorTablaReqNec);
		contenedor.getFlexCellFormatter().setColSpan(2, 0, 3);
		
		contenedor.setWidget(3, 0, tituloReqAdic);
		contenedor.getFlexCellFormatter().setColSpan(3, 0, 3);
		
		contenedor.setWidget(4, 0, insumoAdic);
		contenedor.setWidget(5, 0, insumo);
		contenedor.setWidget(4, 1, marcaInsumoAdic);
		contenedor.setWidget(5, 1, marca);
		contenedor.setWidget(4, 2, provInsumoAdic);
		contenedor.setWidget(5, 2, proveedor);
		
		contenedor.setWidget(6, 0, agregar);
		contenedor.getFlexCellFormatter().setColSpan(6, 0, 3);
		contenedor.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		contenedor.setWidget(7, 0, contenedorTablaReqAdic);
		contenedor.getFlexCellFormatter().setColSpan(7, 0, 3);
		
		contenedor.setWidget(8, 0, pie);
		contenedor.getFlexCellFormatter().setColSpan(8, 0, 3);		

		contenedor.setWidget(9, 0, botones);
		contenedor.getFlexCellFormatter().setColSpan(9, 0, 3);
		
		

		setWidget(contenedor);

		
	}
		
	protected void cargarListaTablaInsumosNec(List<InsumoDTO> result) {
		
		for (InsumoDTO insumoDTO : result) {
			
			CheckBox check = new CheckBox();
			Label nombreInsumo = new Label(insumoDTO.getNombre());
			Label nombreMarca = new Label(insumoDTO.getMarca());
			ListBox proveedores = new ListBox();
			proveedores.setStyleName("gwt-TextArea");
			for (ProveedorDeInsumosDTO prov : insumoDTO.getProveedor()) {
				
				proveedores.addItem(prov.getNombre());		
				
			}
			int fila = tablaElementoReqNec.getRowCount();
			
			tablaElementoReqNec.setWidget(fila, COL_CHECK, check);
			tablaElementoReqNec.getFlexCellFormatter().setHorizontalAlignment(fila, COL_CHECK, HasHorizontalAlignment.ALIGN_CENTER);		
			tablaElementoReqNec.setWidget(fila, COL_INSUMONEC, nombreInsumo);
			tablaElementoReqNec.setWidget(fila, COL_MARCANEC, nombreMarca);
			tablaElementoReqNec.setWidget(fila, COL_PROVEEDORNEC, proveedores);
			tablaElementoReqNec.getRowFormatter().setStyleName(fila, "tablaRenglon");		
		}
	
		
	}

	protected void agregarInsumoAdicional() {
		
		if(insumo.getSelectedIndex() != 0 && marca.getSelectedIndex() != 0 && proveedor.getSelectedIndex() != 0){
			
			final String nombreInsumo = insumo.getItemText(insumo.getSelectedIndex());
			final String nombreMarca = marca.getItemText(marca.getSelectedIndex());
			String nombreProveedor = proveedor.getItemText(proveedor.getSelectedIndex());
			
			int fila = tablaElementoReqAdic.getRowCount();

			Label eliminar = new Label("");
			eliminar.setSize("16px", "16px");
			eliminar.setStyleName("labelBorrar");
			eliminar.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					String[] nombreYMarca={nombreInsumo,nombreMarca};
					eliminar(nombreYMarca);
				}
			});

			tablaElementoReqAdic.setWidget(fila, COL_INSUMO, new Label(nombreInsumo));
			tablaElementoReqAdic.setWidget(fila, COL_MARCA, new Label(nombreMarca));
			tablaElementoReqAdic.setWidget(fila, COL_PROVEEDOR, new Label(nombreProveedor));
			tablaElementoReqAdic.setWidget(fila, COL_BORRAR, eliminar);
			tablaElementoReqAdic.getFlexCellFormatter().setHorizontalAlignment(fila, COL_BORRAR, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementoReqAdic.getRowFormatter().setStyleName(fila, "tablaRenglon");

			String[] nombreYMarca={nombreInsumo,nombreMarca};
			this.listaTablaInsumosAdic.add(nombreYMarca);
			
		}
		else{
			Window.alert("Se deben seleccionar todos los campos");
		}
		
		
		
	}

	private void eliminar(String [] insumoMarca) {
		
		int fila = -1;
		
		for (int i = 0; i < this.listaTablaInsumosAdic.size(); i++) {
			
			String insumoAux = this.listaTablaInsumosAdic.get(i)[0];
			String marcaAux = this.listaTablaInsumosAdic.get(i)[1];
			
			if(insumoMarca[0].compareTo(insumoAux) == 0 && insumoMarca[1].compareTo(marcaAux) == 0){
				
				fila = i;
				break;
			}
		}
		
		if(fila != -1){
			
			this.listaTablaInsumosAdic.remove(fila);
			tablaElementoReqAdic.removeRow(fila + 1);
		}
		
		
	}

	protected void cargarListaInsumos(List<String> result) {
		
		List <String> aux = new LinkedList<String>();
		aux = result;
		
		this.listaInsumos.add("ninguno");
		
		for (String cadena : aux) {
			if(!this.listaInsumos.contains(cadena))
				this.listaInsumos.add(cadena);
		}
		
		
		for (String insumo : listaInsumos) {
			this.insumo.addItem(insumo);
		}
	}

	protected void cargarListaMarcas(List<String> result) {
		
		this.marca.clear();
		
		this.listaMarcas = result;
		this.listaMarcas.add(0,"ninguno");
		
		for (String marca : listaMarcas) {
			this.marca.addItem(marca);
		}
		
	}
		
	protected void cargarListaProveedores(List<String> result) {
		
		this.proveedor.clear();
		
		this.listaProveedores = result;
		this.listaProveedores.add(0,"ninguno");
		
		for (String prov : listaProveedores) {
			this.proveedor.addItem(prov);
		}
		
	}

	protected void salir() {
		this.hide();
	}
	
	
	
}
