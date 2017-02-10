<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="">
	<div class="row">

		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						<fmt:message key="admin.Operations.Title" />
						<small><fmt:message key="admin.Operations.Subtitle" /></small>
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
								<th><fmt:message key="admin.Operations.Id" /></th>
								<th><fmt:message key="admin.Operations.operationDate" /></th>
								<th><fmt:message key="admin.Operations.authType" /></th>
								<th><fmt:message key="admin.Operations.characterEncoding" /></th>
								<th><fmt:message key="admin.Operations.contentLength" /></th>
								<th><fmt:message key="admin.Operations.contentType" /></th>
								<th><fmt:message key="admin.Operations.localName" /></th>
								<th><fmt:message key="admin.Operations.localPort" /></th>
								<th><fmt:message key="admin.Operations.method" /></th>
								<th><fmt:message key="admin.Operations.pathInfo" /></th>
								<th><fmt:message key="admin.Operations.protocol" /></th>
								<th><fmt:message key="admin.Operations.queryString" /></th>
								<th><fmt:message key="admin.Operations.remoteAddr" /></th>
								<th><fmt:message key="admin.Operations.remoteHost" /></th>
								<th><fmt:message key="admin.Operations.remotePort" /></th>
								<th><fmt:message key="admin.Operations.remoteUser" /></th>
								<th><fmt:message key="admin.Operations.requestURL" /></th>
								<th><fmt:message key="admin.Operations.methodSignature" /></th>
								<th><fmt:message key="admin.Operations.acceptLanguage" /></th>
								<th><fmt:message key="admin.Operations.userAgent" /></th>
								<th><fmt:message key="admin.Operations.accept" /></th>
								<th><fmt:message key="admin.Operations.referer" /></th>
								<th><fmt:message key="admin.Operations.originalUri" /></th>
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
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables/dataTables.scroller.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/datatables.js"></script>
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/jquery.spring-friendly.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var dt = $('#datatable').DataTable({
			"ajax" : {
				"url" : '${ pageContext.request.contextPath }/admin/data/operation',
				"dataSrc" : "data"
			},
			"columns" : [{
				"data" : "id"
			}, {
				"data" : "operationDate"
			}, {
				"data" : "authType"
			}, {
				"data" : "characterEncoding"
			}, {
				"data" : "contentLength"
			}, {
				"data" : "contentType"
			}, {
				"data" : "localName"
			}, {
				"data" : "localPort"
			}, {
				"data" : "method"
			}, {
				"data" : "pathInfo"
			}, {
				"data" : "protocol"
			}, {
				"data" : "queryString"
			}, {
				"data" : "remoteAddr"
			}, {
				"data" : "remoteHost"
			}, {
				"data" : "remotePort"
			}, {
				"data" : "remoteUser"
			}, {
				"data" : "requestURL"
			}, {
				"data" : "methodSignature"
			}, {
				"data" : "acceptLanguage"
			}, {
				"data" : "userAgent"
			}, {
				"data" : "accept"
			}, {
				"data" : "referer"
			}, {
				"data" : "originalUri"
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
	});
</script>