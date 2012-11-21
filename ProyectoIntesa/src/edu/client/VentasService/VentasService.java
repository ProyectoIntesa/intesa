package edu.client.VentasService;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;

@RemoteServiceRelativePath("ventas")
public interface VentasService extends RemoteService {

	Boolean registrarNuevoCliente(ClienteDTO cliente) throws IllegalArgumentException;

	List<String> getNombresEmpresas() throws IllegalArgumentException;

	List<String> getRubros() throws IllegalArgumentException;

	List<String> getContactos() throws IllegalArgumentException;

	List<ClienteDTO> getEmpresas(String nombre) throws IllegalArgumentException;

	List<ClienteDTO> getEmpresasPorRubro(String nombre) throws IllegalArgumentException;

	List<ContactoDTO> getEmpresasPorContacto(String nombre) throws IllegalArgumentException;

	ClienteDTO getEmpresaCompleta(String nombre) throws IllegalArgumentException;

	ContactoDTO getContactoCompleto(String nombreContacto, String nombreEmpresa) throws IllegalArgumentException;

	Boolean eliminarContacto(String nombreEmpresa, String nombreContacto) throws IllegalArgumentException;

	Boolean registrarCambioCliente(ClienteDTO cliente) throws IllegalArgumentException;

	Boolean eliminarEmpresa(String nombreEmpresa) throws IllegalArgumentException;

	Boolean modificarContacto(ContactoDTO contacto, int idContacto) throws IllegalArgumentException;

	int retornaIdContacto(String nombreEmpresa, String nombreContacto) throws IllegalArgumentException;



}
