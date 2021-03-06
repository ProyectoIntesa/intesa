package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;

import edu.client.ComprasService.ComprasService;
import edu.client.ComprasService.ComprasServiceAsync;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.RenglonOrdenCompraInsumoDTO;

public class P_DetalleOrdenCompraInsumo extends PopupPanel {

	private static final int COL_ITEM = 0;
	private static final int COL_INSUMO = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_CANT = 3;
	private static final int COL_PRECIOUNITARIO = 4;
	private static final int COL_SUBTOTAL = 5;

	private Constantes constante = GWT.create(Constantes.class);

	TabPanel padre;

	private Label tituloFormulario;
	private Label lineaTabla;
	private Label observaciones;
	private Label pie;

	private Label nroOrden;
	private Label proveedor;
	private Label empleado;
	private Label fechaGeneracion;
	private Label modoEnvio;
	private Label formaDePago;
	private Label iva;
	private Label estado;
	private Label total;
	private Label totalI;
	private Label observacion;

	private Button cancelarOrden;
	private Button salir1;
	private Button salir2;
	private Button salir3;
	private Button salir4;
	private Button salir5;
	private Button salir6;
	private Button enviarOrden;
	private Button cerrarOrden1;
	private Button cerrarOrden2;
	private Button cerrarOrden3;

	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	private FlexTable botonesSalir;
	private FlexTable botonesGenerada;
	private FlexTable botonesValidada;
	private FlexTable botonesEnviada;
	private FlexTable botonesRecibidaParcial;
	private FlexTable botonesRecibidaCompleta;
	private FlexTable lineaTotal;
	private FlexTable lineaTotalI;
	

