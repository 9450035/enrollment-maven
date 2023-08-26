package ir.ac.fum.enrollment.profile.dto;

import java.util.List;

public record TermLessonDTO(List<Long> courseId, Integer year, Integer term) {
}
