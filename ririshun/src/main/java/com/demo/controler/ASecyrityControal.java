package com.demo.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.util.GlobalData;

 
public class ASecyrityControal  implements HandlerInterceptor  { 
		
 		
		public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
				throws Exception {
			// TODO Auto-generated method stub	
		}
		public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
				throws Exception {
			// TODO Auto-generated method stub
		}
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
			HttpSession session = request.getSession(true);
 			// 从session 里面获取用户的信息
			Object obj = session.getAttribute(GlobalData.SESSION_USERID);
			// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
			if (obj == null || "".equals(obj.toString())) {
				response.sendRedirect(GlobalData.loginUrl);
 				return false;
			}
			return true;
		}
		}
		
 
