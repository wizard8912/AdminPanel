<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!-- Datatables-->
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/jquery.dataTables.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/dataTables.bootstrap.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/dataTables.buttons.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/buttons.bootstrap.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/jszip.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/pdfmake.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/vfs_fonts.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/buttons.html5.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/buttons.print.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/dataTables.fixedHeader.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/dataTables.keyTable.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/dataTables.responsive.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/responsive.bootstrap.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/dataTables.scroller.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/jquery.spring-friendly.js"></script>

<script type="text/javascript">
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

	var tableUsers;

	$(document).ready(function(e) {
		var tableUsers = $('#datatable').DataTable({
			"ajax" : {
				"url" : '${ pageContext.request.contextPath }/admin/data/user',
				"dataSrc" : "data"
			},
			"columns" : [{
				"data" : "id"
			}, {
				"data" : "nickname"
			}, {
				"data" : "firstname"
			}, {
				"data" : "lastname"
			}, {
				"data" : "email"
			}, {
				"data" : "country"
			}, {
				"data" : "city"
			}, {
				"data" : "birthdate"
			}, {
				"data" : "phonenumber"
			}, {
				"data" : "registerdate"
			}, {
				"data" : "activateDate"
			}, {
				"data" : "forgetPassDate"
			}],
			"scrollX" : true,
			"deferRender" : true,
			"processing" : true,
			"serverSide" : true,
			"stateSave" : true,
			"columnReorder" : true,
			"dom" : 'Bfrtip',
			"stateDuration" : 60 * 60 * 24 * 365,
			"columnDefs" : [{
				// The `data` parameter refers to the data for the cell (defined by the
				// `data` option, which defaults to the column being worked with, in
				// this case `data: 0`.
				"render" : function(data, type, row) {
					console.log(row.id);
					return "<a href='${ pageContext.request.contextPath }/user/account/" + row.id + "'>" + data + "</a>";

				},
				"targets" : 1
			}],
			"buttons" : [{
				extend : 'collection',
				text : '<i class="fa fa-cog"></i>&nbsp;<fmt:message key="general.Action" />',
				autoClose : true,
				"buttons" : [{
					text : '<fmt:message key="admin.Users.Edit" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						editUser(selectedRow.id);
					}
				}, {
					text : '<fmt:message key="admin.Users.ChangePassword" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						changePassword(selectedRow.id);
					}
				}, {
					text : '<fmt:message key="admin.Users.Delete" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						swal(messages.deleteAccountQuestion, function() {
							deleteAccount(selectedRow.id);
						});
					}
				}, {
					text : '<fmt:message key="admin.Users.Activate" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						if (selectedRow.activateDate) {
							swal(messages.accountIsActive);
							return;
						}

						swal(messages.sendForgotPassLinkQuestion, function() {
							activateUserAccount(selectedRow.id);
						});
					}
				}, {
					text : '<fmt:message key="admin.Users.SendActivateLink" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						if (selectedRow.activateDate) {
							swal(messages.accountIsActive);
							return;
						}

						swal(messages.sendForgotPassLinkQuestion, function() {
							sendActivationLink(selectedRow.id);
						});
					}
				}, {
					text : '<fmt:message key="admin.Users.SendForgotPassLink" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						swal(messages.sendForgotPassLinkQuestion, function() {
							sendForgotPassLink(selectedRow.id);
						});
					}
				}, {
					text : '<fmt:message key="admin.Users.EditRoles" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						editRoles(selectedRow.id);
					}
				}]
			}, {
				text : '<i class="fa fa-refresh"></i>&nbsp;<fmt:message key="general.Reload" />',
				action : function(e, dt, node, config) {
					dt.ajax.reload();
				}
			}, {
				extend : 'collection',
				text : '<i class="fa fa-file-text-o"></i>&nbsp;<fmt:message key="general.Export" />',
				autoClose : true,
				"buttons" : [{
					extend : 'copy',
					text : '<i class="fa fa-clipboard"></i>&nbsp;<fmt:message key="general.Copy" />',
					exportOptions : {
						modifier : {
							page : 'current',
							columns : ':visible'
						}
					}
				}, {
					extend : 'excel',
					text : '<i class="fa fa-file-excel-o"></i>&nbsp;<fmt:message key="general.Excel" />',
					exportOptions : {
						modifier : {
							page : 'current',
							columns : ':visible'
						}
					}
				}, {
					extend : 'pdf',
					text : '<i class="fa fa-file-pdf-o"></i>&nbsp;<fmt:message key="general.Pdf" />',
					exportOptions : {
						modifier : {
							page : 'current',
							columns : ':visible'
						}
					}
				}, {
					extend : 'print',
					text : '<i class="fa fa-print"></i>&nbsp;<fmt:message key="general.Print" />',
					exportOptions : {
						modifier : {
							page : 'current',
							columns : ':visible'
						}
					}
				}]
			}, {
				extend : 'colvis',
				text : '<i class="fa fa-columns"></i>&nbsp;<fmt:message key="general.Columns" />',
				autoClose : false
			}, {
				extend : 'pageLength',
				autoClose : true
			}]
		});

		function renderCbColumn(data, type, row) {
			if (type === 'display') {
				return '<input type="checkbox" class="flat" name="table_records">';
			}
			return data;
		}

		function getSelectedRowOrAlert() {

			var selected = tableUsers.row('.selected').data();

			if (selected) {
				return selected;
			} else {
				swal(messages.notSelectedRow);
			}
		}

		$('#datatable tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				tableUsers.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		function editUser(userId) {
			window.location.href = "${ pageContext.request.contextPath }/user/" + userId + "/changeAccount";
		}

		function changePassword(userId) {
			window.location.href = "${ pageContext.request.contextPath }/user/" + userId + "/changePassword";
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
	});
</script>