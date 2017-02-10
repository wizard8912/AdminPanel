<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script>
	var messages = {
		banIsActive : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Bans.BanIsActiveAlready"/>',
			type : 'error',
			confirmButtonText : '<fmt:message key="general.OK"/>'
		},
		activateBanQuestion : {
			title : '<fmt:message key="admin.Bans.ActivateBanQuestionTitle"/>',
			text : '<fmt:message key="admin.Bans.ActivateBanQuestionText"/>',
			type : 'warning',
			showCancelButton : true,
			cancelButtonText : '<fmt:message key="general.Cancel"/>',
			confirmButtonColor : '#DD6B55',
			confirmButtonText : '<fmt:message key="admin.Bans.ConfirmActivateBan"/>',
			closeOnConfirm : false,
			showLoaderOnConfirm : true
		},
		banActivated : {
			title : '<fmt:message key="general.Done"/>',
			text : '<fmt:message key="admin.Bans.BanActivated"/>',
			type : 'success'
		},
		banNotActivated : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Bans.BanNotActivated"/>',
			type : 'error',
			confirmButtonText : '<fmt:message key="general.OK"/>'
		},
		banIsDeactive : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Bans.BanIsDeactiveAlready"/>',
			type : 'error',
			confirmButtonText : '<fmt:message key="general.OK"/>'
		},
		deactivateBanQuestion : {
			title : '<fmt:message key="admin.Bans.DeactivateBanQuestionTitle"/>',
			text : '<fmt:message key="admin.Bans.DeactivateBanQuestionText"/>',
			type : 'warning',
			showCancelButton : true,
			cancelButtonText : '<fmt:message key="general.Cancel"/>',
			confirmButtonColor : '#DD6B55',
			confirmButtonText : '<fmt:message key="admin.Bans.ConfirmDeactivateBan"/>',
			closeOnConfirm : false,
			showLoaderOnConfirm : true
		},
		banDeactivated : {
			title : '<fmt:message key="general.Done"/>',
			text : '<fmt:message key="admin.Bans.BanDeactivated"/>',
			type : 'success'
		},
		banNotDeactivated : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Bans.BanNotDeactivated"/>',
			type : 'error'
		}
	}

	$('#table').bootstrapTable({
		contextMenu : '#bans-context-menu',
		contextMenuButton : '.example4-button',
		contextMenuAutoClickRow : true,
		onClickRow : function(row, $el) {
			$('#table').find('.success').removeClass('success');
			$el.addClass('success');
		},
		onContextMenuItem : function(row, $el) {

			if ($el.data("item") == "activate") {
				if (row.active) {
					swal(messages.banIsActive);
				} else {
					swal(messages.activateBanQuestion, function() {
						activateBan(row.id);
					});

				}
			} else if ($el.data("item") == "deactivate") {
				if (!row.active) {
					swal(messages.banIsDeactive);
				} else {
					swal(messages.deactivateBanQuestion, function() {
						deactivateBan(row.id);
					});
				}
			}
		}
	});

	function activateBan(banId) {

		var URL = "/admin/data/ban/activate";
		var DATA = {
			banId : banId,
			"${_csrf.parameterName}" : "${_csrf.token}"
		};
		var SUCCESS_MSG = messages.banActivated;
		var ERROR_MSG = messages.banNotActivated;
		doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
	}

	function deactivateBan(banId) {

		var URL = "/admin/data/ban/deactivate";
		var DATA = {
			banId : banId,
			"${_csrf.parameterName}" : "${_csrf.token}"
		};
		var SUCCESS_MSG = messages.banDeactivated;
		var ERROR_MSG = messages.banNotDeactivated;
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
				}).fail(function(msg) {
			swal(ERROR_MSG);
		});
	}

	function rowStyle(value, row, index) {

		var bannedToString = "";
		bannedToString += value.dateTo.substring(6, 10) + "-";
		bannedToString += value.dateTo.substring(3, 5) + "-";
		bannedToString += value.dateTo.substring(0, 2) + "T";
		bannedToString += value.dateTo.substring(11);

		var now = new Date();
		var bannedTo = new Date(bannedToString);

		if (now < bannedTo && value.active) {
			return {
				classes : 'text-nowrap another-class',
				css : {
					"background" : "rgba(255,0,0,0.2)"
				}
			};
		} else if (now < bannedTo) {
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

	$(document)
			.ready(
					function() {
						var btnAddBan = '<button onclick="addBan()" class="btn btn-default" type="button" name="addBan" title="Add ban"><i class="glyphicon glyphicon-eye-open icon-eye-open"></i>&nbsp;&nbsp;<fmt:message key="admin.Bans.AddBan" /></button>';
						$(".fixed-table-toolbar .pull-left").append(btnAddBan);
						var btnBanConfig = '<button onclick="banConfig();" class="btn btn-default" type="button" name="banConfig" title="Congig bans"><i class="glyphicon glyphicon-cog icon-cog"></i>&nbsp;&nbsp;<fmt:message key="admin.Bans.BanConfig" /></button>';
						$(".fixed-table-toolbar .pull-left").append(
								btnBanConfig);

						if ("${showBanConfigForm}" == "true") {
							banConfig();
						}
						if ("${showAddBanForm}" == "true") {
							addBan();
						}
					});

	function banConfig() {
		$("#editBanConfigModal").modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	function addBan() {
		$("#addBanModal").modal({
			backdrop : 'static',
			keyboard : false
		});
	}
</script>