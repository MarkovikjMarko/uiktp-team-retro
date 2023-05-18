package uikt.uiktpteamretrobnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uikt.uiktpteamretrobnd.model.Retrospective;

@Repository
public interface RetrospectiveRepository extends JpaRepository<Retrospective, Long> {
}