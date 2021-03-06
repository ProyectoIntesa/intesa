package edu.client;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.safehtml.shared.SafeHtml;

public interface Constantes extends Constants {

	 @DefaultStringValue("IMPRIMIR")
	  String imprimir();
	 
	 @DefaultStringValue("JEFE DE FABRICA")
	  String jefeDeFabrica();
	 
	 @DefaultStringValue("SUPERVISOR")
	  String supervisor();
	 
	 @DefaultStringValue("GERENTE DE PRODUCCION")
	  String gerenteDeProduccion();
	 
	 @DefaultStringValue("GERENTE DE COMPRAS")
	  String gerenteDeCompras();
	 
	 @DefaultStringValue("USUARIO (*)")
	  String usuarioAsterisco();
	 
	 @DefaultStringValue("CONTRASEÑA (*)")
	  String contraseniaAsterisco();
	 
	 @DefaultStringValue("CANT REAL")
	  String cantReal();
	 
	 @DefaultStringValue("NRO LEGAJO (*)")
	  String nroLegajoAsterisco();
	 
	 @DefaultStringValue("APELLIDO (*)")
	  String apellidoAsterisco();
	 
	 @DefaultStringValue("ACTUALIZAR CANTIDAD")
	  String actualizarCantidad();
	 
	 @DefaultStringValue("ACTUALIZAR CANT")
	  String actualizarCant();
	 
	 @DefaultStringValue("TIPO PROVEEDOR (*)")
	  String tipoProveedorAsterisco();
	 
	 @DefaultStringValue("INSUMO (*)")
	  String insumoAsterisco();
	 	 
	 @DefaultStringValue("CANTIDAD (*)")
	  String cantidadAsterisco();
	 
	 @DefaultStringValue("ELIMINAR PROVEEDOR")
	  String eliminarProveedor();
	 
	 @DefaultStringValue("NOMBRE (*)")
	  String nombreAsterisco();
	 
	 @DefaultStringValue("CATEGORIA (*)")
	  String categoriaAsterisco();
	 
	 @DefaultStringValue("MARCA (*)")
	  String marcaAsterisco();
	 
	 @DefaultStringValue("LOTE COMPRA (*)")
	  String loteCompraAsterisco();
	 
	 @DefaultStringValue("STOCK SEGURIDAD (*)")
	  String stockSeguridadAsterisco();
	 
	 @DefaultStringValue("PRECIO (*)")
	  String precioAsterisco();
	 
	 @DefaultStringValue("CARGAR A LA ORDEN")
	  String cargarALaOrden(); 
	 
	 @DefaultStringValue("COMPRA DE INSUMOS")
	  String compraDeInsumos();
	 
	 @DefaultStringValue("EMPRESA (*)")
	  String empresaAsterisco();
	 
	 @DefaultStringValue("CUIT (*)")
	  String cuitAsterisco();
	 
	 @DefaultStringValue("RESPONSABLE (*)")
	  String responsableAsterisco();
	 
	 @DefaultStringValue("PAIS (*)")
	  String paisAsterisco();
	 
	 @DefaultStringValue("PROVINCIA (*)")
	  String provinciaAsterisco();
	 
	 @DefaultStringValue("LOCALIDAD (*)")
	  String localidadAsterisco();
	 
	 @DefaultStringValue("CALLE (*)")
	  String calleAsterisco();
	 
	 @DefaultStringValue("ALTURA (*)")
	  String alturaAsterisco();
	 
	 @DefaultStringValue("VALIDAR COMPRAS")
	  String validarCompras(); 
	 
	 @DefaultStringValue("NO SE COMPLETO LA RECEPCIÓN DE LA ORDEN, DESEA CONTINUAR CON EL CIERRE. LA ORDEN QUEDARÁ CERRADA EN FORMA PARCIAL")
	  String mensajeOrden(); 
	 
	@DefaultStringValue("ORDEN COMPRA DE INSUMO")
	  String ordenCompraDeInsumo();
	  
