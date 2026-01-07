package easycounter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.List;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CorsFilter corsFilter() {
        var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        var globalCorsConfiguration = new CorsConfiguration();
        globalCorsConfiguration.addAllowedOrigin("http://localhost:4300");
        globalCorsConfiguration.addAllowedHeader(HttpHeaders.AUTHORIZATION);
        globalCorsConfiguration.addAllowedHeader("*");
        globalCorsConfiguration.setAllowCredentials(true);
        globalCorsConfiguration.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name()
        ));
        globalCorsConfiguration.setExposedHeaders(List.of("X-OTHER-CUSTOM-HEADER"));
        globalCorsConfiguration.setMaxAge(Duration.ofSeconds(30));
        corsConfigurationSource.registerCorsConfiguration("/**", globalCorsConfiguration);
        return new CorsFilter(corsConfigurationSource);
    }
}
