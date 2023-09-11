package ir.ac.fum.enrollment.enrollment.controllers;

import ir.ac.fum.enrollment.enrollment.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public Map enroll(@RequestBody List<Long> courseIds, @RequestParam Integer year,@RequestParam Integer term) throws Exception {
        return enrollmentService.enroll(courseIds,year,term);
    }

}
