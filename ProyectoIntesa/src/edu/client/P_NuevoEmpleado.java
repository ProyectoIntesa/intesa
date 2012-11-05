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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import edu.client.AdministradorService.AdministradorService;
import edu.client.AdministradorService.AdministradorServiceAsync;
import edu.shared.DTO.EmpleadoDTO;

public class P_NuevoEmpleado extends Composite {
	
	
	private static final int COL_NROLEGAJO = 1;
	private static final int COL_APELLIDO = 2;
	private static final int COL_NOMBRE = 3;
	private static final int COL_PUESTO = 4;
	private static final int COL_BORRAR = 5;
	private static final int COL_SELECCIONADO = 5;
	
	private Constantes constante = GWT.create(Constantes.class);
	private String tituloTab;
	
	TabPanel padre;
	EmpleadoDTO empleado;
	private Label datosEmpleado;
	private Label datosEmpleadoACargo;
	private Label datosEmpleadoYaCargado;
	private Label inferior;
	
	private FlexTable formularioEmpleado;
	private ScrollPanel contenedorTabla;
	private ScrollPanel contenedorTablaAgregar;
	private FlexTable tablaElemento;
	private FlexTable tablaElementoAgregar;
	
	private Label nroLegajo;
	private Label apellido;
	private Label nombre;
	private Label puesto;

	
	private TextBox nroLegajoTb;
	private TextBox apellidoTb;
	private TextBox nombreTb;
	private TextBox puestoTb;

	
	private Button asignar;
	private Button guardar;
	private Button salir;

