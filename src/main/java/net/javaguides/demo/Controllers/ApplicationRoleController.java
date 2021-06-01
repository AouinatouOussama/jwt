package net.javaguides.demo.Controllers;

import net.javaguides.demo.BLL.Roles.ApplicationRoleBLL;
import net.javaguides.demo.ClientModels.ApplicationRoleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationRoleController {
    @Autowired
    ApplicationRoleBLL applicationRoleBLL;

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

}
