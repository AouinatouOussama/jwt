package net.javaguides.demo.ClientModels;

public class ApplicationRoleClient {
    private String roleName ;

    public ApplicationRoleClient(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
