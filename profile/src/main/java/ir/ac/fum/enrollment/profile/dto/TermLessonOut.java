package ir.ac.fum.enrollment.profile.dto;

import ir.ac.fum.enrollment.profile.entities.CourseOut;

public record TermLessonOut(Long id, Long course, Float grade, Integer courseYear, Integer term, CourseOut courseOut) {
}
