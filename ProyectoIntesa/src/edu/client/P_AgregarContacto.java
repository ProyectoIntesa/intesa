package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class P_AgregarContacto extends PopupPanel {

	P_FormularioCliente padre;
	private FlexTable contenedor;

	private Constantes constante = GWT.create(Constantes.class);
	
	private Label encabezado;
	private Label nombre;
	private Label cargo;
	private Label telEmpresa;
	private Label interno;
	private Label telParticular;
	private Label celular;
	private Label correo;
	private Label pie;

	private TextBox nombreTb;
	private TextBox cargoTb;
	private TextBox telEmpresaTb;
	private TextBox internoTb;
	private TextBox telParticularTb;
	private TextBox celularTb;
	private TextBox correoTb;

	private Button agregar;
	private Button cancelar;

	public P_AgregarContacto(P_FormularioCliente pantalla) {

		super(false);
		padre = pantalla;
		setStyleName("fondoPopup");
		contenedor = new FlexTable();

		
		
		encabezado = new Label(constante.nuevoContacto());
		encabezado.setStyleName("labelTitulo");
		nombre = new Label(constante.nombre());
		nombre.setStyleName("gwt-LabelFormularioDerecho");
		cargo = new Label(constante.cargo());
		cargo.setStyleName("gwt-LabelFormularioDerecho");
		telEmpresa = new Label(constante.telefono());
		telEmpresa.setStyleName("gwt-LabelFormularioDerecho");
		interno = new Label(constante.interno());
		interno.setStyleName("gwt-LabelFormularioDerecho");
		telParticular = new Label(constante.telefonoParticular());
		telParticular.setStyleName("gwt-LabelFormularioDerecho");
		celular = new Label(constante.celular());
		celular.setStyleName("gwt-LabelFormularioDerecho");
		correo = new Label(constante.eMail());
		correo.setStyleName("gwt-LabelFormularioDerecho");

		nombreTb = new TextBox();
		nombreTb.setStyleName("gwt-TextBox");
		cargoTb = new TextBox();
		cargoTb.setStyleName("gwt-TextBox");
		telEmpresaTb = new TextBox();
		telEmpresaTb.setStyleName("gwt-TextBox");
		internoTb = new TextBox();
		internoTb.setStyleName("gwt-TextBox");
		telParticularTb = new TextBox();
		telParticularTb.setStyleName("gwt-TextBox");
		celularTb = new TextBox();
		celularTb.setStyleName("gwt-TextBox");
		correoTb = new TextBox();
		correoTb.setStyleName("gwt-TextBox");

		agregar = new Button(constante.agregar());
		agregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregar();
			}
		});

		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cerrar();
			}
		});

		pie = new Label("");
		pie.setStyleName("labelTitulo");
		
		contenedor.setWidget(0, 0, encabezado);
		contenedor.getFlexCellFormatter().setColSpan(0, 0, 2);
		contenedor.setWidget(1, 0, nombre);
		contenedor.setWidget(2, 0, cargo);
		contenedor.setWidget(3, 0, telEmpresa);
		contenedor.setWidget(4, 0, interno);
		contenedor.setWidget(5, 0, telParticular);
		contenedor.setWidget(6, 0, celular);
		contenedor.setWidget(7, 0, correo);

		contenedor.setWidget(1, 1, nombreTb);
		contenedor.setWidget(2, 1, cargoTb);
		contenedor.setWidget(3, 1, telEmpresaTb);
		contenedor.setWidget(4, 1, internoTb);
		contenedor.setWidget(5, 1, telParticularTb);
		contenedor.setWidget(6, 1, celularTb);
		contenedor.setWidget(7, 1, correoTb);
		contenedor.setWidget(8, 0, pie);
		contenedor.getFlexCellFormatter().setColSpan(8, 0, 2);
		
		contenedor.setWidget(9, 0, agregar);
		contenedor.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_CENTER);
		contenedor.setWidget(9, 1, cancelar);
		contenedor.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_CENTER);
		setWidget(contenedor);

		contenedor.setSize("300px", "300px");

	}

	protected void agregar() {

		this.padre.capturarDatos(nombreTb.getText(), cargoTb.getText(),
				telEmpresaTb.getText(), internoTb.getText(),
				telParticularTb.getText(), celularTb.getText(),
				correoTb.getText());

		this.hide();

	}

	protected void cerrar() {
		this.hide();

	}

}
