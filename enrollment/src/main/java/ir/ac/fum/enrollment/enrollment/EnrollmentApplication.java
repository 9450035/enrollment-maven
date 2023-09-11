package ir.ac.fum.enrollment.enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ir.ac.fum.enrollment.enrollment.configurationproperties")
public class EnrollmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnrollmentApplication.class, args);
    }

}
