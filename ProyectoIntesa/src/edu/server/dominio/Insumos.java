package edu.server.dominio;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Categoria;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.Marca;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.util.HibernateUtil;

public class Insumos {

	public Insumos(){
		
	}
	
	public int categoriaExistente(String categoria) {
		Categoria result;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = (Categoria) sec.createQuery("from Categoria where nombre like '" + categoria + "'").uniqueResult();
		sec.close();
		if (result != null)
			return result.getIdCategoria();
		else
			return -1;
	}
	
	public int marcaExistente(String marca) {
		Marca result;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = (Marca) sec.createQuery("from Marca where nombre like '" + marca + "'").uniqueResult();
		sec.close();
		if (result != null)
			return result.getIdMarca();
		else
			return -1;
	}
	
	public int categoriaConsulta(String categoria, Session sec) {
		Categoria result;
		result = (Categoria) sec.createQuery("from Categoria where nombre like '" + categoria + "'").uniqueResult();
		if (result != null)
			return result.getIdCategoria();
		else
			return -1;
	}
	
	public int marcaConsulta(String marca, Session sec) {
		Marca result;
		result = (Marca) sec.createQuery("from Marca where nombre like '" + marca + "'").uniqueResult();
		if (result != null)
			return result.getIdMarca();
		else
			return -1;
	}
	
	public int getMaxIdMarca(Session sec) {

		Object result;
		result = (Object) sec.createSQLQuery("select Max(id_Marca) from Marca").uniqueResult();
		if (result == null)
			result = 0;
		return (int) result;

	}
	
	public int getMaxIdCategoria(Session sec) {
		Object result;
		result = (Object) sec.createSQLQuery("select Max(id_Categoria) from Categoria").uniqueResult();
		if (result == null)
			result = 0;
		return (int) result;
	}
	
	public int getMaxIdInsumo(Session sec) {
		Object result;
		result = (Object) sec.createSQLQuery("select Max(id_Insumo) from Insumo").uniqueResult();
		if (result == null)
			result = 0;
		return (int) result;
	}
	
	public List<String> getNombresInsumos(String letra) {

		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createSQLQuery("select nombre from Insumo where nombre like '" + letra + "%'").list();

		sec.close();

		return result;

	}
	
	public List<String> getNombresMarcas() {

		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createSQLQuery("select nombre from Marca").list();

		sec.close();

		return result;

	}
	
	public List<String> getNombresCategorias() {

		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createSQLQuery("select nombre from Categoria").list();

		sec.close();

		return result;

	}
	
	public List<Insumo> getInsumosSegunParametro(String tipo, String dato) {

		List<Insumo> result = new LinkedList<Insumo>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		if (tipo.compareTo("insumo") == 0) {

			result = sec.createQuery("from Insumo where nombre like '" + dato + "'").list();

			for (Insumo insumo : result) {

				Marca marca = new Marca();
				Categoria cat = new Categoria();
				marca = (Marca) sec.get(marca.getClass(), insumo.getMarca().getIdMarca());
				cat = (Categoria) sec.get(cat.getClass(), insumo.getCategoria().getIdCategoria());

				insumo.setMarca(marca);
				insumo.setCategoria(cat);

			}

		} else if (tipo.compareTo("marca") == 0) {

			Marca marca = (Marca) sec.createQuery("from Marca where nombre like '" + dato + "'").uniqueResult();

			result = sec.createQuery("from Insumo where id_Marca = " + marca.getIdMarca()).list();

			for (Insumo insumo : result) {

				Categoria cat = new Categoria();
				cat = (Categoria) sec.get(cat.getClass(), insumo.getCategoria().getIdCategoria());

				insumo.setMarca(marca);
				insumo.setCategoria(cat);

			}

		} else if (tipo.compareTo("categoria") == 0) {

			Categoria categoria = (Categoria) sec.createQuery("from Categoria where nombre like '" + dato + "'").uniqueResult();

			result = sec.createQuery("from Insumo where id_Categoria = " + categoria.getIdCategoria()).list();

			for (Insumo insumo : result) {

				Marca marca = new Marca();
				marca = (Marca) sec.get(marca.getClass(), insumo.getMarca().getIdMarca());

				insumo.setMarca(marca);
				insumo.setCategoria(categoria);

			}

		} else if (tipo.compareTo("proveedor") == 0) {

			Proveedor prov = (Proveedor) sec.createQuery("from Proveedor where nombre like '" + dato + "'").uniqueResult();

			List<ProveedorDeInsumo> resultado = new LinkedList<ProveedorDeInsumo>();
			resultado = sec.createQuery("from ProveedorDeInsumo where id_Proveedor = " + prov.getCodigoProveedor()).list();

			for (ProveedorDeInsumo proveedor : resultado) {

				Insumo insu = new Insumo();

				insu = (Insumo) sec.get(insu.getClass(), proveedor.getId().getIdInsumo());

				result.add(insu);

				Marca marca = new Marca();
				Categoria cat = new Categoria();
				marca = (Marca) sec.get(marca.getClass(), insu.getMarca().getIdMarca());
				cat = (Categoria) sec.get(cat.getClass(), insu.getCategoria().getIdCategoria());

				insu.setMarca(marca);
				insu.setCategoria(cat);

			}
		} else if (tipo.compareTo("") == 0) {
						
			result = sec.createQuery("from Insumo").list();
			
			for (Insumo insumo : result) {

				Marca marca = new Marca();
				Categoria cat = new Categoria();
				marca = (Marca) sec.get(marca.getClass(), insumo.getMarca().getIdMarca());
				cat = (Categoria) sec.get(cat.getClass(), insumo.getCategoria().getIdCategoria());

				insumo.setMarca(marca);
				insumo.setCategoria(cat);

			}
		}
		

		sec.close();

		return result;

	}
	
