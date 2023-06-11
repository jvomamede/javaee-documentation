package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mysql.cj.protocol.Resultset;

import model.User;

public class UserDAO {

	
	public void salvar(User user) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        Connection connection = null;
        
        try {
        	connection = ConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query); 
            statement.setString(1, username);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
}
