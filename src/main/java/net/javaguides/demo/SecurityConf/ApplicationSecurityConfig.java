package net.javaguides.demo.SecurityConf;

import net.javaguides.demo.BLL.UserDetailsServiceImplementation;
import net.javaguides.demo.JWT.JwtLoginFilter;
import net.javaguides.demo.JWT.JwtVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


          private BCryptPasswordEncoder bCryptPasswordEncoder;
          private UserDetailsServiceImplementation userDetailsServiceImplementation;

          public ApplicationSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
                                           UserDetailsServiceImplementation userDetailsServiceImplementation) {
              this.bCryptPasswordEncoder = bCryptPasswordEncoder;
              this.userDetailsServiceImplementation = userDetailsServiceImplementation;
          }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilterAfter(new JwtVerifier(),JwtLoginFilter.class)
                .authorizeRequests()
                .antMatchers("/login/**","/register/**"
//                        "delete/applicationusers/**","applicationusers/username/**"
                ).permitAll()
                .antMatchers(HttpMethod.GET,"roles/applicationusers/notvalidated").hasAuthority("admin")
                .antMatchers(HttpMethod.PUT,"roles/applicationusers/validate/**").hasAnyAuthority("admin")
                .antMatchers(HttpMethod.PUT,"superAdmin/roles/{roleId}/applicationusers/{userId}").hasAuthority("superAdmin")
                .antMatchers(HttpMethod.DELETE,"superAdmin/roles/{roleId}/applicationusers/{userId}").hasAuthority("superAdmin")
//                .antMatchers(HttpMethod.DELETE,"/applicationusers/**").hasAnyAuthority("superAdmin")
                //an admin can perform that
                //  .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated();
    }


    //this is how you retrieve users from database
    //it's equivalent in this video is at 46:25 min https://www.youtube.com/watch?v=her_7pa0vrg&ab_channel=Amigoscode
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
              DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
              provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
              provider.setUserDetailsService(userDetailsServiceImplementation);
              return provider;
          }

        //we use it to tell spring how he will got the roles and users
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // take a look at 27 min
             auth.authenticationProvider(daoAuthenticationProvider());
            /*
            or you can use them just in memory
            https://www.youtube.com/watch?v=eLRm2HWNhCE&list=PLI4OjXANJOaFv4XCI1wwO7ZDURpNI18Iu&index=5&ab_channel=Sourcesdel%27InformaticienavecPr.MohamedYOUSSFI
            4 min

            or 23min45
             */
          }
}
