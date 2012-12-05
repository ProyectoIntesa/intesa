package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import edu.server.repositorio.ModoEnvio;
import edu.server.util.HibernateUtil;

public class ModoDeEnvio {
	
	public ModoDeEnvio(){
		
	}
	/**
	 * retorna una lista con el nombre de todos los modos de envío regristrados
	 * @return List<String> descripción
	 */
	public List<String> getModoDeEnvio() {
		List<String> result = new LinkedList<String>();
		List<ModoEnvio> consulta = new LinkedList<ModoEnvio>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		consulta = sec.createQuery("from ModoEnvio").list();
		sec.close();
		for (ModoEnvio modoEnvio : consulta) {
			result.add(modoEnvio.getDescripcion());
		}
		
		return result;
	}
	
	/**
	 * retorna el ID de un modo de envío pasandole como parametro su descripción
	 * 
	 * @param descripcion
	 * @return int id del modo
	 */
	public int getIdModoDeEnvio(String descripcion) {
		Object result = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = sec.createSQLQuery("select id_Modo_Envio from Modo_Envio where descripcion like '"+descripcion+"'").uniqueResult();
		sec.close();
		return (int)result;
	}

	
	public String getNombreModoEnvio(int idModo){
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		ModoEnvio modo = new ModoEnvio();
		modo = (ModoEnvio) sec.get(modo.getClass(), idModo);
		sec.close();
		return modo.getDescripcion();
	}
	
}
