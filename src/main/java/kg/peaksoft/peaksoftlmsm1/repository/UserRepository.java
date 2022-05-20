package kg.peaksoft.peaksoftlmsm1.repository;

import kg.peaksoft.peaksoftlmsm1.entity.securityEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}