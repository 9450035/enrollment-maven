package ir.ac.fum.enrollment.course.dtos;

import java.util.List;

public record CheckRequisiteBody(List<Long> passedCourses, List<Long> selectedCourses) {
}
