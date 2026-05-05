package persistencia;
public class DAOFactory {
	private static DAOFactory instance;
	
	private ProducteDAO daoProducte;
	private ClientDAO daoClient;
	private ComandaDAO daoComanda;
	
	private DAOFactory() {
		daoProducte= new ProducteDAOImpl();
		daoClient = new ClientDAOImpl();
		daoComanda = new ComandaDAOImpl();
	}
	
	public static DAOFactory getInstance() {
		if (instance==null) {
			instance=new DAOFactory();
		}
		return instance;
	}

	public ProducteDAO getDaoProducte() {
		return daoProducte;
	}

	public ClientDAO getDaoClient() {
		return daoClient;
	}

	public ComandaDAO getDaoComanda() {
		return daoComanda;
	}
	
}
