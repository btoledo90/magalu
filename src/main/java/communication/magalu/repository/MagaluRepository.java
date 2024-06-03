package communication.magalu.repository;

import communication.magalu.entity.Magalu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagaluRepository extends JpaRepository<Magalu, Long> {
}