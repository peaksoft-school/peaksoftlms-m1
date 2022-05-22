package kg.peaksoft.peaksoftlmsm1.db.repository;

import kg.peaksoft.peaksoftlmsm1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}