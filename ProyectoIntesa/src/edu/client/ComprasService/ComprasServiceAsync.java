package edu.client.ComprasService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.ProveedorDTO;

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

	void registrarCambioInsumo(InsumoDTO insumo, AsyncCallback<Boolean> callback);

}
