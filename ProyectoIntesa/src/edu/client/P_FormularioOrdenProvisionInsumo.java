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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import edu.client.ProduccionService.ProduccionService;
import edu.client.ProduccionService.ProduccionServiceAsync;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RenglonOrdenProvisionInsumoDTO;

public class P_FormularioOrdenProvisionInsumo extends Composite {
	
	private static final int COL_INSUMO = 0;
	private static final int COL_MARCA = 1;
	private static final int COL_CANTREQUERIDA = 2;
	private static final int COL_BORRAR = 3;
	
	private Constantes constante = GWT.create(Constantes.class);
	
	private TabPanel padre;
	
	private Label titulo;
	private Label empPide;
	private Label fechaEdicion;
	private Label insumo;
	private Label marca;
	private Label cant;
	private Label observaciones;
	private Label pie;
	
	private ListBox empPideLb;
	private ListBox insumoLb;
	private ListBox marcaLb;
	private TextBox cantTb;
	private TextArea observacionesTa;
	
	private Button agregar;
	private Button cancelar;
	private Button generar;
	
	private FlexTable formulario;
	private ScrollPanel contenedorTabla;
	private FlexTable tablaElemento;
	private FlexTable botones1;
	private FlexTable botones2;
	
	private List<String> listaInsumos;
	private List<String> listaMarcas;
	private EmpleadoDTO empleado;
	private List<String[]> listaInsumosYMarcas;
	
	
	public P_FormularioOrdenProvisionInsumo(TabPanel padre, String responsable, String rolUsuario){
		
		this.padre = padre;
		
		String nombre = responsable.split(", ")[1];
		String apellido = responsable.split(", ")[0];
		final String rol = rolUsuario;
		
		listaInsumosYMarcas = new LinkedList<String[]>();
		listaInsumos = new LinkedList<String>();
		listaMarcas = new LinkedList<String>();
		
		ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
		produccionService.getNombresInsumos("", new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				cargarListaInsumos(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
		
		
		
		DateTimeFormat fmtDate = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha = fmtDate.format(new Date());
		
		titulo = new Label(constante.ordenDeProvisionDeInsumos());
		titulo.setStyleName("labelTitulo");
		empPide = new Label(constante.para()+": ");
		empPide.setStyleName("gwt-LabelFormulario");
		fechaEdicion = new Label(constante.fechaEdicion()+": "+fecha);
		fechaEdicion.setStyleName("gwt-LabelFormulario");
		insumo = new Label(constante.insumoAsterisco());
		insumo.setStyleName("gwt-LabelFormulario");
		marca = new Label(constante.marcaAsterisco());
		marca.setStyleName("gwt-LabelFormulario");
		cant = new Label(constante.cantidadAsterisco());
		cant.setStyleName("gwt-LabelFormulario");
		observaciones = new Label(constante.observaciones());
		observaciones.setStyleName("labelTitulo");
		pie = new Label("");
		pie.setStyleName("labelTitulo");
		
		
		agregar = new Button(constante.agregar());
		agregar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				agregarInsumo();
			}
		});
		
		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelar();
			}
		});
		
		generar = new Button(constante.generar());
		generar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				registrarOrden(rol);
			}
		});
		
		botones1 = new FlexTable();
		botones1.setWidget(0, 0, agregar);
		botones1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		botones2 = new FlexTable();
		botones2.setWidget(0, 0, generar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		botones2.setWidget(0, 1, cancelar);
		botones2.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		empPideLb = new ListBox();
		empPideLb.setStyleName("gwt-ListBox");
		
		produccionService.getEmpleado(nombre, apellido, rol, new AsyncCallback<EmpleadoDTO>() {
			@Override
			public void onSuccess(EmpleadoDTO result) {
				empleado = result;
				cargarListaEmpleadosACargo(result);
				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR EN EL SERVICIO");
			}
		});
		
		
		
		insumoLb = new ListBox();
		insumoLb.setStyleName("gwt-ListBox");
		insumoLb.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
								
				if(insumoLb.getSelectedIndex() != 0){
					marcaLb.setEnabled(true);
					
					ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);

					produccionService.getNombresMarcasSegunInsumo(insumoLb.getItemText(insumoLb.getSelectedIndex()), new AsyncCallback<List<String>>() {
						@Override
						public void onSuccess(List<String> result) {
							cargarListaMarcas(result);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("ERROR EN EL SERVICIO");
						}
					});
					
					
					
				}
				else{
					marcaLb.setEnabled(false);
				}
				
			}
		});
		

		marcaLb = new ListBox();
		marcaLb.setStyleName("gwt-ListBox");
		marcaLb.setEnabled(false);

		cantTb = new TextBox();
		
		observacionesTa = new TextArea();
		observacionesTa.setDirectionEstimator(false);
		observacionesTa.setWidth("100%");

		contenedorTabla = new ScrollPanel();
		contenedorTabla.setStyleName("tabla");
		contenedorTabla.setHeight("400px");

		tablaElemento = new FlexTable();
		contenedorTabla.setWidget(tablaElemento);
		tablaElemento.setSize("100%", "100%");

		tablaElemento.setText(0, COL_INSUMO, constante.insumo());
		tablaElemento.getCellFormatter().setWidth(0, COL_INSUMO, "35%");
		tablaElemento.setText(0, COL_MARCA, constante.marca());
		tablaElemento.getCellFormatter().setWidth(0, COL_MARCA, "35%");
		tablaElemento.setText(0, COL_CANTREQUERIDA, constante.cantidad());
		tablaElemento.getCellFormatter().setWidth(0, COL_CANTREQUERIDA, "25%");
		tablaElemento.setText(0, COL_BORRAR, "");
		tablaElemento.getCellFormatter().setWidth(0, COL_BORRAR, "5%");
		tablaElemento.getRowFormatter().addStyleName(0, "tablaEncabezado");
		
		formulario = new FlexTable();
		formulario.setStyleName("formatoFormulario");
		formulario.setHeight("637px");
		formulario.setWidth("920px");
		
		formulario.setWidget(0, 0, titulo);
		formulario.getFlexCellFormatter().setColSpan(0, 0, 6);
		
		formulario.setWidget(1, 0, empPide);
		formulario.setWidget(1, 1, empPideLb);
		formulario.setWidget(1, 5, fechaEdicion);
		
		formulario.setWidget(2, 0, insumo);
		formulario.setWidget(2, 1, insumoLb);
		formulario.getCellFormatter().setWidth(2, 1, "20%");
		formulario.setWidget(2, 2, marca);
		formulario.setWidget(2, 3, marcaLb);
		formulario.getCellFormatter().setWidth(2, 3, "20%");
		formulario.setWidget(2, 4, cant);
		formulario.setWidget(2, 5, cantTb);
		formulario.getCellFormatter().setWidth(2, 5, "20%");
		
		formulario.setWidget(3, 0, botones1);
		formulario.getFlexCellFormatter().setColSpan(3, 0, 6);
		formulario.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		formulario.setWidget(4, 0, contenedorTabla);
		formulario.getFlexCellFormatter().setColSpan(4, 0, 6);
		
		formulario.setWidget(5, 0, observaciones);
		formulario.getFlexCellFormatter().setColSpan(5, 0, 6);
		formulario.setWidget(6, 0, observacionesTa);
		formulario.getFlexCellFormatter().setColSpan(6, 0, 6);

		formulario.setWidget(7, 0, pie);
		formulario.getFlexCellFormatter().setColSpan(7, 0, 6);

		formulario.setWidget(8, 0, botones2);
		formulario.getFlexCellFormatter().setColSpan(8, 0, 6);
		formulario.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		
		initWidget(formulario);

		
		
	}
	
	protected void registrarOrden(String rol) {
		

		OrdenProvisionInsumoDTO nueva = new OrdenProvisionInsumoDTO();
		int pos = this.empPideLb.getSelectedIndex();
		nueva.setFechaEdicion(new Date());
		nueva.setFechaGeneracion(new Date());
		nueva.setEmpleadoByIdPedidoPara(this.empleado.getListaEmpACargo().get(pos));
		nueva.setEmpleadoByIdPedidoPor(this.empleado);
		nueva.setObservaciones(this.observacionesTa.getText());
		
		
		for (int i = 1; i < tablaElemento.getRowCount(); i++) {
			RenglonOrdenProvisionInsumoDTO renglon = new RenglonOrdenProvisionInsumoDTO();
			InsumoDTO insu = new InsumoDTO();
			insu.setMarca(((Label) tablaElemento.getWidget(i, COL_MARCA)).getText());
			insu.setNombre(((Label) tablaElemento.getWidget(i, COL_INSUMO)).getText());
			renglon.setIdRenglon(i);
			renglon.setCantidadRequerida(new Double(((Label) tablaElemento.getWidget(i, COL_CANTREQUERIDA)).getText()));
			renglon.setInsumo(insu);
			nueva.getRenglonOrdenProvisionInsumos().add(renglon);
		}
		
		if(rol.compareTo("SUPERVISOR") == 0){
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.registrarOrdenProvisionInsumo(nueva, new AsyncCallback<Long>() {

				@Override
				public void onSuccess(Long result) {
					if (result != -1) {
						Window.alert("La orden de provisión ha sido \"generada\" con el número: "+result);
						padre.remove(numeroElemento(constante.ordenDeProvisionDeInsumos()));
					} else {
						Window.alert("No se ha podido efectuar la acción");
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");

				}
			});
		}
		
		if(rol.compareTo("JEFE DE FABRICA") == 0 || rol.compareTo("GERENTE DE PRODUCCION") == 0){
			ProduccionServiceAsync produccionService = GWT.create(ProduccionService.class);
			produccionService.registrarOrdenProvisionInsumoGerente(nueva, new AsyncCallback<Long>() {

				@Override
				public void onSuccess(Long result) {
					if (result != -1) {
						Window.alert("La orden de provisión ha sido \"generada\" con el número: "+result);
						padre.remove(numeroElemento(constante.ordenDeProvisionDeInsumos()));
					} else {
						Window.alert("No se ha podido efectuar la acción");
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR EN EL SERVICIO");

				}
			});
		}
	

	
	
}

	protected void agregarInsumo() {
		
		Validaciones validar = new Validaciones();
		
		boolean vCantidad = validar.textBoxSoloNumeros(this.cantTb.getText());
		boolean bandera = false;
		
		
			if(insumoLb.getSelectedIndex() != 0 && marcaLb.getSelectedIndex() != 0 && cantTb.getText().compareTo("") != 0){
				
				if(vCantidad){
					final String nombreInsumo = insumoLb.getItemText(insumoLb.getSelectedIndex());
					final String nombreMarca = marcaLb.getItemText(marcaLb.getSelectedIndex());
					Double cantidad = Double.parseDouble(cantTb.getText());
						
					for (int i = 1; i < tablaElemento.getRowCount(); i++) {
						String nombreInsumoTabla = ((Label)tablaElemento.getWidget(i, COL_INSUMO)).getText();
						String nombreMarcaTabla = ((Label)tablaElemento.getWidget(i, COL_MARCA)).getText();
						
						if(nombreInsumo.compareTo(nombreInsumoTabla)==0 && nombreMarca.compareTo(nombreMarcaTabla)==0){
							bandera = true;
							break;
						}
					}
					
					if(bandera == false){
						int fila = tablaElemento.getRowCount();
						
						Label eliminar = new Label("");
						eliminar.setSize("16px", "16px");
						eliminar.setStyleName("labelBorrar");
						eliminar.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								String[] nombreYMarca={nombreInsumo,nombreMarca};
								eliminar(nombreYMarca);
							}
						});
			
						tablaElemento.setWidget(fila, COL_INSUMO, new Label(nombreInsumo));
						tablaElemento.setWidget(fila, COL_MARCA, new Label(nombreMarca));
						tablaElemento.setWidget(fila, COL_CANTREQUERIDA, new Label(cantidad+""));
						tablaElemento.setWidget(fila, COL_BORRAR, eliminar);
						tablaElemento.getFlexCellFormatter().setHorizontalAlignment(fila, COL_BORRAR, HasHorizontalAlignment.ALIGN_CENTER);
						tablaElemento.getRowFormatter().setStyleName(fila, "tablaRenglon");
			
						String[] nombreYMarca={nombreInsumo,nombreMarca};
						this.listaInsumosYMarcas.add(nombreYMarca);
					}
					else{
						Window.alert("No se puede agregar dos veces el mismo insumo en una misma orden de provisión");
					}
				}
				else{
					Window.alert("La cantidad especificada debe ser un número");
				}
				
				

				
			}
			else{
				Window.alert("Los campos que poseen (*) son oblicatorios");
			}
		

		
		
	}
	
	private void eliminar(String [] insumoMarca) {
		
		int fila = -1;
		
		for (int i = 0; i < this.listaInsumosYMarcas.size(); i++) {
			
			String insumoAux = this.listaInsumosYMarcas.get(i)[0];
			String marcaAux = this.listaInsumosYMarcas.get(i)[1];
			
			if(insumoMarca[0].compareTo(insumoAux) == 0 && insumoMarca[1].compareTo(marcaAux) == 0){
				
				fila = i;
				break;
			}
		}
		
		if(fila != -1){
			
			this.listaInsumosYMarcas.remove(fila);
			tablaElemento.removeRow(fila + 1);
		}
		
		
	}
	
	protected void cargarListaEmpleadosACargo(EmpleadoDTO result) {
		
		for (EmpleadoDTO empleado : result.getListaEmpACargo()) {
			String emp = empleado.getApellido()+", "+empleado.getNombre();
			this.empPideLb.addItem(emp);
		}
		
		
	}

	protected void cargarListaInsumos(List<String> result) {
		
		List <String> aux = new LinkedList<String>();
		aux = result;
		
		this.listaInsumos.add("ninguno");
		
		for (String cadena : aux) {
			if(!this.listaInsumos.contains(cadena))
				this.listaInsumos.add(cadena);
		}
		
		
		for (String insumo : listaInsumos) {
			this.insumoLb.addItem(insumo);
		}
	}

	private void cancelar(){
		padre.remove(numeroElemento(constante.ordenDeProvisionDeInsumos()));
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
	
	protected void cargarListaMarcas(List<String> result) {
		
		this.marcaLb.clear();
		
		this.listaMarcas = result;
		this.listaMarcas.add(0,"ninguno");
		
		for (String marca : listaMarcas) {
			this.marcaLb.addItem(marca);
		}
		
	}
	

}
