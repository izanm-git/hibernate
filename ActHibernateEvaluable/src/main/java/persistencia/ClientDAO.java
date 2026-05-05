package persistencia;

import java.util.List;
import model.Client;

public interface ClientDAO {
	boolean create(Client c);
	Client findById(int id);
	Client findByName(String name);
	List<Client> findAll();
	boolean update(Client c);
}
