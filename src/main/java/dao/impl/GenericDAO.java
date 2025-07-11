package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.IGenericDAO;
import mapper.RowMapper;

public class GenericDAO<T> implements IGenericDAO<T>{
	//Get content in db.properties at folder src/main/resources
	ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
	
	//Create connection to database
	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	//Select query
	@Override
	public List<T> getAll(String sql, RowMapper<T> mapper, Object... parameters) {
		try (Connection connection = getConnection()) {
			//PreparedStatement accept input parameters
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Map parameter to sql query
			for (int i = 0; i < parameters.length; i++) {
	            stmt.setObject(i + 1, parameters[i]);
	        }
			
			//Execute query use executeQuery() with DDL(Create, Alter, Drop...)
			try (ResultSet rs = stmt.executeQuery()) {
	            List<T> result = new ArrayList<>();
	            while (rs.next()) {
	                result.add(mapper.mapRow(rs));
	            }
	            return result;
	        }
		} catch (SQLException e) {
	        throw new RuntimeException("Database query error: ", e);
	    }
	}
	
	@Override
	public T getOne(String sql, RowMapper<T> mapper, Object... parameters) {
		try (Connection connection = getConnection()) {
			//PreparedStatement accept input parameters
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Map parameter to sql query
			for (int i = 0; i < parameters.length; i++) {
	            stmt.setObject(i + 1, parameters[i]);
	        }
			
			//Execute query use executeQuery() with DDL(Create, Alter, Drop...)
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return mapper.mapRow(rs);
				}
				return null;
	        }
		} catch (SQLException e) {
	        throw new RuntimeException("Database query error: ", e);
	    }
	}
	
	//Insert query
	@Override
	public int insert(String sql, Object... parameters) {
		ResultSet resultSet = null;
		int id = 0;
		try (Connection connection = getConnection()){
		        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	
		    	//Turn off autocommit to allow rollback when error
		        connection.setAutoCommit(false);

		        for (int i = 0; i < parameters.length; i++) {
		            statement.setObject(i + 1, parameters[i]);
		        }
		        
		        //Execute query use executeUpdate() with DML(Insert, Update, Merge...)
		        statement.executeUpdate();
		        resultSet = statement.getGeneratedKeys();
		        if(resultSet.next()) {
		        	id = resultSet.getInt(1);
		        }
		        
		        //Commit
		        connection.commit(); 
		        
		        return id;		      
		    } catch (SQLException e) {
		        e.printStackTrace();
		        //Rollback if error
		        try (Connection conn = getConnection()) {
		            if (conn != null && !conn.getAutoCommit()) {
		                conn.rollback();
		            }
		        } catch (SQLException rollbackEx) {
		            rollbackEx.printStackTrace();
		        }
		        throw new RuntimeException("Error executing update", e);
		    }
	}
	
	//Update query
	@Override
	public void update(String sql, Object... parameters) {
	    try (Connection connection = getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	        connection.setAutoCommit(false);

	        for (int i = 0; i < parameters.length; i++) {
	            statement.setObject(i + 1, parameters[i]);
	        }
	        
	        statement.executeUpdate();
	        
	        connection.commit(); 
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        //Rollback if error
	        try (Connection conn = getConnection()) {
	            if (conn != null && !conn.getAutoCommit()) {
	                conn.rollback();
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        throw new RuntimeException("Error executing update", e);
	    }
	}
	
	@Override
	public void delete(String sql, Object... parameters) {
	    try (Connection connection = getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	        connection.setAutoCommit(false);

	        for (int i = 0; i < parameters.length; i++) {
	            statement.setObject(i + 1, parameters[i]);
	        }
	        
	        statement.executeUpdate();
	        
	        connection.commit(); 
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        //Rollback if error
	        try (Connection conn = getConnection()) {
	            if (conn != null && !conn.getAutoCommit()) {
	                conn.rollback();
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        throw new RuntimeException("Error executing update", e);
	    }
	}
	
	@Override
	public int getTotal(String sql, Object... parameters) {
		ResultSet resultSet = null;
		int count = 0;
		try(Connection connection = getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			
			for(int i = 0; i<parameters.length; i++) {
				statement.setObject(i+1, parameters[i]);
			}
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			
			return count;
		} catch (SQLException e){
			return 0;
		}
	}
}
