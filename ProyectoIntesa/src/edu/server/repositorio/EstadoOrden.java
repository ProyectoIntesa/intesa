package edu.server.repositorio;

// Generated 16-oct-2012 11:52:04 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * EstadoOrden generated by hbm2java
 */
public class EstadoOrden implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8604845590863449508L;
	private int idEstadoOrden;
	private String nombre;
	private Set <RemitoMateriales>remitoMaterialeses = new HashSet<RemitoMateriales>(0);
	private Set <RemitoInternoInsumo>remitoInternoInsumos = new HashSet<RemitoInternoInsumo>(0);
	private Set <OrdenPedido> ordenPedidos = new HashSet<OrdenPedido>(0);
	private Set <OrdenFabricacionGeneral>ordenFabricacionGenerals = new HashSet<OrdenFabricacionGeneral>(0);
	private Set <OrdenProvisionInsumo>ordenProvisionInsumos = new HashSet<OrdenProvisionInsumo>(0);
	private Set <OrdenProvisionInterna>ordenProvisionInternas = new HashSet<OrdenProvisionInterna>(0);
	private Set <IngresoProductos>ingresoProductoses = new HashSet<IngresoProductos>(0);
	private Set <OrdenCompraInsumo>ordenCompraInsumos = new HashSet<OrdenCompraInsumo>(0);
	private Set <OrdenTrabajoSectorTercero>ordenTrabajoSectorTerceros = new HashSet<OrdenTrabajoSectorTercero>(0);

	public EstadoOrden() {
	}

	public EstadoOrden(int idEstadoOrden, String nombre) {
		this.idEstadoOrden = idEstadoOrden;
		this.nombre = nombre;
	}

	public EstadoOrden(int idEstadoOrden, String nombre,
			Set<RemitoMateriales> remitoMaterialeses, Set<RemitoInternoInsumo> remitoInternoInsumos, Set <OrdenPedido>ordenPedidos,
			Set <OrdenFabricacionGeneral>ordenFabricacionGenerals, Set <OrdenProvisionInsumo>ordenProvisionInsumos,
			Set <OrdenProvisionInterna>ordenProvisionInternas, Set <IngresoProductos>ingresoProductoses,
			Set <OrdenCompraInsumo>ordenCompraInsumos, Set <OrdenTrabajoSectorTercero>ordenTrabajoSectorTerceros) {
		this.idEstadoOrden = idEstadoOrden;
		this.nombre = nombre;
		this.remitoMaterialeses = remitoMaterialeses;
		this.remitoInternoInsumos = remitoInternoInsumos;
		this.ordenPedidos = ordenPedidos;
		this.ordenFabricacionGenerals = ordenFabricacionGenerals;
		this.ordenProvisionInsumos = ordenProvisionInsumos;
		this.ordenProvisionInternas = ordenProvisionInternas;
		this.ingresoProductoses = ingresoProductoses;
		this.ordenCompraInsumos = ordenCompraInsumos;
		this.ordenTrabajoSectorTerceros = ordenTrabajoSectorTerceros;
	}

	public int getIdEstadoOrden() {
		return this.idEstadoOrden;
	}

	public void setIdEstadoOrden(int idEstadoOrden) {
		this.idEstadoOrden = idEstadoOrden;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<RemitoMateriales> getRemitoMaterialeses() {
		return this.remitoMaterialeses;
	}

	public void setRemitoMaterialeses(Set<RemitoMateriales> remitoMaterialeses) {
		this.remitoMaterialeses = remitoMaterialeses;
	}

	public Set <RemitoInternoInsumo>getRemitoInternoInsumos() {
		return this.remitoInternoInsumos;
	}

	public void setRemitoInternoInsumos(Set <RemitoInternoInsumo>remitoInternoInsumos) {
		this.remitoInternoInsumos = remitoInternoInsumos;
	}

	public Set <OrdenPedido>getOrdenPedidos() {
		return this.ordenPedidos;
	}

	public void setOrdenPedidos(Set <OrdenPedido>ordenPedidos) {
		this.ordenPedidos = ordenPedidos;
	}

	public Set<OrdenFabricacionGeneral> getOrdenFabricacionGenerals() {
		return this.ordenFabricacionGenerals;
	}

	public void setOrdenFabricacionGenerals(Set<OrdenFabricacionGeneral> ordenFabricacionGenerals) {
		this.ordenFabricacionGenerals = ordenFabricacionGenerals;
	}

	public Set <OrdenProvisionInsumo>getOrdenProvisionInsumos() {
		return this.ordenProvisionInsumos;
	}

	public void setOrdenProvisionInsumos(Set <OrdenProvisionInsumo>ordenProvisionInsumos) {
		this.ordenProvisionInsumos = ordenProvisionInsumos;
	}

	public Set<OrdenProvisionInterna> getOrdenProvisionInternas() {
		return this.ordenProvisionInternas;
	}

	public void setOrdenProvisionInternas(Set<OrdenProvisionInterna> ordenProvisionInternas) {
		this.ordenProvisionInternas = ordenProvisionInternas;
	}

	public Set <IngresoProductos>getIngresoProductoses() {
		return this.ingresoProductoses;
	}

	public void setIngresoProductoses(Set <IngresoProductos>ingresoProductoses) {
		this.ingresoProductoses = ingresoProductoses;
	}

	public Set <OrdenCompraInsumo>getOrdenCompraInsumos() {
		return this.ordenCompraInsumos;
	}

	public void setOrdenCompraInsumos(Set <OrdenCompraInsumo>ordenCompraInsumos) {
		this.ordenCompraInsumos = ordenCompraInsumos;
	}

	public Set <OrdenTrabajoSectorTercero>getOrdenTrabajoSectorTerceros() {
		return this.ordenTrabajoSectorTerceros;
	}

	public void setOrdenTrabajoSectorTerceros(Set <OrdenTrabajoSectorTercero>ordenTrabajoSectorTerceros) {
		this.ordenTrabajoSectorTerceros = ordenTrabajoSectorTerceros;
	}

}
