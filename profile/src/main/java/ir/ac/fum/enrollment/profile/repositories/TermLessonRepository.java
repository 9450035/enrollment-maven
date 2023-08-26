package ir.ac.fum.enrollment.profile.repositories;

import ir.ac.fum.enrollment.profile.entities.TermLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermLessonRepository extends JpaRepository<TermLesson, Long> {
    List<TermLesson> findByCourseYearAndTermAndStudentEntity(Integer year, Integer term, Long studentId);
}
