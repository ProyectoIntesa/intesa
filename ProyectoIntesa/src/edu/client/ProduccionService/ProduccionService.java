package edu.client.ProduccionService;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.EmpleadoDTO;

@RemoteServiceRelativePath("produccion")
public interface ProduccionService extends RemoteService {

	List<String> getNombresInsumos(String letra) throws IllegalArgumentException;

	List<String> getNombresMarcas() throws IllegalArgumentException;

	List<String> getNombresMarcasSegunInsumo(String nombreInsumo) throws IllegalArgumentException;

	EmpleadoDTO getEmpleado(String nombre, String apellido, String rol) throws IllegalArgumentException;

}
