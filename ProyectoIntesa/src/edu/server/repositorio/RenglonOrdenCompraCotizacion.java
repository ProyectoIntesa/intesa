package edu.server.repositorio;

import java.util.Date;

/**
 * RenglonOrdenCompraCotizacion generated by hbm2java
 */
public class RenglonOrdenCompraCotizacion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4725756356947892651L;
	private RenglonOrdenCompraCotizacionId id;
	private OrdenCompraCotizacion ordenCompraCotizacion;
	private Producto producto;
	private int td;
	private String medida1;
	private String medida2;
	private double cantidad;
	private Double precioUnitario;
	private Double pesoTotal;
	private Double subtotal;
	private Date fechaEntregaPrometida;
	private String observaciones;

	public RenglonOrdenCompraCotizacion() {
	}

	public RenglonOrdenCompraCotizacion(RenglonOrdenCompraCotizacionId id,
			OrdenCompraCotizacion ordenCompraCotizacion, Producto producto,
			int td, double cantidad, Date fechaEntregaPrometida) {
		this.id = id;
		this.ordenCompraCotizacion = ordenCompraCotizacion;
		this.producto = producto;
		this.td = td;
		this.cantidad = cantidad;
		this.fechaEntregaPrometida = fechaEntregaPrometida;
	}

	public RenglonOrdenCompraCotizacion(RenglonOrdenCompraCotizacionId id,
			OrdenCompraCotizacion ordenCompraCotizacion, Producto producto,
			int td, String medida1, String medida2, double cantidad,
			Double precioUnitario, Double pesoTotal, Double subtotal,
			Date fechaEntregaPrometida, String observaciones) {
		this.id = id;
		this.ordenCompraCotizacion = ordenCompraCotizacion;
		this.producto = producto;
		this.td = td;
		this.medida1 = medida1;
		this.medida2 = medida2;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.pesoTotal = pesoTotal;
		this.subtotal = subtotal;
		this.fechaEntregaPrometida = fechaEntregaPrometida;
		this.observaciones = observaciones;
	}

	public RenglonOrdenCompraCotizacionId getId() {
		return this.id;
	}

	public void setId(RenglonOrdenCompraCotizacionId id) {
		this.id = id;
	}

	public OrdenCompraCotizacion getOrdenCompraCotizacion() {
		return this.ordenCompraCotizacion;
	}

	public void setOrdenCompraCotizacion(
			OrdenCompraCotizacion ordenCompraCotizacion) {
		this.ordenCompraCotizacion = ordenCompraCotizacion;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getTd() {
		return this.td;
	}

	public void setTd(int td) {
		this.td = td;
	}

	public String getMedida1() {
		return this.medida1;
	}

	public void setMedida1(String medida1) {
		this.medida1 = medida1;
	}

	public String getMedida2() {
		return this.medida2;
	}

	public void setMedida2(String medida2) {
		this.medida2 = medida2;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getPesoTotal() {
		return this.pesoTotal;
	}

	public void setPesoTotal(Double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public Double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Date getFechaEntregaPrometida() {
		return this.fechaEntregaPrometida;
	}

	public void setFechaEntregaPrometida(Date fechaEntregaPrometida) {
		this.fechaEntregaPrometida = fechaEntregaPrometida;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
