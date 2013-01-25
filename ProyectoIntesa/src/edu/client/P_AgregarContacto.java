
package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.client.VentasService.VentasService;
import edu.client.VentasService.VentasServiceAsync;
import edu.shared.DTO.ContactoDTO;

public class P_AgregarContacto extends PopupPanel {

	P_FormularioCliente padre;
	P_FormularioProveedor padre2;
	private FlexTable contenedor;
	private FlexTable botones;

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
	private ContactoDTO contSelec;

	public P_AgregarContacto(P_FormularioCliente pantalla) {

		super(false);
		padre = pantalla;
		setStyleName("fondoPopup");
		contenedor = new FlexTable();

		
		
		encabezado = new Label(constante.nuevoContacto());
		encabezado.setStyleName("labelTitulo");
		nombre = new Label(constante.nombreAsterisco());
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

		botones = new FlexTable();
		botones.setWidget(0, 0, agregar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, cancelar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
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
		
		contenedor.setWidget(9, 0, botones);
		contenedor.getFlexCellFormatter().setColSpan(9, 0, 3);
		contenedor.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		setWidget(contenedor);

		contenedor.setSize("300px", "300px");

	}
	
	public P_AgregarContacto(P_FormularioProveedor pantalla) {

		super(false);
		padre2 = pantalla;
		setStyleName("fondoPopup");
		contenedor = new FlexTable();

		
		
		encabezado = new Label(constante.nuevoContacto());
		encabezado.setStyleName("labelTitulo");
		nombre = new Label(constante.nombreAsterisco());
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
				agregar2();
			}
		});

		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cerrar();
			}
		});

		botones = new FlexTable();
		botones.setWidget(0, 0, agregar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, cancelar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
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
		
		contenedor.setWidget(9, 0, botones);
		contenedor.getFlexCellFormatter().setColSpan(9, 0, 3);
		contenedor.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		setWidget(contenedor);

		contenedor.setSize("300px", "300px");

	}
	
	public P_AgregarContacto(ContactoDTO contacto) {

		super(false);
		contSelec = contacto;
		setStyleName("fondoPopup");
		contenedor = new FlexTable();

		
		
		encabezado = new Label(constante.modificarContacto());
		encabezado.setStyleName("labelTitulo");
		nombre = new Label(constante.nombreAsterisco());
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
		nombreTb.setText(contacto.getNombre());
		cargoTb = new TextBox();
		cargoTb.setStyleName("gwt-TextBox");
		cargoTb.setText(contacto.getCargo());
		telEmpresaTb = new TextBox();
		telEmpresaTb.setStyleName("gwt-TextBox");
		telEmpresaTb.setText(contacto.getTelefonoEmpresa());
		internoTb = new TextBox();
		internoTb.setStyleName("gwt-TextBox");
		internoTb.setText(contacto.getInternoEmpresa());
		telParticularTb = new TextBox();
		telParticularTb.setStyleName("gwt-TextBox");
		telParticularTb.setText(contacto.getTelefonoParticular());
		celularTb = new TextBox();
		celularTb.setStyleName("gwt-TextBox");
		celularTb.setText(contacto.getCelular());
		correoTb = new TextBox();
		correoTb.setStyleName("gwt-TextBox");
		correoTb.setText(contacto.getMail());

		agregar = new Button(constante.guardar());
		agregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				guardar();
			}
		});

		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cerrar();
			}
		});

		botones = new FlexTable();
		botones.setWidget(0, 0, agregar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, cancelar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
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
		
		contenedor.setWidget(9, 0, botones);
		contenedor.getFlexCellFormatter().setColSpan(9, 0, 3);
		contenedor.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		setWidget(contenedor);

		contenedor.setSize("300px", "300px");

	}
	
	public P_AgregarContacto(ContactoDTO contacto, String bandera) {

		super(false);
		contSelec = contacto;
		setStyleName("fondoPopup");
		contenedor = new FlexTable();

		
		
		encabezado = new Label(constante.modificarContacto());
		encabezado.setStyleName("labelTitulo");
		nombre = new Label(constante.nombreAsterisco());
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
		nombreTb.setText(contacto.getNombre());
		cargoTb = new TextBox();
		cargoTb.setStyleName("gwt-TextBox");
		cargoTb.setText(contacto.getCargo());
		telEmpresaTb = new TextBox();
		telEmpresaTb.setStyleName("gwt-TextBox");
		telEmpresaTb.setText(contacto.getTelefonoEmpresa());
		internoTb = new TextBox();
		internoTb.setStyleName("gwt-TextBox");
		internoTb.setText(contacto.getInternoEmpresa());
		telParticularTb = new TextBox();
		telParticularTb.setStyleName("gwt-TextBox");
		telParticularTb.setText(contacto.getTelefonoParticular());
		celularTb = new TextBox();
		celularTb.setStyleName("gwt-TextBox");
		celularTb.setText(contacto.getCelular());
		correoTb = new TextBox();
		correoTb.setStyleName("gwt-TextBox");
		correoTb.setText(contacto.getMail());

		agregar = new Button(constante.guardar());
		agregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				guardar2();
			}
		});

		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cerrar();
			}
		});

		botones = new FlexTable();
		botones.setWidget(0, 0, agregar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, cancelar);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
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
		
		contenedor.setWidget(9, 0, botones);
		contenedor.getFlexCellFormatter().setColSpan(9, 0, 3);
		contenedor.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		setWidget(contenedor);

		contenedor.setSize("300px", "300px");

	}	
	
	protected void guardar() {
		
		Validaciones validar = new Validaciones(); 
		
		boolean vNombreContacto = validar.textBoxVacio(this.nombreTb.getText());
		
		if(!vNombreContacto){
			VentasServiceAsync ventasService = GWT.create(VentasService.class);
			ventasService.retornaIdContacto(contSelec.getCliente().getNombre(),contSelec.getNombre() , new AsyncCallback<Integer>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR el modificar contacto");
				}

				@Override
				public void onSuccess(Integer result) {

					int idContacto = result;
					modificarContacto(idContacto);
				}

			});
		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}
			
		
		


		
	}
	
	protected void guardar2() {
		
		Validaciones validar = new Validaciones(); 
		
		boolean vNombreContacto = validar.textBoxVacio(this.nombreTb.getText());
		
		if(!vNombreContacto){
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			comprasService.retornaIdContacto(contSelec.getProveedor().getNombre(),contSelec.getNombre() , new AsyncCallback<Integer>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR el modificar contacto");
				}

				@Override
				public void onSuccess(Integer result) {

					int idContacto = result;
					modificarContacto2(idContacto);
				}

			});
		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}

		
	}

	protected void modificarContacto(int idContacto) {
		
		Validaciones validar = new Validaciones(); 
		
		boolean vNombreContacto = validar.textBoxVacio(this.nombreTb.getText());
		
		if(!vNombreContacto){
			VentasServiceAsync ventasService = GWT.create(VentasService.class);
			contSelec.setNombre(this.nombreTb.getText());
			contSelec.setCargo(this.cargoTb.getText());
			contSelec.setTelefonoEmpresa(this.telEmpresaTb.getText());
			contSelec.setInternoEmpresa(this.internoTb.getText());
			contSelec.setTelefonoParticular(this.telParticularTb.getText());
			contSelec.setCelular(this.celularTb.getText());
			contSelec.setMail(this.correoTb.getText());
			
			ventasService.modificarContacto(contSelec, idContacto, new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR al modificar contacto");
				}

				@Override
				public void onSuccess(Boolean result) {
					
					if (result){
						Window.alert("El contacto ha sido modificado de manera exitosa");
						cerrar();
					}
					else{
						Window.alert("El contacto no se ha podido modificar");
						cerrar();
					}
					
				}

			});
		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}

		
	}
	
	protected void modificarContacto2(int idContacto) {
		
		Validaciones validar = new Validaciones(); 
		
		boolean vNombreContacto = validar.textBoxVacio(this.nombreTb.getText());
		
		if(!vNombreContacto){
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
			contSelec.setNombre(this.nombreTb.getText());
			contSelec.setCargo(this.cargoTb.getText());
			contSelec.setTelefonoEmpresa(this.telEmpresaTb.getText());
			contSelec.setInternoEmpresa(this.internoTb.getText());
			contSelec.setTelefonoParticular(this.telParticularTb.getText());
			contSelec.setCelular(this.celularTb.getText());
			contSelec.setMail(this.correoTb.getText());
			
			comprasService.modificarContacto(contSelec, idContacto, new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR al modificar contacto");
				}

				@Override
				public void onSuccess(Boolean result) {
					
					if (result){
						Window.alert("El contacto ha sido modificado de manera exitosa");
						cerrar();
					}
					else{
						Window.alert("El contacto no se ha podido modificar");
						cerrar();
					}
					
				}

			});
		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}

		
	}

	protected void agregar() {

		Validaciones validar = new Validaciones(); 
		
		boolean vNombreContacto = validar.textBoxVacio(this.nombreTb.getText());
		
		if(!vNombreContacto){
			this.padre.capturarDatos(nombreTb.getText(), cargoTb.getText(),
					telEmpresaTb.getText(), internoTb.getText(),
					telParticularTb.getText(), celularTb.getText(),
					correoTb.getText());

			this.hide();
		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}
		
		


	}
	
	protected void agregar2() {


		
		Validaciones validar = new Validaciones(); 
		
		boolean vNombreContacto = validar.textBoxVacio(this.nombreTb.getText());
		
		if(!vNombreContacto){
			this.padre2.capturarDatos(nombreTb.getText(), cargoTb.getText(),
					telEmpresaTb.getText(), internoTb.getText(),
					telParticularTb.getText(), celularTb.getText(),
					correoTb.getText());

			this.hide();
		}
		else{
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}

	}

	protected void cerrar() {
		this.hide();

	}

}
