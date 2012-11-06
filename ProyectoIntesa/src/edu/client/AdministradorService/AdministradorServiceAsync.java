package edu.client.AdministradorService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.UsuarioCompDTO;

public interface AdministradorServiceAsync {

	void getEmpleados(List<EmpleadoDTO> lista,AsyncCallback<List<EmpleadoDTO>> callback) throws IllegalArgumentException;

	void usuarioExistentes(String nombreUsuario, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getUsuarios(List<UsuarioCompDTO> lista, AsyncCallback<List<UsuarioCompDTO>> callback) throws IllegalArgumentException;
	
	
	
}
