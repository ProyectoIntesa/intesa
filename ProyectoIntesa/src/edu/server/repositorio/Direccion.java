package edu.server.repositorio;

// Generated 16-oct-2012 11:52:04 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Direccion generated by hbm2java
 */
public class Direccion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2733690693569365488L;
	private int idDireccion;
	private Localidad localidad;
	private String calle;
	private String altura;
	private String piso;
	private String oficina;
	private String cpa;
	private Set <Cliente> clientes = new HashSet<Cliente>(0);
	private Set <Proveedor> proveedors = new HashSet<Proveedor>(0);

	public Direccion() {
	}

	public String getCpa() {
		return cpa;
	}

	public void setCpa(String cpa) {
		this.cpa = cpa;
	}

	public Direccion(int idDireccion, Localidad localidad, String calle,
			String altura, String cpa) {
		this.idDireccion = idDireccion;
		this.localidad = localidad;
		this.calle = calle;
		this.altura = altura;
		this.cpa = cpa;
	}

	public Direccion(int idDireccion, Localidad localidad, String calle,
			String altura, String piso, String oficina, String cpa, Set<Cliente> clientes,
			Set <Proveedor>proveedors) {
		this.idDireccion = idDireccion;
		this.localidad = localidad;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.oficina = oficina;
		this.cpa = cpa;
		this.clientes = clientes;
		this.proveedors = proveedors;
	}

	public int getIdDireccion() {
		return this.idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return this.altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return this.piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getOficina() {
		return this.oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public Set<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Set<Proveedor> getProveedors() {
		return this.proveedors;
	}

	public void setProveedors(Set<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}

}
