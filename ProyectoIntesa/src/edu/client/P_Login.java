package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PopupPanel;

import edu.client.LoginService.LoginService;
import edu.client.LoginService.LoginServiceAsync;
import edu.server.servicio.LoginServiceImpl;
import edu.shared.DTO.UsuarioDTO;

public class P_Login extends Composite {

	final LoginServiceAsync loginServie = GWT.create(LoginService.class);

	private Constantes constante = GWT.create(Constantes.class);

	private String rolSeleccionado;
	
	private DockPanel contenedor;
	private LayoutPanel superior;
	private LayoutPanel centro;
	private FlexTable log;

	private int ancho;
	private int alto;
	private int altoCentro;
	private int margenDerecho;

	private Label usuario;
	private Label contrasenia;
	private Label logo;
	private TextBox usuarioTb;
	private PasswordTextBox contraseniaTb;

	private Button iniciarSesion;

	public P_Login() {

		Window.setTitle("Ingeniería Técnica S.A.");
		
		ancho = Window.getClientWidth() - 10;
		alto = Window.getClientHeight() - 13;
		altoCentro = Window.getClientHeight() - 168;
		margenDerecho = (Window.getClientWidth() - 380) / 2;

		usuario = new Label(constante.usuario());
		usuario.setStyleName("gwt-LabelFormularioLogin2");
		contrasenia = new Label(constante.contrasenia());
		contrasenia.setStyleName("gwt-LabelFormularioLogin2");
		logo = new Label();
		logo.setSize("150px", "150px");
		logo.setStyleName("imagenIntesa");
		usuarioTb = new TextBox();
		usuarioTb.setStyleName("gwt-TextArea");
		contraseniaTb = new PasswordTextBox();
		contraseniaTb.setStyleName("gwt-TextArea");

		iniciarSesion = new Button(constante.iniciarSesion());

		iniciarSesion.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				iniciarSesion.setEnabled(false);
				
				loginServie.getUsuario(usuarioTb.getText(), contraseniaTb.getText(), new AsyncCallback<UsuarioDTO>() {
										
					public void onFailure(Throwable caught) {
									
						Window.alert("ERROR EN EL SISTEMA");						
					}



					public void onSuccess(UsuarioDTO result) {
						if (result != null) {
							if (result.getRol().compareTo("COMPRAS") == 0) {
								inicioPantallaCompras(result.getNombre());
							} else if (result.getRol().compareTo("ADMINISTRADOR") == 0) {
								inicioPantallaAdministrador(result.getNombre());
							} else if (result.getRol().compareTo("VENTAS") == 0){
								inicioPantallaVentas(result.getNombre());
							} else if (result.getRol().compareTo("GERENTE DE PRODUCCION") == 0){
								inicioPantallaGerenteProduccion(result.getNombre());
							} else if (result.getRol().compareTo("GERENTE DE COMPRAS") == 0){
								inicioPantallaGerenteCompras(result.getNombre());
							} else if (result.getRol().compareTo("JEFE DE FABRICA") == 0){
								inicioPantallaJefeFabrica(result.getNombre());
							} else if (result.getRol().compareTo("SUPERVISOR") == 0){
								inicioPantallaSupervisorProduccion(result.getNombre());
							} else if (result.getRol().compareTo("INGENIERIA") == 0){
								inicioPantallaIngenieria(result.getNombre());
							} else if (result.getRol().compareTo("ALMACEN") == 0){
								inicioPantallaAlmacen(result.getNombre());
							} else if (result.getRol().compareTo("SUPER USUARIO") == 0){
								
								final P_SeleccionarUsuario popUp = new P_SeleccionarUsuario();
								popUp.setGlassEnabled(true);
								popUp.center();
								popUp.show();
								final String nombre = result.getNombre();
								
								popUp.addCloseHandler(new CloseHandler<PopupPanel>() {

									@Override
									public void onClose(CloseEvent<PopupPanel> event) {
										
										rolSeleccionado= popUp.devolverRolSeleccionado();
									
										if (rolSeleccionado.compareTo("VENTAS") == 0){
											inicioPantallaVentas(nombre);
										} else if (rolSeleccionado.compareTo("COMPRAS") == 0){
											inicioPantallaCompras(nombre);
										} else if (rolSeleccionado.compareTo("GERENTE DE COMPRAS") == 0){
											inicioPantallaGerenteCompras(nombre);
										} else if (rolSeleccionado.compareTo("INGENIERIA") == 0){
											inicioPantallaIngenieria(nombre);
										} else if (rolSeleccionado.compareTo("SUPERVISOR") == 0){
											inicioPantallaSupervisorProduccion(nombre);
										} else if (rolSeleccionado.compareTo("JEFE DE FABRICA") == 0){
											inicioPantallaJefeFabrica(nombre);
										} else if (rolSeleccionado.compareTo("GERENTE DE PRODUCCION") == 0){
											inicioPantallaGerenteProduccion(nombre);
										} else if (rolSeleccionado.compareTo("ALMACEN") == 0){
											inicioPantallaAlmacen(nombre);
										} else if (rolSeleccionado.compareTo("SALIR") == 0){
											cerrar();
										}
										
									}
								});
								
								
							}
						} else {
							iniciarSesion.setEnabled(true);
							Window.alert("Usuario y contraseña incorrectas");
						}
					}
				});
			}
		});

		iniciarSesion.setStyleName("gwt-ButtonLogin");

		log = new FlexTable();
		log.setSize("380px", "155px");

		log.setWidget(0, 0, logo);
		log.getFlexCellFormatter().setRowSpan(0, 0, 4);

		log.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		log.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		log.setWidget(0, 1, usuario);
		log.setWidget(0, 2, usuarioTb);
		log.getCellFormatter().setHeight(0, 1, "50px");
		log.getCellFormatter().setHeight(0, 2, "50px");
		log.setWidget(1, 0, contrasenia);
		log.setWidget(1, 1, contraseniaTb);
		log.getCellFormatter().setHeight(1, 0, "50px");
		log.getCellFormatter().setHeight(1, 1, "50px");
		log.setWidget(2, 0, iniciarSesion);
		log.getFlexCellFormatter().setColSpan(2, 0, 2);
		log.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		log.getCellFormatter().setHeight(2, 0, "50px");

		contenedor = new DockPanel();
		contenedor.setSize(ancho + "px", alto + "px");
		superior = new LayoutPanel();
		superior.setSize(ancho + "px", "155px");
		superior.setStyleName("superior2");

		centro = new LayoutPanel();
		centro.setSize(ancho + "px", altoCentro + "px");
		centro.setStyleName("panelFondo3");

		superior.add(log);

		superior.setWidgetLeftWidth(log, margenDerecho, Unit.PX, 400, Unit.PX);

		contenedor.add(superior, DockPanel.NORTH);
		contenedor.add(centro, DockPanel.CENTER);

		initWidget(contenedor);
	}

	protected void inicioPantallaAdministrador(String usuario) {
		P_PantallaAdministrador adm = new P_PantallaAdministrador(usuario);
		RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		RootPanel.get().add(adm);
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void inicioPantallaVentas(String usuario) {
	
		P_PantallaVentas ventas = new P_PantallaVentas(usuario);
		RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		RootPanel.get().add(ventas);
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void inicioPantallaIngenieria(String usuario) {

		 P_PantallaIngenieria ingenieria = new P_PantallaIngenieria(usuario);
		 RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		 RootPanel.get().add(ingenieria);
		 try {
			 this.finalize();
		 } catch (Throwable e) {
			 e.printStackTrace();
		 }


	}
	
	protected void inicioPantallaGerenteCompras(String usuario) {

		 P_PantallaGerenteCompras gerenteCompras = new P_PantallaGerenteCompras(usuario);
		 RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		 RootPanel.get().add(gerenteCompras);
		 try {
			 this.finalize();
		 } catch (Throwable e) {
			 e.printStackTrace();
		 }


	}
	
	protected void inicioPantallaJefeFabrica(String usuario) {

		 P_PantallaJefeFabrica jefeFabrica = new P_PantallaJefeFabrica(usuario,"JEFE DE FABRICA");
		 RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		 RootPanel.get().add(jefeFabrica);
		 try {
			 this.finalize();
		 } catch (Throwable e) {
			 e.printStackTrace();
		 }


	}

	protected void inicioPantallaGerenteProduccion(String usuario) {

		 P_PantallaGerenteProduccion produccion = new P_PantallaGerenteProduccion(usuario,"GERENTE DE PRODUCCION");
		 RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		 RootPanel.get().add(produccion);
		 try {
		 this.finalize();
		 } catch (Throwable e) {
		 e.printStackTrace();
		 }

	}
	
	protected void inicioPantallaSupervisorProduccion(String usuario) {

		 P_PantallaSupervisorProduccion produccion = new P_PantallaSupervisorProduccion(usuario,"SUPERVISOR");
		 RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		 RootPanel.get().add(produccion);
		 try {
		 this.finalize();
		 } catch (Throwable e) {
		 e.printStackTrace();
		 }

	}

	protected void inicioPantallaCompras(String usuario) {

		 P_PantallaCompras compras = new P_PantallaCompras(usuario);
		 RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		 RootPanel.get().add(compras);
		 try {
		 this.finalize();
		 } catch (Throwable e) {
		 e.printStackTrace();
		 }
	}

	protected void inicioPantallaAlmacen(String usuario) {
		
		P_PantallaAlmacen almacen = new P_PantallaAlmacen(usuario);
		RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		RootPanel.get().add(almacen); 
		try { 
			 this.finalize(); 
		} catch(Throwable e) 
		{ e.printStackTrace(); }
		 
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

}
