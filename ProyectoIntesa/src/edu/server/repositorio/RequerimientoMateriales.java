package edu.server.repositorio;

import java.util.Date;

/**
 * RequerimientoMateriales generated by hbm2java
 */
public class RequerimientoMateriales implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722344748182819229L;
	private long idRequerientoMateriales;
	private OrdenFabricacionGeneral ordenFabricacionGeneral;
	private Producto producto;
	private int td;
	private String medida1;
	private String medida2;
	private double cantidadRequerida;
	private Date fechaNecesaria;

	public RequerimientoMateriales() {
	}

	public RequerimientoMateriales(long idRequerientoMateriales,
			OrdenFabricacionGeneral ordenFabricacionGeneral, Producto producto,
			int td, double cantidadRequerida, Date fechaNecesaria) {
		this.idRequerientoMateriales = idRequerientoMateriales;
		this.ordenFabricacionGeneral = ordenFabricacionGeneral;
		this.producto = producto;
		this.td = td;
		this.cantidadRequerida = cantidadRequerida;
		this.fechaNecesaria = fechaNecesaria;
	}

	public RequerimientoMateriales(long idRequerientoMateriales,
			OrdenFabricacionGeneral ordenFabricacionGeneral, Producto producto,
			int td, String medida1, String medida2, double cantidadRequerida,
			Date fechaNecesaria) {
		this.idRequerientoMateriales = idRequerientoMateriales;
		this.ordenFabricacionGeneral = ordenFabricacionGeneral;
		this.producto = producto;
		this.td = td;
		this.medida1 = medida1;
		this.medida2 = medida2;
		this.cantidadRequerida = cantidadRequerida;
		this.fechaNecesaria = fechaNecesaria;
	}

	public long getIdRequerientoMateriales() {
		return this.idRequerientoMateriales;
	}

	public void setIdRequerientoMateriales(long idRequerientoMateriales) {
		this.idRequerientoMateriales = idRequerientoMateriales;
	}

	public OrdenFabricacionGeneral getOrdenFabricacionGeneral() {
		return this.ordenFabricacionGeneral;
	}

	public void setOrdenFabricacionGeneral(
			OrdenFabricacionGeneral ordenFabricacionGeneral) {
		this.ordenFabricacionGeneral = ordenFabricacionGeneral;
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

	public double getCantidadRequerida() {
		return this.cantidadRequerida;
	}

	public void setCantidadRequerida(double cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}

	public Date getFechaNecesaria() {
		return this.fechaNecesaria;
	}

	public void setFechaNecesaria(Date fechaNecesaria) {
		this.fechaNecesaria = fechaNecesaria;
	}

}
