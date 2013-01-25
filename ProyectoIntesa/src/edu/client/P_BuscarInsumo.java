package edu.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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
import edu.shared.DTO.InsumoDTO;

public class P_BuscarInsumo extends PopupPanel {

	
	private static final int COL_INSUMO = 0;
	private static final int COL_MARCA = 1;
	private static final int COL_CATEGORIA = 2;
	private static final int COL_INFO = 3;


	private FlexTable contenedor;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;
	private FlexTable botones1;
	private FlexTable botones2;

	private Constantes constante = GWT.create(Constantes.class);

	private Label encabezado;
	private RadioButton insumo;
	private RadioButton marca;
	private RadioButton categoria;
	private RadioButton proveedor;
	private Label pie;

	private SuggestBox insumoSb;
	private SuggestBox marcaSb;
	private SuggestBox categoriaSb;
	private SuggestBox proveedorSb;

	private MultiWordSuggestOracle listaInsumos;
	private MultiWordSuggestOracle listaMarca;
	private MultiWordSuggestOracle listaCategoria;
	private MultiWordSuggestOracle listaProveedor;

	private Button buscar;
	private Button salir;
	
	private InsumoDTO insumoInfo;
	private boolean modificarInsumo = false; 
	private List<InsumoDTO> insumos;

	public P_BuscarInsumo() {

		super(false);
		
		setStyleName("fondoPopup");
		contenedor = new FlexTable();
		
		listaInsumos = new MultiWordSuggestOracle();
		listaMarca = new MultiWordSuggestOracle();
		listaCategoria = new MultiWordSuggestOracle();
		listaProveedor = new MultiWordSuggestOracle();
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

		comprasService.getNombresMarcas(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaMarcas(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});
		
		comprasService.getNombresCategorias(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaCategorias(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});
		
		comprasService.getNombresProveedores(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaProveedores(result);
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

		insumo = new RadioButton(constante.insumo());
		insumo.setText(constante.insumo());
		insumo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionInsumo();
			}
		});

		marca = new RadioButton(constante.marca());
		marca.setText(constante.marca());
		marca.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionMarca();
			}
		});
		
		categoria = new RadioButton(constante.categoria());
		categoria.setText(constante.categoria());
		categoria.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionCategoria();
			}
		});

		proveedor = new RadioButton(constante.proveedor());
		proveedor.setText(constante.proveedor());
		proveedor.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionProveedor();
			}
		});
		
		insumoSb = new SuggestBox(listaInsumos);
		insumoSb.getTextBox().setEnabled(false);
		marcaSb = new SuggestBox(listaMarca);
		marcaSb.getTextBox().setEnabled(false);
		categoriaSb = new SuggestBox(listaCategoria);
		categoriaSb.getTextBox().setEnabled(false);
		proveedorSb = new SuggestBox(listaProveedor);
		proveedorSb.getTextBox().setEnabled(false);
		
		
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
		
		botones1 = new FlexTable();
		botones1.setWidget(0, 0, buscar);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		botones2 = new FlexTable();
		botones2.setWidget(0, 0, salir);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("200px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		contenedor.setWidget(0, 0, encabezado);
		contenedor.getFlexCellFormatter().setColSpan(0, 0, 4);

		contenedor.setWidget(1, 0, insumo);
		contenedor.setWidget(1, 1, insumoSb);

		contenedor.setWidget(1, 2, marca);
		contenedor.setWidget(1, 3, marcaSb);

		contenedor.setWidget(2, 0, categoria);
		contenedor.setWidget(2, 1, categoriaSb);
		
		contenedor.setWidget(2, 2, proveedor);
		contenedor.setWidget(2, 3, proveedorSb);		
		
		contenedor.setWidget(3, 0, botones1);
		contenedor.getFlexCellFormatter().setColSpan(3, 0, 4);
		contenedor.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);

		contenedor.setWidget(4, 0, contenedorTabla);
		contenedor.getFlexCellFormatter().setColSpan(4, 0, 4);

		contenedor.setWidget(5, 0, pie);
		contenedor.getFlexCellFormatter().setColSpan(5, 0, 4);

		contenedor.setWidget(6, 0, botones2);
		contenedor.getFlexCellFormatter().setColSpan(6, 0, 4);
		contenedor.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		setWidget(contenedor);
		contenedor.setSize("600px", "300px");
		tablaElemento.setWidth("600px");
	
	}

	protected void cargarTabla() {
		
		// ARMA EL ENCABEZADO DE LA LISTA
		tablaElemento.setText(0, COL_INSUMO, constante.insumo());
		tablaElemento.getCellFormatter().setWidth(0, COL_INSUMO, "145px");
		tablaElemento.setText(0, COL_MARCA, constante.marca());
		tablaElemento.getCellFormatter().setWidth(0, COL_MARCA, "145px");
		tablaElemento.setText(0, COL_CATEGORIA, constante.categoria());
		tablaElemento.getCellFormatter().setWidth(0, COL_CATEGORIA, "145px");
		tablaElemento.setText(0, COL_INFO, constante.masInformacion());
		tablaElemento.getCellFormatter().setWidth(0, COL_INFO, "45px");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		this.tablaElemento.clear();
				
		if (insumo.getValue() == true) {
			
			
			String insumo = insumoSb.getText();

			if(insumo.compareTo("")==0){
				
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

				comprasService.getInsumosSegunParametro("", "", new AsyncCallback<List<InsumoDTO>>() {
					@Override
					public void onSuccess(List<InsumoDTO> result) {
						if (result.size() > 0) {
							insumos = result;
							cargarInsumos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar el insumo");
					}
				});
				
			}else{
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

				comprasService.getInsumosSegunParametro("insumo", insumo, new AsyncCallback<List<InsumoDTO>>() {
					@Override
					public void onSuccess(List<InsumoDTO> result) {
						if (result.size() > 0) {
							insumos = result;
							cargarInsumos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar el insumo");
					}
				});
			}
			

		
			
		}else if (marca.getValue() == true) {
			
			String marca = marcaSb.getText();
			
			if(marca.compareTo("")==0){
				
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

				comprasService.getInsumosSegunParametro("", "", new AsyncCallback<List<InsumoDTO>>() {
					@Override
					public void onSuccess(List<InsumoDTO> result) {
						if (result.size() > 0) {
							insumos = result;
							cargarInsumos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar el insumo");
					}
				});
				
			}else{
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

				comprasService.getInsumosSegunParametro("marca", marca, new AsyncCallback<List<InsumoDTO>>() {
					@Override
					public void onSuccess(List<InsumoDTO> result) {
						if (result.size() > 0) {
							insumos = result;
							cargarInsumos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar el insumo");
					}
				});
			}

			
			
		}else if (categoria.getValue() == true) {
			
			String categoria = categoriaSb.getText();
			
			if(categoria.compareTo("")==0){
				
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

				comprasService.getInsumosSegunParametro("", "", new AsyncCallback<List<InsumoDTO>>() {
					@Override
					public void onSuccess(List<InsumoDTO> result) {
						if (result.size() > 0) {
							insumos = result;
							cargarInsumos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar el insumo");
					}
				});
				
			}else{
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

				comprasService.getInsumosSegunParametro("categoria", categoria, new AsyncCallback<List<InsumoDTO>>() {
					@Override
					public void onSuccess(List<InsumoDTO> result) {
						if (result.size() > 0) {
							insumos = result;
							cargarInsumos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar el insumo");
					}
				});
			}
			

			
		}else if (proveedor.getValue() == true) {
			
			String proveedor = proveedorSb.getText();
			
			if(proveedor.compareTo("")==0){
				
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

				comprasService.getInsumosSegunParametro("", "", new AsyncCallback<List<InsumoDTO>>() {
					@Override
					public void onSuccess(List<InsumoDTO> result) {
						if (result.size() > 0) {
							insumos = result;
							cargarInsumos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar el insumo");
					}
				});
				
			}else{
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

				comprasService.getInsumosSegunParametro("proveedor", proveedor, new AsyncCallback<List<InsumoDTO>>() {
					@Override
					public void onSuccess(List<InsumoDTO> result) {
						if (result.size() > 0) {
							insumos = result;
							cargarInsumos();
						} else
							Window.alert("No se han encontrado resultados");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR al buscar el insumo");
					}
				});
			}
			

			
		}else if (insumo.getValue() == false && marca.getValue() == false && categoria.getValue() == false && proveedor.getValue() == false){
			
			ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

			comprasService.getInsumosSegunParametro("", "", new AsyncCallback<List<InsumoDTO>>() {
				@Override
				public void onSuccess(List<InsumoDTO> result) {
					if (result.size() > 0) {
						insumos = result;
						cargarInsumos();
					} else
						Window.alert("No se han encontrado resultados");

				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR al buscar el insumo");
				}
			});
			
			
		}
		
	}

	protected void cargarInsumos() {
		
		for (int i = 0; i < insumos.size(); i++) {

			Label info = new Label("");
			info.setSize("16px", "16px");
			info.setStyleName("labelInfo");
			
			tablaElemento.setWidget(i + 1, COL_INSUMO, new Label(insumos.get(i).getNombre()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_INSUMO, true);
			tablaElemento.setWidget(i + 1, COL_MARCA, new Label(insumos.get(i).getMarca()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_MARCA, true);
			tablaElemento.setWidget(i + 1, COL_CATEGORIA, new Label(insumos.get(i).getCategoria()));
			tablaElemento.getCellFormatter().setWordWrap(i+1, COL_CATEGORIA, false);
			tablaElemento.setWidget(i + 1, COL_INFO, info);
			tablaElemento.getFlexCellFormatter().setHorizontalAlignment(i + 1, COL_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElemento.getRowFormatter().setStyleName(i + 1, "tablaRenglon");
			info.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Cell celda = tablaElemento.getCellForEvent(event);
					
					String nombreInsumo = insumos.get(celda.getRowIndex() - 1).getNombre();
					int idInsumo = insumos.get(celda.getRowIndex()-1).getIdInsumo();
					
					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
					
					comprasService.getInsumoCompleto(idInsumo, nombreInsumo, new AsyncCallback<InsumoDTO>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR al buscar insumo");
						}

						@Override
						public void onSuccess(InsumoDTO result) {

							insumoInfo = result;
							
							final P_DatoInsumo popUp = new P_DatoInsumo(insumoInfo);
							popUp.setGlassEnabled(true);
							popUp.center();
							popUp.show();
							popUp.addCloseHandler(new CloseHandler<PopupPanel>() {
							boolean modificar = false;

								@Override
								public void onClose(CloseEvent<PopupPanel> event) {

									modificar = popUp.getModificarInsumo();

									if (modificar == true) {
										modificarInsumo();
										salir();
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

	protected void salir() {
		this.hide();

	}

	protected void seleccionProveedor() {
		if (proveedor.getValue() == true) {
			insumo.setValue(false);
			marca.setValue(false);
			categoria.setValue(false);
			proveedorSb.getTextBox().setEnabled(true);
			proveedorSb.setFocus(true);
			insumoSb.getTextBox().setValue("");
			insumoSb.getTextBox().setEnabled(false);
			marcaSb.getTextBox().setValue("");
			marcaSb.getTextBox().setEnabled(false);
			categoriaSb.getTextBox().setValue("");
			categoriaSb.getTextBox().setEnabled(false);

		} else {
			insumo.setValue(false);
			marca.setValue(false);
			categoria.setValue(false);
			proveedor.setValue(false);
			proveedorSb.getTextBox().setEnabled(true);
			insumoSb.getTextBox().setEnabled(true);
			marcaSb.getTextBox().setEnabled(true);
			categoriaSb.getTextBox().setEnabled(true);
		}
		
	}

	protected void seleccionCategoria() {
		if (categoria.getValue() == true) {
			proveedor.setValue(false);
			marca.setValue(false);
			insumo.setValue(false);
			categoriaSb.getTextBox().setEnabled(true);
			categoriaSb.setFocus(true);
			insumoSb.getTextBox().setValue("");
			insumoSb.getTextBox().setEnabled(false);
			proveedorSb.getTextBox().setValue("");
			proveedorSb.getTextBox().setEnabled(false);
			marcaSb.getTextBox().setValue("");
			marcaSb.getTextBox().setEnabled(false);
		} else {
			insumo.setValue(false);
			categoria.setValue(false);
			proveedor.setValue(false);
			proveedorSb.getTextBox().setEnabled(true);
			insumoSb.getTextBox().setEnabled(true);
			categoriaSb.getTextBox().setEnabled(true);
			marca.setValue(false);
			marcaSb.getTextBox().setEnabled(true);
		}
		
	}

	protected void seleccionMarca() {
		if (marca.getValue() == true) {
			proveedor.setValue(false);
			insumo.setValue(false);
			categoria.setValue(false);
			marcaSb.getTextBox().setEnabled(true);
			marcaSb.setFocus(true);
			proveedorSb.getTextBox().setValue("");
			proveedorSb.getTextBox().setEnabled(false);
			insumoSb.getTextBox().setValue("");
			insumoSb.getTextBox().setEnabled(false);
			categoriaSb.getTextBox().setValue("");
			categoriaSb.getTextBox().setEnabled(false);
		} else {
			marca.setValue(false);
			insumo.setValue(false);
			categoria.setValue(false);
			proveedor.setValue(false);
			proveedorSb.getTextBox().setEnabled(true);
			marcaSb.getTextBox().setEnabled(true);
			insumoSb.getTextBox().setEnabled(true);
			categoriaSb.getTextBox().setEnabled(true);
		}
		
	}

	protected void seleccionInsumo() {
		
		if (insumo.getValue() == true) {
					
			insumoSb.addKeyUpHandler(new KeyUpHandler() {
				
				@Override
				public void onKeyUp(KeyUpEvent tecla) {
				
					Character letra = new Character((char) tecla.getNativeKeyCode());
					if(insumoSb.getText().length() == 1 && Character.isLetterOrDigit(letra)){
						cargarSugerenciaInsumos(insumoSb.getText());
					}					
					
				}
			});
			
			
			proveedor.setValue(false);
			marca.setValue(false);
			categoria.setValue(false);
			insumoSb.getTextBox().setEnabled(true);
			insumoSb.setFocus(true);
			proveedorSb.getTextBox().setValue("");
			proveedorSb.getTextBox().setEnabled(false);
			marcaSb.getTextBox().setValue("");
			marcaSb.getTextBox().setEnabled(false);
			categoriaSb.getTextBox().setValue("");
			categoriaSb.getTextBox().setEnabled(false);
		} else {
			insumo.setValue(false);
			marca.setValue(false);
			categoria.setValue(false);
			proveedor.setValue(false);
			proveedorSb.getTextBox().setEnabled(true);
			insumoSb.getTextBox().setEnabled(true);
			marcaSb.getTextBox().setEnabled(true);
			categoriaSb.getTextBox().setEnabled(true);
		}
		
	}

	protected void cargarSugerenciaProveedores(List<String> result) {
		for (String sugerencia : result) {
			listaProveedor.add(sugerencia);
		}
		
	}

	protected void cargarSugerenciaCategorias(List<String> result) {
		for (String sugerencia : result) {
			listaCategoria.add(sugerencia);
		}
		
	}

	protected void cargarSugerenciaMarcas(List<String> result) {
		for (String sugerencia : result) {
			listaMarca.add(sugerencia);
		}
		
	}

	protected void cargarSugerenciaInsumos(String letra){
		
		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);

		comprasService.getNombresInsumos(letra,new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarListaSugerenciaInsumos(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se ha podido cargar la lista de sugerencias");
			}
		});
	
	}
		
	protected void cargarListaSugerenciaInsumos(List<String> result) {
		for (String sugerencia : result) {
			listaInsumos.add(sugerencia);
		}
		insumoSb.showSuggestionList();
	}

	public void modificarInsumo(){
		this.modificarInsumo = true;		
	}

	public InsumoDTO getInsumoDTO(){
		return this.insumoInfo;
	}
	
	public boolean getModificarInsumo(){
		return this.modificarInsumo;
	}

	
}
