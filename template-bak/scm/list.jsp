<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" uri="/WEB-INF/tld/pagetag.tld" %> 
<%@ taglib prefix="elf" uri="/WEB-INF/tld/elfunc.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${table.tableComment}</title>
			<link type="text/css" rel="stylesheet" href="<%=path%>/css/lib.ui.core.css"/>
			<link type="text/css" rel="stylesheet" href="<%=path%>/css/widget/lib.ui.button.css"/>
			<link type="text/css" rel="stylesheet" href="<%=path%>/css/widget/lib.ui.toolbar.css"/>
			<link type="text/css" rel="stylesheet" href="<%=path%>/css/widget/lib.ui.window.css"/>
			<link type="text/css" rel="stylesheet" href="<%=path%>/css/widget/lib.ui.grid.css"/>
			<link type="text/css" rel="stylesheet" href="<%=path%>/css/lib.ui.form.css"/>
			<link type="text/css" rel="stylesheet" href="<%=path%>/css/search.css" />
			<link type="text/css" rel="stylesheet" href="<%=path%>/css/scm/ajaxSearch.css" />
		<style type="text/css">
		.page{
			margin-bottom: 25px;
		}
		.table-head td span{
			white-space: normal;
		}
		#div1 { width: 16px;  position: absolute; z-index:11111;
		right: 0px; top: 47px; background: #F3F3F3; 
		} 
		#div2 { width: 16px; height: 20px; position: absolute; z-index:11111;
		right: 0; top: 0; background: #C1D3FB; margin-bottom: 25px;} 
		
		</style>							
	</head>
