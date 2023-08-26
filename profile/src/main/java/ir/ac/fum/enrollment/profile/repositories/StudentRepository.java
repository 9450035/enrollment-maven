package ir.ac.fum.enrollment.profile.repositories;

import ir.ac.fum.enrollment.profile.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByStudentId(String studentId);
}
