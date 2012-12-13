package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import edu.server.repositorio.EstadoOrden;
import edu.server.util.HibernateUtil;

public class Estado {
	
	public Estado(){
		
	}
	
	
	public int getIdEstado(String estado){
		Object idEstado = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		idEstado = (Object) sec.createSQLQuery("select id_Estado_Orden from Estado_Orden where nombre like '"+estado+"'").uniqueResult();
		if (idEstado == null)
			idEstado = 0;
		sec.close();
		return (int) idEstado;
	}

	public List<String> getNombreEstados(){
		
		List<String> estados = new LinkedList<String>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		estados = sec.createSQLQuery("select nombre from estado_orden").list();
		sec.close();
		return estados;
	}
	
	public String getNombreEstado(int idEstado){
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		String estado = (String) sec.createSQLQuery("select nombre from estado_orden where id_estado_orden = "+idEstado).uniqueResult();
		sec.close();
		return estado;
	}
	
	public EstadoOrden getEstadoCompleto(int idEstado){

		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		EstadoOrden result = new EstadoOrden();
		result = (EstadoOrden)sec.get(result.getClass(), idEstado);
		sec.close();
		return result;
		
	}
	
	
}
