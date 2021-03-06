package edu.client.AlmacenService;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RemitoExternoDTO;

@RemoteServiceRelativePath("almacen")
public interface AlmacenService extends RemoteService {
	
	double getCantFaltanteInsumo(InsumoDTO insumo, long idOrdenCompraInsumo) throws IllegalArgumentException;

	Boolean registrarRemitoExterno(RemitoExternoDTO remito) throws IllegalArgumentException;

	List<Long> getRemitosExternos(long idOrdenCompraInsumos) throws IllegalArgumentException;

	RemitoExternoDTO getRemitoExternoCompleto(OrdenCompraInsumoDTO orden, long nroRemito) throws IllegalArgumentException;

	List<OrdenProvisionInsumoDTO> getOrdenProvisionInsumo(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta) throws IllegalArgumentException;

	OrdenProvisionInsumoDTO getOrdenProvisionInsumoSegunId(long idOrden) throws IllegalArgumentException;

	Boolean getExistenciaRemitoExterno(long nroOrden, long nroRemitoEx) throws IllegalArgumentException;



}
