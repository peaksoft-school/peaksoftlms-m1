package kg.peaksoft.peaksoftlmsm1.db.repository;

import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllBy(Pageable pageable);
}