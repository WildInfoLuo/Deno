<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<link rel="stylesheet" type="text/css" href="${staticURL}/css/registerStyle.css"">
<head>
		<script type="text/javascript" src="${staticURL}/js/jquery-1.7.2/jquery.min.js"></script>
		<script type="text/javascript" charset="utf-8">
			function regesiter(){
				var useid=$.trim($('#userId').val());
				var spassword=$('#spassword').val();
				var password=$('#password').val();
				alert(useid);
				if(useid==''){
					alert("1");
					//return;
				}
				if(spassword!=password){
					$.messager.alert('提示:','两次输入的密码不一致','warning'); 
				 	return; 
				}  if(useid.length>100){
					$.messager.alert('提示:','姓名过长','warning'); 
				 	return; 
				} 
					$("#regesiterForm").submit();
				 
				
			}
		 
		</script>
 </head>
</head>
<body>	

<div class="wrap">
	<div class="login_bj"></div>
    <div class="login">
    	<h2>新 用 户 注 册</h2>
     	<form  action="/demo/userControl/userregisiter"   id="regesiterForm" method="post">
 
    	<table width="100%" border="0">
          <tr>
            <td>姓名：</td>
            <td><input type="text" name="userId" class="xm"></td>
          </tr>
          <tr>
            <td>手机号：</td>
            <td><input type="text" name="phone" class="sjh"></td>
          </tr>
          
          <tr>
            <td>密码：</td>
            <td><input type="password" name="spassword" class="mm"></td>
          </tr>
          <tr>
            <td>确认密码：</td>
            <td><input type="password" name="password" class="qrmm"></td>
          </tr>
        </table>    
        </form>    
        <button onclick="regesiter();" class="zhuce">注 册</button>
        
    </div>
</div>
 
</body>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.jz').click(function(){
		if($('.jz').text() == "√"){
			$('.jz').text("");
		}else{
			$('.jz').text("√");
		};
	});
	$('.jzzh').click(function(){
		if($('.jz').text() == "√"){
			$('.jz').text("");
		}else{
			$('.jz').text("√");
		};
	});
})
</script>

 
  
</body>
</html>