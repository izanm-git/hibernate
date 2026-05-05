package persistencia;

import java.util.List;

import model.Producte;

public interface ProducteDAO {
	boolean create(Producte p);
	Producte findById(int id);
	Producte findByName(String name);
	List<Producte> findAll();
	boolean updateStock(int id,int stock);
	boolean update(Producte p);
}
