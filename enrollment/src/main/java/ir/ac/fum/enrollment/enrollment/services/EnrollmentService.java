package ir.ac.fum.enrollment.enrollment.services;

import ir.ac.fum.enrollment.enrollment.configurationproperties.Urls;
import ir.ac.fum.enrollment.enrollment.entities.CheckRequisiteBody;
import ir.ac.fum.enrollment.enrollment.entities.Profile;
import ir.ac.fum.enrollment.enrollment.repositories.GroupRepository;
import ir.ac.fum.enrollment.enrollment.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final GroupRepository groupRepository;
    private final RestTemplate restTemplate;
    private final Urls urls;

    public Map enroll(List<Integer> groupId) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + SecurityUtils.getCurrentUserJWT().get());
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        var profile = restTemplate.exchange(urls.profile() + "/student", HttpMethod.GET, requestEntity, Profile.class);
        if (groupId.size() > profile.getBody().permittedEnrollment()) {
            throw new Exception();
        }

        var course = restTemplate.postForObject(urls.course() + "/course", new CheckRequisiteBody(profile.getBody().passedCoursesIds(), profile.getBody().passedCoursesIds()), Long[].class);

        var groups = groupRepository.findAllByCourseIn(List.of(course));
        groups.forEach(groupEntity -> groupEntity.setRemainingCapacity(groupEntity.getRemainingCapacity() - 1));

        var updateProfile = new Profile(profile.getBody().name(), profile.getBody().studentId(),
                profile.getBody().passedCoursesIds(), profile.getBody().score(),
                profile.getBody().permittedEnrollment() - course.length, List.of(course));
        groupRepository.saveAll(groups);
        return Map.of("success",course);


    }
}
