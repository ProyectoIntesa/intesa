package edu.server.dominio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import edu.server.repositorio.Categoria;
import edu.server.repositorio.Contacto;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Marca;
import edu.server.repositorio.OrdenCompraInsumo;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.repositorio.Provincia;
import edu.server.repositorio.RenglonOrdenCompraInsumo;
import edu.server.util.HibernateUtil;

public class Compras {

	public Compras() {

	}

	public Boolean registrarProveedor(Proveedor proveedor) {

		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();

			String nombrePais = proveedor.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
			String nombreProvincia = proveedor.getDireccion().getLocalidad().getProvincia().getNombre();
			String nombreLocalidad = proveedor.getDireccion().getLocalidad().getNombre();

			int paisEx = adminLoc.paisConsulta(nombrePais, sec);
			if (paisEx != -1) {
				proveedor.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			} else {
				sec.save(proveedor.getDireccion().getLocalidad().getProvincia().getPais());
				paisEx = adminLoc.paisConsulta(nombrePais, sec);
				proveedor.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}
			int provEx = adminLoc.provinciaConsulta(nombreProvincia, sec);
			if (provEx != -1) {
				proveedor.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			} else {
				sec.save(proveedor.getDireccion().getLocalidad().getProvincia());
				provEx = adminLoc.provinciaConsulta(nombreProvincia, sec);
				proveedor.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}
			int locEx = adminLoc.localidadConsulta(nombreLocalidad, nombreProvincia, sec);
			if (locEx != -1) {
				proveedor.getDireccion().getLocalidad().setIdLocalidad(locEx);
			} else {
				sec.save(proveedor.getDireccion().getLocalidad());
				locEx = adminLoc.localidadConsulta(nombreLocalidad, nombreProvincia, sec);
				proveedor.getDireccion().getLocalidad().setIdLocalidad(locEx);
			}
			sec.save(proveedor.getDireccion());
			sec.save(proveedor);
			if (proveedor.getContactos().size() > 0) {
				for (Contacto contacto : proveedor.getContactos()) {
					sec.save(contacto);
				}
			}
			sec.getTransaction().commit();
			result = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;

	}