	  @DefaultStringValue("CANCELAR ORDENES")
	  String cancelarOrdenes();
	  
	  @DefaultStringValue("CANT DISPONIBLE")
	  String cantDisponible();
	  
	  @DefaultStringValue("GERENTE PRODUCCION")
	  String gerenteProduccion();
	  
	  @DefaultStringValue("SUPERVISOR PRODUCCION")
	  String supervisorProduccion();
	  
	  @DefaultStringValue("PROVISION DE INSUMOS")
	  String provisionDeInsumos();
	  
	  @DefaultStringValue("CREAR ORDEN")
	  String crearOrden();
	  
	  @DefaultStringValue("BUSCAR ORDEN")
	  String buscarOrden();
	  
	  @DefaultStringValue("VALIDAR ORDENES")
	  String validarOrdenes();
	  
	  @DefaultStringValue("VALIDAR ORDEN")
	  String validarOrden();
	  
	  @DefaultStringValue("VALIDAR")
	  String validar();
	  
	  @DefaultStringValue("CERRAR REMITO")
	  String cerrarRemito();
	  
	  @DefaultStringValue("NRO DE REMITO")
	  String nroDeRemito();
	  
	  @DefaultStringValue("CANT ENVIADA")
	  String cantEnviada();
	  
	  @DefaultStringValue("NRO REMITO")
	  String nroRemito();
	  
	  @DefaultStringValue("FECHA CIERRE")
	  String fechaCierre();
	  
	  @DefaultStringValue("CANT FALTANTE A ENTREGAR")
	  String cantFaltanteAEntregar();
	  
	  @DefaultStringValue("CANT A SER ENVIADA")
	  String cantASerEnviada();
	  
	  @DefaultStringValue("SEGUN FECHA DE GENERACION")
	  String segunFechaDeGeneracion();
	  
	  @DefaultStringValue("ORDEN DE PROVISION")
	  String ordenDeProvision();
	  
	  @DefaultStringValue("GENERADO POR")
	  String generadoPor();
	  
	  @DefaultStringValue("ORDEN DE PROVISION DE INSUMOS")
	  String ordenDeProvisionDeInsumos();
	  
	  @DefaultStringValue("ORDEN DE PROVISION DE PRODUCTOS")
	  String ordenDeProvisionDeProductos();
	  
	  
	  @DefaultStringValue("PARA")
	  String para();
	  
	  @DefaultStringValue("OBSERVACIONES DE LA ORDEN DE PROVISION DE INSUMOS")
	  String observacionesDeLaOrdenDeProvisionDeInsumos();
	  
	  @DefaultStringValue("OBSERVACIONES DEL REMITO")
	  String observacionesDelRemito();
	  
	  @DefaultStringValue("GENERADO PARA")
	  String generadoPara();
	  
	  @DefaultStringValue("EGRESO DE MATERIALES")
	  String egresoDeMateriales();
	  
	  @DefaultStringValue("GENERAR REMITO")
	  String generarRemito();
	  	  
	  @DefaultStringValue("BUSCAR REMITO")
	  String buscarRemito();  
	  
	  @DefaultStringValue("INGRESO DE MATERIALES")
	  String IngresoDeMateriales();
	  
	  @DefaultStringValue("INGRESAR REMITO")
	  String ingresarRemito();
	  
	  @DefaultStringValue("REMITO EXTERNO")
	  String remitoExterno();
	  
	  @DefaultStringValue("CANT FALTANTE")
	  String cantFaltante();
	  
	  @DefaultStringValue("NRO REMITO EXTERNO")
	  String nroRemitoExterno();
	  
	  @DefaultStringValue("CANT PEDIDA")
	  String cantPedida();
	  
	  @DefaultStringValue("CANT RECIBIDA")
	  String cantRecibida();
	  
	  @DefaultStringValue("FECHA INGRESO")
	  String fechaIngreso();
	  
	  @DefaultStringValue("NRO ORDEN DE COMPRA")
	  String nroOrdenDeCompra();
	  
