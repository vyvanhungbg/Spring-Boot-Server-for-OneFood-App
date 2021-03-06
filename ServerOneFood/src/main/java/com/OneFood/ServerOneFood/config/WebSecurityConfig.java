package com.OneFood.ServerOneFood.config;

import com.OneFood.ServerOneFood.JWT.BeforeAuthenticationFilter;
import com.OneFood.ServerOneFood.JWT.JwtAuthenticationFilter;
import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.LoginFailureHandler;
import com.OneFood.ServerOneFood.exception.LoginSuccessHandler;
import com.OneFood.ServerOneFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {




    @Autowired
    UserService userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, ????? Spring Security s??? d???ng m?? h??a m???t kh???u ng?????i d??ng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung c??p userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung c???p password encoder

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //
        http
                .csrf().disable()
                .cors() // Ng??n ch???n request t??? m???t domain kh??c
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/bill").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/user").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/notification").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/cart").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/food").permitAll();
                //.anyRequest().authenticated(); // T???t c??? c??c request kh??c ?????u c???n ph???i x??c th???c m???i ???????c truy c???p

        http.authorizeRequests().and().exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> new ErrorAccessDeniedException("Access is denied"));
        // Th??m m???t l???p Filter ki???m tra jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
