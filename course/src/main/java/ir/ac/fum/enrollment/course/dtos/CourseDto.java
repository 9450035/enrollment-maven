package ir.ac.fum.enrollment.course.dtos;

import java.util.Set;

public record CourseDto(int id, String name, Set<CourseDto> preRequisite, Set<CourseDto> coRequisite) {
}