package edu.client.AdministradorService;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.UsuarioCompDTO;

@RemoteServiceRelativePath("administrador")
public interface AdministradorService extends RemoteService {
	List<EmpleadoDTO> getEmpleados(List<EmpleadoDTO> lista) throws IllegalArgumentException;
	Boolean usuarioExistentes(String nombreUsuario) throws IllegalArgumentException;
	List<UsuarioCompDTO> getUsuarios(List<UsuarioCompDTO> lista) throws IllegalArgumentException;
	Boolean guardarUsuario(UsuarioCompDTO usuario) throws IllegalArgumentException;
	Boolean usuarioTieneEmp(int legajo) throws IllegalArgumentException;
	Boolean eliminarUsuario(String nombreUsu) throws IllegalArgumentException;
	Boolean modificarUsuario(String nombreUsu, String passUsu) throws IllegalArgumentException;
	Boolean guardarEmpleado(EmpleadoDTO emp) throws IllegalArgumentException;
}

