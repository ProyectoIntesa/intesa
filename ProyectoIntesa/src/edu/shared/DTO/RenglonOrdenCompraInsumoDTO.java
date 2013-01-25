package edu.shared.DTO;


public class RenglonOrdenCompraInsumoDTO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7562421235565134440L;
	private int item;
	private InsumoDTO insumo;
	private Double cantidad;
	private Double precio;
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
	public Double getCantidad() {
		if(cantidad == null)
			return (Double)null;
		else
			return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public void setCantidad() {
		this.cantidad = null;
	}
	public Double getSubtotal() {
		if(subtotal == null)
			return (Double)null;
		else
			return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public void setSubtotal() {
		this.subtotal = null;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
}
