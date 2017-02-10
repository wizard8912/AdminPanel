<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script>
	var messages = {

		restoreAccountQuestion : {
			title : '<fmt:message key="admin.Users.RestoreAccountQuestionTitle"/>',
			text : '<fmt:message key="admin.Users.RestoreAccountQuestionText"/>',
			type : 'warning',
			showCancelButton : true,
			cancelButtonText : '<fmt:message key="general.Cancel"/>',
			confirmButtonColor : '#DD6B55',
			confirmButtonText : '<fmt:message key="admin.Users.ConfirmRestoreAccount"/>',
			closeOnConfirm : false,
			showLoaderOnConfirm : true
		},
		accountRestored : {
			title : '<fmt:message key="general.Done"/>',
			text : '<fmt:message key="admin.Users.AccountRestored"/>',
			type : 'success'
		},
		accountNotRestored : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="admin.Users.AccountNotRestored"/>',
			type : 'error',
			confirmButtonText : '<fmt:message key="general.OK"/>'
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

			if ($el.data("item") == "restore") {
				swal(messages.restoreAccountQuestion, function() {
					restoreAccount(row.id);
				});
			}
		}
	});

	function restoreAccount(userId) {

		var URL = "/admin/data/user/restore";
		var DATA = {
			userId : userId,
			"${_csrf.parameterName}" : "${_csrf.token}"
		};
		var SUCCESS_MSG = messages.accountRestored;
		var ERROR_MSG = messages.accountNotRestored;
		doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG);
	}

	function doPostAjax(URL, DATA, SUCCESS_MSG, ERROR_MSG) {
	
	$.ajax({
			method : "POST",
			url : "${ pageContext.request.contextPath }/admin/data/user/restore",
			data : {
				userId : userId,
				"${_csrf.parameterName}" : "${_csrf.token}"
			}
		}).done(function(ret) {

			var msg = messages[ret.msg] || (ret.val == "true" ? SUCCESS_MSG : ERROR_MSG);
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
</script>