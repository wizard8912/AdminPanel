<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="localeCode" value="${pageContext.response.locale}" />

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ],
		'language' : '${localeCode}'
	});
	google.charts.setOnLoadCallback(drawCharts);

	function drawCharts() {
		drawTablesChart();
	}

	function drawTablesChart() {

		var dataTables = new google.visualization.DataTable();
		dataTables.addColumn({
			type : 'string',
			id : 'tableName'
		});
		dataTables.addColumn({
			type : 'number',
			id : 'tableSize',
			label : 'Table Size'
		});
		dataTables.addColumn({
			type : 'number',
			id : 'indexesSize',
			label : 'Indexes Size'
		});
		dataTables.addColumn({
			type : 'number',
			id : 'totalSize',
			label : 'Total Size'
		});

		var jsonData = $.ajax({
			url : "${ pageContext.request.contextPath }/admin/data/tablesSize",
			dataType : "json",
			async : false
		}).responseJSON;

		for (i = 0; i < (jsonData.length > 10 ? 10 : jsonData.length); i++) {
			var row = [];

			row.push(jsonData[i].tableSchema + '.' + jsonData[i].tableName);
			row.push(jsonData[i].tableSize / 1000000);
			row.push(jsonData[i].indexesSize / 1000000);
			row.push(jsonData[i].totalSize / 1000000);

			dataTables.addRow(row);

			row = [];
		}

		var chartDataTables = new google.visualization.ColumnChart(document
				.getElementById('chartTable'));

		var optionsAll = {
			width : '100%',
			height : 400
		};

		chartDataTables.draw(dataTables, optionsAll);

	}
</script>