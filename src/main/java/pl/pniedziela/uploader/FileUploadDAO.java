package pl.pniedziela.uploader;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FileUploadDAO extends JpaRepository<UploadFile, Long> {
	public <S extends UploadFile> S save(S entity);

	public List<UploadFile> findByUsername(String username);

	@Modifying
	@Transactional
	@Query("delete from UploadFile u where u.username = ?1")
	void deleteByUsername(String username);
}