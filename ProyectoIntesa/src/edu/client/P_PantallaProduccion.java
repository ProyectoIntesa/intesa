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
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class P_PantallaProduccion extends Composite {

	private Constantes constante = GWT.create(Constantes.class);

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
	private TreeItem verOrdenesFabricacion;
	private TreeItem nuevaOrdenSuministro;
	private TabPanel panelTrabajo;

	public P_PantallaProduccion(String usuarioLogueado) {

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

		superior.setWidgetLeftWidth(lblUsuario, (ancho - (ancho / 2)), Unit.PX, 500.0, Unit.PX);
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

		TreeItem ordenDeFabricacion = menuLateral.addItem(constante.ordenDeFabricacion());
		ordenDeFabricacion.setStyleName("elementoMenu");

		verOrdenesFabricacion = new TreeItem(constante.verOrdenes());
		verOrdenesFabricacion.setStyleName("suElementoMenu");
		ordenDeFabricacion.addItem(verOrdenesFabricacion);

		TreeItem ordenSuministro = menuLateral.addItem(constante.ordenDeSuministro());
		ordenSuministro.setStyleName("elementoMenu");

		nuevaOrdenSuministro = new TreeItem(constante.nueva());
		nuevaOrdenSuministro.setStyleName("suElementoMenu");
		ordenSuministro.addItem(nuevaOrdenSuministro);

		menuLateral.addSelectionHandler(new SelectionHandler<TreeItem>() {
			public void onSelection(SelectionEvent<TreeItem> event) {
				procesa(event);
			}
		});

		/**
		 * Panel central, contiene las distintos formularios que se van
		 * agregando en pestañas cada vez que se elige un formulario del panel
		 * lateral
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

		if (event.getSelectedItem() == verOrdenesFabricacion) {

		}

		if (event.getSelectedItem() == nuevaOrdenSuministro) {

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