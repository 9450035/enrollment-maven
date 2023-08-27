package ir.ac.fum.enrollment.profile.service;

import ir.ac.fum.enrollment.profile.dto.TermLessonDTO;
import ir.ac.fum.enrollment.profile.dto.TermLessonOut;
import ir.ac.fum.enrollment.profile.entities.TermLesson;
import ir.ac.fum.enrollment.profile.mapper.TermLessonMapper;
import ir.ac.fum.enrollment.profile.repositories.TermLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermLessonService {

    private final TermLessonRepository termLessonRepository;
    private final StudentService studentService;
    private final TermLessonMapper termLessonMapper;
    private final CourseService courseService;

//    public TermLessonService(TermLessonRepository termLessonRepository, StudentService studentService, TermLessonMapper termLessonMapper, CourseService courseService) {
//        this.termLessonRepository = termLessonRepository;
//        this.studentService = studentService;
//        this.termLessonMapper = termLessonMapper;
//        this.courseService = courseService;
//    }

    public void chooseTerm(TermLessonDTO termLessonDTO) {

        termLessonRepository.saveAll(termLessonDTO.courseId().stream()
                .map(aLong -> new TermLesson(null, aLong, null,
                        studentService.studentEntity().get().getId(), termLessonDTO.year(), termLessonDTO.term())).collect(Collectors.toList()));
    }

    public List<TermLessonOut> getStatastic(Integer year, Integer term) {
        return studentService.studentEntity().map(studentEntity ->
                        termLessonRepository.findByCourseYearAndTermAndStudentEntity(year, term, studentEntity.getId())
                                .stream().toList())
                .map(termLessons -> {
                    if (!termLessons.isEmpty())
                        return Arrays.stream(courseService.findCourse(termLessons.stream().map(TermLesson::getId).toList()))
                                .flatMap(courseOut -> termLessons.stream()
                                        .filter(termLesson -> termLesson.getId().equals(courseOut.id()))
                                        .map(termLesson -> termLessonMapper.toDto(termLesson, courseOut)));
                    return termLessons.stream().map(termLesson -> termLessonMapper.toDto(termLesson, null));
                }).get().toList();
    }


}
