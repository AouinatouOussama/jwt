package net.javaguides.demo.BLL;

import net.javaguides.demo.BLL.ApplicationUsers.ApplicationUserBLL;
import net.javaguides.demo.ClientModels.CustomUser;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
//we implements the user detail service for the use of spring security
public class UserDetailsServiceImplementation implements UserDetailsService {

    ApplicationUserBLL applicationUserBLL;

    public UserDetailsServiceImplementation(ApplicationUserBLL applicationUserBLL)
    {
        this.applicationUserBLL=applicationUserBLL;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrUserName) throws UsernameNotFoundException {
        ApplicationUser applicationUser =
                applicationUserBLL.
                        loadUserByUsername(emailOrUserName).
                        orElseThrow(
                                ()->new UsernameNotFoundException("the user " + emailOrUserName + "was not found ")
                        );
        // it's mandatory to passe the authorities to the user object bellow
        List<GrantedAuthority> authorities = new ArrayList<>();
        applicationUser.getRoles().forEach(applicationRole -> {
            authorities.add(new SimpleGrantedAuthority(applicationRole.getRoleName()));
        });
        // this is a security spring User that should be used here (see importations)

        if (applicationUser.getUsername() == null || applicationUser.getUsername().equals("")){
            return new User(applicationUser.getEmail(),applicationUser.getPassword(),authorities);
        }
        return new User(applicationUser.getUsername(),applicationUser.getPassword(),authorities);

        // or we will create a custom user Class that inherites from user class and have all data we want
//        CustomUser customUser = new CustomUser(applicationUser.getUsername(),applicationUser1.getPassword(),authorities);
//
//        customUser.setId(applicationUser.getId());
//        customUser.setEmail(applicationUser.getEmail());
//        customUser.setFullName(applicationUser.getFullName());
//        customUser.setStatus(applicationUser.getStatus());
    }


}
