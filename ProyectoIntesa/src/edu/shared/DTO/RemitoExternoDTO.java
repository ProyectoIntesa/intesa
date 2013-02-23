package edu.shared.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class RemitoExternoDTO implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8283704616124392807L;
	private long idOrdenCompra;
	private long idRemitoEx;
	private String empleado;
	private Date fechaIngreso;
	private String observaciones;
	private Set <RenglonRemitoExternoDTO>renglonRemitoExterno = new HashSet<RenglonRemitoExternoDTO>(0);
	
	public RemitoExternoDTO(){
		
	}

	public long getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(long idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	public long getIdRemitoEx() {
		return idRemitoEx;
	}

	public void setIdRemitoEx(long idRemitoEx) {
		this.idRemitoEx = idRemitoEx;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Set<RenglonRemitoExternoDTO> getRenglonRemitoExterno() {
		return renglonRemitoExterno;
	}

	public void setRenglonRemitoExterno(Set<RenglonRemitoExternoDTO> renglonRemitoExterno) {
		this.renglonRemitoExterno = renglonRemitoExterno;
	}
	
	
	public Set<RenglonRemitoExternoDTO> getRenglonRemitoExternoOrdenado() {
		
		
		Set <RenglonRemitoExternoDTO> roci = this.renglonRemitoExterno;
		Set <RenglonRemitoExternoDTO>renglonRemitoExternoSalida = new TreeSet<RenglonRemitoExternoDTO>();
		
		for (RenglonRemitoExternoDTO renglon : roci) {	
			renglonRemitoExternoSalida.add(renglon);
			
		}
		
		
		return renglonRemitoExternoSalida;
	}
	
	
	
}
