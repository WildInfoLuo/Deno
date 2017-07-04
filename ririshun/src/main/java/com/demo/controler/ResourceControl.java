package com.demo.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.Resource;
import com.demo.service.ResourceService;
import com.demo.util.ResponseDate;

@Controller
@RequestMapping("/menu")
public class ResourceControl {
	@Autowired
	private ResourceService  resourceService;
	@RequestMapping("/menuInit")
 	public    void  menuInit(HttpServletRequest req,HttpServletResponse response) throws IOException{
		//获取用户id  根据用户id 获取用户当前菜单资源
		List<Resource> list= resourceService.getResourceAll();
		
	 
		for(int i=0;i<list.size();i++){
			list.get(i).setIcon(null);
			list.get(i).setIsParent("0");
		}	
		
		
		ResponseDate.writeJson(response,list);
	}
}