	  @DefaultStringValue("NRO ORDEN DE PROVISION")
	  String nroOrdenProvision();
	  
	  
	  @DefaultStringValue("ACEPTAR")
	  String aceptar();
	  	  
	  @DefaultStringValue("REMITO INTERNO")
	  String remitoInterno();
	  
	  @DefaultStringValue("INGRESAR")
	  String ingresar();
	  
	  @DefaultStringValue("MODIFICAR ORDEN COMPRA DE INSUMO")
	  String modificarOrdenCompraDeInsumo();
	  
	  @DefaultStringValue("AGREGAR INSUMO")
	  String agregarInsumo();
	  
	  @DefaultStringValue("ELIMINAR ORDEN")
	  String eliminarOrden();
	  
	  @DefaultStringValue("CANCELAR ORDEN")
	  String cancelarOrden();
	  
	  @DefaultStringValue("CERRAR ORDEN")
	  String cerrarOrden();
	  
	  @DefaultStringValue("ENVIAR ORDEN")
	  String enviarOrden();
	  
	  @DefaultStringValue("GENERADA POR")
	  String generadaPor();
	  
	  @DefaultStringValue("ORDEN COMPRA DE PRODUCTO")
	  String ordenCompraDeProducto();
	  
	  @DefaultStringValue("ORDEN COTIZACION DE PRODUCTO")
	  String ordenCotizacionDeProducto();
	
	  @DefaultStringValue("ITEM")
	  String item();
	  
	  @DefaultStringValue("TODOS")
	  String todos();
	  
	  @DefaultStringValue("FECHA DESDE")
	  String fechaDesde();
	  
	  @DefaultStringValue("FECHA HASTA")
	  String fechaHasta();
	  
	  @DefaultStringValue("HASTA")
	  String hasta();
	  
	  @DefaultStringValue("DESDE")
	  String desde();
	  
	  @DefaultStringValue("MODIFICAR ORDEN DE COMPRA DE INSUMO")
	  String modificarOrdenDeCompraDeInsumo();
	  
	  @DefaultStringValue("MODIFICAR ORDEN DE COMPRA DE PRODUCTOS")
	  String modificarOrdenDeCompraDeProductos();
	  
	  @DefaultStringValue("TIPO ORDEN")
	  String tipoOrden();
	  
	  @DefaultStringValue("SEGÚN FECHA DE EDICIÓN")
	  String segunFechaDeEdicion();
	  
	  @DefaultStringValue("NRO ORDEN")
	  String nroOrden();
	  
	  @DefaultStringValue("CANT COMPRAR")
	  String cantComprar();
	  
	  @DefaultStringValue("CANT ENTREGADA")
	  String cantEntregada();
	  
	  @DefaultStringValue("CANT INVENTARIO")
	  String cantInventario();
	  
	  @DefaultStringValue("SUBTOTAL")
	  String subtotal();
	
	  @DefaultStringValue("EMPRESA")
	  String empresa();
	  
	  @DefaultStringValue("GENERAR")
	  String generar();
	  
	  @DefaultStringValue("ARMAR ORDEN")
	  String armarOrden();
	  
	  @DefaultStringValue("FECHA EDICION")
	  String fechaEdicion();
	  
	  @DefaultStringValue("MODO ENVIO")
	  String modoEnvio();
	  
	  @DefaultStringValue("FORMA DE PAGO")
	  String formaDePago();
	  
	  @DefaultStringValue("IVA")
	  String iva();
	  	  
	  @DefaultStringValue("TOTAL")
	  String total();
	  	  
	  @DefaultStringValue("REQUERIMIENTOS DE INSUMO")
	  String requerimientosDeInsumo();
	  
	  @DefaultStringValue("REQUERIMIENTOS NECESARIOS")
	  String requerimientosNecesarios();
	  
	  @DefaultStringValue("REQUERIMIENTOS ADICIONALES")
	  String requerimientosAdicionales();
	  
	  @DefaultStringValue("ORDEN DE COMPRA DE INSUMOS")
	  String ordenDeCompraDeInsumos();
	  
