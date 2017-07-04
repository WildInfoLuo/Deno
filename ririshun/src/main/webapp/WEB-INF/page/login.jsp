<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${staticURL}/css/loginStyle.css">
<head>
		<script type="text/javascript" src="${staticURL}/js/jquery-1.7.2/jquery.min.js"></script>
		<script type="text/javascript" charset="utf-8">
			function login(){
				 
				$("#loginForm").submit();
			}
			function gozhuce(){
				window.location.href='/demo/userControl/goregisiter';
			}
		 
		</script>
 </head>
</head>
<body>	


<div class="wrap">
	<div class="login_bj"></div>
    <div class="login">
    	
    	<form  action="/demo/userControl/login" id="loginForm" method="post">
    	<div class="zhanghao">
        	<input type="text" name="userId"  id="userId"class="zh" placeholder="登陆账号">
        </div>
        <div class="mima">
        	<input type="password" id="password"  name="password" class="mm" placeholder="用户密码">
        </div>
        <div class="jizhuzhanghu">
        	<div class="jz"></div>
            <span class="jzzh">记住账户</span>
            <span class="wangjimima">忘记密码</span>
        </div>
         </form>
        <a  onclick="login();"   class="denglu">登 录</a>
        <a  onclick="gozhuce();"  class="zhuce">注 册</a>
       
    </div>
</div>
</body>
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