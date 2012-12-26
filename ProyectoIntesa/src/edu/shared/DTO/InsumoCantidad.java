package edu.shared.DTO;

public class InsumoCantidad implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8343646520504203901L;
	private int idInsumo;
	private double cantidad;
	
	public InsumoCantidad(){
		
	}
	
	public int getIdInsumo() {
		return idInsumo;
	}
	public void setIdInsumo(int idInsumo) {
		this.idInsumo = idInsumo;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	
}
