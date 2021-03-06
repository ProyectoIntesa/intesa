package edu.server.repositorio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * RemitoInternoInsumo generated by hbm2java
 */
public class RemitoInternoInsumo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6791284347850510962L;
	private long idRemitoInsumo;
	private OrdenProvisionInsumo ordenProvisionInsumo;
	private Empleado empleado;
	private EstadoOrden estadoOrden;
	private Date fechaEdicion;
	private Date fechaGenaracion;
	private Date fechaCierre;
	private String observaciones;
	private boolean recibido;
	private Set <RenglonRemitoInternoInsumo>renglonRemitoInternoInsumos = new HashSet<RenglonRemitoInternoInsumo>(0);

	public RemitoInternoInsumo() {
	}

	public RemitoInternoInsumo(long idRemitoInsumo,
			OrdenProvisionInsumo ordenProvisionInsumo, Empleado empleado,
			EstadoOrden estadoOrden, Date fechaEdicion, boolean recibido) {
		this.idRemitoInsumo = idRemitoInsumo;
		this.ordenProvisionInsumo = ordenProvisionInsumo;
		this.empleado = empleado;
		this.estadoOrden = estadoOrden;
		this.fechaEdicion = fechaEdicion;
		this.recibido = recibido;
	}

	public RemitoInternoInsumo(long idRemitoInsumo,
			OrdenProvisionInsumo ordenProvisionInsumo, Empleado empleado,
			EstadoOrden estadoOrden, Date fechaEdicion, Date fechaGenaracion,
			Date fechaCierre, String observaciones, boolean recibido,
			Set <RenglonRemitoInternoInsumo>renglonRemitoInternoInsumos) {
		this.idRemitoInsumo = idRemitoInsumo;
		this.ordenProvisionInsumo = ordenProvisionInsumo;
		this.empleado = empleado;
		this.estadoOrden = estadoOrden;
		this.fechaEdicion = fechaEdicion;
		this.fechaGenaracion = fechaGenaracion;
		this.fechaCierre = fechaCierre;
		this.observaciones = observaciones;
		this.recibido = recibido;
		this.renglonRemitoInternoInsumos = renglonRemitoInternoInsumos;
	}

	public long getIdRemitoInsumo() {
		return this.idRemitoInsumo;
	}

	public void setIdRemitoInsumo(long idRemitoInsumo) {
		this.idRemitoInsumo = idRemitoInsumo;
	}

	public OrdenProvisionInsumo getOrdenProvisionInsumo() {
		return this.ordenProvisionInsumo;
	}

	public void setOrdenProvisionInsumo(
			OrdenProvisionInsumo ordenProvisionInsumo) {
		this.ordenProvisionInsumo = ordenProvisionInsumo;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public EstadoOrden getEstadoOrden() {
		return this.estadoOrden;
	}

	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public Date getFechaEdicion() {
		return this.fechaEdicion;
	}

	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public Date getFechaGenaracion() {
		return this.fechaGenaracion;
	}

	public void setFechaGenaracion(Date fechaGenaracion) {
		this.fechaGenaracion = fechaGenaracion;
	}

	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isRecibido() {
		return this.recibido;
	}

	public void setRecibido(boolean recibido) {
		this.recibido = recibido;
	}

	public Set<RenglonRemitoInternoInsumo> getRenglonRemitoInternoInsumos() {
		return this.renglonRemitoInternoInsumos;
	}

	public void setRenglonRemitoInternoInsumos(Set <RenglonRemitoInternoInsumo>renglonRemitoInternoInsumos) {
		this.renglonRemitoInternoInsumos = renglonRemitoInternoInsumos;
	}

}
