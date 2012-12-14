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

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.ProveedorDTO;

public class P_BuscarProveedor extends PopupPanel {

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
	private RadioButton tipo;
	private Label pie;


	private SuggestBox empresaSb;
	private SuggestBox rubroSb;
	private SuggestBox contactoSb;
	private SuggestBox tipoSb;

	private MultiWordSuggestOracle listaEmpresas;
	private MultiWordSuggestOracle listaRubros;
	private MultiWordSuggestOracle listaContactos;
	private MultiWordSuggestOracle listaTipos;

	private Button buscar;
	private Button salir;

	private List<ContactoDTO> contactos;
	private List<ProveedorDTO> proveedores;

	private ProveedorDTO empresaInfo;
	private ContactoDTO contactoInfo;
	private boolean modificarProveedor;

	public P_BuscarProveedor() {

		super(false);

		setStyleName("fondoPopup");
		contenedor = new FlexTable();

		this.modificarProveedor = false;

		listaEmpresas = new MultiWordSuggestOracle();
		listaRubros = new MultiWordSuggestOracle();
		listaContactos = new MultiWordSuggestOracle();
		listaTipos = new MultiWordSuggestOracle();

		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

		comprasService.getNombresEmpresas(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaEmpresas(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});

		comprasService.getRubros(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaRubros(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});

		comprasService.getContactos(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaContactos(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});
		
		comprasService.getTipos(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaTipos(result);
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
		
		tipo = new RadioButton(constante.tipo());
		tipo.setText(constante.tipo());
		tipo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionTipo();
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
		tipoSb = new SuggestBox(listaTipos);
		tipoSb.getTextBox().setEnabled(false);

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
		
		contenedor.setWidget(2, 2, tipo);
		contenedor.setWidget(2, 3, tipoSb);		

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
	
	protected void cargarSugerenciaTipos(List<String> result) {

		for (String sugerencia : result) {
			listaTipos.add(sugerencia);
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

			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

			comprasService.getEmpresasPorContacto(contacto, new AsyncCallback<List<ContactoDTO>>() {
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

		} else if (rubro.getValue() == true) {

			String rubro = rubroSb.getText();

			// ARMA EL ENCABEZADO DE LA LISTA
			tablaElemento.setText(0, COL_RUBRO, constante.rubro());
			tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
			tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
			tablaElemento.setText(0, COL_MAIL, constante.eMail());
			tablaElemento.setText(0, COL_INFO, constante.masInformacion());
			tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

			comprasService.getEmpresasPorRubro(rubro, new AsyncCallback<List<ProveedorDTO>>() {
				@Override
				public void onSuccess(List<ProveedorDTO> result) {

					if (result.size() > 0) {
						proveedores = result;
						cargarProveedores();
					} else
						Window.alert("No se han encontrado resultados");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR al buscar empresa");
				}
			});

		} else if (empresa.getValue() == true) {

			String empresa = empresaSb.getText();

			// ARMA EL ENCABEZADO DE LA LISTA
			tablaElemento.setText(0, COL_RUBRO, constante.rubro());
			tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
			tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
			tablaElemento.setText(0, COL_MAIL, constante.eMail());
			tablaElemento.setText(0, COL_INFO, constante.masInformacion());
			tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

			comprasService.getEmpresas(empresa, new AsyncCallback<List<ProveedorDTO>>() {
				@Override
				public void onSuccess(List<ProveedorDTO> result) {

					if (result.size() > 0) {
						proveedores = result;
						cargarProveedores();
					} else
						Window.alert("No se han encontrado resultados");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR al buscar empresa");
				}
			});

		} else if (empresa.getValue() == true) {

			String empresa = empresaSb.getText();

			// ARMA EL ENCABEZADO DE LA LISTA
			tablaElemento.setText(0, COL_RUBRO, constante.rubro());
			tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
			tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
			tablaElemento.setText(0, COL_MAIL, constante.eMail());
			tablaElemento.setText(0, COL_INFO, constante.masInformacion());
			tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

			comprasService.getEmpresas(empresa, new AsyncCallback<List<ProveedorDTO>>() {
				@Override
				public void onSuccess(List<ProveedorDTO> result) {

					if (result.size() > 0) {
						proveedores = result;
						cargarProveedores();
					} else
						Window.alert("No se han encontrado resultados");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR al buscar empresa");
				}
			});

		} else if (tipo.getValue() == true) {

			String tipo = tipoSb.getText();

			// ARMA EL ENCABEZADO DE LA LISTA
			tablaElemento.setText(0, COL_RUBRO, constante.rubro());
			tablaElemento.setText(0, COL_EMPRESA, constante.empresa());
			tablaElemento.setText(0, COL_TELEFONO, constante.telefono());
			tablaElemento.setText(0, COL_MAIL, constante.eMail());
			tablaElemento.setText(0, COL_INFO, constante.masInformacion());
			tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");

			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

			comprasService.getEmpresasPorTipo(tipo, new AsyncCallback<List<ProveedorDTO>>() {
				@Override
				public void onSuccess(List<ProveedorDTO> result) {

					if (result.size() > 0) {
						proveedores = result;
						cargarProveedores();
					} else
						Window.alert("No se han encontrado resultados");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR al buscar empresa");
				}
			});

		}else
			Window.alert("DEBE SELECCIONAR UN TIPO DE BUSQUEDA");
		
		
		
		
	} 

	protected void cargarProveedores() {

		for (int i = 0; i < proveedores.size(); i++) {

			Label info = new Label("");
			info.setSize("16px", "16px");
			info.setStyleName("labelInfo");
			
			tablaElemento.setWidget(i + 1, COL_RUBRO, new Label(proveedores.get(i).getRubro()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_RUBRO, true);
			tablaElemento.setWidget(i + 1, COL_EMPRESA, new Label(proveedores.get(i).getNombre()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_EMPRESA, true);
			tablaElemento.setWidget(i + 1, COL_TELEFONO, new Label(proveedores.get(i).getTelefono()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_TELEFONO, false);
			tablaElemento.setWidget(i + 1, COL_MAIL, new Label(proveedores.get(i).getMail()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_MAIL, true);
			tablaElemento.setWidget(i + 1, COL_INFO, info);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(i + 1, COL_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.getRowFormatter().setStyleName(i + 1, "tablaRenglon");
			info.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Cell celda = tablaElemento.getCellForEvent(event);
					String nombreEmp = proveedores.get(celda.getRowIndex() - 1).getNombre();

					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
					
					comprasService.getEmpresaCompleta(nombreEmp, new AsyncCallback<ProveedorDTO>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR al buscar empresa");
						}

						@Override
						public void onSuccess(ProveedorDTO result) {

							empresaInfo = result;
							final P_DatoEmpresa popUp = new P_DatoEmpresa(empresaInfo);
							popUp.setGlassEnabled(true);
							popUp.center();
							popUp.show();
							popUp.addCloseHandler(new CloseHandler<PopupPanel>() {
							boolean modificar = false;

								@Override
								public void onClose(CloseEvent<PopupPanel> event) {

									modificar = popUp.getModificarProveedor();

									if (modificar == true) {
										modificarProveedor();
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

	public void modificarProveedor() {
		this.modificarProveedor = true;
		this.hide();
	}

	public ProveedorDTO getProveedorDTO() {
		return this.empresaInfo;
	}

	public boolean getModificarProveedor() {
		return this.modificarProveedor;
	}

	protected void cargarContactos() {

		for (int i = 0; i < contactos.size(); i++) {

			Label infoC = new Label("");
			infoC.setSize("16px", "16px");
			infoC.setStyleName("labelInfo");
			
			tablaElemento.setWidget(i + 1, COL_RUBRO, new Label(contactos.get(i).getProveedor().getRubro()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_RUBRO, true);
			tablaElemento.setWidget(i + 1, COL_EMPRESA, new Label(contactos.get(i).getProveedor().getNombre()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_EMPRESA, true);
			tablaElemento.setWidget(i + 1, COL_NOMBRE, new Label(contactos.get(i).getNombre()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_NOMBRE, false);
			tablaElemento.setWidget(i + 1, COL_CARGO, new Label(contactos.get(i).getCargo()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_CARGO, true);
			tablaElemento.setWidget(i + 1, COL_INFO, infoC);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.getRowFormatter().setStyleName(1, "tablaRenglon");
			infoC.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Cell celda = tablaElemento.getCellForEvent(event);
					String nombreCont = contactos.get(celda.getRowIndex() - 1).getNombre();
					final String nombreEmp = contactos.get(celda.getRowIndex() - 1).getProveedor().getNombre();
					final String rubroEmp = contactos.get(celda.getRowIndex() - 1).getProveedor().getRubro();

					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
					comprasService.getContactoCompleto(nombreCont, nombreEmp, new AsyncCallback<ContactoDTO>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR al buscar empresa");
						}

						@Override
						public void onSuccess(ContactoDTO result) {
							contactoInfo = result;

							P_DatoEmpresa datoEmpresa = new P_DatoEmpresa(contactoInfo, nombreEmp, rubroEmp, "");
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
			tipo.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setValue("");
			empresaSb.getTextBox().setEnabled(false);
			rubroSb.getTextBox().setValue("");
			rubroSb.getTextBox().setEnabled(false);
			tipoSb.getTextBox().setValue("");
			tipoSb.getTextBox().setEnabled(false);

		} else {
			empresa.setValue(false);
			rubro.setValue(false);
			tipo.setValue(false);
			contacto.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setEnabled(true);
			rubroSb.getTextBox().setEnabled(true);
			tipoSb.getTextBox().setEnabled(true);
		}

	}

	protected void seleccionRubro() {
		if (rubro.getValue() == true) {
			contacto.setValue(false);
			tipo.setValue(false);
			empresa.setValue(false);
			rubroSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setValue("");
			empresaSb.getTextBox().setEnabled(false);
			contactoSb.getTextBox().setValue("");
			contactoSb.getTextBox().setEnabled(false);
			tipoSb.getTextBox().setValue("");
			tipoSb.getTextBox().setEnabled(false);
		} else {
			empresa.setValue(false);
			rubro.setValue(false);
			contacto.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setEnabled(true);
			rubroSb.getTextBox().setEnabled(true);
			tipo.setValue(false);
			tipoSb.getTextBox().setEnabled(true);
		}

	}
	
	protected void seleccionTipo() {
		if (tipo.getValue() == true) {
			contacto.setValue(false);
			rubro.setValue(false);
			empresa.setValue(false);
			tipoSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setValue("");
			empresaSb.getTextBox().setEnabled(false);
			contactoSb.getTextBox().setValue("");
			contactoSb.getTextBox().setEnabled(false);
			rubroSb.getTextBox().setValue("");
			rubroSb.getTextBox().setEnabled(false);
		} else {
			empresa.setValue(false);
			tipo.setValue(false);
			contacto.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			rubro.setValue(false);
			rubroSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setEnabled(true);
			tipoSb.getTextBox().setEnabled(true);
		}

	}

	protected void seleccionEmpresa() {
		if (empresa.getValue() == true) {
			contacto.setValue(false);
			rubro.setValue(false);
			tipo.setValue(false);
			empresaSb.getTextBox().setEnabled(true);
			contactoSb.getTextBox().setValue("");
			contactoSb.getTextBox().setEnabled(false);
			rubroSb.getTextBox().setValue("");
			rubroSb.getTextBox().setEnabled(false);
			tipoSb.getTextBox().setValue("");
			tipoSb.getTextBox().setEnabled(false);
		} else {
			empresa.setValue(false);
			rubro.setValue(false);
			tipo.setValue(false);
			contacto.setValue(false);
			contactoSb.getTextBox().setEnabled(true);
			empresaSb.getTextBox().setEnabled(true);
			rubroSb.getTextBox().setEnabled(true);
			tipoSb.getTextBox().setEnabled(true);
		}

	}

	protected void salir() {
		this.hide();

	}

}
