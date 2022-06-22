package kg.peaksoft.peaksoftlmsm1.quizPackage.quizRepository;

import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}