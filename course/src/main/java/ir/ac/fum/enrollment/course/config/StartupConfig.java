package ir.ac.fum.enrollment.course.config;

import ir.ac.fum.enrollment.course.services.CourseService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig {
    @Bean
    ApplicationRunner runner(CourseService service) {
        return args -> service.addCourses();
    }
}
