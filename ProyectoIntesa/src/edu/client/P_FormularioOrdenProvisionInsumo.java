package edu.client;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.EmpleadoDTO;

public class P_FormularioOrdenProvisionInsumo extends Composite {
	
	private static final int COL_ITEM = 0;
	private static final int COL_INSUMO = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_CANTREQUERIDA = 3;
	private static final int COL_BORRAR = 4;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	private TabPanel padre;
	
	private Label titulo;
	private Label empPide;
	private Label fechaEdicion;
	private Label insumo;
	private Label marca;
	private Label cant;
	private Label observaciones;
	private Label pie;
	
	private ListBox empPideLb;
	private ListBox insumoLb;
	private ListBox marcaLb;
	private TextBox cantTb;
	private TextArea observacionesTa;
	
	private Button agregar;
	private Button cancelar;
	private Button generar;
	
	private FlexTable formulario;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;
	
	private List<String> listaInsumos;
	private List<String> listaMarcas;
	private EmpleadoDTO empleado;
	
	public P_FormularioOrdenProvisionInsumo(TabPanel padre, String responsable){
		
		this.padre = padre;
		
		String nombre = responsable.split(", ")[1];
		String apellido = responsable.split(", ")[0];
		String rol = "PRODUCCION";
		
		
		listaInsumos = new LinkedList<String>();
		listaMarcas = new LinkedList<String>();
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.getNombresInsumos("", new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarListaInsumos(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de insumos");
			}
		});
		
		
		
		
		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(new Date());
		
		titulo = new Label(constante.ordenDeProvisionDeInsumos());
		titulo.setStyleName("labelTitulo");
		empPide = new Label(constante.para()+": ");
		empPide.setStyleName("gwt-LabelFormulario");
		fechaEdicion = new Label(constante.fechaEdicion()+": "+fecha);
		fechaEdicion.setStyleName("gwt-LabelFormulario");
		insumo = new Label(constante.insumo());
		insumo.setStyleName("gwt-LabelFormulario");
		marca = new Label(constante.marca());
		marca.setStyleName("gwt-LabelFormulario");
		cant = new Label(constante.cantidad());
		cant.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		pie = new Label("");
		pie.setStyleName("labelTitulo");
		
		
		agregar = new Button(constante.agregar());
		agregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//registrarOrden("GENERADA");
			}
		});
		
		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelar();
			}
		});
		
		generar = new Button(constante.generar());
		generar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//registrarOrden("GENERADA");
			}
		});
		
		empPideLb = new ListBox();
		empPideLb.setStyleName("gwt-ListBox");
		
		produccionService.getEmpleado(nombre, apellido, rol, new AsyncCallback<EmpleadoDTO>() {
			@Override
			public void onSuccess(EmpleadoDTO result) {
				empleado = result;
				cargarListaEmpleadosACargo(result);
				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de empleados");
			}
		});
		
		
		
		insumoLb = new ListBox();
		insumoLb.setStyleName("gwt-ListBox");
		insumoLb.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
								
				if(insumoLb.getSelectedIndex() != 0){
					marcaLb.setEnabled(true);
					
					ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);

					produccionService.getNombresMarcasSegunInsumo(insumoLb.getItemText(insumoLb.getSelectedIndex()), new AsyncCallback<List<String>>() {
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

		cantTb = new TextBox();
		
		observacionesTa = new TextArea();
		observacionesTa.setDirectionEstimator(false);
		observacionesTa.setWidth("100%");

		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("400px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		tablaElemento.setText(0, COL_ITEM, constante.item());
		tablaElemento.getCellFormatter().setWidth(0, COL_ITEM, "5%");
		tablaElemento.setText(0, COL_INSUMO, constante.insumo());
		tablaElemento.getCellFormatter().setWidth(0, COL_INSUMO, "35%");
		tablaElemento.setText(0, COL_MARCA, constante.marca());
		tablaElemento.getCellFormatter().setWidth(0, COL_MARCA, "35%");
		tablaElemento.setText(0, COL_CANTREQUERIDA, constante.cantidad());
		tablaElemento.getCellFormatter().setWidth(0, COL_CANTREQUERIDA, "20%");
		tablaElemento.setText(0, COL_BORRAR, "");
		tablaElemento.getCellFormatter().setWidth(0, COL_BORRAR, "5%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		formulario = new FlexTable();
		formulario.setStyleName("formatoFormulario");
		formulario.setHeight("637px");
		formulario.setWidth("920px");
		
		formulario.setWidget(0, 0, titulo);
		formulario.getFlexCellFormatter().setColSpan(0, 0, 6);
		
		formulario.setWidget(1, 0, empPide);
		formulario.setWidget(1, 1, empPideLb);
		formulario.setWidget(1, 5, fechaEdicion);
		
		formulario.setWidget(2, 0, insumo);
		formulario.setWidget(2, 1, insumoLb);
		formulario.getCellFormatter().setWidth(2, 1, "30%");
		formulario.setWidget(2, 2, marca);
		formulario.setWidget(2, 3, marcaLb);
		formulario.getCellFormatter().setWidth(2, 3, "30%");
		formulario.setWidget(2, 4, cant);
		formulario.setWidget(2, 5, cantTb);
		formulario.getCellFormatter().setWidth(2, 5, "30%");
		
		formulario.setWidget(3, 0, agregar);
		formulario.getFlexCellFormatter().setColSpan(3, 0, 6);
		formulario.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		formulario.setWidget(4, 0, contenedorTabla);
		formulario.getFlexCellFormatter().setColSpan(4, 0, 6);
		
		formulario.setWidget(5, 0, observaciones);
		formulario.getFlexCellFormatter().setColSpan(5, 0, 6);
		formulario.setWidget(6, 0, observacionesTa);
		formulario.getFlexCellFormatter().setColSpan(6, 0, 6);

		formulario.setWidget(7, 0, pie);
		formulario.getFlexCellFormatter().setColSpan(7, 0, 6);

		formulario.setWidget(8, 1, generar);
		formulario.setWidget(8, 4, cancelar);
		
		
		initWidget(formulario);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	protected void cargarListaEmpleadosACargo(EmpleadoDTO result) {
		
		for (EmpleadoDTO empleado : result.getListaEmpACargo()) {
			String emp = empleado.getApellido()+", "+empleado.getNombre();
			this.empPideLb.addItem(emp);
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
			this.insumoLb.addItem(insumo);
		}
	}


	private void cancelar(){
		padre.remove(numeroElemento(constante.ordenDeProvisionDeInsumos()));
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
	
	protected void cargarListaMarcas(List<String> result) {
		
		this.marcaLb.clear();
		
		this.listaMarcas = result;
		this.listaMarcas.add(0,"ninguno");
		
		for (String marca : listaMarcas) {
			this.marcaLb.addItem(marca);
		}
		
	}
	

}
