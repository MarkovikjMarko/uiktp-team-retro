package uikt.uiktpteamretrobnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uikt.uiktpteamretrobnd.model.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long> {
}
