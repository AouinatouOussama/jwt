package net.javaguides.demo.DAL.DataBaseEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class ApplicationUser {

        @Id @GeneratedValue
        private Long id;
        private String email;
        private  String username;
        private  String password;
        private String status;
        @ManyToMany (fetch = FetchType.EAGER)
        @JoinTable(
                name = "Association_User_Role",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
        )
        private Collection<ApplicationRole> roles = new ArrayList<>();

        public  ApplicationUser(){

        }

        public ApplicationUser(String email, String username, String password, String status, Collection<ApplicationRole> roles) {
                this.email = email;
                this.username = username;
                this.password = password;
                this.status = status;
                this.roles = roles;
        }

        public Collection<ApplicationRole> getRoles() {
                return roles;
        }

        public void setRoles(Collection<ApplicationRole> roles) {
                this.roles = roles;
        }

        public void AddRole(ApplicationRole applicationRole) {
        this.roles.add(applicationRole);
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
