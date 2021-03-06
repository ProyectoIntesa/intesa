package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.OrdenProvisionInsumo;
import edu.server.repositorio.RemitoInternoInsumo;
import edu.server.repositorio.RenglonOrdenProvisionInsumo;
import edu.server.util.HibernateUtil;

public class Produccion {

	public Produccion() {

	}

	public boolean registrarOrdenProvisionInsumo(OrdenProvisionInsumo orden) {
		boolean result = false;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Object aux = new Object();
			sec.beginTransaction();
			aux = sec.save(orden);

			for (RenglonOrdenProvisionInsumo renglon : orden.getRenglonOrdenProvisionInsumos()) {
				renglon.getId().setIdOrdenProvisionInsumo((long) aux);
				sec.save(renglon);
			}
			sec.getTransaction().commit();
			result = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
		return result;
	}
	
	public long registrarOrdenProvisionInsumo(OrdenProvisionInsumo orden,String nada) {
		long result = -1;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			long aux = (Long) sec.save(orden);
			
			for (RenglonOrdenProvisionInsumo renglon : orden.getRenglonOrdenProvisionInsumos()) {
				renglon.getId().setIdOrdenProvisionInsumo(aux);
				sec.save(renglon);
			}
			sec.getTransaction().commit();
			result = aux;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return -1;
		}
		return result;
	}
	
	
	public List<OrdenProvisionInsumo> getOrdenProvisionInsumo(int idEstado, int idEmpPor, int idEmpPara, String fecDesde, String fecHasta){
		
		List<OrdenProvisionInsumo> result = new LinkedList<OrdenProvisionInsumo>();
		
		String criterios = "";
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();	
		sec.beginTransaction();
		
		if(idEstado != 0){
			criterios = criterios+" where id_Estado_Orden = "+idEstado;
		}
		
		if(idEmpPor != 0){
			if(idEstado != 0){
				criterios = criterios+" and id_Pedido_Por = "+idEmpPor;
			}
			else{
				criterios = criterios+" where id_Pedido_Por = "+idEmpPor;
			}
		}
		
		if(idEmpPara != 0){
			if(idEstado != 0 || idEmpPor != 0){
				criterios = criterios+" and id_Pedido_Para = "+idEmpPara;
			}
			else{
				criterios = criterios+" where id_Pedido_Para = "+idEmpPara;
			}
		}
		if(fecDesde.compareTo("") != 0){
			if(idEstado != 0 || idEmpPor != 0 || idEmpPara != 0){
				criterios = criterios+" and fecha_Edicion between '"+fecDesde+"' and '"+fecHasta+"'";
			}
			else{
				criterios = criterios+" where fecha_Edicion between '"+fecDesde+"' and '"+fecHasta+"'";
			}		
		}		
		
		result = sec.createQuery("from OrdenProvisionInsumo"+criterios).list();
		sec.close();
		return result;
	}

	public OrdenProvisionInsumo getOrdenProvisionInsumoSegunId(long idOrden){
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();		
		
		OrdenProvisionInsumo orden = new OrdenProvisionInsumo();
		
		orden = (OrdenProvisionInsumo) sec.get(orden.getClass(), idOrden);
		
		sec.close();
		return orden;
		
		
	}
	
	public List<RemitoInternoInsumo> getRemitosInternosInsumos(){
		
		List<RemitoInternoInsumo> result = new LinkedList<RemitoInternoInsumo>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from RemitoInternoInsumo").list();
		
		sec.close();
		return result;	
	}
	
	public List<RemitoInternoInsumo> getRemitosInternosInsumosGenerados(int idEstado){
		
		List<RemitoInternoInsumo> result = new LinkedList<RemitoInternoInsumo>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from RemitoInternoInsumo where id_Estado_Remito = "+idEstado).list();
		
		sec.close();
		return result;	
	}
	
	public RemitoInternoInsumo getRemitoInternoInsumoSegunId(Long id){
		
		RemitoInternoInsumo result = new RemitoInternoInsumo();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = (RemitoInternoInsumo) sec.get(result.getClass(), id);
		
		sec.close();
		return result;
	
	}
	
	public List<RemitoInternoInsumo> getRemitosInternosInsumosCerradosDeUnaCiertaOrdenProvisionInsumo(int idEstado, long idOrden){
		
		List<RemitoInternoInsumo> result = new LinkedList<RemitoInternoInsumo>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		result = sec.createQuery("from RemitoInternoInsumo where id_Estado_Remito = "+idEstado+" and id_Orden_Provision_Insumo = "+idOrden).list();
		
		sec.close();
		return result;
	
	}
		
	public boolean validarOrdenProvisionInsumos(Long nroOrden, int estado){

		Boolean respuesta = false;
		int result = 0;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			result = sec.createSQLQuery("update Orden_Provision_Insumo set id_Estado_Orden = "+estado+" where id_Orden_Provision_Insumo = "+nroOrden).executeUpdate();
			sec.getTransaction().commit();
			if (result == 1)
				respuesta = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
		
	}
	
	public boolean cerrarRemitoProvisionInsumos(Long idRemito, int estado, String fecha){

		Boolean respuesta = false;
		int result = 0;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			result = sec.createSQLQuery("update Remito_Interno_Insumo set id_Estado_Remito = "+estado+", fecha_Cierre = '"+fecha+"' where id_Remito_Insumo = "+idRemito).executeUpdate();
			sec.getTransaction().commit();
			if (result == 1)
				respuesta = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
		
	}
	
	public boolean cancelarOrdenesProvisionInsumos(Long nroOrden, int estado){

		Boolean respuesta = false;
		int result = 0;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			result = sec.createSQLQuery("update Orden_Provision_Insumo set id_Estado_Orden = "+estado+" where id_Orden_Provision_Insumo = "+nroOrden).executeUpdate();
			sec.getTransaction().commit();
			if (result == 1)
				respuesta = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
		
	}

	public boolean cerrarOrdenesProvisionInsumos(Long nroOrden, int estado, String fechaCierre){

		Boolean respuesta = false;
		int result = 0;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			result = sec.createSQLQuery("update Orden_Provision_Insumo set id_Estado_Orden = "+estado+", fecha_Cierre = '"+fechaCierre+"' where id_Orden_Provision_Insumo = "+nroOrden).executeUpdate();
			sec.getTransaction().commit();
			if (result == 1)
				respuesta = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
		
	}
	
}
