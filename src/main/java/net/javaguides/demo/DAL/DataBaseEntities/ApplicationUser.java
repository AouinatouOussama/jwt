package net.javaguides.demo.DAL.DataBaseEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ApplicationUser {

        @Id @GeneratedValue
        private Long id;
        @Column (unique = true)
        private String email;
     //   @Column (unique = true)
        private  String username;
        private String fullName;
        private  String password;
        private String status;
        private String profilePic;

        @ManyToMany (fetch = FetchType.EAGER)
        @JoinTable(
                name = "rolesUserAssociation",
                joinColumns = @JoinColumn(name = "User_id"),
                inverseJoinColumns = @JoinColumn(name = "Role_id")
        )
        private List<ApplicationRole> roles = new ArrayList<>();



        public  ApplicationUser(){
        }
        public ApplicationUser(String email, String username, String fullName, String password, String status, List<ApplicationRole> roles) {
                this.email = email;
                this.username = username;
                this.fullName = fullName;
                this.password = password;
                this.status = status;
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

        public List<ApplicationRole> getRoles() {
                return roles;
        }

        public void setRoles(List<ApplicationRole> roles) {
                this.roles = roles;
        }

        public void AddRole(ApplicationRole applicationRole) {
                if (this.roles == null){
                        this.roles= new ArrayList<ApplicationRole>();
                }
                this.roles.add(applicationRole);
        }

    public boolean RemoveRole(ApplicationRole applicationRole) {
        if (this.roles == null){
            System.out.println("has no role");
        }
        return this.roles.remove(applicationRole);
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

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }


        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        @Override
        public String toString() {
                return "ApplicationUser{" +
                        "id=" + id +
                        ", email='" + email + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", roles='" + roles + '\'' +
                        ", status='" + status + '\'' +
                        '}';
        }



}
