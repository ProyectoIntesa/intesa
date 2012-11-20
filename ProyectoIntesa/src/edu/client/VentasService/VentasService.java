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



}
