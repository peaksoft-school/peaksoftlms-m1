package kg.peaksoft.peaksoftlmsm1.db.repository.testRepository;

import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}