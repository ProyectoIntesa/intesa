package edu.shared.DTO;


public class RenglonOrdenProvisionInsumoDTO implements java.io.Serializable, Comparable<RenglonOrdenProvisionInsumoDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3815094009626906998L;
	private int idRenglon;
	private OrdenProvisionInsumoDTO ordenProvisionInsumo;
	private InsumoDTO insumo;
	private Double cantidadRequerida;
	
	
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
	public Double getCantidadRequerida() {
		return cantidadRequerida;
	}
	public void setCantidadRequerida(Double cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}

	@Override
	public int compareTo(RenglonOrdenProvisionInsumoDTO o) {
		if(this.idRenglon == o.idRenglon)
			return 0;
		else if(this.idRenglon < o.idRenglon)
			return -1;
		else
			return 1;
	}
	
	
}
