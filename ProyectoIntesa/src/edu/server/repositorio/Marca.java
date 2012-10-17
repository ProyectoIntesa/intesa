package edu.server.repositorio;

// Generated 16-oct-2012 11:52:04 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Marca generated by hbm2java
 */
public class Marca implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2946967869509851223L;
	private int idMarca;
	private String nombre;
	private Set<Insumo> insumos = new HashSet<Insumo>(0);

	public Marca() {
	}

	public Marca(int idMarca, String nombre) {
		this.idMarca = idMarca;
		this.nombre = nombre;
	}

	public Marca(int idMarca, String nombre, Set<Insumo> insumos) {
		this.idMarca = idMarca;
		this.nombre = nombre;
		this.insumos = insumos;
	}

	public int getIdMarca() {
		return this.idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Insumo> getInsumos() {
		return this.insumos;
	}

	public void setInsumos(Set<Insumo> insumos) {
		this.insumos = insumos;
	}

}
