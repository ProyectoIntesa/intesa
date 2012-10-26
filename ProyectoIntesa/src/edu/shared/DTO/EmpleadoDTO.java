package edu.shared.DTO;

public class EmpleadoDTO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8213969407831803944L;
	private int nroLegajo;
	private String nombre;
	private String apellido;
	private String puesto;
		
	
	public EmpleadoDTO() {
		
	}
	public int getNroLegajo() {
		return nroLegajo;
	}
	public void setNroLegajo(int nroLegajo) {
		this.nroLegajo = nroLegajo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

}
