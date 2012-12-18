package edu.shared.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrdenProvisionInsumoDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6918289593818843107L;
	private long idOrdenProvisionInsumo;
	private EmpleadoDTO empleadoByIdPedidoPara;
	private EmpleadoDTO empleadoByIdPedidoPor;
	private String estadoOrden;
	private Date fechaEdicion;
	private Date fechaGeneracion;
	private Date fechaCierre;
	private String observaciones;
	private Set <RemitoProvisionInsumoDTO>remitoProvisionInsumos = new HashSet<RemitoProvisionInsumoDTO>(0);
	private Set <RenglonOrdenProvisionInsumoDTO>renglonOrdenProvisionInsumos = new HashSet<RenglonOrdenProvisionInsumoDTO>(0);

	public OrdenProvisionInsumoDTO(){
		
	}

	public long getIdOrdenProvisionInsumo() {
		return idOrdenProvisionInsumo;
	}

	public void setIdOrdenProvisionInsumo(long idOrdenProvisionInsumo) {
		this.idOrdenProvisionInsumo = idOrdenProvisionInsumo;
	}

	public EmpleadoDTO getEmpleadoByIdPedidoPara() {
		return empleadoByIdPedidoPara;
	}

	public void setEmpleadoByIdPedidoPara(EmpleadoDTO empleadoByIdPedidoPara) {
		this.empleadoByIdPedidoPara = empleadoByIdPedidoPara;
	}

	public EmpleadoDTO getEmpleadoByIdPedidoPor() {
		return empleadoByIdPedidoPor;
	}

	public void setEmpleadoByIdPedidoPor(EmpleadoDTO empleadoByIdPedidoPor) {
		this.empleadoByIdPedidoPor = empleadoByIdPedidoPor;
	}

	public String getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public Date getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Set<RenglonOrdenProvisionInsumoDTO> getRenglonOrdenProvisionInsumos() {
		return renglonOrdenProvisionInsumos;
	}

	public void setRenglonOrdenProvisionInsumos(Set<RenglonOrdenProvisionInsumoDTO> renglonOrdenProvisionInsumos) {
		this.renglonOrdenProvisionInsumos = renglonOrdenProvisionInsumos;
	}
	
	
	

}