	  @DefaultStringValue("ORDEN DE COMPRA DE PRODUCTOS")
	  String ordenDeCompraDeProductos();
	  
	  @DefaultStringValue("DE PRODUCTOS")
	  String deProductos();
	  
	  @DefaultStringValue("DE INSUMOS")
	  String deInsumos();
	  
	  @DefaultStringValue("ELIMINAR INSUMO")
	  String eliminarInsumo();
	  
	  @DefaultStringValue("MODIFICAR INSUMO")
	  String modificarInsumo();
	  
	  @DefaultStringValue("PRODUCTOS")
	  String productos();
	  
	  @DefaultStringValue("PROVEEDOR")
	  String proveedor();
	  
	  @DefaultStringValue("PRECIO")
	  String precio();
	  
	  @DefaultStringValue("ELIMINAR")
	  String eliminar();

	  @DefaultStringValue("NUEVO INSUMO")
	  String nuevoInsumo();
	  
	  @DefaultStringValue("CARGAR")
	  String cargar();
	  
	  @DefaultStringValue("INSUMO")
	  String insumo();
	  
	  @DefaultStringValue("CATEGORIA")
	  String categoria();
	  
	  @DefaultStringValue("MARCA")
	  String marca();
	  
	  @DefaultStringValue("AGREGAR PROVEEDOR")
	  String agregarProveedor();
	  
	  @DefaultStringValue("INVENTARIO")
	  String inventariol();
	  
	  @DefaultStringValue("LOTE COMPRA")
	  String loteCompra();
	  
	  @DefaultStringValue("STOCK SEGURIDAD")
	  String stockSeguridad();
	  
	  @DefaultStringValue("INSUMOS")
	  String insumos();
	  
	  @DefaultStringValue("TIPO")
	  String tipo();
	  
	  @DefaultStringValue("NUEVO PROVEEDOR")
	  String nuevoProveedor();
	  
	  @DefaultStringValue("TIPO PROVEEDOR")
	  String tipoProveedor();
	  
	  @DefaultStringValue("MODIFICAR CLIENTE")
	  String modificarCliente();
	  
	  @DefaultStringValue("MODIFICAR PROVEEDOR")
	  String modificarProveedor();
	  
	  @DefaultStringValue("MODIFICAR CONTACTO")
	  String modificarContacto();
	  
	  @DefaultStringValue("CPA")
	  String cpa();
	  
	  @DefaultStringValue("ELIMINAR CONTACTO")
	  String eliminarContacto();
	  
	  @DefaultStringValue("Códigos postales con la nueva codificación")
	  String cpaInfo();
	  
	  
	  @DefaultStringValue("VENTAS")
	  String ventas();
	  
	  @DefaultStringValue("PRODUCCION")
	  String produccion();
	  
	  @DefaultStringValue("ALMACEN")
	  String almacen();
	  
	  @DefaultStringValue("INGENIERIA")
	  String ingenieria();
	  
	  @DefaultStringValue("COMPRAS")
	  String compras();
	  
	  @DefaultStringValue("QUITAR")
	  String quitar();
	  
	  @DefaultStringValue("EMPLEADOS POSIBLES A SER ASIGNADOS")
	  String empleadosPosiblesASerAsignados();
	  
	  @DefaultStringValue("MODIFICAR EMPLEADO")
	  String modificarEmpleado();
	  
	  @DefaultStringValue("MODIFICAR USUARIO")
	  String modificarUsuario();
	  
	  @DefaultStringValue("EMPLEADOS")
	  String empleados();
	  
	  @DefaultStringValue("EMPLEADOS A CARGO")
	  String empleadosACargo();
	  
	  @DefaultStringValue("ASIGNAR")
	  String asignar();
	  
	  @DefaultStringValue("USUARIOS")
	  String usuarios();	

	  @DefaultStringValue("NOMBRE")
	  String nombre();
	  
	  @DefaultStringValue("CUIT")
	  String cuit();
	  
