package io.openvidu.call.java.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import filters.ConditionFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigUpdate {

    @Value("${CALL_USER}")
	private String CALL_USER;

	@Value("${CALL_SECRET}")
	private String CALL_SECRET;


    public ConditionFilter conditionFilter() {
        return new ConditionFilter();
    }
    
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/call/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated())
            .addFilter(conditionFilter())
            .httpBasic(Customizer.withDefaults());

            return http.build();

    }
    
    @Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails userDetails = User.builder()
			.username(CALL_USER)
			.password(encoder.encode(CALL_SECRET))
			.roles("ADMIN")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

    @Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
