<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id"
	content="738020815413-m5kk5ls5pmuhm1ohajls7qmk71839ih5.apps.googleusercontent.com">

<c:set var="titleKey">
	<tiles:insertAttribute name="title" ignore="true" />
</c:set>
<title><fmt:message key="${titleKey}" /></title>

<!-- Bootstrap core CSS -->

<link
	href="${ pageContext.request.contextPath }/resources/views/general/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${ pageContext.request.contextPath }/resources/views/general/css/bootstrap-social.css"
	rel="stylesheet">

<link
	href="${ pageContext.request.contextPath }/resources/views/general/fonts/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${ pageContext.request.contextPath }/resources/views/general/css/animate.min.css"
	rel="stylesheet">

<!-- Custom styling plus plugins -->
<link
	href="${ pageContext.request.contextPath }/resources/views/general/css/custom.css"
	rel="stylesheet">
<link
	href="${ pageContext.request.contextPath }/resources/views/general/css/icheck/flat/green.css"
	rel="stylesheet" />
<link
	href="${ pageContext.request.contextPath }/resources/views/general/css/floatexamples.css"
	rel="stylesheet" type="text/css" />

<link
	href="${ pageContext.request.contextPath }/resources/views/general/js/datatables/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ pageContext.request.contextPath }/resources/views/general/js/datatables/buttons.bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ pageContext.request.contextPath }/resources/views/general/js/datatables/fixedHeader.bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ pageContext.request.contextPath }/resources/views/general/js/datatables/responsive.bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ pageContext.request.contextPath }/resources/views/general/js/datatables/scroller.bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/sweetalert-dev.js"></script>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/resources/views/general/css/sweetalert.css">

<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/jquery.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/nprogress.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/jquery.dataTables.min.js"></script>

<!-- echart -->
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/echart/echarts-all.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/echart/green.js"></script>
<!--[if lt IE 9]>
        <script src="${ pageContext.request.contextPath }/resources/views/general/assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>