	  @DefaultStringValue("RESPONSABLE")
	  String responsable();	
	  
	  @DefaultStringValue("RUBRO")
	  String rubro();
	  
	  @DefaultStringValue("CALLE")
	  String calle();
	  
	  @DefaultStringValue("ALTURA")
	  String altura();
	  
	  @DefaultStringValue("PISO")
	  String piso();
	  
	  @DefaultStringValue("OFICINA")
	  String oficina();
	  
	  @DefaultStringValue("LOCALIDAD")
	  String localidad();
	  
	  @DefaultStringValue("CÓDIGO POSTAL")
	  String codigoPostal();
	  
	  @DefaultStringValue("PROVINCIA")
	  String provincia();
	  
	  @DefaultStringValue("PAÍS")
	  String pais();
	  
	  @DefaultStringValue("TELÉFONO")
	  String telefono();
	  
	  @DefaultStringValue("TELÉFONO PARTICULAR")
	  String telefonoParticular();
	  
	  @DefaultStringValue("CELULAR")
	  String celular();
	  
	  @DefaultStringValue("INTERNO")
	  String interno();
	  
	  @DefaultStringValue("CARGO")
	  String cargo();
	  
	  @DefaultStringValue("PÁGINA-WEB")
	  String paginaWeb();
	  
	  @DefaultStringValue("FAX")
	  String fax();
	  
	  @DefaultStringValue("E-MAIL")
	  String eMail();
	  
	  @DefaultStringValue("OBSERVACIONES")
	  String observaciones();
	
	  @DefaultStringValue("AGREGAR")
	  String agregar();
	  
	  @DefaultStringValue("CANCELAR")
	  String cancelar();
	  
	  @DefaultStringValue("GUARDAR")
	  String guardar();
	
	  @DefaultStringValue("AGREGAR CONTACTO")
	  String agregarContacto();
	
	  @DefaultStringValue("DATOS DE EMPRESA")
	  String datosEmpresa();
	  
	  @DefaultStringValue("DATOS DE USUARIO")
	  String datosDeUsuario();
	  
	  @DefaultStringValue("DATOS DE EMPLEADO")
	  String datosDeEmpleado();
	  
	  @DefaultStringValue("DOMICILIO")
	  String domicilio();
	  
	  @DefaultStringValue("CONTACTOS")
	  String constactos();
	
	  @DefaultStringValue("NUEVO CONTACTO")
	  String nuevoContacto();
	  
	  @DefaultStringValue("ORDEN DE PEDIDO NRO")
	  String ordenDePedidoNro();
	  
	  @DefaultStringValue("FECHA COMIENZO EDICIÓN")
	  String fechaComienzoEdicion();
	
	  @DefaultStringValue("CLIENTE")
	  String cliente();
	
	  @DefaultStringValue("FECHA FINALIZACIÓN PREVISTA")
	  String fechaFinalizacionPrevista();
	  
	  @DefaultStringValue("CLASE DE EQUIPO")
	  String claseDeEquipo();
	  
	  @DefaultStringValue("EQUIPO")
	  String equipo();
	
	  @DefaultStringValue("VERSIÓN")
	  String version();
	  
	  @DefaultStringValue("COMPONENTE")
	  String componente();
	  
	  @DefaultStringValue("CANTIDAD")
	  String cantidad();
	
	  @DefaultStringValue("DETALLE DEL RENGLÓN")
	  String detalleDelRenglon();
	
	  @DefaultStringValue("FECHA FINALIZACIÓN EQUIPO")
	  String fechaFinalizacionEquipo();
	  
	  @DefaultStringValue("SELECCIONAR COMPONENTE")
	  String seleccionarComponente();
	  
	  @DefaultStringValue("AGREGAR ELEMENTO")
	  String agregarElemento();
	
	  @DefaultStringValue("GUARDAR ORDEN")
	  String guardarOrden();	  

	  @DefaultStringValue("GENERAR ORDEN")
	  String generarOrden();
	  
