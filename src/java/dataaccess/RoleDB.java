package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Role;

public class RoleDB {
    //SQL statements
    private final String SELECT_ROLE_ID  = "SELECT * FROM role WHERE role_id=?";
    private final String SELECT_ROLE_NAME  = "SELECT * FROM role WHERE role_name=?";
    private final String SELECT_ROLES = "SELECT * FROM role";
    
    public Role getRole(int roleId) {
        Role role = null;
        
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            ps = connection.prepareStatement(SELECT_ROLE_ID);
            ps.setInt(1, roleId);
            
            resultSet = ps.executeQuery();
            
            if(resultSet.next()) {
                role = new Role(resultSet.getInt(1), resultSet.getString(2));
            }
            
        } catch (SQLException e) {
            role = null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
        
        return role;
    }
    
    public Role getRole(String roleName) {
        Role role = null;
        
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            ps = connection.prepareStatement(SELECT_ROLE_NAME);
            ps.setString(1, roleName);
            
            resultSet = ps.executeQuery();
            
            if(resultSet.next()) {
                role = new Role(resultSet.getInt(1), resultSet.getString(2));
            }
            
        } catch (SQLException e) {
            role = null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
        
        return role;
    }
    
    public ArrayList<Role> getAll() {
        ArrayList<Role> roleList = new ArrayList<>();
        
        // Getting connection
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            ps = connection.prepareStatement(SELECT_ROLES);
            
            resultSet = ps.executeQuery();
            
            while(resultSet.next()) {
                roleList.add(new Role(resultSet.getInt(1), resultSet.getString(2)));
            }
            
        } catch (SQLException e) {
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            
            cp.freeConnection(connection);
        }
        
        return roleList;
    }
}
