package edu.client.AdministradorService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.EmpleadoDTO;

public interface AdministradorServiceAsync {

	void getEmpleados(AsyncCallback<List<EmpleadoDTO>> callback)throws IllegalArgumentException;
}