	  @DefaultStringValue("CÓDIGO")
	  String codigo();
	
	  @DefaultStringValue("FECHA DE ENTREGA")
	  String fechaDeEntrega();	 
	  
	  @DefaultStringValue("DETALLE")
	  String detalle();	 
	  
	  @DefaultStringValue("BUSCAR POR")
	  String buscarPor();	
	  
	  @DefaultStringValue("NOMBRE CONTACTO")
	  String nombreContacto();	
	  
	  @DefaultStringValue("NUEVO")
	  String nuevo();	
	  
	  @DefaultStringValue("BUSCAR")
	  String buscar();	
	  
	  @DefaultStringValue("ORDEN DE PEDIDO:")
	  String ordenDePedido();	
	  
	  @DefaultStringValue("NUEVA")
	  String nueva();
	  
	  @DefaultStringValue("GUARDADAS")
	  String guardadas();
	  
	  @DefaultStringValue("PLANIFICADAS")
	  String planificadas();
	  
	  @DefaultStringValue("NUEVO CLIENTE")
	  String nuevoCliente();
	  
	  @DefaultStringValue("NUEVA ORDEN DE PEDIDO")
	  String nuevaOrdenDePedido();
	  
	  @DefaultStringValue("SALIR")
	  String salir();
	  
	  @DefaultStringValue("BUSCAR CLIENTE")
	  String buscarCliente();
	  
	  @DefaultStringValue("Cerrar Sesión")
	  String cerrarSesion();
	  
	  @DefaultStringValue("USUARIO")
	  String usuario();
	  
	  @DefaultStringValue("+ INFO")
	  String masInformacion();
	  
	  @DefaultStringValue("MODIFICAR")
	  String modificar();
	  
	  @DefaultStringValue("CONTRASEÑA")
	  String contrasenia();
	  
	  @DefaultStringValue("INICIAR SESIÓN")
	  String iniciarSesion();
	  
	  @DefaultStringValue("ELIMINAR CLIENTE")
	  String eliminarCliente();
	  
	  @DefaultStringValue("NRO ORDEN DE PEDIDO")
	  String nroOrdenDePedido();
	  
	  @DefaultStringValue("ESTADO")
	  String estado();
	  
	  @DefaultStringValue("FECHA GENERACIÓN")
	  String fechaGeneracion();
	  
	  @DefaultStringValue("FECHA FINALIZACIÓN")
	  String fechaFinalizacion();
	  
	  @DefaultStringValue("ACCEDER")
	  String acceder();
	  
	  @DefaultStringValue("GENERADAS")
	  String generadas();
	  
	  @DefaultStringValue("LISTA DE MATERIALES")
	  String listaDeMateriales();
	  
	  @DefaultStringValue("BUSCAR EQUIPO")
	  String buscarEquipo();
	  
	  @DefaultStringValue("VER ÓRDENES")
	  String verOrdenes();
	  
	  @DefaultStringValue("ORDEN DE FABRICACIÓN")
	  String ordenDeFabricacion();
	  
	  @DefaultStringValue("ORDEN DE SUMINISTRO")
	  String ordenDeSuministro();
	  
	  @DefaultStringValue("PROVEEDORES")
	  String proveedores();
	  
	  @DefaultStringValue("ORDEN DE COMPRA")
	  String ordenDeCompra();
	  
	  @DefaultStringValue("MATERIAL A COMPRAR")
	  String materialAComprar();
	  
	  @DefaultStringValue("INSUMO A COMPRAR")
	  String insumoAComprar();
	  
	  @DefaultStringValue("EMPLEADO")
	  String empleado();
	  
	  @DefaultStringValue("NRO LEGAJO")
	  String nroLegajo();
	  
	  @DefaultStringValue("APELLIDO")
	  String apellido();
	  
	  @DefaultStringValue("PUESTO")
	  String puesto();
	  
	  @DefaultStringValue("ROL")
	  String rol();

	 @DefaultStringValue("ACTUALIZAR TOTAL")
	  String actualizarTotal();
}

