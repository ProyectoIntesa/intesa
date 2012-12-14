package edu.client.ProduccionService;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RemitoProvisionInsumoDTO;
import edu.shared.DTO.UsuarioCompDTO;

@RemoteServiceRelativePath("produccion")
public interface ProduccionService extends RemoteService {

	List<String> getNombresInsumos(String letra) throws IllegalArgumentException;

	List<String> getNombresMarcas() throws IllegalArgumentException;

	List<String> getNombresMarcasSegunInsumo(String nombreInsumo) throws IllegalArgumentException;

	EmpleadoDTO getEmpleado(String nombre, String apellido, String rol) throws IllegalArgumentException;

	boolean registrarOrdenProvisionInsumo(OrdenProvisionInsumoDTO orden) throws IllegalArgumentException;

	List<String> getNombreEstados() throws IllegalArgumentException;

	List<OrdenProvisionInsumoDTO> getOrdenProvisionInsumo(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta) throws IllegalArgumentException;

	OrdenProvisionInsumoDTO getOrdenProvisionInsumoSegunId(long idOrden) throws IllegalArgumentException;

	double getCantFaltanteInsumo(InsumoDTO insumo, long idOrdenProvisionInsumo) throws IllegalArgumentException;

	Boolean registrarRemitoProvisionInsumo(RemitoProvisionInsumoDTO remito) throws IllegalArgumentException;

	List<Long> idsRemitosInternosInsumos() throws IllegalArgumentException;

	RemitoProvisionInsumoDTO getOrdenRemitoInternoInsumoSegunId(Long id) throws IllegalArgumentException;

	List<OrdenProvisionInsumoDTO> getOrdenProvisionInsumoCompletos(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta) throws IllegalArgumentException;

	Boolean validarOrdenesProvisionInsumos(List<Long> listaOrdenes) throws IllegalArgumentException;

	List<Long> getRemitosInternosInsumosGenerados() throws IllegalArgumentException;

	Boolean cerrarRemitoProvisionInsumos(RemitoProvisionInsumoDTO remito, String fecha) throws IllegalArgumentException;

	double getCantInsumo(InsumoDTO insumo) throws IllegalArgumentException;

	Boolean cancelarOrdenesProvisionInsumos(List<Long> listaOrdenes) throws IllegalArgumentException;

	List<UsuarioCompDTO> getUsuariosSupervisoresYGerenteProduccion() throws IllegalArgumentException;

}
