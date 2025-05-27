package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class ConvertJSONToMap {
    public static List<HashMap<String, Object>> getJSONDataToMap(String filePath) {

        //reading JSON to String
        String jsonContent;
        try {
            jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //converting String to HashMap

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> data;
        try {
            data = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return data;

    }
}
