package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;

import edu.client.Constantes;

public class P_NuevoUsuario extends Composite {
	
	private Constantes constante = GWT.create(Constantes.class);

	TabPanel padre;
	
	private Label datosUsuario;
	private Label datosEmpleado;
	private Label inferior;
	
	private FlexTable formularioUsuario;
	
	private Label usuario;
	private Label contrasenia;
	private Label rol;
	private Label nroLegajo;
	private Label nombreEmpleado;
	private Label apellidoEmpleado;
	
	private TextBox usuarioTb;
	private PasswordTextBox contraseniaPtb;
	private ListBox rolTb;
	private ListBox nroLegajoTb;
	private TextBox nombreEmpleadoTb;
	private TextBox apellidoEmpleadoTb;
	
	private Button guardar;
	private Button salir;
	
	public P_NuevoUsuario(TabPanel padre){
		
		this.padre = padre;
		
		datosUsuario = new Label(constante.datosDeUsuario());
		datosUsuario.setStyleName("labelTitulo");
		datosEmpleado = new Label(constante.datosDeEmpleado());
		datosEmpleado.setStyleName("labelTitulo");
		inferior = new Label("");
		inferior.setStyleName("labelTitulo");
		
		usuario = new Label(constante.usuario());
		usuario.setStyleName("gwt-LabelFormulario");
		contrasenia = new Label(constante.contrasenia());
		contrasenia.setStyleName("gwt-LabelFormulario");
		rol = new Label(constante.rol());
		rol.setStyleName("gwt-LabelFormulario");
		nroLegajo = new Label(constante.nroLegajo());
		nroLegajo.setStyleName("gwt-LabelFormulario");
		nombreEmpleado = new Label(constante.nombre());
		nombreEmpleado.setStyleName("gwt-LabelFormulario");
		apellidoEmpleado = new Label(constante.apellido());
		apellidoEmpleado.setStyleName("gwt-LabelFormulario");
		
		usuarioTb = new TextBox();
		usuarioTb.setStyleName("gwt-TextArea");
		contraseniaPtb = new PasswordTextBox();
		contraseniaPtb.setStyleName("gwt-TextArea");
		rolTb = new ListBox();
		rolTb.addItem("VENTAS");
		rolTb.addItem("INGENIERIA");
		rolTb.addItem("PRODUCCION");
		rolTb.addItem("COMPRAS");
		rolTb.addItem("ALMACEN");
		rolTb.setStyleName("gwt-TextArea");
		nroLegajoTb = new ListBox();
		nroLegajoTb.setStyleName("gwt-TextArea");
		nombreEmpleadoTb = new TextBox();
		nombreEmpleadoTb.setStyleName("gwt-TextArea");
		nombreEmpleadoTb.setEnabled(false);
		apellidoEmpleadoTb = new TextBox();
		apellidoEmpleadoTb.setStyleName("gwt-TextArea");
		apellidoEmpleadoTb.setEnabled(false);
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir(event);
			}
		});
		
		guardar = new Button(constante.guardar());
		
		
		formularioUsuario = new FlexTable();
		formularioUsuario.setStyleName("formatoFormulario");
		formularioUsuario.setHeight("637px");
		formularioUsuario.setWidth("350px");
		
		formularioUsuario.setWidget(0, 0, datosUsuario);
		formularioUsuario.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		formularioUsuario.setWidget(1, 0, usuario);
		formularioUsuario.setWidget(2, 0, usuarioTb);
		formularioUsuario.getFlexCellFormatter().setColSpan(2, 0, 2);
		formularioUsuario.setWidget(3, 0, contrasenia);
		formularioUsuario.setWidget(4, 0, contraseniaPtb);
		formularioUsuario.getFlexCellFormatter().setColSpan(4, 0, 2);
		formularioUsuario.setWidget(5, 0, rol);
		formularioUsuario.setWidget(6, 0, rolTb);
		formularioUsuario.getFlexCellFormatter().setColSpan(6, 0, 2);

		formularioUsuario.setWidget(7, 0, datosEmpleado);
		formularioUsuario.getFlexCellFormatter().setColSpan(7, 0, 2);
		
		formularioUsuario.setWidget(8, 0, nroLegajo);
		formularioUsuario.setWidget(9, 0, nroLegajoTb);
		formularioUsuario.getFlexCellFormatter().setColSpan(9, 0, 2);
		formularioUsuario.setWidget(10, 0, nombreEmpleado);
		formularioUsuario.setWidget(11, 0, nombreEmpleadoTb);
		formularioUsuario.getFlexCellFormatter().setColSpan(11, 0, 2);
		formularioUsuario.setWidget(12, 0, apellidoEmpleado);
		formularioUsuario.setWidget(13, 0, apellidoEmpleadoTb);
		formularioUsuario.getFlexCellFormatter().setColSpan(13, 0, 2);
		
		formularioUsuario.setWidget(14, 0, inferior);
		formularioUsuario.getFlexCellFormatter().setColSpan(14, 0, 2);
		
		formularioUsuario.setWidget(15, 0, guardar);
		formularioUsuario.setWidget(15, 1, salir);
		
		
		
		initWidget(formularioUsuario);	
		
		
	}
	
	
	
	
	
	
	
	
	
	
	public void salir(ClickEvent event) {
		padre.remove(numeroElemento(constante.usuario()));
		
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
