package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RenglonOrdenProvisionInsumoDTO;

import com.google.gwt.i18n.client.DateTimeFormat;

public class P_DetalleOrdenProvisionInsumo  extends PopupPanel {
	
	private static final int COL_ITEM = 0;
	private static final int COL_INSUMO = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_CANT = 3;
	
	private Constantes constante = GWT.create(Constantes.class);

	TabPanel padre;
	
	private Label tituloFormulario;
	private Label lineaTabla;
	private Label observaciones;
	private Label pie;
	
	private Label nroOrden;
	private Label empleadoPor;
	private Label empleadoPara;
	private Label fechaGeneracion;
	private Label fechaCierre;
	private Label estado;
	private Label observacion;

	private Button salir;

	
	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	
	private OrdenProvisionInsumoDTO orden;
	private boolean accionSalir;
	
	public P_DetalleOrdenProvisionInsumo(OrdenProvisionInsumoDTO orden){
		
		super(false);	
		this.orden = orden;
		setStyleName("fondoPopup");
		accionSalir = false;
		
		tituloFormulario = new Label(constante.ordenDeProvisionDeInsumos());
		tituloFormulario.setStyleName("labelTitulo");
		lineaTabla = new Label();
		lineaTabla.setStyleName("labelTitulo");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		nroOrden = new Label(constante.nroOrden()+": "+orden.getIdOrdenProvisionInsumo());
		nroOrden.setStyleName("gwt-LabelFormulario");
		empleadoPor = new Label(constante.generadoPor()+": "+orden.getEmpleadoByIdPedidoPor().getApellido()+", "+orden.getEmpleadoByIdPedidoPor().getNombre());
		empleadoPor.setStyleName("gwt-LabelFormulario");
		empleadoPara = new Label(constante.generadoPara()+": "+orden.getEmpleadoByIdPedidoPara().getApellido()+", "+orden.getEmpleadoByIdPedidoPara().getNombre());
		empleadoPara.setStyleName("gwt-LabelFormulario");
		
		DateTimeFormat fmtDate1 = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fechaG = fmtDate1.format(orden.getFechaGeneracion());
		
		fechaGeneracion = new Label(constante.fechaGeneracion()+": "+fechaG);
		fechaGeneracion.setStyleName("gwt-LabelFormulario");
		
		String fechaC = "";
		if(orden.getFechaCierre()!=null){

			DateTimeFormat fmtDate2 = DateTimeFormat.getFormat("dd/MM/yyyy");
			fechaC = fmtDate2.format(orden.getFechaCierre());			
		}

		fechaCierre = new Label(constante.fechaCierre()+": "+fechaC);
		fechaCierre.setStyleName("gwt-LabelFormulario");
		

		estado = new Label(constante.estado()+": "+orden.getEstadoOrden());
		estado.setStyleName("gwt-LabelFormulario");

		observacion = new Label(orden.getObservaciones());
		observacion.setStyleName("gwt-LabelFormulario");
		
				
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				accionSalir = true;
				salir();
			}
		});
		
		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_ITEM, constante.item());
		tablaElementos.getCellFormatter().setWidth(0, COL_ITEM, "10%");
		tablaElementos.setText(0, COL_INSUMO, constante.insumo());
		tablaElementos.getCellFormatter().setWidth(0, COL_INSUMO, "30%");
		tablaElementos.setText(0, COL_MARCA, constante.marca());
		tablaElementos.getCellFormatter().setWidth(0, COL_MARCA, "30%");
		tablaElementos.setText(0, COL_CANT, constante.cantidad());
		tablaElementos.getCellFormatter().setWidth(0, COL_CANT, "30%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		panel.setWidget(0, 0, tituloFormulario);
		panel.getFlexCellFormatter().setColSpan(0, 0, 4);

		panel.setWidget(1, 0, nroOrden);
		panel.setWidget(1, 1, fechaGeneracion);
		panel.setWidget(1, 2, fechaCierre);
		panel.setWidget(1, 3, estado);
		
		panel.setWidget(2, 0, empleadoPor);
		panel.setWidget(2, 2, empleadoPara);

		panel.setWidget(3, 0, lineaTabla);
		panel.getFlexCellFormatter().setColSpan(3, 0, 4);
		
		panel.setWidget(4, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(4, 0, 4);
				
		panel.setWidget(5, 0, observaciones);
		panel.getFlexCellFormatter().setColSpan(5, 0, 4);
		
		panel.setWidget(6, 0, observacion);
		panel.getFlexCellFormatter().setColSpan(6, 0, 4);
		
		panel.setWidget(7, 0, pie);
		panel.getFlexCellFormatter().setColSpan(7, 0, 4);
			
		panel.setWidget(8, 1, salir);
		panel.getCellFormatter().setHorizontalAlignment(8, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		int item = 1;
		for (RenglonOrdenProvisionInsumoDTO renglon : orden.getRenglonOrdenProvisionInsumos()) {
			
			tablaElementos.setWidget(item, COL_ITEM, new Label("" + renglon.getIdRenglon()));
			tablaElementos.setWidget(item, COL_INSUMO, new Label(renglon.getInsumo().getNombre()));
			tablaElementos.setWidget(item, COL_MARCA, new Label(renglon.getInsumo().getMarca()));
			tablaElementos.setWidget(item, COL_CANT, new Label(renglon.getCantidadRequerida()+""));
			tablaElementos.getRowFormatter().setStyleName(item, "tablaRenglon");
			item++;	
			
		}

		setWidget(panel);	
		
		
	}
	
	protected void salir() {
		this.hide();

	}

	public boolean getAccionSalir(){
		return this.accionSalir;
	}
	
}
