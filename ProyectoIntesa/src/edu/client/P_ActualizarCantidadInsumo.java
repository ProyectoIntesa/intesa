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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

import edu.client.AlmacenService.AlmacenService;
import edu.client.AlmacenService.AlmacenServiceAsync;
import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.InsumoDTO;

public class P_ActualizarCantidadInsumo extends PopupPanel {

	private Constantes constante = GWT.create(Constantes.class);
	
	private Label insumo;
	private	Label marca;
	private Label titulo;
	private Label cantDisponible;
	private Label cantReal;
	private Label lineaMedia;
	private Label pie;
	
	private ListBox insumoLb;
	private ListBox marcaLb;
	
	private Button buscar;
	private Button cancelar;
	private Button aceptar;
	
	private FlexTable contenedor;
	private FlexTable botones;
	private FlexTable botones1;
	
	private TextBox cantRealTb;
	
	private InsumoDTO insumoBuscado;
	
	
	public P_ActualizarCantidadInsumo() {
		
		super(false);
		setStyleName("fondoPopup");
		
		titulo = new Label("BUSCAR INSUMO PARA ACTUALIZAR SU CANTIDAD EN INVENTARIO");
		titulo.setStyleName("labelTitulo");
		lineaMedia = new Label("");
		lineaMedia.setStyleName("labelTitulo");
		pie = new Label("");
		pie.setStyleName("labelTitulo");
		
		insumo = new Label(constante.insumo());
		insumo.setStyleName("gwt-LabelFormulario");
		marca = new Label(constante.marca());
		marca.setStyleName("gwt-LabelFormulario");
		cantDisponible = new Label(constante.cantDisponible());
		cantDisponible.setStyleName("gwt-LabelFormulario");
		cantReal = new Label(constante.cantReal());
		cantReal.setStyleName("gwt-LabelFormulario");
		
		cantRealTb = new TextBox();
		
		insumoBuscado = new InsumoDTO();
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
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
		
		insumoLb = new ListBox();
		insumoLb.setStyleName("gwt-ListBox");
		insumoLb.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
								
				if(insumoLb.getSelectedIndex() != 0){
					
					marcaLb.setEnabled(true);
					
					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

					comprasService.getNombresMarcasSegunInsumo(insumoLb.getItemText(insumoLb.getSelectedIndex()), new AsyncCallback<List<String>>() {
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
				else{
					marcaLb.setEnabled(false);
				}
				
			}
		});
		
		marcaLb = new ListBox();
		marcaLb.setStyleName("gwt-ListBox");	
		marcaLb.setEnabled(false);
		
		buscar = new Button(constante.buscar());
		buscar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscarCantidadEnInventario();				
			}
		});
		
		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		aceptar = new Button(constante.aceptar());
		aceptar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				boolean confirm = Window.confirm("Está seguro de que desea modificar la cantidad en inventario del insumo buscado?");
				if(confirm){
					modificarCantidadEnInventario();
				}	
			}
		});
		
		botones = new FlexTable();
		botones.setWidget(0, 0, buscar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		botones1 = new FlexTable();
		botones1.setWidget(0, 0, aceptar);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones1.setWidget(0, 1, cancelar);
		botones1.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		contenedor = new FlexTable();
		contenedor.setSize("500px", "300px");
		
		contenedor.setWidget(0, 0, titulo);
		contenedor.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		contenedor.setWidget(1, 0, insumo);
		contenedor.setWidget(1, 1, insumoLb);
		
		contenedor.setWidget(2, 0, marca);
		contenedor.setWidget(2, 1, marcaLb);
		
		contenedor.setWidget(3, 0, botones);
		contenedor.getFlexCellFormatter().setColSpan(3, 0, 2);
		contenedor.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		contenedor.setWidget(4, 0, lineaMedia);
		contenedor.getFlexCellFormatter().setColSpan(4, 0, 2);
		
		contenedor.setWidget(5, 0, cantDisponible);
		
		contenedor.setWidget(6, 0, cantReal);
		contenedor.setWidget(6, 1, cantRealTb);	
		
		contenedor.setWidget(7, 0, pie);
		contenedor.getFlexCellFormatter().setColSpan(7, 0, 2);
		
		contenedor.setWidget(8, 0, botones1);
		contenedor.getFlexCellFormatter().setColSpan(8, 0, 2);
		contenedor.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		

		
		setWidget(contenedor);
		
	}
		
	protected void buscarCantidadEnInventario(){

		if(insumoLb.getSelectedIndex() != 0 && marcaLb.getSelectedIndex() != 0){
						
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.getInsumoCompleto(insumoLb.getItemText(insumoLb.getSelectedIndex()), marcaLb.getItemText(marcaLb.getSelectedIndex()), new AsyncCallback<InsumoDTO>() {
				@Override
				public void onSuccess(InsumoDTO result) {
					insumoBuscado = result;
					cantDisponible = new Label(constante.cantDisponible()+": "+insumoBuscado.getCantidad());
					contenedor.setWidget(5, 0, cantDisponible);
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
				}
			});
		}
		else{
			Window.alert("Se debe seleccionar el nombre y marca del insumo");
		}
		
	}
	
	protected void modificarCantidadEnInventario(){
		
		Validaciones validar = new Validaciones();
		boolean cantReal1 = false;
		cantReal1 = validar.textBoxVacio(cantRealTb.getText());
		boolean cantReal2 = false;
		cantReal2 = validar.textBoxSoloNumeros(cantRealTb.getText());
		
		if(!cantReal1 && cantReal2){
			
			Integer cantNueva = Integer.parseInt(cantRealTb.getText());
		
			insumoBuscado.setCantidad(cantNueva);
			
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.registrarCambioInsumo(insumoBuscado, new AsyncCallback<Boolean>() {
				@Override
				public void onSuccess(Boolean result) {
					
					if(result){
						Window.alert("La cantidad de insumo ha sido modificada");
						buscarCantidadEnInventario();
						cantRealTb.setText("");
					}
					else
						Window.alert("La cantidad de insumo NO ha sido modificada");
					
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
				}
			});
			
			
			
		}
		else{
			if(cantReal1)
				Window.alert("La cantidad de insumos real no pude ser vacia");
			else if(!cantReal2)
				Window.alert("La cantidad de insumos no debe poseer letras y debe ser un número entero");
		}
		
		
	}
	
	protected void cargarListaInsumos(List<String> result) {
		
		List <String> aux = new LinkedList<String>();
		aux = result;
		List <String> listaInsumos = new LinkedList<String>();
		
		listaInsumos.add("ninguno");
		
		for (String cadena : aux) {
			if(!listaInsumos.contains(cadena))
				listaInsumos.add(cadena);
		}
		for (String insumo : listaInsumos) {
			this.insumoLb.addItem(insumo);
		}	
	}
	
	protected void cargarListaMarcas(List<String> result) {
		
		this.marcaLb.clear();
		List <String> listaMarcas = new LinkedList<String>();
		
		listaMarcas = result;
		listaMarcas.add(0,"ninguno");
		
		for (String marca : listaMarcas) {
			this.marcaLb.addItem(marca);
		}
		
	}
	
	protected void salir(){
		this.hide();
	}
	
}
