package testingwithhsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {
	private final DataSource myDataSource;
	
	public DAO(DataSource dataSource) {
		myDataSource = dataSource;
	}

	/**
	 * Renvoie le nom d'un client à partir de son ID
	 * @param id la clé du client à chercher
	 * @return le nom du client (LastName) ou null si pas trouvé
	 * @throws SQLException 
	 */
	public String nameOfCustomer(int id) throws SQLException {
		String result = null;
		
		String sql = "SELECT LastName FROM Customer WHERE ID = ?";
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql)) {
			statement.setInt(1, id); // On fixe le 1° paramètre de la requête
			try ( ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// est-ce qu'il y a un résultat ? (pas besoin de "while", 
                                        // il y a au plus un enregistrement)
					// On récupère les champs de l'enregistrement courant
					result = resultSet.getString("LastName");
				}
			}
		}
		// dernière ligne : on renvoie le résultat
		return result;
	}
        
        public int insertProduct(ProduitEntity unProduit) throws Exception {

		// Une requête SQL paramétrée
		String sql = "INSERT INTO PRODUCT VALUES (?,?,?)";
		try (   Connection connection = myDataSource.getConnection();
                        //requête préparée : avec paramètre(s)
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur des paramètres
                        //on récupère les valeurs
			stmt.setInt(1, unProduit.getId());
                        stmt.setString(2, unProduit.getName());
                        stmt.setFloat(3, unProduit.getPrice());
			
			return stmt.executeUpdate();

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new Exception(ex.getMessage());
		}
	}
        
        public int productWithCode(int productId) throws Exception {
		int result = 0;
                
		// Une requête SQL paramétrée
		String sql = "SELECT name FROM PRODUCT WHERE ID = ?";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setInt(1, productId);

			try (ResultSet rs = stmt.executeQuery()) {
				rs.next(); // On a toujours exactement 1 enregistrement dans le résultat
				result = rs.getInt("name");
			}
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new Exception(ex.getMessage());
		}
		return result;
	}
	
}
