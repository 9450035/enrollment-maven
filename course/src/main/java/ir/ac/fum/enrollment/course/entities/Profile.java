package ir.ac.fum.enrollment.course.entities;

import java.util.List;

public record Profile(String name, String studentId, List<Long> passedCoursesIds, Float score,
                      Integer permittedEnrollment, List<Long> currentCourseIds) {
}
