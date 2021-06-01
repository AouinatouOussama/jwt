package net.javaguides.demo.BLL.ApplicationUsers;


import net.javaguides.demo.BLL.UserDetailsServiceImplementation;
import net.javaguides.demo.ClientModels.ClientModelRequest.RegisterForm;
import net.javaguides.demo.ClientModels.ClientModelRequest.UpdateApplicationUser;
import net.javaguides.demo.ClientModels.ClientModelResponse.ApplicationUserClient;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import net.javaguides.demo.DAL.repository.ApplicationRoleRepo;
import net.javaguides.demo.DAL.repository.ApplicationUserRepo;
import net.javaguides.demo.JWT.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ApplicationUserBLLImpl implements ApplicationUserBLL {


    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @Autowired
    ApplicationRoleRepo applicationRoleRepo;

    @Autowired
    UserDetailsServiceImplementation userDetailsServiceImplementation;





    @Override
    public List<ApplicationUserClient> GetAllApplicationUser() {
        List<ApplicationUserClient> list= new ArrayList<ApplicationUserClient>();
        List<ApplicationUser> applicationUsers= applicationUserRepo.findAll();
        for ( ApplicationUser user: applicationUsers ) {
            ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                    user.getId(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getFullName(),
                    user.getProfilePic(),
                    user.getStatus()
            );
            applicationUserClient.setRoles(user.getRoles());
            list.add(applicationUserClient);
        }

        return list ;
    }

    @Override
    public ApplicationUserClient GetApplicationUserById(Long id) {
          ApplicationUser applicationUser=  applicationUserRepo.findById(id).get() ;
          ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                  applicationUser.getId(),
                  applicationUser.getEmail(),
                  applicationUser.getUsername(),
                  applicationUser.getFullName(),
                  applicationUser.getProfilePic(),
                  applicationUser.getStatus()
          );
          applicationUserClient.setRoles(applicationUser.getRoles());

          return  applicationUserClient;
    }

    @Override
    public ApplicationUserClient RegisterApplicationUser(RegisterForm registerForm) {
        // what if the user already exists in db
        // may application crashes on this exception i think
        //        String usernameOrEmail = registerForm.getUsername() ;
        //        if (usernameOrEmail == ""){
        //            usernameOrEmail = registerForm.getEmail();
        //        }
        //        ApplicationUser userExist = applicationUserRepo.loadUserByUsername(usernameOrEmail).get();
        //            if (userExist == null ){
        //
        //            }
        //            else {
        //            }

        ApplicationUser applicationUser = new ApplicationUser(
                registerForm.getEmail(),
                registerForm.getUsername(),
                registerForm.getFullName(),
                registerForm.getPassword(),
                "need to be validated",
                null);

        ApplicationUser applicationUserSaved = applicationUserRepo.save(applicationUser);
        AddRoleToUser(registerForm.getUsername(),"user");
        //to save those roles to db
        applicationUserRepo.save(applicationUser);

        ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                applicationUserSaved.getId(),
                applicationUserSaved.getEmail(),
                applicationUserSaved.getUsername(),
                applicationUserSaved.getFullName(),
                applicationUserSaved.getProfilePic(),
                applicationUserSaved.getStatus()
        );
        applicationUserClient.setRoles(applicationUserSaved.getRoles());

        return applicationUserClient;
    }

    @Override
    public void AddRoleToUser(String userName, String roleName) {
        ApplicationUser applicationUser = loadUserByUsername(userName).get();
        ApplicationRole applicationRole = applicationRoleRepo.findByRoleName(roleName).get();
        applicationUser.AddRole(applicationRole);

    }

    @Override
    public ApplicationUserClient Validate(Long id) {

        ApplicationUser  applicationUser = applicationUserRepo.findById(id).get();

        if(applicationUser.getStatus()=="need to be validated"){
            applicationUser.setStatus("validated");
        }

        ApplicationUser applicationUserUpdated= applicationUserRepo.save(applicationUser);

        ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                applicationUserUpdated.getId(),
                applicationUser.getEmail(),
                applicationUser.getUsername(),
                applicationUser.getFullName(),
                applicationUser.getProfilePic(),
                applicationUser.getStatus()
        );

        applicationUserClient.setRoles(applicationUser.getRoles());


        return  applicationUserClient;
    }

    @Override
    public List<ApplicationUserClient> GetAllApplicationUserNotValidated() {
        List<ApplicationUserClient> list= new ArrayList<ApplicationUserClient>();
        List<ApplicationUser> applicationUsers= applicationUserRepo.findAll();
        for ( ApplicationUser user: applicationUsers ) {
            if (user.getStatus()!="validated"){
                ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                        user.getId(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getProfilePic(),
                        user.getStatus()
                );
                applicationUserClient.setRoles(user.getRoles());
                list.add(applicationUserClient);
            }
        }

        return list ;
    }

    @Override
    public ApplicationUserClient GetApplicationUserByUsername(String username) {
       ApplicationUser applicationUser = loadUserByUsername(username).get();

        return new ApplicationUserClient(applicationUser.getId(),
                    applicationUser.getEmail(),
                applicationUser.getUsername(),
                applicationUser.getFullName(),
                    applicationUser.getProfilePic(),
                applicationUser.getStatus());

    }


    @Override
    public ApplicationUserClient UpdateApplicationUser(Long id, UpdateApplicationUser updateApplicationUser) {

        ApplicationUser  applicationUser = applicationUserRepo.findById(id).get();

             applicationUser.setEmail(updateApplicationUser.getEmail());
             applicationUser.setUsername(updateApplicationUser.getUsername());
             if (updateApplicationUser.getPassword() != null ){
                 if (!updateApplicationUser.getPassword().equals("")){
                     applicationUser.setPassword(updateApplicationUser.getPassword());
                 }
             }
             applicationUser.setFullName(updateApplicationUser.getFullName());
             applicationUser.setId(id);

             ApplicationUser applicationUserUpdated= applicationUserRepo.save(applicationUser);

             ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                     applicationUserUpdated.getId(),
                     applicationUser.getEmail(),
                     applicationUser.getUsername(),
                     applicationUser.getFullName(),
                     applicationUser.getProfilePic(),
                     applicationUser.getStatus()
             );

        applicationUserClient.setRoles(applicationUser.getRoles());


        return  applicationUserClient;
    }


    @Override
    public ApplicationUserClient UpdateApplicationUserProfilePic(Long id, String imageURL) {
        ApplicationUser applicationUser = applicationUserRepo.findById(id).get();

        applicationUser.setProfilePic(imageURL);

        applicationUserRepo.save(applicationUser);

        ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                applicationUser.getId(),applicationUser.getEmail(),applicationUser.getUsername(), applicationUser.getProfilePic()
        );

        applicationUserClient.setRoles(applicationUser.getRoles());

        return  applicationUserClient;
    }


    @Override
    public boolean DeleteApplicationUser(Long id) {
        if (!applicationUserRepo.existsById(id)) {
            return false;
        };
        applicationUserRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<ApplicationUser> loadUserByUsername(String emailOrUserName) {
        return applicationUserRepo.loadUserByUsername(emailOrUserName);
    }


//    @Override
//    public UserRoles AddRoleToUser(String username, String roleName) {
//        Optional<ApplicationUser> user = applicationUserRepo.loadUserByUsername(username);
//        ApplicationRole role = applicationRoleRepo.findByRoleName(roleName);
//        Collection<ApplicationRole> roles= user.get().getRoles();
//        roles.add(role);
//        user.get().setRoles(roles);
//
//        // more efficient :
//        // user.get().getRoles().add(role)
//
//        Collection<String> rolesString =new ArrayList<>();
//        roles.forEach(applicationRole -> rolesString.add(applicationRole.getRoleName()));
//        UserRoles userRoles = new UserRoles(username,rolesString);
//        return userRoles ;
//    }


    // we can do this association using the username or email since there unique
    @Override
    public ApplicationUser AddRoleToUser(Long userId, Integer roleId) {
        ApplicationUser applicationUser = applicationUserRepo.findById(userId).get();
        ApplicationRole applicationRole = applicationRoleRepo.findById(roleId).get();

        applicationUser.AddRole(applicationRole);

        return applicationUserRepo.save(applicationUser);
    }

    @Override
    public boolean RemoveRoleToUser(Long userId, Integer roleId) {

        ApplicationUser applicationUser = applicationUserRepo.findById(userId).get();
        ApplicationRole applicationRole = applicationRoleRepo.findById(roleId).get();

        boolean b= applicationUser.RemoveRole(applicationRole);

        applicationUserRepo.save(applicationUser);

        return b;

    }


}
