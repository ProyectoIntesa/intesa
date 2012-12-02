package edu.server.dominio;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Empleado;
import edu.server.repositorio.Usuario;
import edu.server.util.HibernateUtil;
import edu.shared.DTO.UsuarioDTO;

public class Login {

	public Login() {

	}
	
	/**
	 * recibe como parámetro el nombre del usuario y su contraseña y devuelve el nombre, el apellido y el rol del usuario
	 * si no existe retorna null
	 * @param nombreUsuario
	 * @param pass
	 * @return
	 */
	
public UsuarioDTO getUsuario(String nombreUsuario, String pass){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try{
			session.beginTransaction();
			
			Usuario usr= new Usuario();
			UsuarioDTO respuesta  = new UsuarioDTO();
			List <Usuario> usuarios = session.createQuery("from Usuario where usuario like '"+nombreUsuario+"' and contrasenia like '"+pass+"'").list();  
						
			if(!usuarios.isEmpty())
			{
				usr = usuarios.get(0);
				Empleado emp = new Empleado();
				emp = (Empleado) session.get(Empleado.class, usr.getEmpleado().getIdEmpleado());
				respuesta.setNombre(emp.getApellido() + ", " + emp.getNombre());
				respuesta.setRol(usr.getRol());

				session.close();
				return respuesta;
			}
			else{
				session.close();
				return null;
			} 
			
		} catch (HibernateException he){	
			session.close();
			return null;
		}
	}	
}












