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
		'packages' : [ 'corechart', 'calendar', 'corechart' ],
		'language' : '${localeCode}'
	});
	google.charts.setOnLoadCallback(drawCharts);

	function drawCharts() {
		drawBrowsersChart();
		drawOsChart();
		drawVisitsCharts();
	}

	function drawBrowsersChart() {
		var browsersDetails = [];
		browsersDetails.push([ 'Browser', 'Usages' ]);

		var jsonData = $
				.ajax({
					url : "${ pageContext.request.contextPath }/admin/data/browsersusage",
					dataType : "json",
					async : false
				}).responseJSON;

		var row = [];
		for (i = 0; i < jsonData.length; i++) {

			row.push(jsonData[i].browserName);
			row.push(jsonData[i].usages);
			browsersDetails.push(row);
			row = [];
		}

		browsersDetails = google.visualization
				.arrayToDataTable(browsersDetails);

		var options = {
			is3D : true,
		};

		var browsersChart = new google.visualization.PieChart(document
				.getElementById('browsers_3d'));
		browsersChart.draw(browsersDetails, options);
	}

	function drawOsChart() {
		var osDetails = [];
		osDetails.push([ 'Browser', 'Usages' ]);

		var jsonData = $.ajax({
			url : "${ pageContext.request.contextPath }/admin/data/osusage",
			dataType : "json",
			async : false
		}).responseJSON;

		var row = [];
		for (i = 0; i < jsonData.length; i++) {

			row.push(jsonData[i].osName);
			row.push(jsonData[i].usages);
			osDetails.push(row);
			row = [];
		}

		osDetails = google.visualization.arrayToDataTable(osDetails);

		var options = {
			is3D : true,
		};

		var osChart = new google.visualization.PieChart(document
				.getElementById('os_3d'));
		osChart.draw(osDetails, options);
	}

	function drawVisitsCharts() {
		var dataUniqueVisits = new google.visualization.DataTable();
		var dataTotalVisits = new google.visualization.DataTable();
		var dataVisitsChart = new google.visualization.DataTable();

		dataUniqueVisits.addColumn({
			type : 'date',
			id : 'Date'
		});
		dataUniqueVisits.addColumn({
			type : 'number',
			id : 'unique'
		});
		dataTotalVisits.addColumn({
			type : 'date',
			id : 'Date'
		});
		dataTotalVisits.addColumn({
			type : 'number',
			id : 'total'
		});
		dataVisitsChart.addColumn({
			type : 'date',
			id : 'Date'
		});
		dataVisitsChart.addColumn({
			type : 'number',
			id : 'unique',
			label : '<fmt:message key="admin.Statistics.UniqueVisits" />'
		});
		dataVisitsChart.addColumn({
			type : 'number',
			id : 'total',
			label : '<fmt:message key="admin.Statistics.TotalVisits" />'
		});

		var jsonData = $.ajax({
			url : "${ pageContext.request.contextPath }/admin/data/visit",
			dataType : "json",
			async : false
		}).responseJSON;

		for (i = 0; i < jsonData.length; i++) {
			var rowUnique = [];
			var rowTotal = [];
			var rowDVChart = [];
			var dt = jsonData[i].date;
			var date = new Date(dt.substring(0, 4), dt.substring(5, 7) - 1, dt
					.substring(8, 10));
			rowUnique.push(date);
			rowTotal.push(date);
			rowDVChart.push(date);

			rowUnique.push(jsonData[i].uniqueVisit);
			rowTotal.push(jsonData[i].generalVisit);
			rowDVChart.push(jsonData[i].generalVisit);
			rowDVChart.push(jsonData[i].uniqueVisit);

			dataUniqueVisits.addRow(rowUnique);
			dataTotalVisits.addRow(rowTotal);
			dataVisitsChart.addRow(rowDVChart);

			rowUnique = [];
			rowTotal = [];
			rowDVChart = [];
		}

		var chartUniqueVisits = new google.visualization.Calendar(document
				.getElementById('unique_calendar'));
		var chartTotalVisits = new google.visualization.Calendar(document
				.getElementById('total_calendar'));
		var chartAllVisits = new google.visualization.ColumnChart(document
				.getElementById('visitsChart'));

		var optionsUnique = {
			title : '<fmt:message key="admin.Statistics.UniqueVisits" />',
			height : 350,
		};

		var optionsTotal = {
			title : '<fmt:message key="admin.Statistics.TotalVisits" />',
			height : 350,
		};

		var optionsAll = {
			width : '100%',
			height : 400
		};

		chartUniqueVisits.draw(dataUniqueVisits, optionsUnique);
		chartTotalVisits.draw(dataTotalVisits, optionsTotal);
		chartAllVisits.draw(dataVisitsChart, optionsAll);
	}
</script>