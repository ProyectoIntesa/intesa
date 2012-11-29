package edu.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import edu.shared.DTO.InsumoDTO;

public class P_FormularioOrdenCompraInsumo extends Composite {

	private static final int COL_ITEM = 0;
	private static final int COL_INSUMO = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_LOTE = 3;
	private static final int COL_CANTINVENTARIO = 4;
	private static final int COL_CANTCOMPRAR = 5;
	private static final int COL_PRECIOUNITARIO = 6;
	private static final int COL_SUBTOTAL = 7;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	TabPanel padre;
	
	private Label tituloFormulario;
	private Label proveedor;
	private Label fechaEdicion;
	private Label modoEnvio;
	private Label formaDePago;
	private Label iva;
	private Label observaciones;
	private Label total;
	private Label pie;
	
	private ListBox modoEnvioLb;
	private TextBox formaDePagoTb;
	private TextBox ivaTb;
	private TextArea observacionesTa;
	private TextBox totalTb;
	
	private Button generar;
	private Button guardar;
	private Button cancelar;
		
	private FlexTable formulario;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;
	
	private String titulo;
	private List<InsumoDTO> insumos;
	private String prov;
	
	public P_FormularioOrdenCompraInsumo(TabPanel padre, List<InsumoDTO> insumos, String prov, String titulo) {
		
		this.padre = padre;
		this.insumos = insumos;
		this.prov = prov;
		this.titulo = prov;
		
		DateTimeFormat fmtDate=DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha=fmtDate.format(new Date());
		
		tituloFormulario = new Label("ORDEN DE COMPRA DE INSUMOS");
		tituloFormulario.setStyleName("labelTitulo");
		proveedor = new Label(constante.proveedor()+": "+prov);
		proveedor.setStyleName("gwt-LabelFormulario");
		fechaEdicion = new Label(constante.fechaEdicion()+": "+fecha);
		fechaEdicion.setStyleName("gwt-LabelFormulario");
		modoEnvio = new Label(constante.modoEnvio()+": ");
		modoEnvio.setStyleName("gwt-LabelFormulario");
		formaDePago = new Label(constante.formaDePago()+": ");
		formaDePago.setStyleName("gwt-LabelFormulario");
		iva = new Label(constante.iva()+": ");
		iva.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		total = new Label(constante.total());
		total.setStyleName("gwt-LabelFormulario");
		pie = new Label("");
		pie.setStyleName("labelTitulo");
		
		modoEnvioLb = new ListBox();
		modoEnvioLb.setStyleName("gwt-ListBox");
		formaDePagoTb = new TextBox();
		ivaTb = new TextBox();
		observacionesTa = new TextArea();
		observacionesTa.setDirectionEstimator(false);
		observacionesTa.setWidth("100%");
		totalTb = new TextBox();
		
		
		generar = new Button(constante.generar());
		generar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//armarOrden();
			}
		});
		
		guardar = new Button(constante.guardar());
		guardar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//armarOrden();
			}
		});
		
		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//armarOrden();
			}
		});
		
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("400px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		tablaElemento.setText(0, COL_ITEM, constante.item());
		tablaElemento.getCellFormatter().setWidth(0, COL_ITEM, "10%");
		tablaElemento.setText(0, COL_INSUMO, constante.insumo());
		tablaElemento.getCellFormatter().setWidth(0, COL_INSUMO, "10%");
		tablaElemento.setText(0, COL_MARCA, constante.marca());
		tablaElemento.getCellFormatter().setWidth(0, COL_MARCA, "10%");
		tablaElemento.setText(0, COL_LOTE, constante.loteCompra());
		tablaElemento.getCellFormatter().setWidth(0, COL_LOTE, "10%");
		tablaElemento.setText(0, COL_CANTINVENTARIO, constante.cantInventario());
		tablaElemento.getCellFormatter().setWidth(0, COL_CANTINVENTARIO, "10%");
		tablaElemento.setText(0, COL_CANTCOMPRAR, constante.cantComprar());
		tablaElemento.getCellFormatter().setWidth(0, COL_CANTCOMPRAR, "10%");
		tablaElemento.setText(0, COL_PRECIOUNITARIO, constante.precio());
		tablaElemento.getCellFormatter().setWidth(0, COL_PRECIOUNITARIO, "10%");
		tablaElemento.setText(0, COL_SUBTOTAL, constante.subtotal());
		tablaElemento.getCellFormatter().setWidth(0, COL_SUBTOTAL, "10%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		formulario = new FlexTable();
		formulario.setStyleName("formatoFormulario");
		formulario.setHeight("637px");
		formulario.setWidth("920px");
		
		formulario.setWidget(0, 0, tituloFormulario);
		formulario.getFlexCellFormatter().setColSpan(0, 0, 6);
		
		formulario.setWidget(1, 0, proveedor);
		formulario.setWidget(1, 5, fechaEdicion);
		
		formulario.setWidget(2, 0, modoEnvio);
		formulario.setWidget(2, 1, modoEnvioLb);
		formulario.getCellFormatter().setWidth(2, 1, "20%");
		formulario.setWidget(2, 2, formaDePago);
		formulario.setWidget(2, 3, formaDePagoTb);
		formulario.setWidget(2, 4, iva);
		formulario.setWidget(2, 5, ivaTb);
		
		formulario.setWidget(3, 0, contenedorTabla);
		formulario.getFlexCellFormatter().setColSpan(3, 0, 6);
		
		formulario.setWidget(4, 4, total);
		formulario.setWidget(4, 5, totalTb);
		
		formulario.setWidget(5, 0, observaciones);
		formulario.getFlexCellFormatter().setColSpan(5, 0, 6);
		formulario.setWidget(6, 0, observacionesTa);
		formulario.getFlexCellFormatter().setColSpan(6, 0, 6);
		
		formulario.setWidget(7, 0, pie);
		formulario.getFlexCellFormatter().setColSpan(7, 0, 6);
		
		formulario.setWidget(8, 0, guardar);
		formulario.setWidget(8, 2, generar);
		formulario.setWidget(8, 4, cancelar);

		
		
		
		
		initWidget(formulario);
		
		
	}
	
	public void cancelar(ClickEvent event) {
		padre.remove(numeroElemento(constante.ordenDeCompraDeInsumos()));

	}
	
	private int numeroElemento(String titulo) {

		int elemento = -1;
		int contador = 0;

		while (elemento == -1 && contador < padre.getWidgetCount()) {

			if (padre.getWidget(contador).getTitle().compareTo(titulo) == 0)
				elemento = contador;
			else
				contador++;
		}

		return elemento;
	}
	
	
}


