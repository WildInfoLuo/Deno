package com.demo.controler;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.demo.util.GlobalData;
public class SecurityListener   implements ServletContextListener{
 	/**
	 * 项目启动监听器 项目启动后执行
	 * @author  demo
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext context = arg0.getServletContext();
		context.setAttribute("staticURL", GlobalData.staticURl);
	}
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}


	 
	 
}
