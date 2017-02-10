<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<style>
.btn-dark>li>a {
	cursor: pointer;
	color: white;
	line-height: 25px;
}
</style>
<div class="">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						<fmt:message key="admin.Users.Title" />
						<small><fmt:message key="admin.Users.Subtitle" /></small>
					</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a href="#"><i class="fa fa-chevron-up"></i></a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false"><i
								class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">Settings 1</a></li>
								<li><a href="#">Settings 2</a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-close"></i></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<table id="datatable"
						class="table table-striped responsive-utilities jambo_table bulk_action"
						width="100%">
						<thead>
							<tr class="headings">
								<th class="column-title"><fmt:message key="admin.Users.Id" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Nickname" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Firstname" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Lastname" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Email" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Country" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.City" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Birthdate" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Phonenumber" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Registerdate" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.Activatedate" /></th>
								<th class="column-title"><fmt:message
										key="admin.Users.ForgetPassDate" /></th>
							</tr>
						</thead>

						<tbody>

						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
</div>