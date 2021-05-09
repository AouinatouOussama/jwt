package net.javaguides.demo.Controllers;

import net.javaguides.demo.BLL.ApplicationUserBLL;
import net.javaguides.demo.ClientModels.*;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ApplicationUserClient GetApplicationUser(@PathVariable Long id){
        return applicationUserBLL.GetApplicationUserById(id);
    }

    @PostMapping("register")
    public ApplicationUserClient RegisterApplicationUser(@RequestBody RegisterForm registerForm, HttpServletRequest request,
                                                         HttpServletResponse response){
        return applicationUserBLL.RegisterApplicationUser(registerForm,request,response);
    }

    //the login path is automatically hated by the spring security for attempting to login

    @PutMapping("applicationusers/{id}")
    public ApplicationUserClient UpdateApplicationUser(@PathVariable Long id, @RequestBody UpdateApplicationUser updateApplicationUser){
        return applicationUserBLL.UpdateApplicationUser(id,updateApplicationUser);
    }
    @DeleteMapping("applicationusers/{id}")
    public boolean DeleteApplicationUser(@PathVariable Long id){
        return applicationUserBLL.DeleteApplicationUser(id);
    }

//    @PostMapping("applicationusers")
//    public UserRoles AddRoleToUser(@RequestBody UserRole userRole){
//        UserRoles returnedUserRoles= applicationUserBLL.AddRoleToUser(userRole.getUsername(),userRole.getRoleName());
//            return returnedUserRoles;
//    }

    @PutMapping("applicationusers/{userId}/applicationroles/{roleId}")
    ApplicationUser AddRoleToUser(@PathVariable Long userId,
                                  @PathVariable Integer roleId){

        return applicationUserBLL.AddRoleToUser(userId,roleId);

    }
}
