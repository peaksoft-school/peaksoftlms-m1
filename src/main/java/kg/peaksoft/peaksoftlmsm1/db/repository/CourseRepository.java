package kg.peaksoft.peaksoftlmsm1.db.repository;

import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}