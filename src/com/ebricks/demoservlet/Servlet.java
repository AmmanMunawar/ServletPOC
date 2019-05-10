package com.ebricks.demoservlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class Servlet extends HttpServlet{
	
	private ReadjsonFile readjsonfile = new ReadjsonFile();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		
		try {
			
			JSONArray shapes = readjsonfile.getShapesfromjsonfile();
			response.setContentType("applications/json");
			PrintWriter out = response.getWriter();
			out.println(shapes);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("application/json");
		StringBuffer stringbuffer = new StringBuffer();
		String line = "";
		BufferedReader reader = request.getReader();
		while((line=reader.readLine())!=null) {
			stringbuffer.append(line);
		}
		JSONObject jsonobject;
		try {
			
			jsonobject = new JSONObject(stringbuffer.toString());
			JSONArray shapes = (JSONArray) jsonobject.get("shapes");	
			readjsonfile.inputShapesInJsonFile(shapes);
			PrintWriter out = response.getWriter();
			out.println(jsonobject.get("shapes"));
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
