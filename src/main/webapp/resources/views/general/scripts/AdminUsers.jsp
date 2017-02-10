<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script>
	var messages = {
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
		deleteAccount : {
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
		}
	};

	$('#table').bootstrapTable({
		contextMenu : '#users-context-menu',
		contextMenuButton : '.example4-button',
		contextMenuAutoClickRow : true,
		onClickRow : function(row, $el) {
			$('#table').find('.success').removeClass('success');
			$el.addClass('success');
		},
		onContextMenuItem : function(row, $el) {

			if ($el.data("item") == "edit") {
				editUser(row);
			} else if ($el.data("item") == "delete") {
				swal(messages.deleteAccount, function() {
					deleteAccount(row.id);
				});
			} else if ($el.data("item") == "activate") {
				if (row.activateDate) {
					swal(messages.accountIsActive);
				} else {
					swal(messages.activateAccountQuestion, function() {
						activateUserAccount(row.id);
					});
				}
			} else if ($el.data("item") == "sendActivateLink") {

				if (row.activateDate) {
					swal(messages.accountIsActive);
				} else {

					swal(messages.sendActivateLinkQuestion, function() {
						sendActivationLink(row.id);
					});

				}
			} else if ($el.data("item") == "changePass") {
				changePassword(row);
			} else if ($el.data("item") == "sendForgotPassLink") {
				swal(messages.sendForgotPassLinkQuestion, function() {
					sendForgotPassLink(row.id);
				});
			} else if ($el.data("item") == "editRoles") {
				editRoles(row);
			}
		}
	});

	function activateUserAccount(userId) {

		var URL = "/admin/data/user/activate";
		var DATA = {
			userId : userId,
			"${_csrf.parameterName}" : "${_csrf.token}"
		};
		var SUCCESS_MSG = messages.accountActivated;
		var ERROR_MSG = messages.accountNotActivated;
		doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
	}

	function deleteAccount(userId) {

		var URL = "/admin/data/user/delete";
		var DATA = {
			userId : userId,
			"${_csrf.parameterName}" : "${_csrf.token}"
		};
		var SUCCESS_MSG = messages.accountDeleted;
		var ERROR_MSG = messages.accountNotDeleted;
		doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
	}

	function sendActivationLink(userId) {

		var URL = "/admin/data/user/activationlink";
		var DATA = {
			userId : userId,
			"${_csrf.parameterName}" : "${_csrf.token}"
		};
		var SUCCESS_MSG = messages.activationLinkWasSend;
		var ERROR_MSG = messages.activationLinkWasNotSend;
		doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
	}

	function sendForgotPassLink(userId) {

		var URL = "/admin/data/user/passlink";
		var DATA = {
			userId : userId,
			"${_csrf.parameterName}" : "${_csrf.token}"
		};
		var SUCCESS_MSG = messages.forgotPassLinkWasSend;
		var ERROR_MSG = messages.forgotPassLinkWasNotSend;
		doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
	}

	function doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG) {
		$.ajax({
			method : "POST",
			url : "${ pageContext.request.contextPath }" + URL,
			data : DATA
		}).done(
				function(ret) {

					var msg = messages[ret.msg]
							|| (ret.val == "true" ? SUCCESS_MSG : ERROR_MSG);
					swal(msg);

					$("#table").bootstrapTable('refresh');
				}).fail(function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.status == 403) {
				swal(messages.accessDisabled);
			} else {
				swal(messages.unexpectedError);
			}
		});
	}

	function editUser(user) {

		$(".form-group").removeClass("has-error");
		$(".help-block").remove();
		$(".btn").button('reset');

		$("#id").val(user.id);
		$("#nickname").val(user.nickname);
		$("#firstname").val(user.firstname);
		$("#lastname").val(user.lastname);
		$("#email").val(user.email);
		$("#country").val(user.country);
		$("#birthdate").val(user.birthdate);
		$("#phonenumber").val(user.phonenumber);

		$("#editUserModal").modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	function changePassword(user) {

		$(".form-group").removeClass("has-error");
		$(".help-block").remove();
		$(".btn").button('reset');

		$("#userFormPassword")[0][0].value = user.id;
		$("#password").val("");
		$("#passwordVerify").val("");

		$("#editPasswordModal").modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	function editRoles(user) {

		$("#userFormRoles")[0][0].value = user.id;
		console.log(user);
		$("#role").val(
				user["roles.2.id"] || user["roles.1.id"] || user["roles.0.id"]);
		$("#passwordVerify").val("");

		$("#editRolesModal").modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	function disableInputsAndButtons(obj) {

		$("#" + obj + " :button").attr("disabled", true);
		$("#" + obj + " :input").attr("readonly", true);
	}

	$(document).ready(function() {
		if ($("#nickname").val()) {
			$("#editUserModal").modal({
				backdrop : 'static',
				keyboard : false
			});
		} else if ($("#password").val() || $("#passwordVerify").val()) {
			
	$("#editPasswordModal").modal({
				backdrop : 'static',
				keyboard : false
			});
		} else {
			$("#id").val("");
			$("#nickname").val("");
			$("#firstname").val("");
			$("#lastname").val("");
			$("#email").val("");
			$("#country").val("");
			$("#birthdate").val("");
			$("#phonenumber").val("");
			$("#password").val("");
			$("#passwordVerify").val("");
		}

		$('#editUserModal .btn').on('click', function() {
			$(this).button('loading');
		});

		$('#editPasswordModal .btn').on('click', function() {
			$(this).button('loading');
		});

		$('#editRolesModal .btn').on('click', function() {
			$(this).button('loading');
		});

	});

	function rowStyle(value, row, index) {

		var roles = [];
		roles.push(value["roles.0.name"]);
		roles.push(value["roles.1.name"]);
		roles.push(value["roles.2.name"]);

		if (roles.indexOf("ROLE_SUPERADMIN") > -1) {
			return {
				classes : 'text-nowrap another-class',
				css : {
					"background" : "rgba(255,0,0,0.2)"
				}
			};
		} else if (roles.indexOf("ROLE_ADMIN") > -1) {
			return {
				classes : 'text-nowrap another-class',
				css : {
					"background" : "rgba(255,255,0,0.2)"
				}
			};
		} else {
			return {
				classes : 'text-nowrap another-class'
			}
		}
	}
</script>