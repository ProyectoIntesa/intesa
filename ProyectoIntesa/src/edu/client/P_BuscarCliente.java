package edu.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;

import edu.client.VentasService.VentasService;
import edu.client.VentasService.VentasServiceAsync;
import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;

public class P_BuscarCliente extends PopupPanel {

	private static final int COL_RUBRO = 0;
	private static final int COL_EMPRESA = 1;
	private static final int COL_TELEFONO = 2;
	private static final int COL_MAIL = 3;
	private static final int COL_INFO = 4;
	private static final int COL_NOMBRE = 2;
	private static final int COL_CARGO = 3;

	private FlexTable contenedor;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;

	private Constantes constante = GWT.create(Constantes.class);

	private Label encabezado;
	private RadioButton empresa;
	private RadioButton rubro;
	private RadioButton contacto;
	private Label pie;


	private SuggestBox empresaSb;
	private SuggestBox rubroSb;
	private SuggestBox contactoSb;

	private MultiWordSuggestOracle listaEmpresas;
	private MultiWordSuggestOracle listaRubros;
	private MultiWordSuggestOracle listaContactos;

	private Button buscar;
	private Button salir;

	private List<ContactoDTO> contactos;
	private List<ClienteDTO> clientes;

	private ClienteDTO empresaInfo;
	private ContactoDTO contactoInfo;
	private boolean modificarCliente;

