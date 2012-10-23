package edu.server.dominio;

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
	 * contrase�a pasado
	 * 
	 * @param nombreDeUsuario
	 * @param pass
	 * @return
	 */
	public boolean usuarioPassExsistentes(String nombreDeUsuario, String pass) {
		boolean respuesta = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		Usuario usuario = new Usuario();
		usuario = (Usuario) sec.createQuery("from Usuario where usuario="
				+ nombreDeUsuario + " and contrasenia=" + pass);
		if (usuario == null)
			respuesta = false;
		else
			respuesta = true;
		return respuesta;
	}

	/**
	 * Permite obtener el id del empleado con el numero de legajo solicitado
	 * 
	 * @param nroLegajo
	 * @return
	 */
	public int idEmpleado(int nroLegajo) {
		int id = 0;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();

		Empleado empleadoResultado = new Empleado();
		empleadoResultado = (Empleado) sec
				.createQuery("from Empleado where nro_Legajo= " + nroLegajo)
				.list().get(0);
		if (empleadoResultado == null)
			id = -1;
		else
			id = empleadoResultado.getIdEmpleado();

		return id;
	}

}
