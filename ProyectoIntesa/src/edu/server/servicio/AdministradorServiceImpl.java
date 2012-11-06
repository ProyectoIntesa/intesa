package edu.server.servicio;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.AdministradorService.AdministradorService;
import edu.server.dominio.Administrador;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.UsuarioCompDTO;

public class AdministradorServiceImpl extends RemoteServiceServlet implements AdministradorService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728818305014735714L;

	@Override
	public List<EmpleadoDTO> getEmpleados(List<EmpleadoDTO> lista) throws IllegalArgumentException {
		Administrador admin = new Administrador();
		lista = admin.getEmpleados();
		
		return lista;
	}

	@Override
	public Boolean usuarioExistentes(String nombreUsuario) throws IllegalArgumentException {
		Administrador admin = new Administrador();
		Boolean result = new Boolean(admin.usuarioExistentes(nombreUsuario));
		return result;
	}
	
	@Override
	public List<UsuarioCompDTO> getUsuarios(List<UsuarioCompDTO> lista) throws IllegalArgumentException {
		Administrador admin = new Administrador();
		lista = admin.getUsuarios();
		
		return lista;
	}
	
	 
	
	
	
}
