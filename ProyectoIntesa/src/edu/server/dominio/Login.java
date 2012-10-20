package edu.server.dominio;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import edu.server.repositorio.Empleado;
import edu.server.repositorio.Usuario;
import edu.server.util.HibernateUtil;

public class Login {

	public Login() {

	}

	public boolean registrarUsuario(Usuario nuevoUsuario) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();

			sec.save(nuevoUsuario);
			sec.getTransaction().commit();
			respuesta = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
	}

	public int idEmpleado(int nroLegajo)
	{
		int id= 0;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sec.beginTransaction();
            Empleado empleadoResultado = new Empleado();
             empleadoResultado = (Empleado) sec.createQuery("from Empleado where nro_Legajo= "+nroLegajo).list().get(0);
             if(empleadoResultado==null)
            	 id=-1;
             else 
            	 id = empleadoResultado.getIdEmpleado();
            sec.getTransaction().commit();
        } catch (HibernateException he) {
            sec.getTransaction().rollback();
            return -1;
        }

		return id;
	}
}
