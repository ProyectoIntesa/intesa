package edu.shared.DTO;

import java.util.LinkedList;
import java.util.List;

public class EmpleadoDTO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8213969407831803944L;
	private int nroLegajo;
	private int idEmpleado;
	private String nombre;
	private String apellido;
	private String puesto;
	private List<EmpleadoDTO> listaEmpACargo; 	
	
	public EmpleadoDTO() {
		
		listaEmpACargo = new LinkedList<EmpleadoDTO>();
		
	}
	
	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getNroLegajo() {
		return nroLegajo;
	}
	public void setNroLegajo(int nroLegajo) {
		this.nroLegajo = nroLegajo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public List<EmpleadoDTO> getListaEmpACargo() {
		return listaEmpACargo;
	}
	public void setListaEmpACargo(List<EmpleadoDTO> listaEmpACargo) {
		this.listaEmpACargo = listaEmpACargo;
	}

}
