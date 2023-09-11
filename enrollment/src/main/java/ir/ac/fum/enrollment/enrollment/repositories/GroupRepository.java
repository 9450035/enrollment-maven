package ir.ac.fum.enrollment.enrollment.repositories;

import ir.ac.fum.enrollment.enrollment.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    List<GroupEntity> findAllByCourseIn(List<Long> course);
}
