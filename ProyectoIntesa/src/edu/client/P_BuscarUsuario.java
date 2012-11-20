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
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import edu.client.AdministradorService.AdministradorService;
import edu.client.AdministradorService.AdministradorServiceAsync;
import edu.shared.DTO.UsuarioCompDTO;

public class P_BuscarUsuario extends PopupPanel {

	private static final int COL_USUARIO = 0;
	private static final int COL_CONTRASENIA = 1;
	private static final int COL_NOMBRE = 2;
	private static final int COL_APELLIDO = 3;
	private static final int COL_ROL = 4;
	private static final int COL_MAS_INFO = 5;
	private static final int COL_QUITAR = 6;

	private FlexTable panel;
	private FlexTable tablaElementos;

	private UsuarioCompDTO usuarioSeleccionado;

	private ScrollPanel contenedorTabla;

	private Constantes constante = GWT.create(Constantes.class);

	private Label usuarios;
	private Label pie;

	private Button salir;

	public List<UsuarioCompDTO> listaUsuarios;

	public P_BuscarUsuario() {

		super(false);
		listaUsuarios = new LinkedList<UsuarioCompDTO>();

		AdministradorServiceAsync adminServie = GWT.create(AdministradorService.class);

		adminServie.getUsuarios(listaUsuarios, new AsyncCallback<List<UsuarioCompDTO>>() {
			@Override
			public void onSuccess(List<UsuarioCompDTO> result) {
				cargarListaUsuarios(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de empleados");
			}
		});

		setStyleName("fondoPopup");
		panel = new FlexTable();

		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("350px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_USUARIO, constante.usuario());
		tablaElementos.getCellFormatter().setWidth(0, COL_USUARIO, "18%");
		tablaElementos.setText(0, COL_CONTRASENIA, constante.contrasenia());
		tablaElementos.getCellFormatter().setWidth(0, COL_CONTRASENIA, "18%");
		tablaElementos.setText(0, COL_NOMBRE, constante.nombre());
		tablaElementos.getCellFormatter().setWidth(0, COL_NOMBRE, "18%");
		tablaElementos.setText(0, COL_APELLIDO, constante.apellido());
		tablaElementos.getCellFormatter().setWidth(0, COL_APELLIDO, "18%");
		tablaElementos.setText(0, COL_ROL, constante.rol());
		tablaElementos.getCellFormatter().setWidth(0, COL_ROL, "18%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "5%");
		tablaElementos.setText(0, COL_QUITAR, constante.quitar());
		tablaElementos.getCellFormatter().setWidth(0, COL_QUITAR, "5%");

		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");

		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});

		usuarios = new Label(constante.usuarios());
		usuarios.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");

		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		panel.setWidget(0, 0, usuarios);

		panel.getFlexCellFormatter().setColSpan(1, 0, 2);
		panel.setWidget(1, 0, contenedorTabla);

		panel.getFlexCellFormatter().setColSpan(2, 0, 2);
		panel.setWidget(2, 0, pie);

		panel.setWidget(3, 1, salir);
		panel.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_CENTER);

		setWidget(panel);
		panel.setSize("850px", "400px");

	}

	public void cargarListaUsuarios(List<UsuarioCompDTO> lista) {

		listaUsuarios = lista;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			Label info = new Label("");
			Label quitar = new Label("");
			quitar.setSize("16px", "16px");
			info.setSize("16px", "16px");
			quitar.addStyleName("labelBorrar");
			info.addStyleName("labelInfo");
			tablaElementos.setWidget(i + 1, COL_USUARIO, new Label(listaUsuarios.get(i).getNombreUsu()));
			tablaElementos.setWidget(i + 1, COL_CONTRASENIA, new Label(listaUsuarios.get(i).getPassUsu()));
			tablaElementos.setWidget(i + 1, COL_NOMBRE, new Label(listaUsuarios.get(i).getNombreEmp()));
			tablaElementos.setWidget(i + 1, COL_APELLIDO, new Label(listaUsuarios.get(i).getApellidoEmp()));
			tablaElementos.setWidget(i + 1, COL_ROL, new Label(listaUsuarios.get(i).getRolUsu()));
			tablaElementos.setWidget(i + 1, COL_MAS_INFO, info);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(i + 1, COL_MAS_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementos.setWidget(i + 1, COL_QUITAR, quitar);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(i + 1, COL_QUITAR, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementos.getRowFormatter().addStyleName(i + 1, "renglon");
			info.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Cell celda = tablaElementos.getCellForEvent(event);
					irAUsuario(listaUsuarios.get(celda.getRowIndex() - 1));
				}
			});
			quitar.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Cell celda = tablaElementos.getCellForEvent(event);
					boolean respuesta = Window.confirm("Esta seguro de eliminar el usuario: " + (listaUsuarios.get(celda.getRowIndex() - 1)).getNombreUsu()
							+ "?");
					if (respuesta)
						borrarUsuario((listaUsuarios.get(celda.getRowIndex() - 1)).getNombreUsu(), celda.getRowIndex());
				}
			});
		}

	}

	protected void borrarUsuario(String usuario, final int renglon) {

		final String usuarioAEliminar = usuario;

		AdministradorServiceAsync adminService = GWT.create(AdministradorService.class);

		adminService.eliminarUsuario(usuario, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR en el servicio");

			}

			@Override
			public void onSuccess(Boolean result) {
				if (result == true) {
					Window.alert("El usuario ha sido eliminado de manera exitosa");
					int indice = 0;
					while (indice < listaUsuarios.size()) {
						if (listaUsuarios.get(indice).getNombreUsu().compareTo(usuarioAEliminar) == 0) {
							listaUsuarios.remove(indice);
							indice = listaUsuarios.size();
						}
						indice++;
					}
					tablaElementos.removeRow(renglon);
				} else {
					Window.alert("No se ha podido eliminar el usuario");
				}
			}
		});

	}

	protected void irAUsuario(UsuarioCompDTO usuario) {
		usuarioSeleccionado = usuario;

		this.hide();
	}

	public UsuarioCompDTO getUsuario() {
		return usuarioSeleccionado;
	}

	protected void salir() {
		this.hide();

	}

}
