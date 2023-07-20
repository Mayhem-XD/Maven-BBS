package utility;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.cj.xdevapi.JsonParser;

public class JsonUitl {
	public String ListToJson (List<String> list) {
		JSONObject jobj = new JSONObject();
		jobj.put("list", list);
		
	
		return jobj.toString();
	}
	public List<String> jsonToList(String jStr){
		JSONParser parser = new JSONParser();
		List<String> list = null;
		try {
			JSONObject jsonList = (JSONObject) parser.parse(jStr);
			JSONArray jsonArr = (JSONArray) jsonList.get("list");
			list = (List<String>) jsonArr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
