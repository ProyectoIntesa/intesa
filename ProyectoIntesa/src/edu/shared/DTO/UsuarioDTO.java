package edu.shared.DTO;



public class UsuarioDTO implements java.io.Serializable {

	private static final long serialVersionUID = -724522615792528383L;
	private String nombre;
	private String rol;
	
	
	public UsuarioDTO() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
}



