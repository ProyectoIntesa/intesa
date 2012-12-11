package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.UsuarioCompDTO;

public class P_PantallaAlmacen extends Composite {

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
	private TreeItem ingresarRemitoEx;
	private TreeItem buscarRemitoEx;
	private TreeItem ingresarRemitoIn;
	private TreeItem buscarRemitoIn;
	private String usuario;

	private TabPanel panelTrabajo;
	private ScrollPanel formulario;
	
	public P_PantallaAlmacen(String usuarioLogueado) {

		try {
			this.usuario = usuarioLogueado;
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

			TreeItem remitoEx = menuLateral.addItem(constante.IngresoDeMateriales());
			remitoEx.setStyleName("elementoMenu");

			ingresarRemitoEx = new TreeItem(constante.ingresarRemito());
			ingresarRemitoEx.setStyleName("suElementoMenu");
			remitoEx.addItem(ingresarRemitoEx);
			
			buscarRemitoEx = new TreeItem(constante.buscarRemito());
			buscarRemitoEx.setStyleName("suElementoMenu");
			remitoEx.addItem(buscarRemitoEx);


			TreeItem remitoIn = menuLateral.addItem(constante.egresoDeMateriales());
			remitoIn.setStyleName("elementoMenu");

			ingresarRemitoIn = new TreeItem(constante.generarRemito());
			ingresarRemitoIn.setStyleName("suElementoMenu");
			remitoIn.addItem(ingresarRemitoIn);
			
			buscarRemitoIn = new TreeItem(constante.buscarRemito());
			buscarRemitoIn.setStyleName("suElementoMenu");
			remitoIn.addItem(buscarRemitoIn);


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

		if (event.getSelectedItem() == ingresarRemitoEx) {

			if(this.numeroElemento(constante.remitoExterno())!=-1){
				Window.alert("Para realizar una nueva busqueda debe cerrar previamente la pestaña REMITO EXTERNO");
			}
			else{
				
				P_PreguntaPorNroOrdenCompra popUp = new P_PreguntaPorNroOrdenCompra(this.usuario);
				popUp.setGlassEnabled(true);
				popUp.center();
				popUp.show();
//				popUp.addCloseHandler(new CloseHandler<PopupPanel>() {
//
//					@Override
//					public void onClose(CloseEvent<PopupPanel> event) {
//						
//						proveedorSelec= popUp.getProveedorDTO();
//						boolean modificar = popUp.getModificarProveedor();
//					
//						if (modificar == true)
//						{
//							modificarProveedor();
//						}
//					}
//				});
			}
			
		}
		
		if (event.getSelectedItem() == buscarRemitoEx) {

			P_PreguntaPorNroOrdenCompraYRemito popUp = new P_PreguntaPorNroOrdenCompraYRemito();
			popUp.setGlassEnabled(true);
			popUp.center();
			popUp.show();			
			
		}

		if (event.getSelectedItem() == ingresarRemitoIn) {
			if(this.numeroElemento(constante.remitoExterno())!=-1){
				Window.alert("Para realizar una nueva busqueda debe cerrar previamente la pestaña REMITO INTERNO");
			}
			else{
				P_PreguntarPorOrdenProvision popUp = new P_PreguntarPorOrdenProvision(this.usuario);
				popUp.setGlassEnabled(true);
				popUp.center();
				popUp.show();
			}
		}
		
		if (event.getSelectedItem() == buscarRemitoIn) {
			
			
		}

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
