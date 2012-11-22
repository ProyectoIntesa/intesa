package edu.client.VentasService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;

public interface VentasServiceAsync {

	void registrarNuevoCliente(ClienteDTO cliente, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void getNombresEmpresas(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getRubros(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getContactos(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getEmpresas(String nombre, AsyncCallback<List<ClienteDTO>> callback) throws IllegalArgumentException;

	void getEmpresasPorRubro(String nombre, AsyncCallback<List<ClienteDTO>> callback) throws IllegalArgumentException;

	void getEmpresasPorContacto(String nombre, AsyncCallback<List<ContactoDTO>> callback) throws IllegalArgumentException;

	void getEmpresaCompleta(String nombre, AsyncCallback<ClienteDTO> callback) throws IllegalArgumentException;

	void getContactoCompleto(String nombreContacto, String nombreEmpresa, AsyncCallback<ContactoDTO> callback) throws IllegalArgumentException;

	void eliminarContacto(String nombreEmpresa, String nombreContacto, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void registrarCambioCliente(ClienteDTO cliente, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void eliminarEmpresa(String nombreEmpresa, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void modificarContacto(ContactoDTO contacto, int idContacto, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void retornaIdContacto(String nombreEmpresa, String nombreContacto, AsyncCallback<Integer> callback) throws IllegalArgumentException;



	
	

}
