package uikt.uiktpteamretrobnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uikt.uiktpteamretrobnd.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
