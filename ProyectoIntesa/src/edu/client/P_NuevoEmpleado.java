package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;

public class P_NuevoEmpleado extends Composite {
	
	
	private static final int COL_NROLEGAJO = 1;
	private static final int COL_APELLIDO = 2;
	private static final int COL_NOMBRE = 3;
	private static final int COL_PUESTO = 4;
	private static final int COL_BORRAR = 5;
	private static final int COL_SELECCIONADO = 5;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	TabPanel padre;
	
	private Label datosEmpleado;
	private Label datosEmpleadoACargo;
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

	
	public P_NuevoEmpleado(TabPanel padre){
		
				
		
		this.padre = padre;
		
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
		
		
		
		
		
		
		
		
		
	}
	
	public void salir(ClickEvent event) {
		padre.remove(numeroElemento(constante.empleado()));
		
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
	
	protected void capturarDatos() {

		
		/*
		
		if (this.fechaFinalizacionEquipoDb.getValue() != null) {
			final String codigo;
			DateTimeFormat df = DateTimeFormat.getFormat("dd/MM/yyyy");
			int fila = tablaElemento.getRowCount();
			if (componente.getValue()==false){codigo = this.claseEquipoLb
					.getItemText(this.claseEquipoLb.getSelectedIndex());}
			else{
				 codigo = this.componenteEquipoLb.getItemText(this.componenteEquipoLb.getSelectedIndex());
			}
			String nombre = this.equipoLb.getItemText(this.equipoLb
					.getSelectedIndex());
			String cantidad = this.cantidadLb.getItemText((this.cantidadLb
					.getSelectedIndex()));
			String fecha = df.format(this.fechaFinalizacionEquipoDb.getValue());
			String detalle = this.descripcionRenglon.getText();
			Label eliminar = new Label("");
			eliminar.setSize("16px", "16px");
			eliminar.setStyleName("labelBorrar");
			eliminar.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					eliminar(codigo);
				}
			});
			tablaElemento.setWidget(fila, COL_ELIMINAR, eliminar);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(fila, COL_ELIMINAR,HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.setWidget(fila, COL_CODIGO, new Label(codigo));
			tablaElemento.setWidget(fila, COL_NOMBRE, new Label(nombre));
			tablaElemento.setWidget(fila, COL_CANTIDAD, new Label(cantidad));
			tablaElemento.setWidget(fila, COL_FECHA, new Label(fecha));
			tablaElemento.setWidget(fila, COL_DETALLE, new Label(detalle));

			String estilo = (fila % 2 == 0) ? "tablaRenglon" : "tablaRenglon2";

			tablaElemento.getRowFormatter().setStyleName(fila, estilo);
			limpiarCampos();
			elementos.add(codigo);
		} else
			Window.alert("NO HA SELECCIONADO UNA FECHA");
			
			*/
			
	}
	
	
}
