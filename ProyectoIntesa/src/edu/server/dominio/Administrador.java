package edu.server.dominio;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Empleado;
import edu.server.repositorio.Usuario;
import edu.server.util.HibernateUtil;

public class Administrador {

	public Administrador() {

	}

	/**
	 * Registra en la base de datos un nuevo empleado
	 * 
	 * @param nuevoEmpleado
	 * @return
	 */

	public boolean registrarEmpleado(Empleado nuevoEmpleado) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			sec.save(nuevoEmpleado);
			sec.getTransaction().commit();
			respuesta = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
	}

	/**
	 * Registra en la base de datos un nuevo usuario para un empleado
	 * correspondiente
	 * 
	 * @param nuevoUsuario
	 * @return
	 */
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

	/**
	 * Permite informar si existe un usuario resgistrado con el nombre y
	 * contraseñia pasado
	 * 
	 * @param nombreDeUsuario
	 * @param pass
	 * @return
	 */
	public boolean usuarioExsistentes(String nombreDeUsuario) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		Usuario usuario = new Usuario();
		usuario = (Usuario) sec.createQuery("from Usuario where usuario like '" + nombreDeUsuario + "'").uniqueResult();
		sec.close();
		if (usuario == null)
			respuesta = false;
		else
			respuesta = true;
		return respuesta;
	}

	/**
	 * Permite obtener el id del empleado con el número de legajo solicitado
	 * 
	 * @param nroLegajo
	 * @return
	 */
	public int idEmpleado(int nroLegajo) {
		int id = -1;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		List<Empleado> empleados = sec.createQuery("from Empleado where nro_Legajo = " + nroLegajo).list();
		sec.close();
		if (empleados.isEmpty())
			id = -1;
		else {
			Empleado empleado = empleados.get(0);
			id = empleado.getIdEmpleado();
		}
		return id;
	}

	
	/**
	 * Elimina un usuario de la base de datos, retorna true si tu exito y flase en caso contrario
	 * @param usuario
	 * @return
	 */
	public boolean eliminarUsuario(Usuario usuario) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			sec.delete(usuario);
			sec.getTransaction().commit();
			respuesta = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
	}

}
