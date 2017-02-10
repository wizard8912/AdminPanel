<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="x_content bs-example-popovers">
	<c:if test="${successAlert != null}">
		<div class="alert alert-success alert-dismissible fade in"
			role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<fmt:message key="${successAlert}" />
			<c:if test="${successMessageAttribute != null}">
				${successMessageAttribute}
			</c:if>
		</div>
	</c:if>
	<c:if test="${errorAlert != null}">
		<div class="alert alert-danger alert-dismissible fade in" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<fmt:message key="${errorAlert}" />
			<c:if test="${errorMessageAttribute != null}">
				${errorMessageAttribute}
			</c:if>
		</div>
	</c:if>
	<c:if test="${infoAlert != null}">
		<div class="alert alert-info alert-dismissible fade in" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<fmt:message key="${infoAlert}" />
			<c:if test="${infoMessageAttribute != null}">
				${infoMessageAttribute}
			</c:if>
		</div>
	</c:if>
</div>
