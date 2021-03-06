<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.threebody.com/cp" prefix="cp" %>
<!DOCTYPE HTML>
<html>
<head></head>
<body>
<form action="admin/dictManage" method="post" >
	<div class="tab-search">
		<ul>
			<li>字典编码：<input type="text" name="dictCode" value="${dictCode}"/></li>
			<li>字典名称：<input type="text" name="dictName" value="${dictName}"/></li>
			<li>
				<a href="javascript:void(0);" class="easyui-linkbutton" 
						data-options="iconCls:'icon-search'" onclick="$css.tabSearch(this)">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" 
						data-options="iconCls:'icon-clear'" onclick="$css.form_reset(this)">重置</a>
			</li>
		</ul>
	</div>
</form>
<div class="btn-header">
	<a href="page/addOrUpdateDict" class="easyui-linkbutton addDict" data-options="iconCls:'icon-add'" >添加字典</a>
</div>
<table class="bordered" id="dictList">
	<tr>
		<th style="width:5%">序号</th>
		<th style="width:12%;">字典编码</th>
		<th style="width:15%;">字典名称</th>
		<th style="width:25%;">备注</th>
		<th style="width:12%;">操作</th>
	</tr>
	<% int i=0; %>
	<c:forEach var="dict" items="${page.result}" >
	<tr>
		<td><%=++i %></td>
		<td>${dict.dictCode}</td>
		<td>${dict.dictName}</td>
		<td>${dict.remark}</td>
		<td >
			<a href="javascript:void(0);" dictid="${dict.id}" class="dict_clause fa fa-bars">
				<span>字典项</span>
			</a>
			<a href="page/addOrUpdateDict?id=${dict.id}" class="editDict fa fa-edit">
				<span>编辑</span>
			</a>
			<a href="dict/delete?id=${dict.id}" class="delDict fa fa-trash" >
				<span>删除</span>
			</a>
		</td>
	</tr>
	</c:forEach>
</table>
<div class="pageSplit">
	<cp:pageSplit page="${page}" />
</div>
<script type="text/javascript" >
$(function(){
	//给分页按钮添加点击事件
	$("#DictClause").prev(".pageSplit").find("a.page_btn").on("click",$css.jumpPage);
	//给删除添加委托事件
	$("#dictList")
	.on("click","a.delDict",{url:"admin/dictManage"},$css.delRecord)
	.on("click","a.editDict",{tabName:"编辑字典"},$css.editRecord);
	$("a.addDict").on("click",{tabName:"添加字典"},$css.editRecord);
	/**
	 * 打开配置字典项页面
	 */
	var openDictClause = function(event) {
		var dictId = $(event.currentTarget).attr("dictid");
		var save = $css.buildDialogSave({formId:"dictClause",saveLine:true});
		var cancel = function(e){
			if($css.newLineFlag){
				//只有在新增了一行以后,保存与编辑才有效
				return;
			}
			var $form = $("form#dictClause");
			$css.addLine($form.find("tr:last span.comment"));
		};
		$("<div></div>").dialog({
		    title: "配置字典项",
			width: 600,
			height: 400,
			closed: false,
			cache: false,
			modal: true,
			href: "dict/dictClause?id="+dictId,
			onClose : function() {
                $(this).dialog("destroy");
            },
			buttons:[{
				text:"保存",
				handler:save,
				iconCls:"icon-save"
			},{
				text:"取消",
				handler:cancel,
				iconCls:"icon-cancel"
			}]
		});
	};
	$("#dictList").on("click","a.dict_clause",openDictClause);
});
</script>
</body>
</html>