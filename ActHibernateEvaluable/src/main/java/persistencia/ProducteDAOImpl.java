package persistencia;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Client;
import model.Producte;

public class ProducteDAOImpl implements ProducteDAO{
	private EntityManagerFactory emf = ConfigEntityManager.getInstance().getEntityManagerFactory();
	@Override
	public boolean create(Producte p) {
		EntityManager em=emf.createEntityManager();
		try{
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(p);
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
	public Producte findById(int id) {
		Producte p = null;
		try(EntityManager em=emf.createEntityManager()){
			p=em.find(Producte.class,id);			
		}catch(Exception e) {
			throw new ProducteException("Error buscando producto: "+e);
		}	
		return p;
	}

	@Override
	public Producte findByName(String name) {
		Producte p = null;
		try(EntityManager em=emf.createEntityManager()){
			TypedQuery<Producte> query=em.createQuery("SELECT p FROM Producte p WHERE p.nom = :nom", Producte.class);
			query.setParameter("nom", name);
			p=query.getSingleResult();		
		}catch(Exception e) {
			throw new ProducteException("Error buscando por nombre: "+e);
		}	
		return p;
	}

	@Override
	public List<Producte> findAll() {
		List<Producte> listaProductos = null;
		try(EntityManager em=emf.createEntityManager()){	    			
			listaProductos=	em.createQuery("SELECT p FROM Producte p", Producte.class).getResultList(); 
		}catch(Exception e) {
			throw new ProducteException("Error buscando todos: "+e);
		} 
		return listaProductos;
	}

	@Override
	public boolean updateStock(int id, int stock) {
		Producte p = null;
		try(EntityManager em=emf.createEntityManager()){
			p=em.find(Producte.class,id);
			p.setStock(stock);
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			throw new ProducteException("Error buscando producto: "+e);
		}	
		return true;
	}

	@Override
	public boolean update(Producte p) {
		EntityManager em=null;
		try{
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
		}catch(Exception e) {
			
			  if(em.getTransaction().isActive()) { em.getTransaction().rollback(); }
			 
			throw new ProducteException("Error actualizando producto: "+e);
		}finally {
			if (em != null) {
				em.close();
			}
		}
		return true;
	}

}
