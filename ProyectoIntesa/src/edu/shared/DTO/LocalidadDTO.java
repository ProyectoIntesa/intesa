package edu.shared.DTO;


public class LocalidadDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5946316407909090633L;
	
	private String pais;
	private String provincia;
	private String localidad;
	private String codigo;
	
	
	
	public LocalidadDTO() {
		
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
