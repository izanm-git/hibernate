package persistencia;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Client;

public class ClientDAOImpl implements ClientDAO{
	private EntityManagerFactory emf = ConfigEntityManager.getInstance().getEntityManagerFactory();
	@Override
	public boolean create(Client c) {
		EntityManager em=null;
		try{
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		}catch(Exception e) {
			
			  if(em.getTransaction().isActive()) { em.getTransaction().rollback(); }
			 
			throw new ClientException("Error agregando cliente: "+e);
		}finally {
			if (em != null) {
				em.close();
			}
		}
		return true;
	}

	@Override
	public Client findById(int id) {
		Client c = null;
		try(EntityManager em=emf.createEntityManager()){
			c=em.find(Client.class,id);			
		}catch(Exception e) {
			throw new ClientException("Error cercant per Id: "+e);
		}	
		return c;
	}

	@Override
	public Client findByName(String name) {
		Client c = null;
		try(EntityManager em=emf.createEntityManager()){
			TypedQuery<Client> query=em.createQuery("SELECT c FROM Client c WHERE c.nom = :nom", Client.class);
			query.setParameter("nom", name);
			c=query.getSingleResult();		
		}catch(Exception e) {
			throw new ClientException("Error buscando por nombre: "+e);
		}	
		return c;
	}

	@Override
	public List<Client> findAll() {
		List<Client> listaClientes = null;
		try(EntityManager em=emf.createEntityManager()){	    			
			listaClientes=	em.createQuery("SELECT c FROM Client c", Client.class).getResultList(); 
		}catch(Exception e) {
			throw new ClientException("Error buscando todos: "+e);
		} 
		return listaClientes;
	}

	@Override
	public boolean update(Client c) {
		EntityManager em=null;
		try{
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
		}catch(Exception e) {
			
			  if(em.getTransaction().isActive()) { em.getTransaction().rollback(); }
			 
			throw new ClientException("Error actualizando cliente: "+e);
		}finally {
			if (em != null) {
				em.close();
			}
		}
		return true;
	}

}