	public P_BuscarCliente() {

		super(false);

		setStyleName("fondoPopup");
		
		contenedor = new FlexTable();

		this.modificarCliente = false;

		listaEmpresas = new MultiWordSuggestOracle();
		listaRubros = new MultiWordSuggestOracle();
		listaContactos = new MultiWordSuggestOracle();

		VentasServiceAsync ventasService = GWT.create(VentasService.class);

		ventasService.getNombresEmpresas(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaEmpresas(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});

		ventasService.getRubros(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaRubros(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});

		ventasService.getContactos(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaContactos(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});

		encabezado = new Label(constante.buscarPor());
		encabezado.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");

		empresa = new RadioButton(constante.empresa());
		empresa.setText(constante.empresa());
		empresa.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionEmpresa();
			}
		});

		rubro = new RadioButton(constante.rubro());
		rubro.setText(constante.rubro());
		rubro.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionRubro();
			}
		});

		contacto = new RadioButton(constante.nombreContacto());
		contacto.setText(constante.nombreContacto());
		contacto.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionContacto();
			}
		});

		empresaSb = new SuggestBox(listaEmpresas);
		empresaSb.getTextBox().setEnabled(false);
		rubroSb = new SuggestBox(listaRubros);
		rubroSb.getTextBox().setEnabled(false);
		contactoSb = new SuggestBox(listaContactos);
		contactoSb.getTextBox().setEnabled(false);

		buscar = new Button(constante.buscar());
		buscar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cargarTabla();
			}
		});

		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});

		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("200px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		contenedor.setWidget(0, 0, encabezado);
		contenedor.getFlexCellFormatter().setColSpan(0, 0, 4);

		contenedor.setWidget(1, 0, empresa);
		contenedor.setWidget(1, 1, empresaSb);

		contenedor.setWidget(1, 2, rubro);
		contenedor.setWidget(1, 3, rubroSb);

		contenedor.setWidget(2, 0, contacto);
		contenedor.setWidget(2, 1, contactoSb);

		contenedor.setWidget(3, 0, buscar);
		contenedor.getFlexCellFormatter().setColSpan(3, 0, 4);
		contenedor.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);

		contenedor.setWidget(4, 0, contenedorTabla);
		contenedor.getFlexCellFormatter().setColSpan(4, 0, 4);

		contenedor.setWidget(5, 0, pie);
		contenedor.getFlexCellFormatter().setColSpan(5, 0, 4);

		contenedor.setWidget(6, 0, salir);
		contenedor.getFlexCellFormatter().setColSpan(6, 0, 4);
		contenedor.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);

		setWidget(contenedor);
		contenedor.setSize("600px", "300px");
		tablaElemento.setWidth("600px");

	}

	protected void cargarSugerenciaContactos(List<String> result) {

		for (String sugerencia : result) {
			listaContactos.add(sugerencia);
		}

	}

	protected void cargarSugerenciaRubros(List<String> result) {

		for (String sugerencia : result) {
			listaRubros.add(sugerencia);
		}

	}

	protected void cargarSugerenciaEmpresas(List<String> result) {

		for (String sugerencia : result) {
			listaEmpresas.add(sugerencia);
		}

	}

	protected void cargarTabla() {

		this.tablaElemento.clear();
		
		if (contacto.getValue() == true) {

			String contacto = contactoSb.getText();

			if(contacto.compareTo("")==0){
				// ARMA EL ENCABEZADO DE LA LISTA
				tablaElemento.setText(0, COL_RUBRO, constante.rubro());
				tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
				tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
				tablaElemento.setText(0, COL_MAIL, constante.eMail());
				tablaElemento.setText(0, COL_INFO, constante.masInformacion());
				tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

				VentasServiceAsync ventasService = GWT.create(VentasService.class);

				ventasService.getEmpresas(new AsyncCallback<List<ClienteDTO>>() {
					@Override
					public void onSuccess(List<ClienteDTO> result) {

						if (result.size() > 0) {
							clientes = result;
							cargarClientes();
						} else
							Window.alert("No se han encontrado resultados");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar empresa");
					}
				});
			}
			else{
				// ARMA EL ENCABEZADO DE LA LISTA
				tablaElemento.setText(0, COL_RUBRO, constante.rubro());
				tablaElemento.getCellFormatter().setWidth(0, COL_RUBRO, "145px");
				tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
				tablaElemento.getCellFormatter().setWidth(0, COL_EMPRESA, "145px");
				tablaElemento.setText(0, COL_NOMBRE, constante.nombreContacto());
				tablaElemento.getCellFormatter().setWidth(0, COL_NOMBRE, "145px");
				tablaElemento.setText(0, COL_CARGO, constante.cargo());
				tablaElemento.getCellFormatter().setWidth(0, COL_CARGO, "145px");
				tablaElemento.setText(0, COL_INFO, constante.masInformacion());
				tablaElemento.getCellFormatter().setWidth(0, COL_INFO, "20px");
				tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

				VentasServiceAsync ventasService = GWT.create(VentasService.class);

				ventasService.getEmpresasPorContacto(contacto, new AsyncCallback<List<ContactoDTO>>() {
					@Override
					public void onSuccess(List<ContactoDTO> result) {
						if (result.size() > 0) {
							contactos = result;
							cargarContactos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar contacto");
					}
				});
			}
				
			


		} else if (rubro.getValue() == true) {

			String rubro = rubroSb.getText();

			if(rubro.compareTo("")==0){
				// ARMA EL ENCABEZADO DE LA LISTA
				tablaElemento.setText(0, COL_RUBRO, constante.rubro());
				tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
				tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
				tablaElemento.setText(0, COL_MAIL, constante.eMail());
				tablaElemento.setText(0, COL_INFO, constante.masInformacion());
				tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

				VentasServiceAsync ventasService = GWT.create(VentasService.class);

				ventasService.getEmpresas(new AsyncCallback<List<ClienteDTO>>() {
					@Override
					public void onSuccess(List<ClienteDTO> result) {

						if (result.size() > 0) {
							clientes = result;
							cargarClientes();
						} else
							Window.alert("No se han encontrado resultados");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar empresa");
					}
				});
			}
			else{
				// ARMA EL ENCABEZADO DE LA LISTA
				tablaElemento.setText(0, COL_RUBRO, constante.rubro());
				tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
				tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
				tablaElemento.setText(0, COL_MAIL, constante.eMail());
				tablaElemento.setText(0, COL_INFO, constante.masInformacion());
				tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

				VentasServiceAsync ventasService = GWT.create(VentasService.class);

				ventasService.getEmpresasPorRubro(rubro, new AsyncCallback<List<ClienteDTO>>() {
					@Override
					public void onSuccess(List<ClienteDTO> result) {

						if (result.size() > 0) {
							clientes = result;
							cargarClientes();
						} else
							Window.alert("No se han encontrado resultados");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar empresa");
					}
				});
			}
			


		} else if (empresa.getValue() == true) {

			String empresa = empresaSb.getText();

			if(empresa.compareTo("")==0){
				// ARMA EL ENCABEZADO DE LA LISTA
				tablaElemento.setText(0, COL_RUBRO, constante.rubro());
				tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
				tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
				tablaElemento.setText(0, COL_MAIL, constante.eMail());
				tablaElemento.setText(0, COL_INFO, constante.masInformacion());
				tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

				VentasServiceAsync ventasService = GWT.create(VentasService.class);

				ventasService.getEmpresas(new AsyncCallback<List<ClienteDTO>>() {
					@Override
					public void onSuccess(List<ClienteDTO> result) {

						if (result.size() > 0) {
							clientes = result;
							cargarClientes();
						} else
							Window.alert("No se han encontrado resultados");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar empresa");
					}
				});
			}
			else{
				// ARMA EL ENCABEZADO DE LA LISTA
				tablaElemento.setText(0, COL_RUBRO, constante.rubro());
				tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
				tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
				tablaElemento.setText(0, COL_MAIL, constante.eMail());
				tablaElemento.setText(0, COL_INFO, constante.masInformacion());
				tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

				VentasServiceAsync ventasService = GWT.create(VentasService.class);

				ventasService.getEmpresas(empresa, new AsyncCallback<List<ClienteDTO>>() {
					@Override
					public void onSuccess(List<ClienteDTO> result) {

						if (result.size() > 0) {
							clientes = result;
							cargarClientes();
						} else
							Window.alert("No se han encontrado resultados");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar empresa");
					}
				});
			}
			


		} else if(contacto.getValue() == false && rubro.getValue() == false && empresa.getValue() == false){
			// ARMA EL ENCABEZADO DE LA LISTA
			tablaElemento.setText(0, COL_RUBRO, constante.rubro());
			tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
			tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
			tablaElemento.setText(0, COL_MAIL, constante.eMail());
			tablaElemento.setText(0, COL_INFO, constante.masInformacion());
			tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

			VentasServiceAsync ventasService = GWT.create(VentasService.class);

			ventasService.getEmpresas(new AsyncCallback<List<ClienteDTO>>() {
				@Override
				public void onSuccess(List<ClienteDTO> result) {

					if (result.size() > 0) {
						clientes = result;
						cargarClientes();
					} else
						Window.alert("No se han encontrado resultados");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR al buscar empresa");
				}
			});
			
			
		}

	}

	protected void cargarClientes() {

		for (int i = 0; i < clientes.size(); i++) {

			Label info = new Label("");
			info.setSize("16px", "16px");
			info.setStyleName("labelInfo");
			
			tablaElemento.setWidget(i + 1, COL_RUBRO, new Label(clientes.get(i).getRubro()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_RUBRO, true);
			tablaElemento.setWidget(i + 1, COL_EMPRESA, new Label(clientes.get(i).getNombre()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_EMPRESA, true);
			tablaElemento.setWidget(i + 1, COL_TELEFONO, new Label(clientes.get(i).getTelefono()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_TELEFONO, false);
			tablaElemento.setWidget(i + 1, COL_MAIL, new Label(clientes.get(i).getMail()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_MAIL, true);
			tablaElemento.setWidget(i + 1, COL_INFO, info);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(i + 1, COL_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.getRowFormatter().setStyleName(i + 1, "tablaRenglon");
			info.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Cell celda = tablaElemento.getCellForEvent(event);
					String nombreEmp = clientes.get(celda.getRowIndex() - 1).getNombre();

					VentasServiceAsync ventasService = GWT.create(VentasService.class);
					ventasService.getEmpresaCompleta(nombreEmp, new AsyncCallback<ClienteDTO>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR al buscar empresa");
						}

						@Override
						public void onSuccess(ClienteDTO result) {

							empresaInfo = result;
							final P_DatoEmpresa popUp = new P_DatoEmpresa(empresaInfo);
							popUp.setGlassEnabled(true);
							popUp.center();
							popUp.show();
							popUp.addCloseHandler(new CloseHandler<PopupPanel>() {
								boolean modificar = false;

								@Override
								public void onClose(CloseEvent<PopupPanel> event) {

									modificar = popUp.getModificarCliente();

									if (modificar == true) {
										modificarCliente();
									}
									if (modificar == false) {
										salir();
									}
								}
							});

						}

					});

				}
			});

		}
	}

	public void modificarCliente() {
		this.modificarCliente = true;
		this.hide();
	}

	public ClienteDTO getClienteDTO() {
		return this.empresaInfo;
	}

	public boolean getModificarCliente() {
		return this.modificarCliente;
	}

	protected void cargarContactos() {

		for (int i = 0; i < contactos.size(); i++) {

			Label infoC = new Label("");
			infoC.setSize("16px", "16px");
			infoC.setStyleName("labelInfo");
			
			tablaElemento.setWidget(i + 1, COL_RUBRO, new Label(contactos.get(i).getCliente().getRubro()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_RUBRO, true);
			tablaElemento.setWidget(i + 1, COL_EMPRESA, new Label(contactos.get(i).getCliente().getNombre()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_EMPRESA, true);
			tablaElemento.setWidget(i + 1, COL_NOMBRE, new Label(contactos.get(i).getNombre()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_NOMBRE, false);
			tablaElemento.setWidget(i + 1, COL_CARGO, new Label(contactos.get(i).getCargo()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_CARGO, true);
			tablaElemento.setWidget(i + 1, COL_INFO, infoC);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.getRowFormatter().setStyleName(i+1, "tablaRenglon");
			infoC.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Cell celda = tablaElemento.getCellForEvent(event);
					String nombreCont = contactos.get(celda.getRowIndex() - 1).getNombre();
					final String nombreEmp = contactos.get(celda.getRowIndex() - 1).getCliente().getNombre();
					final String rubroEmp = contactos.get(celda.getRowIndex() - 1).getCliente().getRubro();

					VentasServiceAsync ventasService = GWT.create(VentasService.class);
					ventasService.getContactoCompleto(nombreCont, nombreEmp, new AsyncCallback<ContactoDTO>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR al buscar empresa");
						}

						@Override
						public void onSuccess(ContactoDTO result) {
							contactoInfo = result;

							P_DatoEmpresa datoEmpresa = new P_DatoEmpresa(contactoInfo, nombreEmp, rubroEmp);
							datoEmpresa.setGlassEnabled(true);
							datoEmpresa.center();
							datoEmpresa.show();
							salir();
						}

					});

				}
			});

		}

	}

	protected void seleccionContacto() {
		if (contacto.getValue() == true) {
			empresa.setValue(false);
			rubro.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			contactoSb.setFocus(true);
			empresaSb.getTextBox().setValue("");
			empresaSb.getTextBox().setEnabled(false);
			rubroSb.getTextBox().setValue("");
			rubroSb.getTextBox().setEnabled(false);

		} else {
			empresa.setValue(false);
			rubro.setValue(false);
			contacto.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setEnabled(true);
			rubroSb.getTextBox().setEnabled(true);
		}

	}

	protected void seleccionRubro() {
		if (rubro.getValue() == true) {
			contacto.setValue(false);
			empresa.setValue(false);
			rubroSb.getTextBox().setEnabled(true);
			rubroSb.setFocus(true);
			empresaSb.getTextBox().setValue("");
			empresaSb.getTextBox().setEnabled(false);
			contactoSb.getTextBox().setValue("");
			contactoSb.getTextBox().setEnabled(false);
		} else {
			empresa.setValue(false);
			rubro.setValue(false);
			contacto.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setEnabled(true);
			rubroSb.getTextBox().setEnabled(true);
		}

	}

	protected void seleccionEmpresa() {
		if (empresa.getValue() == true) {
			contacto.setValue(false);
			rubro.setValue(false);
			empresaSb.getTextBox().setEnabled(true);
			empresaSb.setFocus(true);
			contactoSb.getTextBox().setValue("");
			contactoSb.getTextBox().setEnabled(false);
			rubroSb.getTextBox().setValue("");
			rubroSb.getTextBox().setEnabled(false);
		} else {
			empresa.setValue(false);
			rubro.setValue(false);
			contacto.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setEnabled(true);
			rubroSb.getTextBox().setEnabled(true);
		}

	}

	protected void salir() {
		this.hide();

	}

}
