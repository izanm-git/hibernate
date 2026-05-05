package persistencia;

import java.util.List;

import model.Comanda;

public interface ComandaDAO {
	boolean create(Comanda c);
	Comanda findById(int id);
	List<Comanda> findByClient(int id);
	List<Comanda> findAll();
	boolean update(Comanda c);
	boolean delete(int id);
}
