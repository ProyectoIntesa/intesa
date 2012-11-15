package edu.server.dominio;

import static org.junit.Assert.assertEquals;

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

public class VentasTest {

	
	private Ventas pruebaVentas;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
		pais.setNombre("argentina");
		pais.setIdPais(1);
		
		Provincia prov = new Provincia();
		prov.setNombre("santa fe");
		prov.setPais(pais);
		prov.setIdProvincia(1);
		
		Localidad localidad = new Localidad();
		localidad.setCodigoPostal("3000");
		localidad.setNombre("santa fe");
		localidad.setProvincia(prov);
		localidad.setIdLocalidad(1);
		
				
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

}
