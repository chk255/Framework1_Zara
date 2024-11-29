package MyProject.SeleniumFramework.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	
	public List<HashMap<String,String>> getDataJSONtoMap() throws IOException {
		
		//read data from json and covert to String
		String JSONContent=    FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//MyProject//SeleniumFramework//Data//getData.json"), 
				StandardCharsets.UTF_8);
		
		//Convert String to HashMap--> jacksonDatabind;
		ObjectMapper mapper=new ObjectMapper();
		
		List<HashMap<String,String>> data=mapper.readValue(JSONContent, new TypeReference<List<HashMap<String,String>>>(){});
		
		return data;
	}

}
