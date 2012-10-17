package edu.server.repositorio;
// Generated 16-oct-2012 11:52:04 by Hibernate Tools 3.4.0.CR1

/**
 * ProveedorDeProducto generated by hbm2java
 */
public class ProveedorDeProducto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1631669978693589174L;
	private ProveedorDeProductoId id;
	private Proveedor proveedor;
	private double precio;
	private String observaciones;

	public ProveedorDeProducto() {
	}

	public ProveedorDeProducto(ProveedorDeProductoId id, Proveedor proveedor,
			double precio) {
		this.id = id;
		this.proveedor = proveedor;
		this.precio = precio;
	}

	public ProveedorDeProducto(ProveedorDeProductoId id, Proveedor proveedor,
			double precio, String observaciones) {
		this.id = id;
		this.proveedor = proveedor;
		this.precio = precio;
		this.observaciones = observaciones;
	}

	public ProveedorDeProductoId getId() {
		return this.id;
	}

	public void setId(ProveedorDeProductoId id) {
		this.id = id;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
