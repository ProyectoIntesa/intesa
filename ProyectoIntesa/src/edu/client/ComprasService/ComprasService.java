package edu.client.ComprasService;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.ProveedorDTO;

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

}
