package com.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class TooL {
	public static SimpleDateFormat df_date = new SimpleDateFormat("yyyy-MM-dd");

	public static void addTimeBetweenStament
	(Criteria cri, String beanPro, String value_min,String value_max) {
	
	if (null != value_min && !value_min.equals("")){
		 
			Date date_min;
			try {
				date_min = df_date.parse(value_min);
				cri.add(Restrictions.ge(beanPro,date_min));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	 
	}
	if (null != value_max && !value_max.equals("")){
		try {
			//获取当前日期 24点
			Date date_max =  df_date.parse(value_max);
			date_max=getTimesnight(date_max);
			cri.add(Restrictions.le(beanPro,date_max));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
 			return;
		}
	}
}
	//获得当天24点时间 
		public static Date getTimesnight(Date date){ 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 24); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return  cal.getTime();
		} 
}
