package edu.shared.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class RemitoProvisionInsumoDTO implements java.io.Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2553703790163280960L;
	private long idRemitoInsumo;
	private long idOrdenProvisionInsumo;
	private String empleado;
	private String estadoOrden;
	private Date fechaEdicion;
	private Date fechaGenaracion;
	private Date fechaCierre;
	private String observaciones;
	private Set <RenglonRemitoProvisionInsumoDTO>renglonRemitoProvisionInsumos = new HashSet<RenglonRemitoProvisionInsumoDTO>(0);
	
	
	
	public RemitoProvisionInsumoDTO(){
		
	}



	public Set<RenglonRemitoProvisionInsumoDTO> getRenglonRemitoProvisionInsumos() {
		return renglonRemitoProvisionInsumos;
	}



	public void setRenglonRemitoProvisionInsumos(Set<RenglonRemitoProvisionInsumoDTO> renglonRemitoProvisionInsumos) {
		this.renglonRemitoProvisionInsumos = renglonRemitoProvisionInsumos;
	}



	public long getIdRemitoInsumo() {
		return idRemitoInsumo;
	}



	public void setIdRemitoInsumo(long idRemitoInsumo) {
		this.idRemitoInsumo = idRemitoInsumo;
	}



	public long getIdOrdenProvisionInsumo() {
		return idOrdenProvisionInsumo;
	}



	public void setIdOrdenProvisionInsumo(long idOrdenProvisionInsumo) {
		this.idOrdenProvisionInsumo = idOrdenProvisionInsumo;
	}



	public String getEmpleado() {
		return empleado;
	}



	public void setEmpleado(String empleado) {
		this.empleado = empleado;
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



	public Date getFechaGenaracion() {
		return fechaGenaracion;
	}



	public void setFechaGenaracion(Date fechaGenaracion) {
		this.fechaGenaracion = fechaGenaracion;
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







	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
public Set<RenglonRemitoProvisionInsumoDTO>  getRenglonRemitoProvisionInsumosOrdenado() {
		
		
		Set <RenglonRemitoProvisionInsumoDTO> roci = this.renglonRemitoProvisionInsumos;
		Set <RenglonRemitoProvisionInsumoDTO>renglonRemitoProvisionInsumosSalida = new TreeSet<RenglonRemitoProvisionInsumoDTO>();
		
		for (RenglonRemitoProvisionInsumoDTO renglon : roci) {	
			renglonRemitoProvisionInsumosSalida.add(renglon);
			
		}
		
		
		return renglonRemitoProvisionInsumosSalida;
	}
	
}
