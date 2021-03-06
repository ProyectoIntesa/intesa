package edu.server.repositorio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OrdenProvisionInterna generated by hbm2java
 */
public class OrdenProvisionInterna implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8954435115111374811L;
	private long nroOrden;
	private Empleado empleado;
	private RenglonOrdenTrabajo renglonOrdenTrabajo;
	private EstadoOrden estadoOrden;
	private Date fechaGeneracion;
	private Date fechaCierre;
	private String observaciones;
	private Set <RenglonOrdenProvisionInterna>renglonOrdenProvisionInternas = new HashSet<RenglonOrdenProvisionInterna>(0);
	private Set <RemitoMateriales>remitoMaterialeses = new HashSet<RemitoMateriales>(0);

	public OrdenProvisionInterna() {
	}

	public OrdenProvisionInterna(long nroOrden, Empleado empleado,
			RenglonOrdenTrabajo renglonOrdenTrabajo, EstadoOrden estadoOrden,
			Date fechaGeneracion) {
		this.nroOrden = nroOrden;
		this.empleado = empleado;
		this.renglonOrdenTrabajo = renglonOrdenTrabajo;
		this.estadoOrden = estadoOrden;
		this.fechaGeneracion = fechaGeneracion;
	}

	public OrdenProvisionInterna(long nroOrden, Empleado empleado,
			RenglonOrdenTrabajo renglonOrdenTrabajo, EstadoOrden estadoOrden,
			Date fechaGeneracion, Date fechaCierre, String observaciones,
			Set <RenglonOrdenProvisionInterna>renglonOrdenProvisionInternas, Set<RemitoMateriales> remitoMaterialeses) {
		this.nroOrden = nroOrden;
		this.empleado = empleado;
		this.renglonOrdenTrabajo = renglonOrdenTrabajo;
		this.estadoOrden = estadoOrden;
		this.fechaGeneracion = fechaGeneracion;
		this.fechaCierre = fechaCierre;
		this.observaciones = observaciones;
		this.renglonOrdenProvisionInternas = renglonOrdenProvisionInternas;
		this.remitoMaterialeses = remitoMaterialeses;
	}

	public long getNroOrden() {
		return this.nroOrden;
	}

	public void setNroOrden(long nroOrden) {
		this.nroOrden = nroOrden;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public RenglonOrdenTrabajo getRenglonOrdenTrabajo() {
		return this.renglonOrdenTrabajo;
	}

	public void setRenglonOrdenTrabajo(RenglonOrdenTrabajo renglonOrdenTrabajo) {
		this.renglonOrdenTrabajo = renglonOrdenTrabajo;
	}

	public EstadoOrden getEstadoOrden() {
		return this.estadoOrden;
	}

	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
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

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Set <RenglonOrdenProvisionInterna>getRenglonOrdenProvisionInternas() {
		return this.renglonOrdenProvisionInternas;
	}

	public void setRenglonOrdenProvisionInternas(
			Set<RenglonOrdenProvisionInterna> renglonOrdenProvisionInternas) {
		this.renglonOrdenProvisionInternas = renglonOrdenProvisionInternas;
	}

	public Set<RemitoMateriales> getRemitoMaterialeses() {
		return this.remitoMaterialeses;
	}

	public void setRemitoMaterialeses(Set <RemitoMateriales>remitoMaterialeses) {
		this.remitoMaterialeses = remitoMaterialeses;
	}

}
