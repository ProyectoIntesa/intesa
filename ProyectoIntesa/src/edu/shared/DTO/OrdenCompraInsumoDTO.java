package edu.shared.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrdenCompraInsumoDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3023213797220305379L;

	private long idOrden;
	private String empleado;
	private String nroOrden;
	private String proveedor;
	private String modoEnvio;
	private String estadoOrden;
	private Date fechaEdicion;
	private Date fechaGeneracion;
	private Date fechaCierre;
	private String formaPago;
	private Double iva;
	private Double total;
	private String observaciones;
	private Set <RenglonOrdenCompraInsumoDTO>renglonOrdenCompraInsumos = new HashSet<RenglonOrdenCompraInsumoDTO>(0);
	
	public OrdenCompraInsumoDTO(){
		
	}
	
	
	public long getIdOrden() {
		return idOrden;
	}


	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}


	public String getNroOrden() {
		return nroOrden;
	}


	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}


	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getModoEnvio() {
		return modoEnvio;
	}
	public void setModoEnvio(String modoEnvio) {
		this.modoEnvio = modoEnvio;
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
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Set<RenglonOrdenCompraInsumoDTO> getRenglonOrdenCompraInsumos() {
		return renglonOrdenCompraInsumos;
	}
	public void setRenglonOrdenCompraInsumos(Set<RenglonOrdenCompraInsumoDTO> renglonOrdenCompraInsumos) {
		this.renglonOrdenCompraInsumos = renglonOrdenCompraInsumos;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
