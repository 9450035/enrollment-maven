package ir.ac.fum.enrollment.profile.mapper;

import ir.ac.fum.enrollment.profile.dto.TermLessonOut;
import ir.ac.fum.enrollment.profile.entities.CourseOut;
import ir.ac.fum.enrollment.profile.entities.TermLesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

//@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TermLessonMapper {

    @Mapping(target = "id", expression = "java(termLesson.getId())")
    TermLessonOut toDto(TermLesson termLesson, CourseOut courseOut);

}
