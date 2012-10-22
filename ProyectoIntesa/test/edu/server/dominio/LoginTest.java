package edu.server.dominio;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.server.repositorio.Empleado;
import edu.server.repositorio.Usuario;
import edu.server.util.HibernateUtil;
import edu.shared.DTO.UsuarioDTO;

public class LoginTest {

	private Login pruebaLogin;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Empleado empleadoPrueba = new Empleado();
		empleadoPrueba.setApellido("tibaldo");
		empleadoPrueba.setNombre("arnaldo");
		empleadoPrueba.setNroLegajo(15);
		empleadoPrueba.setPuesto("sistema");
		
		Usuario usuarioPrueba = new Usuario();
		usuarioPrueba.setEmpleado(empleadoPrueba);
		usuarioPrueba.setUsuario("arnaldo");
		usuarioPrueba.setContrasenia("tibaldo");
		usuarioPrueba.setRol("seo");
		
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		sesion.save(empleadoPrueba);
		sesion.save(usuarioPrueba);
		sesion.getTransaction().commit();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		sesion.createQuery("delete from Usuario").executeUpdate();
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
	
	@Test
	public void getUsuarioTest()
	{
		UsuarioDTO respuesta = pruebaLogin.getUsuario("arnaldo", "tibaldo");
		assertEquals("arnaldo tibaldo", respuesta.nombre);
		assertEquals("seo", respuesta.rol);
	}
	


}
