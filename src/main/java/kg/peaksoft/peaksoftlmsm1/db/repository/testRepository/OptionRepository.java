package kg.peaksoft.peaksoftlmsm1.db.repository.testRepository;

import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}