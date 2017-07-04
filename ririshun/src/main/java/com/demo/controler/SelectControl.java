package com.demo.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.Project;
import com.demo.entity.Provider;
import com.demo.service.ProjectService;
import com.demo.service.ProviderService;
import com.demo.util.GlobalData;
import com.demo.util.JsonResult;
import com.demo.util.ResponseDate;
import com.demo.view.ProjectDetail;

@Controller
@RequestMapping("/selectControl")
public class SelectControl {
	@Autowired
	ProjectService  projectService;
	
	@Autowired
	ProviderService  providerService;
	
	
	
	@RequestMapping("/select")
	public String commission( HttpServletRequest request){
		return "/select/select";
	}
	  
	@RequestMapping("/showInfos")
	public @ResponseBody Map<String, Object> showProInfos(HttpServletRequest request,Project  project){
		
		if(null==project.getStartTimeQueryS()&&null==project.getEndTimeQueryS()){
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("rows", null);
			m.put("total", 0);
			return m;
		}
		
		if("".equals(project.getStartTimeQueryS())&&"".equals(project.getEndTimeQueryS())){
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("rows", null);
			m.put("total", 0);
			return m;
		}
		int page = 1;
		int rows = 0;
		try {
			page = ServletRequestUtils.getIntParameter(request, "page");
			rows = ServletRequestUtils.getIntParameter(request, "rows");
		} catch (ServletRequestBindingException e) {
			e.printStackTrace();
		}		
		project.setIfSelect("0");
		List<Project> shopManages = projectService.findPage(page,rows,project);
		Long count = projectService.getCount(project);
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("rows", shopManages);
		m.put("total", count);
		return m;
	}
	
	@RequestMapping("/selecton")
	public void selectready(  HttpServletResponse response,HttpServletRequest req) throws IOException{
		List<String>  provide =new ArrayList<String>();
		String id=req.getParameter("id");
		String ids=req.getParameter("ids");//现在选择的 
		List<String> list=providerService.findAllID(Long.parseLong(id));//已经 选择的
		String[] list2=ids.split(",");
		
		if(null==list||list.size()==0){
			 
			int index=(int)(Math.random()*list2.length);
			provide.add(index+"");
		}else{	
			 
			for(String a:list){
				for(int i=0;i<list2.length;i++){
					if(a.equals(list2[i])){
						provide.add(a);
					}
				}
			}
			if(provide.size()==0){
				int index=(int)(Math.random()*list2.length);
				provide.add(index+"");
			} 
		}
		
		String result="";
		String projectids="";
		//获取抽取的供应商信息 
 		for(int i=0;i<provide.size();i++){
			
 			Provider provide2=	providerService.load(Long.parseLong(provide.get(i)));
			if(provide.size()==1||provide.size()==(i+1)){
			
			result=result+provide2.getProvider_name();
			}else{
				projectids=projectids+provide.get(i)+",";
			result=result+provide2.getProvider_name()+",";
			}
		}
		//更新抽取状态
		Project  project = projectService.load(Long.parseLong(id));//获取 项目基本信息
		project.setIfSelect("1");
		project.setSelectDate(new Date());
		project.setProjectids(projectids);
		
		projectService.update(project);
		ResponseDate.writeJson(response,result);
	}
	
	@RequestMapping("/update")
	public void update(HttpServletResponse response,HttpServletRequest req) throws IOException{
		JsonResult jr = new JsonResult();
		String uid=req.getParameter("id");
		String providerids=req.getParameter("providerids");
		Project project=projectService.load(Long.parseLong(uid));
		if(project!=null){
			HttpSession session = req.getSession(true);
			String modifyBy=session.getAttribute(GlobalData.SESSION_USERID).toString();
 			project.setModifyBy(modifyBy);
 			project.setProjectids(providerids);
			project.setModifyDate(new Date());
			projectService.update(project);
			jr.setSuccess(true);
			jr.setIfsuccess(true); 
		}else{
			jr.setSuccess(false);
			jr.setIfsuccess(false);
		}
		ResponseDate.writeJson(response, jr);
	}
	

	@RequestMapping("/showInfo/{id}")
	public void showUserInfo(HttpServletResponse response,
			  @PathVariable Long id){
		JsonResult jr = new JsonResult();
		
		 
		Project  project = projectService.load(id);
		if (null != project){
			jr.setSuccess(true);
			jr.setResult(project);
		} else {
			jr.setSuccess(false);
		}

		try {
			ResponseDate.writeJson(response, jr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
