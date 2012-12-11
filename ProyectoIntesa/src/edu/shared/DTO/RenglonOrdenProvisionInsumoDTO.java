package edu.shared.DTO;


public class RenglonOrdenProvisionInsumoDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3815094009626906998L;
	private int idRenglon;
	private OrdenProvisionInsumoDTO ordenProvisionInsumo;
	private InsumoDTO insumo;
	private double cantidadRequerida;
	
	
	public RenglonOrdenProvisionInsumoDTO(){
	}
	
	public int getIdRenglon() {
		return idRenglon;
	}
	public void setIdRenglon(int idRenglon) {
		this.idRenglon = idRenglon;
	}
	public OrdenProvisionInsumoDTO getOrdenProvisionInsumo() {
		return ordenProvisionInsumo;
	}
	public void setOrdenProvisionInsumo(OrdenProvisionInsumoDTO ordenProvisionInsumo) {
		this.ordenProvisionInsumo = ordenProvisionInsumo;
	}
	public InsumoDTO getInsumo() {
		return insumo;
	}
	public void setInsumo(InsumoDTO insumo) {
		this.insumo = insumo;
	}
	public double getCantidadRequerida() {
		return cantidadRequerida;
	}
	public void setCantidadRequerida(double cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}
	
	
}
