package com.example.ecommerce_website.security;

import com.example.ecommerce_website.security.JwtUtil;
import com.example.ecommerce_website.security.filter.CustomAuthenticationFilter;
import com.example.ecommerce_website.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests()
//                .antMatchers("/api/user/**").hasAnyAuthority("ADMIN")
//                .antMatchers(POST, "/api/category/**").hasAnyAuthority("ADMIN")
//                .antMatchers(PUT, "/api/category/**").hasAnyAuthority("ADMIN")
//                .antMatchers(DELETE, "/api/category/**").hasAnyAuthority("ADMIN")
//                .antMatchers(POST, "/api/image/**").hasAnyAuthority("ADMIN")
//                .antMatchers(POST, "/api/product/**").hasAnyAuthority("ADMIN")
//                .antMatchers(GET, "/api/product/**").hasAnyAuthority("ADMIN")
//                .antMatchers(GET, "/api/product/search/**").hasAnyAuthority("ADMIN")
//                .antMatchers(PUT, "/api/product/**").hasAnyAuthority("ADMIN")
//                .antMatchers(DELETE, "/api/product/**").hasAnyAuthority("ADMIN");;



        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), jwtUtil));
        http.addFilterBefore(new CustomAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
