package edu.client.VentasService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.ClienteDTO;

@RemoteServiceRelativePath("ventas")
public interface VentasService extends RemoteService {

	Boolean registrarNuevoCliente(ClienteDTO cliente)throws IllegalArgumentException;

}
