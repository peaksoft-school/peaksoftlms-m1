package kg.peaksoft.peaksoftlmsm1.quizPackage.quizRepository;

import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}