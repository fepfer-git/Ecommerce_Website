package com.example.ecommerce_website.security;

import com.example.ecommerce_website.repository.UserRepository;
import com.example.ecommerce_website.security.JwtUtil;
import com.example.ecommerce_website.security.filter.CustomAuthenticationFilter;
import com.example.ecommerce_website.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/category").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/categories").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/category").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/category/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/image/upload/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/product").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/products/available").permitAll()
                .antMatchers(HttpMethod.GET, "/api/products").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/product").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/product/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "api/product/category/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/product/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/product/search/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/rating").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/api/rating/delete/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/rating/**").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.GET, "/api/sizes").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/size").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/size/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/user").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll();


        http.authorizeRequests().anyRequest().authenticated();


        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), jwtUtil, userRepository));
        http.addFilterBefore(new CustomAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
