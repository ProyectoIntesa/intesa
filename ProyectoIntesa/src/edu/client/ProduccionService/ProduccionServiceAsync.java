package edu.client.ProduccionService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RemitoProvisionInsumoDTO;
import edu.shared.DTO.UsuarioCompDTO;

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

	void registrarRemitoProvisionInsumo(RemitoProvisionInsumoDTO remito, AsyncCallback<Long> callback) throws IllegalArgumentException;

	void idsRemitosInternosInsumos(AsyncCallback<List<Long>> callback) throws IllegalArgumentException;

	void getOrdenRemitoInternoInsumoSegunId(Long id, AsyncCallback<RemitoProvisionInsumoDTO> callback) throws IllegalArgumentException;

	void getOrdenProvisionInsumoCompletos(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta, AsyncCallback<List<OrdenProvisionInsumoDTO>> callback) throws IllegalArgumentException;

	void validarOrdenesProvisionInsumos(List<Long> listaOrdenes, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getRemitosInternosInsumosGenerados(AsyncCallback<List<Long>> callback) throws IllegalArgumentException;

	void cerrarRemitoProvisionInsumos(RemitoProvisionInsumoDTO remito, String fecha, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getCantInsumo(InsumoDTO insumo, AsyncCallback<Double> callback) throws IllegalArgumentException;

	void cancelarOrdenesProvisionInsumos(List<Long> listaOrdenes, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getUsuariosSupervisoresYGerenteProduccion(AsyncCallback<List<UsuarioCompDTO>> callback) throws IllegalArgumentException;

	void getRemitosInternosInsumosSegunIdOrdenProvision(long idOrden, AsyncCallback<List<Long>> callback) throws IllegalArgumentException;

	void cerrarOrdenesProvision(String fecha, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void registrarOrdenProvisionInsumoGerente(OrdenProvisionInsumoDTO orden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void registrarRemitoProvisionInsumo(RemitoProvisionInsumoDTO remito, String nada, AsyncCallback<RemitoProvisionInsumoDTO> callback) throws IllegalArgumentException;

	

}
