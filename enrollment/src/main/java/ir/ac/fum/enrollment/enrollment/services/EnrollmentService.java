package ir.ac.fum.enrollment.enrollment.services;

import ir.ac.fum.enrollment.enrollment.configurationproperties.Urls;
import ir.ac.fum.enrollment.enrollment.entities.CheckRequisiteBody;
import ir.ac.fum.enrollment.enrollment.entities.GroupEntity;
import ir.ac.fum.enrollment.enrollment.entities.Profile;
import ir.ac.fum.enrollment.enrollment.repositories.GroupRepository;
import ir.ac.fum.enrollment.enrollment.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final GroupRepository groupRepository;
    private final RestTemplate restTemplate;
    private final Urls urls;

    public Map enroll(List<Long> groupId, Integer year, Integer term) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + SecurityUtils.getCurrentUserJWT().get());
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        var profile = restTemplate.exchange(urls.profile() + "/student", HttpMethod.GET, requestEntity, Profile.class);
        if (groupId.size() > profile.getBody().permittedEnrollment()) {
            throw new Exception();
        }

        var groups = groupRepository.findAllById(groupId);
        var course = restTemplate.postForObject(urls.course() + "/course", new CheckRequisiteBody(profile.getBody().passedCoursesIds(),
                groups.stream().map(GroupEntity::getCourse).toList()), Long[].class);
        groups.forEach(groupEntity -> groupEntity.setRemainingCapacity(groupEntity.getRemainingCapacity() - 1));
        HttpEntity<TermLessonDTO> requestEntity1 = new HttpEntity<>(new TermLessonDTO(groupId, year, term),headers);
        restTemplate.exchange(urls.profile() + "/create-terms",HttpMethod.POST, requestEntity1, Void.class);
        groupRepository.saveAll(groups);

        return Map.of("success", course);


    }
}
