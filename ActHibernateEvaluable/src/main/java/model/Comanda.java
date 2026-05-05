package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comanda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private LocalDate data;
	
	private double total;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Client client;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="comanda_producte",
			joinColumns = @JoinColumn(name="comanda_id"),
			inverseJoinColumns = @JoinColumn(name = "producte_id")
	)
	
	private List<Producte> productes;
	
	public Comanda(int id, LocalDate data, double total, Client client, List<Producte> productes) {
		this.id = id;
		this.data = data;
		this.total = total;
		this.client = client;
		this.productes = productes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Producte> getProductes() {
		return productes;
	}

	public void setProductes(List<Producte> productes) {
		this.productes = productes;
	}
	
}
