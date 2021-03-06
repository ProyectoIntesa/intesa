package edu.server.repositorio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OrdenCompraCotizacion generated by hbm2java
 */
public class OrdenCompraCotizacion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958496824676541568L;
	private long idOrden;
	private Proveedor proveedor;
	private ModoEnvio modoEnvio;
	private Empleado empleado;
	private boolean ordenCotizacion;
	private long nroOrden;
	private String nroPresupuesto;
	private int idEstadoOrden;
	private Date fechaEdicion;
	private Date fechaGeneracion;
	private Date fechaCierre;
	private Integer referente;
	private boolean dolar;
	private Double cambioDolar;
	private Double bonificacion;
	private double iva;
	private String observaciones;
	private String formaPago;
	private Double total;
	private Set <IngresoProductos>ingresoProductoses = new HashSet<IngresoProductos>(0);
	private Set <OrdenFabricacionGeneral>ordenFabricacionGenerals = new HashSet<OrdenFabricacionGeneral>(0);
	private Set <RenglonOrdenCompraCotizacion>renglonOrdenCompraCotizacions = new HashSet<RenglonOrdenCompraCotizacion>(0);

	public OrdenCompraCotizacion() {
	}

	public OrdenCompraCotizacion(long idOrden, Proveedor proveedor,
			Empleado empleado, boolean ordenCotizacion, long nroOrden,
			int idEstadoOrden, Date fechaEdicion, boolean dolar, double iva,
			String formaPago) {
		this.idOrden = idOrden;
		this.proveedor = proveedor;
		this.empleado = empleado;
		this.ordenCotizacion = ordenCotizacion;
		this.nroOrden = nroOrden;
		this.idEstadoOrden = idEstadoOrden;
		this.fechaEdicion = fechaEdicion;
		this.dolar = dolar;
		this.iva = iva;
		this.formaPago = formaPago;
	}

	public OrdenCompraCotizacion(long idOrden, Proveedor proveedor,
			ModoEnvio modoEnvio, Empleado empleado, boolean ordenCotizacion,
			long nroOrden, String nroPresupuesto, int idEstadoOrden,
			Date fechaEdicion, Date fechaGeneracion, Date fechaCierre,
			Integer referente, boolean dolar, Double cambioDolar,
			Double bonificacion, double iva, String observaciones,
			String formaPago, Double total, Set<IngresoProductos> ingresoProductoses,
			Set<OrdenFabricacionGeneral> ordenFabricacionGenerals, Set<RenglonOrdenCompraCotizacion> renglonOrdenCompraCotizacions) {
		this.idOrden = idOrden;
		this.proveedor = proveedor;
		this.modoEnvio = modoEnvio;
		this.empleado = empleado;
		this.ordenCotizacion = ordenCotizacion;
		this.nroOrden = nroOrden;
		this.nroPresupuesto = nroPresupuesto;
		this.idEstadoOrden = idEstadoOrden;
		this.fechaEdicion = fechaEdicion;
		this.fechaGeneracion = fechaGeneracion;
		this.fechaCierre = fechaCierre;
		this.referente = referente;
		this.dolar = dolar;
		this.cambioDolar = cambioDolar;
		this.bonificacion = bonificacion;
		this.iva = iva;
		this.observaciones = observaciones;
		this.formaPago = formaPago;
		this.total = total;
		this.ingresoProductoses = ingresoProductoses;
		this.ordenFabricacionGenerals = ordenFabricacionGenerals;
		this.renglonOrdenCompraCotizacions = renglonOrdenCompraCotizacions;
	}

	public long getIdOrden() {
		return this.idOrden;
	}

	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public ModoEnvio getModoEnvio() {
		return this.modoEnvio;
	}

	public void setModoEnvio(ModoEnvio modoEnvio) {
		this.modoEnvio = modoEnvio;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public boolean isOrdenCotizacion() {
		return this.ordenCotizacion;
	}

	public void setOrdenCotizacion(boolean ordenCotizacion) {
		this.ordenCotizacion = ordenCotizacion;
	}

	public long getNroOrden() {
		return this.nroOrden;
	}

	public void setNroOrden(long nroOrden) {
		this.nroOrden = nroOrden;
	}

	public String getNroPresupuesto() {
		return this.nroPresupuesto;
	}

	public void setNroPresupuesto(String nroPresupuesto) {
		this.nroPresupuesto = nroPresupuesto;
	}

	public int getIdEstadoOrden() {
		return this.idEstadoOrden;
	}

	public void setIdEstadoOrden(int idEstadoOrden) {
		this.idEstadoOrden = idEstadoOrden;
	}

	public Date getFechaEdicion() {
		return this.fechaEdicion;
	}

	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public Date getFechaGeneracion() {
		return this.fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Integer getReferente() {
		return this.referente;
	}

	public void setReferente(Integer referente) {
		this.referente = referente;
	}

	public boolean isDolar() {
		return this.dolar;
	}

	public void setDolar(boolean dolar) {
		this.dolar = dolar;
	}

	public Double getCambioDolar() {
		return this.cambioDolar;
	}

	public void setCambioDolar(Double cambioDolar) {
		this.cambioDolar = cambioDolar;
	}

	public Double getBonificacion() {
		return this.bonificacion;
	}

	public void setBonificacion(Double bonificacion) {
		this.bonificacion = bonificacion;
	}

	public double getIva() {
		return this.iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Set<IngresoProductos> getIngresoProductoses() {
		return this.ingresoProductoses;
	}

	public void setIngresoProductoses(Set<IngresoProductos>ingresoProductoses) {
		this.ingresoProductoses = ingresoProductoses;
	}

	public Set<OrdenFabricacionGeneral> getOrdenFabricacionGenerals() {
		return this.ordenFabricacionGenerals;
	}

	public void setOrdenFabricacionGenerals(Set<OrdenFabricacionGeneral> ordenFabricacionGenerals) {
		this.ordenFabricacionGenerals = ordenFabricacionGenerals;
	}

	public Set<RenglonOrdenCompraCotizacion> getRenglonOrdenCompraCotizacions() {
		return this.renglonOrdenCompraCotizacions;
	}

	public void setRenglonOrdenCompraCotizacions(
			Set <RenglonOrdenCompraCotizacion>renglonOrdenCompraCotizacions) {
		this.renglonOrdenCompraCotizacions = renglonOrdenCompraCotizacions;
	}

}
