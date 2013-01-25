package edu.shared.DTO;

public class ProveedorDeInsumosDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 736054868991272637L;
	private Double precio;
	private String observaciones;
	private String nombre;

	
	public ProveedorDeInsumosDTO(){
		
	}

	public Double getPrecio() {
		
		if(precio == null)
			return (Double)null;
		else
			return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public void setPrecio() {
		this.precio = null;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
	
}
