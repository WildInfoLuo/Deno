package com.demo.controler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.util.GlobalData;


@Controller
@RequestMapping("/userControl")
public class UserControl {
	
	@Autowired
	private UserService  userService;
	
	@RequestMapping("/userLogin")
	public String goLogin(HttpServletRequest request){
		return "/login"; 
	}
	
	@RequestMapping("/exit")
	public String logOut(HttpServletRequest request){
		 //清空session
		HttpSession session = request.getSession(true);
		session.removeAttribute(GlobalData.SESSION_USERID);
		return "redirect:/userControl/userLogin";
	}
	
	/*
	 * 用户名 密码登陆
	 */
	@RequestMapping(value="/login" ,method =RequestMethod.POST)
	public String login(HttpServletRequest request){
		String userId =  request.getParameter("userId");
		String password = request.getParameter("password");
		boolean flag=userService.login(userId, password);
		
		HttpSession session = request.getSession(true);
		session.setAttribute(GlobalData.SESSION_USERID, userId);
		if(flag){
		return "redirect:/userControl/main";
		}
		return "/login";
	}
	
	/*
	 *  
	 */
	@RequestMapping(value="/goregisiter" ,method =RequestMethod.GET)
	public String goregisiter(HttpServletRequest request){
		return "/regisiter";
	}
	
	@RequestMapping(value="/userregisiter" ,method =RequestMethod.POST)
	public String regisiter(HttpServletRequest request){
		
		String userId =  request.getParameter("userId");
		String password = request.getParameter("password");
		User user=new User(); 
		user.setPassWord(password);
		user.setUserId(userId);
		user.setCreateDate(new Date());
		boolean flag=userService.save(user);
		
		HttpSession session = request.getSession(true);
		session.setAttribute(GlobalData.SESSION_USERID, userId);
		if(flag){
		return "redirect:/userControl/main";
		}
		return "/regisiter";
	}
	//跳转首页
	@RequestMapping("/main")
	public String goMain(HttpServletRequest request){
		return "/user/main"; 
	}
}
