package persistencia;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfigEntityManager {
	private static ConfigEntityManager instance;
	private EntityManagerFactory emf;
	
	private ConfigEntityManager() {
		emf=Persistence.createEntityManagerFactory("H2_PU");
	}
	
	public static ConfigEntityManager getInstance() {
		if (instance==null) {
			instance=new ConfigEntityManager();
		}
		return instance;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
}
