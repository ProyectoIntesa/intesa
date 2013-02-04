package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import edu.client.P_NuevoUsuario;

import edu.client.P_BuscarEmpleado;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.UsuarioCompDTO;

public class P_PantallaAdministrador extends Composite {

	private Constantes constante = GWT.create(Constantes.class);
	
	private EmpleadoDTO empladoSeleccionado;
	private UsuarioCompDTO usuarioSeleccionado;
	private int ancho;
	private int alto;
	private int anchoLateral;
	private DockPanel contenedor;
	private LayoutPanel superior;
	private Label logoEmpresa;
	private Label lblUsuario;
	private Label logotipo;
	private Button btnCerrarSesin;
	private LayoutPanel menu;
	private Tree menuLateral;
	private TreeItem buscarEmpleado;
	private TreeItem nuevoEmpleado;
	private TreeItem buscarUsuario;
	private TreeItem nuevoUsuario;

	private TabPanel panelTrabajo;
	private ScrollPanel formulario;
	
	
	public P_PantallaAdministrador(String usuarioLogueado) {

		Window.setTitle("INTESA - ADMINISTRADOR");
		
		try {
			ancho = Window.getClientWidth() - 15;
			alto = Window.getClientHeight() - 13;
			anchoLateral = 180;
			contenedor = new DockPanel(); 
			contenedor.setStyleName("panelFondo");
			contenedor.setSize(ancho + "px", alto + "px");
			initWidget(contenedor);

			superior = new LayoutPanel();
			superior.setStyleName("superior");
			superior.setSize(ancho + "px", "75px");
			contenedor.add(superior, DockPanel.NORTH);

			logoEmpresa = new Label("");

			logoEmpresa.setStyleName("imagenLogo");
			superior.add(logoEmpresa);
			superior.setWidgetLeftWidth(logoEmpresa, 0.0, Unit.PX, 556.0, Unit.PX);
			superior.setWidgetTopHeight(logoEmpresa, 0.0, Unit.PX, 75.0, Unit.PX);

			lblUsuario = new Label(constante.usuario() + ": " + usuarioLogueado.toUpperCase());
			lblUsuario.setStyleName("labelSuperior");
			lblUsuario.setDirectionEstimator(true);
			lblUsuario.setWidth("500px");
			superior.add(lblUsuario);
			
			superior.setWidgetLeftWidth(lblUsuario, (ancho-(ancho/2)),Unit.PX,500.0, Unit.PX);
			superior.setWidgetTopHeight(lblUsuario, 16.0, Unit.PX, 29.0, Unit.PX);
			btnCerrarSesin = new Button(constante.cerrarSesion());
			btnCerrarSesin.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					cerrar();
				}
			});
			btnCerrarSesin.setStyleName("botonSuperior");
			superior.add(btnCerrarSesin);
			superior.setWidgetLeftWidth(btnCerrarSesin, ancho - 150, Unit.PX, 130, Unit.PX);
			superior.setWidgetTopHeight(btnCerrarSesin, 51.0, Unit.PX, 24.0, Unit.PX);

			menu = new LayoutPanel();
			menu.setStyleName("menuLateral");
			menu.setSize(anchoLateral + "px", (alto - 90) + "px");
			contenedor.add(menu, DockPanel.WEST);

			
			
			
			/**
			 * Arma el menú del panel lateral
			 * 
			 */

			logotipo = new Label("");
			logotipo.setStyleName("imagenIntesa");
			menu.add(logotipo);
			menu.setWidgetLeftWidth(logotipo, 5.0, Unit.PX, 150.0, Unit.PX);
			menu.setWidgetTopHeight(logotipo, alto - 250, Unit.PX, 150.0, Unit.PX);

			menuLateral = new Tree();
			menu.add(menuLateral);
			menuLateral.setSize("100%", "100%");

			TreeItem empleado = menuLateral.addItem(constante.empleado());
			empleado.setStyleName("elementoMenu");

			buscarEmpleado = new TreeItem(constante.buscar());
			buscarEmpleado.setStyleName("suElementoMenu");
			empleado.addItem(buscarEmpleado);

			nuevoEmpleado = new TreeItem(constante.nuevo());
			nuevoEmpleado.setStyleName("suElementoMenu");
			empleado.addItem(nuevoEmpleado);

			TreeItem usuario = menuLateral.addItem(constante.usuario());
			usuario.setStyleName("elementoMenu");

			buscarUsuario = new TreeItem(constante.buscar());
			buscarUsuario.setStyleName("suElementoMenu");
			usuario.addItem(buscarUsuario);

			nuevoUsuario = new TreeItem(constante.nuevo());
			nuevoUsuario.setStyleName("suElementoMenu");
			usuario.addItem(nuevoUsuario);

			menuLateral.addSelectionHandler(new SelectionHandler<TreeItem>() {
				public void onSelection(SelectionEvent<TreeItem> event) {
					procesa(event);
				}
			});

			/**
			 * Panel central, contiene las distintos formularios que se van
			 * agregando en pestañas cada vez que se elige un formulario del
			 * panel lateral
			 */
			panelTrabajo = new TabPanel();
			panelTrabajo.setAnimationEnabled(true);
			panelTrabajo.setStyleName("panelTrabajo");

			contenedor.add(panelTrabajo, DockPanel.CENTER);
			panelTrabajo.setSize((ancho - anchoLateral - 10) + "px", (alto - 90) + "px");

			Window.addResizeHandler(new ResizeHandler() {
				public void onResize(ResizeEvent event) {

				}
			});

			Window.addWindowClosingHandler(new Window.ClosingHandler() {
				public void onWindowClosing(Window.ClosingEvent closingEvent) {
					closingEvent.setMessage("");
				}
			});
		} catch (Throwable e) {
			Window.alert("Excepcion: " + e.getMessage());
		}

	}

	protected void cerrar() {

		int cantidad = RootPanel.get().getWidgetIndex(this);
		RootPanel.get().remove(cantidad);
		P_Login log = new P_Login();
		RootPanel.get().add(log);
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void procesa(SelectionEvent<TreeItem> event) {
		
		String titulo;
		int tab;
		
		if (event.getSelectedItem() == nuevoEmpleado) {
								
			titulo = constante.empleado();
			tab = numeroElemento(titulo);
			
			if (tab == -1) {

				formulario = new ScrollPanel();
				formulario.setTitle(titulo);
				formulario.setStyleName("panelFormulario");
				formulario.setSize((ancho - anchoLateral - 25) + "px", (alto - 145) + "px");
				P_NuevoEmpleado empleado = new P_NuevoEmpleado(panelTrabajo,titulo);
				formulario.add(empleado);
				panelTrabajo.add(formulario, titulo, false);
				panelTrabajo.selectTab(numeroElemento(titulo));
			} else
				panelTrabajo.selectTab(tab);
		}

		else if (event.getSelectedItem() == buscarEmpleado) {
			
				if(this.numeroElemento(constante.modificarEmpleado())!=-1){
					Window.alert("Para realizar una nueva busqueda debe cerrar previamente la pestaña \"MODIFICAR EMPLEADO\"");
				}
				else{
					
					final P_BuscarEmpleado popUp = new P_BuscarEmpleado(this.panelTrabajo);
					popUp.setGlassEnabled(true);
					popUp.center();
					popUp.show();
					popUp.addCloseHandler(new CloseHandler<PopupPanel>() {

						@Override
						public void onClose(CloseEvent<PopupPanel> event) {
							
							empladoSeleccionado= popUp.getEmpleado();
						
							if (empladoSeleccionado != null)
							{
								modificarEmpleado();
							}
						}
					});
				}

		}

		else if (event.getSelectedItem() == nuevoUsuario) {
			
				titulo = constante.usuario();
				tab = numeroElemento(titulo);
			
				if (tab == -1) {

					formulario = new ScrollPanel();
					formulario.setTitle(titulo);
					formulario.setStyleName("panelFormulario");
					formulario.setSize((ancho - anchoLateral - 25) + "px", (alto - 145) + "px");
					P_NuevoUsuario usuario = new P_NuevoUsuario(panelTrabajo, constante.usuario());
					formulario.add(usuario);
					panelTrabajo.add(formulario, titulo, false);
					panelTrabajo.selectTab(numeroElemento(titulo));
				} else
					panelTrabajo.selectTab(tab);
		}
				

		else if (event.getSelectedItem() == buscarUsuario) {
			if(this.numeroElemento(constante.modificarUsuario())!=-1){
				Window.alert("Para realizar una nueva busqueda debe cerrar previamente la pestaña \"MODIFICAR USUARIO\"");
			}
			else{
				
				final P_BuscarUsuario popUp = new P_BuscarUsuario();
				popUp.setGlassEnabled(true);
				popUp.center();
				popUp.show();
				popUp.addCloseHandler(new CloseHandler<PopupPanel>() {

					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						
						usuarioSeleccionado= popUp.getUsuario();
					
						if (usuarioSeleccionado != null)
						{
							modificarUsuario();
						}
					}
				});
			}
			}
		
	}
	
	protected void modificarEmpleado() {
		String titulo;
		int tab;
		titulo = constante.modificarEmpleado();
		tab = numeroElemento(titulo);
		
		if (tab == -1) {

			formulario = new ScrollPanel();
			formulario.setTitle(titulo);
			formulario.setStyleName("panelFormulario");
			formulario.setSize((ancho - anchoLateral - 25) + "px", (alto - 145) + "px");
			P_NuevoEmpleado empleado = new P_NuevoEmpleado(panelTrabajo,empladoSeleccionado,titulo);
			formulario.add(empleado);
			panelTrabajo.add(formulario, titulo, false);
			panelTrabajo.selectTab(numeroElemento(titulo));
		} else
			panelTrabajo.selectTab(tab);
	}
	
	protected void modificarUsuario() {
		String titulo;
		int tab;
		titulo = constante.modificarUsuario();
		tab = numeroElemento(titulo);
		
		if (tab == -1) {

			formulario = new ScrollPanel();
			formulario.setTitle(titulo);
			formulario.setStyleName("panelFormulario");
			formulario.setSize((ancho - anchoLateral - 25) + "px", (alto - 145) + "px");
			P_NuevoUsuario usuario = new P_NuevoUsuario(panelTrabajo,usuarioSeleccionado,titulo);
			formulario.add(usuario);
			panelTrabajo.add(formulario, titulo, false);
			panelTrabajo.selectTab(numeroElemento(titulo));
		} else
			panelTrabajo.selectTab(tab);
	}
	
	private int numeroElemento(String titulo) {

		int elemento = -1;
		int contador = 0;

		while (elemento == -1 && contador < panelTrabajo.getWidgetCount()) {

			if (panelTrabajo.getWidget(contador).getTitle().compareTo(titulo) == 0)
				elemento = contador;
			else
				contador++;
		}

		return elemento;
	}

}
