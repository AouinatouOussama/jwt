package net.javaguides.demo.ClientModels;

import java.util.Collection;

public class UserRoles {

    private String username;
    private Collection<String>  roleName;

    public UserRoles(String username, Collection<String> roleName) {
        this.username = username;
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<String> getRoleName() {
        return roleName;
    }

    public void setRoleName(Collection<String> roleName) {
        this.roleName = roleName;
    }
}
