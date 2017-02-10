<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row tile_count">
	<div
		class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
		<div class="left"></div>
		<div class="right">
			<span class="count_top"><i class="fa fa-bar-chart"></i> <fmt:message
					key="admin.Resources.MemUsageNow" /></span>
			<div id="memUsageCount" class="count">?</div>
			<span class="count_bottom"><i id="memUsagePercents" class="">?
			</i> <fmt:message key="general.LastDayChange" /></span>
		</div>
	</div>
	<div
		class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
		<div class="left"></div>
		<div class="right">
			<span class="count_top"><i class="fa fa-bar-chart"></i> <fmt:message
					key="admin.Resources.CpuUsageNow" /></span>
			<div id="cpuUsageCount" class="count">?</div>
			<span class="count_bottom"><i id="cpuUsagePercents" class="">?
			</i> <fmt:message key="general.LastDayChange" /></span>
		</div>
	</div>
	<div
		class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
		<div class="left"></div>
		<div class="right">
			<span class="count_top"><i class="fa fa-bar-chart"></i> <fmt:message
					key="admin.Resources.FSUsageNow" /></span>
			<div id="fsUsageCount" class="count">?</div>
			<span class="count_bottom"><i id="fsUsagePercents" class="">?
			</i> <fmt:message key="general.LastDayChange" /></span>
		</div>
	</div>
</div>

<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel">
		<div class="x_title">
			<h2>Memory + CPU</h2>
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

			<div id="memoryAndCpu" style="height: 350px;"></div>

		</div>
	</div>
</div>
<div class="col-md-6 col-sm-6 col-xs-12">
	<div class="x_panel">
		<div class="x_title">
			<h2>Memory</h2>
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

			<div id="memory" style="height: 350px;"></div>

		</div>
	</div>
</div>
<div class="col-md-6 col-sm-6 col-xs-12">
	<div class="x_panel">
		<div class="x_title">
			<h2>CPU</h2>
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

			<div id="cpu" style="height: 350px;"></div>

		</div>
	</div>
</div>
<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel">
		<div class="x_title">
			<h2>File system</h2>
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

			<div id="fileSys" style="height: 350px;"></div>

		</div>
	</div>
</div>

