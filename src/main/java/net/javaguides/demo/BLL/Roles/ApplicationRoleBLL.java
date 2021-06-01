package net.javaguides.demo.BLL.Roles;


import net.javaguides.demo.ClientModels.ApplicationRoleClient;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;

import java.util.List;
import java.util.Optional;

public interface ApplicationRoleBLL {

    List<ApplicationRoleClient> GetAllApplicationRole();
    ApplicationRoleClient GetApplicationRoleById(Integer id);
    ApplicationRoleClient   CreateApplicationRole(String roleName);
    ApplicationRoleClient UpdateApplicationRole(Integer id,String roleName);
    boolean DeleteApplicationRole(Integer id);

    Optional<ApplicationRole> loadRoleByRoleName(String userName);

}
