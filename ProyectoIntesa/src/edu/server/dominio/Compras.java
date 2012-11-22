

package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Cliente;
import edu.server.repositorio.Contacto;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.Provincia;
import edu.server.util.HibernateUtil;

public class Compras {

	
	public Compras(){
		
	}
	
	public Boolean registrarProveedor(Proveedor proveedor){
		
		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			
			String nombrePais = proveedor.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
			String nombreProvincia = proveedor.getDireccion().getLocalidad().getProvincia().getNombre();
			String nombreLocalidad = proveedor.getDireccion().getLocalidad().getNombre();
			
			int paisEx =adminLoc.paisConsulta(nombrePais,sec);
			if (paisEx != -1) {
				proveedor.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}else
			{
				sec.save(proveedor.getDireccion().getLocalidad().getProvincia().getPais());
				paisEx =adminLoc.paisConsulta(nombrePais,sec);
				proveedor.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}
			int provEx = adminLoc.provinciaConsulta(nombreProvincia,sec);
			if (provEx != -1) {
				proveedor.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}else
			{
				sec.save(proveedor.getDireccion().getLocalidad().getProvincia());
				provEx = adminLoc.provinciaConsulta(nombreProvincia,sec);
				proveedor.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}
			int locEx = adminLoc.localidadConsulta(nombreLocalidad,nombreProvincia,sec);
			if (locEx != -1) {
				proveedor.getDireccion().getLocalidad().setIdLocalidad(locEx);
			} else 
			{
				sec.save(proveedor.getDireccion().getLocalidad());
				locEx = adminLoc.localidadConsulta(nombreLocalidad,nombreProvincia,sec);
				proveedor.getDireccion().getLocalidad().setIdLocalidad(locEx);
			}
			sec.save(proveedor.getDireccion());
			sec.save(proveedor);
			if(proveedor.getContactos().size()>0)
			{
				for (Contacto contacto : proveedor.getContactos()) {
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
	
	public Boolean registrarCambiosProveedor(Proveedor proveedorNuevo){
		
		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		Proveedor proveedorViejo = this.getEmpresaCompleta(proveedorNuevo.getNombre());
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		try{
		
			for (Contacto contacto : proveedorNuevo.getContactos()) {
				
				if (!proveedorViejo.getContactos().contains(contacto)){
					contacto.getProveedor().setCodigoProveedor(proveedorViejo.getCodigoProveedor());
					sec.save(contacto);
				}
			}
			
			for (Contacto contacto : proveedorViejo.getContactos()) {
				
				if (!proveedorNuevo.getContactos().contains(contacto)){
					sec.delete(contacto);
				}
			}		
			
			
			
			String nombrePais = proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
			String nombreProvincia = proveedorNuevo.getDireccion().getLocalidad().getProvincia().getNombre();
			String nombreLocalidad = proveedorNuevo.getDireccion().getLocalidad().getNombre();
			
			int paisEx =adminLoc.paisConsulta(nombrePais,sec);
			if (paisEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}else
			{
				sec.save(proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais());
				paisEx =adminLoc.paisConsulta(nombrePais,sec);
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}
			int provEx = adminLoc.provinciaConsulta(nombreProvincia,sec);
			if (provEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}else
			{
				sec.save(proveedorNuevo.getDireccion().getLocalidad().getProvincia());
				provEx = adminLoc.provinciaConsulta(nombreProvincia,sec);
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}
			int locEx = adminLoc.localidadConsulta(nombreLocalidad,nombreProvincia,sec);
			if (locEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().setIdLocalidad(locEx);
			} else 
			{
				sec.save(proveedorNuevo.getDireccion().getLocalidad());
				locEx = adminLoc.localidadConsulta(nombreLocalidad,nombreProvincia,sec);
				proveedorNuevo.getDireccion().getLocalidad().setIdLocalidad(locEx);
			}
			
			
			proveedorNuevo.getDireccion().setIdDireccion(proveedorViejo.getDireccion().getIdDireccion());			
			sec.update(proveedorNuevo.getDireccion());
			proveedorNuevo.setCodigoProveedor(proveedorViejo.getCodigoProveedor());
			sec.update(proveedorNuevo);
			
			sec.getTransaction().commit();
			
			return true; 
						
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
			
	}
		
	public Proveedor getEmpresaCompleta(String nombre){
		
		Proveedor result;
		Direccion dirResult;
		Localidad locResult;
		Provincia provResult;
		Pais paResult;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		
		result = (Proveedor) sec.createQuery("from Proveedor where nombre like '"+nombre+"'").uniqueResult();
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
	
	public List<String> getNombresEmpresas(){
		
		List<String> result = new LinkedList<String>();
		
		List<Proveedor> busqueda;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		busqueda = sec.createQuery("from Proveedor").list();
		
		sec.close();
		
		for (Proveedor proveedor : busqueda) {
			result.add(proveedor.getNombre());
		}			
		
		return result;
		
	}

	public List<String> getRubros(){
		
		List<String> result = new LinkedList<String>();
		
		List<Proveedor> busqueda;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		busqueda = sec.createQuery("from Proveedor").list();
		
		sec.close();
		
		for (Proveedor proveedor : busqueda) {
			
			if(!result.contains(proveedor.getRubro())){
				result.add(proveedor.getRubro());
			}
			
		}			
		
		return result;
		
	}
	
	public List<String> getContactos(){
		
		List<String> result = new LinkedList<String>();
		
		List<Contacto> busqueda;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		busqueda = sec.createQuery("from Contacto where id_Empresa is not null").list();
		
		sec.close();
				
		for (Contacto contacto : busqueda) {
			result.add(contacto.getNombre());
		}			
		
		return result;
		
	}

	public List<Contacto> getEmpresasPorContacto(String nombre){
		
		List<Contacto> result = new LinkedList<Contacto>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from Contacto where nombre like '"+nombre+"' and id_Empresa is not null").list();
		
		sec.close();		
		
		return result;
		
	}

	public String[] getEmpresaRubroPorIdProveedor(int idProveedor){
		
		String [] result = {"",""} ;
		
		Proveedor proveedor = new Proveedor();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		proveedor = (Proveedor) sec.get(proveedor.getClass(),idProveedor); 
		
		sec.close();
		
		result[0] = proveedor.getNombre();
		result[1] = proveedor.getRubro();
		
		return result;
	}

	public Contacto getContactoCompleto(String nombreContacto, String nombreEmpresa){
		
		Contacto contacto;
		
		Proveedor proveedor;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		proveedor = (Proveedor) sec.createQuery("from Proveedor where nombre like '"+nombreEmpresa+"'").uniqueResult();
		
		contacto = (Contacto) sec.createQuery("from Contacto where nombre like '"+nombreContacto+"' and id_Empresa = "+proveedor.getCodigoProveedor()).uniqueResult();
		
		sec.close();
		
		return contacto;		
		
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

	public List<Proveedor> getEmpresasPorRubro(String nombre){
		
		List<Proveedor> result = new LinkedList<Proveedor>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from Proveedor where rubro like '"+nombre+"'").list();
		
		sec.close();		
		
		return result;
		
	}

	public Boolean eliminarEmpresa(String nombreEmpresa){
		Boolean result = false;
		Proveedor emp = this.getEmpresaCompleta(nombreEmpresa);
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			
			sec.beginTransaction();
			for (Contacto contacto : emp.getContactos()) {
				contacto.setProveedor(emp);
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

	public List<Proveedor> getEmpresas(String nombre){
		
		List<Proveedor> result = new LinkedList<Proveedor>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from Proveedor where nombre like '"+nombre+"'").list();
		
		sec.close();		
		
		return result;
		
	}

	public int retornaIdContacto(String nombreEmpresa, String nombreContacto){
		
		Object result;
		
		String consulta = "select c.id_Contacto from Contacto as c, Proveedor as p where c.nombre like '"+nombreContacto+"' and c.id_Empresa = p.codigo_Proveedor and p.nombre like '"+nombreEmpresa+"'";

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result =  sec.createSQLQuery(consulta).uniqueResult();
		sec.close();
		if (result != null)
			return (int) result;
		else
			return -1;
	
		
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
}
