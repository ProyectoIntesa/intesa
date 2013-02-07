package edu.client;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.UsuarioCompDTO;

import com.google.gwt.user.client.ui.HTMLTable.Cell;

import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

public class P_BuscarOrdenProvisionInsumo extends PopupPanel {
	
	private static final int COL_NRO_ORDEN = 0;
	private static final int COL_GENERADO_POR = 1;
	private static final int COL_GENERADO_PARA = 2;
	private static final int COL_ESTADO = 3;
	private static final int COL_FECHAGENERACION = 4;
	private static final int COL_MAS_INFO = 5;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	private Label titulo;
	private Label generadaPor;
	private Label generadaPara;
	private Label estado;
	private Label fechaDesde;
	private Label fechaHasta;
	private Label pie;
	private Label segunFechaGeneracion;
	
	private ListBox generadaPorLb;
	private ListBox generadaParaLb;
	private ListBox estadoLb;
	
	private DateBox fechaDesdeDb;
	private DateBox fechaHastaDb;
	
	private CheckBox fechaCb;
	
	private Button buscar;
	private Button salir;
	
	private FlexTable panel;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElementos;
	private FlexTable botones1;
	private FlexTable botones2;
	
	//elementos que no aperecen en el popup, para supervisor y para jefe de fabrica
	private String nombreUsuario;
	private String apellidoUsuario;
	private String rolUsuario;
	
	//elementos que no aparecen en el popup, para supervisor unicamente
	private EmpleadoDTO emp;	
	private List<OrdenProvisionInsumoDTO> ordenesProvision;
	
	//elementos que no aparecen en el popup, para jefe de fabrica unicamente
	private List<EmpleadoDTO> listaGeneradosPor;
	private List<EmpleadoDTO> listaGeneradosPara;
	private List<Integer> listaIdGeneradosPara;
	
	
	
	
	public P_BuscarOrdenProvisionInsumo(String usuario, String rolUsuario){
		
		super(false);
		setStyleName("fondoPopup");
		this.nombreUsuario = usuario.split(", ")[1];
		this.apellidoUsuario = usuario.split(", ")[0]; 
		this.rolUsuario = rolUsuario;
		
		if(rolUsuario.compareTo("SUPERVISOR") == 0)
			this.ingresoUnSupervisor();
		else
			this.ingresoUnGerente();
	
	}
	
