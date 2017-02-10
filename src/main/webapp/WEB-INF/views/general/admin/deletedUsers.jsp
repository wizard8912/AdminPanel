<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						<fmt:message key="admin.DeletedUsers.Title" />
						<small><fmt:message key="admin.DeletedUsers.Subtitle" /></small>
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
							<tr>
								<th><fmt:message key="admin.Users.Id" /></th>
								<th><fmt:message key="admin.Users.Nickname" /></th>
								<th><fmt:message key="admin.Users.Email" /></th>
								<th><fmt:message key="admin.Users.Registerdate" /></th>
								<th><fmt:message key="admin.Users.Activatedate" /></th>
								<th><fmt:message key="admin.Users.DeletedDate" /></th>
								<th><fmt:message key="admin.Users.DeletedBy" /></th>
							</tr>
						</thead>
						<tfoot>

						</tfoot>
					</table>

				</div>
			</div>
		</div>
	</div>
</div>

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
		},
		notSelectedRow : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="general.RowIsNotSelected"/>',
			type : 'error'
		}
	};

	var deletedUsersTable;

	$(document).ready(function() {
		deletedUsersTable = $('#datatable').DataTable({
			"ajax" : {
				"url" : '${ pageContext.request.contextPath }/admin/data/deletedUser',
				"dataSrc" : "data"
			},
			"columns" : [{
				"data" : "id"
			}, {
				"data" : "nickname"
			}, {
				"data" : "email"
			}, {
				"data" : "registerdate"
			}, {
				"data" : "activateDate"
			}, {
				"data" : "deletedDate"
			}, {
				"data" : "deletedBy"
			}],
			"scrollX" : true,
			"deferRender" : true,
			"processing" : true,
			"serverSide" : true,
			"stateSave" : true,
			"columnReorder" : true,
			"dom" : 'Bfrtip',
			"stateDuration" : 60 * 60 * 24 * 365,
			"buttons" : [{
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
			}, {
				text : '<fmt:message key="admin.DeletedUsers.Recovery" />',
				action : function(e, dt, node, config) {

					var selected = deletedUsersTable.row('.selected').data();

					if (selected) {
						recoveryUser(selected.id);
					} else {
						swal(messages.notSelectedRow);
						return;
					}
				}
			}]
		});

		$('#datatable tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				deletedUsersTable.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
	});

	function recoveryUser(userID) {

		swal(messages.restoreAccountQuestion, function() {
			$.ajax({
				method : "POST",
				url : "${ pageContext.request.contextPath }/admin/data/user/restore",
				data : {
					userId : userID,
					"${_csrf.parameterName}" : "${_csrf.token}"
				}
			}).done(function(ret) {

				var SUCCESS_MSG = messages.accountRestored;
				var ERROR_MSG = messages.accountNotRestored;
				var msg = messages[ret.msg] || (ret.val == "true" ? SUCCESS_MSG : ERROR_MSG);
				swal(msg);

				deletedUsersTable.ajax.reload();
			}).fail(function(jqXHR, textStatus, errorThrown) {
				if (jqXHR.status == 403) {
					swal(messages.accessDisabled);
				} else {
					swal(messages.unexpectedError);
				}
			})
		});
	}
</script>