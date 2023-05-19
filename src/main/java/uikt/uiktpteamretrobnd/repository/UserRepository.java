package uikt.uiktpteamretrobnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uikt.uiktpteamretrobnd.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
