package pl.pniedziela.application.config;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationConfigDao extends JpaRepository<ApplicationConfig, Long> {

	public List<ApplicationConfig> findAll();

	public <S extends ApplicationConfig> S save(S entity);
}
