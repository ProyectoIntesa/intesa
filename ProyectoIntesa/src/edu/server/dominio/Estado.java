package edu.server.dominio;

import org.hibernate.Session;

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

}
