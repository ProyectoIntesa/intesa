package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import edu.server.repositorio.Contacto;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.Provincia;
import edu.server.util.HibernateUtil;

public class Proveedores {

	public Proveedores(){
		
	}
	
	public Proveedor getEmpresaCompleta(String nombre) {

		Proveedor result;
		Direccion dirResult;
		Localidad locResult;
		Provincia provResult;
		Pais paResult;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = (Proveedor) sec.createQuery("from Proveedor where nombre like '" + nombre + "'").uniqueResult();
		dirResult = (Direccion) sec.createQuery("from Direccion where id_Direccion = " + result.getDireccion().getIdDireccion()).uniqueResult();
		result.setDireccion(dirResult);
		locResult = (Localidad) sec.createQuery("from Localidad where id_Localidad = " + dirResult.getLocalidad().getIdLocalidad()).uniqueResult();
		dirResult.setLocalidad(locResult);
		provResult = (Provincia) sec.createQuery("from Provincia where id_Provincia = " + locResult.getProvincia().getIdProvincia()).uniqueResult();
		locResult.setProvincia(provResult);
		paResult = (Pais) sec.createQuery("from Pais where id_Pais = " + provResult.getPais().getIdPais()).uniqueResult();
		provResult.setPais(paResult);

		sec.close();

		return result;

	}
		
	public List<String> getNombresEmpresas() {

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
	
	public List<String> getRubros() {

		List<String> result = new LinkedList<String>();

		List<Proveedor> busqueda;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		busqueda = sec.createQuery("from Proveedor").list();

		sec.close();

		for (Proveedor proveedor : busqueda) {

			if (!result.contains(proveedor.getRubro())) {
				result.add(proveedor.getRubro());
			}

		}

		return result;

	}
	
	public List<String> getTipos() {

		List<String> result = new LinkedList<String>();

		List<Proveedor> busqueda;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		busqueda = sec.createQuery("from Proveedor").list();

		sec.close();

		for (Proveedor proveedor : busqueda) {

			if (!result.contains(proveedor.getTipoProveedor())) {
				result.add(proveedor.getTipoProveedor());
			}

		}

		return result;

	}
	
	public List<String> getContactos() {

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
	
	public List<Contacto> getEmpresasPorContacto(String nombre) {

		List<Contacto> result = new LinkedList<Contacto>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Contacto where nombre like '" + nombre + "' and id_Empresa is not null").list();

		sec.close();

		return result;

	}
	
	public String[] getEmpresaRubroPorIdProveedor(int idProveedor) {

		String[] result = { "", "" };

		Proveedor proveedor = new Proveedor();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		proveedor = (Proveedor) sec.get(proveedor.getClass(), idProveedor);

		sec.close();

		result[0] = proveedor.getNombre();
		result[1] = proveedor.getRubro();

		return result;
	}
	
	public Contacto getContactoCompleto(String nombreContacto, String nombreEmpresa) {

		Contacto contacto;

		Proveedor proveedor;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		proveedor = (Proveedor) sec.createQuery("from Proveedor where nombre like '" + nombreEmpresa + "'").uniqueResult();

		contacto = (Contacto) sec.createQuery("from Contacto where nombre like '" + nombreContacto + "' and id_Empresa = " + proveedor.getCodigoProveedor())
				.uniqueResult();

		sec.close();

		return contacto;

	}
	
	public List<Proveedor> getEmpresasPorRubro(String nombre) {

		List<Proveedor> result = new LinkedList<Proveedor>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Proveedor where rubro like '" + nombre + "'").list();

		sec.close();

		return result;

	}
	
	public List<Proveedor> getEmpresasPorTipo(String nombre) {

		List<Proveedor> result = new LinkedList<Proveedor>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Proveedor where tipo_Proveedor like '" + nombre + "'").list();

		sec.close();

		return result;

	}
	
	public List<Proveedor> getEmpresas(String nombre) {

		List<Proveedor> result = new LinkedList<Proveedor>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Proveedor where nombre like '" + nombre + "'").list();

		sec.close();

		return result;

	}
	
	public List<Proveedor> getEmpresas() {

		List<Proveedor> result = new LinkedList<Proveedor>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Proveedor").list();

		sec.close();

		return result;

	}
	
	public int retornaIdContacto(String nombreEmpresa, String nombreContacto) {

		Object result;

		String consulta = "select c.id_Contacto from Contacto as c, Proveedor as p where c.nombre like '" + nombreContacto
				+ "' and c.id_Empresa = p.codigo_Proveedor and p.nombre like '" + nombreEmpresa + "'";

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = sec.createSQLQuery(consulta).uniqueResult();
		sec.close();
		if (result != null)
			return (int) result;
		else
			return -1;

	}
	
	public Proveedor getProveedorPorNombre(String nombre) {

		Proveedor result;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = (Proveedor) sec.createQuery("from Proveedor where nombre like '" + nombre + "'").uniqueResult();

		sec.close();

		return result;

	}
	
	public List<String> getNombresProveedores() {

		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createSQLQuery("select p.nombre from Proveedor_de_Insumo as pdi, Proveedor as p where pdi.id_Proveedor = p.codigo_Proveedor").list();

		sec.close();

		return result;

	}
	
	public int getIdProveedor(String nombre){
		Object idProv = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		idProv = sec.createSQLQuery("select codigo_Proveedor from Proveedor where nombre like '"+nombre+"'").uniqueResult();
		if (idProv == null)
			idProv = 0;
		sec.close();
		return (int)idProv;
	}
	
	public String getNombreProveedor(int idProv){
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		String prov = (String) sec.createSQLQuery("select nombre from proveedor where codigo_proveedor = "+idProv).uniqueResult();
		sec.close();
		return prov;
	}

	public List<String> getNombresPaises() {


		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("select nombre from Pais").list();

		sec.close();
		
		return result;

	}
	
	public List<String> getNombresProvincias(int idPais) {

		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("select nombre from Provincia where id_Pais = " + idPais).list();

		sec.close();

		return result;

	}
	
	public List<String> getNombresLocalidades(int idProvincia) {

		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("select nombre from Localidad where id_Provincia = " + idProvincia).list();

		sec.close();

		return result;

	}
	
	public int getNroIdPais(String pais){
		
		Object idPais = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		idPais = sec.createSQLQuery("select id_Pais from Pais where nombre like '" + pais + "'").uniqueResult();
		if (idPais == null)
			idPais = -1;
		sec.close();
		
		return (int) idPais;
		
	}
	
	public int getNroIdProvincia(String prov){
		
		Object idProv = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		idProv = sec.createSQLQuery("select id_Provincia from Provincia where nombre like '" + prov + "'").uniqueResult();
		if (idProv == null)
			idProv = -1;
		sec.close();
		
		return (int) idProv;
		
	}
	
	
}
