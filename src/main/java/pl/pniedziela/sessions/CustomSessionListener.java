package pl.pniedziela.sessions;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import eu.bitwalker.useragentutils.UserAgent;
import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;

@Component
public class CustomSessionListener implements ServletContextListener, HttpSessionListener {

	@Autowired
	SessionsDetailsService sessionDetailsService;
	@Autowired
	UserService userService;

	public void sessionCreated(HttpSessionEvent se) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		SessionDetails sd = new SessionDetails();
		sd.setCreatedDate(se.getSession().getCreationTime());
		sd.setIpAddress(request.getRemoteAddr());
		sd.setLocale(request.getLocale().getCountry());
		sd.setSessionID(se.getSession().getId());
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		sd.setUserBrowser(userAgent.getBrowser().getName());
		sd.setUserBrowserVersion(userAgent.getBrowserVersion().getVersion());
		sd.setUserOS(userAgent.getOperatingSystem().getName());
		sessionDetailsService.save(sd);
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		SessionDetails sd = sessionDetailsService.findById(se.getSession().getId());

		sd.setDestroyedDate(new Date());
		sessionDetailsService.save(sd);
	}

	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
