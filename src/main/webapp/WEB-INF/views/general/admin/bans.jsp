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
						<fmt:message key="admin.Bans.Title" />
						<small><fmt:message key="admin.Bans.Subtitle" /></small>
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
								<th><fmt:message key="admin.Bans.Id" /></th>
								<th><fmt:message key="admin.Bans.DateFrom" /></th>
								<th><fmt:message key="admin.Bans.DateTo" /></th>
								<th><fmt:message key="admin.Bans.Reason" /></th>
								<th><fmt:message key="admin.Bans.Username" /></th>
								<th><fmt:message key="admin.Bans.IpAddress" /></th>
								<th><fmt:message key="admin.Bans.Active" /></th>
								<th><fmt:message key="admin.Bans.Admin" /></th>
								<th><fmt:message key="admin.Bans.LastModifyDate" /></th>
								<th><fmt:message key="admin.Users.LastModifyBy" /></th>
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
		},
		notSelectedRow : {
			title : '<fmt:message key="general.Error"/>',
			text : '<fmt:message key="general.RowIsNotSelected"/>',
			type : 'error'
		}
	}

	$(document).ready(function() {
		var dt = $('#datatable').DataTable({
			"ajax" : {
				"url" : '${ pageContext.request.contextPath }/admin/data/ban',
				"dataSrc" : "data"
			},
			"columns" : [{
				"data" : "id"
			}, {
				"data" : "dateFrom"
			}, {
				"data" : "dateTo"
			}, {
				"data" : "reason"
			}, {
				"data" : "user.nickname"
			}, {
				"data" : "ipAddress"
			}, {
				"data" : "active"
			}, {
				"data" : "admin.nickname"
			}, {
				"data" : "lastModifiedDate"
			}, {
				"data" : "lastModifiedAdmin.nickname"
			}],
			"scrollX" : true,
			"deferRender" : true,
			"processing" : true,
			"serverSide" : true,
			"stateSave" : true,
			"columnReorder" : true,
			"createdRow" : rowFormatter,
			"dom" : 'Bfrtip',
			"stateDuration" : 60 * 60 * 24 * 365,
			"buttons" : [{
				extend : 'collection',
				text : '<i class="fa fa-cog"></i>&nbsp;<fmt:message key="general.Action" />',
				autoClose : true,
				"buttons" : [{
					text : '<fmt:message key="admin.Bans.Add" />',
					action : function(e, dt, node, config) {

						window.location.href = "${ pageContext.request.contextPath }/admin/addBan";
					}
				}, {
					text : '<fmt:message key="admin.Bans.Activate" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						if (selectedRow.active) {
							swal(messages.banIsActive);
						} else {
							swal(messages.activateBanQuestion, function() {
								activateBan(selectedRow.id);
							});

						}
					}
				}, {
					text : '<fmt:message key="admin.Bans.Deactivate" />',
					action : function(e, dt, node, config) {

						var selectedRow = getSelectedRowOrAlert();

						if (!selectedRow) {
							return;
						}

						if (!selectedRow.active) {
							swal(messages.banIsDeactive);
						} else {
							swal(messages.deactivateBanQuestion, function() {
								deactivateBan(selectedRow.id);
							});

						}
					}
				}, {
					text : '<fmt:message key="admin.Bans.BanConfig" />',
					action : function(e, dt, node, config) {

						window.location.href = "${ pageContext.request.contextPath }/admin/banConfig";
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

		$('#datatable tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				dt.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		function getSelectedRowOrAlert() {

			var selected = dt.row('.selected').data();

			if (selected) {
				return selected;
			} else {
				swal(messages.notSelectedRow);
			}
		}

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
			}).done(function(ret) {
				var msg = messages[ret.msg] || (ret.val == "true" ? SUCCESS_MSG : ERROR_MSG);
				swal(msg);
				dt.ajax.reload();
			}).fail(function(msg) {
				swal(ERROR_MSG);
			});
		}
	});

	function rowFormatter(row, data, index) {

		var bannedToString = "";
		bannedToString += data.dateTo.substring(6, 10) + "-";
		bannedToString += data.dateTo.substring(3, 5) + "-";
		bannedToString += data.dateTo.substring(0, 2) + "T";
		bannedToString += data.dateTo.substring(11);

		var now = new Date();
		var bannedTo = new Date(bannedToString);

		if (now < bannedTo && data.active) {
			$(row).css("background", "rgba(255,0,0,0.2)");
		} else if (now < bannedTo) {
			$(row).css("background", "rgba(255,255,0,0.2)");
		}
	}
</script>