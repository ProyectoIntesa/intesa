package edu.client.LoginService;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.DTO.UsuarioDTO;

public interface LoginServiceAsync {

	void getUsuario(String user, String pass, AsyncCallback<UsuarioDTO> callback)throws IllegalArgumentException;

}
