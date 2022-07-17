package services;

import dataaccess.UserDB;
import java.util.ArrayList;
import models.User;

public class UserService {
    
    public User getUser(String email) {
        UserDB userDB = new UserDB();
        return userDB.getUser(email);
    }
    
    public ArrayList<User> getUsers() {
        UserDB userDB = new UserDB();
        return userDB.getUsers();
    }
    
    public void updateUser(User updatedUser) {
        UserDB userDB = new UserDB();
        userDB.updateUser(updatedUser);
    }
    
    public boolean updateUserPassword(User updatedUser) {
        if(updatedUser.getPassword().trim().isEmpty()) {
            return false;
        }
        
        UserDB userDB = new UserDB();
        userDB.updateUserPassword(updatedUser);
        
        return true;
    }
    
    public void addUser(User newUser) {        
        UserDB userDB = new UserDB();
        userDB.addUser(newUser);
    }
    
    public void deleteUser(String userEmail) {
        UserDB userDB = new UserDB();
        userDB.deleteUser(userEmail);
    }
    
    public boolean checkUserEntry(User user, boolean checkPassword) {
        if(user.getEmail().trim().isEmpty() || user.getEmail().length() > 40) {
            return false;
        }
        
        if(user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 20) {
            return false;
        }
        
        if(user.getLastName().trim().isEmpty() || user.getLastName().length() > 20) {
            return false;
        }
        
        if(checkPassword) {
            if(user.getPassword().trim().isEmpty() || user.getPassword().length() > 20) {
                return false;
            }
        }
        
        return true;
    }
}
