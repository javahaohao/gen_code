<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>role Info</title>
		<link type="text/css" rel="stylesheet" href="<%=path %>/css/lib.ui.core.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path %>/css/lib.ui.form.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/validate.css" />
		<style type="text/css">
			.form-line{
				height: 35px;
			}
			.form-line select{
				width:100%;
			}
			.form-line .form-input{
				width:250px;
			}
		</style>
	</head>
	<body>
		<div class="form">
			<form id="otForm" method="post" action="<%=path %>/${(camel(table.tableName)?cap_first)?lower_case}/${"$"}{empty ${camel(table.tableName)}.id?'save':'update'}.do">
				<input type="hidden" id="id" name="id" class="text" value="${"$"}{${camel(table.tableName)}.id}"/>
				<#list table.columns as column>
				<#if !column.primary && !contains(excludeColumns,column.columnName)>
				<div class="form-line">
					<div class="form-label">${column.columnComment}</div>
					<div class="form-input">
						<input type="hidden" name="${camel(column.columnName)}" value="${"$"}{${camel(table.tableName)}.${camel(column.columnName)}}"/>
					</div>
				</div>
				</#if>
				</#list>
			</form>
		</div>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="<%=path%>/js/validate.js"></script>	
		<script type="text/javascript">
		var validate;
		${"$"}(document).ready(function(){
			/*验证*/
			validate = new Validate();
		});
		</script>
	</body>
</html>