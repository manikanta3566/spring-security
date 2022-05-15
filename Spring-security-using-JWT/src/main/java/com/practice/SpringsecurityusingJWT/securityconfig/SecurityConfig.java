package com.practice.SpringsecurityusingJWT.securityconfig;

import com.practice.SpringsecurityusingJWT.services.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userService;

    public static  final String[] PUBLIC_URLS={
            "/api/user"
    };
    public static  final String[] ADMIN_ROLE_URLS={
            "/api/user/getallusers",  "/api/user/{userid}","/api/auth/adminsettings"
    };
    public static  final String[] USER_ROLES_URLS={
            "/api/auth/login"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS)
                .permitAll()
                .antMatchers(USER_ROLES_URLS).hasAuthority("ROLE_USER")
                .antMatchers(ADMIN_ROLE_URLS).hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/auth/dashboard").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


}
