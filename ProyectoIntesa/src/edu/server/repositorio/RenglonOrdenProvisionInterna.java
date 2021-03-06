package edu.server.repositorio;

import java.util.HashSet;
import java.util.Set;

/**
 * RenglonOrdenProvisionInterna generated by hbm2java
 */
public class RenglonOrdenProvisionInterna implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2155800008191560167L;
	private RenglonOrdenProvisionInternaId id;
	private Componente componente;
	private OrdenProvisionInterna ordenProvisionInterna;
	private Producto producto;
	private int cantidad;
	private String medidaRequerida;
	private String observaciones;
	private Set <RenglonRemitoMateriales>renglonRemitoMaterialeses = new HashSet<RenglonRemitoMateriales>(0);

	public RenglonOrdenProvisionInterna() {
	}

	public RenglonOrdenProvisionInterna(RenglonOrdenProvisionInternaId id,
			OrdenProvisionInterna ordenProvisionInterna, int cantidad) {
		this.id = id;
		this.ordenProvisionInterna = ordenProvisionInterna;
		this.cantidad = cantidad;
	}

	public RenglonOrdenProvisionInterna(RenglonOrdenProvisionInternaId id,
			Componente componente, OrdenProvisionInterna ordenProvisionInterna,
			Producto producto, int cantidad, String medidaRequerida,
			String observaciones, Set <RenglonRemitoMateriales>renglonRemitoMaterialeses) {
		this.id = id;
		this.componente = componente;
		this.ordenProvisionInterna = ordenProvisionInterna;
		this.producto = producto;
		this.cantidad = cantidad;
		this.medidaRequerida = medidaRequerida;
		this.observaciones = observaciones;
		this.renglonRemitoMaterialeses = renglonRemitoMaterialeses;
	}

	public RenglonOrdenProvisionInternaId getId() {
		return this.id;
	}

	public void setId(RenglonOrdenProvisionInternaId id) {
		this.id = id;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public OrdenProvisionInterna getOrdenProvisionInterna() {
		return this.ordenProvisionInterna;
	}

	public void setOrdenProvisionInterna(
			OrdenProvisionInterna ordenProvisionInterna) {
		this.ordenProvisionInterna = ordenProvisionInterna;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getMedidaRequerida() {
		return this.medidaRequerida;
	}

	public void setMedidaRequerida(String medidaRequerida) {
		this.medidaRequerida = medidaRequerida;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Set <RenglonRemitoMateriales>getRenglonRemitoMaterialeses() {
		return this.renglonRemitoMaterialeses;
	}

	public void setRenglonRemitoMaterialeses(Set<RenglonRemitoMateriales> renglonRemitoMaterialeses) {
		this.renglonRemitoMaterialeses = renglonRemitoMaterialeses;
	}

}
