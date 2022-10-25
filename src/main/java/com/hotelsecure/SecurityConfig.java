package com.hotelsecure;

import com.hotelsecure.filter.CustomAuthenticationFilter;
import com.hotelsecure.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@CrossOrigin
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("http://localhost:1012", "http://127.0.0.1:3300", "http://localhost:7001"));
            cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }).and();
        http.cors().and();
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests().antMatchers("/login/**", "/token/refresh").permitAll();
        http.authorizeHttpRequests().antMatchers(GET,"/users/getAll").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(GET,"/missions/getAll").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(POST,"/missions/create").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(PUT,"/missions/changeToProces/{id}").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(PUT,"/missions/changeToDone/{id}").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(POST, "/user/save/**").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(PUT,"/users/lock/{id}").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(PUT,"/users/unLock/{id}").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(PUT,"/users/delete/{id}").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(POST,"/users/create/").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(POST,"/LearenEmglish/trasformDataToXml/").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(POST,"/messages/create").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(GET,"/messages/getAll").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(GET,"/messages/getMessageData/{id}").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeHttpRequests().antMatchers(POST,"/messages/updateGreate/{id}").hasAnyAuthority("ROLE_MANAGER");

        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
