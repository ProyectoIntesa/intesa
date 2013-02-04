package edu.client.AlmacenService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.RemitoExternoDTO;

public interface AlmacenServiceAsync {

	void getCantFaltanteInsumo(InsumoDTO insumo, long idOrdenCompraInsumo, AsyncCallback<Double> callback) throws IllegalArgumentException;

	void registrarRemitoExterno(RemitoExternoDTO remito, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getRemitosExternos(long idOrdenCompraInsumos, AsyncCallback<List<Long>> callback) throws IllegalArgumentException;

	void getRemitoExternoCompleto(OrdenCompraInsumoDTO orden, long nroRemito, AsyncCallback<RemitoExternoDTO> callback) throws IllegalArgumentException;

	void getOrdenProvisionInsumo(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta, AsyncCallback<List<OrdenProvisionInsumoDTO>> callback) throws IllegalArgumentException;

	void getOrdenProvisionInsumoSegunId(long idOrden, AsyncCallback<OrdenProvisionInsumoDTO> callback) throws IllegalArgumentException;

	void getExistenciaRemitoExterno(long nroOrden, long nroRemitoEx, AsyncCallback<Boolean> callback) throws IllegalArgumentException;



}
