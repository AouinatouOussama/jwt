package net.javaguides.demo.BLL;

import net.javaguides.demo.ClientModels.ApplicationRoleClient;
import net.javaguides.demo.DAL.repository.ApplicationRoleRepo;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationRoleBLLImp implements ApplicationRoleBll{

    @Autowired
    ApplicationRoleRepo applicationRoleRepo;

    @Override
    public List<ApplicationRoleClient> GetAllApplicationRole() {
        List<ApplicationRoleClient> roleClients = new ArrayList<>();
        applicationRoleRepo
                .findAll()
                .stream().
                forEach(
                applicationRole -> roleClients.add
                        (new ApplicationRoleClient(applicationRole.getRoleName()))); ;
        return roleClients ;
    }

    @Override
    public ApplicationRoleClient GetApplicationRoleById(Integer id) {
        return
                new ApplicationRoleClient(
                        applicationRoleRepo.findById(id)
                                .get().getRoleName()
                ) ;
    }

//    @Override
//    public ApplicationRoleClient CreateApplicationRole(String roleName) {
//        return applicationRoleRepo.save();
//    }

    @Override
    public void DeleteApplicationRole(Integer id) {
        applicationRoleRepo.delete(applicationRoleRepo.findById(id).get());
    }

    @Override
    public Optional<ApplicationRole> loadRoleByRoleName(String roleName) {
        Optional<ApplicationRole> role = applicationRoleRepo.findByRoleName(roleName);
        return role;
    }
}
