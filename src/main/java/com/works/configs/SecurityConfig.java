package com.works.configs;

import com.works.services.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final JwtFilter jwtFilter;
    final UserDetailService jwtUserDetailService;
    public SecurityConfig(JwtFilter jwtFilter, UserDetailService jwtUserDetailService) {
        this.jwtFilter = jwtFilter;
        this.jwtUserDetailService = jwtUserDetailService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailService).passwordEncoder( jwtUserDetailService.encoder() );
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .deny()
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
              /*  .antMatchers("/product/productSearch").hasRole("user")

                .antMatchers("/product/list").hasAnyRole("user" , "admin")
                .antMatchers("/basket/**").hasRole("admin")
                .antMatchers("/product/**").hasRole("admin")
                .antMatchers("/category/**").hasRole("admin")*/


                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class );
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }



    private static final String[] AUTH_WHITELIST = {
            "/auth",
            "/register",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };




}
