package ir.ac.fum.enrollment.course.services;

import ir.ac.fum.enrollment.course.dtos.CheckRequisiteBody;
import ir.ac.fum.enrollment.course.dtos.CourseOut;
import ir.ac.fum.enrollment.course.entities.CourseEntity;
import ir.ac.fum.enrollment.course.mappers.CourseMapper;
import ir.ac.fum.enrollment.course.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.function.Predicate.not;

@Service
@Transactional
//@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository repository, CourseMapper courseMapper) {
        this.repository = repository;
        this.courseMapper = courseMapper;
    }

    public void addCourses() {
        var math = repository.save(new CourseEntity("Math"));
        var physics = repository.save(new CourseEntity("Physics"));
        var programming = repository.save(new CourseEntity("Programming"));
        var logicCircuit = repository.save(new CourseEntity("Logic Circuit"));
        var electricCircuit = repository.save(new CourseEntity("Electric Circuit"));
        physics.setPreRequisite(Set.of(math));
        programming.setCoRequisite(Set.of(math));
        logicCircuit.setPreRequisite(Set.of(programming));
        electricCircuit.setPreRequisite(Set.of(physics));
    }

    public List<Long> checkRequisite(CheckRequisiteBody body) {
        return body.selectedCourses().stream()
                .map(repository::findById)
                .filter(Optional::isPresent).map(Optional::get)
                .filter(courseEntity -> checkCourseRequisite(body, courseEntity))
                .map(CourseEntity::getId)
                .toList();
    }

    private static boolean checkCourseRequisite(CheckRequisiteBody body, CourseEntity courseEntity) {
//        var preRequisite = courseEntity.getPreRequisite().stream()
//                .map(CourseEntity::getId)
//                .allMatch(body.passedCourses()::contains);
//        var coRequisite = courseEntity.getCoRequisite().stream()
//                .map(CourseEntity::getId)
//                .filter(not(body.passedCourses()::contains))
//                .allMatch(body.selectedCourses()::contains);
        return true /*&& coRequisite*/;
    }

    public List<CourseOut> findCourse(List<Long> courseIds) {
        return repository.findAllById(courseIds).stream().map(courseMapper::toDTO).toList();
    }
}
