package com.novi.hexagon.config;

import com.novi.hexagon.filter.JwtRequestFilter;
import com.novi.hexagon.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/authenticate").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/producers/**").hasRole("ADMIN")
                // ALL ENDPOINTS
//                .antMatchers(HttpMethod.GET,"/api/v1/authenticated").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/v1/authenticate").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/v1/demo").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/v1/demo/{fileName}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/v1/demo/{fileName}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/v1/demo/{fileName}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/v1/{fileName}/comment").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/v1/{fileName}/comment").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/v1/{fileName}/comment").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/v1/{fileName}/feedback").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/v1/{fileName}/feedback").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/v1/{fileName}/feedback").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/v1/downloadFile/{fileName:.+}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/v1/users/").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/v1/users/{username}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/v1/users/{username}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/v1/users/{username}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/v1/users/{username}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/v1/users/{username}/password").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/v1/users/{username}/authorities").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/v1/users/{username}/authorities").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/v1/users/{username}/authorities/{authority}").hasRole("ADMIN")
                // END
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Configuration
    public class WebConfig {

        @Autowired
        private MyCorsFilter corsFilter;

        @Bean
        public FilterRegistrationBean corsFilter() {
            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(corsFilter);
            registration.addUrlPatterns("/*");
            registration.setName("corsFilter");
            registration.setOrder(1);
            return registration;
        }
    }

}