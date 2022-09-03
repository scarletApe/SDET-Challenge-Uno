package mx.com.microsoft.Util;

import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

public class DataUtil {

	@SuppressWarnings("unchecked")
	@DataProvider
	public Object[] jsonDataProvider() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;

		try {
			Object obj = parser.parse(new FileReader("src/main/resources/testData.json"));
			jsonObj = (JSONObject) obj;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		Object[] data = new Object[1];
		HashMap<String, String> map = new LinkedHashMap<String, String>();
		if (jsonObj != null) {
			Set<String> jsonObjKeys = jsonObj.keySet();
			for (String key : jsonObjKeys) {
				map.put(key, (String) jsonObj.get(key));
			}
		} else {
			System.err.println("Error reading json data");
		}

		data[0] = map;
		return data;
	}
}
