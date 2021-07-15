package net.javaguides.demo.Controllers;

import net.javaguides.demo.BLL.ApplicationUsers.ApplicationUserBLL;
import net.javaguides.demo.BLL.Roles.ApplicationRoleBLL;
import net.javaguides.demo.ClientModels.ApplicationRoleClient;
import net.javaguides.demo.ClientModels.ClientModelResponse.ApplicationUserClient;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationRoleController {
    @Autowired
    ApplicationRoleBLL applicationRoleBLL;

    @Autowired
    ApplicationUserBLL applicationUserBLL;


    // for superAdmin and down there are specified for admin
    @GetMapping("roles")
    public List<ApplicationRoleClient> GetAllApplicationRole(){
        return applicationRoleBLL.GetAllApplicationRole();
    }

    @GetMapping("roles/{id}")
    public ApplicationRoleClient GetApplicationRole(@PathVariable Integer id){
        return applicationRoleBLL.GetApplicationRoleById(id);
    }

    @PostMapping("roles")
    public ApplicationRoleClient RegisterApplicationRole(@RequestBody String roleName){
        return applicationRoleBLL.CreateApplicationRole(roleName);
    }

    @PutMapping("roles/{id}")
    public ApplicationRoleClient UpdateApplicationRole(@PathVariable Integer id,@RequestBody String roleName){
        return applicationRoleBLL.UpdateApplicationRole(id,roleName);
    }

    @DeleteMapping("roles/{id}")
    public boolean DeleteApplicationRole(@PathVariable Integer id){
        return applicationRoleBLL.DeleteApplicationRole(id);
    }


    @PutMapping("superAdmin/roles/{roleId}/applicationusers/{userId}")
    ApplicationUser AddRoleToUser(@PathVariable Long userId,
                                  @PathVariable Integer roleId){

        return applicationUserBLL.AddRoleToUser(userId,roleId);

    }
    @DeleteMapping("superAdmin/roles/{roleId}/applicationusers/{userId}")
    boolean RemoveRoleToUser(@PathVariable Long userId,
                             @PathVariable Integer roleId){
        return applicationUserBLL.RemoveRoleToUser(userId,roleId);
    }


    // for admin
    @GetMapping("roles/applicationusers/notvalidated")
    public List<ApplicationUserClient> GetAllApplicationUserNotValidated(){
        return applicationUserBLL.GetAllApplicationUserNotValidated();
    }

    @PutMapping("roles/applicationusers/validate/{id}")
    public ApplicationUserClient ValidateUser(@PathVariable Long id){
        return applicationUserBLL.Validate(id);

    }

}