<script>
	var memoryAndCpuChart = echarts.init(document.getElementById('memoryAndCpu'), theme);
	var memoryChart = echarts.init(document.getElementById('memory'), theme);
	var cpuChart = echarts.init(document.getElementById('cpu'), theme);
	var fsChart = echarts.init(document.getElementById('fileSys'), theme);

	var jsonData = $.ajax({
		url : "${ pageContext.request.contextPath }/admin/data/resources",
		dataType : "json",
		async : false
	}).responseJSON;

	var date = [];
	var memory = [];
	var cpu = [];
	var dateFileSys = [];
	var fileSys = [];

	var jsonDataLength = jsonData.length;
	var memAvg = 0;
	var cpuAvg = 0;
	for (i = 0; i < jsonDataLength; i++) {
		date.push(jsonData[i].date);
		memory.push(jsonData[i].memUsage.toFixed(2));
		cpu.push(jsonData[i].cpuUsage.toFixed(2));

		if (jsonDataLength - 1500 < i) {
			memAvg += jsonData[i].memUsage;
			cpuAvg += jsonData[i].cpuUsage;
		}
	}
	memAvg = memAvg / (jsonDataLength > 1500 ? 1500 : jsonDataLength);
	cpuAvg = cpuAvg / (jsonDataLength > 1500 ? 1500 : jsonDataLength);
	memDiff = memory[memory.length - 1] - memAvg;
	CpuDiff = cpu[cpu.length - 1] - cpuAvg;
	memDiff = memDiff.toFixed(0);
	CpuDiff = CpuDiff.toFixed(0);

	if (memDiff == 0) {
		$("#memUsagePercents").text("0%");
	} else if (memDiff > 0) {
		$("#memUsagePercents").text("+" + memDiff + "%");
		$("#memUsagePercents").addClass("red");
	} else {
		$("#memUsagePercents").text("" + memDiff + "%");
		$("#memUsagePercents").addClass("green");
	}

	if (CpuDiff == 0) {
		$("#cpuUsagePercents").text("0%");
	} else if (CpuDiff > 0) {
		$("#cpuUsagePercents").text("+" + CpuDiff + "%");
		$("#cpuUsagePercents").addClass("red");
	} else {
		$("#cpuUsagePercents").text("" + CpuDiff + "%");
		$("#cpuUsagePercents").addClass("green");
	}

	$("#memUsageCount").text(memory[memory.length - 1] + "%");
	$("#cpuUsageCount").text(cpu[cpu.length - 1] + "%");

	var jsonDataFS = $.ajax({
		url : "${ pageContext.request.contextPath }/admin/data/resourcesfs",
		dataType : "json",
		async : false
	}).responseJSON;

	jsonDataLength = jsonDataFS.length;
	var fsAvg = 0;
	var b = 0;
	for (i = 0; i < jsonDataLength; i++) {

		dateFileSys.push(jsonDataFS[i].date);
		fileSys.push(jsonDataFS[i].fileSysUsage);

		if (jsonDataLength - 25 <= i) {

			fsAvg += jsonDataFS[i].fileSysUsage;
		}
	}

	fsAvg = fsAvg / (jsonDataLength > 25 ? 25 : jsonDataLength);
	fsDiff = jsonDataFS[jsonDataLength - 1].fileSysUsage - fsAvg;
	fsDiff = fsDiff.toFixed(0);

	if (fsDiff == 0) {
		$("#fsUsagePercents").text("0%");
	} else if (fsDiff > 0) {
		$("#fsUsagePercents").text("+" + fsDiff + "%");
		$("#fsUsagePercents").addClass("red");
	} else {
		$("#fsUsagePercents").text("" + fsDiff + "%");
		$("#fsUsagePercents").addClass("green");
	}

	$("#fsUsageCount").text(fileSys[fileSys.length - 1] + "%");

	var maxM = Math.max.apply(Math, memory) + 5;
	var maxC = Math.max.apply(Math, cpu) + 5;
	var minM = Math.min.apply(Math, memory) - 5;
	var minC = Math.min.apply(Math, cpu) - 5;
	var maxFS = Math.max.apply(Math, fileSys) + 5;
	var minFS = Math.min.apply(Math, fileSys) - 5;

	var maxV = Math.round(maxM > maxC ? maxM : maxC);
	var minV = Math.round(minM < minC ? minM : minC);
	var minV = minV < 0 ? 0 : minV;

	var option = {
		title : {
			text : 'Memory + CPU'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : ['Memory', 'CPU']
		},
		toolbox : {
			show : true,
			feature : {

				dataZoom : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : ['line', 'bar']
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		dataZoom : {
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		xAxis : [{
			type : 'category',
			boundaryGap : true,
			axisTick : {
				onGap : false
			},

			data : date
		}],
		yAxis : [{
			type : 'value',
			splitNumber : 5,
			boundaryGap : [0.01, 0.01],
			min : minV,
			max : maxV
		}, {
			type : 'value'
		}],
		series : [{
			name : 'Memory',
			type : 'line',
			data : memory
		}, {
			name : 'CPU',
			type : 'line',
			data : cpu
		}]
	};

	memoryAndCpuChart.setOption(option);
	option.title.text = 'Memory';
	option.legend.data = ['Memory'];
	option.yAxis.min = minM;
	option.yAxis.max = maxM;
	option.series = [{
		name : 'Memory',
		type : 'line',
		data : memory
	}];
	memoryChart.setOption(option);

	option.title.text = 'CPU';
	option.legend.data = ['CPU'];
	option.yAxis.min = minC;
	option.yAxis.max = maxC;
	option.series = [{
		name : 'CPU',
		type : 'line',
		data : cpu
	}];
	cpuChart.setOption(option);

	option.title.text = 'File system';
	option.legend.data = ['File system'];
	option.yAxis.min = minFS;
	option.yAxis.max = maxFS;
	option.xAxis[0].data = dateFileSys;
	option.series = [{
		name : 'File system',
		type : 'line',
		data : fileSys
	}];
	fsChart.setOption(option);
	console.log(option);
</script>