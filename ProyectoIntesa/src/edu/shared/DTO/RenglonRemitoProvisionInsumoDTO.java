package edu.shared.DTO;

import edu.server.repositorio.Insumo;
import edu.server.repositorio.RemitoInternoInsumo;
import edu.server.repositorio.RenglonRemitoInternoInsumoId;

public class RenglonRemitoProvisionInsumoDTO implements java.io.Serializable, Comparable<RenglonRemitoProvisionInsumoDTO>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2419816992797023399L;
	private int item;
	private InsumoDTO insumo;
	private double cantidadEntregada;
	
	
	
	public RenglonRemitoProvisionInsumoDTO(){
		
	}



	public int getItem() {
		return item;
	}



	public void setItem(int item) {
		this.item = item;
	}



	public InsumoDTO getInsumo() {
		return insumo;
	}



	public void setInsumo(InsumoDTO insumo) {
		this.insumo = insumo;
	}



	public double getCantidadEntregada() {
		return cantidadEntregada;
	}



	public void setCantidadEntregada(double cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}



	@Override
	public int compareTo(RenglonRemitoProvisionInsumoDTO o) {
		if(this.item == o.item)
			return 0;
		else if(this.item < o.item)
			return -1;
		else
			return 1;
	}
	
	
}
