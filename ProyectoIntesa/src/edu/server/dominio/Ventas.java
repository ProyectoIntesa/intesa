package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Cliente;
import edu.server.repositorio.Contacto;
import edu.server.repositorio.Empleado;
import edu.server.repositorio.Provincia;
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
	
	
	
	public List<String> getNombresEmpresas(){
		
		List<String> result = new LinkedList<String>();
		
		List<Cliente> busqueda;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		busqueda = sec.createQuery("from Cliente").list();
		
		sec.close();
		
		for (Cliente cliente : busqueda) {
			result.add(cliente.getNombre());
		}			
		
		return result;
		
	}
	
	public List<String> getRubros(){
		
		List<String> result = new LinkedList<String>();
		
		List<Cliente> busqueda;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		busqueda = sec.createQuery("from Cliente").list();
		
		sec.close();
		
		for (Cliente cliente : busqueda) {
			
			if(!result.contains(cliente.getRubro())){
				result.add(cliente.getRubro());
			}
			
		}			
		
		return result;
		
	}
	
	public List<String> getContactos(){
		
		List<String> result = new LinkedList<String>();
		
		List<Contacto> busqueda;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		busqueda = sec.createQuery("from Contacto where id_cliente is not null").list();
		
		sec.close();
				
		for (Contacto contacto : busqueda) {
			result.add(contacto.getNombre());
		}			
		
		return result;
		
	}
	
	
	public List<Cliente> getEmpresas(String nombre){
		
		List<Cliente> result = new LinkedList<Cliente>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from Cliente where nombre like '"+nombre+"'").list();
		
		sec.close();		
		
		return result;
		
	}
	
	public List<Cliente> getEmpresasPorRubro(String nombre){
		
		List<Cliente> result = new LinkedList<Cliente>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from Cliente where rubro like '"+nombre+"'").list();
		
		sec.close();		
		
		return result;
		
	}
	
	public List<Contacto> getEmpresasPorContacto(String nombre){
		
		List<Contacto> result = new LinkedList<Contacto>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from Contacto where nombre like '"+nombre+"' and id_cliente is not null").list();
		
		sec.close();		
		
		return result;
		
	}
	
	public String[] getEmpresaRubroPorIdCliente(int idCliente){
		
		String [] result = {"",""} ;
		
		Cliente cliente = new Cliente();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		cliente = (Cliente) sec.get(cliente.getClass(),idCliente); 
		
		sec.close();
		
		result[0] = cliente.getNombre();
		result[1] = cliente.getRubro();
		
		return result;
	}
	
	
	public Cliente getEmpresaCompleta(String nombre){
		
		Cliente result;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = (Cliente) sec.createQuery("from Cliente where nombre like '"+nombre+"'").uniqueResult();
				
		sec.close();		
		
		return result;
		
		
		
	}
	
	
	public Contacto getContactoIdContacto(int idContacto){
		
		Contacto result = new Contacto();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = (Contacto) sec.get(result.getClass(),idContacto); 
			
		sec.close();		
		
		return result;
		
	}
	
	
	
	
}
