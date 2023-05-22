package uikt.uiktpteamretrobnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uikt.uiktpteamretrobnd.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}