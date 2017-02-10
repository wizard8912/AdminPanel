<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.nav-side-menu {
	overflow: auto;
	font-family: verdana;
	font-size: 12px;
	font-weight: 200;
	background-color: #2e353d;
	height: 100%;
	color: #e1ffff;
	z-index: 1000;
	top: 0px;
	width: 100%;
}

.navMenuIcon {
	overflow: auto;
	position: fixed;
	top: 0px;
	z-index: 9999;
}

.nav-side-menu .brand {
	background-color: #23282e;
	line-height: 50px;
	display: block;
	text-align: center;
	font-size: 14px;
	min-height: 50px;
}

.nav-side-menu .toggle-btn {
	display: none;
}

.nav-side-menu ul, .nav-side-menu li {
	list-style: none;
	padding: 0px;
	margin: 0px;
	line-height: 35px;
	cursor: pointer;
	/*    
    .collapsed{
       .arrow:before{
                 font-family: FontAwesome;
                 content: "\f053";
                 display: inline-block;
                 padding-left:10px;
                 padding-right: 10px;
                 vertical-align: middle;
                 float:right;
            }
     }
*/
}

.nav-side-menu ul :not(collapsed) .arrow:before, .nav-side-menu li :not(collapsed) .arrow:before
	{
	font-family: FontAwesome;
	content: "\f078";
	display: inline-block;
	padding-left: 10px;
	padding-right: 10px;
	vertical-align: middle;
	float: right;
}

.nav-side-menu ul .active, .nav-side-menu li .active {
	border-left: 3px solid #d19b3d;
	background-color: #4f5b69;
}

.nav-side-menu ul .sub-menu li.active, .nav-side-menu li .sub-menu li.active
	{
	color: #d19b3d;
}

.nav-side-menu ul .sub-menu li.active a, .nav-side-menu li .sub-menu li.active a
	{
	color: #d19b3d;
}

.nav-side-menu ul .sub-menu li, .nav-side-menu li .sub-menu li {
	background-color: #181c20;
	border: none;
	line-height: 28px;
	border-bottom: 1px solid #23282e;
	margin-left: 0px;
}

.nav-side-menu ul .sub-menu li:hover, .nav-side-menu li .sub-menu li:hover
	{
	background-color: #020203;
}

.nav-side-menu ul .sub-menu a:before, .nav-side-menu li .sub-menu a:before
	{
	font-family: FontAwesome;
	content: "\f105";
	display: inline-block;
	padding-left: 10px;
	padding-right: 10px;
	vertical-align: middle;
}

.nav-side-menu li {
	padding-left: 0px;
	border-left: 3px solid #2e353d;
	border-bottom: 1px solid #23282e;
}

.nav-side-menu li a {
	width: 100%;
	display: inline-block;
	text-decoration: none;
	color: #e1ffff;
	text-decoration: none;
}

.nav-side-menu li a i {
	padding-left: 10px;
	width: 20px;
	padding-right: 20px;
}

.nav-side-menu li:hover {
	border-left: 3px solid #d19b3d;
	background-color: #4f5b69;
	-webkit-transition: all 1s ease;
	-moz-transition: all 1s ease;
	-o-transition: all 1s ease;
	-ms-transition: all 1s ease;
	transition: all 1s ease;
}

@media ( max-width : 767px) {
	.nav-side-menu {
		position: relative;
		width: 100%;
		margin-bottom: 10px;
	}
	.nav-side-menu .toggle-btn {
		display: block;
		cursor: pointer;
		position: absolute;
		right: 10px;
		top: 10px;
		z-index: 10 !important;
		padding: 3px;
		background-color: #ffffff;
		color: #000;
		width: 40px;
		text-align: center;
	}
	.brand {
		text-align: left !important;
		font-size: 22px;
		padding-left: 20px;
		line-height: 50px !important;
	}
}

@media ( min-width : 767px) {
	.nav-side-menu .menu-list .menu-content {
		display: block;
	}
}

body {
	margin: 0px;
	padding: 0px;
}
</style>

