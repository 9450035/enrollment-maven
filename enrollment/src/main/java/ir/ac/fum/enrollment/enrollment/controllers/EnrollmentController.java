package ir.ac.fum.enrollment.enrollment.controllers;

import ir.ac.fum.enrollment.enrollment.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public Map enroll(@RequestBody List<Integer> courseIds) throws Exception {
        return enrollmentService.enroll(courseIds);
    }

}
