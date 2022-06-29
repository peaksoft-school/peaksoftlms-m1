package kg.peaksoft.peaksoftlmsm1.db.repository.testRepository;

import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response.ResultRatingResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Result;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByTest(Test test);
    List<Result> findAllBy(Pageable pageable);
}