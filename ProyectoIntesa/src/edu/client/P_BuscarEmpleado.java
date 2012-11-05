package edu.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import edu.client.AdministradorService.AdministradorService;
import edu.client.AdministradorService.AdministradorServiceAsync;
import edu.shared.DTO.EmpleadoDTO;

public class P_BuscarEmpleado extends PopupPanel {

	TabPanel padre;
	private static final int COL_NROLEGAJO = 1;
	private static final int COL_NOMBRE = 2;
	private static final int COL_APELLIDO = 3;
	private static final int COL_PUESTO = 4;
	private static final int COL_MAS_INFO = 5;
	
	private EmpleadoDTO empleadoSeleccionado;
	private FlexTable panel;
	private FlexTable tablaElementos;

	private ScrollPanel contenedorTabla;

	private Constantes constante = GWT.create(Constantes.class);

	private Label empleados;
	private Label pie;
	public List<EmpleadoDTO> listaEmpleados;
	private Button salir;

	public P_BuscarEmpleado(TabPanel padre) {

		super(false);
		this.padre=padre;
		listaEmpleados = new LinkedList<EmpleadoDTO>();
		
		AdministradorServiceAsync adminServie = GWT.create(AdministradorService.class);
		
		adminServie.getEmpleados(listaEmpleados,new AsyncCallback<List<EmpleadoDTO>>() {
			@Override
			public void onSuccess(List<EmpleadoDTO> result) {
				cargarListaEmpleados(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("No se pudo cargar la lista de empleados");
			}
		});
		
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

	public void cargarListaEmpleados(List<EmpleadoDTO> lista)
	{
		listaEmpleados= lista;
		for (int i = 0; i < listaEmpleados.size(); i++) {
			Label info=new Label("");
			info.setSize("16px", "16px");
			info.addStyleName("labelInfo");
			tablaElementos.setWidget(i + 1, COL_NROLEGAJO, new Label("" + listaEmpleados.get(i).getNroLegajo()));
			tablaElementos.setWidget(i + 1, COL_NOMBRE, new Label(listaEmpleados.get(i).getNombre()));
			tablaElementos.setWidget(i + 1, COL_APELLIDO, new Label("" + listaEmpleados.get(i).getApellido()));
			tablaElementos.setWidget(i + 1, COL_PUESTO, new Label("" + listaEmpleados.get(i).getPuesto()));			
			tablaElementos.setWidget(i + 1, COL_MAS_INFO, info);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_MAS_INFO, HasHorizontalAlignment.ALIGN_CENTER );			
			tablaElementos.getRowFormatter().addStyleName(i+1, "renglon");
			info.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){					
					Cell celda= tablaElementos.getCellForEvent(event);
					irAEmpleado(listaEmpleados.get(celda.getRowIndex()-1));
				}
			});
		}
		
	}
	
	
	
	protected void irAEmpleado(EmpleadoDTO empleado) {
		empleadoSeleccionado = empleado;
		
		this.hide();
	}

	protected void salir() {
		
		this.hide();

	}
	
	public EmpleadoDTO getEmpleado()
	{
		return empleadoSeleccionado;
	}

	

}
