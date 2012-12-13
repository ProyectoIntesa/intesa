package edu.client.ProduccionService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RemitoProvisionInsumoDTO;

public interface ProduccionServiceAsync {

	void getNombresInsumos(String letra, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresMarcas(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresMarcasSegunInsumo(String nombreInsumo, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getEmpleado(String nombre, String apellido, String rol, AsyncCallback<EmpleadoDTO> callback) throws IllegalArgumentException;

	void registrarOrdenProvisionInsumo(OrdenProvisionInsumoDTO orden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getNombreEstados(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getOrdenProvisionInsumo(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta, AsyncCallback<List<OrdenProvisionInsumoDTO>> callback) throws IllegalArgumentException;

	void getOrdenProvisionInsumoSegunId(long idOrden, AsyncCallback<OrdenProvisionInsumoDTO> callback) throws IllegalArgumentException;

	void getCantFaltanteInsumo(InsumoDTO insumo, long idOrdenProvisionInsumo, AsyncCallback<Double> callback) throws IllegalArgumentException;

	void registrarRemitoProvisionInsumo(RemitoProvisionInsumoDTO remito, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void idsRemitosInternosInsumos(AsyncCallback<List<Long>> callback) throws IllegalArgumentException;

	void getOrdenRemitoInternoInsumoSegunId(Long id, AsyncCallback<RemitoProvisionInsumoDTO> callback) throws IllegalArgumentException;

	void getOrdenProvisionInsumoCompletos(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta, AsyncCallback<List<OrdenProvisionInsumoDTO>> callback) throws IllegalArgumentException;

}
