package edu.server.dominio;

import org.hibernate.Session;

import edu.server.util.HibernateUtil;

public class Empleado {
	
	public Empleado(){
		
	}
	
	public int getIdEmpleado(String nombre , String apellido , String rol) {
		Object result = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = sec.createSQLQuery("select e.id_Empleado from Empleado as e, usuario as u where nombre like '"+nombre+"' and apellido like '"+apellido+"' and u.rol like '"+rol+"'").uniqueResult();
		sec.close();
		return (int)result;
	}

	

}
