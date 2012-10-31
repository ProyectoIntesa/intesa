package edu.client.AdministradorService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.EmpleadoDTO;

public interface AdministradorServiceAsync {

	void getEmpleados(List<EmpleadoDTO> lista,AsyncCallback<List<EmpleadoDTO>> callback)throws IllegalArgumentException;

	void usuarioExistentes(String nombreUsuario, AsyncCallback<Boolean> callback);
	
	
	
}
