package persistencia;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Client;
import model.Comanda;
import model.Producte;

public class ComandaDAOImpl implements ComandaDAO{
	private EntityManagerFactory emf =ConfigEntityManager.getInstance().getEntityManagerFactory();
	@Override
	public boolean create(Comanda c) {
		EntityManager em=null;
		try{
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		}catch(Exception e) {
			
			  if(em.getTransaction().isActive()) { em.getTransaction().rollback(); }
			 
			throw new ProducteException("Error agregando producto: "+e);
		}finally {
			if (em != null) {
				em.close();
			}
		}
		return true;
	}

	@Override
	public Comanda findById(int id) {
		Comanda c = null;
		try(EntityManager em=emf.createEntityManager()){
			c=em.find(Comanda.class,id);			
		}catch(Exception e) {
			throw new ComandaException("Error buscando comanda: "+e);
		}	
		return c;
	}

	@Override
	public List<Comanda> findByClient(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comanda> findAll() {
		List<Comanda> listaComandas = null;
		try(EntityManager em=emf.createEntityManager()){	    			
			listaComandas=	em.createQuery("SELECT c FROM Comanda c", Comanda.class).getResultList(); 
		}catch(Exception e) {
			throw new ComandaException("Error buscando todos: "+e);
		} 
		return listaComandas;
	}

	@Override
	public boolean update(Comanda c) {
		EntityManager em=null;
		try{
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
		}catch(Exception e) {
			
			  if(em.getTransaction().isActive()) { em.getTransaction().rollback(); }
			 
			throw new ComandaException("Error actualizando comanda: "+e);
		}finally {
			if (em != null) {
				em.close();
			}
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		Comanda c = null;
		try(EntityManager em=emf.createEntityManager()){
			c=em.find(Comanda.class,id);
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			throw new ProducteException("Error borrando comanda: "+e);
		}	
		return true;
	}

}
