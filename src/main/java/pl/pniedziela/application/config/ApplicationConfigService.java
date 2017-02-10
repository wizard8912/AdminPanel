package pl.pniedziela.application.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.bans.BanDto;

@Service
@Transactional
public class ApplicationConfigService {

	@Autowired
	ApplicationConfigDao applicationConfigDao;

	public ApplicationConfig getApplicationConfig() {

		List<ApplicationConfig> listAppConfig = applicationConfigDao.findAll();
		if (listAppConfig.size() != 0) {
			return listAppConfig.get(0);
		} else {
			ApplicationConfig appConfig = new ApplicationConfig();
			appConfig.setBanCheckSeconds(60 * 60);
			appConfig.setBanForSeconds(60 * 60);
			appConfig.setBanLoginAttempsIp(5);
			appConfig.setBanLoginAttempsUsr(5);

			return saveApplicationConfig(appConfig);
		}
	}

	private ApplicationConfig saveApplicationConfig(ApplicationConfig appConfig) {

		List<ApplicationConfig> listAppConfig = applicationConfigDao.findAll();
		if (listAppConfig.size() != 0) {
			ApplicationConfig ac = listAppConfig.get(0);
			ac.setBanCheckSeconds(appConfig.getBanCheckSeconds());
			ac.setBanForSeconds(appConfig.getBanForSeconds());
			ac.setBanLoginAttempsIp(appConfig.getBanLoginAttempsIp());
			ac.setBanLoginAttempsUsr(appConfig.getBanLoginAttempsUsr());
			ac.setBanForIp(appConfig.isBanForIp());
			ac.setBanForUsr(appConfig.isBanForUsr());
			return applicationConfigDao.save(ac);
		} else {
			return applicationConfigDao.save(appConfig);
		}
	}

	public BanConfigDto getBanConfigDtoFromApplicationConfig() {

		BanConfigDto banConfigDto = new BanConfigDto();
		ApplicationConfig applicationConfig = getApplicationConfig();
		long banCheckSeconds = applicationConfig.getBanCheckSeconds();
		long banForSeconds = applicationConfig.getBanForSeconds();

		banConfigDto.setBanForIp(applicationConfig.isBanForIp());
		banConfigDto.setBanForUsr(applicationConfig.isBanForUsr());
		banConfigDto.setBanLoginAttempsIp(applicationConfig.getBanLoginAttempsIp());
		banConfigDto.setBanLoginAttempsUsr(applicationConfig.getBanLoginAttempsUsr());
		banConfigDto.setBanCheckDays(getDaysFromSeconds(banCheckSeconds));
		banConfigDto.setBanCheckHours(getHoursFromSeconds(banCheckSeconds));
		banConfigDto.setBanCheckMinutes(getMinutesFromSeconds(banCheckSeconds));
		banConfigDto.setBanForDays(getDaysFromSeconds(banForSeconds));
		banConfigDto.setBanForHours(getHoursFromSeconds(banForSeconds));
		banConfigDto.setBanForMinutes(getMinutesFromSeconds(banForSeconds));

		return banConfigDto;
	}

	public void changeAppConfigFromBanConfigDto(BanConfigDto banConfigDto) {

		ApplicationConfig applicationConfig = getApplicationConfig();
		applicationConfig.setBanForUsr(banConfigDto.isBanForUsr());
		applicationConfig.setBanForIp(banConfigDto.isBanForIp());
		applicationConfig.setBanLoginAttempsIp(banConfigDto.getBanLoginAttempsIp());
		applicationConfig.setBanLoginAttempsUsr(banConfigDto.getBanLoginAttempsUsr());
		applicationConfig.setBanCheckSeconds(getSeconds(banConfigDto.getBanCheckDays(), banConfigDto.getBanCheckHours(),
				banConfigDto.getBanCheckMinutes()));
		applicationConfig.setBanForSeconds(getSeconds(banConfigDto.getBanForDays(), banConfigDto.getBanForHours(),
				banConfigDto.getBanForMinutes()));

		saveApplicationConfig(applicationConfig);
	}

	private int getSeconds(int days, int hours, int minutes) {

		return minutes * 60 + hours * 60 * 60 + days * 24 * 60 * 60;
	}

	private int getDaysFromSeconds(long seconds) {

		return (int) (seconds / 60 / 60 / 24);
	}

	private int getHoursFromSeconds(long seconds) {

		return (int) ((seconds % (60 * 60 * 24)) / 60 / 60);
	}

	private int getMinutesFromSeconds(long seconds) {

		return (int) ((seconds % (60 * 60)) / 60);
	}
}
