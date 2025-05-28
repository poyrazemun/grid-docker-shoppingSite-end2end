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
import java.util.HashMap;
import java.util.List;

public class ConvertJSONToMap {
    private static final Logger logger = LogManager.getLogger(ConvertJSONToMap.class);

    public static List<HashMap<String, Object>> getJSONDataToMap(String filePath) {
        logger.info("Reading JSON file from path: {}", filePath);

        //reading JSON to String
        String jsonContent;
        try {
            jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Failed to read file: {}", filePath, e);
            throw new RuntimeException(e);
        }

        //converting String to HashMap

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> data;
        try {
            data = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse JSON content from file: {}", filePath, e);
            throw new RuntimeException(e);
        }

        logger.info("Successfully parsed {} records from JSON.", data.size());
        return data;

    }
}
