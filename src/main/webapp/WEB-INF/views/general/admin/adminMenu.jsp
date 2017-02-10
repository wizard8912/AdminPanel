<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:set var="memUsage">
	<fmt:formatNumber type="number" minFractionDigits="2"
		maxFractionDigits="2" value="${resourcesUsage.memUsage}" />
</c:set>
<c:set var="cpuUsage">
	<fmt:formatNumber type="number" minFractionDigits="2"
		maxFractionDigits="2" value="${resourcesUsage.cpuUsage}" />
</c:set>
<c:set var="fsUsage">
	<fmt:formatNumber type="number" minFractionDigits="2"
		maxFractionDigits="2" value="${fileSysUsage.fileSysUsage}" />
</c:set>
<fmt:parseNumber var="memUsageInt" integerOnly="true" type="number"
	value="${memUsage}" />
<fmt:parseNumber var="cpuUsageInt" integerOnly="true" type="number"
	value="${cpuUsage}" />
<fmt:parseNumber var="fsUsageInt" integerOnly="true" type="number"
	value="${fsUsage}" />
<div id="adminPanelRight">
	<p data-ulId="loggedUsers">
		<fmt:message key="admin.RightMenu.LoggedUsers" />
	</p>
	<div id="divLoggedUsers" data-ulId="loggedUsers">
		<div class="inputDiv">
			<span><input id="searchLoggedUsers" type="text"
				class="search rounded"
				placeholder='<fmt:message key="general.Search" />'></span>
		</div>
		<ul id="ulLoggedUsers" data-ulId="loggedUsers" class="dual-align-list">
			<c:forEach items="${listOfActiveUsers}" var="item">
				<li>${item[0]}
					<div class="pull-rightAdminMenu">${item[1]}</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<p data-ulId="pResources">
		<fmt:message key="admin.RightMenu.Resources" />
	</p>
	<div id="divResources" data-ulId="divResources">
		<ul id="divResources" data-ulId="divResources" class="dual-align-list">
			<li><a
				href="${ pageContext.request.contextPath }/admin/resources"><font
					color="${ memUsageInt > 90 ? 'red' : ( memUsageInt > 70 ? 'yellow' : 'green') }">M:
						${ memUsage }%</font> &nbsp;<font
					color="${ cpuUsageInt > 85 ? 'red' : ( cpuUsageInt > 40 ? 'yellow' : 'green') }">C:
						${ cpuUsage }%</font> <font
					color="${ fsUsageInt > 90 ? 'red' : ( fsUsageInt > 70 ? 'yellow' : 'green') }">F:
						${ fsUsage }%</font> </a></li>
		</ul>
	</div>
</div>