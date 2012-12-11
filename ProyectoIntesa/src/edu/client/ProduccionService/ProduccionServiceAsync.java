package edu.client.ProduccionService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;

public interface ProduccionServiceAsync {

	void getNombresInsumos(String letra, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresMarcas(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresMarcasSegunInsumo(String nombreInsumo, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getEmpleado(String nombre, String apellido, String rol, AsyncCallback<EmpleadoDTO> callback) throws IllegalArgumentException;

	void registrarOrdenProvisionInsumo(OrdenProvisionInsumoDTO orden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getNombreEstados(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getOrdenProvisionInsumo(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta, AsyncCallback<List<OrdenProvisionInsumoDTO>> callback) throws IllegalArgumentException;

	void getOrdenProvisionInsumoSegunId(long idOrden, AsyncCallback<OrdenProvisionInsumoDTO> callback) throws IllegalArgumentException;

}
