package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Contacto;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.OrdenCompraInsumo;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.repositorio.RenglonOrdenCompraInsumo;
import edu.server.util.HibernateUtil;

public class Compras {

	public Compras() {

	}

	public Boolean registrarProveedor(Proveedor proveedor) {

		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();

			String nombrePais = proveedor.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
			String nombreProvincia = proveedor.getDireccion().getLocalidad().getProvincia().getNombre();
			String nombreLocalidad = proveedor.getDireccion().getLocalidad().getNombre();

			int paisEx = adminLoc.paisConsulta(nombrePais, sec);
			if (paisEx != -1) {
				proveedor.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			} else {
				sec.save(proveedor.getDireccion().getLocalidad().getProvincia().getPais());
				paisEx = adminLoc.paisConsulta(nombrePais, sec);
				proveedor.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}
			int provEx = adminLoc.provinciaConsulta(nombreProvincia, sec);
			if (provEx != -1) {
				proveedor.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			} else {
				sec.save(proveedor.getDireccion().getLocalidad().getProvincia());
				provEx = adminLoc.provinciaConsulta(nombreProvincia, sec);
				proveedor.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}
			int locEx = adminLoc.localidadConsulta(nombreLocalidad, nombreProvincia, sec);
			if (locEx != -1) {
				proveedor.getDireccion().getLocalidad().setIdLocalidad(locEx);
			} else {
				sec.save(proveedor.getDireccion().getLocalidad());
				locEx = adminLoc.localidadConsulta(nombreLocalidad, nombreProvincia, sec);
				proveedor.getDireccion().getLocalidad().setIdLocalidad(locEx);
			}
			sec.save(proveedor.getDireccion());
			sec.save(proveedor);
			if (proveedor.getContactos().size() > 0) {
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

	public Boolean registrarCambiosProveedor(Proveedor proveedorNuevo) {

		Proveedores prov = new Proveedores();
		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		Proveedor proveedorViejo = prov.getEmpresaCompleta(proveedorNuevo.getNombre());
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		try {

			for (Contacto contacto : proveedorNuevo.getContactos()) {

				if (!proveedorViejo.getContactos().contains(contacto)) {
					contacto.getProveedor().setCodigoProveedor(proveedorViejo.getCodigoProveedor());
					sec.save(contacto);
				}
			}

			for (Contacto contacto : proveedorViejo.getContactos()) {

				if (!proveedorNuevo.getContactos().contains(contacto)) {
					sec.delete(contacto);
				}
			}

			String nombrePais = proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
			String nombreProvincia = proveedorNuevo.getDireccion().getLocalidad().getProvincia().getNombre();
			String nombreLocalidad = proveedorNuevo.getDireccion().getLocalidad().getNombre();

			int paisEx = adminLoc.paisConsulta(nombrePais, sec);
			if (paisEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			} else {
				sec.save(proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais());
				paisEx = adminLoc.paisConsulta(nombrePais, sec);
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}
			int provEx = adminLoc.provinciaConsulta(nombreProvincia, sec);
			if (provEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			} else {
				sec.save(proveedorNuevo.getDireccion().getLocalidad().getProvincia());
				provEx = adminLoc.provinciaConsulta(nombreProvincia, sec);
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}
			int locEx = adminLoc.localidadConsulta(nombreLocalidad, nombreProvincia, sec);
			if (locEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().setIdLocalidad(locEx);
			} else {
				sec.save(proveedorNuevo.getDireccion().getLocalidad());
				locEx = adminLoc.localidadConsulta(nombreLocalidad, nombreProvincia, sec);
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



	public Boolean eliminarContacto(String nombreEmpresa, String nombreContacto) {
		
		Proveedores prov = new Proveedores();
		Contacto contacto = prov.getContactoCompleto(nombreContacto, nombreEmpresa);
		Boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

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
	

	public Boolean eliminarEmpresa(String nombreEmpresa) {
		
		Proveedores prov = new Proveedores();
		Boolean result = false;
		Proveedor emp = prov.getEmpresaCompleta(nombreEmpresa);
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

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



	public Boolean registrarCambiosContacto(Contacto contactoNuevo) {

		Boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

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


	public boolean registrarInsumo(Insumo insumo) {

		boolean result = false;
		Insumos insumos = new Insumos();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();

			String categoria = insumo.getCategoria().getNombre();
			String marca = insumo.getMarca().getNombre();

			int categoriaEx = insumos.categoriaConsulta(categoria, sec);
			if (categoriaEx != -1) {
				insumo.getCategoria().setIdCategoria(categoriaEx);
			} else {
				int idCategoria = insumos.getMaxIdCategoria(sec);
				idCategoria++;
				insumo.getCategoria().setIdCategoria(idCategoria);
				sec.save(insumo.getCategoria());
			}
			int marcaEx = insumos.marcaConsulta(marca, sec);
			if (marcaEx != -1) {
				insumo.getMarca().setIdMarca(marcaEx);
			} else {
				int idMarca = insumos.getMaxIdMarca(sec);
				idMarca++;
				insumo.getMarca().setIdMarca(idMarca);
				sec.save(insumo.getMarca());
			}

			int idInsumo = insumos.getMaxIdInsumo(sec);
			idInsumo++;
			insumo.setIdInsumo(idInsumo);
			sec.save(insumo);
			if (insumo.getProveedorDeInsumos().size() > 0) {
				for (ProveedorDeInsumo proveedor : insumo.getProveedorDeInsumos()) {
					proveedor.setInsumo(insumo);
					proveedor.getId().setIdInsumo(idInsumo);
					sec.save(proveedor);
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
	

	public boolean registrarCambioInsumo(Insumo insumo) {

		boolean result = false;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();

			sec.createQuery("delete from ProveedorDeInsumo where id_Insumo = " + insumo.getIdInsumo()).executeUpdate();

			sec.update(insumo);

			if (insumo.getProveedorDeInsumos().size() > 0) {
				for (ProveedorDeInsumo proveedor : insumo.getProveedorDeInsumos()) {
					proveedor.setInsumo(insumo);
					proveedor.getId().setIdInsumo(insumo.getIdInsumo());
					sec.save(proveedor);
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


	public long getMaxIdOrdenCompraInsumo(Session sec) {
		Object result;
		result = (Object) sec.createSQLQuery("select Max(nro_Orden_Compra_Insumo_Generada) from Orden_Compra_Insumo").uniqueResult();
		if (result == null){
			long primero = 0;
			result = primero;
		}		
		else{
			long nro = Long.parseLong(result.toString());
			result = nro;
		}
		
		
		return (long) result;
	}

	public boolean eliminarInsumo(Insumo insumo) {

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean result = false;

		try {

			sec.beginTransaction();
			sec.createQuery("delete from ProveedorDeInsumo where id_Insumo = " + insumo.getIdInsumo()).executeUpdate();
			sec.delete(insumo);
			sec.getTransaction().commit();
			result = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;

	}

	public boolean registrarOrdenCompraInsumos(OrdenCompraInsumo orden){
		boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();		
		try {
			Object aux = new Object();
			sec.beginTransaction();
			if(orden.getEstadoOrden().getNombre().compareTo("GENERADA") == 0){
			
				Long nroOrden = this.getMaxIdOrdenCompraInsumo(sec)+1;
				orden.setNroOrdenCompraInsumoGenerada(nroOrden);
				
			}
			
			aux= sec.save(orden);
			
			for (RenglonOrdenCompraInsumo renglon : orden.getRenglonOrdenCompraInsumos()) {
				renglon.getId().setNroOrdenCompraInsumo((long) aux);
				sec.save(renglon);
				ProveedorDeInsumo prov = new ProveedorDeInsumo();
				prov = (ProveedorDeInsumo) renglon.getInsumo().getProveedorDeInsumos().toArray()[0];				
				sec.update(prov);
			}
			sec.getTransaction().commit();
			result = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
		return result;
	}


	public List<OrdenCompraInsumo> getOrdenCompraInsumo(int idEstado, int idProv, String fecDesde, String fecHasta){
		
		List<OrdenCompraInsumo> result = new LinkedList<OrdenCompraInsumo>();
		Estado adminEstado = new Estado();
			
		String criterios = " where id_Estado_Orden != "+ adminEstado.getIdEstado("EDICION");
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();	
		sec.beginTransaction();
		
		if(idEstado != 0){
			criterios = criterios+" and id_Estado_Orden = "+idEstado;
		}
		
		if(idProv != 0){
			criterios = criterios+" and id_Proveedor = "+idProv;
		}
		if(fecDesde.compareTo("") != 0){
				criterios = criterios+" and fecha_Edicion between '"+fecDesde+"' and '"+fecHasta+"'";
		}
		
		result = sec.createQuery("from OrdenCompraInsumo"+criterios).list();
		
		sec.close();
		
		return result;
	}
	

	public OrdenCompraInsumo getOrdenCompraInsumoSegunId(long idOrden){
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();		
		
		OrdenCompraInsumo orden = new OrdenCompraInsumo();
		
		orden = (OrdenCompraInsumo) sec.get(orden.getClass(), idOrden);
		
		
		sec.close();
		return orden;
		
		
	}

	
	public boolean cancelarOrdenCompraInsumo(long  idOrden, int estado){
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		try {
			sec.createSQLQuery("update orden_compra_insumo set id_Estado_Orden = "+estado+" where nro_Orden_Compra_Insumo = "+idOrden).executeUpdate();
			sec.getTransaction().commit();
			return true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
	}


	public List<OrdenCompraInsumo> getOrdenCompraInsumoGuardada(){
		
		List<OrdenCompraInsumo> result = new LinkedList<OrdenCompraInsumo>();
		Estado adminEstado = new Estado();
			
		String criterios = " where id_Estado_Orden = "+ adminEstado.getIdEstado("EDICION");
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();	
		sec.beginTransaction();

		result = sec.createQuery("from OrdenCompraInsumo"+criterios).list();
		
		sec.close();
		
		return result;
	}
	

	public List<OrdenCompraInsumo> getOrdenCompraInsumoEnviada(){
		
		List<OrdenCompraInsumo> result = new LinkedList<OrdenCompraInsumo>();
		Estado adminEstado = new Estado();
			
		String criterios = " where id_Estado_Orden = "+ adminEstado.getIdEstado("ENVIADA");
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();	
		sec.beginTransaction();

		result = sec.createQuery("from OrdenCompraInsumo"+criterios).list();
		
		sec.close();
			
		return result;
	}


	
	public int getIdOrdenCompraInsumo(long nroOrden){
		
		Object objeto = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
				
		objeto = sec.createQuery("from OrdenCompraInsumo where nroOrdenCompraInsumoGenerada = "+nroOrden).uniqueResult();
		
		int result = (int) ((OrdenCompraInsumo) objeto).getNroOrdenCompraInsumo();
				
		sec.close();
				
		return result;
		
		
	}


	
}
