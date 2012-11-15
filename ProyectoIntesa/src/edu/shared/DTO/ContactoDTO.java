package edu.shared.DTO;


public class ContactoDTO implements java.io.Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1917163963435746758L;
	//private ProveedorDTO proveedor;
	private ClienteDTO cliente;
	private String nombre;
	private String cargo;
	private String telefonoEmpresa;
	private String internoEmpresa;
	private String telefonoParticular;
	private String celular;
	private String mail;
	

	public ContactoDTO() {
	}
	
	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}
	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}
	public String getInternoEmpresa() {
		return internoEmpresa;
	}
	public void setInternoEmpresa(String internoEmpresa) {
		this.internoEmpresa = internoEmpresa;
	}
	public String getTelefonoParticular() {
		return telefonoParticular;
	}
	public void setTelefonoParticular(String telefonoParticular) {
		this.telefonoParticular = telefonoParticular;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	
	
	
	
}
