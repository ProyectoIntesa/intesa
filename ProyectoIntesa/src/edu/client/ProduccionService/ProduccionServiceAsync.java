package edu.client.ProduccionService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.EmpleadoDTO;

public interface ProduccionServiceAsync {

	void getNombresInsumos(String letra, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresMarcas(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresMarcasSegunInsumo(String nombreInsumo, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getEmpleado(String nombre, String apellido, String rol, AsyncCallback<EmpleadoDTO> callback) throws IllegalArgumentException;

}
