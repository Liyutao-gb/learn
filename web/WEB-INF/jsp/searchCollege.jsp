<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<title>查询学院 在线学习平台ForFuture</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="ForFuture Group、ForFuturn News,ForFuture Share,ForFuture Shopping" />
	<meta name="keywords" content="在线学习平台，知识共享" />
	<link rel="shortcut icon"	href="https://8.url.cn/edu/edu_modules/edu-ui/img/nohash/favicon.ico">
	<base href="<%=basePath%>">

	<!-- Vue_Pageination -->
	<link rel="stylesheet" href="resource/css/zpageNav.css" type="text/css" />
	<link rel="stylesheet" href="resource/bootstrap/css/bootstrap.min.css" >    
	<link rel="stylesheet" href="resource/bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="resource/css/wukong-ui.css">
	<link rel="stylesheet" href="resource/bootstrap/css/bootstrap-select.min.css">
	<script type="text/javascript" src="resource/lib/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="resource/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resource/bootstrap/js/bootstrap-select.min.js"></script>
	</head>

<body>
	<div class="row">
		<div class="col-lg-12">
			<ul class="breadcrumb wk-breadcrumb">
				<li><a href="javascript:void(0);">在线学习平台</a></li>
				<li><a href="javascript:void(0);">学院信息管理</a></li>
				<li><a href="javascript:void(0);">学院信息查询</a></li>
			</ul>
		</div>
	</div>

	<!-- 管理员权限操作 -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default wk-panel wk-table-tools-panel">
				<div class="panel-heading">工具栏 Tools</div>
				<div class="panel-body panel-button">
					<button id="deleteBtn" type="button" class="btn btn-default wk-tool-btn">删除</button>
					<button id="updateBtn" type="button" class="btn btn-default wk-tool-btn">编辑</button>
				</div>
			</div>
		</div>
	</div>

	<div class="row" id="collegeContainer" v-cloak>
		<div class="col-lg-12">
			<div class="panel panel-default  wk-panel">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th width="5%">选择</th>
							<th width="5%">序号</th>
							<th width="15%">学院名称</th>
							<th width="75%">学院介绍</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(college,index) in collegeList" class="showStyle">
							<td>
								<input :id="'grid_'+(college.id)" name="workerRadio" type="radio" aria-label="选择" />
							</td>
							<td>{{index+1+(page-1)*pageSize}}</td>
							<td>{{college.name}}</td>
							<td>{{college.intro}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--分页 start-->
			<div style="width: 300px; margin: 0 auto; margin-top: 10px;">
				<zpagenav  v-bind:page="page"  v-bind:page-size="pageSize" v-bind:total="total" 
							v-bind:max-page="maxPage" v-on:pagehandler="pageHandler">
				</zpagenav>
			</div>
			<!-- 分页 end -->
		</div>
	</div>

	<div class="modal fade" tabindex="-1" role="dialog" id="deleteConfirm">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header wk-modal-header">
					<button class="close" type="button" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="confirmStyle">在线学习平台</h4>
					<h4>系统提示</h4>
				</div>
				<div class="modal-body">您确定要删除这些记录？</div>
				<div class="modal-footer wk-modal-footer">
					<button id="deleteBtnOK" type="submit"
						class="btn btn-default wk-btn" data-dismiss="modal">确定</button>
					<button type="button" class="btn btn-default wk-tool-btn"
						data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<script>

    $("#deleteBtn").on("click", function() {
        var id = "";
        $(":radio").each(function() {
            if ($(this).is(":checked")) {
                id = $(this).attr("id").substr(5, $(this).attr("id").length);
            }
        });

        if (id != "") {
            $("#deleteConfirm").modal();
            $("#deleteBtnOK").on("click", function() {
                location.href = "<%=basePath%>deleteCollege/" + id + ".html";
            });
        }else{
        	alert("请选择您需要删除的学院！！");
        }
    });

    

     $("#updateBtn").on("click", function() {
        var id = "";
        $(":radio").each(function() {
            if ($(this).is(":checked")) {
                id = $(this).attr("id").substr(5, $(this).attr("id").length);
            }
        });         

        if (id != "") {
			location.href = "<%=basePath%>updateCollege/" + id+ ".html";
		}else{
        	alert("请选择您需要修改的学院！！");
        }
	 });
	</script>
	<!-- 导入Vue -->
	<script type="text/javascript" src="resource/js/vue.min.js"></script>
	<script type="text/javascript" src="resource/js/vue-resource.js"></script>
	<!-- pageination -->
	<script type="text/javascript" src="resource/js/zpageNav.js"></script>
	<script type="text/javascript">
		var collegeVue=new Vue({
			el:"#collegeContainer",
			data:{
				collegeList:[],
				page:1,//显示的是哪一页
				pageSize:5,//页面显示的数据条数
				total:100,//记录总数
				maxPage:9 //最大的页数
			},
			methods:{
				pageHandler:function(page){//跳转到page页
					this.page=page;//修改显示的页数page
					//发送Ajax请求   参数params 
					var params={"page":page,"pageSize":this.pageSize};
					//
					this.$http.post("collegeListByPage.action",params,{emulateJSON:true}).then(
						function(res){
							this.collegeList=res.data.list;
							this.total=res.data.total;
							this.maxPage=res.data.maxPage;
						},
						function(res){
							console.log(res);
						}
					);
				}
			},
			created:function(){ 
			    this.pageHandler(1); 				
			}
		});
	</script>
</body>
</html>