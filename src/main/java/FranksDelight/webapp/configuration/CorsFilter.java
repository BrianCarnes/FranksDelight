package FranksDelight.webapp.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Solves the CORS issue and forces exposed headers for Content-Range
@Component
public class CorsFilter implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .exposedHeaders("Content-Range", "Access-Control-Allow-Origin")
                .allowCredentials(false)
                .maxAge(-1)
                .allowedOrigins("http://localhost:5173")
                .allowedOrigins("http://localhost:5174")
                .allowedOrigins("http://localhost:8080")
                .allowedOrigins("https://franks.fulgentcorp.com:5173")
                .allowedOrigins("https://franks.fulgentcorp.com:5174")
                .allowedOrigins("https://franks.fulgentcorp.com:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
    }
}
