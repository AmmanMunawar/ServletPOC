package com.ebricks.demoservlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class ReadjsonFile {

	//Fields of Classes
	private JSONObject jsonObject;
	private JSONArray shapes;

	public org.json.JSONArray getShapesfromjsonfile() throws FileNotFoundException, IOException, ParseException {

		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Aman Munawar\\Desktop\\json\\shapesjson.json"));
		StringBuffer stringbuffer = new StringBuffer();
		String line = "";
		while((line=reader.readLine())!=null) {
			stringbuffer.append(line);
		}

		try {

			jsonObject = new JSONObject(stringbuffer.toString());
			shapes = (JSONArray) jsonObject.get("shapesList");

		}catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return shapes;
	} 


	public void inputShapesInJsonFile(JSONArray shapesArray) throws FileNotFoundException, IOException, ParseException {
		this.getShapesfromjsonfile();
		JSONObject jsonObject;// = null;
		for (int i=0;i<shapesArray.length();i++) {

			try {
				jsonObject = shapesArray.getJSONObject(i);
				this.shapes.put(jsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		jsonObject = null;
		jsonObject = new JSONObject();;

		try {
			jsonObject.put("shapesList", this.shapes);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		this.writeinFile(jsonObject);
		System.out.println("SuccessFully Uploaded");
	}
	
	public void writeinFile(JSONObject jsonObject) throws IOException {
		
		FileWriter file = new FileWriter("C:\\Users\\Aman Munawar\\Desktop\\json\\shapesjson.json");
		file.write(jsonObject.toString());
		file.close();
		
	}
	
}
