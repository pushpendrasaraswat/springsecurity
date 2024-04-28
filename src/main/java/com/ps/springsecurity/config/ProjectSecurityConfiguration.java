package com.ps.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfiguration {

    @Bean
    SecurityFilterChain SpringSecurityCustomFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests ->
                                requests
                                        .requestMatchers("/myAccount", "/myBalance", "/loanDetails", "/myCards")
                                        .authenticated()
                                        .requestMatchers("/contact", "/notices")
                                        .permitAll()
                        //.denyAll()
                ).formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


   /* @Bean
    InMemoryUserDetailsManager getUserDetails() {
       //  ----- -This is the firsy approach for creating in memory user ----- -
       *//* UserDetails admin = User.withDefaultPasswordEncoder()*//*
       *//*         .username("security-admin")*//*
       *//*         .password("admin-password")*//*
       *//*         .build();*//*
       *//* UserDetails user = User.withDefaultPasswordEncoder()*//*
       *//*         .username("security-user")*//*
       *//*         .password("user-password")*//*
       *//*         .build();*//*

       // return new InMemoryUserDetailsManager(admin, user);

          //  ----- -This is the second  approach for creating in memory user ----- -

        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager() ;
        UserDetails admin=User.withUsername("admin").password("12345").authorities("admin").build();
        UserDetails user=User.withUsername("user").password("12345").authorities("read").build();
        inMemoryUserDetailsManager.createUser(admin);
        inMemoryUserDetailsManager.createUser(user);

        return inMemoryUserDetailsManager;
    }*/

    @Bean
    PasswordEncoder createPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    // now we are using user details service so we don´t need to create In memory user details
     @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