	public Boolean registrarCambiosProveedor(Proveedor proveedorNuevo) {

		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		Proveedor proveedorViejo = this.getEmpresaCompleta(proveedorNuevo.getNombre());
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		try {

			for (Contacto contacto : proveedorNuevo.getContactos()) {

				if (!proveedorViejo.getContactos().contains(contacto)) {
					contacto.getProveedor().setCodigoProveedor(proveedorViejo.getCodigoProveedor());
					sec.save(contacto);
				}
			}

			for (Contacto contacto : proveedorViejo.getContactos()) {

				if (!proveedorNuevo.getContactos().contains(contacto)) {
					sec.delete(contacto);
				}
			}

			String nombrePais = proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().getNombre();
			String nombreProvincia = proveedorNuevo.getDireccion().getLocalidad().getProvincia().getNombre();
			String nombreLocalidad = proveedorNuevo.getDireccion().getLocalidad().getNombre();

			int paisEx = adminLoc.paisConsulta(nombrePais, sec);
			if (paisEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			} else {
				sec.save(proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais());
				paisEx = adminLoc.paisConsulta(nombrePais, sec);
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().getPais().setIdPais(paisEx);
			}
			int provEx = adminLoc.provinciaConsulta(nombreProvincia, sec);
			if (provEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			} else {
				sec.save(proveedorNuevo.getDireccion().getLocalidad().getProvincia());
				provEx = adminLoc.provinciaConsulta(nombreProvincia, sec);
				proveedorNuevo.getDireccion().getLocalidad().getProvincia().setIdProvincia(provEx);
			}
			int locEx = adminLoc.localidadConsulta(nombreLocalidad, nombreProvincia, sec);
			if (locEx != -1) {
				proveedorNuevo.getDireccion().getLocalidad().setIdLocalidad(locEx);
			} else {
				sec.save(proveedorNuevo.getDireccion().getLocalidad());
				locEx = adminLoc.localidadConsulta(nombreLocalidad, nombreProvincia, sec);
				proveedorNuevo.getDireccion().getLocalidad().setIdLocalidad(locEx);
			}

			proveedorNuevo.getDireccion().setIdDireccion(proveedorViejo.getDireccion().getIdDireccion());
			sec.update(proveedorNuevo.getDireccion());
			proveedorNuevo.setCodigoProveedor(proveedorViejo.getCodigoProveedor());
			sec.update(proveedorNuevo);

			sec.getTransaction().commit();

			return true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

	}

	public Proveedor getEmpresaCompleta(String nombre) {

		Proveedor result;
		Direccion dirResult;
		Localidad locResult;
		Provincia provResult;
		Pais paResult;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = (Proveedor) sec.createQuery("from Proveedor where nombre like '" + nombre + "'").uniqueResult();
		dirResult = (Direccion) sec.createQuery("from Direccion where id_Direccion = " + result.getDireccion().getIdDireccion()).uniqueResult();
		result.setDireccion(dirResult);
		locResult = (Localidad) sec.createQuery("from Localidad where id_Localidad = " + dirResult.getLocalidad().getIdLocalidad()).uniqueResult();
		dirResult.setLocalidad(locResult);
		provResult = (Provincia) sec.createQuery("from Provincia where id_Provincia = " + locResult.getProvincia().getIdProvincia()).uniqueResult();
		locResult.setProvincia(provResult);
		paResult = (Pais) sec.createQuery("from Pais where id_Pais = " + provResult.getPais().getIdPais()).uniqueResult();
		provResult.setPais(paResult);

		sec.close();

		return result;

	}

	public List<String> getNombresEmpresas() {

		List<String> result = new LinkedList<String>();

		List<Proveedor> busqueda;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		busqueda = sec.createQuery("from Proveedor").list();

		sec.close();

		for (Proveedor proveedor : busqueda) {
			result.add(proveedor.getNombre());
		}

		return result;

	}

	public List<String> getRubros() {

		List<String> result = new LinkedList<String>();

		List<Proveedor> busqueda;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		busqueda = sec.createQuery("from Proveedor").list();

		sec.close();

		for (Proveedor proveedor : busqueda) {

			if (!result.contains(proveedor.getRubro())) {
				result.add(proveedor.getRubro());
			}

		}

		return result;

	}

	public List<String> getTipos() {

		List<String> result = new LinkedList<String>();

		List<Proveedor> busqueda;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		busqueda = sec.createQuery("from Proveedor").list();

		sec.close();

		for (Proveedor proveedor : busqueda) {

			if (!result.contains(proveedor.getTipoProveedor())) {
				result.add(proveedor.getTipoProveedor());
			}

		}

		return result;

	}

	public List<String> getContactos() {

		List<String> result = new LinkedList<String>();

		List<Contacto> busqueda;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		busqueda = sec.createQuery("from Contacto where id_Empresa is not null").list();

		sec.close();

		for (Contacto contacto : busqueda) {
			result.add(contacto.getNombre());
		}

		return result;

	}

	public List<Contacto> getEmpresasPorContacto(String nombre) {

		List<Contacto> result = new LinkedList<Contacto>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Contacto where nombre like '" + nombre + "' and id_Empresa is not null").list();

		sec.close();

		return result;

	}

	public String[] getEmpresaRubroPorIdProveedor(int idProveedor) {

		String[] result = { "", "" };

		Proveedor proveedor = new Proveedor();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		proveedor = (Proveedor) sec.get(proveedor.getClass(), idProveedor);

		sec.close();

		result[0] = proveedor.getNombre();
		result[1] = proveedor.getRubro();

		return result;
	}

	public Contacto getContactoCompleto(String nombreContacto, String nombreEmpresa) {

		Contacto contacto;

		Proveedor proveedor;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		proveedor = (Proveedor) sec.createQuery("from Proveedor where nombre like '" + nombreEmpresa + "'").uniqueResult();

		contacto = (Contacto) sec.createQuery("from Contacto where nombre like '" + nombreContacto + "' and id_Empresa = " + proveedor.getCodigoProveedor())
				.uniqueResult();

		sec.close();

		return contacto;

	}

	public Boolean eliminarContacto(String nombreEmpresa, String nombreContacto) {

		Contacto contacto = this.getContactoCompleto(nombreContacto, nombreEmpresa);
		Boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

			sec.beginTransaction();
			sec.delete(contacto);
			sec.getTransaction().commit();
			result = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;

	}

	public List<Proveedor> getEmpresasPorRubro(String nombre) {

		List<Proveedor> result = new LinkedList<Proveedor>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Proveedor where rubro like '" + nombre + "'").list();

		sec.close();

		return result;

	}

	public List<Proveedor> getEmpresasPorTipo(String nombre) {

		List<Proveedor> result = new LinkedList<Proveedor>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Proveedor where tipo_Proveedor like '" + nombre + "'").list();

		sec.close();

		return result;

	}

	public Boolean eliminarEmpresa(String nombreEmpresa) {
		Boolean result = false;
		Proveedor emp = this.getEmpresaCompleta(nombreEmpresa);
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			sec.beginTransaction();
			for (Contacto contacto : emp.getContactos()) {
				contacto.setProveedor(emp);
				sec.delete(contacto);
			}

			sec.delete(emp);
			sec.delete(emp.getDireccion());
			sec.getTransaction().commit();
			result = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
		return result;

	}

	public List<Proveedor> getEmpresas(String nombre) {

		List<Proveedor> result = new LinkedList<Proveedor>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createQuery("from Proveedor where nombre like '" + nombre + "'").list();

		sec.close();

		return result;

	}

	public int retornaIdContacto(String nombreEmpresa, String nombreContacto) {

		Object result;

		String consulta = "select c.id_Contacto from Contacto as c, Proveedor as p where c.nombre like '" + nombreContacto
				+ "' and c.id_Empresa = p.codigo_Proveedor and p.nombre like '" + nombreEmpresa + "'";

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		result = sec.createSQLQuery(consulta).uniqueResult();
		sec.close();
		if (result != null)
			return (int) result;
		else
			return -1;

	}

	public Boolean registrarCambiosContacto(Contacto contactoNuevo) {

		Boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

			sec.beginTransaction();
			sec.update(contactoNuevo);
			sec.getTransaction().commit();
			result = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;
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

	public boolean registrarInsumo(Insumo insumo) {

		boolean result = false;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();

			String categoria = insumo.getCategoria().getNombre();
			String marca = insumo.getMarca().getNombre();

			int categoriaEx = this.categoriaConsulta(categoria, sec);
			if (categoriaEx != -1) {
				insumo.getCategoria().setIdCategoria(categoriaEx);
			} else {
				int idCategoria = this.getMaxIdCategoria(sec);
				idCategoria++;
				insumo.getCategoria().setIdCategoria(idCategoria);
				sec.save(insumo.getCategoria());
			}
			int marcaEx = this.marcaConsulta(marca, sec);
			if (marcaEx != -1) {
				insumo.getMarca().setIdMarca(marcaEx);
			} else {
				int idMarca = this.getMaxIdMarca(sec);
				idMarca++;
				insumo.getMarca().setIdMarca(idMarca);
				sec.save(insumo.getMarca());
			}

			int idInsumo = this.getMaxIdInsumo(sec);
			idInsumo++;
			insumo.setIdInsumo(idInsumo);
			sec.save(insumo);
			if (insumo.getProveedorDeInsumos().size() > 0) {
				for (ProveedorDeInsumo proveedor : insumo.getProveedorDeInsumos()) {
					proveedor.setInsumo(insumo);
					proveedor.getId().setIdInsumo(idInsumo);
					sec.save(proveedor);
				}
			}

			sec.getTransaction().commit();
			result = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;
	}

	public boolean registrarCambioInsumo(Insumo insumo) {

		boolean result = false;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sec.beginTransaction();

			sec.createQuery("delete from ProveedorDeInsumo where id_Insumo = " + insumo.getIdInsumo()).executeUpdate();

			sec.update(insumo);

			if (insumo.getProveedorDeInsumos().size() > 0) {
				for (ProveedorDeInsumo proveedor : insumo.getProveedorDeInsumos()) {
					proveedor.setInsumo(insumo);
					proveedor.getId().setIdInsumo(insumo.getIdInsumo());
					sec.save(proveedor);
				}
			}

			sec.getTransaction().commit();
			result = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

		return result;
	}

	public Proveedor getProveedorPorNombre(String nombre) {

		Proveedor result;

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = (Proveedor) sec.createQuery("from Proveedor where nombre like '" + nombre + "'").uniqueResult();

		sec.close();

		return result;

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
	
	public long getMaxIdOrdenCompraInsumo(Session sec) {
		Object result;
		result = (Object) sec.createSQLQuery("select Max(nro_Orden_Compra_Insumo_Generada) from Orden_Compra_Insumo").uniqueResult();
		if (result == null){
			long primero = 0;
			result = primero;
		}		
		else{
			long nro = Long.parseLong(result.toString());
			result = nro;
		}
		
		
		return (long) result;
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

	public List<String> getNombresProveedores() {

		List<String> result = new LinkedList<String>();

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();

		result = sec.createSQLQuery("select p.nombre from Proveedor_de_Insumo as pdi, Proveedor as p where pdi.id_Proveedor = p.codigo_Proveedor").list();

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

	public boolean eliminarInsumo(Insumo insumo) {

		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean result = false;

		try {

			sec.beginTransaction();
			sec.createQuery("delete from ProveedorDeInsumo where id_Insumo = " + insumo.getIdInsumo()).executeUpdate();
			sec.delete(insumo);
			sec.getTransaction().commit();
			result = true;

		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}

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
	
	public boolean registrarOrdenCompraInsumos(OrdenCompraInsumo orden){
		boolean result = false;
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();		
		try {
			Object aux = new Object();
			sec.beginTransaction();
			if(orden.getEstadoOrden().getNombre().compareTo("GENERADA") == 0){
			
				Long nroOrden = this.getMaxIdOrdenCompraInsumo(sec)+1;
				orden.setNroOrdenCompraInsumoGenerada(nroOrden);
				
			}
			
			aux= sec.save(orden);
			
			for (RenglonOrdenCompraInsumo renglon : orden.getRenglonOrdenCompraInsumos()) {
				renglon.getId().setNroOrdenCompraInsumo((long) aux);
				sec.save(renglon);
				ProveedorDeInsumo prov = new ProveedorDeInsumo();
				prov = (ProveedorDeInsumo) renglon.getInsumo().getProveedorDeInsumos().toArray()[0];				
				sec.update(prov);
			}
			sec.getTransaction().commit();
			result = true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
		return result;
	}

	public List<OrdenCompraInsumo> getOrdenCompraInsumo(int idEstado, int idProv, String fecDesde, String fecHasta){
		
		List<OrdenCompraInsumo> result = new LinkedList<OrdenCompraInsumo>();
		Estado adminEstado = new Estado();
			
		String criterios = " where id_Estado_Orden != "+ adminEstado.getIdEstado("EDICION");
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();	
		sec.beginTransaction();
		
		if(idEstado != 0){
			criterios = criterios+" and id_Estado_Orden = "+idEstado;
		}
		
		if(idProv != 0){
			criterios = criterios+" and id_Proveedor = "+idProv;
		}
		if(fecDesde.compareTo("") != 0){
				criterios = criterios+" and fecha_Edicion between '"+fecDesde+"' and '"+fecHasta+"'";
		}
		
		result = sec.createQuery("from OrdenCompraInsumo"+criterios).list();
		
		sec.close();
		
		return result;
	}
	
	public int getIdProveedor(String nombre){
		Object idProv = new Object();
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		idProv = sec.createSQLQuery("select codigo_Proveedor from Proveedor where nombre like '"+nombre+"'").uniqueResult();
		if (idProv == null)
			idProv = 0;
		sec.close();
		return (int)idProv;
	}

	public String getNombreProveedor(int idProv){
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		String prov = (String) sec.createSQLQuery("select nombre from proveedor where codigo_proveedor = "+idProv).uniqueResult();
		sec.close();
		return prov;
	}
	
	public OrdenCompraInsumo getOrdenCompraInsumoSegunId(long idOrden){
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();		
		
		OrdenCompraInsumo orden = new OrdenCompraInsumo();
		
		orden = (OrdenCompraInsumo) sec.get(orden.getClass(), idOrden);
		
		
		sec.close();
		return orden;
		
		
	}

	public boolean cancelarOrdenCompraInsumo(long  idOrden, int estado){
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();
		sec.beginTransaction();
		try {
			sec.createSQLQuery("update orden_compra_insumo set id_Estado_Orden = "+estado+" where nro_Orden_Compra_Insumo = "+idOrden).executeUpdate();
			sec.getTransaction().commit();
			return true;
		} catch (HibernateException he) {
			sec.getTransaction().rollback();
			return false;
		}
	}

	public List<OrdenCompraInsumo> getOrdenCompraInsumoGuardada(){
		
		List<OrdenCompraInsumo> result = new LinkedList<OrdenCompraInsumo>();
		Estado adminEstado = new Estado();
			
		String criterios = " where id_Estado_Orden = "+ adminEstado.getIdEstado("EDICION");
		
		Session sec = HibernateUtil.getSessionFactory().getCurrentSession();	
		sec.beginTransaction();

		result = sec.createQuery("from OrdenCompraInsumo"+criterios).list();
		
		sec.close();
		
		return result;
	}

}
