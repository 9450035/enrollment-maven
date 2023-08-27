package ir.ac.fum.enrollment.course.controllers;

import ir.ac.fum.enrollment.course.dtos.CheckRequisiteBody;
import ir.ac.fum.enrollment.course.dtos.CourseOut;
import ir.ac.fum.enrollment.course.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService service;

    @PostMapping
    public List<Long> getValidCourses(@RequestBody CheckRequisiteBody body) {
        return service.checkRequisite(body);
    }

    @GetMapping
    public ResponseEntity<List<CourseOut>> getCourses(@RequestParam List<Long> courseIds) {
        return ResponseEntity.ok(service.findCourse(courseIds));
    }

}
