package pl.pniedziela.operations;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@NamedQuery(name = "findAllOperations", query = "SELECT o FROM Operation o order by o.id desc")
public class Operation {

	@Id
	@SequenceGenerator(name = "operations_id_seq", sequenceName = "operations_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "operations_id_seq")
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "CET", pattern = "dd-MM-yyyy HH:mm:ss")
	private Date operationDate;
	private String authType;
	private String characterEncoding;
	private int contentLength;
	private String contentType;
	private String localName;
	private int localPort;
	private String method;
	private String pathInfo;
	private String protocol;
	private String queryString;
	private String remoteAddr;
	private String remoteHost;
	private int remotePort;
	private String remoteUser;
	private String requestURL;
	private String methodSignature;
	private String acceptLanguage;
	private String userAgent;
	private String accept;
	private String referer;
	private String originalUri;

	public Operation() {
		this.operationDate = new Date();
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPathInfo() {
		return pathInfo;
	}

	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public String getMethodSignature() {
		return methodSignature;
	}

	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}

	public String getAcceptLanguage() {
		return acceptLanguage;
	}

	public void setAcceptLanguage(String acceptLanguage) {
		this.acceptLanguage = acceptLanguage;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public long getId() {
		return id;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getOriginalUri() {
		return originalUri;
	}

	public void setOriginalUri(String originalUri) {
		this.originalUri = originalUri;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", operationDate=" + operationDate + ", authType=" + authType
				+ ", characterEncoding=" + characterEncoding + ", contentLength=" + contentLength + ", contentType="
				+ contentType + ", localName=" + localName + ", localPort=" + localPort + ", method=" + method
				+ ", pathInfo=" + pathInfo + ", protocol=" + protocol + ", queryString=" + queryString + ", remoteAddr="
				+ remoteAddr + ", remoteHost=" + remoteHost + ", remotePort=" + remotePort + ", remoteUser="
				+ remoteUser + ", requestURL=" + requestURL + ", methodSignature=" + methodSignature
				+ ", acceptLanguage=" + acceptLanguage + ", userAgent=" + userAgent + ", accept=" + accept
				+ ", referer=" + referer + ", originalUri=" + originalUri + "]";
	}
}
