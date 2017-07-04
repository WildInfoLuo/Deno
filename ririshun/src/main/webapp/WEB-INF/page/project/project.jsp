<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
 <script type="text/javascript" src="${staticURL}/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${staticURL}/js/easyui1.4/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/js/easyui1.4/themes/icon.css">
 <script type="text/javascript" src="${staticURL}/js/easyui1.4/jquery.easyui.min.js"></script> 
   <script type="text/javascript" src="${staticURL}/js/easyui1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8">

    var searchForm;
	var datagrid;
	var loginInfoAdd;
	$(function() {
		

		jQuery.prototype.serializeObject=function(){  
		    var obj=new Object();  
		    $.each(this.serializeArray(),function(index,param){  
		        if(!(param.name in obj)){  
		            obj[param.name]=param.value;  
		        }  
		    });  
		    return obj;  
		};  
		
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'showInfos.do',
			iconCls : 'icon-save',
			title:"项目信息",
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 15,
			pageList : [15, 25, 35, 45 ],
			fit : true,
			fitColumns:true,//列长度自适应，不出现横向动作条
	    	collapsible: true,//可折叠
			autoRowHeight:true,
			singleSelect: false,
			nowrap : false,
			border : false,
			idField : 'id',
			columns : [[  
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.id;
						}
					},
					{field:'id',title:'编号',align:'center',sortable:true,width:100,
						formatter:function(value,row,index){
							return row.id;
						}
					},
				 {field:'project_name',title:'项目名称',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.project_name;
					}
				},
				{field:'createBy',title:'创建人',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.createBy;
					}
				},
				{field:'createDate',title:'创建时间',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						if(value==null){
							return  "";
						}
						 var date = new Date(value);
		                 return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
					}
				},
				{field:'modifyBy',title:'修改人',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.modifyBy;
					}
				},
				{field:'modifyDate',title:'修改时间',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						if(value==null){
							return "";
						}
						 var date = new Date(value);
		                 return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
					}
				},
				{field:'ifSelect',title:'是否抽取',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						if(value==1){
							return '是';
					 	}else {
					 		return '否';
					 	}
 					}
				},
				{field:'action',title:'详情',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						var orderId = row.id;
						var d = '<a href="#" onclick="detail(\'' +orderId+ '\')">查&nbsp;看</a>';
						 return  d;
					}}	 
			 ] ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}

		});
		// 新增
		loginInfoAdd = $('#loginInfoAdd').form({
			url : 'add',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : "新增成功"
					});
					datagrid.datagrid('reload');
					itemClassAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
			}
		});
		loginInfoAddDialog = $('#add_div').show().dialog({
			title : '添加',
			modal : true,
			closed : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					//验证 该 供应商名字 是否 存在 
						var proName=$("#provider_nameadd").val();
 						$.ajax({
							url : 'validateName',
							 data: {"provider_name":proName}, 
							dataType : 'json',
							success : function(response) {
 								 if(response.ifsuccess){
									loginInfoAdd.submit();
		 							loginInfoAddDialog.dialog('close');
		 							 
								 }else{
									 $.messager.alert('提示:','该供应商名字已存在','warning'); 
								 }
							}
						});
						
						
 					
				}
			} ]
		});

		
		// 更新
		updateForm = $('#update_form').form({
			url : 'update',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : "更新成功"
					});
					datagrid.datagrid('reload');
					itemClassAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
			}
		});

		updateDialog = $('#update_div').show().dialog({
			title : '更新',
			modal : true,
			closed : true,
			buttons : [ {
				text : '保存',
				handler : function() {
					var a=$("#resourceIds2").combotree("getValues");
					$("#resourceIdup").val(a);
					$.ajax({
						url : 'validateName',
						data : a,
						dataType : 'json',
						success : function(response) {			 
						}
					});
 						updateForm.submit();
						updateDialog.dialog('close');
				}
			} ]
		});
		
		
		
		detailDialog = $('#detail_div').show().dialog({
			title : '详情',
			modal : true,
			closed : true
		});
		
	});

	function detail(a){
		//项目 详细信息 
		$.ajax({
			url : 'detail',
			data : { id : a  },
			dataType : 'json',
			success : function(response) {
				detailDialog.dialog('open');
				$('#detail_project_name').val(response.project.project_name);
				var ss="";
				for(var i = 0 ; i < response.list.length; i++){
				 
 					 ss=ss+" <li>"+response.list[i]+"</li>";
 				} 
				if(ss==""){
					$('#sss').html("");
					$('#sss').html("未设置任何供应商");
				}else{
					ss="<ul>"+ss+"</ul>";
					$('#sss').html("");
					$('#sss').html(ss);
				}
			 
			}
		});
		 
	}
	
	function _search() {
		//去空 处理 
	
 		datagrid.datagrid('load',$("#searchForm").serializeObject());
		$("#datagrid").datagrid("clearSelections");
	}
	function cleanSearch() {
		searchForm.form("clear");
		datagrid.datagrid('load', {});
	}
	/*
	*新增项目信息 
	*/
	function add() {
		loginInfoAdd.form("clear");
		$('div.validatebox-tip').remove();
		loginInfoAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = "";
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {	
					for ( var i = 0; i < rows.length; i++) {
						if(i!=rows.length-1)
							ids=ids+"ids="+rows[i].id+"&";
						else ids=ids+"ids="+rows[i].id;
					}		
					$.ajax({
						url : 'delete',
						data : ids,
						dataType : 'json',
						success : function(response) {
							datagrid.datagrid('load');
							datagrid.datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : '删除成功！'
							});
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要删除的记录！', 'error');
		}
	}
	
	
	function edit() {
		
		//修改关联资源 
			
		var rows = datagrid.datagrid('getSelections');
		
		if (rows.length == 1) {
			
			if (rows[0].ifSelect == 1) {
				$.messager.alert('提示', '请选择未抽取的记录！', 'error');
				return;
			}
			
			$.messager.progress({
				text : '数据加载中....',
				interval : 100
			});
			//获取 勾选 的供应商 信息  
			$("#resourceIds2").combotree({   
				url: '/demo/providerControl/getproject', 
				valueField:'project_name',
			    animate:true,
				checkbox:true,
				multiple:true,
				cascadeCheck:false, 
				onLoadSuccess : initTree
			});

			$.ajax({
				url : 'showInfo/'+rows[0].id,
				data : {},
				dataType : 'json',
				cache : false,
				success : function(response) {
					updateForm.form('load', response.result);
					$('div.validatebox-tip').remove();
					
					updateDialog.dialog('open');
					
					datagrid.datagrid('clearSelections');
					$.messager.progress('close');
				}
			});
		} else {
			$.messager.alert('提示', '请选择一项要编辑的记录！', 'error');
		}
	}
	
	function initTree(){
		var array = new Array();
		var row = $("#datagrid").datagrid("getSelected");
	
		 //获取用户当前资源
		$.ajax({
			url: '/demo/projectControl/projectById?id='+row.id,   
			type:"POST",
 			success:function(data1){ 
 				 
 			 	data1 = data1.replace(/\"/g, "");
 				var str = data1.split(",");
 				 
 				for(var i = 0 ; i < str.length; i++){
 					array.push(str[i]);	 
 				} 
 				$('#resourceIds2').combotree('setValues', array);
			}
		});
			 
		 
		
		$(".tree span[class*='tree-checkbox']").bind(
				'click',function(){
					var resourceTree = $("#resourceIds2").combotree('tree');
					var nodeId = $(this).parent().attr("id");
					var node = resourceTree.tree("find",nodeId);
					//选中、取消 父节点及所有自己子节点(顺序不可颠倒)
					iteratorChildrenNodes(resourceTree,node,!node.checked);
					iteratorParentNode(resourceTree,node,!node.checked);
				}
		);
	}
</script>
</head>
</head>
<body>	
 
<div class="easyui-layout"   data-options="fit:true" > 
 <div region="north" border="false" collapsible="true" collapsed="false"   title="查询条件" style="height:90px; overflow: auto;">
	<form id="searchForm">
	<table>
	<tr>
		<td>名称: </td>
		<td><input id="provider_name" name="provider_name"  style="width:145px;border:1px solid #D6D3D6;"  ></td>
	 
		<td>创建人: </td>
		<td><input id="createBy" name="createBy"  style="width:145px;border:1px solid #D6D3D6;"  ></td>
	 
 
	<td>创建时间从:</td>
	<td><input id="startTimeQuery" name="startTimeQuery"  class="easyui-datebox" style="width:150px;border:1px solid #D6D3D6;"></td>
	<td>到:</td>
	<td><input id="endTimeQuery" name="endTimeQuery" class="easyui-datebox" style="width:150px;border:1px solid #D6D3D6;"></td>
	 
	 <tr>
	 <td colspan="6">			
			<a href="#" class="easyui-linkbutton"  iconCls="icon-search"  onclick="_search()">搜索</a>	
			<a href="#" class="easyui-linkbutton"  iconCls="icon-edit"  onclick="edit()">修改</a>		
	</td>
	</tr>
	</table>
<!-- 	</div> -->
	</form>
	</div>
	
	<div  region="center" border="false">
		<table data-options="singleSelect:true,collapsible:true"  id="datagrid"></table>
	</div>

	<div  id="add_div" icon="icon-save"  style="padding: 5px; width: 700px; height: 300px;  display: none" >  
    <form id="loginInfoAdd" action="add" method="post">
    <div   id="table_loginInfoEdit"   onkeydown="if(event.keyCode==13){loginInfoEdit();}">  
        <input type="hidden" id="loginInfoEdit_loginId"></input>  
        <ul> 
        	<li>  
            	<div  >
            		<div  >
            			<div >供应商名称： </div>
                		<div  ><input type="text" class="easyui-validatebox" id="provider_nameadd" name="provider_name" maxlength="20" required="true"></input>  </div>
              		</div>
              </div>  
            </li>  
        </ul>  
    </div>  
    </form>
</div>
               
	
	<div  id="update_div" icon="icon-save"  style="padding: 5px; width: 700px; height: 300px;  display: none" >  
    <form id="update_form" action="update" method="post">
    <div class="ToolTip_Form" id="table_loginInfoEdit" onkeydown="if(event.keyCode==13){loginInfoEdit();}">  
        <input  type="hidden"  name="id"></input>
        <ul>  
            <li>  
            	<div class=oneline>
            		<div class="item100">
            			<div class="itemleft50"> 项目名称： </div>
                		<div class="righttext" ><input type="text" class="easyui-validatebox" id="project_name" name="project_name"  readonly maxlength="20" required="true"></input>  </div>
              		</div>
              </div>  
            </li> 
 			<li>  
            	<div class=oneline>
            		<div class="item100">
            			<div class="itemleft50"> 项目供应商： </div>
                		<div class="righttext" >
						<select  name="resourceIds2" id="resourceIds2"></select>	         
						<input type="hidden" id="resourceIdup"  name="providerids"/>       		
                		  </div>
              		</div>
              </div>  </li></ul></div></form></div>
             
             
             	<div  id="detail_div" icon="icon-save"  style="padding: 5px; width: 700px; height: 300px;  display: none" >  
     <div class="ToolTip_Form" id="table_loginInfoEdit" onkeydown="if(event.keyCode==13){loginInfoEdit();}">  
        <input  type="hidden"  name="id"></input>
        <ul>  
            <li>  
            	<div class=oneline>
            		<div class="item100">
            			<div class="itemleft50"> 项目名称： </div>
                		<div class="righttext" ><input type="text" class="easyui-validatebox" id="detail_project_name"    readonly maxlength="20"  ></input>  </div>
              		</div>
              </div>  
            </li> 
 			<li>  
            	<div class=oneline>
            		<div class="item100">
            			<div class="itemleft50"> 项目供应商： </div>
                		<div id="sss"  class="righttext" >
					 	    		
                		</div>
              		</div>
              </div>  </li></ul></div> </div> 
              
              </div>
              
</body>
 </html>