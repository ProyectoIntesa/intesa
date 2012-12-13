package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.IngresoInsumos;
import edu.server.repositorio.IngresoInsumosId;
import edu.server.repositorio.RemitoInternoInsumo;
import edu.server.repositorio.RenglonIngresoInsumos;
import edu.server.repositorio.RenglonRemitoInternoInsumo;
import edu.server.util.HibernateUtil;

public class Almacen {

	public Almacen(){
		
	}
	
	public List<IngresoInsumos> getRemitosExternos(long idOrdenCompraInsumo){
		
		long idoci = idOrdenCompraInsumo;
		List<IngresoInsumos> result = new LinkedList<IngresoInsumos>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();	
		sec.beginTransaction();

		result = sec.createQuery("from IngresoInsumos where id_Orden_Compra_Insumos = "+idOrdenCompraInsumo).list();

		sec.close();
				
		return result;

	}
	
	public Boolean registrarRemitoExterno(IngresoInsumos remito){
		
		Boolean result = false;
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			
			sec.beginTransaction();
			sec.save(remito);
			
			for (RenglonIngresoInsumos renglon : remito.getRenglonIngresoInsumoses()) {
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
	
	public IngresoInsumos getRemitoExternoCompleto(long idOrden, long nroRemito){
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
			
		IngresoInsumosId id = new IngresoInsumosId();
		id.setIdOrdenCompraInsumos(idOrden);
		id.setNroRemitoExterno(nroRemito);
		
		IngresoInsumos result = new IngresoInsumos();

		result.setId(id);
		result = (IngresoInsumos) sec.get(result.getClass(), id);
		sec.close();
				
		return result;
	}
	
	public List<RemitoInternoInsumo> getRemitosInternos(long idOrdenProvisionInsumo){
		
		List<RemitoInternoInsumo> result = new LinkedList<RemitoInternoInsumo>();
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();	
		sec.beginTransaction();

		result = sec.createQuery("from RemitoInternoInsumo where id_Orden_Provision_Insumo = "+idOrdenProvisionInsumo).list();

		sec.close();
				
		return result;

	}
	

	
	public long registrarRemitoProvisionInsumo(RemitoInternoInsumo remito){
		
		long result = -1;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			Object aux = new Object();
			sec.beginTransaction();
			aux = sec.save(remito);		
			sec.getTransaction().commit();
			result = (long) aux;
		} catch (HibernateException he){
			sec.getTransaction().rollback();
		}
		
		return result;
		
	}
	
	public Boolean registrarRenglonesDelRemitoProvisionInsumo(RemitoInternoInsumo remito){
		Boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			sec.beginTransaction();
			for (RenglonRemitoInternoInsumo renglon : remito.getRenglonRemitoInternoInsumos()) {
			sec.save(renglon);	
			}
			sec.getTransaction().commit();
			result = true;
		} catch (HibernateException he){
			sec.getTransaction().rollback();
			return false;
		}
		
		return result;		
		
		
	}
	
	
	
	
}
