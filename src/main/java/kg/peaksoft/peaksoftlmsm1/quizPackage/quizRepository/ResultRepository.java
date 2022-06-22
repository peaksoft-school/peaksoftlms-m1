package kg.peaksoft.peaksoftlmsm1.quizPackage.quizRepository;

import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Result;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByTest(Test test);
}