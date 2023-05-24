package uikt.uiktpteamretrobnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uikt.uiktpteamretrobnd.model.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

}
