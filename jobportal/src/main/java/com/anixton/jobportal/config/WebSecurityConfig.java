package com.anixton.jobportal.config;

import com.anixton.jobportal.services.CustomUserDetailsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    /**
     * URLs that can be accessed without authentication
     */
    private final String[] publicUrl = {"/",
            "/global-search/**",
            "/register",
            "/register/**",
            "/webjars/**",
            "/resources/**",
            "/assets/**",
            "/css/**",
            "/summernote/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/fonts**", "/favicon.ico", "/resources/**", "/error"};


    /**
     * Constructor Injection for WebSecurityConfig.
     *
     * @param customUserDetailsService the custom user details service
     * @param customAuthenticationSuccessHandler the custom authentication success handler
     */
    @Autowired
    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity configuration
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());

        // Configure URL authorization
        http.authorizeHttpRequests(auth-> {
            auth.requestMatchers(publicUrl).permitAll();
            auth.anyRequest().authenticated();
        });

        // Configure form login and logout
        http.formLogin(form  -> form.loginPage("/login").permitAll()
                .successHandler(customAuthenticationSuccessHandler))
                .logout(logout -> {
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/");
                }).cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    /**
     * Creates the authentication provider bean.
     *
     * @return the DaoAuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        return authenticationProvider;
    }

    /**
     * Creates the password encoder bean.
     *
     * @return the PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
