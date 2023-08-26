package ir.ac.fum.enrollment.profile.configurationproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "urls")
public record Urls(String profile, String course) {
}