	protected void ingresoUnSupervisor(){
		
		fechaCb = new CheckBox(constante.fechaGeneracion());
		fechaCb.setStyleName("check");
		fechaCb.setWordWrap(false);
		fechaCb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionCheck();
			}
		});
		
		titulo = new Label(constante.buscarPor());
		titulo.setStyleName("labelTitulo");
		generadaPor = new Label(constante.generadaPor());
		generadaPor.setStyleName("gwt-LabelFormulario");
		generadaPara = new Label(constante.generadoPara());
		generadaPara.setStyleName("gwt-LabelFormulario");
		fechaDesde = new Label(constante.desde());
		fechaDesde.setStyleName("gwt-LabelFormulario");
		fechaHasta = new Label(constante.hasta());
		fechaHasta.setStyleName("gwt-LabelFormulario");
		estado = new Label(constante.estado());
		estado.setStyleName("gwt-LabelFormulario");
		segunFechaGeneracion = new Label(constante.segunFechaDeGeneracion());
		segunFechaGeneracion.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		generadaPorLb = new ListBox();
		generadaPorLb.setStyleName("gwt-TextArea");
		generadaPorLb.addItem(this.nombreUsuario+", "+this.apellidoUsuario);
		generadaPorLb.setEnabled(false);
		
		generadaParaLb = new ListBox();
		generadaParaLb.setStyleName("gwt-TextArea");	
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.getEmpleado(this.nombreUsuario, this.apellidoUsuario, this.rolUsuario, new AsyncCallback<EmpleadoDTO>() {
			@Override
			public void onSuccess(EmpleadoDTO result) {
				emp = result;
				cargarListaEmpleadosACargo(result);			
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
		estadoLb = new ListBox();
		estadoLb.setStyleName("gwt-TextArea");
		
		produccionService.getNombreEstados(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaEstados(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
		fechaDesdeDb = new DateBox();
		fechaDesdeDb.setEnabled(false);
		fechaDesdeDb.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		fechaHastaDb = new DateBox();
		fechaHastaDb.setEnabled(false);
		fechaHastaDb.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		buscar = new Button(constante.buscar());
		buscar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscarComoSupervisor();
			}
		});
		
		botones1 = new FlexTable();
		botones1.setWidget(0, 0, buscar);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		botones2 = new FlexTable();
		botones2.setWidget(0, 0, salir);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_NRO_ORDEN, constante.nroOrden());
		tablaElementos.getCellFormatter().setWidth(0, COL_NRO_ORDEN, "18%");
		tablaElementos.setText(0, COL_GENERADO_POR, constante.generadaPor());
		tablaElementos.getCellFormatter().setWidth(0, COL_GENERADO_POR, "18%");
		tablaElementos.setText(0, COL_GENERADO_PARA, constante.generadoPara());
		tablaElementos.getCellFormatter().setWidth(0, COL_GENERADO_PARA, "18%");
		tablaElementos.setText(0, COL_ESTADO, constante.estado());
		tablaElementos.getCellFormatter().setWidth(0, COL_ESTADO, "18%");
		tablaElementos.setText(0, COL_FECHAGENERACION, constante.fechaGeneracion());
		tablaElementos.getCellFormatter().setWidth(0, COL_FECHAGENERACION, "18%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "10%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 6);

		panel.setWidget(1, 0, generadaPor);
		panel.setWidget(1, 1, generadaPorLb);
		panel.getCellFormatter().setWidth(1, 1, "20%");
		panel.setWidget(1, 2, generadaPara);
		panel.setWidget(1, 3, generadaParaLb);
		panel.getCellFormatter().setWidth(1, 3, "20%");
		panel.setWidget(1, 4, estado);
		panel.setWidget(1, 5, estadoLb);
		panel.getCellFormatter().setWidth(1, 5, "20%");
		
		panel.setWidget(2, 0, segunFechaGeneracion);
		panel.getFlexCellFormatter().setColSpan(2, 0, 6);
		
		panel.setWidget(3, 0, fechaCb);
		panel.getFlexCellFormatter().setColSpan(3, 0, 1);
		panel.setWidget(3, 2, fechaDesde);
		panel.setWidget(3, 3, fechaDesdeDb);
		panel.setWidget(3, 4, fechaHasta);
		panel.setWidget(3, 5, fechaHastaDb);
		
		panel.setWidget(4, 0, botones1);
		panel.getFlexCellFormatter().setColSpan(4, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
				
		panel.setWidget(5, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(5, 0, 6);
		
		panel.setWidget(6, 0, pie);
		panel.getFlexCellFormatter().setColSpan(6, 0, 6);

		panel.setWidget(7, 0, botones2);
		panel.getFlexCellFormatter().setColSpan(7, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	
		
		setWidget(panel);
		
		
	}

	protected void ingresoUnGerente(){

		listaGeneradosPor = new LinkedList<EmpleadoDTO>();
		listaGeneradosPara = new LinkedList<EmpleadoDTO>();
		
		fechaCb = new CheckBox(constante.fechaGeneracion());
		fechaCb.setStyleName("check");
		fechaCb.setWordWrap(false);
		fechaCb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				seleccionCheck();
			}
		});
		
		titulo = new Label(constante.buscarPor());
		titulo.setStyleName("labelTitulo");
		generadaPor = new Label(constante.generadaPor());
		generadaPor.setStyleName("gwt-LabelFormulario");
		generadaPara = new Label(constante.generadoPara());
		generadaPara.setStyleName("gwt-LabelFormulario");
		fechaDesde = new Label(constante.desde());
		fechaDesde.setStyleName("gwt-LabelFormulario");
		fechaHasta = new Label(constante.hasta());
		fechaHasta.setStyleName("gwt-LabelFormulario");
		estado = new Label(constante.estado());
		estado.setStyleName("gwt-LabelFormulario");
		segunFechaGeneracion = new Label(constante.segunFechaDeGeneracion());
		segunFechaGeneracion.setStyleName("labelTitulo");
		pie = new Label();
		pie.setStyleName("labelTitulo");
		
		generadaPorLb = new ListBox();
		generadaPorLb.setStyleName("gwt-TextArea");
		generadaPorLb.addItem("TODOS");
		cargarListaGeneradaPorLb();	
		
		generadaPorLb.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if(generadaPorLb.getSelectedIndex() == 0){
					cargarGeneradaParaLbConTODOS();
				}
				if(generadaPorLb.getSelectedIndex() > 0){
					EmpleadoDTO empGeneradaPor = listaGeneradosPor.get(generadaPorLb.getSelectedIndex()-1);
					cargarGeneradaParaLbConParaUnGeneradaPor(empGeneradaPor);
				}
			}
		});
		
		
		generadaParaLb = new ListBox();
		generadaParaLb.setStyleName("gwt-TextArea");
		generadaParaLb.setEnabled(false);
		
		estadoLb = new ListBox();
		estadoLb.setStyleName("gwt-TextArea");
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);		
		produccionService.getNombreEstados(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarSugerenciaEstados(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
						
		
		fechaDesdeDb = new DateBox();
		fechaDesdeDb.setEnabled(false);
		fechaDesdeDb.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		fechaHastaDb = new DateBox();
		fechaHastaDb.setEnabled(false);
		fechaHastaDb.setFormat(new DefaultFormat(DateTimeFormat.getMediumDateFormat()));
		
		salir = new Button(constante.salir());
		salir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
		
		buscar = new Button(constante.buscar());
		buscar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscarComoGerente();
			}
		});
		
		botones1 = new FlexTable();
		botones1.setWidget(0, 0, buscar);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		botones2 = new FlexTable();
		botones2.setWidget(0, 0, salir);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		panel = new FlexTable();
		panel.setSize("1000px", "300px");
		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("300px");
		tablaElementos = new FlexTable();
		contenedorTabla.setWidget(tablaElementos);
		tablaElementos.setSize("100%", "100%");
		tablaElementos.setText(0, COL_NRO_ORDEN, constante.nroOrden());
		tablaElementos.getCellFormatter().setWidth(0, COL_NRO_ORDEN, "18%");
		tablaElementos.setText(0, COL_GENERADO_POR, constante.generadaPor());
		tablaElementos.getCellFormatter().setWidth(0, COL_GENERADO_POR, "18%");
		tablaElementos.setText(0, COL_GENERADO_PARA, constante.generadoPara());
		tablaElementos.getCellFormatter().setWidth(0, COL_GENERADO_PARA, "18%");
		tablaElementos.setText(0, COL_ESTADO, constante.estado());
		tablaElementos.getCellFormatter().setWidth(0, COL_ESTADO, "18%");
		tablaElementos.setText(0, COL_FECHAGENERACION, constante.fechaGeneracion());
		tablaElementos.getCellFormatter().setWidth(0, COL_FECHAGENERACION, "18%");
		tablaElementos.setText(0, COL_MAS_INFO, constante.masInformacion());
		tablaElementos.getCellFormatter().setWidth(0, COL_MAS_INFO, "10%");
		tablaElementos.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		
		panel.setWidget(0, 0, titulo);
		panel.getFlexCellFormatter().setColSpan(0, 0, 6);

		panel.setWidget(1, 0, generadaPor);
		panel.setWidget(1, 1, generadaPorLb);
		panel.getCellFormatter().setWidth(1, 1, "20%");
		panel.setWidget(1, 2, generadaPara);
		panel.setWidget(1, 3, generadaParaLb);
		panel.getCellFormatter().setWidth(1, 3, "20%");
		panel.setWidget(1, 4, estado);
		panel.setWidget(1, 5, estadoLb);
		panel.getCellFormatter().setWidth(1, 5, "20%");
		
		panel.setWidget(2, 0, segunFechaGeneracion);
		panel.getFlexCellFormatter().setColSpan(2, 0, 6);
		
		panel.setWidget(3, 0, fechaCb);
		panel.getFlexCellFormatter().setColSpan(3, 0, 1);
		panel.setWidget(3, 2, fechaDesde);
		panel.setWidget(3, 3, fechaDesdeDb);
		panel.setWidget(3, 4, fechaHasta);
		panel.setWidget(3, 5, fechaHastaDb);
		
		panel.setWidget(4, 0, botones1);
		panel.getFlexCellFormatter().setColSpan(4, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
				
		panel.setWidget(5, 0, contenedorTabla);
		panel.getFlexCellFormatter().setColSpan(5, 0, 6);
		
		panel.setWidget(6, 0, pie);
		panel.getFlexCellFormatter().setColSpan(6, 0, 6);

		panel.setWidget(7, 0, botones2);
		panel.getFlexCellFormatter().setColSpan(7, 0, 6);
		panel.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	
		
		setWidget(panel);
		
	}
	
	protected void buscarComoGerente() {
		
		String fecDesde = "";
		String fecHasta = "";
		
		if(fechaCb.getValue() == true){
						
			fecDesde = fechaDesdeDb.getTextBox().getText();
			fecHasta = fechaHastaDb.getTextBox().getText();	
			
			if(fecDesde.compareTo("") == 0 || fecHasta.compareTo("") == 0){
				
				Window.alert("Al filtrar la búsqueda por fechas, es obligatorio el ingreso de ambas fechas");
			}
			else{
				
				Date fecDesdeDate = new Date();
				fecDesdeDate = fechaDesdeDb.getValue();
				Date fecHastaDate = new Date();
				fecHastaDate = fechaHastaDb.getValue();
				
				if(fecDesdeDate.before(fecHastaDate) == true){
					String unEstado = estadoLb.getItemText(estadoLb.getSelectedIndex());
					
					int posPor = generadaPorLb.getSelectedIndex();
					int idEmpleadoPor;
					
					
					if(posPor <= 0) {
						idEmpleadoPor = 0;
					}
					else {		
						idEmpleadoPor = this.listaGeneradosPor.get(posPor-1).getIdEmpleado();
					}
						
					int posPara;
					int idEmpleadoPara;
					
					if(this.generadaParaLb.getItemCount() <= 0){
						posPara = 0;
						idEmpleadoPara = 0;
						
					} 
					else {
						
						posPara = generadaParaLb.getSelectedIndex();
						
						if(posPara == 0)
							idEmpleadoPara = 0;
						else {
							idEmpleadoPara = this.listaGeneradosPara.get(posPara-1).getIdEmpleado();
						}

					}
						
					ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
					produccionService.getOrdenProvisionInsumo(unEstado, idEmpleadoPor, idEmpleadoPara, fecDesde, fecHasta, new AsyncCallback<List<OrdenProvisionInsumoDTO>>() {
						@Override
						public void onSuccess(List<OrdenProvisionInsumoDTO> result) {	
							cargarTabla(result);			
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");
						}
					});

				}
				else{
					Window.alert("La fecha \"desde\" debe ser menor a la fecha \"hasta\"");
				}
		
			}
		
			
		}
		else {
			String unEstado = estadoLb.getItemText(estadoLb.getSelectedIndex());
			
			int posPor = generadaPorLb.getSelectedIndex();
			int idEmpleadoPor;
			
			
			if(posPor <= 0) {
				idEmpleadoPor = 0;
			}
			else {		
				idEmpleadoPor = this.listaGeneradosPor.get(posPor-1).getIdEmpleado();
			}
				
			int posPara;
			int idEmpleadoPara;
			
			if(this.generadaParaLb.getItemCount() <= 0){
				posPara = 0;
				idEmpleadoPara = 0;
				
			} 
			else {
				
				posPara = generadaParaLb.getSelectedIndex();
				
				if(posPara == 0)
					idEmpleadoPara = 0;
				else {
					idEmpleadoPara = this.listaGeneradosPara.get(posPara-1).getIdEmpleado();
				}

			}
				
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.getOrdenProvisionInsumo(unEstado, idEmpleadoPor, idEmpleadoPara, fecDesde, fecHasta, new AsyncCallback<List<OrdenProvisionInsumoDTO>>() {
				@Override
				public void onSuccess(List<OrdenProvisionInsumoDTO> result) {	
					cargarTabla(result);			
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
				}
			});
		}
		
			

		
		
		
		
	}

	protected void buscarComoSupervisor() {
		
		String unEstado = estadoLb.getItemText(estadoLb.getSelectedIndex());
		int pos = generadaParaLb.getSelectedIndex();
		int idEmpleadoPara;
		
		if(pos == 0)
			idEmpleadoPara = 0;
		else 
			idEmpleadoPara = this.emp.getListaEmpACargo().get(pos-1).getIdEmpleado();
		

		
		String fecDesde = "";
		String fecHasta = "";
		
		if(fechaCb.getValue() == true){
						
			fecDesde = fechaDesdeDb.getTextBox().getText();
			fecHasta = fechaHastaDb.getTextBox().getText();	
			
			if(fecDesde.compareTo("") == 0 || fecHasta.compareTo("") == 0){
				
				Window.alert("Al filtrar la búsqueda por fechas, es obligatorio el ingreso de ambas fechas");
			}
			else{
				
				Date fecDesdeDate = new Date();
				fecDesdeDate = fechaDesdeDb.getValue();
				Date fecHastaDate = new Date();
				fecHastaDate = fechaHastaDb.getValue();
				
				if(fecDesdeDate.before(fecHastaDate) == true){
					ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
					produccionService.getOrdenProvisionInsumo(unEstado, this.emp.getIdEmpleado(), idEmpleadoPara, fecDesde, fecHasta, new AsyncCallback<List<OrdenProvisionInsumoDTO>>() {
						@Override
						public void onSuccess(List<OrdenProvisionInsumoDTO> result) {	
							cargarTabla(result);			
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");
						}
					});
				}
				else{
					Window.alert("La fecha \"desde\" debe ser menor a la fecha \"hasta\"");
				}
			}
		}
		else {
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.getOrdenProvisionInsumo(unEstado, this.emp.getIdEmpleado(), idEmpleadoPara, fecDesde, fecHasta, new AsyncCallback<List<OrdenProvisionInsumoDTO>>() {
				@Override
				public void onSuccess(List<OrdenProvisionInsumoDTO> result) {	
					cargarTabla(result);			
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");
				}
			});
		}
				
	}
	
	protected void cargarGeneradaParaLbConTODOS(){
			
		this.generadaParaLb.clear();
		this.generadaParaLb.setEnabled(true);
		this.generadaParaLb.addItem("TODOS");
		this.listaGeneradosPara = new LinkedList<EmpleadoDTO>();
		this.listaIdGeneradosPara = new LinkedList<Integer>();
		boolean bandera = true;
				
		for (EmpleadoDTO emp : this.listaGeneradosPor) {
						
			for(EmpleadoDTO empInterno : emp.getListaEmpACargo()) {
				
				bandera = true;
				
				for(int i = 0; i < this.listaIdGeneradosPara.size(); i++) {
					
					if(this.listaIdGeneradosPara.get(i) == empInterno.getIdEmpleado()) {
						bandera = false;
					}
				}
				if(bandera == true) {
					this.listaIdGeneradosPara.add(empInterno.getIdEmpleado());
					this.listaGeneradosPara.add(empInterno);
					this.generadaParaLb.addItem(empInterno.getNombre()+", "+empInterno.getApellido());
				}
			}
		
		}		
	}
	
	protected void cargarGeneradaParaLbConParaUnGeneradaPor(EmpleadoDTO emp){

		this.generadaParaLb.clear();
		this.generadaParaLb.setEnabled(true);
		this.generadaParaLb.addItem("TODOS");
		this.listaGeneradosPara = new LinkedList<EmpleadoDTO>();
		this.listaIdGeneradosPara = new LinkedList<Integer>();
		
		for(EmpleadoDTO empInterno : emp.getListaEmpACargo()) {
			
			this.listaIdGeneradosPara.add(empInterno.getIdEmpleado());
			this.listaGeneradosPara.add(empInterno);
			this.generadaParaLb.addItem(empInterno.getNombre()+", "+empInterno.getApellido());
			
		}
			
	}
	
	protected void cargarListaGeneradaPorLb(){
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);		
		produccionService.getUsuariosSupervisoresYGerenteProduccion(new AsyncCallback<List<UsuarioCompDTO>>() {
			
			@Override
			public void onSuccess(List<UsuarioCompDTO> result) {
				
				for (UsuarioCompDTO usuario : result) {
					
					generadaPorLb.addItem(usuario.getNombreEmp()+", "+usuario.getApellidoEmp()+" ("+usuario.getRolUsu()+")");
					
					ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
					produccionService.getEmpleado(usuario.getNombreEmp(),usuario.getApellidoEmp(),usuario.getRolUsu(), new AsyncCallback<EmpleadoDTO>() {
						@Override
						public void onSuccess(EmpleadoDTO result) {
							
							agregarAListaGeneradosPor(result);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");
						}
					});			
				}
				
				
				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
	}
	
	protected void agregarAListaGeneradosPor(EmpleadoDTO result){
		listaGeneradosPor.add(result);		
	}
	
	protected void cargarListaEmpleadosACargo(EmpleadoDTO result) {
		
		generadaParaLb.addItem("TODOS");
		
		for (EmpleadoDTO empleado : result.getListaEmpACargo()) {
			String emp = empleado.getApellido()+", "+empleado.getNombre();
			this.generadaParaLb.addItem(emp);
		}
		
		
	}
	
	protected void cargarTabla(List<OrdenProvisionInsumoDTO> result) {
		
		this.tablaElementos.clear();
		ordenesProvision = new LinkedList<OrdenProvisionInsumoDTO>();
		ordenesProvision = result;
		
		for(int i = 0; i < result.size(); i++){
			
			Label infoC = new Label("");
			infoC.setSize("16px", "16px");
			infoC.setStyleName("labelInfo");
			
			DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
			String fecha = fmtDate.format(result.get(i).getFechaGeneracion());
			
			tablaElementos.setWidget(i + 1, COL_NRO_ORDEN, new Label(result.get(i).getIdOrdenProvisionInsumo()+""));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_NRO_ORDEN, true);
			tablaElementos.setWidget(i + 1, COL_GENERADO_POR, new Label(result.get(i).getEmpleadoByIdPedidoPor().getNombre()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_GENERADO_POR, true);
			tablaElementos.setWidget(i + 1, COL_GENERADO_PARA, new Label(result.get(i).getEmpleadoByIdPedidoPara().getNombre()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_GENERADO_PARA, false);
			tablaElementos.setWidget(i + 1, COL_ESTADO, new Label(result.get(i).getEstadoOrden()));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_ESTADO, true);
			tablaElementos.setWidget(i + 1, COL_FECHAGENERACION, new Label(fecha));
			tablaElementos.getCellFormatter().setWordWrap(i+1, COL_FECHAGENERACION, true);			
			tablaElementos.setWidget(i + 1, COL_MAS_INFO, infoC);
			tablaElementos.getFlexCellFormatter().setHorizontalAlignment(i+1, COL_MAS_INFO, HasHorizontalAlignment.ALIGN_CENTER);
			tablaElementos.getRowFormatter().setStyleName(i + 1, "tablaRenglon");
			
			infoC.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Cell celda = tablaElementos.getCellForEvent(event);
					long idOrden= ordenesProvision.get(celda.getRowIndex() - 1).getIdOrdenProvisionInsumo();
					
					ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
					produccionService.getOrdenProvisionInsumoSegunId(idOrden,new AsyncCallback<OrdenProvisionInsumoDTO>() {
						@Override
						public void onSuccess(OrdenProvisionInsumoDTO result) {

							P_DetalleOrdenProvisionInsumo detalle = new P_DetalleOrdenProvisionInsumo(result);		
							detalle.setGlassEnabled(true);
							detalle.center();
							detalle.show();							
							
						}
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");
						}
					});
				}
			});	
		}	
	}

	protected void salir(){
		this.hide();
	}
	
	protected void seleccionCheck() {
		
		if(fechaCb.getValue() == true){
			fechaDesdeDb.setEnabled(true);
			fechaHastaDb.setEnabled(true);
		}
			
		else{
			fechaDesdeDb.setEnabled(false);
			fechaDesdeDb.getTextBox().setText("");
			fechaHastaDb.setEnabled(false);
			fechaHastaDb.getTextBox().setText("");
		}
	}

	protected void cargarSugerenciaEstados(List<String> result) {

		estadoLb.addItem("TODOS");
		
		for (String sugerencia : result) {
			if(sugerencia.compareTo("EDICION") != 0)
				estadoLb.addItem(sugerencia);
		}

	}
}