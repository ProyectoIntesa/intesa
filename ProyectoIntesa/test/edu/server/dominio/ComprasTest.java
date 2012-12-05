package edu.server.dominio;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
import edu.server.repositorio.EstadoOrden;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Marca;
import edu.server.repositorio.ModoEnvio;
import edu.server.repositorio.OrdenCompraInsumo;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.repositorio.ProveedorDeInsumoId;
import edu.server.repositorio.ProveedorDeProductoId;
import edu.server.repositorio.Provincia;
import edu.server.repositorio.RenglonOrdenCompraInsumo;
import edu.server.repositorio.RenglonOrdenCompraInsumoId;
import edu.server.util.HibernateUtil;
import edu.shared.DTO.ProveedorDeInsumosDTO;
import edu.shared.DTO.RenglonOrdenCompraInsumoDTO;

public class ComprasTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		session.createQuery("delete from ProveedorDeInsumo where observaciones like 'mmm'").executeUpdate();
//		session.createQuery("delete from Proveedor where nombre like 'EGA'").executeUpdate();
//		session.createQuery("delete from Insumo where nombre like 'tuerca'").executeUpdate();
//		session.createQuery("delete from Marca where nombre like 'john-deere'").executeUpdate();
//		session.createQuery("delete from Categoria where nombre like 'repuesto'").executeUpdate();
//		session.getTransaction().commit();	
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void registrarInsumoTest() {
//		
//		boolean result = false;
//		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
//		Compras pruebaCompras = new Compras();
//		Set<ProveedorDeInsumo>provDeInsumo = new HashSet<ProveedorDeInsumo>();
//		Marca marca = new Marca();
//		marca.setNombre("john-deere");
//
//		
//		Categoria categoria = new Categoria();
//		categoria.setNombre("repuesto");
//	
//				
//		Insumo insumo = new Insumo();
//		insumo.setNombre("tuerca");
//		insumo.setMarca(marca);
//		insumo.setCategoria(categoria);
//		insumo.setLoteCompra(50);
//		insumo.setStockSeguridad(10);
//		insumo.setObservaciones("tuerca grande");
//
//
//			
//		Pais pais = new Pais();
//		pais.setNombre("ARGENTINA");
//		pais.setIdPais(adminLoc.paisExtistente("ARGENTINA"));
//		
//		Provincia prov = new Provincia();
//		prov.setNombre("SANTA FE");
//		prov.setPais(pais);
//		prov.setIdProvincia(adminLoc.provinciaExtistente("SANTA FE"));
//		
//		Localidad localidad = new Localidad();
//		localidad.setCodigoPostal("4887");
//		localidad.setNombre("SANTA FE");
//		localidad.setProvincia(prov);
//		localidad.setIdLocalidad(adminLoc.localidadExtistente("SANTA FE","SANTA FE"));
//		
//				
//		Direccion direccion = new Direccion();
//		direccion.setCalle("cullen");
//		direccion.setAltura("6430");
//		direccion.setPiso("-");
//		direccion.setOficina("5");
//		direccion.setCpa("xx3005der");
//		direccion.setLocalidad(localidad);
//		
//		Proveedor prove = new Proveedor();
//		prove.setNombre("EGA");
//		prove.setDireccion(direccion);
//		prove.setCuit("5646546");
//		prove.setResponsable("si");
//		prove.setTipoProveedor("tuerca");
//		
//		
//		
//		pruebaCompras.registrarProveedor(prove);
//		Proveedor aux = pruebaCompras.getEmpresaCompleta(prove.getNombre());
//		
//		ProveedorDeInsumoId idPI = new ProveedorDeInsumoId();
//		idPI.setIdProveedor(aux.getCodigoProveedor());
//		ProveedorDeInsumo provInsumos = new ProveedorDeInsumo();
//		provInsumos.setId(idPI);		
//		provInsumos.setProveedor(aux);
//		provInsumos.setInsumo(insumo);
//		provInsumos.setObservaciones("mmm");
//		provInsumos.setPrecio(1.0);
//		
//		provDeInsumo.add(provInsumos);
//		
//		insumo.setProveedorDeInsumos(provDeInsumo);
//		
//		result = pruebaCompras.registrarInsumo(insumo);
//		
//		assertEquals(true, result);
//		
//		
//	}

	@Test
	public void getRequerimientosNecesarioTest(){
		
		
//		Compras pruebaCompras = new Compras();
//		List<Object> result = pruebaCompras.getRequerimientosNecesario();
//		
//		assertEquals(true, result.isEmpty());
		
	}
	
	@Test
	public void registrarOrdenInsumoTest(){
//		OrdenCompraInsumo nueva = new OrdenCompraInsumo();
//		Compras adminCompras = new Compras();
//		Estado adminEstado = new Estado();
//		Empleado adEmpleado = new Empleado();
//		ModoDeEnvio adminModo = new ModoDeEnvio();
//		String nombre = "cristian";
//		String apellido = "ramirez";
//		int idEmpleado = adEmpleado.getIdEmpleado(nombre, apellido, "COMPRAS");
//		int idEstado = adminEstado.getIdEstado("GENERADA");
//		Proveedor prov = adminCompras.getProveedorPorNombre("Ferretería La Pampa SRL");
//		int idModoEnvio = adminModo.getIdModoDeEnvio("en camion") ;
//		edu.server.repositorio.Empleado responsable = new edu.server.repositorio.Empleado();
//		responsable.setIdEmpleado(idEmpleado);
//		responsable.setApellido(apellido);
//		responsable.setNombre(nombre);
//		nueva.setEmpleado(responsable);
//		nueva.setProveedor(prov);
//		EstadoOrden eo = new EstadoOrden();
//		eo.setIdEstadoOrden(idEstado);
//		eo.setNombre("GENERADA");
//		nueva.setEstadoOrden(eo);
//		ModoEnvio me = new ModoEnvio();
//		me.setIdModoEnvio(idModoEnvio);
//		me.setDescripcion("en camion");
//		nueva.setIva(21.0);
//		nueva.setFechaEdicion(new Date());
//		if(new Date() != null){nueva.setFechaGeneracion(new Date()); }
//		nueva.setFormaPago("contado");
//		nueva.setTotal(0.0);
//		nueva.setObservaciones("");
//		
//			RenglonOrdenCompraInsumo renglon = new RenglonOrdenCompraInsumo();
//			RenglonOrdenCompraInsumoId id = new RenglonOrdenCompraInsumoId();
//			id.setIdRenglonOrdenCompraInsumo(1);
//			renglon.setCantidad(1);
//			renglon.setSubtotal(0.0);
//			renglon.setId(id);
//			int idInsumo = adminCompras.getIdInsumo("tornillo","rubens");
//			Insumo insu = new Insumo();
//			insu.setIdInsumo(idInsumo);
//			renglon.setInsumo(insu);
//			nueva.getRenglonOrdenCompraInsumos().add(renglon);
//
//		
//		boolean result = adminCompras.registrarOrdenCompraInsumos(nueva);
//		
//		assertEquals(true, result);
	}
	
	
	@Test
	public void getOrdenCompraInsumoTest(){
		
		Compras adminCompras = new Compras();
//		List<OrdenCompraInsumo> result = adminCompras.getOrdenCompraInsumo(2, 38, null, null);
		//List<OrdenCompraInsumo> result = adminCompras.getOrdenCompraInsumo(2, 38, "30/11/2012", "04/12/2012");
		//assertEquals(5,result.size());
		List<OrdenCompraInsumo> result = adminCompras.getOrdenCompraInsumo(0, 0, "30/11/2012", "04/12/2012");
		assertEquals(10,result.size());
		
		
	}
	
}
