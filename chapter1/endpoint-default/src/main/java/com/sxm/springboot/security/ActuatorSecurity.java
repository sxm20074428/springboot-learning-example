package com.sxm.springboot.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * This class is used to create user and set their authentication.
 * Each time login is required when user want to access into the application.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/6/22 0022 上午 10:30
 * @since 0.1
 */
@EnableWebSecurity
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() throws Exception {

        return new InMemoryUserDetailsManager(

                User.withDefaultPasswordEncoder()
                        .username("sxm")
                        .password("123456")
                        .roles("USER")
                        .build(),

                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("123456")
                        .roles("ADMIN","ENDPOINT_ADMIN")
                        .build());

    }

    /**
     * Spring security provides a URL When we request this URL.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        //only allow users with a certain role to access them,
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
                .anyRequest()
                .hasRole("ENDPOINT_ADMIN")
                .and()
                .httpBasic();

        //Additionally, if Spring Security is present, you would need to add custom security configuration that allows unauthenticated access to the endpoints as shown in the following example:
        //.requestMatcher(EndpointRequest.toAnyEndpoint())
        //.authorizeRequests()
        //.anyRequest()
        //.permitAll()
        ;


    }

}  