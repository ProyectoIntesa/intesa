package edu.server.dominio;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Cliente;
import edu.server.util.HibernateUtil;

public class Ventas {

	
	public Ventas(){
		
	}
	
	
	public Boolean registrarCliente(Cliente cliente){
		
		boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			sec.save(cliente.getDireccion());
			sec.save(cliente);
			sec.getTransaction().commit();
			result = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;	 
		
	}
	
	
	
	
	
	
	
	
	
}
