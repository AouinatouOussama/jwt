package net.javaguides.demo.DAL.DataBaseEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ApplicationRole {
    @Id @GeneratedValue
    private Integer id;
    private String roleName;
    //to avoid the recursive problem
    @JsonIgnore
    @ManyToMany (mappedBy = "roles")
    private List<ApplicationUser> applicationUsers = new ArrayList<>();

    public List<ApplicationUser> getApplicationUsers() {
        return applicationUsers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
