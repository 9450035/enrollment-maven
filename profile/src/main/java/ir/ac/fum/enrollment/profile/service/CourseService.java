package ir.ac.fum.enrollment.profile.service;

import com.fasterxml.jackson.core.type.TypeReference;
import ir.ac.fum.enrollment.profile.configurationproperties.Urls;
import ir.ac.fum.enrollment.profile.configurations.WebclientConfig;
import ir.ac.fum.enrollment.profile.entities.CourseOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseService {

    private final WebclientConfig webclientConfig;
    private final Urls urls;

    public CourseOut[] findCourse(List<Long> courseId) {
        StringBuilder queryParam = new StringBuilder("?");
        for (Long id : courseId)
            queryParam.append("courseIds=").append(id).append("&");
        return webclientConfig.restTemplate().getForObject(urls.course() + "/course" + queryParam,CourseOut[].class);
    }
}
