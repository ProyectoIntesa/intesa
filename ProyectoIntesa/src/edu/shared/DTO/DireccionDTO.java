package edu.shared.DTO;


public class DireccionDTO implements java.io.Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2287708907922730752L;
	private String localidad;
	private String calle;
	private String altura;
	private String piso;
	private String oficina;
	private String cpa;
	private String provincia;
	private String pais;
	private String codigoLocalidad;
	

	public DireccionDTO(){
		
	}
	
	
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getCpa() {
		return cpa;
	}
	public void setCpa(String cpa) {
		this.cpa = cpa;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getCodigoLocalidad() {
		return codigoLocalidad;
	}


	public void setCodigoLocalidad(String codigoLocalidad) {
		this.codigoLocalidad = codigoLocalidad;
	}
	
	
	
	
	
	
	
}
