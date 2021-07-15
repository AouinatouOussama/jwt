package net.javaguides.demo.ClientModels.ClientModelResponse;


import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;

import java.util.ArrayList;
import java.util.List;

public class ApplicationUserClient {

    private Long id;
    private String email;
    private  String username;
    private String fullName;
    private String  profilePic;
    private String  status;
    private List<ApplicationRole> roles = new ArrayList<>();


    public ApplicationUserClient() {
    }

    public ApplicationUserClient(Long id, String email, String username,  String fullName ,String profilePic, String status) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.profilePic = profilePic;
        this.fullName = fullName;
        this.status=status;

    }

    public ApplicationUserClient(Long id, String email, String username, String  profilePic) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.profilePic= profilePic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public  void addRoleToRoleNames (ApplicationRole role){
        this.roles.add(role);
    }

    public List<ApplicationRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ApplicationRole> roles) {
        this.roles = roles;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
