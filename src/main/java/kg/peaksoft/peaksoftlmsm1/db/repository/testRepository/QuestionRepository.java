package kg.peaksoft.peaksoftlmsm1.db.repository.testRepository;

import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}