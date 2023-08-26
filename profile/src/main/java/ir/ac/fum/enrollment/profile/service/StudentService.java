package ir.ac.fum.enrollment.profile.service;

import ir.ac.fum.enrollment.profile.entities.StudentEntity;
import ir.ac.fum.enrollment.profile.repositories.StudentRepository;
import ir.ac.fum.enrollment.profile.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Optional<StudentEntity> studentEntity() {
        return SecurityUtils.getCurrentUserLogin().flatMap(studentRepository::findByStudentId);
    }
}
