package daos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Client;
import persistencia.ClientDAOImpl;
import persistencia.ClientDAO;

class ClientDAOTest {
	private ClientDAO daoClient = new ClientDAOImpl();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCreate() {
		assertTrue(daoClient.create(new Client("Manolito","13412323HD","123123123")));
	}

	@Test
	void testFindById() {
		daoClient.create(new Client("JoseManuel","13412323HD","123123123"));
		assertEquals("JoseManuel",daoClient.findById(2).getNom());
	}

	@Test
	void testFindByName() {
		daoClient.create(new Client("Torrente","13412323HD","321321321"));
		assertEquals("321321321",daoClient.findByName("Torrente").getTelefon());
	}

	@Test
	void testFindAll() {
		daoClient.create(new Client("Julio","13412323HD","123123123"));
		daoClient.create(new Client("Paco","13412323HD","123123123"));
		daoClient.create(new Client("Juan","13412323HD","123123123"));
		assertEquals(3,daoClient.findAll().size());
	}

	@Test
	void testUpdate() {
		Client c = new Client("Alberto","13412323HD","123123123");
		daoClient.create(c);
		c.setNom("Albertito");
		assertTrue(daoClient.update(c));
		
	}

}
