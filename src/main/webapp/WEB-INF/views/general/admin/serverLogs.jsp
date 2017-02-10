<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<style>
.fileChecker {
	cursor: pointer;
}
</style>
</head>

<div class="">
	<div class="row">
		<c:forEach items="${filesDetails}" var="file">
			<div data-filename="${ file.name }"
				class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12 fileChecker">
				<div class="tile-stats">
					<div class="icon">
						<i class="fa fa-file"></i>
					</div>
					<div class="count" style="font-size: 14px;">${ file.name }</div>

					<h3 style="font-size: 14px;">${ file.dateString }</h3>
					<p>${ file.size }</p>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<script>
	$(".fileChecker").click(function(e) {
		window.location.href = "${ pageContext.request.contextPath }/admin/serverlogs/" + $(this).attr("data-filename") + "/";
	})
</script>