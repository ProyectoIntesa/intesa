package edu.server.servicio;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.AdministradorService.AdministradorService;
import edu.server.dominio.Administrador;
import edu.server.repositorio.Empleado;
import edu.server.repositorio.Usuario;
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
	
	@Override
	public Boolean guardarUsuario(UsuarioCompDTO usuario) throws IllegalArgumentException {
		Usuario nuevo = new Usuario();
		nuevo.setUsuario(usuario.getNombreUsu());
		nuevo.setContrasenia(usuario.getPassUsu());
		nuevo.setRol(usuario.getRolUsu());
		Administrador admin = new Administrador();
		int idEmp = admin.idEmpleado(usuario.getNroLegajoEmp());
		Empleado emp = new Empleado();
		emp.setIdEmpleado(idEmp);
		nuevo.setEmpleado(emp);
		return admin.registrarUsuario(nuevo);
		
		
	}
	
	@Override
	public Boolean usuarioTieneEmp(int legajo) throws IllegalArgumentException {
		Administrador admin = new Administrador();
		return admin.usuarioTieneEmpleado(legajo);
	}
	
	
	@Override
	public Boolean eliminarUsuario(String nombreUsu) throws IllegalArgumentException {
		Administrador admin = new Administrador();
		return admin.eliminarElUsuario(nombreUsu);
	}
	
	
	@Override
	public Boolean modificarUsuario(String nombreUsu, String passUsu) throws IllegalArgumentException {
		Administrador admin = new Administrador();
		return admin.modificarUsuario(nombreUsu, passUsu);
	}
	
	@Override
	public Boolean guardarEmpleado(EmpleadoDTO emp) throws IllegalArgumentException {
		
		Administrador admin = new Administrador();
		
		Empleado nuevo = new Empleado();
		nuevo.setNombre(emp.getNombre());
		nuevo.setApellido(emp.getApellido());
		nuevo.setNroLegajo(emp.getNroLegajo());
		nuevo.setPuesto(emp.getPuesto());
		
		for (int i=0; i < emp.getListaEmpACargo().size();i++  ){
			Empleado empLista = new Empleado();
			int idEmpleado = admin.idEmpleado(emp.getListaEmpACargo().get(i).getNroLegajo());
			empLista.setIdEmpleado(idEmpleado);
			nuevo.getEmpleadosForEmpleado().add(empLista);
		}
		return admin.registrarEmpleado(nuevo);
	}
	
	@Override
	public Boolean existeEmpleado(int nroLegajo) throws IllegalArgumentException {
		
		Administrador admin = new Administrador();
		return admin.existeEmpleado(nroLegajo);		
	}
	
}
