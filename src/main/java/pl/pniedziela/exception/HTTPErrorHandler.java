package pl.pniedziela.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPErrorHandler {

	String path = "error";

	@RequestMapping(value = "/400")
	public String error400() {
		return path + ".400";
	}

	@RequestMapping(value = "/403")
	public String error403() {
		return path + ".403";
	}

	@RequestMapping(value = "/404")
	public String error404() {
		return path + ".404";
	}

	@RequestMapping(value = "/500")
	public String error500() {
		return path + ".500";
	}
}