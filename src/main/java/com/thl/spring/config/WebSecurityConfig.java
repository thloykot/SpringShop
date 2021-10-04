package com.thl.spring.config;

import com.thl.spring.redis.SessionFilter;
import com.thl.spring.service.impl.LoginUserServise;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginUserServise loginUserServise;
    private final SessionFilter sessionFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserServise);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/register")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .addFilterAfter(sessionFilter, BasicAuthenticationFilter.class);
        http.csrf().disable();

    }
}

