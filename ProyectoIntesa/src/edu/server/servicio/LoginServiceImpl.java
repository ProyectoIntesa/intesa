package edu.server.servicio;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.LoginService.LoginService;
import edu.server.dominio.Login;
import edu.shared.DTO.UsuarioDTO;

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	private static final long serialVersionUID = 3559703489669019192L;

	public UsuarioDTO getUsuario(String user, String pass) throws IllegalArgumentException {

		Login loginDominio = new Login();
		UsuarioDTO usuario = loginDominio.getUsuario(user, pass);
		return usuario;
	}

}
