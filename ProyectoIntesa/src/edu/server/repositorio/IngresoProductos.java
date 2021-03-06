package edu.server.repositorio;

import java.util.Date;

/**
 * IngresoProductos generated by hbm2java
 */
public class IngresoProductos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7068820292774778136L;
	private long idIngreso;
	private EstadoOrden estadoOrden;
	private Empleado empleado;
	private OrdenCompraCotizacion ordenCompraCotizacion;
	private String nroRemitoProveedor;
	private Date fechaIngreso;
	private String observaciones;

	public IngresoProductos() {
	}

	public IngresoProductos(long idIngreso, EstadoOrden estadoOrden,
			Empleado empleado, OrdenCompraCotizacion ordenCompraCotizacion,
			String nroRemitoProveedor, Date fechaIngreso) {
		this.idIngreso = idIngreso;
		this.estadoOrden = estadoOrden;
		this.empleado = empleado;
		this.ordenCompraCotizacion = ordenCompraCotizacion;
		this.nroRemitoProveedor = nroRemitoProveedor;
		this.fechaIngreso = fechaIngreso;
	}

	public IngresoProductos(long idIngreso, EstadoOrden estadoOrden,
			Empleado empleado, OrdenCompraCotizacion ordenCompraCotizacion,
			String nroRemitoProveedor, Date fechaIngreso, String observaciones) {
		this.idIngreso = idIngreso;
		this.estadoOrden = estadoOrden;
		this.empleado = empleado;
		this.ordenCompraCotizacion = ordenCompraCotizacion;
		this.nroRemitoProveedor = nroRemitoProveedor;
		this.fechaIngreso = fechaIngreso;
		this.observaciones = observaciones;
	}

	public long getIdIngreso() {
		return this.idIngreso;
	}

	public void setIdIngreso(long idIngreso) {
		this.idIngreso = idIngreso;
	}

	public EstadoOrden getEstadoOrden() {
		return this.estadoOrden;
	}

	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public OrdenCompraCotizacion getOrdenCompraCotizacion() {
		return this.ordenCompraCotizacion;
	}

	public void setOrdenCompraCotizacion(
			OrdenCompraCotizacion ordenCompraCotizacion) {
		this.ordenCompraCotizacion = ordenCompraCotizacion;
	}

	public String getNroRemitoProveedor() {
		return this.nroRemitoProveedor;
	}

	public void setNroRemitoProveedor(String nroRemitoProveedor) {
		this.nroRemitoProveedor = nroRemitoProveedor;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
