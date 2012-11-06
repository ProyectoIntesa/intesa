package edu.shared.DTO;

public class UsuarioCompDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7567520599773803620L;
	private String nombreUsu;
	private String passUsu;
	private String nombreEmp;
	private String apellidoEmp;
	private String rolUsu;
	private int nroLegajoEmp;
	
	
	public UsuarioCompDTO() {
		
	}
	
	
	
	public String getNombreUsu() {
		return nombreUsu;
	}
	public void setNombreUsu(String nombreUsu) {
		this.nombreUsu = nombreUsu;
	}
	public String getPassUsu() {
		return passUsu;
	}
	public void setPassUsu(String passUsu) {
		this.passUsu = passUsu;
	}
	public String getNombreEmp() {
		return nombreEmp;
	}
	public void setNombreEmp(String nombreEmp) {
		this.nombreEmp = nombreEmp;
	}
	public String getApellidoEmp() {
		return apellidoEmp;
	}
	public void setApellidoEmp(String apellidoEmp) {
		this.apellidoEmp = apellidoEmp;
	}
	public String getRolUsu() {
		return rolUsu;
	}
	public void setRolUsu(String rolUsu) {
		this.rolUsu = rolUsu;
	}

	public int getNroLegajoEmp() {
		return nroLegajoEmp;
	}

	public void setNroLegajoEmp(int nroLegajoEmp) {
		this.nroLegajoEmp = nroLegajoEmp;
	}
	
	
	
	
	
	
}
