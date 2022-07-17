package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Role;
import models.User;

public class UserDB {
    //SQL statements
    private final String SELECT_USER          = "SELECT * FROM user WHERE email = ?";
    private final String SELECT_USERS         = "SELECT * FROM user";
    private final String UPDATE_USER          = "UPDATE user SET active=?, first_name=?, last_name=?, role=? WHERE email=?";
    private final String UPDATE_USER_PASSWORD = "UPDATE user SET password=? WHERE email=?";
    private final String INSERT_USER          = "INSERT INTO user(email, active, first_name, last_name, password, role) VALUES(?, ?, ?, ?, ?, ?)";
    private final String DELETE_USER          = "DELETE FROM user WHERE email=?";
    
    public User getUser(String email) {
        User user = null;
        
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            ps = connection.prepareStatement(SELECT_USER);
            ps.setString(1, email);
            
            resultSet = ps.executeQuery();
            
            if(resultSet.next()) {
                user = new User(resultSet.getString(1), resultSet.getBoolean(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), new Role(resultSet.getInt(6)));
            }
            
        } catch (SQLException e) {
            user = null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
        
        return user;
    }
    
    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            ps = connection.prepareStatement(SELECT_USERS);
            
            resultSet = ps.executeQuery();
            
            while(resultSet.next()) {
                userList.add(new User(resultSet.getString(1), resultSet.getBoolean(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), new Role(resultSet.getInt(6))));
            }
            
        } catch (SQLException e) {
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
        
        return userList;
    }
    
    public void updateUser(User updatedUser) {
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        
        try {
            ps = connection.prepareStatement(UPDATE_USER);
            ps.setBoolean(1, updatedUser.getActive());
            ps.setString(2, updatedUser.getFirstName());
            ps.setString(3, updatedUser.getLastName());
            ps.setInt(4, updatedUser.getRole().getRoleId());
            ps.setString(5, updatedUser.getEmail());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
        
    }
    
    public void updateUserPassword(User updatedUser) {
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        
        try {
            ps = connection.prepareStatement(UPDATE_USER_PASSWORD);
            ps.setString(1, updatedUser.getPassword());
            ps.setString(2, updatedUser.getEmail());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
    }
    
    public void addUser(User newUser) {
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        
        try {
            ps = connection.prepareStatement(INSERT_USER);
            ps.setString(1, newUser.getEmail());
            ps.setBoolean(2, newUser.getActive());
            ps.setString(3, newUser.getFirstName());
            ps.setString(4, newUser.getLastName());
            ps.setString(5, newUser.getPassword());
            ps.setInt(6, newUser.getRole().getRoleId());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
    }
    
    public void deleteUser(String userEmail) {
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        
        try {
            ps = connection.prepareStatement(DELETE_USER);
            ps.setString(1, userEmail);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
    }
}
