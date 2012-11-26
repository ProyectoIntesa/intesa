package edu.shared.DTO;

import java.util.LinkedList;
import java.util.List;

public class InsumoDTO  implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6739740857986143489L;
	private String categoria;
	private String marca;
	private String nombre;
	private String observaciones;
	private int cantidad;
	private int loteCompra;
	private int stockSeguridad;
	private List<ProveedorDeInsumosDTO> proveedor;
	
	
	public InsumoDTO(){
		proveedor = new LinkedList<ProveedorDeInsumosDTO>();
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getLoteCompra() {
		return loteCompra;
	}


	public void setLoteCompra(int loteCompra) {
		this.loteCompra = loteCompra;
	}


	public int getStockSeguridad() {
		return stockSeguridad;
	}


	public void setStockSeguridad(int stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}


	public List<ProveedorDeInsumosDTO> getProveedor() {
		return proveedor;
	}


	public void setProveedor(List<ProveedorDeInsumosDTO> proveedor) {
		this.proveedor = proveedor;
	}
	
	

}