package edu.server.repositorio;
// Generated 16-oct-2012 11:52:04 by Hibernate Tools 3.4.0.CR1

/**
 * ProveedorDeInsumo generated by hbm2java
 */
public class ProveedorDeInsumo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6255704346724517581L;
	private ProveedorDeInsumoId id;
	private Insumo insumo;
	private Proveedor proveedor;
	private Double precio;
	private String observaciones;

	public ProveedorDeInsumo() {
	}

	public ProveedorDeInsumo(ProveedorDeInsumoId id, Insumo insumo,
			Proveedor proveedor) {
		this.id = id;
		this.insumo = insumo;
		this.proveedor = proveedor;
	}

	public ProveedorDeInsumo(ProveedorDeInsumoId id, Insumo insumo,
			Proveedor proveedor, Double precio, String observaciones) {
		this.id = id;
		this.insumo = insumo;
		this.proveedor = proveedor;
		this.precio = precio;
		this.observaciones = observaciones;
	}

	public ProveedorDeInsumoId getId() {
		return this.id;
	}

	public void setId(ProveedorDeInsumoId id) {
		this.id = id;
	}

	public Insumo getInsumo() {
		return this.insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
