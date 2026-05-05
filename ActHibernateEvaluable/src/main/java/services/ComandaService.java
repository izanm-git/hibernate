package services;

import java.util.List;

import persistencia.ComandaDAO;
import persistencia.ComandaDAOImpl;
import persistencia.ComandaException;
import model.Client;
import model.Comanda;

public class ComandaService {
	private ComandaDAO daoComanda= new ComandaDAOImpl();
	
	public void afegirComanda(Comanda c) {
		try {
			if(c.getData()==null||
					c.getClient()==null||
					c.getProductes()==null||
					c.getTotal()<0) {
				throw new ComandaException("Datos Incorrectos");
			}
			daoComanda.create(c);
		} catch (ComandaException e) {
			System.out.println(e);
		}
	}
	
	public Comanda buscarComandaPerId(int id) {
		Comanda c = null;
		try {
			c=daoComanda.findById(id);
		} catch (ComandaException e) {
			System.out.println(e);
		}
		return c;
	}
	
	public List<Comanda> buscarComandaPerClient(Client c) {
		List<Comanda> lc = null;
		try {
			if (c!=null) {
				lc=daoComanda.findByClient((int)c.getId());
			} else {
				throw new ComandaException("Valores incorrectos.");
			}
		} catch (ComandaException e) {
			System.out.println(e);
		}
		return lc;
	}
	
	public List<Comanda> buscarTots() {
		List<Comanda> lc = null;
		try {
			lc=daoComanda.findAll();
		} catch (ComandaException e) {
			System.out.println(e);
		}
		return lc;
	}
	
	public void modificarComanda(Comanda c) {
		try {
			if(c.getData()==null||
					c.getClient()==null||
					c.getProductes()==null||
					c.getTotal()<0) {
				throw new ComandaException("Datos Incorrectos");
			}
			daoComanda.update(c);
		} catch (ComandaException e) {
			System.out.println(e);
		}
	}
	
	public boolean esborrarComanda(int id) {
		boolean check=false;
		try {
			check=daoComanda.delete(id);
		} catch (ComandaException e) {
			System.out.println(e);
		}
		return check;
	}
}