<body class="nav-sm">

	<div class="container body">

		<div class="main_container">

			<div class="col-md-3 left_col">
				<div class="left_col">

					<div class="navbar nav_title" style="border: 0;">
						<a href="${ pageContext.request.contextPath }/" class="site_title"><i
							class="fa fa-futbol-o"></i> <span><fmt:message
									key="pageTitle" /></span></a>
					</div>
					<div class="clearfix"></div>

					<sec:authorize access="isAuthenticated()">
						<!-- menu prile quick info -->
						<div class="profile">
							<div class="profile_pic">
								<img
									src="${ pageContext.request.contextPath }/user/avatars/${ pageContext.request.remoteUser }"
									class="img-circle profile_img">
							</div>
							<div class="profile_info">
								<span><fmt:message key="general.Welcome" />,</span>
								<h2>${ pageContext.request.remoteUser }</h2>
							</div>
						</div>
						<!-- /menu prile quick info -->

						<br />
					</sec:authorize>

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">

						<div class="menu_section">
							<ul class="nav side-menu">
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<li><a><i class="fa fa-home"></i> <fmt:message
												key="home.Header.Admin" /> <span class="fa fa-chevron-down"></span></a>
										<ul class="nav child_menu" style="display: none">
											<li><a
												href="${ pageContext.request.contextPath }/admin/users"><fmt:message
														key="admin.Users" /></a></li>
											<li><a
												href="${ pageContext.request.contextPath }/admin/deletedUsers"><fmt:message
														key="admin.DeletedUsers" /></a></li>
											<li><a
												href="${ pageContext.request.contextPath }/admin/bans"><fmt:message
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
											<li><a
												href="${ pageContext.request.contextPath }/admin/logs"><fmt:message
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
											<li><a
												href="${ pageContext.request.contextPath }/admin/sessions"><fmt:message
														key="admin.Sessions" /></a></li>
											<li><a
												href="${ pageContext.request.contextPath }/admin/serverlogs"><fmt:message
														key="admin.ServerLogs" /></a></li>
										</ul></li>
								</sec:authorize>
								<sec:authorize access="isAuthenticated()">
									<li><a><i class="fa fa-user"></i> <fmt:message
												key="home.Header.Account" /> <span
											class="fa fa-chevron-down"></span></a>
										<ul class="nav child_menu" style="display: none">
											<li><a
												href="${ pageContext.request.contextPath }/user/account"><fmt:message
														key="user.Account" /></a></li>
										</ul></li>
								</sec:authorize>
								<li><a><i class="fa fa-edit"></i> <fmt:message
											key="home.Header.Languages" /> <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
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
									<li><a
										href="${ pageContext.request.contextPath }/register"><i
											class="fa fa-user fa-lg"></i> <fmt:message
												key="home.Header.User.SignUp" /></a></li>
									<li><a href="${ pageContext.request.contextPath }/login"><i
											class="fa fa-user fa-lg"></i> <fmt:message
												key="home.Header.User.LogIn" /></a></li>
								</sec:authorize>

								<sec:authorize access="isAuthenticated()">
									<li><a href="#" onclick="signOut(true);"> <i
											class="fa fa-sign-out fa-lg"></i> <fmt:message
												key="home.Header.User.LogOut" />
									</a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_SUPERADMIN')">
									<li><a><i class="fa fa-users"></i> <fmt:message
												key="home.Header.ActiveUsers" /> <font
											style="opacity: 0.5;">(${fn:length(listOfActiveUsers)})</font>
											<span class="fa fa-chevron-down"></span></a>
										<ul class="nav child_menu" style="display: none">
											<c:forEach items="${listOfActiveUsers}" var="activeUser">
												<li
													class="${ activeUser.inactiveTimeStr == 'min' ? 'greenActive' : activeUser.inactiveTimeStr == 'h' ? 'yellowActive' : 'redActive'}"><a
													href="${ pageContext.request.contextPath }/user/account/${activeUser.user.id}">
														${activeUser.user.nickname} <font style="opacity: 0.5;">
															(${ activeUser.inactiveTime } ${ activeUser.inactiveTimeStr })</font>
												</a></li>
											</c:forEach>
										</ul></li>
								</sec:authorize>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div style="display: none;" class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
							<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
							class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout">
							<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">

				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>

						<ul class="nav navbar-nav navbar-right">
							<sec:authorize access="isAuthenticated()">
								<li class=""><a href="javascript:;"
									class="user-profile dropdown-toggle" data-toggle="dropdown"
									aria-expanded="false"> <img
										src="${ pageContext.request.contextPath }/user/avatars/${ pageContext.request.remoteUser }"
										alt="">${ pageContext.request.remoteUser } <span
										class=" fa fa-angle-down"></span>
								</a>
									<ul
										class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
										<li><a href="javascript:;"> Profile</a></li>
										<li><a href="javascript:;"> <span
												class="badge bg-red pull-right">50%</span> <span>Settings</span>
										</a></li>
										<li><a href="javascript:;">Help</a></li>
										<li><a href="login.html"><i
												class="fa fa-sign-out pull-right"></i> Log Out</a></li>
									</ul></li>

								<li role="presentation" class="dropdown"><a
									href="javascript:;" class="dropdown-toggle info-number"
									data-toggle="dropdown" aria-expanded="false"> <i
										class="fa fa-envelope-o"></i> <span class="badge bg-green">6</span>
								</a>
									<ul id="menu1"
										class="dropdown-menu list-unstyled msg_list animated fadeInDown"
										role="menu">
										<li><a> <span class="image"> <img
													src="${ pageContext.request.contextPath }/user/avatars/${ pageContext.request.remoteUser }"
													alt="Profile Image" />
											</span> <span> <span>John Smith</span> <span class="time">3
														mins ago</span>
											</span> <span class="message"> Film festivals used to be
													do-or-die moments for movie makers. They were where... </span>
										</a></li>
										<li><a> <span class="image"> <img
													src="${ pageContext.request.contextPath }/user/avatars/${ pageContext.request.remoteUser }"
													alt="Profile Image" />
											</span> <span> <span>John Smith</span> <span class="time">3
														mins ago</span>
											</span> <span class="message"> Film festivals used to be
													do-or-die moments for movie makers. They were where... </span>
										</a></li>
										<li><a> <span class="image"> <img
													src="${ pageContext.request.contextPath }/user/avatars/${ pageContext.request.remoteUser }"
													alt="Profile Image" />
											</span> <span> <span>John Smith</span> <span class="time">3
														mins ago</span>
											</span> <span class="message"> Film festivals used to be
													do-or-die moments for movie makers. They were where... </span>
										</a></li>
										<li><a> <span class="image"> <img
													src="${ pageContext.request.contextPath }/user/avatars/${ pageContext.request.remoteUser }"
													alt="Profile Image" />
											</span> <span> <span>John Smith</span> <span class="time">3
														mins ago</span>
											</span> <span class="message"> Film festivals used to be
													do-or-die moments for movie makers. They were where... </span>
										</a></li>
										<li>
											<div class="text-center">
												<a href="inbox.html"> <strong>See All Alerts</strong> <i
													class="fa fa-angle-right"></i>
												</a>
											</div>
										</li>
									</ul></li>
							</sec:authorize>
						</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->


			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row tile_count">
					<tiles:insertAttribute name="alerts" />
					<tiles:insertAttribute name="content" />
				</div>

				<!-- footer content -->

				<footer>
					<div class="copyright-info">
						<p class="pull-right">
							Gentelella - Bootstrap Admin Template by <a
								href="https://colorlib.com">Colorlib</a>
						</p>
					</div>
					<div class="clearfix"></div>
				</footer>
				<!-- /footer content -->
			</div>
			<!-- /page content -->

		</div>

	</div>

	<div id="custom_notifications" class="custom-notifications dsp_none">
		<ul class="list-unstyled notifications clearfix"
			data-tabbed_notifications="notif-group">
		</ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
	</div>

	<form id="googleAuth" name="googleAuth"
		action="${ pageContext.request.contextPath }/auth/google"
		method="POST">
		<input type="hidden" name="googleAuthEmail"> <input
			type="hidden" name="googleAuthID"> <input type="hidden"
			name="googleAuthFirstName"> <input type="hidden"
			name="googleAuthLastName"> <input type="hidden"
			name="googleAuthImageUrl"> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

	<script
		src="${ pageContext.request.contextPath }/resources/views/general/js/bootstrap.min.js">
		
	</script>

	<!-- bootstrap progress js -->
	<script
		src="${ pageContext.request.contextPath }/resources/views/general/js/progressbar/bootstrap-progressbar.min.js"></script>
	<script
		src="${ pageContext.request.contextPath }/resources/views/general/js/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- input mask -->
	<script
		src="${ pageContext.request.contextPath }/resources/views/general/js/input_mask/jquery.inputmask.js"></script>
	<!-- icheck -->
	<script
		src="${ pageContext.request.contextPath }/resources/views/general/js/icheck/icheck.min.js"></script>
	<!-- daterangepicker -->
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/moment/moment.min.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/datepicker/daterangepicker.js"></script>
	<!-- chart js -->

	<script
		src="${ pageContext.request.contextPath }/resources/views/general/js/custom.js"></script>

	<!-- flot js -->
	<!--[if lte IE 8]><script type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/jquery.flot.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/jquery.flot.pie.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/jquery.flot.orderBars.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/jquery.flot.time.min.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/date.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/jquery.flot.spline.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/jquery.flot.stack.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/curvedLines.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/flot/jquery.flot.resize.js"></script>
	<tiles:insertAttribute name="bodyIncludes" />
	<script>
		$(document).ready(function() {
			// [17, 74, 6, 39, 20, 85, 7]
			//[82, 23, 66, 9, 99, 6, 2]
			var data1 = [[gd(2012, 1, 1), 17], [gd(2012, 1, 2), 74], [gd(2012, 1, 3), 6], [gd(2012, 1, 4), 39], [gd(2012, 1, 5), 20], [gd(2012, 1, 6), 85], [gd(2012, 1, 7), 7]];

			var data2 = [[gd(2012, 1, 1), 82], [gd(2012, 1, 2), 23], [gd(2012, 1, 3), 66], [gd(2012, 1, 4), 9], [gd(2012, 1, 5), 119], [gd(2012, 1, 6), 6], [gd(2012, 1, 7), 9]];
			$("#canvas_dahs").length && $.plot($("#canvas_dahs"), [data1, data2], {
				series : {
					lines : {
						show : false,
						fill : true
					},
					splines : {
						show : true,
						tension : 0.4,
						lineWidth : 1,
						fill : 0.4
					},
					points : {
						radius : 0,
						show : true
					},
					shadowSize : 2
				},
				grid : {
					verticalLines : true,
					hoverable : true,
					clickable : true,
					tickColor : "#d5d5d5",
					borderWidth : 1,
					color : '#fff'
				},
				colors : ["rgba(38, 185, 154, 0.38)", "rgba(3, 88, 106, 0.38)"],
				xaxis : {
					tickColor : "rgba(51, 51, 51, 0.06)",
					mode : "time",
					tickSize : [1, "day"],
					//tickLength: 10,
					axisLabel : "Date",
					axisLabelUseCanvas : true,
					axisLabelFontSizePixels : 12,
					axisLabelFontFamily : 'Verdana, Arial',
					axisLabelPadding : 10
				//mode: "time", timeformat: "%m/%d/%y", minTickSize: [1, "day"]
				},
				yaxis : {
					ticks : 8,
					tickColor : "rgba(51, 51, 51, 0.06)",
				},
				tooltip : false
			});

			function gd(year, month, day) {
				return new Date(year, month - 1, day).getTime();
			}
		});
	</script>

	<!-- worldmap -->
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/resources/views/general/js/maps/gdp-data.js"></script>
	<!-- pace -->
	<script
		src="${ pageContext.request.contextPath }/resources/views/general/js/pace/pace.min.js"></script>

	<!-- skycons -->
	<script
		src="${ pageContext.request.contextPath }/resources/views/general/js/skycons/skycons.min.js"></script>
	<script>
		var icons = new Skycons({
			"color" : "#73879C"
		}), list = ["clear-day", "clear-night", "partly-cloudy-day", "partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind", "fog"], i;

		for (i = list.length; i--;)
			icons.set(list[i], list[i]);

		icons.play();
	</script>


	<script>
		$(document).ready(function() {
			$(":input").inputmask();

		});
	</script>
	<!-- /input mask -->
	<script>
		NProgress.done();
	</script>
	<!-- /datepicker -->
	<!-- /footer content -->

	<script>
		function onSignIn(googleUser) {
			var profile = googleUser.getBasicProfile();

			var email = profile.getEmail();
			var id = profile.getId();
			var imageUrl = profile.getImageUrl();
			var name = profile.getName();

			$("input[name='googleAuthEmail']").val(profile.getEmail());
			$("input[name='googleAuthID']").val(profile.getId());
			$("input[name='googleAuthFirstName']").val(profile.getGivenName());
			$("input[name='googleAuthLastName']").val(profile.getFamilyName());
			$("input[name='googleAuthImageUrl']").val(profile.getImageUrl());

			$("#googleAuth").submit();
		}
	</script>

	<script>
		function signOut(redirectLogout) {
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function() {
				if (redirectLogout) {
					window.location.href = "${ pageContext.request.contextPath }/logout";
				}
			})
		}

		function onLoad() {
			gapi.load('auth2', function() {
				gapi.auth2.init().then(function() {

				});
			});
		}
	</script>
	<script src="https://apis.google.com/js/platform.js?onload=onLoad"
		async defer></script>
</body>
</html>
