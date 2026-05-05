package services;

import java.util.ArrayList;
import java.util.List;

import persistencia.DAOFactory;
import persistencia.ProducteDAO;
import persistencia.ProducteException;
import model.Producte;

public class ProducteService {
	private ProducteDAO daoProducte=DAOFactory.getInstance().getDaoProducte();
	
	public void afegirProducte(Producte p) {
		try {
			if (p.getNom()!=null||p.getNom().equals("")&&
					p.getPreu()>=0&&
					p.getStock()>=0) {
				daoProducte.create(p);
			} else {
				throw new ProducteException("Datos incorrectos.");
			}
		} catch (ProducteException e) {
			System.out.println(e);
		}
	}
	
	public Producte buscarProductePerId(int id) {
		Producte p=null;
		try {
			p=daoProducte.findById(id);
		} catch (ProducteException e) {
			System.out.println(e);
		}
		return p;
	}
	
	public Producte buscarProductePerNom(String nom) {
		Producte p=null;
		try {
			p=daoProducte.findByName(nom);
		} catch (ProducteException e) {
			System.out.println(e);
		}
		return p;
	}
	
	public List<Producte> buscarTots(){
		List<Producte> listaProductos=new ArrayList<Producte>();
		try {
			listaProductos=daoProducte.findAll();
		} catch (ProducteException e) {
			System.out.println(e);
		}
		return listaProductos;
	}
	
	public void modificarStock(int id,int stock) {
		try {
			if(stock<0) {
				throw new ProducteException("Stock no valido.");
			}
			daoProducte.updateStock(id, stock);
		} catch (ProducteException e) {
			System.out.println(e);
		}
	}
	
	public void modificarProducte(Producte p) {
		try {
			if (p.getNom()!=null||p.getNom().equals("")&&
					p.getPreu()>=0&&
					p.getStock()>=0) {
				daoProducte.update(p);
			} else {
				throw new ProducteException("Datos incorrectos.");
			}
		} catch (ProducteException e) {
			System.out.println(e);
		}
	}
}
