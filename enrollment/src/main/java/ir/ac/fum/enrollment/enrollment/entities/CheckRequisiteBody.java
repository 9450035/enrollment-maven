package ir.ac.fum.enrollment.enrollment.entities;

import java.util.List;

public record CheckRequisiteBody(List<Long> passedCourses, List<Long> selectedCourses) {
}
