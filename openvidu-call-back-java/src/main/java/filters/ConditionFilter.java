
package filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ConditionFilter extends OncePerRequestFilter {

    
	@Value("${CALL_PRIVATE_ACCESS}")
	private String CALL_PRIVATE_ACCESS;

    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
                HttpSecurity http = someMethod();
        // TO-DO Auto-generated method stub
        if (CALL_PRIVATE_ACCESS.equals("ENABLED")) {
            // Do something
            http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/recordings/**").authenticated()
                .requestMatchers("/sessions/**").authenticated());
        }
        else {
            http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/recordings/**").permitAll()
                .requestMatchers("/sessions/**").permitAll());
        };
    } catch (Exception exception) {}

        filterChain.doFilter(request, response);
    }

    private HttpSecurity someMethod() {
        return new HttpSecurity(null, null, null);
    }

}
