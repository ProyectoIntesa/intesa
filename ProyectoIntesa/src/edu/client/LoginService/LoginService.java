package edu.client.LoginService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DTO.UsuarioDTO;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
	UsuarioDTO getUsuario(String user, String pass) throws IllegalArgumentException;
}
