package edu.server.servicio;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.ProduccionService.ProduccionService;
import edu.server.dominio.Insumos;
import edu.server.repositorio.Empleado;
import edu.shared.DTO.EmpleadoDTO;

public class ProduccionServiceImpl extends RemoteServiceServlet implements ProduccionService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5391111874855282111L;

	@Override
	public List<String> getNombresInsumos(String letra) throws IllegalArgumentException {
		
		Insumos adminInsumo = new Insumos();
		
		return adminInsumo.getNombresInsumos(letra);
		
	}
	
	@Override
	public List<String> getNombresMarcas() throws IllegalArgumentException {
		
		Insumos adminInsumo = new Insumos();
		
		return adminInsumo.getNombresMarcas();
		
	}
	
	@Override
	public List<String> getNombresMarcasSegunInsumo(String nombreInsumo) throws IllegalArgumentException {
		
		Insumos adminInsumos = new Insumos();
		
		return adminInsumos.getNombresMarcasSegunInsumo(nombreInsumo);
	}
	
	
	@Override
	public EmpleadoDTO getEmpleado(String nombre, String apellido, String rol) throws IllegalArgumentException {
		
		edu.server.dominio.Empleado adminEmpleado = new edu.server.dominio.Empleado(); 
		Empleado emp;
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		int idEmpleado = adminEmpleado.getIdEmpleado(nombre, apellido, rol);
		
		emp = adminEmpleado.getEmpleado(idEmpleado);
		
		empleadoDTO.setApellido(emp.getApellido());
		empleadoDTO.setNombre(emp.getNombre());
		empleadoDTO.setIdEmpleado(emp.getIdEmpleado());
		
		for (Empleado emple : emp.getEmpleadosForEmpleado()) {
		
			EmpleadoDTO aux = new EmpleadoDTO();
			aux.setIdEmpleado(emple.getIdEmpleado());
			aux.setApellido(emple.getApellido());
			aux.setNombre(emple.getNombre());
			
			empleadoDTO.getListaEmpACargo().add(aux);
			
		}

		return empleadoDTO;
	}
}
