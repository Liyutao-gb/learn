<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>编辑单词-在线学习平台</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" href="resource/bootstrap/css/bootstrap.min.css" >    
	<link rel="stylesheet" href="resource/bootstrap/css/bootstrap-theme.min.css">
	<script type="text/javascript" src="resource/lib/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="resource/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="resource/css/wukong-ui.css">
	<link rel="stylesheet" href="resource/bootstrap/css/bootstrap-select.min.css">
    <script type="text/javascript" src="resource/bootstrap/js/bootstrap-select.min.js"></script>
</head>

<body>
<div class="row">
    <div class="col-lg-12">
        <ul class="breadcrumb wk-breadcrumb">
            <li><a href="javascript:void(0);">在线学习平台</a></li>
            <li><a href="javascript:void(0);">单词管理</a></li>
            <li><a href="javascript:void(0);">编辑单词</a></li>
        </ul>
    </div>
</div>
    
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default wk-panel ">
                <div class="panel-heading">
                    编辑单词 Update Word
                </div>
                <form action="<%=basePath%>/editWord/${word.id}.html" method="POST">
                <div class="panel-body">
                    <div class="row">
                    <div class="form-inline"> 
	                   		<div class="form-group">
									<label for="filePath" class="control-label wk-filed-label">所属课程:
									</label> <select class="selectpicker" name="belongTo">
										<c:forEach items="${courses}" var="var">
											<c:choose>
								          		<c:when test="${var.id == discuss.courseId}">
								          			<option value="${var.id}" selected="selected">${var.name}</option>
								          		</c:when>
								          		<c:otherwise>
								          			<option value="${var.id}" >${var.name}</option>
								          		</c:otherwise>
								          	</c:choose>
										</c:forEach>
									</select>
								</div>
	                   	</div>
                      <div class="form-inline">  
                            <div class="form-group">
                                <label for="filePath" class="control-label wk-filed-label">单词原型: </label>
                                 <div class="input-group">
                                    <input required="required" name="content" type="text" class="form-control wk-long-2col-input" value="${word.content}"placeholder="请输入单词原型"/>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-inline">
                        	<div class="form-group">
                                <label for="filePath" class="control-label wk-filed-label">中文含义: </label>
                                <div class="input-group">
                                    <input required="required" name="values" type="text" value="${word.values}" class="form-control wk-long-2col-input" style="height:300px;" placeholder="请输入中文含义"/>
                                </div>
                            </div>
                        </div>

						<div class="form-inline">
                        	<div class="form-group">
                                <label for="filePath" class="control-label wk-filed-label">造句样例: </label>
                                <div class="input-group">
                                    <input required="required" name="sentence" type="text" value="${word.sentence}" class="form-control wk-long-2col-input" style="height:300px;" placeholder="请输入造句样例"/>
                                </div>
                            </div>
                        </div>
                    </div>
                 </div>

                <div class="panel-footer wk-panel-footer">
                    <button type="submit" class="btn btn-default wk-btn">提&nbsp;&nbsp;交</button>
                </div>
                </form>
            </div>
        </div>
    </div>

</body>
</html>