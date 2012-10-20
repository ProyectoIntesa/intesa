package edu.server.dominio;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Empleado;
import edu.server.repositorio.Usuario;
import edu.server.util.HibernateUtil;

public class Login {

	public Login() {

	}
	
public Usuario getUsuario(String nombreUsuario, String pass){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try{
			session.beginTransaction();
			
			Usuario usr = new Usuario();
			
			usr = (Usuario) session.createQuery("from usuario where usuario= '"+nombreUsuario+"' and contrasenia='"+pass+"'").list().get(0);
			
			if(usr != null)
				return usr;		
			
		} catch (HibernateException he){
			
			return null;
		}
		return null;
	}

	
}
