package com.demo.controler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.Provider;
import com.demo.service.ProviderService;
import com.demo.util.GlobalData;
import com.demo.util.JsonResult;
import com.demo.util.ResponseDate;
import com.demo.view.CommTree;

@Controller
@RequestMapping("/providerControl")
public class ProviderControl {
	 
	@Autowired
	ProviderService providerService;
	
	@RequestMapping("/provider")
	public String commission( HttpServletRequest request){
	 
		return "/provider/provider_1";
	}
	
	
//	@RequestMapping("/validateName")
//	public  void  validateName(HttpServletRequest request,HttpServletResponse response) throws IOException{
//			JsonResult js=new JsonResult();
//		 	String name=request.getParameter("provider_name");
//		 	Boolean flag=providerService.validateName(name); 
//		 	js.setIfsuccess(flag);
//		 	ResponseDate.writeJson(response,js);
//	}

	@RequestMapping("/add")
	public  void  add(HttpServletRequest request,HttpServletResponse response,Provider provider) throws IOException{
			JsonResult js=new JsonResult();
			
			HttpSession session = request.getSession(true);
			String createby =session.getAttribute(GlobalData.SESSION_USERID).toString();
			
			
			 
		 	Boolean flag=providerService.validateName(provider.getProvider_name()); 
			if(flag){
			provider.setCreateBy(createby);
			provider.setCreateDate(new Date());
 		 	providerService.add(provider);
			}else{
				Provider aa=new Provider();
				aa.setProvider_name(provider.getProvider_name());
				Provider vv=providerService.load(aa);
				vv.setModifyBy(createby);
				vv.setModifyDate(new Date());
	 		 	providerService.update(vv);
			}
		 	js.setIfsuccess(true);
		 	js.setSuccess(true);
		 	ResponseDate.writeJson(response,js);
	}
	
	
	@RequestMapping("/showInfos")
	public @ResponseBody Map<String, Object> showProInfos(HttpServletRequest request,Provider provider){
		int page = 1;
		int rows = 0;
		try {
			page = ServletRequestUtils.getIntParameter(request, "page");
			rows = ServletRequestUtils.getIntParameter(request, "rows");
		} catch (ServletRequestBindingException e) {
			e.printStackTrace();
		}		
		List<Provider> shopManages = providerService.findPage(page,rows,provider);
		Long count = providerService.getCount(provider);
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("rows", shopManages);
		m.put("total", count);
		return m;
	}
	
	
	
	@RequestMapping("/getproject")
	public  void  getproject(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Calendar cal = Calendar.getInstance();
		Date date1=cal.getTime();
		cal.add(Calendar.DATE, -3);
		Date date2=cal.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Provider provide= new Provider();
		provide.setStartTimeQuerym(df.format(date2));
		provide.setEndTimeQuerym(df.format(date1));
    		 List<Provider> list=providerService.findAll(provide);
    		 
    		List<CommTree>reList= new ArrayList<CommTree>();
    		CommTree a;
    		for(Provider provider:list){
    			a=new CommTree(); 
    			a.setId(provider.getId());
    			a.setText(provider.getProvider_name());
    			reList.add(a);
    		}
		 	ResponseDate.writeJson(response,reList);
	}
	
}
