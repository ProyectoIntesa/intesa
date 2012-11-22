package edu.server.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdministradorLocalidadesTest {

	public AdministradorLocalidades pruebaLocal;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		pruebaLocal = new AdministradorLocalidades();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testpaisExtistente() {
		int result = pruebaLocal.paisExtistente("ARGENTINA");
		assertEquals(1, result);
	}

	@Test
	public void testpaisExtistenteFalla() {
		int result = pruebaLocal.paisExtistente("URUGUAY");
		assertEquals(-1, result);
	}

	@Test
	public void testprovinciaExtistente() {
		int result = pruebaLocal.provinciaExtistente("SANTA FE");
		assertEquals(20, result);
	}

	@Test
	public void testprovinciaExtistenteFalla() {
		int result = pruebaLocal.provinciaExtistente("sao pablo");
		assertEquals(-1, result);
	}

	@Test
	public void testlocalidadExtistente() {
		int result = pruebaLocal.localidadExtistente("RECONQUISTA", "SANTA FE");
		assertEquals(16579, result);
	}

	@Test
	public void testlocalidadExtistenteFalla() {
		int result = pruebaLocal.localidadExtistente("RECONQUISTA", "SALTA");
		assertEquals(-1, result);
	}

}
