//package donTouch.energy_server;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig{
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/energy/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//        //  .oauth2Login(oauth2 -> oauth2.disable());
////                .oauth2Login(oauth2 -> oauth2
////                        .successHandler(new SimpleUrlAuthenticationSuccessHandler("/"))
////                        .failureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"))
////                );
//        return http.build();
//    }
//}
