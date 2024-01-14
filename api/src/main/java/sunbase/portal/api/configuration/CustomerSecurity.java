package sunbase.portal.api.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sunbase.portal.api.service.UserDetailedService;

import java.util.Arrays;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomerSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailedService userDetailedService;
    @Autowired
    JwtFilterForReq jwtFilterForReq;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .authorizeRequests().antMatchers("/user/signup","/user/login").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtFilterForReq, UsernamePasswordAuthenticationFilter.class).csrf().disable();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
//bcrypt password encoder is used
        return  new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  auth.inMemoryAuthentication().withUser("moin").password(this.passwordEncoder().encode("momin")).roles("normal");
        auth.userDetailsService(userDetailedService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //for solve cors issue globally
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        log.info("inside cors");
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-Requested-With", "accept", "Origin", "Content-Length", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization", "x-tfa"));
        System.err.println("-inCors");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
