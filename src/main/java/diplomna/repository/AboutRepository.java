package diplomna.repository;

import diplomna.model.entity.About;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository extends JpaRepository<About, String> {
}
