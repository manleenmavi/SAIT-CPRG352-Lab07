package models;

import java.io.Serializable;
import services.RoleService;

public class Role implements Serializable {
    private int roleId;
    private String roleName;

    public Role() {
    }

    public Role(int roleId) {
        this.roleId = roleId;
        RoleService roleService = new RoleService();
        
        this.roleName = roleService.getRoleName(roleId);
    }

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    
}
