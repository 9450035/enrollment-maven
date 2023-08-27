package ir.ac.fum.enrollment.course.mappers;

import ir.ac.fum.enrollment.course.dtos.CourseOut;
import ir.ac.fum.enrollment.course.entities.CourseEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CourseMapper {

    CourseOut toDTO(CourseEntity course);
}
