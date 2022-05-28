package kg.peaksoft.peaksoftlmsm1.db.repository;

import kg.peaksoft.peaksoftlmsm1.db.entity.models.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
}