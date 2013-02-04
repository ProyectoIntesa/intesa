package edu.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;

import edu.client.AdministradorService.AdministradorService;
import edu.client.AdministradorService.AdministradorServiceAsync;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.UsuarioCompDTO;

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
	private TextBox contraseniaPtb;
	private ListBox rolTb;
	private ListBox nroLegajoTb;
	private TextBox nombreEmpleadoTb;
	private TextBox apellidoEmpleadoTb;

	private Button guardar;
	private Button salir;
	private Button modificar;
	private FlexTable botones;

	public List<EmpleadoDTO> listaEmpleados;

	public UsuarioCompDTO usuarioDTO;

	private String tituloTab;

	public P_NuevoUsuario(TabPanel padre, String titulo) {

		this.padre = padre;
		this.tituloTab = titulo;

		listaEmpleados = new LinkedList<EmpleadoDTO>();

		AdministradorServiceAsync adminServie = GWT.create(AdministradorService.class);

		adminServie.getEmpleadosSinUsuario(listaEmpleados, new AsyncCallback<List<EmpleadoDTO>>() {
			@Override
			public void onSuccess(List<EmpleadoDTO> result) {
				cargarListaEmpleados(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});

		datosUsuario = new Label(constante.datosDeUsuario());
		datosUsuario.setStyleName("labelTitulo");
		datosEmpleado = new Label(constante.datosDeEmpleado());
		datosEmpleado.setStyleName("labelTitulo");
		inferior = new Label("");
		inferior.setStyleName("labelTitulo");

		usuario = new Label(constante.usuarioAsterisco());
		usuario.setStyleName("gwt-LabelFormulario");
		contrasenia = new Label(constante.contraseniaAsterisco());
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
		contraseniaPtb = new TextBox();
		contraseniaPtb.setStyleName("gwt-TextArea");
		rolTb = new ListBox();
		rolTb.addItem("VENTAS");
		rolTb.addItem("INGENIERIA");
		rolTb.addItem("GERENTE PRODUCCION");
		rolTb.addItem("SUPERVISOR PRODUCCION");
		rolTb.addItem("COMPRAS");
		rolTb.addItem("ALMACEN");
		rolTb.addItem("SUPER USUARIO");
		rolTb.setStyleName("gwt-TextArea");
		nroLegajoTb = new ListBox();
		nroLegajoTb.setStyleName("gwt-TextArea");
		nroLegajoTb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(listaEmpleados.size() < 1){
					Window.alert("No existen empleados disponibles");
				}
				else{
					nombreEmpleadoTb.setText(listaEmpleados.get(nroLegajoTb.getSelectedIndex()).getNombre());
					apellidoEmpleadoTb.setText(listaEmpleados.get(nroLegajoTb.getSelectedIndex()).getApellido());
				}
			}
		});

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
		guardar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				guardar();
			}
		});

		botones = new FlexTable();
		botones.setWidget(0, 0, guardar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
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

		formularioUsuario.setWidget(15, 0, botones);
		formularioUsuario.getFlexCellFormatter().setColSpan(15, 0, 2);
		formularioUsuario.getCellFormatter().setHorizontalAlignment(15, 0, HasHorizontalAlignment.ALIGN_RIGHT);


		initWidget(formularioUsuario);

	}

	public P_NuevoUsuario(TabPanel padre, UsuarioCompDTO usuarioDTO, String titulo) {

		this.padre = padre;
		this.tituloTab = titulo;
		this.usuarioDTO = usuarioDTO;

		datosUsuario = new Label(constante.datosDeUsuario());
		datosUsuario.setStyleName("labelTitulo");
		datosEmpleado = new Label(constante.datosDeEmpleado());
		datosEmpleado.setStyleName("labelTitulo");
		inferior = new Label("");
		inferior.setStyleName("labelTitulo");

		usuario = new Label(constante.usuario());
		usuario.setStyleName("gwt-LabelFormulario");
		contrasenia = new Label(constante.contraseniaAsterisco());
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
		usuarioTb.setText(usuarioDTO.getNombreUsu());
		usuarioTb.setEnabled(false);
		usuarioTb.setStyleName("gwt-TextArea");
		contraseniaPtb = new TextBox();
		contraseniaPtb.setText(usuarioDTO.getPassUsu());
		contraseniaPtb.setStyleName("gwt-TextArea");
		rolTb = new ListBox();
		rolTb.addItem(usuarioDTO.getRolUsu());
		rolTb.setEnabled(false);
		rolTb.setStyleName("gwt-TextArea");
		nroLegajoTb = new ListBox();
		nroLegajoTb.addItem("" + usuarioDTO.getNroLegajoEmp());
		nroLegajoTb.setEnabled(false);
		nroLegajoTb.setStyleName("gwt-TextArea");
		nombreEmpleadoTb = new TextBox();
		nombreEmpleadoTb.setText(usuarioDTO.getNombreEmp());
		nombreEmpleadoTb.setStyleName("gwt-TextArea");
		nombreEmpleadoTb.setEnabled(false);
		apellidoEmpleadoTb = new TextBox();
		apellidoEmpleadoTb.setText(usuarioDTO.getApellidoEmp());
		apellidoEmpleadoTb.setStyleName("gwt-TextArea");
		apellidoEmpleadoTb.setEnabled(false);

		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir(event);
			}
		});

		modificar = new Button(constante.modificar());
		modificar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				modificar();
			}
		});

		botones = new FlexTable();
		botones.setWidget(0, 0, modificar);
		botones.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones.setWidget(0, 1, salir);
		botones.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
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

		formularioUsuario.setWidget(15, 0, botones);
		formularioUsuario.getFlexCellFormatter().setColSpan(15, 0, 2);
		formularioUsuario.getCellFormatter().setHorizontalAlignment(15, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		initWidget(formularioUsuario);

	}

	protected void modificar() {

		if(this.contraseniaPtb.getText().compareTo("")==0){
			Window.alert("Los campos que poseen (*) son oblicatorios");
		}
		else{
			if (usuarioDTO.getPassUsu().compareTo(this.contraseniaPtb.getText()) == 0) {
				Window.alert("La contraseña no ha sido modificada");
			} else {

				AdministradorServiceAsync adminService = GWT.create(AdministradorService.class);

				String nombreUsu = this.usuarioTb.getText();
				String passUsu = this.contraseniaPtb.getText();

				adminService.modificarUsuario(nombreUsu, passUsu, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");
					}

					@Override
					public void onSuccess(Boolean result) {
						if (result == true)
							Window.alert("La contraseña del usuario ha sido modificada de manera exitosa");
						else
							Window.alert("No se ha podido modificar la contraseña del usuario");

					}
				});
				padre.remove(numeroElemento(tituloTab));
			}
		}

	}

	public void cargarListaEmpleados(List<EmpleadoDTO> lista) {
		listaEmpleados = lista;
		for (int i = 0; i < listaEmpleados.size(); i++) {
			this.nroLegajoTb.addItem("" + listaEmpleados.get(i).getNroLegajo());
		}		
	}

	public void guardar() {

		if (usuarioTb.getText().isEmpty() || contraseniaPtb.getText().isEmpty()) {
			Window.alert("Los campos que poseen (*) son oblicatorios");
		} else {

			AdministradorServiceAsync adminServie = GWT.create(AdministradorService.class);

			adminServie.usuarioExistentes(usuarioTb.getText(), new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {

					if (result == true)
						Window.alert("Usuario ya existente");
					else {
						usuarioTieneEmpleado();
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
				}

			});

		}

	}

	public void usuarioTieneEmpleado() {

		if(listaEmpleados.size() < 1){
			Window.alert("No se puede crear un usuario, ya que no existen empleados sin usuario");
		}
		else{
			AdministradorServiceAsync adminService = GWT.create(AdministradorService.class);

			Integer legajo = new Integer(this.nroLegajoTb.getItemText(this.nroLegajoTb.getSelectedIndex()));

			adminService.usuarioTieneEmp(legajo, new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");

				}

				@Override
				public void onSuccess(Boolean result) {
					if (result == false) {
						guardarUsuario();
					} else {
						Window.alert("El empleado seleccionado ya posee un usuario registrado");
					}
				}
			});
		}
		
		

	}

	public void guardarUsuario() {

		UsuarioCompDTO nuevo = new UsuarioCompDTO();
		nuevo.setNombreUsu(this.usuarioTb.getText());
		nuevo.setPassUsu(this.contraseniaPtb.getText());
		nuevo.setRolUsu(this.rolTb.getItemText(this.rolTb.getSelectedIndex()));
		Integer legajo = new Integer(this.nroLegajoTb.getItemText(this.nroLegajoTb.getSelectedIndex()));
		nuevo.setNroLegajoEmp(legajo);

		AdministradorServiceAsync adminService = GWT.create(AdministradorService.class);

		adminService.guardarUsuario(nuevo, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");

			}

			@Override
			public void onSuccess(Boolean result) {

				if (result == true) {
					Window.alert("El usuario ha sido registrado de manera exitosa");
				} else {
					Window.alert("No se ha podido registrar el usuario");
				}

			}
		});

		padre.remove(numeroElemento(tituloTab));
	}

	public void salir(ClickEvent event) {

		padre.remove(numeroElemento(tituloTab));

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
