package edu.shared.DTO;

public class RenglonRemitoExternoDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6792970000389521044L;
	private int item;
	private InsumoDTO insumo;
	private double cantFaltante;
	private double cantIngresada;

	
	public RenglonRemitoExternoDTO(){
		
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


	public double getCantFaltante() {
		return cantFaltante;
	}


	public void setCantFaltante(double cantFaltante) {
		this.cantFaltante = cantFaltante;
	}


	public double getCantIngresada() {
		return cantIngresada;
	}


	public void setCantIngresada(double cantIngresada) {
		this.cantIngresada = cantIngresada;
	}
	
	
	
	
	
	
}
