package pl.pniedziela.home;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

	@Autowired
	SessionRegistry sessionRegistry;

	@RequestMapping("/")
	public String getHomePage() {
		
		return "home.mainPage";
	}
}
