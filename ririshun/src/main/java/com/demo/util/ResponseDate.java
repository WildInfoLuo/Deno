package com.demo.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class ResponseDate {
	public static void writeJson(HttpServletResponse response,Object object) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(JSON.toJSONString(object));
		writer.flush();
		writer.close();
	}
}
