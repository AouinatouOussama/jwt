package net.javaguides.demo.BLL;

import net.javaguides.demo.ClientModels.ApplicationUserClient;
import net.javaguides.demo.ClientModels.RegisterForm;
import net.javaguides.demo.ClientModels.UpdateApplicationUser;
import net.javaguides.demo.ClientModels.UserRoles;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

//All used Methods will be here
public interface ApplicationUserBLL {

    List<ApplicationUserClient> GetAllApplicationUser();
    ApplicationUserClient GetApplicationUserById(Long id);
    ApplicationUserClient   RegisterApplicationUser(RegisterForm registerForm, HttpServletRequest request,
                                                    HttpServletResponse response);
    ApplicationUserClient UpdateApplicationUser(Long id, UpdateApplicationUser updateApplicationUser);
    boolean DeleteApplicationUser(Long id);
    //this one is for Login
    Optional<ApplicationUser> loadUserByUsername(String userName);

    //
    ApplicationRole CreateRole(ApplicationRole role);
    ApplicationUser AddRoleToUser(Long userId, Integer roleId);
}
