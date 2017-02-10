package pl.pniedziela.admin.bans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.pniedziela.bans.Ban;
import pl.pniedziela.bans.BanService;

@RestController
@RequestMapping("/admin/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataBanController {
	@Autowired
	BanService banService;

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public DataTablesOutput<Ban> getBansData(@Valid DataTablesInput input) {
		DataTablesOutput<Ban> listBan;
		listBan = banService.findAll(input);
		return listBan;
	}

	@RequestMapping(value = "/ban/activate", method = RequestMethod.POST)
	public Map<String, String> activateBanById(@RequestParam Long banId, HttpServletRequest request) {

		String adminNickname = request.getRemoteUser();
		Map<String, String> ret = new HashMap<String, String>();
		boolean complete = banService.activateBanById(banId, adminNickname);
		ret.put("val", complete ? "true" : "false");
		return ret;
	}

	@RequestMapping(value = "/ban/deactivate", method = RequestMethod.POST)
	public Map<String, String> deactivateBanById(@RequestParam Long banId, HttpServletRequest request) {

		String adminNickname = request.getRemoteUser();
		Map<String, String> ret = new HashMap<String, String>();
		boolean complete = banService.deactivateBanById(banId, adminNickname);
		ret.put("val", complete ? "true" : "false");
		return ret;
	}
}
