package com.bolsadeideas.bolsadeideas.config.auth;

import com.bolsadeideas.bolsadeideas.config.auth.filters.JwtAuthenticationFilter;
import com.bolsadeideas.bolsadeideas.services.interfaces.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Component
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(config -> config.disable())
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig ->{
                    authConfig.requestMatchers(new AntPathRequestMatcher("/cliente/upload/img/*")).permitAll();
                    authConfig.requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll();
                    authConfig.requestMatchers(new AntPathRequestMatcher("/cliente/page/*")).permitAll();
                    //authConfig.requestMatchers(new AntPathRequestMatcher("/cliente/regiones")).permitAll();
                    authConfig.requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll();
                    authConfig.requestMatchers(new AntPathRequestMatcher("/cliente/**")).authenticated();
                    authConfig.anyRequest().authenticated();
                    //authConfig.anyRequest().permitAll(); // PERMITE TODAS LAS REQUEST
                    //authConfig.anyRequest().denyAll(); //Deniega cualquier otra configuracion que no este autorizada
                })
                .cors(cors -> cors.configurationSource(apiConfigurationSource()));
        return http.build();
    }

    @Bean
    CorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTION"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
/*
    @Bean
    FilterRegistrationBean<CorsFilter>corsFilter(){
        FilterRegistrationBean<CorsFilter> bean= new FilterRegistrationBean<CorsFilter>();
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

 */
}
