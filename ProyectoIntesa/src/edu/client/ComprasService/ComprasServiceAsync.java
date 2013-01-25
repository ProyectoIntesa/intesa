package edu.client.ComprasService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.server.repositorio.OrdenCompraInsumo;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.ProveedorDTO;
import edu.shared.DTO.RemitoExternoDTO;

public interface ComprasServiceAsync {

	void registrarNuevoProveedor(ProveedorDTO proveedor, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void registrarCambioProveedor(ProveedorDTO proveedor, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getEmpresaCompleta(String nombre, AsyncCallback<ProveedorDTO> callback) throws IllegalArgumentException;

	void getNombresEmpresas(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getRubros(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getContactos(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getEmpresasPorContacto(String nombre, AsyncCallback<List<ContactoDTO>> callback) throws IllegalArgumentException;

	void getContactoCompleto(String nombreContacto, String nombreEmpresa, AsyncCallback<ContactoDTO> callback) throws IllegalArgumentException;

	void eliminarContacto(String nombreEmpresa, String nombreContacto, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getEmpresasPorRubro(String nombre, AsyncCallback<List<ProveedorDTO>> callback) throws IllegalArgumentException;

	void eliminarEmpresa(String nombreEmpresa, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getEmpresas(String nombre, AsyncCallback<List<ProveedorDTO>> callback) throws IllegalArgumentException;

	void retornaIdContacto(String nombreEmpresa, String nombreContacto, AsyncCallback<Integer> callback) throws IllegalArgumentException;

	void modificarContacto(ContactoDTO contacto, int idContacto, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getTipos(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getEmpresasPorTipo(String nombre, AsyncCallback<List<ProveedorDTO>> callback) throws IllegalArgumentException;

	void registrarNuevoInsumo(InsumoDTO insumo, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getNombresInsumos(String letra, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresMarcas(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresCategorias(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresProveedores(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getInsumosSegunParametro(String tipo, String dato, AsyncCallback<List<InsumoDTO>> callback) throws IllegalArgumentException;

	void getInsumoCompleto(int idInsumo, String nombreInsumo, AsyncCallback<InsumoDTO> callback) throws IllegalArgumentException;

	void eliminarInsumo(InsumoDTO insumo, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void registrarCambioInsumo(InsumoDTO insumo, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getNombresMarcasSegunInsumo(String nombreInsumo, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getNombresProvSegunInsumoYMarca(String nombreInsumo, String nombreMarca, AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getRequerimientosInsumosCompletos(AsyncCallback<List<InsumoDTO>> callback) throws IllegalArgumentException;

	void completarValoresInsumos(List<InsumoDTO> insumos, String proveedor, AsyncCallback<List<InsumoDTO>> callback)throws IllegalArgumentException;

	void getModoDeEnvio(AsyncCallback<List<String>> callback)throws IllegalArgumentException;

	void registrarOrdenCompraInsumos(OrdenCompraInsumoDTO orden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getNombreEstados(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getOrdenCompraInsumo(String estado, String prov, String fecDesde, String fecHasta, AsyncCallback<List<OrdenCompraInsumoDTO>> callback) throws IllegalArgumentException;

	void getOrdenCompraInsumoSegunId(long idOrden, AsyncCallback<OrdenCompraInsumoDTO> callback) throws IllegalArgumentException;

	void cancelarOrdencompraInsumo(long idOrden, String estado, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getOrdenCompraInsumoGuardada(AsyncCallback<List<OrdenCompraInsumoDTO>> callback) throws IllegalArgumentException;

	void getOrdenCompraInsumoEnviada(AsyncCallback<List<OrdenCompraInsumoDTO>> callback) throws IllegalArgumentException;

	void getEmpresas(AsyncCallback<List<ProveedorDTO>> callback) throws IllegalArgumentException;

	void ordenDeComprasCompleta(long idOrden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void actualizarOrdenCompraInsumos(OrdenCompraInsumoDTO orden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void registrarModificacionOrdenCompraInsumos(OrdenCompraInsumoDTO orden, OrdenCompraInsumoDTO ordenVieja, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void eliminarOrdenCompraInsumos(OrdenCompraInsumoDTO orden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void cancelarOrdenCompraInsumo(List<Long> listaOrdenes, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void validarOrdenCompraInsumo(List<Long> listaOrdenes, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void estadoEntregaParcialOrdenCompraInsumo(Long nroOrden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void recibidaCompletaOrdenCompraInsumo(Long nroOrden, AsyncCallback<Boolean> callback) throws IllegalArgumentException;



	



	


}
