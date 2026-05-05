package services;

import java.util.ArrayList;
import java.util.List;

import persistencia.ClientDAO;
import persistencia.ClientException;
import persistencia.DAOFactory;
import model.Client;

public class ClientService {
	private ClientDAO daoClient = DAOFactory.getInstance().getDaoClient();
	
	public void afegirClient(Client c) {
		try {
			if (c.getNom()!=null||!c.getNom().equals("")&&
					c.getEmail()!=null||!c.getEmail().equals("")&&
					c.getTelefon()!=null||!c.getTelefon().equals("")) {
				daoClient.create(c);
			} else {
				throw new ClientException("Datos incorrectos.");
			}
		} catch (ClientException e) {
			System.out.println(e);
		}
	}
	
	public Client buscarClientPerId(int id) {
		Client c=null;
		try {
			c=daoClient.findById(id);
		} catch (ClientException e) {
			System.out.println(e);
		}
		return c;
	}
	
	public Client buscarClientPerNom(String nom) {
		Client c=null;
		try {
			if(nom==null||nom.equals("")) {
				throw new ClientException("Datos incorrectos.");
			}
			c=daoClient.findByName(nom);
		} catch (ClientException e) {
			System.out.println(e);
		}
		return c;
	}
	
	public List<Client> buscarTots(){
		List<Client> listaClientes=new ArrayList<Client>();
		try {
			listaClientes=daoClient.findAll();
		} catch (ClientException e) {
			System.out.println(e);
		}
		return listaClientes;
	}
	
	public void modificarClient(Client c) {
		try {
			if (c.getNom()!=null||!c.getNom().equals("")&&
					c.getEmail()!=null||!c.getEmail().equals("")&&
					c.getTelefon()!=null||!c.getTelefon().equals("")) {
				daoClient.update(c);
			} else {
				throw new ClientException("Datos incorrectos.");
			}
		} catch (ClientException e) {
			System.out.println(e);
		}
	}
}
