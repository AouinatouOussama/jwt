package net.javaguides.demo.BLL.Roles;

import net.javaguides.demo.ClientModels.ApplicationRoleClient;
import net.javaguides.demo.DAL.repository.ApplicationRoleRepo;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationRoleBLLImp implements ApplicationRoleBLL {

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

    @Override
    public ApplicationRoleClient CreateApplicationRole(String roleName) {
        ApplicationRole applicationRole = new ApplicationRole();
        applicationRole.setRoleName(roleName);
        return new ApplicationRoleClient(applicationRoleRepo.save(applicationRole).getRoleName());
    }

    @Override
    public ApplicationRoleClient UpdateApplicationRole(Integer id,String roleName) {
        ApplicationRole applicationRole = applicationRoleRepo.findById(id).get();
        applicationRole.setRoleName(roleName);
        applicationRoleRepo.save(applicationRole);
        return new ApplicationRoleClient(applicationRole.getRoleName());
    }


    @Override
    public boolean DeleteApplicationRole(Integer id) {
        if (applicationRoleRepo.findById(id).get() != null){

            applicationRoleRepo.delete(applicationRoleRepo.findById(id).get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<ApplicationRole> loadRoleByRoleName(String roleName) {
        Optional<ApplicationRole> role = applicationRoleRepo.findByRoleName(roleName);
        return role;
    }
}
