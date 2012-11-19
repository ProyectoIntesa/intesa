package edu.server.dominio;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Cliente;
import edu.server.repositorio.Contacto;
import edu.server.util.HibernateUtil;

public class Ventas {

	
	public Ventas(){
		
	}
	
	
	public Boolean registrarCliente(Cliente cliente){
		
		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			
			String nombrePais=cliente.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
			String nombreProvincia = cliente.getDireccion().getLocalidad().getProvincia().getNombre();
			String nombreLocalidad = cliente.getDireccion().getLocalidad().getNombre();
			int paisEx =adminLoc.paisConsulta(nombrePais,sec);
			if (paisEx != -1) {
				cliente.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}else
			{
				sec.save(cliente.getDireccion().getLocalidad().getProvincia().getPais());
				paisEx =adminLoc.paisConsulta(nombrePais,sec);
				cliente.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}
			int provEx = adminLoc.provinciaConsulta(nombreProvincia,sec);
			if (provEx != -1) {
				cliente.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}else
			{
				sec.save(cliente.getDireccion().getLocalidad().getProvincia());
				provEx = adminLoc.provinciaConsulta(nombreProvincia,sec);
				cliente.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}
			int locEx = adminLoc.localidadConsulta(nombreLocalidad,nombreProvincia,sec);
			if (locEx != -1) {
				cliente.getDireccion().getLocalidad().setIdLocalidad(locEx);
			} else 
			{
				sec.save(cliente.getDireccion().getLocalidad());
				locEx = adminLoc.localidadConsulta(nombreLocalidad,nombreProvincia,sec);
				cliente.getDireccion().getLocalidad().setIdLocalidad(locEx);
			}
			sec.save(cliente.getDireccion());
			sec.save(cliente);
			if(cliente.getContactos().size()>0)
			{
				for (Contacto contacto : cliente.getContactos()) {
					sec.save(contacto);
				}
			}
			sec.getTransaction().commit();
			result = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		} 

		return result;	 
		
	}
	
	
	
	
	
	
	
	
	
}
