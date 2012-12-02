package edu.shared.DTO;


public class RenglonOrdenCompraInsumoDTO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7562421235565134440L;
	private int item;
	private InsumoDTO insumo;
	private double cantidad;
	private double precio;
	private Double subtotal;
	
	
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public InsumoDTO getInsumo() {
		return insumo;
	}
	public void setInsumo(InsumoDTO insumo) {
		this.insumo = insumo;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
