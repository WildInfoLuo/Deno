package com.demo.controler;

import java.io.IOException;
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
import com.demo.service.ProjectService;
import com.demo.service.ProviderService;
import com.demo.util.GlobalData;
import com.demo.util.JsonResult;
import com.demo.util.ResponseDate;
import com.demo.view.ProjectDetail;


@Controller
@RequestMapping("/projectControl")
public class ProjectControl {
	
	@Autowired
	ProjectService  projectService;
	
	@Autowired
	ProviderService  providerService;
	
	
	@RequestMapping("/project")
	public String commission( HttpServletRequest request){
	 
		return "/project/project";
	}
	
	@RequestMapping("/showInfos")
	public @ResponseBody Map<String, Object> showProInfos(HttpServletRequest request,Project  project){
		int page = 1;
		int rows = 0;
		try {
			page = ServletRequestUtils.getIntParameter(request, "page");
			rows = ServletRequestUtils.getIntParameter(request, "rows");
		} catch (ServletRequestBindingException e) {
			e.printStackTrace();
		}	
		List<Project> shopManages = projectService.findPage(page,rows,project);
		Long count = projectService.getCount(project);
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("rows", shopManages);
		m.put("total", count);
		return m;
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
	
	
	@RequestMapping("/projectById")
	public void projectById(HttpServletResponse response,HttpServletRequest req) throws IOException{
	 
		String uid=req.getParameter("id");
 		List<String> list =  projectService.getProjectId(Long.parseLong(uid));
		String ids="";
		for(String aa:list){
			ids=ids+aa+",";
		}
	 
		ResponseDate.writeJson(response, ids);
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
	
	//获取项目 详情 
	@RequestMapping("/detail")
	public void detail(HttpServletResponse response,HttpServletRequest req) throws IOException{
	 
		String id=req.getParameter("id");
		Project  project = projectService.load(Long.parseLong(id));//获取 项目基本信息
		List<String> list=providerService.findAll(Long.parseLong(id));
		//获取修改的供应商信息 
		ProjectDetail aa=new ProjectDetail();
		aa.setList(list);
		aa.setProject(project);
		ResponseDate.writeJson(response,aa);
	}
	
	
}
