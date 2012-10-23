
package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

import edu.client.LoginService.LoginService;
import edu.client.LoginService.LoginServiceAsync;
import edu.server.servicio.LoginServiceImpl;
import edu.shared.DTO.UsuarioDTO;

public class P_Login extends Composite {

	final LoginServiceAsync loginServie = GWT.create(LoginService.class);
	
	private Constantes constante = GWT.create(Constantes.class);
    
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
			
			
			loginServie.getUsuario(usuarioTb.getText(), contraseniaTb.getText(), new AsyncCallback<UsuarioDTO>() 
					{
						public void onFailure(Throwable caught) {
							Window.alert("USUARIO Y CONTRASEÑA INCORRECTOS");	
						}

						public void onSuccess(UsuarioDTO result) {
							if (result.getRol().compareTo("ventas") == 0) {
								
								Window.alert("entro a ventas "+ result.getNombre());	
								//inicioPantallaVentas();
							}
						}
					});
				
				
//				if (usuarioTb.getText().compareTo("ventas") == 0
//						&& contraseniaTb.getText().compareTo("ventas") == 0) {
//					inicioPantallaVentas();
//				}
//				else if (usuarioTb.getText().compareTo("ingenieria") == 0
//						&& contraseniaTb.getText().compareTo("ingenieria") == 0){
//					inicioPantallaIngenieria();
//				}
//				else if (usuarioTb.getText().compareTo("produccion") == 0
//						&& contraseniaTb.getText().compareTo("produccion") == 0){
//					inicioPantallaProduccion();
//				}
//				else if (usuarioTb.getText().compareTo("compras") == 0
//						&& contraseniaTb.getText().compareTo("compras") == 0){
//					inicioPantallaCompras();
//				}
//				else if (usuarioTb.getText().compareTo("almacen") == 0
//						&& contraseniaTb.getText().compareTo("almacen") == 0){
//					inicioPantallaAlmacen();
//				}				
//				else
//					Window.alert("USUARIO Y CONTRASEÑA INCORRECTOS");
				
								
				
				
			}
		});
		
		iniciarSesion.setStyleName("gwt-ButtonLogin");

		log = new FlexTable();
		log.setSize("380px", "155px");

		log.setWidget(0, 0, logo);
		log.getFlexCellFormatter().setRowSpan(0, 0, 4);

		log.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);
		log.getCellFormatter().setVerticalAlignment(0, 0,
				HasVerticalAlignment.ALIGN_MIDDLE);
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
		log.getCellFormatter().setHorizontalAlignment(2, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);
		log.getCellFormatter().setHeight(2, 0, "50px");

		contenedor = new DockPanel();
		contenedor.setSize(ancho + "px", alto + "px");
		superior = new LayoutPanel();
		superior.setSize(ancho + "px", "155px");
		superior.setStyleName("superior2");

		centro = new LayoutPanel();
		centro.setSize(ancho + "px", altoCentro + "px");
		centro.setStyleName("panelFondo2");

		superior.add(log);

		superior.setWidgetLeftWidth(log, margenDerecho, Unit.PX, 400, Unit.PX);

		contenedor.add(superior, DockPanel.NORTH);
		contenedor.add(centro, DockPanel.CENTER);

		initWidget(contenedor);
	}

	protected void inicioPantallaVentas() {
//		P_PantallaVentas ventas = new P_PantallaVentas();
//		RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
//		RootPanel.get().add(ventas);
//		try {
//			this.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
	}
	
	protected void inicioPantallaIngenieria() {
		
//		P_PantallaIngenieria ingenieria = new P_PantallaIngenieria();
//		RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
//		RootPanel.get().add(ingenieria);
//		try {
//			this.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
		
	}
	
	protected void inicioPantallaProduccion() {
		
//		P_PantallaProduccion produccion = new P_PantallaProduccion();
//		RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
//		RootPanel.get().add(produccion);
//		try {
//			this.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
		
	}
	
	protected void inicioPantallaCompras() {
		
//		P_PantallaCompras compras = new P_PantallaCompras();
//		RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
//		RootPanel.get().add(compras);
//		try {
//			this.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
		
	}
	
	protected void inicioPantallaAlmacen() {
		/*
		P_PantallaAlmacen almacen = new P_PantallaAlmacen();
		RootPanel.get().remove(RootPanel.get().getWidgetIndex(this));
		RootPanel.get().add(almacen);
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		*/
	}
	
}
