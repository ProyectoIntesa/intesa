package edu.client;

import java.util.Date;
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
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.RenglonOrdenCompraInsumoDTO;

public class P_DetalleOrdenCompraInsumoCierre extends PopupPanel {

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
	private Label totals;
	private Label observacion;

	private Button salir;
	private Button cerrarOrden;
	private Button actualizar;

	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	private boolean cambioEstado;
	private OrdenCompraInsumoDTO orden;
	private boolean accionSalir;

	public P_DetalleOrdenCompraInsumoCierre(OrdenCompraInsumoDTO orden, final String tipo) {

		super(false);
		this.orden = orden;
		setStyleName("fondoPopup");
		final long idOrden = orden.getIdOrden();
		accionSalir = false;
		cambioEstado = false;
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

		cerrarOrden = new Button(constante.cerrarOrden());
		cerrarOrden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				actualizaValores();
				cambioEstado = true;
				if (tipo.compareTo("completa") == 0){
					cambiarEstado("CERRADA");
					salir();
				}
				else if (tipo.compareTo("parcial") == 0){
					cambiarEstado("CERRADA PARCIAL");
					salir();
				}
			}
		});
		cerrarOrden.setEnabled(false);
		actualizar = new Button(constante.actualizarTotal());
		actualizar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				actualizaValores();
				cerrarOrden.setEnabled(true);
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
		panel.setWidget(5, 1, actualizar);
		panel.setWidget(5, 3, total);

		panel.setWidget(6, 0, observaciones);
		panel.getFlexCellFormatter().setColSpan(6, 0, 4);

		panel.setWidget(7, 0, observacion);
		panel.getFlexCellFormatter().setColSpan(7, 0, 4);

		panel.setWidget(8, 0, pie);
		panel.getFlexCellFormatter().setColSpan(8, 0, 4);
		panel.setWidget(9, 1, cerrarOrden);
		panel.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_CENTER);
		panel.setWidget(9, 3, salir);
		panel.getCellFormatter().setHorizontalAlignment(9, 3, HasHorizontalAlignment.ALIGN_CENTER);

		int item = 1;
		for (RenglonOrdenCompraInsumoDTO renglon : orden.getRenglonOrdenCompraInsumos()) {
			TextBox precioUnitario = new TextBox();
			precioUnitario.setText(renglon.getPrecio() + "");
			tablaElementos.setWidget(item, COL_ITEM, new Label("" + renglon.getItem()));
			tablaElementos.setWidget(item, COL_INSUMO, new Label(renglon.getInsumo().getNombre()));
			tablaElementos.setWidget(item, COL_MARCA, new Label(renglon.getInsumo().getMarca()));
			tablaElementos.setWidget(item, COL_CANT, new Label(renglon.getCantidad() + ""));
			tablaElementos.setWidget(item, COL_PRECIOUNITARIO, precioUnitario);
			tablaElementos.setWidget(item, COL_SUBTOTAL, new Label(renglon.getSubtotal() + ""));
			tablaElementos.getRowFormatter().setStyleName(item, "tablaRenglon");
			item++;

		}

		setWidget(panel);

	}

	protected void salir() {
		this.hide();

	}

	public boolean getAccionSalir() {
		return this.accionSalir;
	}

	public void actualizaValores() {
		Validaciones valida = new Validaciones();
		double tt = 0.0;
		for (int i = 1; i < tablaElementos.getRowCount(); i++) {
			double cantidad = 0.0;
			cantidad = new Double(((Label) tablaElementos.getWidget(i, COL_CANT)).getText());
			double precio = new Double(((TextBox) tablaElementos.getWidget(i, COL_PRECIOUNITARIO)).getText());
			double stt = precio * cantidad;
			tt = tt + stt;
			((Label) tablaElementos.getWidget(i, COL_SUBTOTAL)).setText(stt + "");
		}
		total.setText(tt + "");
		totals = new Label(constante.total() + ": ");
		totals.setStyleName("gwt-LabelFormularioDerecho");
		panel.setWidget(5, 2, totals);
		panel.setWidget(5, 3, total);
	}

	public void cambiarEstado(String estado) {
		this.orden.setEstadoOrden(estado);
		orden.setTotal(new Double(this.total.getText()));
		orden.getRenglonOrdenCompraInsumos().clear();
		orden.setFechaCierre(new Date());
		for (int i = 1; i < tablaElementos.getRowCount(); i++) {
			RenglonOrdenCompraInsumoDTO renglon = new RenglonOrdenCompraInsumoDTO();
			InsumoDTO insu = new InsumoDTO();
			insu.setMarca(((Label) tablaElementos.getWidget(i, COL_MARCA)).getText());
			insu.setNombre(((Label) tablaElementos.getWidget(i, COL_INSUMO)).getText());
			renglon.setCantidad(new Double(((Label) tablaElementos.getWidget(i, COL_CANT)).getText()));
			renglon.setItem(i);
			renglon.setPrecio(new Double(((TextBox) tablaElementos.getWidget(i, COL_PRECIOUNITARIO)).getText()));
			renglon.setSubtotal(new Double(((Label) tablaElementos.getWidget(i, COL_SUBTOTAL)).getText()));
			renglon.setInsumo(insu);
			orden.getRenglonOrdenCompraInsumos().add(renglon);
		}

		ComprasServiceAsync comprasService = GWT.create(ComprasService.class);
		comprasService.actualizarOrdenCompraInsumos(orden, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					Window.alert("La orden ha sido CERRADA");
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

	public boolean cambioEstado() {
		return this.cambioEstado;
	}

}
