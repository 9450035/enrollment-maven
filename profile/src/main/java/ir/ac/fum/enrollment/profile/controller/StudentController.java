package ir.ac.fum.enrollment.profile.controller;

import ir.ac.fum.enrollment.profile.entities.StudentEntity;
import ir.ac.fum.enrollment.profile.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<StudentEntity> findStudent() {
        return ResponseEntity.of(studentService.studentEntity());
    }
}
