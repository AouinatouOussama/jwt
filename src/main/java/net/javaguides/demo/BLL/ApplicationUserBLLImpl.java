package net.javaguides.demo.BLL;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.demo.ClientModels.*;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import net.javaguides.demo.DAL.repository.ApplicationRoleRepo;
import net.javaguides.demo.DAL.repository.ApplicationUserRepo;
import net.javaguides.demo.JWT.JwtLoginFilter;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserBLLImpl implements ApplicationUserBLL {

    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @Autowired
    ApplicationRoleRepo applicationRoleRepo;

    @Autowired
    UserDetailsServiceImplementation userDetailsServiceImplementation;

    JwtLoginFilter jwtLoginFilter ;

    private AuthenticationManager authenticationManager;





    @Override
    public List<ApplicationUserClient> GetAllApplicationUser() {
        List<ApplicationUserClient> list= new ArrayList<ApplicationUserClient>();
        List<ApplicationUser> applicationUsers= applicationUserRepo.findAll();
        for ( ApplicationUser user: applicationUsers ) {
            ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                    user.getId(),
                    user.getEmail(),
                    user.getUsername()
            );
            list.add(applicationUserClient);
        }
        return list ;
    }

    @Override
    public ApplicationUserClient GetApplicationUserById(Long id) {

          Optional<ApplicationUser> applicationUser=  applicationUserRepo.findById(id) ;
          ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                  applicationUser.get().getId(),
                  applicationUser.get().getEmail(),
                  applicationUser.get().getUsername()
          );

          return  applicationUserClient;
    }

    @Override
    public ApplicationUserClient RegisterApplicationUser(RegisterForm registerForm, HttpServletRequest request,
                                                         HttpServletResponse response) {
       if(!registerForm.getPassword().equals(registerForm.getRePassword())){
           throw new RuntimeException("You must confirm your password");
       }
       else{
           ApplicationUser applicationUser = new ApplicationUser(
                   registerForm.getEmail(),
                   registerForm.getUserName(),
                   registerForm.getPassword(),
                   "user",
                   null);

           ApplicationUser applicationUser1 = applicationUserRepo.save(applicationUser);

           // when we save it then we should get back the token

//           try {
//               UserDetails userDetails = userDetailsServiceImplementation.loadUserByUsername(registerForm.getEmail());
//               new JwtLoginFilter(new AuthenticationManager() {
//                   @Override
//                   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                       try {
//                           //pour mettre les info qui arrive depuis l'objet json (Credentials) dans l'objet de la class
//                           Login login = new ObjectMapper()
//                                   .readValue(request.getInputStream(), Login.class);
//                           login.setEmail(registerForm.getEmail());
//                           login.setPassword(registerForm.getPassword());
//                           Authentication authentication1 = new UsernamePasswordAuthenticationToken(
//                                   login.getEmail(),
//                                   login.getPassword()
//                           );
//
//                           System.out.println(login.toString());
//                           return authenticationManager.authenticate(authentication1);
//
//                       } catch (IOException e) {
//                           throw new RuntimeException(e);
//                       }
//                   }
//
//               });
//
//           } catch (Exception e) {
//               System.out.println(",fkf");
//           }


           ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                   applicationUser1.getId(),applicationUser1.getEmail(),applicationUser1.getUsername()
           );
           return applicationUserClient;
       }

    }

    @Override
    public ApplicationUserClient UpdateApplicationUser(Long id, UpdateApplicationUser updateApplicationUser) {
             Optional<ApplicationUser>  applicationUser = applicationUserRepo.findById(id);

             applicationUser.get().setEmail(updateApplicationUser.getEmail());
             applicationUser.get().setUsername(updateApplicationUser.getUsername());
             applicationUser.get().setPassword(updateApplicationUser.getPassword());
             applicationUser.get().setId(id);

             applicationUserRepo.save(applicationUser.get());

             ApplicationUserClient applicationUserClient = new ApplicationUserClient(
                     applicationUser.get().getId(),applicationUser.get().getEmail(),applicationUser.get().getUsername()
             );

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
    public Optional<ApplicationUser> loadUserByUsername(String userName) {
        return applicationUserRepo.loadUserByUsername(userName);
    }

    @Override
    public ApplicationRole CreateRole(ApplicationRole role) {
        return applicationRoleRepo.save(role);
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


    @Override
    public ApplicationUser AddRoleToUser(Long userId, Integer roleId) {
        ApplicationUser applicationUser = applicationUserRepo.findById(userId).get();
        ApplicationRole applicationRole = applicationRoleRepo.findById(roleId).get();

        applicationUser.AddRole(applicationRole);

        return applicationUserRepo.save(applicationUser);
    }
}
