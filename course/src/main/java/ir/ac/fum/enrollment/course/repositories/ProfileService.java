package ir.ac.fum.enrollment.course.repositories;

import ir.ac.fum.enrollment.course.configurationproperties.Urls;
import ir.ac.fum.enrollment.course.entities.Profile;
import ir.ac.fum.enrollment.course.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProfileService {


    private final RestTemplate restTemplate;
    private final Urls urls;

    public Profile getProfile() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + SecurityUtils.getCurrentUserJWT().get());
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        var profile = restTemplate.exchange(urls.profile() + "/student", HttpMethod.GET, requestEntity, Profile.class);
        return profile.getBody();
    }
}
