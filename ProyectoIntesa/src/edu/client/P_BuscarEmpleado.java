package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import edu.client.Constantes;

public class P_BuscarEmpleado extends PopupPanel {
	
	private static final int COL_NROLEGAJO = 1;
	private static final int COL_NOMBRE = 2;
	private static final int COL_APELLIDO = 3;
	private static final int COL_PUESTO = 4;
	private static final int COL_MAS_INFO = 5;
	
	private FlexTable panel;
	private FlexTable tablaElementos;
	
	private ScrollPanel contenedorTabla;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	private Label empleados;
	private Label pie;
	
	private Button salir;
	
	public P_BuscarEmpleado(){
		
		super(false);

		setStyleName("fondoPopup");
		
		panel = new FlexTable();
		
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("350px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");	
		tablaElementos.setText(0, COL_NROLEGAJO, constante.nroLegajo());
		tablaElementos.getCellFormatter().setWidth(0, COL_NROLEGAJO, "23%");
		tablaElementos.setText(0, COL_NOMBRE, constante.nombre());
		tablaElementos.getCellFormatter().setWidth(0, COL_NOMBRE, "23%");
		tablaElementos.setText(0, COL_APELLIDO, constante.apellido());
		tablaElementos.getCellFormatter().setWidth(0, COL_APELLIDO, "23%");
		tablaElementos.setText(0, COL_PUESTO, constante.puesto());
		tablaElementos.getCellFormatter().setWidth(0, COL_PUESTO, "23%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "8%");		
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		
		
		empleados = new Label(constante.empleados());
		empleados.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		panel.getFlexCellFormatter().setColSpan(0, 0, 2);
		panel.setWidget(0, 0, empleados);
		
		panel.getFlexCellFormatter().setColSpan(1, 0, 2);
		panel.setWidget(1, 0, contenedorTabla);
		
		panel.getFlexCellFormatter().setColSpan(2, 0, 2);
		panel.setWidget(2, 0, pie);
			
		panel.setWidget(3, 1, salir);
		panel.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_CENTER);	
		
		
		
		
		
		
		setWidget(panel);
		panel.setSize("850px", "400px");
	}
	
	protected void salir() {
		this.hide();

	}
	
}
