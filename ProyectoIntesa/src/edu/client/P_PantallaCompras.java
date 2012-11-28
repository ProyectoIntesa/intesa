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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.ProveedorDTO;

public class P_PantallaCompras extends Composite {

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
	private TreeItem nuevoProveedor;
	private TreeItem buscarProveedor;
	private TreeItem guardadas;
	private TreeItem materialAComprar;
	private TreeItem insumoAComprar;
	
	//-------------------------------------------------------------------------------
	private TreeItem insumos;
	private TreeItem insumosCargar;
	private TreeItem insumosBuscar;
	private TreeItem productos;
	private TreeItem productosCargar;
	private TreeItem productosBuscar;
	//-------------------------------------------------------------------------------
	
	private TabPanel panelTrabajo;
	private ScrollPanel formulario;
	private ProveedorDTO proveedorSelec;
	private InsumoDTO insumoSelec;

	public P_PantallaCompras(String usuarioLogueado) {

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

		TreeItem proveedores = menuLateral.addItem(constante.proveedores());
		proveedores.setStyleName("elementoMenu");

		nuevoProveedor = new TreeItem(constante.nuevo());
		nuevoProveedor.setStyleName("suElementoMenu");
		proveedores.addItem(nuevoProveedor);

		buscarProveedor = new TreeItem(constante.buscar());
		buscarProveedor.setStyleName("suElementoMenu");
		proveedores.addItem(buscarProveedor);

		TreeItem ordenCompra = menuLateral.addItem(constante.ordenDeCompra());
		ordenCompra.setStyleName("elementoMenu");

		guardadas = new TreeItem(constante.guardadas());
		guardadas.setStyleName("suElementoMenu");
		ordenCompra.addItem(guardadas);

		materialAComprar = new TreeItem(constante.materialAComprar());
		materialAComprar.setStyleName("suElementoMenu");
		ordenCompra.addItem(materialAComprar);

		insumoAComprar = new TreeItem(constante.insumoAComprar());
		insumoAComprar.setStyleName("suElementoMenu");
		ordenCompra.addItem(insumoAComprar);
		
		//-------------------------------------------------------------------------------
		TreeItem insumos = menuLateral.addItem(constante.insumos());
		insumos.setStyleName("elementoMenu");
		
		insumosCargar = new TreeItem(constante.cargar());
		insumosCargar.setStyleName("suElementoMenu");
		insumos.addItem(insumosCargar);
		
		insumosBuscar = new TreeItem(constante.buscar());
		insumosBuscar.setStyleName("suElementoMenu");
		insumos.addItem(insumosBuscar);		
		
		TreeItem productos = menuLateral.addItem(constante.productos());
		productos.setStyleName("elementoMenu");
		
		productosCargar = new TreeItem(constante.cargar());
		productosCargar.setStyleName("suElementoMenu");
		productos.addItem(productosCargar);
		
		productosBuscar = new TreeItem(constante.buscar());
		productosBuscar.setStyleName("suElementoMenu");
		productos.addItem(productosBuscar);		
		//-------------------------------------------------------------------------------		

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

		String titulo;
		int tab;
		
		if (event.getSelectedItem() == nuevoProveedor) {

			titulo = constante.nuevoProveedor();
			tab = numeroElemento(titulo);
			if (tab == -1) {

				formulario = new ScrollPanel();
				formulario.setTitle(titulo);
				formulario.setStyleName("panelFormulario");
				formulario.setSize((ancho - anchoLateral - 25) + "px",(alto - 145) + "px");
				P_FormularioProveedor proveedor = new P_FormularioProveedor(panelTrabajo);
				formulario.add(proveedor);
				panelTrabajo.add(formulario, titulo, false);
				panelTrabajo.selectTab(numeroElemento(titulo));
			} else
				panelTrabajo.selectTab(tab);
			
			
		}

		if (event.getSelectedItem() == buscarProveedor) {

			if(this.numeroElemento(constante.modificarProveedor())!=-1){
				Window.alert("Para realizar una nueva busqueda debe cerrar previamente la pestaña MODIFICAR PROVEEDOR");
			}
			else{
				
				final P_BuscarProveedor popUp = new P_BuscarProveedor();
				popUp.setGlassEnabled(true);
				popUp.center();
				popUp.show();
				popUp.addCloseHandler(new CloseHandler<PopupPanel>() {

					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						
						proveedorSelec= popUp.getProveedorDTO();
						boolean modificar = popUp.getModificarProveedor();
					
						if (modificar == true)
						{
							modificarProveedor();
						}
					}
				});
			}
			
		}

		if (event.getSelectedItem() == insumosCargar) {

			titulo = constante.nuevoInsumo();
			tab = numeroElemento(titulo);
			if (tab == -1) {

				formulario = new ScrollPanel();
				formulario.setTitle(titulo);
				formulario.setStyleName("panelFormulario");
				formulario.setSize((ancho - anchoLateral - 25) + "px",(alto - 145) + "px");
				P_FormularioInsumo insumo = new P_FormularioInsumo(panelTrabajo);
				formulario.add(insumo);
				panelTrabajo.add(formulario, titulo, false);
				panelTrabajo.selectTab(numeroElemento(titulo));
			} else
				panelTrabajo.selectTab(tab);
			
		}
		
		if (event.getSelectedItem() == insumosBuscar) {
			
			if(this.numeroElemento(constante.modificarInsumo())!=-1){
				Window.alert("Para realizar una nueva busqueda debe cerrar previamente la pestaña MODIFICAR INSUMO");
			}
			else{
				
				final P_BuscarInsumo popUp = new P_BuscarInsumo();
				popUp.setGlassEnabled(true);
				popUp.center();
				popUp.show();
				popUp.addCloseHandler(new CloseHandler<PopupPanel>() {

					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						
						insumoSelec= popUp.getInsumoDTO();
						boolean modificar = popUp.getModificarInsumo();
					
						if (modificar == true)
						{
							modificarInsumo();
						}
					}
				});
			}
		}
		
		if (event.getSelectedItem() == productosBuscar) {

		}
		
		if (event.getSelectedItem() == productosCargar) {

		}
		
		if (event.getSelectedItem() == guardadas) {

		}

		if (event.getSelectedItem() == materialAComprar) {

		}

		if (event.getSelectedItem() == insumoAComprar) {

		}

	}
	
	
	protected void modificarProveedor() {
		String titulo;
		int tab;
		titulo = constante.modificarProveedor();
		tab = numeroElemento(titulo);

		if (tab == -1) {

			formulario = new ScrollPanel();
			formulario.setTitle(titulo);
			formulario.setStyleName("panelFormulario");
			formulario.setSize((ancho - anchoLateral - 25) + "px",(alto - 145) + "px");
			P_FormularioProveedor proveedor = new P_FormularioProveedor(panelTrabajo,this.proveedorSelec,titulo);
			formulario.add(proveedor);
			panelTrabajo.add(formulario, titulo, false);
			panelTrabajo.selectTab(numeroElemento(titulo));
		} else
			panelTrabajo.selectTab(tab);
		
		
	}
	
	protected void modificarInsumo(){
		
		String titulo;
		int tab;
		titulo = constante.modificarInsumo();
		tab = numeroElemento(titulo);

		if (tab == -1) {

			formulario = new ScrollPanel();
			formulario.setTitle(titulo);
			formulario.setStyleName("panelFormulario");
			formulario.setSize((ancho - anchoLateral - 25) + "px",(alto - 145) + "px");
			P_FormularioInsumo insumo = new P_FormularioInsumo(panelTrabajo,this.insumoSelec,titulo);
			formulario.add(insumo);
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
