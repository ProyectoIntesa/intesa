package edu.server.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.server.repositorio.Empleado;
import edu.server.repositorio.Usuario;
import edu.server.util.HibernateUtil;
import edu.shared.DTO.EmpleadoDTO;

public class AdministradorTest {

	private Administrador pruebaAdministrador;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete from Usuario").executeUpdate();
		session.createQuery("delete from Empleado").executeUpdate();
		session.getTransaction().commit();	
	}

	@Before
	public void setUp() throws Exception {
		pruebaAdministrador = new Administrador();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void registrarEmpleadoTest() {
		
		boolean respuesta;
		
		Empleado emp = new Empleado();
		emp.setApellido("chiotti");
		emp.setNombre("pablo");
		emp.setNroLegajo(25);
		emp.setPuesto("lava pisos");
		
		respuesta = pruebaAdministrador.registrarEmpleado(emp);
		
		assertEquals(true, respuesta);
		
		
	}

	@Test
	public void registrarEmpleadoFalseTest() {
		
		boolean respuesta;
		
		Empleado emp = new Empleado();
		emp.setNombre("pablo");
		emp.setNroLegajo(25);
		emp.setPuesto("lava pisos");
		
		respuesta = pruebaAdministrador.registrarEmpleado(emp);
		
		assertEquals(false, respuesta);
				
	}
	
	
	@Test
	public void idEmpleadoTest() {
		
		int idEmpleado = pruebaAdministrador.idEmpleado(25);
		assertNotSame(-1, idEmpleado);	
				
	}
	
	
	@Test
	public void idEmpleadoFalseTest() {
		
		int idEmpleado = pruebaAdministrador.idEmpleado(26);
		assertEquals(-1, idEmpleado);	
				
	}
	

	@Test
	public void registrarUsuarioTest() {
		int idEmpleado = pruebaAdministrador.idEmpleado(25);
		Empleado emp = new Empleado();
		emp.setIdEmpleado(idEmpleado);
		Usuario usr = new Usuario();
		usr.setUsuario("polsky");
		usr.setContrasenia("isirimovich");
		usr.setRol("Chef");
		usr.setEmpleado(emp);
		boolean result = pruebaAdministrador.registrarUsuario(usr);
		assertEquals(true, result);	
				
	}
	
	@Test
	public void registrarUsuarioFalseTest() {
		int idEmpleado = pruebaAdministrador.idEmpleado(35);
		Empleado emp = new Empleado();
		emp.setIdEmpleado(idEmpleado);
		Usuario usr = new Usuario();
		usr.setUsuario("polsky");
		usr.setContrasenia("isirimovich");
		usr.setRol("Chef");
		usr.setEmpleado(emp);
		boolean result = pruebaAdministrador.registrarUsuario(usr);
		assertEquals(false, result);	
				
	}
	
	
	@Test
	public void usuarioExsistentesTest() {

		boolean result = pruebaAdministrador.usuarioExsistentes("polsky");
		assertEquals(true, result);	
				
	}
	
	@Test
	public void usuarioExsistentesFalseTest() {

		boolean result = pruebaAdministrador.usuarioExsistentes("polinsky");
		assertEquals(false, result);	
				
	}
	
	@Test
	public void getEmpleadosTest() {

		List<EmpleadoDTO> result = pruebaAdministrador.getEmpleados();
		assertEquals(false, result.isEmpty());	
				
	}
	
	
}
