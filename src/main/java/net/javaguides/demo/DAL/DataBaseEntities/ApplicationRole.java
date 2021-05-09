package net.javaguides.demo.DAL.DataBaseEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class ApplicationRole {
    @Id @GeneratedValue
    private Integer id;
    private String roleName;

    @ManyToMany (mappedBy = "roles")
    private Collection<ApplicationUser> applicationUsers = new ArrayList<>();

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
