package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Empleado;
import edu.server.repositorio.Usuario;
import edu.server.util.HibernateUtil;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.UsuarioCompDTO;


public class Administrador {

	public Administrador() {

	}

	/**
	 * Registra en la base de datos un nuevo empleado
	 * 
	 * @param nuevoEmpleado
	 * @return
	 */

	public boolean registrarEmpleado(Empleado nuevoEmpleado) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			sec.save(nuevoEmpleado);
			sec.getTransaction().commit();
			respuesta = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
	}

	/**
	 * Registra en la base de datos un nuevo usuario para un empleado
	 * correspondiente
	 * 
	 * @param nuevoUsuario
	 * @return
	 */
	public boolean registrarUsuario(Usuario nuevoUsuario) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			sec.save(nuevoUsuario);
			sec.getTransaction().commit();
			respuesta = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
	}

	/**
	 * Permite informar si existe un usuario resgistrado con el nombre y
	 * contraseñia pasado
	 * 
	 * @param nombreDeUsuario
	 * @return
	 */
	public boolean usuarioExistentes(String nombreDeUsuario) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		Usuario usuario = new Usuario();
		usuario = (Usuario) sec.createQuery("from Usuario where usuario like '" + nombreDeUsuario + "'").uniqueResult();
		sec.close();
		if (usuario == null)
			respuesta = false;
		else
			respuesta = true;
		return respuesta;
	}

	/**
	 * Permite obtener el id del empleado con el número de legajo solicitado
	 * 
	 * @param nroLegajo
	 * @return
	 */
	public int idEmpleado(int nroLegajo) {
		int id = -1;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		List<Empleado> empleados = sec.createQuery("from Empleado where nro_Legajo = " + nroLegajo).list();
		sec.close();
		if (empleados.isEmpty())
			id = -1;
		else {
			Empleado empleado = empleados.get(0);
			id = empleado.getIdEmpleado();
		}
		return id;
	}

	
	/**
	 * Elimina un usuario de la base de datos, retorna true si tu exito y false en caso contrario
	 * @param usuario
	 * @return
	 */
	private boolean eliminarUsuario(String usuario) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			sec.createQuery("delete from Usuario where usuario like '"+usuario+"'").executeUpdate();
			sec.getTransaction().commit();
				respuesta = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		} 

		return respuesta;
	}
	

	public Boolean eliminarElUsuario(String usuario){
		Boolean respueta=false;
		if(this.usuarioExistentes(usuario)){
			this.eliminarUsuario(usuario);
			if(this.usuarioExistentes(usuario)){
				respueta = false;
			}
			else
				respueta=true;
		}
		else
			respueta = false;
		
		return respueta;
	}
	
		
	public List<EmpleadoDTO> getEmpleados()
	{
		List<Empleado> listaEmpleados;
		List<EmpleadoDTO> listaResultado = new LinkedList<EmpleadoDTO>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		listaEmpleados = sec.createQuery("from Empleado ").list();
		sec.close();
		
		if(!listaEmpleados.isEmpty())
		{
			for(int i =0; i < listaEmpleados.size();i++)
			{
				EmpleadoDTO nuevo= new EmpleadoDTO();
				nuevo.setNroLegajo(listaEmpleados.get(i).getNroLegajo());
				nuevo.setNombre(listaEmpleados.get(i).getNombre());
				nuevo.setApellido(listaEmpleados.get(i).getApellido());
				nuevo.setPuesto(listaEmpleados.get(i).getPuesto());
			 listaResultado.add(nuevo);
			}
		}

		return listaResultado;
	}

	
	public List<EmpleadoDTO> getEmpleadosSinUsuario()
	{
		boolean bandera = false;
		List<Usuario> listaUsuarios;
		List<Empleado> listaEmpleados;
		List<EmpleadoDTO> listaResultado = new LinkedList<EmpleadoDTO>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		listaUsuarios = sec.createQuery("from Usuario").list();
		listaEmpleados = sec.createQuery("from Empleado").list();

		sec.close();
		
		if(!listaEmpleados.isEmpty())
		{
			for(int i =0; i < listaEmpleados.size();i++)
			{
				for(int j= 0; j<listaUsuarios.size();j++){
					
					
					if(listaEmpleados.get(i).getIdEmpleado()==listaUsuarios.get(j).getEmpleado().getIdEmpleado()){
						bandera = true;
						break;
					}					
				}
				
				if(bandera == false){
					EmpleadoDTO nuevo= new EmpleadoDTO();
					nuevo.setNroLegajo(listaEmpleados.get(i).getNroLegajo());
					nuevo.setNombre(listaEmpleados.get(i).getNombre());
					nuevo.setApellido(listaEmpleados.get(i).getApellido());
					nuevo.setPuesto(listaEmpleados.get(i).getPuesto());
					listaResultado.add(nuevo);		 
				}
				bandera = false;
			}
		}

		return listaResultado;
	}
	
	
	public List<UsuarioCompDTO> getUsuarios()
	{
		List<Usuario> listaUsuarios;
		List<UsuarioCompDTO> listaResultado = new LinkedList<UsuarioCompDTO>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		listaUsuarios = sec.createQuery("from Usuario").list();
		sec.close();
		
		if(!listaUsuarios.isEmpty())
		{
			for(int i =0; i < listaUsuarios.size();i++)
			{
				UsuarioCompDTO nuevo = new UsuarioCompDTO();
				nuevo.setNombreUsu(listaUsuarios.get(i).getUsuario());
				nuevo.setPassUsu(listaUsuarios.get(i).getContrasenia());
				nuevo.setRolUsu(listaUsuarios.get(i).getRol());
				
				int idEmp = listaUsuarios.get(i).getEmpleado().getIdEmpleado();
				
				listaUsuarios.get(i).setEmpleado(this.getEmpleado(idEmp));			
				
				nuevo.setApellidoEmp(listaUsuarios.get(i).getEmpleado().getApellido());
				nuevo.setNombreEmp(listaUsuarios.get(i).getEmpleado().getNombre());
				nuevo.setNroLegajoEmp(listaUsuarios.get(i).getEmpleado().getNroLegajo());
				
				listaResultado.add(nuevo);
			}
		}

		return listaResultado;
	}
	
	
	
	private Empleado getEmpleado(int idEmpleado){
		
		Empleado result = new Empleado();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = (Empleado) sec.get(result.getClass(), idEmpleado);
		
		sec.close();
		
		
		
		return result;
	}
	
	
	public Boolean modificarUsuario(String usuario, String pass){
		Boolean respuesta = false;
		int result = 0;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			result= sec.createQuery("update Usuario set contrasenia='"+pass+"' where usuario like'"+usuario+"'").executeUpdate();
			sec.getTransaction().commit();
			if(result == 1)
				respuesta = true;
	
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		} 
		
		return respuesta;
	}
	
	
	public Boolean usuarioTieneEmpleado(int nroLegajo){
		
		Boolean result = false;
		int idEmp = this.idEmpleado(nroLegajo);
		Usuario resultado = new Usuario();
		
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			resultado= (Usuario) sec.createQuery("from Usuario where id_Empleado = "+idEmp).uniqueResult();
			sec.close();
			if (resultado != null){
				result = true;
			}
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		} 
		
		return result;	
	}
	
	
	
	
	
}
