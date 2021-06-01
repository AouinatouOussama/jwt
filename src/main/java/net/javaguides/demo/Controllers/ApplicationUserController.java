package net.javaguides.demo.Controllers;

import net.javaguides.demo.BLL.ApplicationUsers.ApplicationUserBLL;
import net.javaguides.demo.ClientModels.ClientModelRequest.RegisterForm;
import net.javaguides.demo.ClientModels.ClientModelRequest.UpdateApplicationUser;
import net.javaguides.demo.ClientModels.ClientModelResponse.ApplicationUserClient;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ApplicationUserController {

    @Autowired
    ApplicationUserBLL applicationUserBLL;

    @GetMapping("applicationusers")
    public List<ApplicationUserClient> GetAllApplicationUser(){
        return applicationUserBLL.GetAllApplicationUser();
    }

    @GetMapping("applicationusers/{id}")
    public ApplicationUserClient GetApplicationUserById(@PathVariable Long id){
        return applicationUserBLL.GetApplicationUserById(id);
    }

    @Secured({"user","admin","superAdmin"})
    @GetMapping("applicationusers/username/{username}")
    public ApplicationUserClient GetApplicationUserByUsername(@PathVariable String username){
        return applicationUserBLL.GetApplicationUserByUsername(username);
    }

    @PostMapping("register")
    public ApplicationUserClient RegisterApplicationUser(@RequestBody RegisterForm registerForm){
        return applicationUserBLL.RegisterApplicationUser(registerForm);
    }

    //the login path is automatically hited by the spring security for attempting to login

    @PutMapping("applicationusers/{id}")
    public ApplicationUserClient UpdateApplicationUser(@PathVariable Long id, @RequestBody UpdateApplicationUser updateApplicationUser){
        return applicationUserBLL.UpdateApplicationUser(id,updateApplicationUser);
    }

    @DeleteMapping("applicationusers/{id}")
    public boolean DeleteApplicationUser(@PathVariable Long id){
        return applicationUserBLL.DeleteApplicationUser(id);
    }


    @PutMapping("applicationusers/{userId}/roles/{roleId}")
    ApplicationUser AddRoleToUser(@PathVariable Long userId,
                                  @PathVariable Integer roleId){

        return applicationUserBLL.AddRoleToUser(userId,roleId);

    }
    @DeleteMapping("applicationusers/{userId}/roles/{roleId}")
    boolean RemoveRoleToUser(@PathVariable Long userId,
                                  @PathVariable Integer roleId){
        return applicationUserBLL.RemoveRoleToUser(userId,roleId);
    }

    @PutMapping("applicationusers/{userId}/")
    ApplicationUserClient ValidateUser(@PathVariable Long userId){

        return applicationUserBLL.Validate(userId);

    }

    @GetMapping("applicationusers/notvalidated")
    public List<ApplicationUserClient> GetAllApplicationUserNotValidated(){
        return applicationUserBLL.GetAllApplicationUserNotValidated();
    }


}
