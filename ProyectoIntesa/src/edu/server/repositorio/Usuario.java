package edu.server.repositorio;
// Generated 16-oct-2012 11:52:04 by Hibernate Tools 3.4.0.CR1

/**
 * Usuario generated by hbm2java
 */
public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7441764061631158577L;
	private int idUsuario;
	private Empleado empleado;
	private String usuario;
	private String contrasenia;
	private String rol;

	public Usuario() {
	}

	public Usuario(int idUsuario, Empleado empleado, String usuario,
			String contrasenia, String rol) {
		this.idUsuario = idUsuario;
		this.empleado = empleado;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.rol = rol;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
