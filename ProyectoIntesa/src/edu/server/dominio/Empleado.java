package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import edu.server.repositorio.IngresoInsumos;
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

	public int getIdEmpleado(String usuario) {
				
		String[] cadena = usuario.split(","); 
		String apellido = cadena[0];
		String nombre = cadena[1];
		nombre = nombre.substring(1);
				
		Object result = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = sec.createSQLQuery("select e.id_Empleado from Empleado as e, usuario as u where nombre like '"+nombre+"' and apellido like '"+apellido+"'").uniqueResult();
		sec.close();
		return (int)result;
	}
	
	
	public String getNobreYApellidoEmpleado(int idEmpleado){
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		String result;
		edu.server.repositorio.Empleado emp = new edu.server.repositorio.Empleado();
		emp = (edu.server.repositorio.Empleado) sec.get(emp.getClass(), idEmpleado);
		result = emp.getApellido()+" "+emp.getNombre();
		sec.close();
		return result;
	}
	
	public edu.server.repositorio.Empleado getEmpleado(int idEmpleado) {

		edu.server.repositorio.Empleado result = new edu.server.repositorio.Empleado();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = (edu.server.repositorio.Empleado) sec.get(result.getClass(), idEmpleado);

		sec.close();

		return result;
	}
	
}
