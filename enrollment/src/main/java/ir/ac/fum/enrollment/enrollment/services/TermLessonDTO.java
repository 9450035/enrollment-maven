package ir.ac.fum.enrollment.enrollment.services;

import java.util.List;

public record TermLessonDTO(List<Long> courseId, Integer year, Integer term) {
}
