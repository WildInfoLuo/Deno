<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${staticURL}/js/jquery-1.7.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${staticURL}/js/easyui1.4/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/js/easyui1.4/themes/icon.css">

<link rel="stylesheet" type="text/css" href="${staticURL}/css/main.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/css/zTreeStyle.css">

 <script type="text/javascript" src="${staticURL}/js/easyui1.4/jquery.easyui.min.js"></script> 
 <script type="text/javascript" src="${staticURL}/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
		var user_name_ = ${userId};
		$(function(){
			 //左侧菜单树
			 var setting = {		
				 data : {
						simpleData : {
							enable : true,
							icon:"",
							idKey : "id",
							pIdKey : "parentId",
							rootPId : "1"
						}
				 },
				 view : {
					showLine : true
 					 
				 },
				 //回调函数 点击左侧菜单 跳转 
				 callback : {
					 onClick : function(event,treeId,treeNode,clickFlag){
					 
						 if(treeNode.urlLocation!=null && ""!=treeNode.urlLocation){
							 addTab(treeNode.name,"/demo"+treeNode.urlLocation,treeNode.resourceType);
						 }
					 }
				 }
			 };
			 $.post("/demo/menu/menuInit",{parentId:'1'},function(data){
				 $.fn.zTree.init($("#dhul"),setting,data);
				 var treeObj = $.fn.zTree.getZTreeObj("dhul");
				 treeObj.expandAll(true);
			 },"json");
			 
			var	content = '<iframe scrolling="auto" frameborder="0"  src="" style="width:100%;height:100%;z-index:-1"></iframe>';
			$('#tabs').tabs('add',{
				title:'消息中心',
				content:content,
				style : {
					padding : 1
				},
				closable:false,
			}
			);
			$("#dlgMain").removeClass("disareas");
			$('#dlgMain-buttons').removeClass("disareas");
			//修改密码 对话框
			$("#dlgMain").dialog({   
			    title: '重置密码',   
			    width: 390,   
			    height: 280,  
			    closed: true,   
			    cache: false,   
			    buttons: '#dlgMain-buttons',   
			    modal: true  
			});
			
	 
			
			//右键菜单
			$('#mm').removeClass("disareas");
			$('#mm').menu({});
			 
			//菜单双击及右键事件绑定
			$(".tabs-inner").live({
				dblclick : function(){ //tabs 绑定双击关闭事件
					var subtitle = $(this).children(".tabs-closable").text();
					if(subtitle == ""){
						return;
					}
					$('#tabs').tabs('close',subtitle);
				},
				contextmenu : function(e){ //tabs 绑定右键弹出菜单
					//弹出自己的菜单
					$('#mm').menu('show', { 
						left: e.pageX,
						top: e.pageY
					});
					//获取可以关闭的tabs的标题
					var subtitle =$(this).children(".tabs-title").text();
					 
					$('#mm').data("currtab",subtitle);
				 
					return false;//阻止默认菜单的弹出
				},
				click : function(){
					//获取标题
					var subtitle =$(this).children(".tabs-title").text();
					$(".ztree a").removeClass("curSelectedNode");
					$(".ztree a[title='"+subtitle+"']").addClass("curSelectedNode");
					if("消息中心" != subtitle){
						var treeObj = $.fn.zTree.getZTreeObj("dhul");
						var node = treeObj.getSelectedNodes();
						if(node.length>0){
							treeObj.expandNode(node[0].getParentNode(),true,false,false);
						}
					}
				}
			});
		 
			//给菜单的按钮 点击增加事件
			addMenuEvent();
			
			//导航背景色
			$("#tit a>div").hover(
				function(){
					$(this).addClass("hoverbgcolor");
				},
				function(){
					$(this).removeClass("hoverbgcolor");
				}
			)
		});
		//给菜单的按钮 点击增加事件
		function addMenuEvent(){
			//刷新按钮
			$("#mm-tabupdate").click(function(){
				var currTab = $('#tabs').tabs('getSelected');
				var url = $(currTab.panel('options').content).attr('src');
				if(url != undefined) {
					$('#tabs').tabs('update',{
						tab:currTab,
						options:{
							content:createFrame(url)
						}
					})
				}
			});
			//关闭按钮
			$("#mm-tabclose").click(function(){
				if($('#mm').data("currtab")!="消息中心"){
					$('#tabs').tabs('close',$('#mm').data("currtab"));
				}
			});
			//关闭其他
			$("#mm-tabcloseother").click(function(){
				$(".tabs-closable").each(function(i,n){
					var title = $(n).text();
					if(title != $('#mm').data("currtab")){
						$('#tabs').tabs('close',title);
					}
				});
			});
			//全部关闭
			$("#mm-tabcloseall").click(function(){
				$(".tabs-closable").each(function(i,n){
					var title = $(n).text();
					$('#tabs').tabs('close',title);
				});
			});
			
		}
 		function createFrame(url,type) { 
 
				var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				return s;
		}
		
		//点击菜单增加tab或选中对应的tab
		function addTab(title,url,type){
	
				//存在则 选中
				if($("#tabs").tabs("exists",title)){
					$('#tabs').tabs('select', title);//选中并刷新
					var currTab = $('#tabs').tabs('getSelected');
 				 
					if(url != undefined && currTab.panel('options').title != '消息中心') {
						$('#tabs').tabs('update',{
							tab:currTab,
							options:{
								content:createFrame(url,type)
							}
						})
					}
				}else{ //不存在则增加
				 
					var content = createFrame(url,type);
					$('#tabs').tabs('add',{
						title:title,
						content:content,
						style : {
							padding : 5
						},
						closable:true
					});
				}
			}
 
	</script>
</head>
<body class="easyui-layout"  id="aaa">
	<div   class="northDiv"   data-options="region:'north'"  >
		<div class="topDiv" >
			<div  style="margin-top:35px;margin-left:80%">
					<div class="imgDiv_p" >
					<div >
							<span style="font-size:18px">${userId},您好！</span>	
					</div>
					<a href="/demo/userControl/exit">
 						 <span>注销</span> 
					</a>
				</div>
			</div>	
		</div>
	</div>
	<div class="westDiv" data-options="region:'west',border:true,split:true,title:'导航菜单'">
		<ul id="dhul" class="ztree"></ul>
	</div>
	<div id="mainPanle" data-options="region:'center',border:true">
		<div id="tabs"   class="easyui-tabs"   data-options="fit:true,border:false">
        </div>
	</div>
	<!-- 右键菜单 -->
	<div id="mm" class="easyui-menu cs-tab-menu disareas" >
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
</body>
</html>