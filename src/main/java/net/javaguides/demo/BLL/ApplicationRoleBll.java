package net.javaguides.demo.BLL;


import net.javaguides.demo.ClientModels.ApplicationRoleClient;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;

import java.util.List;
import java.util.Optional;

public interface ApplicationRoleBll {

    List<ApplicationRoleClient> GetAllApplicationRole();
    ApplicationRoleClient GetApplicationRoleById(Integer id);
   // ApplicationRoleClient   CreateApplicationRole(String roleName);
    void DeleteApplicationRole(Integer id);

    Optional<ApplicationRole> loadRoleByRoleName(String userName);

}
