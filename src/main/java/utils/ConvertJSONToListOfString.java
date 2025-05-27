package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConvertJSONToListOfString {
    public static List<String> getJSONDataToListOfString(String filePath) {
        String jsonContent;
        try {
            jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> rawList;
        try {
            rawList = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<String> productNames = new ArrayList<>();
        for (HashMap<String, Object> map : rawList) {
            productNames.add(map.get("product").toString());
        }

        return productNames;
    }
}
