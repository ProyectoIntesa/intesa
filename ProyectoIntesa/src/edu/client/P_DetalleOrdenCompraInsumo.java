package edu.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.google.gwt.user.client.ui.TextBox;

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
	private Label observacion;

	private Button cancelarOrden;
	private Button salir;
	private Button enviarOrden;
	private Button cerrarOrden;

	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;

	private OrdenCompraInsumoDTO orden;
	private boolean accionSalir;
	private boolean imprimir;
	public P_DetalleOrdenCompraInsumo(OrdenCompraInsumoDTO orden) {

		super(false);
		this.orden = orden;
		setStyleName("fondoPopup");
		final long idOrden = orden.getIdOrden();
		accionSalir = false;

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
		observacion = new Label(orden.getObservaciones());
		observacion.setStyleName("gwt-LabelFormulario");

		cancelarOrden = new Button(constante.cancelarOrden());
		cancelarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
				comprasService.cancelarOrdencompraInsumo(idOrden, "CANCELADA", new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							Window.alert("La orden ha sido CANCELADA");
							salir();
						} else {
							Window.alert("No se ha podido cambiar el estado de la orden");
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR DE SERVICIO");

					}
				});
			}
		});

		cerrarOrden = new Button(constante.cerrarOrden());
		cerrarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// registrarOrden("GENERADA");
			}
		});

		enviarOrden = new Button(constante.enviarOrden());
		enviarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				imprimir = true;
				accionSalir = true;
				salir();
			}
		});

		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				accionSalir = true;
				salir();
			}
		});

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

		panel.setWidget(5, 3, total);

		panel.setWidget(6, 0, observaciones);
		panel.getFlexCellFormatter().setColSpan(6, 0, 4);

		panel.setWidget(7, 0, observacion);
		panel.getFlexCellFormatter().setColSpan(7, 0, 4);

		panel.setWidget(8, 0, pie);
		panel.getFlexCellFormatter().setColSpan(8, 0, 4);

		if (orden.getEstadoOrden().compareTo("GENERADA") == 0) {
			panel.setWidget(9, 1, cancelarOrden);
			panel.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_CENTER);
		} else if (orden.getEstadoOrden().compareTo("VALIDADA") == 0) {
			panel.setWidget(9, 1, enviarOrden);
			panel.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_CENTER);
		} else if (orden.getEstadoOrden().compareTo("ENVIADA") == 0) {
			panel.setWidget(9, 1, cerrarOrden);
			panel.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_CENTER);
		}

		panel.setWidget(9, 3, salir);
		panel.getCellFormatter().setHorizontalAlignment(9, 3, HasHorizontalAlignment.ALIGN_CENTER);

		int item = 1;
		for (RenglonOrdenCompraInsumoDTO renglon : orden.getRenglonOrdenCompraInsumos()) {

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

		formulario.setWidget(6, 3, total);

		formulario.setWidget(7, 0, observaciones);
		formulario.getFlexCellFormatter().setColSpan(7, 0, 4);

		formulario.setWidget(8, 0, observacion);
		formulario.getFlexCellFormatter().setColSpan(8, 0, 4);

		formulario.setWidget(9, 0, pie);
		formulario.getFlexCellFormatter().setColSpan(9, 0, 4);
		int cantidad = orden.getRenglonOrdenCompraInsumos().size();
		// formulario.setSize("1000px", "1300px");
		ScrollPanel tabla = new ScrollPanel();
		tabla.setStyleName("tabla");

		tabla.setHeight(cantidad * 22 + "px");
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
		for (RenglonOrdenCompraInsumoDTO renglon : orden.getRenglonOrdenCompraInsumos()) {

			elementos.setWidget(item, COL_ITEM, new Label("" + renglon.getItem()));
			elementos.setWidget(item, COL_INSUMO, new Label(renglon.getInsumo().getNombre()));
			elementos.setWidget(item, COL_MARCA, new Label(renglon.getInsumo().getMarca()));
			elementos.setWidget(item, COL_CANT, new Label(renglon.getCantidad() + ""));
			if (renglon.getPrecio() == 0)
				elementos.setWidget(item, COL_PRECIOUNITARIO, new Label(""));
			else
				elementos.setWidget(item, COL_PRECIOUNITARIO, new Label(renglon.getPrecio() + ""));
			if (renglon.getSubtotal() == 0) {
				elementos.setWidget(item, COL_SUBTOTAL, new Label(""));
				total.setText(constante.total() + ": ");
			} else
				elementos.setWidget(item, COL_SUBTOTAL, new Label(renglon.getSubtotal() + ""));
			elementos.getRowFormatter().setStyleName(item, "tablaRenglonImpresion");
			item++;

		}

		formulario.setWidget(5, 0, tabla);
		formulario.getFlexCellFormatter().setColSpan(5, 0, 4);
		return formulario;
	}

	protected void salir() {
		this.hide();

	}

	public boolean getAccionSalir() {
		return this.accionSalir;
	}

	public boolean imprimir() {
		return this.imprimir;
	}

}
