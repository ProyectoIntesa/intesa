package edu.server.repositorio;

import java.util.HashSet;
import java.util.Set;

/**
 * Elemento generated by hbm2java
 */
public class Elemento implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8162565548035792173L;
	private ElementoId id;
	private Clase clase;
	private String nombre;
	private String unidad;
	private String caracAuxiliar;
	private Set <CodigoProducto> codigoProductos = new HashSet<CodigoProducto>(0);

	public Elemento() {
	}

	public Elemento(ElementoId id, Clase clase, String nombre, String unidad) {
		this.id = id;
		this.clase = clase;
		this.nombre = nombre;
		this.unidad = unidad;
	}

	public Elemento(ElementoId id, Clase clase, String nombre, String unidad,
			String caracAuxiliar, Set <CodigoProducto>codigoProductos) {
		this.id = id;
		this.clase = clase;
		this.nombre = nombre;
		this.unidad = unidad;
		this.caracAuxiliar = caracAuxiliar;
		this.codigoProductos = codigoProductos;
	}

	public ElementoId getId() {
		return this.id;
	}

	public void setId(ElementoId id) {
		this.id = id;
	}

	public Clase getClase() {
		return this.clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidad() {
		return this.unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getCaracAuxiliar() {
		return this.caracAuxiliar;
	}

	public void setCaracAuxiliar(String caracAuxiliar) {
		this.caracAuxiliar = caracAuxiliar;
	}

	public Set <CodigoProducto> getCodigoProductos() {
		return this.codigoProductos;
	}

	public void setCodigoProductos(Set <CodigoProducto> codigoProductos) {
		this.codigoProductos = codigoProductos;
	}

}
