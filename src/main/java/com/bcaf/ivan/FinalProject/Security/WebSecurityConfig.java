package com.bcaf.ivan.FinalProject.Security;

import com.bcaf.ivan.FinalProject.Security.Service.UserServiceImpl;
import com.bcaf.ivan.FinalProject.Util.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("test@test.com").password("1234")
                .roles("owner").build();
        return new InMemoryUserDetailsManager(user);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] resource = new String[]{
                "/css/**", "/img/**", "/js/**", "/scss/**", "/vendor/**"
        };
        http.authorizeRequests().antMatchers(resource).permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/index").permitAll()
                .usernameParameter("email").permitAll()
                .passwordParameter("password").permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }


}
