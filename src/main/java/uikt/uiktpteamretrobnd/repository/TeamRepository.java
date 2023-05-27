package uikt.uiktpteamretrobnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uikt.uiktpteamretrobnd.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "SELECT * FROM teams ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Team randomTeam();
}
