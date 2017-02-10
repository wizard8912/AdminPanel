package pl.pniedziela.admin.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.pniedziela.logs.Log;
import pl.pniedziela.logs.LogService;
import pl.pniedziela.operations.OperationService;
import pl.pniedziela.scheduler.resources.FileSysUsage;
import pl.pniedziela.scheduler.resources.ResourcesUsage;
import pl.pniedziela.scheduler.resources.ResourcesUsageService;

@ControllerAdvice(basePackages = "pl.pniedziela")
public class GlobalAttributes {
	/*
	 * @Autowired
	 * 
	 * @Qualifier("sessionRegistry") private SessionRegistry sessionRegistry;
	 * 
	 * @Autowired LogService logService;
	 * 
	 * @Autowired ResourcesUsageService resourcesUsageService;
	 * 
	 * @ModelAttribute("myAttribute") public void globalAttribute(Model model) {
	 * List<SessionInformation> sessions = getActiveSessions();
	 * 
	 * String[][] usersList = new String[sessions.size()][2]; DateFormat date =
	 * new SimpleDateFormat("dd.MM"); DateFormat time = new
	 * SimpleDateFormat("HH:mm");
	 * 
	 * for (int i = 0; i < sessions.size(); i++) { usersList[i][0] =
	 * sessions.get(i).getPrincipal().toString(); try { Log log =
	 * logService.getLastSuccessLoginByUsername(sessions.get(i).getPrincipal().
	 * toString()); DateTime dt = new DateTime(log != null ? log.getDate() : new
	 * Date()); if ((dt.toLocalDate()).equals(new LocalDate())) {
	 * usersList[i][1] = time.format(dt.toDate()); } else { usersList[i][1] =
	 * date.format(dt.toDate()); } } catch (Exception e) { } }
	 * 
	 * model.addAttribute("listOfActiveUsers", usersList);
	 * 
	 * List<ResourcesUsage> resourcesUsage =
	 * resourcesUsageService.getLastResourceUsage(); if (resourcesUsage != null
	 * && resourcesUsage.size() > 0) { model.addAttribute("resourcesUsage",
	 * resourcesUsage.get(0)); }
	 * 
	 * List<ResourcesUsage> fileSysUsage =
	 * resourcesUsageService.getLastFileSysUsage(); if (fileSysUsage != null &&
	 * fileSysUsage.size() > 0) { model.addAttribute("fileSysUsage",
	 * fileSysUsage.get(0)); } }
	 * 
	 * public List<SessionInformation> getActiveSessions() {
	 * List<SessionInformation> activeSessions = new
	 * ArrayList<SessionInformation>(); for (Object principal :
	 * sessionRegistry.getAllPrincipals()) {
	 * activeSessions.addAll(sessionRegistry.getAllSessions(principal, false));
	 * } return activeSessions; }
	 */

	@Autowired
	OperationService operationService;

	@ModelAttribute("myAttribute")
	public void globalAttribute(Model model, HttpServletRequest request) {

		if (request.isUserInRole("ADMIN")) {
			model.addAttribute("listOfActiveUsers", operationService.getListOfActiveUsers());
		}
	}
}
