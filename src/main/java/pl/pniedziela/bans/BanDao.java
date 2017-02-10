package pl.pniedziela.bans;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.pniedziela.user.User;

public interface BanDao extends DataTablesRepository<Ban, Long> {

	public Ban findById(long id);

	public Page<Ban> findByIpAddressAndDateToGreaterThanAndActiveTrue(String ipAddress, Date dateTo, Pageable pageable);

	public Page<Ban> findByUserAndDateToAfterAndIpAddressIsNullAndActiveTrue(User user, Date dateTo, Pageable pageable);

	public Page<Ban> findByUserAndIpAddressAndDateToAfterAndActiveTrue(User user, String ipAddress, Date dateTo,
			Pageable pageable);

	public <S extends Ban> S save(S entity);

}
