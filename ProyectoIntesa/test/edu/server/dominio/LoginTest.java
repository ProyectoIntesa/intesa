package edu.server.dominio;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.server.repositorio.Empleado;
import edu.server.util.HibernateUtil;

public class LoginTest {

	private Login pruebaLogin;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Empleado empleadoPrueba = new Empleado();
		empleadoPrueba.setApellido("tibaldo");
		empleadoPrueba.setNombre("arnaldo");
		empleadoPrueba.setNroLegajo(15);
		empleadoPrueba.setPuesto("sistema");
		
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		sesion.save(empleadoPrueba);
		sesion.getTransaction().commit();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		sesion.createQuery("delete from Empleado").executeUpdate();
		sesion.getTransaction().commit();
	}

	@Before
	public void setUp() throws Exception {
		pruebaLogin = new Login();
	}

	@After
	public void tearDown() throws Exception {
	}


//	@Test
//	public void testRegistrarUsuario() {
//	
//	}
	
	@Test
	public void testidEmpleado() {
		int result = pruebaLogin.idEmpleado(15);
		assertNotSame(-1, result);
	}

}
