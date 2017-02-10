<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="localeCode" value="${pageContext.response.locale}" />

<section id="admin-database" class="container">
	<div class="row">
		<div class="center">
			<h4>
				<fmt:message key="admin.Database" />
			</h4>
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tablesTable"><fmt:message
							key="admin.Database.TableTable" /></a></li>
				<li><a data-toggle="tab" href="#tablesChart"><fmt:message
							key="admin.Database.TableChart" /></a></li>

			</ul>
			<div class="tab-content">
				<div id="tablesTable" class="tab-pane fade in active"
					style="width: 100%;">
					<table id="table" data-toggle="table" data-toolbar="#toolbar"
						data-pagination="true" data-search="true"
						data-click-to-select="true" data-flat="true" data-search="true"
						data-show-columns="true" data-show-multi-sort="true"
						data-sort-priority='[]' data-cookie="true"
						data-cookie-id-table="tableBans2" data-advanced-search="true"
						data-id-table="tableBans" data-show-refresh="true"
						data-url="${ pageContext.request.contextPath }/admin/data/tablesSize"
						data-row-style="rowStyle" data-locale="${ localeCode }">
						<thead>
							<tr>
								<th data-field="tableSchema" data-sortable="true"><fmt:message
										key="admin.Database.TableSchema" /></th>
								<th data-field="tableName" data-sortable="true"><fmt:message
										key="admin.Database.TableName" /></th>
								<th data-field="tableSize" data-sortable="true"
									data-filter-control="input"><fmt:message
										key="admin.Database.TableSize" /></th>
								<th data-field="indexesSize" data-sortable="true"
									data-filter-control="input"><fmt:message
										key="admin.Database.IndexesSize" /></th>
								<th data-field="totalSize" data-sortable="true"
									data-filter-control="input"><fmt:message
										key="admin.Database.TotalSize" /></th>
							</tr>
						</thead>
					</table>
				</div>
				<div id="tablesChart" class="tab-pane fade" style="width: 100%;">
					<div id="chartTable" style="width: 100%; height: 350px;"></div>
				</div>
			</div>
		</div>
	</div>
</section>