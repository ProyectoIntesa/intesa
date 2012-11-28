package edu.shared.DTO;

public class ProveedorDeInsumosDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 736054868991272637L;
	private double precio;
	private String observaciones;
	private String nombre;

	
	public ProveedorDeInsumosDTO(){
		
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
