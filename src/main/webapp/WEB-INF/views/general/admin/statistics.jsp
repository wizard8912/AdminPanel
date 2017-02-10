<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-md-6 col-sm-6 col-xs-12">
	<div class="x_panel">
		<div class="x_title">
			<h2>
				<fmt:message key="general.Browsers" />
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

			<div id="browsers" style="height: 350px;"></div>

		</div>
	</div>
</div>
<div class="col-md-6 col-sm-6 col-xs-12">
	<div class="x_panel">
		<div class="x_title">
			<h2>
				<fmt:message key="general.Os" />
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

			<div id="systems" style="height: 350px;"></div>

		</div>
	</div>
</div>
<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel">
		<div class="x_title">
			<h2>
				<fmt:message key="general.Visits" />
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

			<div id="visits" style="height: 350px; width: 90%;"></div>

		</div>
	</div>
</div>

<script>
	function drawChartBrowsers() {
		var jsonData = $.ajax({
			url : "${ pageContext.request.contextPath }/admin/data/browsersusage",
			dataType : "json",
			async : false
		}).responseJSON;

		var browsersNames = [];
		var browsersUsages = [];
		var browsersData = [];

		for (i = 0; i < jsonData.length; i++) {

			browsersNames.push(jsonData[i].browserName);
			browsersUsages.push(jsonData[i].usages);

			browsersData.push({
				value : jsonData[i].usages,
				name : jsonData[i].browserName
			});
		}

		var myChart = echarts.init(document.getElementById('browsers'), theme);
		myChart.setOption({
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				//orient: 'vertical',
				//x: 'left',
				x : 'center',
				y : 'bottom',
				data : browsersNames
			},
			toolbox : {
				show : true,
				feature : {
					magicType : {
						show : true,
						type : ['pie', 'funnel'],
						option : {
							funnel : {
								x : '25%',
								width : '50%',
								funnelAlign : 'left',
								max : 1548
							}
						}
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			series : [{
				type : 'pie',
				radius : '65%',
				center : ['50%', '48%'], //left,top
				data : browsersData
			}]
		});
	}

	function drawChartOs() {
		var jsonData = $.ajax({
			url : "${ pageContext.request.contextPath }/admin/data/osusage",
			dataType : "json",
			async : false
		}).responseJSON;

		var osNames = [];
		var osUsages = [];
		var osData = [];

		for (i = 0; i < jsonData.length; i++) {

			osNames.push(jsonData[i].osName);
			osUsages.push(jsonData[i].usages);

			osData.push({
				value : jsonData[i].usages,
				name : jsonData[i].osName
			});
		}

		var myChart = echarts.init(document.getElementById('systems'), theme);
		myChart.setOption({
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				//orient: 'vertical',
				//x: 'left',
				x : 'center',
				y : 'bottom',
				data : osNames
			},
			toolbox : {
				show : true,
				feature : {
					magicType : {
						show : true,
						type : ['pie', 'funnel'],
						option : {
							funnel : {
								x : '25%',
								width : '50%',
								funnelAlign : 'left',
								max : 1548
							}
						}
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			series : [{
				type : 'pie',
				radius : '65%',
				center : ['50%', '48%'], //left,top
				data : osData
			}]
		});
	}

	function drawChartVisits() {
		var jsonData = $.ajax({
			url : "${ pageContext.request.contextPath }/admin/data/visit",
			dataType : "json",
			async : false
		}).responseJSON;

		var rowUnique = [];
		var rowTotal = [];
		var dates = [];

		for (i = 0; i < jsonData.length; i++) {

			rowUnique.push(jsonData[i].uniqueVisit);
			rowTotal.push(jsonData[i].generalVisit);
			dates.push(jsonData[i].date);
		}

		var myChart = echarts.init(document.getElementById('visits'), theme);
		myChart.setOption({
			tooltip : {
				trigger : 'axis'
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
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
				start : (dates.length - 20) / dates.length * 100,
				end : 100
			},
			legend : {
				data : ['Total', 'Unique']
			},
			xAxis : [{
				type : 'category',
				data : dates
			}],
			yAxis : [{
				type : 'value',
				name : 'Visits',
				axisLabel : {
					formatter : '{value}'
				}
			}],
			series : [

			{
				name : 'Total',
				type : 'bar',
				data : rowTotal
			}, {
				name : 'Unique',
				type : 'line',
				data : rowUnique
			}]
		});
	}

	drawChartBrowsers();
	drawChartOs();
	drawChartVisits();
</script>