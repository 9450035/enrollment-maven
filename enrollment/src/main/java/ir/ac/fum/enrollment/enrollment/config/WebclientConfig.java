package ir.ac.fum.enrollment.enrollment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebclientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
