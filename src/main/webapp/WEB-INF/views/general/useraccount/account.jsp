<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						<fmt:message key="account.Title" />
						<small><fmt:message key="account.Subtitle" /></small>
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

					<div class="col-md-3 col-sm-3 col-xs-12 profile_left">

						<div class="profile_img">

							<!-- end of image cropping -->
							<div id="crop-avatar">
								<!-- Current avatar -->
								<div class="avatar-view"
									title='<fmt:message key="account.ChangeAvatar" />'
									onclick="$('#fileAvatarUpload').click();">
									<img
										src="${ pageContext.request.contextPath }/user/avatars/${ userDetails.nickname }"
										alt="Avatar">
								</div>
								<form method="post" id="fileAvatarForm"
									action="${ pageContext.request.contextPath }/user/avatars/${ userDetails.id }/doUpload"
									enctype="multipart/form-data">

									<input class="hidden" type="file" id="fileAvatarUpload"
										name="fileUpload" size="50" accept="image/*" /> <input
										class="hidden" type="submit" value="Upload"
										id="fileAvatarUploadSubmit" /> <input type="hidden"
										name="${_csrf.parameterName}" value="${_csrf.token}" />
								</form>

								<!-- Cropping modal -->
								<div class="modal fade" id="avatar-modal" aria-hidden="true"
									aria-labelledby="avatar-modal-label" role="dialog"
									tabindex="-1">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<form class="avatar-form" action="crop.php"
												enctype="multipart/form-data" method="post">
												<div class="modal-header">
													<button class="close" data-dismiss="modal" type="button">&times;</button>
													<h4 class="modal-title" id="avatar-modal-label">Change
														Avatar</h4>
												</div>
												<div class="modal-body">
													<div class="avatar-body">

														<!-- Upload image and data -->
														<div class="avatar-upload">
															<input class="avatar-src" name="avatar_src" type="hidden">
															<input class="avatar-data" name="avatar_data"
																type="hidden"> <label for="avatarInput">Local
																upload</label> <input class="avatar-input" id="avatarInput"
																name="avatar_file" type="file">
														</div>

														<!-- Crop and preview -->
														<div class="row">
															<div class="col-md-9">
																<div class="avatar-wrapper"></div>
															</div>
															<div class="col-md-3">
																<div class="avatar-preview preview-lg"></div>
																<div class="avatar-preview preview-md"></div>
																<div class="avatar-preview preview-sm"></div>
															</div>
														</div>

														<div class="row avatar-btns">
															<div class="col-md-9">
																<div class="btn-group">
																	<button class="btn btn-primary" data-method="rotate"
																		data-option="-90" type="button"
																		title="Rotate -90 degrees">Rotate Left</button>
																	<button class="btn btn-primary" data-method="rotate"
																		data-option="-15" type="button">-15deg</button>
																	<button class="btn btn-primary" data-method="rotate"
																		data-option="-30" type="button">-30deg</button>
																	<button class="btn btn-primary" data-method="rotate"
																		data-option="-45" type="button">-45deg</button>
																</div>
																<div class="btn-group">
																	<button class="btn btn-primary" data-method="rotate"
																		data-option="90" type="button"
																		title="Rotate 90 degrees">Rotate Right</button>
																	<button class="btn btn-primary" data-method="rotate"
																		data-option="15" type="button">15deg</button>
																	<button class="btn btn-primary" data-method="rotate"
																		data-option="30" type="button">30deg</button>
																	<button class="btn btn-primary" data-method="rotate"
																		data-option="45" type="button">45deg</button>
																</div>
															</div>
															<div class="col-md-3">
																<button class="btn btn-primary btn-block avatar-save"
																	type="submit">Done</button>
															</div>
														</div>
													</div>
												</div>
												<!-- <div class="modal-footer">
                                                  <button class="btn btn-default" data-dismiss="modal" type="button">Close</button>
                                                </div> -->
											</form>
										</div>
									</div>
								</div>
								<!-- /.modal -->

								<!-- Loading state -->
								<div class="loading" aria-label="Loading" role="img"
									tabindex="-1"></div>
							</div>
							<!-- end of image cropping -->

						</div>

						<h3>${ userDetails.nickname }</h3>

						<ul class="list-unstyled user_data">
							<li><i class="fa fa-user user-profile-icon"></i>&nbsp; ${ userDetails.firstname }
								${ userDetails.lastname }</li>

							<li><i class="fa fa-map user-profile-icon"></i>&nbsp; ${ userDetails.city },
								${ userDetails.country }</li>
							<fmt:formatDate value="${userDetails.registerdate}"
								var="registerDateString" pattern="dd-MM-yyyy" />
							<li><i class="fa fa-calendar user-profile-icon"></i>&nbsp;
								${ registerDateString }</li>

						</ul>
						<div class="x_panel">
							<div class="x_content">



								<button data-toggle="dropdown"
									class="btn btn-success dropdown-toggle" type="button"
									aria-expanded="true" style="width: 100%;">
									<i class="fa fa-edit m-right-xs"></i>
									<fmt:message key="general.Action" />
									<span class="caret"></span>
								</button>

								<ul role="menu" class="dropdown-menu text-primary"
									style="width: 100%;">
									<li><a
										href="${ pageContext.request.contextPath }/user/${ userDetails.id }/changeAccount"><fmt:message
												key="admin.Users.Edit" /></a></li>
									<li><a
										href="${ pageContext.request.contextPath }/user/${ userDetails.id }/changePassword"><fmt:message
												key="admin.Users.ChangePassword" /></a></li>
									<li><a onclick="deleteAccount(${ userDetails.id });"><fmt:message
												key="admin.Users.Delete" /></a></li>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li><a
											href="${ pageContext.request.contextPath }/admin/addBan?userId_=${ userDetails.id }"><fmt:message
													key="admin.Users.AddBan" /></a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_SUPERADMIN')">
										<li><a
											onclick="activateUserAccount(${ userDetails.id });"><fmt:message
													key="admin.Users.Activate" /></a></li>
										<li><a onclick="sendActivationLink(${ userDetails.id });"><fmt:message
													key="admin.Users.SendActivateLink" /></a></li>
										<li><a onclick="sendForgotPassLink(${ userDetails.id });"><fmt:message
													key="admin.Users.SendForgotPassLink" /></a></li>
										<li><a
											href="${ pageContext.request.contextPath }/user/${ userDetails.id }/changeRole"><fmt:message
													key="admin.Users.EditRoles" /></a></li>
									</sec:authorize>
								</ul>
							</div>
						</div>
						<br />

						<!-- start skills -->
						<h4>
							<fmt:message key="account.Details" />
						</h4>
						<ul class="list-unstyled user_data">
							<li>
								<div class="media-body">
									<div class="title">
										<fmt:message key="account.Details.SuccessLoginCount" />
									</div>
									<p>${ accountDetails.loginCount }</p>
								</div>
							</li>
							<li>
								<div class="media-body">
									<div class="title">
										<fmt:message key="account.Details.FailedLoginCount" />
									</div>
									<p>${ accountDetails.failedLoginCount }</p>
								</div>
							</li>
							<li>
								<div class="media-body">
									<div class="title">
										<fmt:message key="account.Details.LastSuccessLogin" />
									</div>
									<fmt:formatDate value="${ accountDetails.lastLogin }"
										var="lastLoginDateString" pattern="dd-MM-yyyy HH:mm" />
									<p>${ lastLoginDateString }</p>
								</div>
							</li>
							<li>
								<div class="media-body">
									<div class="title">
										<fmt:message key="account.Details.LastFailedLogin" />
									</div>
									<fmt:formatDate value="${ accountDetails.lastFailedLogin }"
										var="lastFailedLoginDateString" pattern="dd-MM-yyyy HH:mm" />
									<p>${ lastFailedLoginDateString }</p>
								</div>
							</li>
						</ul>
						<!-- end of skills -->

					</div>
					<div class="col-md-9 col-sm-9 col-xs-12">

						<div class="profile_title">
							<div class="col-md-6">
								<h2>User Activity Report</h2>
							</div>
							<div class="col-md-6">
								<div id="reportrange" class="pull-right"
									style="margin-top: 5px; background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #E6E9ED">
									<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> <span>December
										30, 2014 - January 28, 2015</span> <b class="caret"></b>
								</div>
							</div>
						</div>
						<!-- start of user-activity-graph -->
						<div id="graph_bar" style="width: 100%; height: 280px;"></div>
						<!-- end of user-activity-graph -->

						<div class="" role="tabpanel" data-example-id="togglable-tabs">
							<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
								<li role="presentation" class="active"><a
									href="#tab_content1" id="home-tab" role="tab" data-toggle="tab"
									aria-expanded="true">Recent Activity</a></li>
								<li role="presentation" class=""><a href="#tab_content2"
									role="tab" id="profile-tab" data-toggle="tab"
									aria-expanded="false">Projects Worked on</a></li>
								<li role="presentation" class=""><a href="#tab_content3"
									role="tab" id="profile-tab2" data-toggle="tab"
									aria-expanded="false">Profile</a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<div role="tabpanel" class="tab-pane fade active in"
									id="tab_content1" aria-labelledby="home-tab">

									<!-- start recent activity -->
									<ul class="messages">
										<li>
											<div class="message_date">
												<h3 class="date text-info">24</h3>
												<p class="month">May</p>
											</div>
											<div class="message_wrapper">
												<h4 class="heading">Desmond Davison</h4>
												<blockquote class="message">Raw denim you
													probably haven't heard of them jean shorts Austin. Nesciunt
													tofu stumptown aliqua butcher retro keffiyeh dreamcatcher
													synth.</blockquote>
												<br />
												<p class="url">
													<span class="fs1 text-info" aria-hidden="true"
														data-icon=""></span> <a href="#"><i
														class="fa fa-paperclip"></i> User Acceptance Test.doc </a>
												</p>
											</div>
										</li>
										<li>
											<div class="message_date">
												<h3 class="date text-error">21</h3>
												<p class="month">May</p>
											</div>
											<div class="message_wrapper">
												<h4 class="heading">Brian Michaels</h4>
												<blockquote class="message">Raw denim you
													probably haven't heard of them jean shorts Austin. Nesciunt
													tofu stumptown aliqua butcher retro keffiyeh dreamcatcher
													synth.</blockquote>
												<br />
												<p class="url">
													<span class="fs1" aria-hidden="true" data-icon=""></span>
													<a href="#" data-original-title="">Download</a>
												</p>
											</div>
										</li>
										<li>
											<div class="message_date">
												<h3 class="date text-info">24</h3>
												<p class="month">May</p>
											</div>
											<div class="message_wrapper">
												<h4 class="heading">Desmond Davison</h4>
												<blockquote class="message">Raw denim you
													probably haven't heard of them jean shorts Austin. Nesciunt
													tofu stumptown aliqua butcher retro keffiyeh dreamcatcher
													synth.</blockquote>
												<br />
												<p class="url">
													<span class="fs1 text-info" aria-hidden="true"
														data-icon=""></span> <a href="#"><i
														class="fa fa-paperclip"></i> User Acceptance Test.doc </a>
												</p>
											</div>
										</li>
										<li>
											<div class="message_date">
												<h3 class="date text-error">21</h3>
												<p class="month">May</p>
											</div>
											<div class="message_wrapper">
												<h4 class="heading">Brian Michaels</h4>
												<blockquote class="message">Raw denim you
													probably haven't heard of them jean shorts Austin. Nesciunt
													tofu stumptown aliqua butcher retro keffiyeh dreamcatcher
													synth.</blockquote>
												<br />
												<p class="url">
													<span class="fs1" aria-hidden="true" data-icon=""></span>
													<a href="#" data-original-title="">Download</a>
												</p>
											</div>
										</li>

									</ul>
									<!-- end recent activity -->

								</div>
								<div role="tabpanel" class="tab-pane fade" id="tab_content2"
									aria-labelledby="profile-tab">

									<!-- start user projects -->
									<table class="data table table-striped no-margin">
										<thead>
											<tr>
												<th>#</th>
												<th>Project Name</th>
												<th>Client Company</th>
												<th class="hidden-phone">Hours Spent</th>
												<th>Contribution</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>New Company Takeover Review</td>
												<td>Deveint Inc</td>
												<td class="hidden-phone">18</td>
												<td class="vertical-align-mid">
													<div class="progress">
														<div class="progress-bar progress-bar-success"
															data-transitiongoal="35"></div>
													</div>
												</td>
											</tr>
											<tr>
												<td>2</td>
												<td>New Partner Contracts Consultanci</td>
												<td>Deveint Inc</td>
												<td class="hidden-phone">13</td>
												<td class="vertical-align-mid">
													<div class="progress">
														<div class="progress-bar progress-bar-danger"
															data-transitiongoal="15"></div>
													</div>
												</td>
											</tr>
											<tr>
												<td>3</td>
												<td>Partners and Inverstors report</td>
												<td>Deveint Inc</td>
												<td class="hidden-phone">30</td>
												<td class="vertical-align-mid">
													<div class="progress">
														<div class="progress-bar progress-bar-success"
															data-transitiongoal="45"></div>
													</div>
												</td>
											</tr>
											<tr>
												<td>4</td>
												<td>New Company Takeover Review</td>
												<td>Deveint Inc</td>
												<td class="hidden-phone">28</td>
												<td class="vertical-align-mid">
													<div class="progress">
														<div class="progress-bar progress-bar-success"
															data-transitiongoal="75"></div>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
									<!-- end user projects -->

								</div>
								<div role="tabpanel" class="tab-pane fade" id="tab_content3"
									aria-labelledby="profile-tab">
									<p>xxFood truck fixie locavore, accusamus mcsweeney's marfa
										nulla single-origin coffee squid. Exercitation +1 labore
										velit, blog sartorial PBR leggings next level wes anderson
										artisan four loko farm-to-table craft beer twee. Qui photo
										booth letterpress, commodo enim craft beer mlkshk</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	var messages = {
		fileIsTooLarge : {
			title : '<fmt:message key="account.Avatar.TooLargeFileTitle"/>',
			text : '<fmt:message key="account.Avatar.TooLargeFileText"/>',
			type : 'error',
			confirmButtonText : '<fmt:message key="general.Close"/>'
		},
		chooseAnotherFile : {
			title : '<fmt:message key="account.Avatar.ChooseAnotherFileTitle"/>',
			text : '<fmt:message key="account.Avatar.ChooseAnotherFileText"/>',
			type : 'info',
			confirmButtonText : '<fmt:message key="general.Close"/>'
		},
		fileIsNotAnImage : {
			title : '<fmt:message key="account.Avatar.FileIsNotAnImageTitle"/>',
			text : '<fmt:message key="account.Avatar.FileIsNotAnImageText"/>',
			type : 'error',
			confirmButtonText : '<fmt:message key="general.Close"/>'
		},
		accountIsActive : {
			title : '<fmt:message key="admin.Users.AccountIsActiveAlreadyTitle"/>',
			text : '<fmt:message key="admin.Users.AccountIsActiveAlreadyText"/>',
			type : 'info',
			confirmButtonText : '<fmt:message key="general.Close"/>'
		},
		accountActivated : {
			title : '<fmt:message key="general.Done"/>',
			text : '<fmt:message key="admin.Users.AccountActivated"/>',
			type : 'success'
		},
		accountNotActivated : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Users.AccountNotActivated"/>',
			type : 'error',
			confirmButtonText : '<fmt:message key="general.OK"/>'
		},
		activateAccountQuestion : {
			title : '<fmt:message key="admin.Users.ActivateAccountQuestionTitle"/>',
			text : '<fmt:message key="admin.Users.ActivateAccountQuestionText"/>',
			type : 'warning',
			showCancelButton : true,
			cancelButtonText : '<fmt:message key="general.Cancel"/>',
			confirmButtonColor : '#DD6B55',
			confirmButtonText : '<fmt:message key="admin.Users.ConfirmActivateAccount"/>',
			closeOnConfirm : false,
			showLoaderOnConfirm : true
		},
		activationLinkWasSend : {
			title : '<fmt:message key="general.Done"/>',
			text : '<fmt:message key="admin.Users.ActivationLinkWasSend"/>',
			type : 'success'
		},
		activationLinkWasNotSend : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Users.ActivationLinkNotSend"/>',
			type : 'error',
			confirmButtonText : '<fmt:message key="general.OK"/>'
		},
		sendActivateLinkQuestion : {
			title : '<fmt:message key="admin.Users.SendActivationLinkTitle"/>',
			text : '<fmt:message key="admin.Users.SendActivationLinkText"/>',
			type : 'warning',
			showCancelButton : true,
			cancelButtonText : '<fmt:message key="general.Cancel"/>',
			confirmButtonColor : '#DD6B55',
			confirmButtonText : '<fmt:message key="admin.Users.ConfirmSendActivationLink"/>',
			closeOnConfirm : false,
			showLoaderOnConfirm : true
		},
		forgotPassLinkWasSend : {
			title : '<fmt:message key="general.Done"/>',
			text : '<fmt:message key="admin.Users.PasswordLinkWasSend"/>',
			type : 'success'
		},
		forgotPassLinkWasNotSend : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Users.PasswordLinkNotSend"/>',
			type : "error",
			confirmButtonText : '<fmt:message key="general.OK"/>'
		},
		sendForgotPassLinkQuestion : {
			title : '<fmt:message key="admin.Users.Confirm" />',
			text : '<fmt:message key="admin.Users.SendForgotPassInfo" />',
			type : 'info',
			showCancelButton : true,
			cancelButtonText : '<fmt:message key="general.Cancel"/>',
			confirmButtonColor : '#5ADD55',
			confirmButtonText : '<fmt:message key="admin.Users.ConfirmSendIt" />',
			closeOnConfirm : false,
			showLoaderOnConfirm : true
		},
		deleteAccountQuestion : {
			title : '<fmt:message key="admin.Users.DeleteAccountQuestionTitle"/>',
			text : '<fmt:message key="admin.Users.DeleteAccountQuestionText"/>',
			type : "warning",
			showCancelButton : true,
			cancelButtonText : '<fmt:message key="general.Cancel"/>',
			confirmButtonColor : "#DD6B55",
			confirmButtonText : '<fmt:message key="admin.Users.ConfirmDeleteAccount"/>',
			closeOnConfirm : false
		},
		accountDeleted : {
			title : '<fmt:message key="general.Done"/>',
			text : '<fmt:message key="admin.Users.AccountDeleted"/>',
			type : 'success'
		},
		accountNotDeleted : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Users.AccountNotDeleted"/>',
			type : 'error'
		},
		accessDisabled : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="general.AccessDisabled"/>',
			type : 'error'
		},
		unexpectedError : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="general.UnexpectedError"/>',
			type : 'error'
		},
		notSelectedRow : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="general.RowIsNotSelected"/>',
			type : 'error'
		}
	};

	function deleteAccount(userId) {
		
		swal(messages.deleteAccountQuestion, function() {
			var URL = "/admin/data/user/delete";
			var DATA = {
				userId : userId,
				"${_csrf.parameterName}" : "${_csrf.token}"
			};
			var SUCCESS_MSG = messages.accountDeleted;
			var ERROR_MSG = messages.accountNotDeleted;
			doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
		});		
	}

	function activateUserAccount(userId) {

		swal(messages.sendForgotPassLinkQuestion, function() {
			var URL = "/admin/data/user/activate";
			var DATA = {
				userId : userId,
				"${_csrf.parameterName}" : "${_csrf.token}"
			};
			var SUCCESS_MSG = messages.accountActivated;
			var ERROR_MSG = messages.accountNotActivated;
			doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
		});	
		
	}

	function sendActivationLink(userId) {

		swal(messages.sendForgotPassLinkQuestion, function() {
			var URL = "/admin/data/user/activationlink";
			var DATA = {
				userId : userId,
				"${_csrf.parameterName}" : "${_csrf.token}"
			};
			var SUCCESS_MSG = messages.activationLinkWasSend;
			var ERROR_MSG = messages.activationLinkWasNotSend;
			doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
		});
	}

	function sendForgotPassLink(userId) {

		swal(messages.sendForgotPassLinkQuestion, function() {
			var URL = "/admin/data/user/passlink";
			var DATA = {
				userId : userId,
				"${_csrf.parameterName}" : "${_csrf.token}"
			};
			var SUCCESS_MSG = messages.forgotPassLinkWasSend;
			var ERROR_MSG = messages.forgotPassLinkWasNotSend;
			doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
		});
	}

	function editRoles(userId) {
		window.location.href = "${ pageContext.request.contextPath }/user/" + userId + "/changeRole";
	}

	function doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG) {
		$.ajax({
			method : "POST",
			url : "${ pageContext.request.contextPath }" + URL,
			data : DATA
		}).done(function(ret) {

			var msg = messages[ret.msg] || (ret.val == "true" ? SUCCESS_MSG : ERROR_MSG);
			swal(msg);

			tableUsers.ajax.reload();
		}).fail(function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.status == 403) {
				swal(messages.accessDisabled);
			} else {
				swal(messages.unexpectedError);
			}
		});
	}
	
	$("#fileAvatarUpload").change(function (){
		
		var file = $("#fileAvatarUpload");
		
			if(file.get(0).files.length){
			 var fileSize = file.get(0).files[0].size; // in bytes
			 
	          if(fileSize>150000){
	          	swal(messages.fileIsTooLarge);
	              return false;
	          }else if (!file.get(0).files[0].name.match(/\.(jpg|jpeg|png|gif)$/)){
	        	  swal(messages.fileIsNotAnImage);
	        	  return false;
			}else{
	          	 $("#fileAvatarUploadSubmit").click();
	          }
	      }else{
	      	swal(messages.chooseAnotherFile);
	          return false;
	      }     
    });
</script>