package edu.server.dominio;

import static org.junit.Assert.*;

import java.util.Set;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gwt.dev.util.collect.HashSet;

import edu.server.repositorio.Categoria;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Marca;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.repositorio.ProveedorDeInsumoId;
import edu.server.repositorio.ProveedorDeProductoId;
import edu.server.repositorio.Provincia;
import edu.server.util.HibernateUtil;
import edu.shared.DTO.ProveedorDeInsumosDTO;

public class ComprasTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete from ProveedorDeInsumo where observaciones like 'mmm'").executeUpdate();
		session.createQuery("delete from Proveedor where nombre like 'EGA'").executeUpdate();
		session.createQuery("delete from Insumo where nombre like 'tuerca'").executeUpdate();
		session.createQuery("delete from Marca where nombre like 'john-deere'").executeUpdate();
		session.createQuery("delete from Categoria where nombre like 'repuesto'").executeUpdate();
		session.getTransaction().commit();	
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void registrarInsumoTest() {
		
		boolean result = false;
		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
		Compras pruebaCompras = new Compras();
		Set<ProveedorDeInsumo>provDeInsumo = new HashSet<ProveedorDeInsumo>();
		Marca marca = new Marca();
		marca.setNombre("john-deere");

		
		Categoria categoria = new Categoria();
		categoria.setNombre("repuesto");
	
				
		Insumo insumo = new Insumo();
		insumo.setNombre("tuerca");
		insumo.setMarca(marca);
		insumo.setCategoria(categoria);
		insumo.setLoteCompra(50);
		insumo.setStockSeguridad(10);
		insumo.setObservaciones("tuerca grande");


			
		Pais pais = new Pais();
		pais.setNombre("ARGENTINA");
		pais.setIdPais(adminLoc.paisExtistente("ARGENTINA"));
		
		Provincia prov = new Provincia();
		prov.setNombre("SANTA FE");
		prov.setPais(pais);
		prov.setIdProvincia(adminLoc.provinciaExtistente("SANTA FE"));
		
		Localidad localidad = new Localidad();
		localidad.setCodigoPostal("4887");
		localidad.setNombre("SANTA FE");
		localidad.setProvincia(prov);
		localidad.setIdLocalidad(adminLoc.localidadExtistente("SANTA FE","SANTA FE"));
		
				
		Direccion direccion = new Direccion();
		direccion.setCalle("cullen");
		direccion.setAltura("6430");
		direccion.setPiso("-");
		direccion.setOficina("5");
		direccion.setCpa("xx3005der");
		direccion.setLocalidad(localidad);
		
		Proveedor prove = new Proveedor();
		prove.setNombre("EGA");
		prove.setDireccion(direccion);
		prove.setCuit("5646546");
		prove.setResponsable("si");
		prove.setTipoProveedor("tuerca");
		
		
		
		pruebaCompras.registrarProveedor(prove);
		Proveedor aux = pruebaCompras.getEmpresaCompleta(prove.getNombre());
		
		ProveedorDeInsumoId idPI = new ProveedorDeInsumoId();
		idPI.setIdProveedor(aux.getCodigoProveedor());
		ProveedorDeInsumo provInsumos = new ProveedorDeInsumo();
		provInsumos.setId(idPI);		
		provInsumos.setProveedor(aux);
		provInsumos.setInsumo(insumo);
		provInsumos.setObservaciones("mmm");
		provInsumos.setPrecio(1.0);
		
		provDeInsumo.add(provInsumos);
		
		insumo.setProveedorDeInsumos(provDeInsumo);
		
		result = pruebaCompras.registrarInsumo(insumo);
		
		assertEquals(true, result);
		
		
	}

}
