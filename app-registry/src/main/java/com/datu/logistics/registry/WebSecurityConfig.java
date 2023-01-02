package com.datu.logistics.registry;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**")
                .and().authorizeRequests()
                .antMatchers("/eureka/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }
}
