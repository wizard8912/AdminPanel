<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'annotationchart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var dataAll = new google.visualization.DataTable();
		dataAll.addColumn('date', 'Date');
		dataAll.addColumn('number', 'Memory');
		dataAll.addColumn('number', 'CPU');

		var dataCPU = new google.visualization.DataTable();
		dataCPU.addColumn('date', 'Date');
		dataCPU.addColumn('number', 'CPU');

		var dataMemory = new google.visualization.DataTable();
		dataMemory.addColumn('date', 'Date');
		dataMemory.addColumn('number', 'Memory');

		var dataFileSystem = new google.visualization.DataTable();
		dataFileSystem.addColumn('date', 'Date');
		dataFileSystem.addColumn('number', 'File System');

		var jsonData = $.ajax({
			url : "${ pageContext.request.contextPath }/admin/data/resources",
			dataType : "json",
			async : false
		}).responseJSON;

		var row = [];
		var rowCpu = [];
		var rowMem = [];
		var rowFS = [];

		for (i = 0; i < jsonData.length; i++) {
			var dt = jsonData[i].date;
			var date = new Date(dt.substring(6, 10), dt.substring(3, 5), dt
					.substring(0, 2), dt.substring(11, 13), dt
					.substring(14, 16));
			row.push(date);
			row.push(jsonData[i].memUsage);
			row.push(jsonData[i].cpuUsage);

			rowCpu.push(date);
			rowCpu.push(jsonData[i].cpuUsage);

			rowMem.push(date);
			rowMem.push(jsonData[i].memUsage);

			dataAll.addRow(row);
			dataCPU.addRow(rowCpu);
			dataMemory.addRow(rowMem);

			row = [];
			rowCpu = [];
			rowMem = [];
		}

		var jsonDataFS = $
				.ajax({
					url : "${ pageContext.request.contextPath }/admin/data/resourcesfs",
					dataType : "json",
					async : false
				}).responseJSON;

		for (i = 0; i < jsonDataFS.length; i++) {
			var dt = jsonDataFS[i].date;
			var date = new Date(dt.substring(6, 10), dt.substring(3, 5), dt
					.substring(0, 2), dt.substring(11, 13), dt
					.substring(14, 16));
			rowFS.push(date);
			rowFS.push(jsonDataFS[i].fileSysUsage);

			dataFileSystem.addRow(rowFS);

			rowFS = [];
		}

		var chart = new google.visualization.AnnotationChart(document
				.getElementById('chart_div_all'));

		var chartCPU = new google.visualization.AnnotationChart(document
				.getElementById('chart_div_cpu'));

		var chartMEM = new google.visualization.AnnotationChart(document
				.getElementById('chart_div_memory'));

		var chartFS = new google.visualization.AnnotationChart(document
				.getElementById('chart_div_fileSystem'));

		chart.draw(dataAll);
		chartCPU.draw(dataCPU);
		chartMEM.draw(dataMemory);
		chartFS.draw(dataFileSystem);
	}
</script>