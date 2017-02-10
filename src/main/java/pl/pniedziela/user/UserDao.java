package pl.pniedziela.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import pl.pniedziela.scheduler.resources.ResourcesUsage;

public interface UserDao extends DataTablesRepository<User, Long> {

	public User findByNicknameAndDeletedAccount(String nickname, boolean deletedAccount);

	public User findByEmailAndDeletedAccount(String email, boolean deletedAccount);

	public User findByActivateLinkAndDeletedAccount(String activateLink, boolean deletedAccount);

	public User findByForgetPassLinkAndDeletedAccount(String rpCode, boolean deletedAccount);

	public User findByIdAndDeletedAccount(Long id, boolean deletedAccount);

	public User findById(long id);

	public User findByEmail(String email);

	public User findByNickname(String nickname);

	public List<User> findByDeletedAccount(boolean deletedAccount);

	public <S extends User> S save(S entity);

	public User findByNicknameAndIdNot(String nickname, long id);

	public User findByEmailAndIdNot(String email, long id);

	public User findByGoogleID(String googleID);
}
