package edu.client.AdministradorService;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.EmpleadoDTO;

@RemoteServiceRelativePath("administrador")
public interface AdministradorService extends RemoteService {
	List<EmpleadoDTO> getEmpleados(List<EmpleadoDTO> lista) throws IllegalArgumentException;
	Boolean usuarioExistentes(String nombreUsuario) throws IllegalArgumentException;
}



