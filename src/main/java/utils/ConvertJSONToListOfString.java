package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConvertJSONToListOfString {
    private static final Logger logger = LogManager.getLogger(ConvertJSONToListOfString.class);

    public static List<String> getJSONDataToListOfString(String filePath) {
        logger.info("Reading JSON file from path: {}", filePath);
        String jsonContent;
        try {
            jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Failed to read file: {}", filePath, e);
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> rawList;
        try {
            rawList = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse JSON content from file: {}", filePath, e);
            throw new RuntimeException(e);
        }

        List<String> productNames = new ArrayList<>();
        for (HashMap<String, Object> map : rawList) {
            productNames.add(map.get("product").toString());
        }
        logger.info("Successfully parsed {} product names from JSON.", productNames.size());
        return productNames;
    }
}