	public Insumo getInsumoCompleto(int idInsumo, String nombreInsumo) {
		
		Insumo result = new Insumo();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = (Insumo) sec.get(result.getClass(), idInsumo);
		Marca marca = new Marca();
		Categoria cat = new Categoria();
		marca = (Marca) sec.get(marca.getClass(), result.getMarca().getIdMarca());
		cat = (Categoria) sec.get(cat.getClass(), result.getCategoria().getIdCategoria());

		result.setMarca(marca);
		result.setCategoria(cat);

		if (!result.getProveedorDeInsumos().isEmpty()) {

			List<ProveedorDeInsumo> resultado = new LinkedList<ProveedorDeInsumo>();
			resultado = sec.createQuery("from ProveedorDeInsumo where id_Insumo = " + result.getIdInsumo()).list();
			for (ProveedorDeInsumo proveedor : resultado) {

				Proveedor prov = (Proveedor) sec.createQuery("from Proveedor where codigo_Proveedor = " + proveedor.getId().getIdProveedor()).uniqueResult();

				proveedor.setProveedor(prov);

				result.getProveedorDeInsumos().add(proveedor);

			}
		}
		sec.close();

		return result;

	}
	
	public List<String> getNombresMarcasSegunInsumo(String nombreInsumo) {

		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createSQLQuery("select m.nombre from marca as m, insumo as i where m.id_marca = i.id_marca and i.nombre like '" + nombreInsumo + "'").list();

		sec.close();

		return result;

	}
	
	public List<String> getNombresProvSegunInsumoYMarca(String nombreInsumo, String nombreMarca){
		
		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		String consulta = "select p.nombre from proveedor as p, insumo as i, marca as m, proveedor_de_insumo as pdi where p.codigo_proveedor = pdi.id_proveedor " +
				"and i.id_insumo = pdi.id_insumo and i.nombre like '"+nombreInsumo+"' and i.id_marca = m.id_marca and m.nombre like '"+nombreMarca+"'";
		
		result = sec.createSQLQuery(consulta).list();

		sec.close();

		return result;
	}
	
	public List<Object> getRequerimientosNecesario(){
		
		List<Object> resultCons = new LinkedList<Object>();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		
		String consulta1 = "create view orcoin as select oci.nro_orden_compra_insumo from orden_compra_insumo as oci, estado_orden as eo " +
				"where oci.id_estado_orden = eo.id_estado_orden and " +
				"( eo.nombre like 'GENERADA' or eo.nombre like 'EDICION' or eo.nombre like 'VALIDADA' or eo.nombre like 'ENVIADA' ) ";
		sec.createSQLQuery(consulta1).executeUpdate();
		
		String consulta2 ="create view renglones as select roci.id_insumo from renglon_orden_compra_insumo as roci, orcoin as t " +
		"where roci.nro_orden_compra_insumo = t.nro_orden_compra_insumo";
		
		sec.createSQLQuery(consulta2).executeUpdate();
		String consulta3 = "select id_insumo from insumo where necesidad_compra = 1 and id_insumo not in (select t.id_insumo from renglones as t)";
		
		resultCons = sec.createSQLQuery(consulta3).list();
		sec.createSQLQuery("drop view orcoin").executeUpdate();
		sec.createSQLQuery("drop view renglones").executeUpdate();
		
		sec.close();

		return resultCons;
		
		
	}
	
	public int getIdInsumo(String nombre, String marca){
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		String consultaMarca = "select id_Marca from Marca where nombre like '"+marca+"'";
		Object idmarca = sec.createSQLQuery(consultaMarca).uniqueResult();
		String consultaInsumo = "select id_Insumo from Insumo where nombre like '"+nombre+"' and id_Marca = "+idmarca;
		Object idInsumo = sec.createSQLQuery(consultaInsumo).uniqueResult();
		sec.close();
		return (int)idInsumo;
	}

	public String getNombreMarca(int idMarca){
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		String nombre;
		Marca marca = new Marca();
		marca = (Marca) sec.get(marca.getClass(), idMarca);
		nombre = marca.getNombre();
		sec.close();
		return nombre;		
		
		
	}
	
	public boolean setCantInsumo(int idInsumo, double cant){
		
		Boolean respuesta = false;
		int result = 0;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();
			result = sec.createSQLQuery("update Insumo set Cantidad = "+cant+" where id_Insumo = "+idInsumo).executeUpdate();
			sec.getTransaction().commit();
			if (result == 1)
				respuesta = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return respuesta;
		
	}




}
