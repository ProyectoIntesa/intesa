package edu.server.dominio;

import org.hibernate.Session;

import edu.server.repositorio.Pais;
import edu.server.repositorio.Provincia;
import edu.server.util.HibernateUtil;

public class AdministradorLocalidades {

	public AdministradorLocalidades() {

	}

	public int paisExtistente(String pais) {
		Pais result;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = (Pais) sec.createQuery(
				"from Pais where nombre like '" + pais + "'").uniqueResult();
		sec.close();
		if (result != null)
			return result.getIdPais();
		else
			return -1;
	}

	public int provinciaExtistente(String provincia) {
		Provincia result;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = (Provincia) sec.createQuery(
				"from Provincia where nombre like '" + provincia + "'")
				.uniqueResult();
		sec.close();
		if (result != null)
			return result.getIdProvincia();
		else
			return -1;
	}

	public int localidadExtistente(String localidad, String provincia) {
		Object result;
		String consulta = "select l.id_Localidad from Localidad as l, Provincia as p where l.nombre like '"
				+ localidad
				+ "' and p.id_Provincia= l.id_Provincia and p.nombre like '"
				+ provincia + "'";
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result =  sec.createSQLQuery(consulta).uniqueResult();
		sec.close();
		if (result != null)
			return (int) result;
		else
			return -1;
	}
	
	
	public int paisConsulta(String pais, Session sec) {
		Pais result;
		result = (Pais) sec.createQuery(
				"from Pais where nombre like '" + pais + "'").uniqueResult();
		if (result != null)
			return result.getIdPais();
		else
			return -1;
	}

	public int provinciaConsulta(String provincia, Session sec) {
		Provincia result;
		result = (Provincia) sec.createQuery(
				"from Provincia where nombre like '" + provincia + "'")
				.uniqueResult();
		if (result != null)
			return result.getIdProvincia();
		else
			return -1;
	}

	public int localidadConsulta(String localidad, String provincia,Session sec) {
		Object result;
		String consulta = "select l.id_Localidad from Localidad as l, Provincia as p where l.nombre like '"
				+ localidad
				+ "' and p.id_Provincia= l.id_Provincia and p.nombre like '"
				+ provincia + "'";
		result =  sec.createSQLQuery(consulta).uniqueResult();
		if (result != null)
			return (int) result;
		else
			return -1;
	}

}
