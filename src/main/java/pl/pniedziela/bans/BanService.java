package pl.pniedziela.bans;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.logs.LogTypes;
import pl.pniedziela.sessions.SessionsDetailsService;
import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;

@Service
@Transactional
public class BanService {

	@Autowired
	BanDao banDao;
	@Autowired
	UserService userService;
	@Autowired
	SessionsDetailsService sessionsService;

	public Ban addBanForUser(String username, Date from, Date to, String reason) {

		User user = userService.findByNickname(username);

		Ban ban = new Ban();
		ban.setUser(user);
		ban.setDateFrom(from);
		ban.setDateTo(to);
		ban.setReason(reason);

		return saveBan(ban);
	}

	public Ban addBanForIpAddress(String ipAddress, Date from, Date to, String reason) {

		Ban ban = new Ban();
		ban.setIpAddress(ipAddress);
		ban.setDateFrom(from);
		ban.setDateTo(to);
		ban.setReason(reason);

		return saveBan(ban);
	}

	public DataTablesOutput<Ban> findAll(DataTablesInput input) {
		return banDao.findAll(input);
	}

	public boolean activateBanById(Long banId, String adminNickname) {
		User user = userService.findByNickname(adminNickname);
		Ban ban = findById(banId);
		if (user == null || ban == null) {
			return false;
		}
		ban.setActive(true);
		ban.setLastModifiedDate(new Date());
		ban.setLastModifiedAdmin(user);
		saveBan(ban);

		destroyUserSessions(user);
		return true;
	}

	public boolean deactivateBanById(Long banId, String adminNickname) {
		User user = userService.findByNickname(adminNickname);
		Ban ban = findById(banId);
		if (user == null || ban == null) {
			return false;
		}
		ban.setActive(false);
		ban.setLastModifiedDate(new Date());
		ban.setLastModifiedAdmin(user);
		saveBan(ban);

		return true;
	}

	private Ban findById(Long banId) {
		return banDao.findById(banId);
	}

	public Ban saveBan(Ban ban) {
		return banDao.save(ban);
	}

	public Ban getActiveBanForUsernameOrIpAddress(String username, String ipAddress) {

		Ban banIpAddress;
		Ban banUser;

		if ((banIpAddress = getActiveBanForIp(ipAddress)) != null) {
			return banIpAddress;
		} else if ((banUser = getActiveBanForUsername(username)) != null) {
			return banUser;
		} else {
			return getActiveBanForUsernameAndIpAddress(username, ipAddress);
		}

	}

	private Ban getActiveBanForUsernameAndIpAddress(String username, String ipAddress) {
		User user = userService.findByNickname(username);
		final PageRequest page1 = new PageRequest(0, 20, Direction.DESC, "dateTo");
		List<Ban> content = banDao.findByUserAndIpAddressAndDateToAfterAndActiveTrue(user, ipAddress, new Date(), page1)
				.getContent();
		if (content.size() > 0) {
			return content.get(0);
		} else {
			return null;
		}
	}

	private Ban getActiveBanForUsername(String username) {
		User user = userService.findByNickname(username);
		final PageRequest page1 = new PageRequest(0, 20, Direction.DESC, "dateTo");
		List<Ban> content = banDao.findByUserAndDateToAfterAndIpAddressIsNullAndActiveTrue(user, new Date(), page1)
				.getContent();
		if (content.size() > 0) {
			return content.get(0);
		} else {
			return null;
		}
	}

	private Ban getActiveBanForIp(String remoteAddr) {
		final PageRequest page1 = new PageRequest(0, 20, Direction.DESC, "dateTo");
		List<Ban> content = banDao.findByIpAddressAndDateToGreaterThanAndActiveTrue(remoteAddr, new Date(), page1)
				.getContent();
		if (content.size() > 0) {
			return content.get(0);
		} else {
			return null;
		}
	}

	public Ban addBanFromBanDto(BanDto banDto, User admin) {

		Ban ban = new Ban();

		User user = userService.findById(banDto.getUserId());
		if (banDto.getBanType() == 1) {
			ban.setIpAddress(banDto.getIpAddress());
		} else if (banDto.getBanType() == 2) {
			ban.setUser(user);
		} else {
			return null;
		}

		ban.setActive(true);
		ban.setAdmin(admin);
		ban.setDateFrom(new Date());
		ban.setDateTo(banDto.getDateTo());
		ban.setReason(banDto.getReason());

		ban = saveBan(ban);
		destroyUserSessions(user);
		return ban;
	}

	private void destroyUserSessions(User user) {
		sessionsService.destroyUserSessions(user);
	}

}
