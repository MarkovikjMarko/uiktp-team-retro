package uikt.uiktpteamretrobnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uikt.uiktpteamretrobnd.model.Team;
import uikt.uiktpteamretrobnd.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users ORDER BY RAND() LIMIT 1", nativeQuery = true)
    User randomUser();

    List<User> findByTeam(Team team);
}
