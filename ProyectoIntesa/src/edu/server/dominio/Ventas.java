package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Cliente;
import edu.server.repositorio.Contacto;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.Empleado;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Pais;
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
			
			String nombrePais = cliente.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
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
	
	public Boolean registrarCambiosCliente(Cliente clienteNuevo){
		
		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		Cliente clienteViejo = this.getEmpresaCompleta(clienteNuevo.getNombre());
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		try{
		
			for (Contacto contacto : clienteNuevo.getContactos()) {
				
				if (!clienteViejo.getContactos().contains(contacto)){
					contacto.getCliente().setIdCliente(clienteViejo.getIdCliente());
					sec.save(contacto);
				}
			}
			
			for (Contacto contacto : clienteViejo.getContactos()) {
				
				if (!clienteNuevo.getContactos().contains(contacto)){
					sec.delete(contacto);
				}
			}		
			
			
			
			String nombrePais = clienteNuevo.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
			String nombreProvincia = clienteNuevo.getDireccion().getLocalidad().getProvincia().getNombre();
			String nombreLocalidad = clienteNuevo.getDireccion().getLocalidad().getNombre();
			
			int paisEx =adminLoc.paisConsulta(nombrePais,sec);
			if (paisEx != -1) {
				clienteNuevo.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}else
			{
				sec.save(clienteNuevo.getDireccion().getLocalidad().getProvincia().getPais());
				paisEx =adminLoc.paisConsulta(nombrePais,sec);
				clienteNuevo.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}
			int provEx = adminLoc.provinciaConsulta(nombreProvincia,sec);
			if (provEx != -1) {
				clienteNuevo.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}else
			{
				sec.save(clienteNuevo.getDireccion().getLocalidad().getProvincia());
				provEx = adminLoc.provinciaConsulta(nombreProvincia,sec);
				clienteNuevo.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}
			int locEx = adminLoc.localidadConsulta(nombreLocalidad,nombreProvincia,sec);
			if (locEx != -1) {
				clienteNuevo.getDireccion().getLocalidad().setIdLocalidad(locEx);
			} else 
			{
				sec.save(clienteNuevo.getDireccion().getLocalidad());
				locEx = adminLoc.localidadConsulta(nombreLocalidad,nombreProvincia,sec);
				clienteNuevo.getDireccion().getLocalidad().setIdLocalidad(locEx);
			}
			
			
			clienteNuevo.getDireccion().setIdDireccion(clienteViejo.getDireccion().getIdDireccion());			
			sec.update(clienteNuevo.getDireccion());
			clienteNuevo.setIdCliente(clienteViejo.getIdCliente());
			sec.update(clienteNuevo);
			
			sec.getTransaction().commit();
			
			return true; 
						
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
			
	}
	
	public int nombreEmpresaExistente(String nombreEmpresa) {
		Cliente result;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = (Cliente) sec.createQuery("from Cliente where nombre like '" + nombreEmpresa + "'").uniqueResult();
		sec.close();
		if (result != null)
			return result.getIdCliente();
		else
			return -1;
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
		
		busqueda = sec.createQuery("from Contacto where id_Cliente is not null").list();
		
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
	
	public List<Cliente> getEmpresas(){
		
		List<Cliente> result = new LinkedList<Cliente>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from Cliente").list();
		
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
		
		result = sec.createQuery("from Contacto where nombre like '"+nombre+"' and id_Cliente is not null").list();
		
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
		Direccion dirResult;
		Localidad locResult;
		Provincia provResult;
		Pais paResult;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = (Cliente) sec.createQuery("from Cliente where nombre like '"+nombre+"'").uniqueResult();
		dirResult = (Direccion) sec.createQuery("from Direccion where id_Direccion = "+result.getDireccion().getIdDireccion()).uniqueResult();
		result.setDireccion(dirResult);
		locResult = (Localidad) sec.createQuery("from Localidad where id_Localidad = "+dirResult.getLocalidad().getIdLocalidad()).uniqueResult();
		dirResult.setLocalidad(locResult);
		provResult = (Provincia) sec.createQuery("from Provincia where id_Provincia = "+locResult.getProvincia().getIdProvincia()).uniqueResult();
		locResult.setProvincia(provResult);
		paResult = (Pais) sec.createQuery("from Pais where id_Pais = "+provResult.getPais().getIdPais()).uniqueResult();
		provResult.setPais(paResult);
				
		sec.close();		
		
		return result;
		
		
		
	}
	
	public Contacto getContactoCompleto(String nombreContacto, String nombreEmpresa){
		
		Contacto contacto;
		Cliente cliente;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		cliente = (Cliente) sec.createQuery("from Cliente where nombre like '"+nombreEmpresa+"'").uniqueResult();
		
		contacto = (Contacto) sec.createQuery("from Contacto where nombre like '"+nombreContacto+"' and id_Cliente = "+cliente.getIdCliente()).uniqueResult();
		
		sec.close();
		
		return contacto;		
		
	}
		
	public Contacto getContactoIdContacto(int idContacto){
		
		Contacto result = new Contacto();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = (Contacto) sec.get(result.getClass(),idContacto); 
			
		sec.close();		
		
		return result;
		
	}
	
	public Boolean eliminarContacto(String nombreEmpresa, String nombreContacto){
		
		Contacto contacto = this.getContactoCompleto(nombreContacto, nombreEmpresa);
		Boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try{
	
			sec.beginTransaction();
			sec.delete(contacto);		
			sec.getTransaction().commit();
			result = true;			
			
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;
		
		
	}
		
	public Boolean eliminarEmpresa(String nombreEmpresa){
		Boolean result = false;
		Cliente emp = this.getEmpresaCompleta(nombreEmpresa);
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			
			sec.beginTransaction();
			for (Contacto contacto : emp.getContactos()) {
				contacto.setCliente(emp);
				sec.delete(contacto);
			}
			
			sec.delete(emp);
			sec.delete(emp.getDireccion());
			sec.getTransaction().commit();
			result = true;
			
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
		return result;
	
	}
	
	public Boolean registrarCambiosContacto(Contacto contactoNuevo){
		
		Boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try{
	
			sec.beginTransaction();
			sec.update(contactoNuevo);
			sec.getTransaction().commit();
			result = true;			
			
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;
		
		
	}
	
	public int retornaIdContacto(String nombreEmpresa, String nombreContacto){
		
		Object result;
		
		String consulta = "select c.id_Contacto from Contacto as c, Cliente as e where c.nombre like '"+nombreContacto+"' and c.id_Cliente = e.id_Cliente and e.nombre like '"+nombreEmpresa+"'";

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result =  sec.createSQLQuery(consulta).uniqueResult();
		sec.close();
		if (result != null)
			return (int) result;
		else
			return -1;
	
		
	}
	
	
	
	
}