<div id="sidebarNav" class="nav-side-menu sidebar-wrapper">

	<div class="brand"></div>
	<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
		data-target="#menu-content"></i>

	<div class="menu-list">

		<ul id="menu-content" class="menu-content collapse out">
			<li id="headerMenuHome"><i class="fa fa-dashboard fa-lg"></i> <a
				href="${ pageContext.request.contextPath }/"><i
					class="fa fa-dashboard fa-lg"></i> <fmt:message
						key="home.Header.MainPage" /></a></li>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li data-toggle="collapse" data-target="#adminMenu"
					class="collapsed"><a href="#" data-toggle="collapse"
					data-target="#adminMenu" class="collapsed dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-gift fa-lg"></i> <fmt:message
							key="home.Header.Admin" /> <span class="arrow"></span></a>
					<ul class="sub-menu collapse" id="adminMenu">
						<li><a
							href="${ pageContext.request.contextPath }/admin/users"><fmt:message
									key="admin.Users" /></a></li>
						<sec:authorize access="hasRole('ROLE_SUPERADMIN')">
							<li><a
								href="${ pageContext.request.contextPath }/admin/deletedUsers"><fmt:message
										key="admin.DeletedUsers" /></a></li>
						</sec:authorize>
						<li><a href="${ pageContext.request.contextPath }/admin/bans"><fmt:message
									key="admin.Bans" /></a></li>
						<li><a
							href="${ pageContext.request.contextPath }/admin/operations"><fmt:message
									key="admin.Operations" /></a></li>
						<li><a
							href="${ pageContext.request.contextPath }/admin/emails"><fmt:message
									key="admin.Emails" /></a></li>
						<sec:authorize access="hasRole('ROLE_SUPERADMIN')">
							<li><a
								href="${ pageContext.request.contextPath }/admin/emailTasks"><fmt:message
										key="admin.EmailTasks" /></a></li>
						</sec:authorize>
						<li><a href="${ pageContext.request.contextPath }/admin/logs"><fmt:message
									key="admin.Logs" /></a></li>
						<li><a
							href="${ pageContext.request.contextPath }/admin/resources"><fmt:message
									key="admin.Resources" /></a></li>
						<li><a
							href="${ pageContext.request.contextPath }/admin/statistics"><fmt:message
									key="admin.Statistics" /></a></li>
						<li><a
							href="${ pageContext.request.contextPath }/admin/database"><fmt:message
									key="admin.Database" /></a></li>
					</ul></li>
			</sec:authorize>

			<li data-toggle="collapse" data-target="#languagesMenu"
				class="collapsed"><a href="#" data-toggle="collapse"
				data-target="#languagesMenu" class="collapsed dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-gift fa-lg"></i> <fmt:message
						key="home.Header.Languages" /> <span class="arrow"></span></a>

				<ul class="sub-menu collapse" id="languagesMenu">
					<li><a href="?language=en"><img
							src="${ pageContext.request.contextPath }/resources/icons/uk-icon.png"
							width="25px" height="25px" /> &nbsp; <fmt:message
								key="home.Header.Languages.English" /></a></li>
					<li><a href="?language=pl"><img
							src="${ pageContext.request.contextPath }/resources/icons/pl-icon.png"
							width="25px" height="25px" /> &nbsp; <fmt:message
								key="home.Header.Languages.Polish" /></a></li>
				</ul></li>

			<sec:authorize access="!isAuthenticated()">
				<li id="headerMenuRegister"><a
					href="${ pageContext.request.contextPath }/register"><i
						class="fa fa-user fa-lg"></i> <fmt:message
							key="home.Header.User.SignUp" /></a></li>
				<li id="headerMenuLogin"><a
					href="${ pageContext.request.contextPath }/login"><i
						class="fa fa-user fa-lg"></i> <fmt:message
							key="home.Header.User.LogIn" /></a></li>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<li id="headerMenuLogout"><a
					href="${ pageContext.request.contextPath }/logout"> <i
						class="fa fa-user fa-lg"></i> <fmt:message
							key="home.Header.User.LogOut" /></a></li>
			</sec:authorize>
		</ul>
	</div>
</div>