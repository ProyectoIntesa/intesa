package edu.server.dominio;

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
	 * 
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
			
			System.out.println("from Usuario where usuario like '"+nombreUsuario+"' and contrasenia like '"+pass+"'");
			usr = (Usuario) session.createQuery("from Usuario where usuario like '"+nombreUsuario+"' and contrasenia like '"+pass+"'").list().get(0);
			
			if(usr != null)
			{
				Empleado emp = new Empleado();
				emp = (Empleado) session.get(Empleado.class, usr.getEmpleado().getIdEmpleado());
				respuesta.setNombre(emp.getNombre()+" "+emp.getApellido());
				respuesta.setRol(usr.getRol());
				
				session.close();
				return respuesta;
			}
			
		} catch (HibernateException he){	
			session.close();
			return null;
		}
		session.close();
		return null;
	}	

}