	public List<EmpleadoDTO> listaEmpleados;
	public List<String> listaEmpleadosACargo;
	
	
	public P_NuevoEmpleado(TabPanel padre, String titulo){
		
				
		this.tituloTab = titulo;
		this.padre = padre;
		
		
		
		listaEmpleados = new LinkedList<EmpleadoDTO>();
		listaEmpleadosACargo = new LinkedList<String>();
		
		AdministradorServiceAsync adminServie = GWT.create(AdministradorService.class);
		
		adminServie.getEmpleados(listaEmpleados,new AsyncCallback<List<EmpleadoDTO>>() {
			@Override
			public void onSuccess(List<EmpleadoDTO> result) {
				cargarListaEmpleados(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de empleados");
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		datosEmpleado = new Label(constante.datosDeEmpleado());
		datosEmpleado.setStyleName("labelTitulo");
		datosEmpleadoYaCargado = new Label(constante.empleadosPosiblesASerAsignados());
		datosEmpleadoYaCargado.setStyleName("labelTitulo");
		datosEmpleadoACargo = new Label(constante.empleadosACargo());
		datosEmpleadoACargo.setStyleName("labelTitulo");
		inferior = new Label("");
		inferior.setStyleName("labelTitulo");
		
		nroLegajo = new Label(constante.nroLegajo());
		nroLegajo.setStyleName("gwt-LabelFormulario");
		apellido = new Label(constante.apellido());
		apellido.setStyleName("gwt-LabelFormulario");
		nombre = new Label(constante.nombre());
		nombre.setStyleName("gwt-LabelFormulario");
		puesto = new Label(constante.puesto());
		puesto.setStyleName("gwt-LabelFormulario");

		
		nroLegajoTb = new TextBox();
		apellidoTb = new TextBox();
		nombreTb = new TextBox();
		puestoTb = new TextBox();

		
		
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir(event);
			}
		});
		
		
		guardar = new Button(constante.guardar());
		
		
		asignar = new Button(constante.asignar());
		
		asignar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				capturarDatos();
			}
		});
		
		
		contenedorTablaAgregar = new ScrollPanel();
		contenedorTablaAgregar.setHeight("200px");
		tablaElementoAgregar = new FlexTable();
		contenedorTablaAgregar.setWidget(tablaElementoAgregar);
		tablaElementoAgregar.setSize("100%", "100%");
		tablaElementoAgregar.setText(0, COL_NROLEGAJO, constante.nroLegajo());
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_NROLEGAJO, "24%");
		tablaElementoAgregar.setText(0, COL_APELLIDO, constante.apellido()); 
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_APELLIDO, "24%");
		tablaElementoAgregar.setText(0, COL_NOMBRE, constante.nombre());
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_NOMBRE, "24%");
		tablaElementoAgregar.setText(0, COL_PUESTO, constante.puesto());
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_PUESTO, "24%");
		tablaElementoAgregar.setText(0, COL_SELECCIONADO, "");
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_SELECCIONADO, "4%");
		tablaElementoAgregar.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setHeight("200px");
		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");
		tablaElemento.setText(0, COL_NROLEGAJO, constante.nroLegajo());
		tablaElemento.getCellFormatter().setWidth(0, COL_NROLEGAJO, "24%");
		tablaElemento.setText(0, COL_APELLIDO, constante.apellido()); 
		tablaElemento.getCellFormatter().setWidth(0, COL_APELLIDO, "24%");
		tablaElemento.setText(0, COL_NOMBRE, constante.nombre());
		tablaElemento.getCellFormatter().setWidth(0, COL_NOMBRE, "24%");
		tablaElemento.setText(0, COL_PUESTO, constante.puesto());
		tablaElemento.getCellFormatter().setWidth(0, COL_PUESTO, "24%");
		tablaElemento.setText(0, COL_BORRAR, "");
		tablaElemento.getCellFormatter().setWidth(0, COL_BORRAR, "4%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");
		

		formularioEmpleado = new FlexTable();
		formularioEmpleado.setStyleName("formatoFormulario");
		formularioEmpleado.setHeight("637px");
				
		formularioEmpleado.setWidget(0, 0, datosEmpleado);
		formularioEmpleado.getFlexCellFormatter().setColSpan(0, 0, 8);
		
		formularioEmpleado.setWidget(1, 0, nroLegajo);
		formularioEmpleado.setWidget(1, 1, nroLegajoTb);
		formularioEmpleado.setWidget(1, 2, apellido);
		formularioEmpleado.setWidget(1, 3, apellidoTb);
		formularioEmpleado.setWidget(1, 4, nombre);
		formularioEmpleado.setWidget(1, 5, nombreTb);
		formularioEmpleado.setWidget(1, 6, puesto);
		formularioEmpleado.setWidget(1, 7, puestoTb);
		
		formularioEmpleado.setWidget(2, 0, datosEmpleadoYaCargado);
		formularioEmpleado.getFlexCellFormatter().setColSpan(2, 0, 8);
		
		formularioEmpleado.setWidget(3, 0, contenedorTablaAgregar);
		formularioEmpleado.getFlexCellFormatter().setColSpan(3, 0, 8);
		
		formularioEmpleado.setWidget(4, 4, asignar);
		
		formularioEmpleado.setWidget(5, 0, datosEmpleadoACargo);
		formularioEmpleado.getFlexCellFormatter().setColSpan(5, 0, 8);
		
		formularioEmpleado.setWidget(6, 0, contenedorTabla);
		formularioEmpleado.getFlexCellFormatter().setColSpan(6, 0, 8);
		
		formularioEmpleado.setWidget(7, 0, inferior);
		formularioEmpleado.getFlexCellFormatter().setColSpan(7, 0, 8);
		
		formularioEmpleado.setWidget(8, 3, guardar);
		formularioEmpleado.setWidget(8, 4, salir);
		
		
		initWidget(formularioEmpleado);	

	}
	
	      /**
	       * constructor para la pantalla modificar empleado!
	       * 
	       * @param padre
	       * @param empleado
	       * @param titulo
	       */
	      
	public P_NuevoEmpleado(TabPanel padre, EmpleadoDTO empleado, String titulo){
		
		this.tituloTab= titulo;
		this.empleado = empleado;
		this.padre = padre;
		
		
		
		
		listaEmpleados = new LinkedList<EmpleadoDTO>();
		listaEmpleadosACargo = new LinkedList<String>();
		
		AdministradorServiceAsync adminServie = GWT.create(AdministradorService.class);
		
		adminServie.getEmpleados(listaEmpleados,new AsyncCallback<List<EmpleadoDTO>>() {
			@Override
			public void onSuccess(List<EmpleadoDTO> result) {
				cargarListaEmpleados(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de empleados");
			}
		});
		
		
		
		datosEmpleado = new Label(constante.datosDeEmpleado());
		datosEmpleado.setStyleName("labelTitulo");
		datosEmpleadoACargo = new Label(constante.empleadosACargo());
		datosEmpleadoACargo.setStyleName("labelTitulo");
		inferior = new Label("");
		inferior.setStyleName("labelTitulo");
		
		nroLegajo = new Label(constante.nroLegajo());
		nroLegajo.setStyleName("gwt-LabelFormulario");
		apellido = new Label(constante.apellido());
		apellido.setStyleName("gwt-LabelFormulario");
		nombre = new Label(constante.nombre());
		nombre.setStyleName("gwt-LabelFormulario");
		puesto = new Label(constante.puesto());
		puesto.setStyleName("gwt-LabelFormulario");

		
		nroLegajoTb = new TextBox();
		apellidoTb = new TextBox();
		nombreTb = new TextBox();
		puestoTb = new TextBox();

		
		
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir(event);
			}
		});
		
		
		guardar = new Button(constante.guardar());
		
		
		asignar = new Button(constante.asignar());
		
		asignar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				capturarDatos();
			}
		});
		
		
		contenedorTablaAgregar = new ScrollPanel();
		contenedorTablaAgregar.setHeight("200px");
		tablaElementoAgregar = new FlexTable();
		contenedorTablaAgregar.setWidget(tablaElementoAgregar);
		tablaElementoAgregar.setSize("100%", "100%");
		tablaElementoAgregar.setText(0, COL_NROLEGAJO, constante.nroLegajo());
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_NROLEGAJO, "24%");
		tablaElementoAgregar.setText(0, COL_APELLIDO, constante.apellido()); 
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_APELLIDO, "24%");
		tablaElementoAgregar.setText(0, COL_NOMBRE, constante.nombre());
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_NOMBRE, "24%");
		tablaElementoAgregar.setText(0, COL_PUESTO, constante.puesto());
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_PUESTO, "24%");
		tablaElementoAgregar.setText(0, COL_SELECCIONADO, "");
		tablaElementoAgregar.getCellFormatter().setWidth(0, COL_SELECCIONADO, "4%");
		tablaElementoAgregar.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setHeight("200px");
		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");
		tablaElemento.setText(0, COL_NROLEGAJO, constante.nroLegajo());
		tablaElemento.getCellFormatter().setWidth(0, COL_NROLEGAJO, "24%");
		tablaElemento.setText(0, COL_APELLIDO, constante.apellido()); 
		tablaElemento.getCellFormatter().setWidth(0, COL_APELLIDO, "24%");
		tablaElemento.setText(0, COL_NOMBRE, constante.nombre());
		tablaElemento.getCellFormatter().setWidth(0, COL_NOMBRE, "24%");
		tablaElemento.setText(0, COL_PUESTO, constante.puesto());
		tablaElemento.getCellFormatter().setWidth(0, COL_PUESTO, "24%");
		tablaElemento.setText(0, COL_BORRAR, "");
		tablaElemento.getCellFormatter().setWidth(0, COL_BORRAR, "4%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");
		

		formularioEmpleado = new FlexTable();
		formularioEmpleado.setStyleName("formatoFormulario");
		formularioEmpleado.setHeight("637px");
				
		formularioEmpleado.setWidget(0, 0, datosEmpleado);
		formularioEmpleado.getFlexCellFormatter().setColSpan(0, 0, 8);
		
		formularioEmpleado.setWidget(1, 0, nroLegajo);
		formularioEmpleado.setWidget(1, 1, nroLegajoTb);
		formularioEmpleado.setWidget(1, 2, apellido);
		formularioEmpleado.setWidget(1, 3, apellidoTb);
		formularioEmpleado.setWidget(1, 4, nombre);
		formularioEmpleado.setWidget(1, 5, nombreTb);
		formularioEmpleado.setWidget(1, 6, puesto);
		formularioEmpleado.setWidget(1, 7, puestoTb);
		
		formularioEmpleado.setWidget(2, 0, datosEmpleadoACargo);
		formularioEmpleado.getFlexCellFormatter().setColSpan(2, 0, 8);
		
		formularioEmpleado.setWidget(3, 0, contenedorTablaAgregar);
		formularioEmpleado.getFlexCellFormatter().setColSpan(3, 0, 8);
		
		formularioEmpleado.setWidget(4, 4, asignar);
		
		formularioEmpleado.setWidget(5, 0, contenedorTabla);
		formularioEmpleado.getFlexCellFormatter().setColSpan(5, 0, 8);
		
		formularioEmpleado.setWidget(6, 0, inferior);
		formularioEmpleado.getFlexCellFormatter().setColSpan(6, 0, 8);
		
		formularioEmpleado.setWidget(7, 3, guardar);
		formularioEmpleado.setWidget(7, 4, salir);
		
		
		initWidget(formularioEmpleado);	
		
		refrescarDatos();
		
	}
	
	
	private void refrescarDatos() {
	nroLegajoTb.setText(""+this.empleado.getNroLegajo());
	apellidoTb.setText(this.empleado.getApellido());
	nombreTb.setText(this.empleado.getNombre());
	puestoTb.setText(this.empleado.getPuesto());
	
}


	public void refrescarDatos(EmpleadoDTO empleado) {
		this.empleado = empleado;
		nroLegajoTb.setText(""+this.empleado.getNroLegajo());
		apellidoTb.setText(this.empleado.getApellido());
		nombreTb.setText(this.empleado.getNombre());
		puestoTb.setText(this.empleado.getPuesto());
		
	}

	
	public void salir(ClickEvent event) {
		if (constante.empleado().compareTo(tituloTab )!= 0){
			padre.remove(numeroElemento(tituloTab));	
		}	
		else{
			padre.remove(numeroElemento(tituloTab));			
		}

		
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
	
	
	public void cargarListaEmpleados(List<EmpleadoDTO> lista)
	{
		listaEmpleados= lista;
		for (int i = 0; i < listaEmpleados.size(); i++) {
			CheckBox select = new CheckBox();
			
			tablaElementoAgregar.setWidget(i + 1, COL_NROLEGAJO, new Label("" + listaEmpleados.get(i).getNroLegajo()));
			tablaElementoAgregar.setWidget(i + 1, COL_NOMBRE, new Label(listaEmpleados.get(i).getNombre()));
			tablaElementoAgregar.setWidget(i + 1, COL_APELLIDO, new Label("" + listaEmpleados.get(i).getApellido()));
			tablaElementoAgregar.setWidget(i + 1, COL_PUESTO, new Label("" + listaEmpleados.get(i).getPuesto()));			
			tablaElementoAgregar.setWidget(i + 1, COL_SELECCIONADO, select);
			tablaElementoAgregar.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_SELECCIONADO, HasHorizontalAlignment.ALIGN_CENTER );			
			tablaElementoAgregar.getRowFormatter().addStyleName(i+1, "renglon");
		}
		
	}
	
	protected void capturarDatos() {
		
		boolean bandera = false;
		String codEliminar = "";
		String codEmpleadoCargado = "";
		int insertar = tablaElemento.getRowCount();
		
	
		
		for(int i = 1; i < this.tablaElementoAgregar.getRowCount(); i++){
		
			if(((CheckBox)this.tablaElementoAgregar.getWidget(i, 5)).getValue() == true){
				
				
				codEliminar= ((Label)this.tablaElementoAgregar.getWidget(i, 1)).getText();
				
				//sirve para saber si el empleado que se eligio ya esta a cargo del empleado en cuestion	
				for(int k = 0; k < listaEmpleadosACargo.size(); k++){
					
					codEmpleadoCargado = listaEmpleadosACargo.get(k);
					
					if(codEliminar.compareTo(codEmpleadoCargado) == 0){
					
						Window.alert("Uno de los empleados seleccionados ya se encuentra a cargo del empleado en cuestion");
						bandera = true;
						codEmpleadoCargado = "";
						break;
					}						
				}				
				
				//si el empleado seleccionado no se encontraba ya cargo, hay que agregarlo			
				if(bandera == false){
					
					listaEmpleadosACargo.add(codEliminar);
					
					for(int j = 0; j < listaEmpleados.size(); j++){
						
						String codBusqueda = "";
						codBusqueda = ""+listaEmpleados.get(j).getNroLegajo();
						
						if(codEliminar.compareTo(codBusqueda) == 0){							
							
							Label borrar = new Label("");
							borrar.setSize("16px", "16px");
							borrar.addStyleName("labelBorrar");
							tablaElemento.setWidget(insertar, COL_NROLEGAJO, new Label("" + listaEmpleados.get(j).getNroLegajo()));
							tablaElemento.setWidget(insertar, COL_NOMBRE, new Label(listaEmpleados.get(j).getNombre()));
							tablaElemento.setWidget(insertar, COL_APELLIDO, new Label(listaEmpleados.get(j).getApellido()));
							tablaElemento.setWidget(insertar, COL_PUESTO, new Label(listaEmpleados.get(j).getPuesto()));			
							tablaElemento.setWidget(insertar, COL_SELECCIONADO, borrar);
							tablaElemento.getFlexCellFormatter().setHorizontalAlignment(insertar, COL_SELECCIONADO, HasHorizontalAlignment.ALIGN_CENTER );			
							tablaElemento.getRowFormatter().addStyleName(insertar, "renglon");
							
							borrar.addClickHandler(new ClickHandler(){
								public void onClick(ClickEvent event){		
									Cell celda= tablaElemento.getCellForEvent(event);
									borrarEmpleadoACargo(celda);
								}
							});					
						}					
					}
					insertar++;					
				}					
				bandera = false;
				((CheckBox)this.tablaElementoAgregar.getWidget(i,5)).setValue(false);
			}							
		}					
	}
		



	protected void borrarEmpleadoACargo(Cell celda) {

		
		String codEliminar = listaEmpleadosACargo.get(celda.getRowIndex()-1);
		
		this.tablaElemento.removeRow(celda.getRowIndex());
		
		this.listaEmpleadosACargo.remove(codEliminar);
	}
	
	
}
