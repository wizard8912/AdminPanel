package pl.pniedziela.user.login;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pniedziela.logs.Log;
import pl.pniedziela.logs.LogService;
import pl.pniedziela.logs.LogTypes;

@Service
public class LoginService {

	@Autowired
	LogService logService;

	public void logLogout(String username, String ipaddress) {
		Log log = new Log();
		log.setUsername(username);
		log.setDate(new Date());
		log.setLogType(LogTypes.SUCCESS_LOGOUT);
		log.setIpAddress(ipaddress);
		logService.saveLog(log);
	}

	public void logBanned(String username, String ipaddress) {
		Log log = new Log();
		log.setUsername(username);
		log.setDate(new Date());
		log.setLogType(LogTypes.FAILTURE_BANNED_USER);
		log.setIpAddress(ipaddress);
		logService.saveLog(log);
	}

	public void logGoogleSuccessLogin(String username, String ipaddress) {
		Log log = new Log();
		log.setUsername(username);
		log.setDate(new Date());
		log.setLogType(LogTypes.GOOGLE_SUCCESS_LOGIN);
		log.setIpAddress(ipaddress);
		logService.saveLog(log);
	}

	public void logGoogleFailedLogin(String username, String ipaddress) {
		Log log = new Log();
		log.setUsername(username);
		log.setDate(new Date());
		log.setLogType(LogTypes.GOOGLE_FAILED_LOGIN);
		log.setIpAddress(ipaddress);
		logService.saveLog(log);
	}
}
