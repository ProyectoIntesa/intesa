package edu.server.dominio;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.server.repositorio.Cliente;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Provincia;
import edu.server.util.HibernateUtil;

public class VentasTest {

	
	private Ventas pruebaVentas;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete from Cliente where nombre like 'forestal maderas s.a.'").executeUpdate();
		session.createQuery("delete from Direccion where cpa like 'xx3005der'").executeUpdate();
		session.createQuery("delete from Localidad where codigo_Postal like '4887'").executeUpdate();
		session.createQuery("delete from Provincia where nombre like 'zona ss'").executeUpdate();
		session.createQuery("delete from Pais where nombre like 'colombia'").executeUpdate();
		session.getTransaction().commit();	
		
		
		
	}

	@Before
	public void setUp() throws Exception {
		
		pruebaVentas = new Ventas();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void registrarClienteTest() {
		
		boolean respuesta;
		
		
//		private Set<OrdenPedido> ordenPedidos = new HashSet<OrdenPedido>(0);
//		private Set<Contacto>contactos = new HashSet<Contacto>(0);
		
		Pais pais = new Pais();
		pais.setNombre("colombia");
		//pais.setIdPais(1);
		
		Provincia prov = new Provincia();
		prov.setNombre("zona ss");
		prov.setPais(pais);
		//prov.setIdProvincia(1);
		
		Localidad localidad = new Localidad();
		localidad.setCodigoPostal("4887");
		localidad.setNombre("bogota");
		localidad.setProvincia(prov);
		//localidad.setIdLocalidad(1);
		
				
		Direccion direccion = new Direccion();
		direccion.setCalle("alem");
		direccion.setAltura("202");
		direccion.setPiso("-");
		direccion.setOficina("5");
		direccion.setCpa("xx3005der");
		direccion.setLocalidad(localidad);
		
		
		Cliente cliente = new Cliente();
		cliente.setNombre("forestal maderas s.a.");
		cliente.setCuit("20-31457274-3");
		cliente.setResponsable("responsable inscripto");
		cliente.setRubro("mayorista");
		cliente.setTelefono("0342-4589685");
		cliente.setMail("forestalmaderas@gmail.com");
		cliente.setFax("0342-4895689");
		cliente.setPaginaWeb("www.forestalmaderas.com.ar");
		cliente.setObservaciones("no pagan nunca");
		cliente.setDireccion(direccion);
		
		
		
		respuesta = pruebaVentas.registrarCliente(cliente);
		
		assertEquals(true, respuesta);

	}
	
	@Test
	public void registrarClienteDatosEnBaseTest() {
		
		boolean respuesta;
		
		AdministradorLocalidades adminLoc = new AdministradorLocalidades();
//		private Set<OrdenPedido> ordenPedidos = new HashSet<OrdenPedido>(0);
//		private Set<Contacto>contactos = new HashSet<Contacto>(0);
		
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
		
		
		Cliente cliente = new Cliente();
		cliente.setNombre("forestal");
		cliente.setCuit("20-31457274-3");
		cliente.setResponsable("responsable inscripto");
		cliente.setRubro("mayorista");
		cliente.setTelefono("0342-4589685");
		cliente.setMail("forestalmaderas@gmail.com");
		cliente.setFax("0342-4895689");
		cliente.setPaginaWeb("www.forestalmaderas.com.ar");
		cliente.setObservaciones("no pagan nunca");
		cliente.setDireccion(direccion);
		
		
		
		respuesta = pruebaVentas.registrarCliente(cliente);
		
		assertEquals(true, respuesta);
	
	}
	
	
	@Test
	public void getEmpresaCompletaTest(){
		
		Cliente result = new Cliente();
		
		result = pruebaVentas.getEmpresaCompleta("forestal");
		
		System.out.println("result: "+result.getDireccion().getCalle());
		assertEquals(true, result.getNombre().equals("forestal"));
		
		
		
		
	}
	
	
	
	
	

}
