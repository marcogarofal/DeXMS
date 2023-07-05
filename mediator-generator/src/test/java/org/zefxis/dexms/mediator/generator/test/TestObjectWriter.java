package org.zefxis.dexms.mediator.generator.test;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
public class TestObjectWriter {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, ParseException, JSONException {
		 Map<String,Object> person = new HashMap<String,Object>();
		 Map<String,String> address = new HashMap<String,String>();
		 address.put("Vill.", "Dhananjaypur");
		 address.put("Dist.", "Varanasi");
		 address.put("State", "UP");
		 person.put("id", "1");
		 person.put("name", "Arvind");
		 person.put("address", address);
		 ObjectMapper mapper = new ObjectMapper();
		 ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		 
		String jsonstring =  writer.writeValueAsString(person);
		
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		jsonObject = (JSONObject) parser.parse(jsonstring);
		
		JSONObject jsonaddress =  (JSONObject) jsonObject.get("address");
		
		Map<String, String> map = new HashMap<>(jsonaddress.size());
        for (Object jsonEntry : jsonaddress.entrySet()) {
            Map.Entry<?, ?> entry = ((Map.Entry<?, ?>) jsonEntry);
            map.put(entry.getKey().toString(), entry.getValue().toString());
            
            System.out.println(entry.getKey().toString()+":"+entry.getValue().toString());
        }
		 
		 System.out.println("--jsonstring--: "+jsonstring);
	}
} 