<body>
	<div id="div1"> 
           <div id="div2"></div> 
       </div> 

	<div class="tool">
	</div>
	<form id="listForm" action="<%=path%>/${(camel(table.tableName)?cap_first)?lower_case}.do" method="post">
		<div class="grid" id="div3">
			<div class="table-head" >
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td><span class="num" style="width:25px;">&nbsp;</span></td>
							<td><span style="width:20px;"><input type="checkbox" id="chkAll"/></span></td>
							<#list table.columns as column>
							<#if !column.primary && !contains(excludeColumns,column.columnName)>
							<td><span style="text-align:center;width:120px;">${column.columnComment}</span></td>
							</#if>
							</#list>
						</tr>
					</thead>
				</table>
			</div>
			<div class="table-body" id="div4">
				<table cellspacing="0" cellpadding="0">
					<tbody>
						<c:forEach var="${camel(table.tableName)}" varStatus="status" items="${"$"}{list}">
							<tr>
								<td><span class="num" style="width:25px;">${"$"}{status.index+1}</span></td>
								<td><span style="width:20px;text-align: center;">
									<input type="checkbox" name="idList" id="chk_${"$"}{${camel(table.tableName)}.id}" value="${"$"}{${camel(table.tableName)}.id}"/></span>
								</td>
								<#list table.columns as column>
								<#if !column.primary && !contains(excludeColumns,column.columnName)>
								<td align="center">
									<span style="text-align:center;width:120px;">${"$"}{${camel(table.tableName)}.${camel(column.columnName)}}</span>
								</td>
								</#if>
								</#list>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<page:page form="listForm" page="${"$"}{pageobj}"></page:page>
		<input type="hidden" name ="nowPage" id="nowPage" value="${"$"}{pageobj.nowPage }" />
		<input type="hidden" name ="pageSize"  id="pageSize" value="${"$"}{pageobj.pageSize }" />
	</form>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="<%=path%>/js/BoxSelect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/lib.ui.core.js"></script>
	<script type="text/javascript" src="<%=path%>/js/widget/lib.ui.button.js"></script>
	<script type="text/javascript" src="<%=path%>/js/widget/lib.ui.toolbar.js"></script>
	<script type="text/javascript" src="<%=path%>/js/widget/lib.ui.window.js"></script>
	<script type="text/javascript" src="<%=path%>/js/widget/lib.ui.drag.js"></script>
	<script type="text/javascript">
		$(function(){
			var tool = $('.tool').toolbar({
				items: [{
						text: '<fmt:message key="insert"/>',
						title: '<fmt:message key="insert"/>',
						useable: <c:out value="${"$"}{operateMap['insert']}" default="false"/>,
						icon: {
							url: '<%=path%>/image/Button/op_owner.gif',
							position: ['-38px','0px']
						},
						handler: function(){
							add();
						}
					},{
						text: '<fmt:message key="update"/>',
						title: '<fmt:message key="update"/>',
						useable: <c:out value="${"$"}{operateMap['update']}" default="false"/>,
						icon: {
							url: '<%=path%>/image/Button/op_owner.gif',
							position: ['-38px','0px']
						},
						handler: function(){
							update();
						}
					},{
						text: '<fmt:message key="delete"/>',
						title: '<fmt:message key="delete"/>',
						useable: <c:out value="${"$"}{operateMap['delete']}" default="false"/>,
						icon: {
							url: '<%=path%>/image/Button/op_owner.gif',
							position: ['-38px','0px']
						},
						handler: function(){
							deleteSt();
						}
					},{
						text: '<fmt:message key="quit" />',
						title: '<fmt:message key="quit" />',
						icon: {
							url: '<%=path%>/image/Button/op_owner.gif',
							position: ['-160px','-100px']
						},
						handler: function(){
							invokeClick(${"$"}(window.parent.parent.document).find('.main').find('.tab-item').find('.button-click').find('.button-arrow').get(0));
							
						}
					}
				]
			});
			setElementHeight('.grid',['.tool'],$(document.body),50);	//计算.grid的高度
			setElementHeight('.table-body',['.table-head'],'.grid');	//计算.table-body的高度
			loadGrid();//  自动计算滚动条的js方法
			//当点击tr行的时候，tr行头的checkbox也能被选中，不用非得点击checkbox才能选中行
            ${"$"}('.grid').find('.table-body').find('tr').live("click", function () {
			     if (${"$"}(this).hasClass("bgBlue")) {
                     ${"$"}(this).removeClass("bgBlue").find(":checkbox").attr("checked", false);
			     }
			     else
			     {
                     ${"$"}(this).addClass("bgBlue").find(":checkbox").attr("checked", true);
			     }
			 });
		});
		function add(id){
			var savebody=$('body').window({
				id: 'window_ordertype',
				title: '<fmt:message key="insert" />',
				content: '<iframe id="saveOrdertype" name="saveOrdertype" frameborder="0" src="<%=path%>/om/ordertype/edit.do?id='+(id||'')+'"></iframe>',
				width: '450px',
				height: '350px',
				draggable: true,
				isModal: true,
				topBar: {
					items: [{
							text: '<fmt:message key="save" />',
							title: '<fmt:message key="save" />配置',
							useable: <c:out value="${"$"}{operateMap['insert']}" default="false"/>,
							icon: {
								url: '<%=path%>/image/Button/op_owner.gif',
								position: ['-80px','-0px']
							},
							handler: function(){
								if(window.document.getElementById("saveOrdertype").contentWindow.validate._submitValidate())
									submitFrameForm('saveOrdertype','otForm');
							}
						},{
							text: '<fmt:message key="cancel" />',
							title: '<fmt:message key="cancel" />',
							icon: {
								url: '<%=path%>/image/Button/op_owner.gif',
								position: ['-160px','-100px']
							},
							handler: function(){
								$('.close').click();
							}
						}
					]
				}
			});
		}
		function update(){
			var checkboxList = $('.grid').find('.table-body').find(':checkbox');
			if(checkboxList 
					&& checkboxList.filter(':checked').size() ==1){
				add(checkboxList.filter(':checked').eq(0).val());
			}else	if(checkboxList 
					&& checkboxList.filter(':checked').size() > 1){
				alert('<fmt:message key="please_select_data" />！');
				return ;
			}
			else{
				alert('<fmt:message key="please_select_information_you_need_to_modify" />！');
				return ;
			}
		}
		function deleteSt(){
			var checkboxList = ${"$"}('.grid').find('.table-body').find(':checkbox');
			
			if(checkboxList 
					&& checkboxList.filter(':checked').size() > 0){
				if(confirm('<fmt:message key="delete_data_confirm" />？')){
					var chkValue = [];
					
					checkboxList.filter(':checked').each(function(){
						chkValue.push($(this).val());
					});
					
					window.location.href='<%=path%>/om/ordertype/delete.do?id='+chkValue.join(",");
				}
			}else{
				alert('<fmt:message key="please_select_information_you_need_to_delete" />！');
				return ;
			}
		}
	</script>
</body>
</html>