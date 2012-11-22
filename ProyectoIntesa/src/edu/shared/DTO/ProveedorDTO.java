package edu.shared.DTO;

import java.util.LinkedList;
import java.util.List;

public class ProveedorDTO implements java.io.Serializable{
	
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 7816448383029691190L;
	private DireccionDTO direccion;
	private String nombre;
	private String cuit;
	private String responsable;
	private String rubro;
	private String telefono;
	private String mail;
	private String fax;
	private String paginaWeb;
	private String observaciones;
	private String tipoProveedor;
	//private List<OrdenPedidoDTO> ordenPedidos;
	private List<ContactoDTO> contacto;
	
	public ProveedorDTO(){
		
		contacto = new LinkedList<ContactoDTO>();
		
	}
	
	
	public String getTipoProveedor() {
		return tipoProveedor;
	}


	public void setTipoProveedor(String tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}


	public DireccionDTO getDireccion() {
		return direccion;
	}
	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getRubro() {
		return rubro;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
//	public List<OrdenPedidoDTO> getOrdenPedidos() {
//		return ordenPedidos;
//	}
//	public void setOrdenPedidos(List<OrdenPedidoDTO> ordenPedidos) {
//		this.ordenPedidos = ordenPedidos;
//	}

	public List<ContactoDTO> getContacto() {
		return contacto;
	}
	public void setContacto(List<ContactoDTO> contacto) {
		this.contacto = contacto;
	} 
	
	
	

}
