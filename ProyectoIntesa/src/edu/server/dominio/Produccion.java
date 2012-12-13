package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.OrdenCompraInsumo;
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
	
	public RemitoInternoInsumo getRemitoInternoInsumoSegunId(Long id){
		
		System.out.println("---------------------------------------1.1");
		
		RemitoInternoInsumo result = new RemitoInternoInsumo();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = (RemitoInternoInsumo) sec.get(result.getClass(), id);
		
		System.out.println("---------------------------------------1.2");
		
		sec.close();
		return result;
	
	}
	
	
}