	private OrdenCompraInsumoDTO orden;

	
	private boolean cierreOrdenCerrar;
	private boolean cierreOrdenSalir;
	private boolean cierreOrdenCancelar;
	private boolean cierreOrdenImprimir;
	
	
	
	
	public P_DetalleOrdenCompraInsumo(OrdenCompraInsumoDTO orden, String usuario) {

		super(false);
		this.orden = orden;
		setStyleName("fondoPopup");
		final long idOrden = orden.getIdOrden();

		this.cierreOrdenCerrar = false;
		this.cierreOrdenSalir = false;
		
		
		tituloFormulario = new Label(constante.ordenCompraDeInsumo());
		tituloFormulario.setStyleName("labelTitulo");
		lineaTabla = new Label();
		lineaTabla.setStyleName("labelTitulo");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");

		nroOrden = new Label(constante.nroOrden() + ": " + orden.getNroOrden());
		nroOrden.setStyleName("gwt-LabelFormulario");
		proveedor = new Label(constante.proveedor() + ": " + orden.getProveedor());
		proveedor.setStyleName("gwt-LabelFormulario");
		empleado = new Label(constante.generadaPor() + ": " + orden.getEmpleado());
		empleado.setStyleName("gwt-LabelFormulario");
		modoEnvio = new Label(constante.modoEnvio() + ": " + orden.getModoEnvio());
		modoEnvio.setStyleName("gwt-LabelFormulario");
		formaDePago = new Label(constante.formaDePago() + ": " + orden.getFormaPago());
		formaDePago.setStyleName("gwt-LabelFormulario");
		iva = new Label(constante.iva() + ": " + orden.getIva() + "%");
		iva.setStyleName("gwt-LabelFormulario");
		estado = new Label(constante.estado() + ": " + orden.getEstadoOrden());
		estado.setStyleName("gwt-LabelFormulario");
		total = new Label(constante.total() + ": " + orden.getTotal());
		total.setStyleName("gwt-LabelFormulario");
		totalI = new Label(constante.total() + ": " + orden.getTotal());
		totalI.setStyleName("gwt-LabelFormulario");
		
		
		observacion = new Label(orden.getObservaciones());
		observacion.setStyleName("gwt-LabelFormulario");

		cancelarOrden = new Button(constante.cancelarOrden());
		cancelarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				boolean confirm = Window.confirm("Está seguro de que desea \"cancelar\" la orden de compra de insumos?");
				if(confirm == true){
					ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
					comprasService.cancelarOrdencompraInsumo(idOrden, "CANCELADA", new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							if (result) {
								Window.alert("La orden ha sido \"cancelada\"");
								cierreOrdenCancelar = true;
								salir();
							} else {
								Window.alert("No se ha podido cambiar el estado de la orden");
							}
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");

						}
					});
				}
			}
		});

		cerrarOrden1 = new Button(constante.cerrarOrden());
		cerrarOrden1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.ordenDeComprasCompleta(idOrden, new AsyncCallback<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							verDetalle("completa");
						} else {
							
							boolean confirm = Window.confirm("La recepción de la orden de compra de insumo NO ha sido completada. Si acepta, la orden quedará cerrada en forma parcial");
							if(confirm == true){
								verDetalle("parcial");
							}	
 						}
					}
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");

					}
				});
			}
		});

		cerrarOrden2 = new Button(constante.cerrarOrden());
		cerrarOrden2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.ordenDeComprasCompleta(idOrden, new AsyncCallback<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							verDetalle("completa");
						} else {
							
							boolean confirm = Window.confirm("La recepción de la orden de compra de insumo NO ha sido completada. Si acepta, la orden quedará cerrada en forma parcial");
							if(confirm == true){
								verDetalle("parcial");
							}	
 						}
					}
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");

					}
				});
			}
		});
		
		cerrarOrden3 = new Button(constante.cerrarOrden());
		cerrarOrden3.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.ordenDeComprasCompleta(idOrden, new AsyncCallback<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							verDetalle("completa");
						} else {
							
							boolean confirm = Window.confirm("La recepción de la orden de compra de insumo NO ha sido completada. Si acepta, la orden quedará cerrada en forma parcial");
							if(confirm == true){
								verDetalle("parcial");
							}	
 						}
					}
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");

					}
				});
			}
		});

		enviarOrden = new Button(constante.enviarOrden());
		enviarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.cancelarOrdencompraInsumo(idOrden, "ENVIADA", new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							cierreOrdenImprimir = true;
							salir();
						} else {
							Window.alert("No se ha podido cambiar el estado de la orden");
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN EL SERVICIO");

					}

				});
			}
		});

		salir1 = new Button(constante.salir());
		salir1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cierreOrdenSalir = true;
				salir();
			}
		});
		
		salir2 = new Button(constante.salir());
		salir2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cierreOrdenSalir = true;
				salir();
			}
		});
		
		salir3 = new Button(constante.salir());
		salir3.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cierreOrdenSalir = true;
				salir();
			}
		});
		
		salir4 = new Button(constante.salir());
		salir4.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cierreOrdenSalir = true;
				salir();
			}
		});
		
		salir5 = new Button(constante.salir());
		salir5.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cierreOrdenSalir = true;
				salir();
			}
		});
		
		salir6 = new Button(constante.salir());
		salir6.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cierreOrdenSalir = true;
				salir();
			}
		});

		botonesGenerada = new FlexTable();
		botonesGenerada.setWidget(0, 0, cancelarOrden);
		botonesGenerada.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botonesGenerada.setWidget(0, 1, salir1);
		botonesGenerada.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		botonesValidada = new FlexTable();
		botonesValidada.setWidget(0, 0, enviarOrden);
		botonesValidada.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botonesValidada.setWidget(0, 1, salir2);
		botonesValidada.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		botonesEnviada = new FlexTable();
		botonesEnviada.setWidget(0, 0, cerrarOrden1);
		botonesEnviada.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botonesEnviada.setWidget(0, 1, salir3);
		botonesEnviada.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		botonesRecibidaParcial = new FlexTable();
		botonesRecibidaParcial.setWidget(0, 0, cerrarOrden2);
		botonesRecibidaParcial.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botonesRecibidaParcial.setWidget(0, 1, salir4);
		botonesRecibidaParcial.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		botonesRecibidaCompleta = new FlexTable();
		botonesRecibidaCompleta.setWidget(0, 0, cerrarOrden3);
		botonesRecibidaCompleta.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botonesRecibidaCompleta.setWidget(0, 1, salir5);
		botonesRecibidaCompleta.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		botonesSalir = new FlexTable();
		botonesSalir.setWidget(0, 0, salir6);
		botonesSalir.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		lineaTotal = new FlexTable();
		lineaTotal.setWidget(0, 0, total);
		lineaTotal.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		lineaTotalI = new FlexTable();
		lineaTotalI.setWidget(0, 0, totalI);
		lineaTotalI.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(orden.getFechaGeneracion());

		fechaGeneracion = new Label(constante.fechaGeneracion() + ": " + fecha);
		fechaGeneracion.setStyleName("gwt-LabelFormulario");

		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_ITEM, constante.item());
		tablaElementos.getCellFormatter().setWidth(0, COL_ITEM, "16%");
		tablaElementos.setText(0, COL_INSUMO, constante.insumo());
		tablaElementos.getCellFormatter().setWidth(0, COL_INSUMO, "16%");
		tablaElementos.setText(0, COL_MARCA, constante.marca());
		tablaElementos.getCellFormatter().setWidth(0, COL_MARCA, "16%");
		tablaElementos.setText(0, COL_CANT, constante.cantidad());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANT, "16%");
		tablaElementos.setText(0, COL_PRECIOUNITARIO, constante.precio());
		tablaElementos.getCellFormatter().setWidth(0, COL_PRECIOUNITARIO, "16%");
		tablaElementos.setText(0, COL_SUBTOTAL, constante.subtotal());
		tablaElementos.getCellFormatter().setWidth(0, COL_SUBTOTAL, "16%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");

		panel.setWidget(0, 0, tituloFormulario);
		panel.getFlexCellFormatter().setColSpan(0, 0, 4);

		panel.setWidget(1, 0, nroOrden);
		panel.setWidget(1, 1, fechaGeneracion);
		panel.setWidget(1, 2, estado);
		panel.setWidget(1, 3, empleado);

		panel.setWidget(2, 0, proveedor);
		panel.setWidget(2, 1, modoEnvio);
		panel.setWidget(2, 2, formaDePago);
		panel.setWidget(2, 3, iva);

		panel.setWidget(3, 0, lineaTabla);
		panel.getFlexCellFormatter().setColSpan(3, 0, 4);

		panel.setWidget(4, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(4, 0, 4);

		panel.setWidget(5, 0, lineaTotal);
		panel.getFlexCellFormatter().setColSpan(5, 0, 4);
		panel.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		panel.setWidget(6, 0, observaciones);
		panel.getFlexCellFormatter().setColSpan(6, 0, 4);

		panel.setWidget(7, 0, observacion);
		panel.getFlexCellFormatter().setColSpan(7, 0, 4);

		panel.setWidget(8, 0, pie);
		panel.getFlexCellFormatter().setColSpan(8, 0, 4);
			
		if(usuario.compareTo("COMPRAS") == 0){
		
			if (orden.getEstadoOrden().compareTo("GENERADA") == 0) {
				panel.setWidget(9, 0, botonesGenerada);
				panel.getFlexCellFormatter().setColSpan(9, 0, 4);
				panel.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			} else if (orden.getEstadoOrden().compareTo("VALIDADA") == 0) {
				panel.setWidget(9, 0, botonesValidada);
				panel.getFlexCellFormatter().setColSpan(9, 0, 4);
				panel.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			} else if (orden.getEstadoOrden().compareTo("ENVIADA") == 0) {
				panel.setWidget(9, 0, botonesEnviada);
				panel.getFlexCellFormatter().setColSpan(9, 0, 4);
				panel.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			} else if (orden.getEstadoOrden().compareTo("RECIBIDA PARCIAL") == 0) {
				panel.setWidget(9, 0, botonesRecibidaParcial);
				panel.getFlexCellFormatter().setColSpan(9, 0, 4);
				panel.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			} else if (orden.getEstadoOrden().compareTo("RECIBIDA COMPLETA") == 0) {
				panel.setWidget(9, 0, botonesRecibidaCompleta);
				panel.getFlexCellFormatter().setColSpan(9, 0, 4);
				panel.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			} else {
				panel.setWidget(9, 0, botonesSalir);
				panel.getFlexCellFormatter().setColSpan(9, 0, 4);
				panel.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);				
			}
			
		}
		else {
			panel.setWidget(9, 0, botonesSalir);
			panel.getFlexCellFormatter().setColSpan(9, 0, 4);
			panel.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);		
		}
				
		
		int item = 1;
		
		for (RenglonOrdenCompraInsumoDTO renglon : orden.getRenglonOrdenCompraInsumosOrdenado()) {

			tablaElementos.setWidget(item, COL_ITEM, new Label("" + renglon.getItem()));
			tablaElementos.setWidget(item, COL_INSUMO, new Label(renglon.getInsumo().getNombre()));
			tablaElementos.setWidget(item, COL_MARCA, new Label(renglon.getInsumo().getMarca()));
			tablaElementos.setWidget(item, COL_CANT, new Label(renglon.getCantidad() + ""));
			tablaElementos.setWidget(item, COL_PRECIOUNITARIO, new Label(renglon.getPrecio() + ""));
			tablaElementos.setWidget(item, COL_SUBTOTAL, new Label(renglon.getSubtotal() + ""));
			tablaElementos.getRowFormatter().setStyleName(item, "tablaRenglon");
			item++;

		}

		setWidget(panel);

	}

	public FlexTable armarImpresion() {
		
		FlexTable formulario = new FlexTable();
		
		formulario.setWidget(1, 0, tituloFormulario);
		formulario.getFlexCellFormatter().setColSpan(1, 0, 4);

		formulario.setWidget(2, 0, nroOrden);
		formulario.setWidget(2, 1, fechaGeneracion);
		formulario.setWidget(2, 2, estado);
		formulario.setWidget(2, 3, empleado);

		formulario.setWidget(3, 0, proveedor);
		formulario.setWidget(3, 1, modoEnvio);
		formulario.setWidget(3, 2, formaDePago);
		formulario.setWidget(3, 3, iva);

		formulario.setWidget(4, 0, lineaTabla);
		formulario.getFlexCellFormatter().setColSpan(4, 0, 4);

		formulario.setWidget(6, 0, lineaTotalI);
		formulario.getFlexCellFormatter().setColSpan(6, 0, 4);
		formulario.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		formulario.setWidget(7, 0, observaciones);
		formulario.getFlexCellFormatter().setColSpan(7, 0, 4);

		formulario.setWidget(8, 0, observacion);
		formulario.getFlexCellFormatter().setColSpan(8, 0, 4);

		formulario.setWidget(9, 0, pie);
		formulario.getFlexCellFormatter().setColSpan(9, 0, 4);
		int cantidad = orden.getRenglonOrdenCompraInsumos().size();
		
		ScrollPanel tabla = new ScrollPanel();
		tabla.setStyleName("tabla");
		if (cantidad > 5)
			tabla.setHeight(cantidad * 22 + "px");
		else
			tabla.setHeight("400 px");
		FlexTable elementos = new FlexTable();
		tabla.setWidget(elementos);
		elementos.setSize("100%", "100%");
		elementos.setText(0, COL_ITEM, constante.item());
		elementos.getCellFormatter().setWidth(0, COL_ITEM, "16%");
		elementos.setText(0, COL_INSUMO, constante.insumo());
		elementos.getCellFormatter().setWidth(0, COL_INSUMO, "16%");
		elementos.setText(0, COL_MARCA, constante.marca());
		elementos.getCellFormatter().setWidth(0, COL_MARCA, "16%");
		elementos.setText(0, COL_CANT, constante.cantidad());
		elementos.getCellFormatter().setWidth(0, COL_CANT, "16%");
		elementos.setText(0, COL_PRECIOUNITARIO, constante.precio());
		elementos.getCellFormatter().setWidth(0, COL_PRECIOUNITARIO, "16%");
		elementos.setText(0, COL_SUBTOTAL, constante.subtotal());
		elementos.getCellFormatter().setWidth(0, COL_SUBTOTAL, "16%");
		elementos.getRowFormatter().addStyleName(0, "tablaEncabezado");

		int item = 1;
		for (RenglonOrdenCompraInsumoDTO renglon : orden.getRenglonOrdenCompraInsumosOrdenado()) {

			elementos.setWidget(item, COL_ITEM, new Label("" + renglon.getItem()));
			elementos.setWidget(item, COL_INSUMO, new Label(renglon.getInsumo().getNombre()));
			elementos.setWidget(item, COL_MARCA, new Label(renglon.getInsumo().getMarca()));
			elementos.setWidget(item, COL_CANT, new Label(renglon.getCantidad() + ""));
			
			if (renglon.getPrecio() == null)
				elementos.setWidget(item, COL_PRECIOUNITARIO, new Label(""));
			else
				elementos.setWidget(item, COL_PRECIOUNITARIO, new Label(renglon.getPrecio() + ""));
			
			if (renglon.getSubtotal() == null) {
				elementos.setWidget(item, COL_SUBTOTAL, new Label(""));
				total.setText(constante.total() + ": ");
			} else
				elementos.setWidget(item, COL_SUBTOTAL, new Label(renglon.getSubtotal() + ""));
			
			elementos.getRowFormatter().setStyleName(item, "tablaRenglon2");
			item++;

		}

		formulario.setWidget(5, 0, tabla);
		formulario.getFlexCellFormatter().setColSpan(5, 0, 4);
		return formulario;
	}

	protected void salir() {
		this.hide();

	}

	public boolean getCierreOrdenCerrar() {
		return this.cierreOrdenCerrar;
	}

	public boolean getCierreOrdenImprimir() {
		return this.cierreOrdenImprimir;
	}
	
	public boolean getCierreOrdenSalir() {
		return this.cierreOrdenSalir;
	}
	
	public boolean getCierreOrdenCancelar() {
		return this.cierreOrdenCancelar;
	}

	private void verDetalle(String tipo){
		
		final P_DetalleOrdenCompraInsumoCierre detalle = new P_DetalleOrdenCompraInsumoCierre(orden, tipo);		
		detalle.setGlassEnabled(true);
		detalle.center();
		detalle.show();	
		detalle.addCloseHandler(new CloseHandler<PopupPanel>() {

			@Override
			public void onClose(CloseEvent<PopupPanel> event) {				
			
				if (detalle.getCierreOrdenCerrar() == true)
				{	
					Window.alert("La orden ha sido \"cerrada\"");
					cierreOrdenCerrar = true;
					salir();
				}
				if(detalle.getCierreOrdenSalir() == true)
				{	
					cierreOrdenSalir = true;
					salir();
				}
			}
		});
	}
		
}
