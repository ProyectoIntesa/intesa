package edu.client.VentasService;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.LocalidadDTO;

public interface VentasServiceAsync {

	void registrarNuevoCliente(ClienteDTO cliente,
			AsyncCallback<Boolean> callback)throws IllegalArgumentException;

	
	

}
