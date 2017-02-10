package pl.pniedziela.admin.bans;

import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pniedziela.application.config.ApplicationConfigService;
import pl.pniedziela.application.config.BanConfigDto;
import pl.pniedziela.bans.BanDto;
import pl.pniedziela.bans.BanService;
import pl.pniedziela.user.User;
import pl.pniedziela.user.UserService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminBansController {

	@Autowired
	ApplicationConfigService appConfigService;
	@Autowired
	BanService banService;
	@Autowired
	UserService userService;

	@RequestMapping("/bans")
	public String getBansPage(Model model) {
		return "admin.bans";
	}

	@RequestMapping(value = "/banConfig", method = RequestMethod.GET)
	public String getBanConfigPage(Model model) {

		model.addAttribute("banConfigDto", appConfigService.getBanConfigDtoFromApplicationConfig());
		return "admin.bans.config";
	}

	@RequestMapping(value = "/banConfig", method = RequestMethod.POST)
	public String saveBanConfig(BanConfigDto banConfigDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "admin.bans.config";
		} else {
			appConfigService.changeAppConfigFromBanConfigDto(banConfigDto);
			return "redirect:/admin/banConfig/complete";
		}
	}

	@RequestMapping("/banConfig/complete")
	public String changedBanConfig(Model model) {

		model.addAttribute("successAlert", "admin.BanConfig.BanConfigChanged");
		return getBansPage(model);
	}

	@RequestMapping(value = "/addBan", method = RequestMethod.GET)
	public String addBan(Model model, @RequestParam(name = "userId_", required = false) Long userId) {

		BanDto banDto = new BanDto();
		model.addAttribute("banDto", banDto);
		model.addAttribute("usersList", userService.getMapIdUser());
		model.addAttribute("userId", userId);
		return "admin.bans.addBan";
	}

	@RequestMapping(value = "/addBan", method = RequestMethod.POST)
	public String saveBan(@Valid BanDto banDto, BindingResult result, Model model, Authentication auth) {

		User admin = (User) auth.getPrincipal();

		if (result.hasErrors()) {
			return "admin.bans.addBan";
		} else {
			banService.addBanFromBanDto(banDto, admin);
			return "redirect:/admin/addBan/complete";
		}
	}

	@RequestMapping("/addBan/complete")
	public String addedBan(Model model) {

		model.addAttribute("successAlert", "admin.Bans.AddedBan");
		return getBansPage(model);
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(true);
		webDataBinder.registerCustomEditor(DateTime.class, new CustomDateEditor(dateFormat, true));
	}
}
