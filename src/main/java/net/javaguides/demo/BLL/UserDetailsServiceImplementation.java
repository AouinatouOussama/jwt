package net.javaguides.demo.BLL;

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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ApplicationUser applicationUser =
                applicationUserBLL.
                        loadUserByUsername(userName).
                        orElseThrow(
                                ()->new UsernameNotFoundException("the user " + userName + "was not found ")
                        );
        // it's mandatory to passe the authorities to the user object bellow
        List<GrantedAuthority> authorities = new ArrayList<>();
//        applicationUser.getRoles().forEach(applicationRole -> {
//            authorities.add(new SimpleGrantedAuthority(applicationRole.getRoleName()));
//        });
        // this is a security spring User that should be used here (see importations)
        return new User(applicationUser.getUsername(),applicationUser.getPassword(),authorities);
    }


}
