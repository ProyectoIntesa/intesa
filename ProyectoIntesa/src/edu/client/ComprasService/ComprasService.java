package edu.client.ComprasService;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.server.repositorio.OrdenCompraInsumo;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.ProveedorDTO;
import edu.shared.DTO.RemitoExternoDTO;

@RemoteServiceRelativePath("compras")
public interface ComprasService extends RemoteService {

	Boolean registrarNuevoProveedor(ProveedorDTO proveedor) throws IllegalArgumentException;

	Boolean registrarCambioProveedor(ProveedorDTO proveedor) throws IllegalArgumentException;

	ProveedorDTO getEmpresaCompleta(String nombre) throws IllegalArgumentException;

	List<String> getNombresEmpresas() throws IllegalArgumentException;

	List<String> getRubros() throws IllegalArgumentException;

	List<String> getContactos() throws IllegalArgumentException;

	List<ContactoDTO> getEmpresasPorContacto(String nombre) throws IllegalArgumentException;

	ContactoDTO getContactoCompleto(String nombreContacto, String nombreEmpresa) throws IllegalArgumentException;

	Boolean eliminarContacto(String nombreEmpresa, String nombreContacto) throws IllegalArgumentException;

	List<ProveedorDTO> getEmpresasPorRubro(String nombre) throws IllegalArgumentException;

	Boolean eliminarEmpresa(String nombreEmpresa) throws IllegalArgumentException;

	List<ProveedorDTO> getEmpresas(String nombre) throws IllegalArgumentException;

	int retornaIdContacto(String nombreEmpresa, String nombreContacto) throws IllegalArgumentException;

	Boolean modificarContacto(ContactoDTO contacto, int idContacto) throws IllegalArgumentException;

	List<String> getTipos() throws IllegalArgumentException;

	List<ProveedorDTO> getEmpresasPorTipo(String nombre) throws IllegalArgumentException;

	Boolean registrarNuevoInsumo(InsumoDTO insumo) throws IllegalArgumentException;

	List<String> getNombresInsumos(String letra) throws IllegalArgumentException;

	List<String> getNombresMarcas() throws IllegalArgumentException;

	List<String> getNombresCategorias() throws IllegalArgumentException;

	List<String> getNombresProveedores() throws IllegalArgumentException;

	List<InsumoDTO> getInsumosSegunParametro(String tipo, String dato) throws IllegalArgumentException;

	InsumoDTO getInsumoCompleto(int idInsumo, String nombreInsumo) throws IllegalArgumentException;

	Boolean eliminarInsumo(InsumoDTO insumo) throws IllegalArgumentException;

	Boolean registrarCambioInsumo(InsumoDTO insumo) throws IllegalArgumentException;

	List<String> getNombresMarcasSegunInsumo(String nombreInsumo) throws IllegalArgumentException;

	List<String> getNombresProvSegunInsumoYMarca(String nombreInsumo, String nombreMarca) throws IllegalArgumentException;

	List<InsumoDTO> getRequerimientosInsumosCompletos() throws IllegalArgumentException;

	List<InsumoDTO> completarValoresInsumos(List<InsumoDTO> insumos, String proveedor) throws IllegalArgumentException;

	List<String> getModoDeEnvio() throws IllegalArgumentException;

	boolean registrarOrdenCompraInsumos(OrdenCompraInsumoDTO orden, String tipoUsuario) throws IllegalArgumentException;

	List<String> getNombreEstados() throws IllegalArgumentException;

	List<OrdenCompraInsumoDTO> getOrdenCompraInsumo(String estado, String prov, String fecDesde, String fecHasta) throws IllegalArgumentException;

	OrdenCompraInsumoDTO getOrdenCompraInsumoSegunId(long idOrden) throws IllegalArgumentException;

	boolean cancelarOrdencompraInsumo(long idOrden, String estado) throws IllegalArgumentException;

	List<OrdenCompraInsumoDTO> getOrdenCompraInsumoGuardada(String rolUsuario, String nombreUsuario, String apellidoUsuario) throws IllegalArgumentException;

	List<OrdenCompraInsumoDTO> getOrdenCompraInsumoEnviada() throws IllegalArgumentException;

	List<ProveedorDTO> getEmpresas() throws IllegalArgumentException;

	boolean ordenDeComprasCompleta(long idOrden) throws IllegalArgumentException;

	boolean actualizarOrdenCompraInsumos(OrdenCompraInsumoDTO orden) throws IllegalArgumentException;

	boolean registrarModificacionOrdenCompraInsumos(OrdenCompraInsumoDTO orden, OrdenCompraInsumoDTO ordenVieja, String tipoUsuario) throws IllegalArgumentException;

	boolean eliminarOrdenCompraInsumos(OrdenCompraInsumoDTO orden, String tipoUsuario) throws IllegalArgumentException;

	Boolean cancelarOrdenCompraInsumo(List<Long> listaOrdenes) throws IllegalArgumentException;

	Boolean validarOrdenCompraInsumo(List<Long> listaOrdenes) throws IllegalArgumentException;

	Boolean estadoEntregaParcialOrdenCompraInsumo(Long nroOrden) throws IllegalArgumentException;

	Boolean recibidaCompletaOrdenCompraInsumo(Long nroOrden) throws IllegalArgumentException;

	InsumoDTO getInsumoCompleto(String nombre, String marca) throws IllegalArgumentException;

	Boolean getExistenciaInsumo(String nombreInsumo, String marcaInsumo) throws IllegalArgumentException;

	List<OrdenCompraInsumoDTO> getOrdenCompraInsumoEnviadaRecibidaCerrada() throws IllegalArgumentException;

	List<String> getNombresPaises() throws IllegalArgumentException;

	List<String> getNombresProvincias(String pais) throws IllegalArgumentException;

	List<String> getNombresLocalidades(String prov) throws IllegalArgumentException;

	List<String> getInsumos() throws IllegalArgumentException;

	List<String> getCategorias() throws IllegalArgumentException;

	List<String> getMarcas() throws IllegalArgumentException;

	




	

	


}
