package pl.pniedziela.operations;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@EnableAsync
public class LogsAspect {

	@Autowired
	OperationService operationService;

	private static LogsAspect logsAspect;

	public static LogsAspect aspectOf() {
		if (logsAspect == null) {
			logsAspect = new LogsAspect();
		}
		return logsAspect;
	}

	private LogsAspect() {

	}

	@Pointcut("execution(* pl.pniedziela..*.*(..)) && !execution(* pl.pniedziela.operations.*.*(..))")

	public void allMethodsPointcut() {

	}

	@After("allMethodsPointcut()")
	public void logOperation(JoinPoint jp) {

		Operation operation = new Operation();

		operation.setMethodSignature(jp.getSignature().toString());
		operation.setOperationDate(new Date());

		try {

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();

			String origialUri = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);

			operation.setAuthType(request.getAuthType());
			operation.setCharacterEncoding(request.getCharacterEncoding());
			operation.setContentLength(request.getContentLength());
			operation.setContentType(request.getContentType());
			operation.setLocalName(request.getLocalName());
			operation.setLocalPort(request.getLocalPort());
			operation.setMethod(request.getMethod());

			operation.setPathInfo(request.getPathInfo());
			operation.setProtocol(request.getProtocol());
			operation.setQueryString(request.getQueryString());
			operation.setRemoteAddr(request.getRemoteAddr());
			operation.setRemoteHost(request.getRemoteHost());
			operation.setRemotePort(request.getRemotePort());
			operation.setRemoteUser(request.getRemoteUser());
			operation.setAccept(request.getHeader("Accept"));
			operation.setAcceptLanguage(request.getHeader("Accept-Language"));
			operation.setUserAgent(request.getHeader("User-Agent"));
			operation.setReferer(request.getHeader("Referer"));
			operation.setRequestURL(request.getRequestURL().toString());
			operation.setOriginalUri(origialUri);

		} catch (Exception e) {
		}

		operationService.saveOperation(operation);
	}
}
