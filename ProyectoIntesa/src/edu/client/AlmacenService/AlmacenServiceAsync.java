package edu.client.AlmacenService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.RemitoExternoDTO;

public interface AlmacenServiceAsync {

	void getCantFaltanteInsumo(InsumoDTO insumo, long idOrdenCompraInsumo, AsyncCallback<Double> callback) throws IllegalArgumentException;

	void registrarRemitoExterno(RemitoExternoDTO remito, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getRemitosExternos(long idOrdenCompraInsumos, AsyncCallback<List<Long>> callback) throws IllegalArgumentException;

	void getRemitoExternoCompleto(OrdenCompraInsumoDTO orden, long nroRemito, AsyncCallback<RemitoExternoDTO> callback) throws IllegalArgumentException;

}
