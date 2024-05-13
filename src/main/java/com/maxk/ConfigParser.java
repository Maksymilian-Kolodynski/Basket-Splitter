package com.maxk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ConfigParser {

    public static Map<String, List<String>> parseConfigFile(String filePath) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> productDeliveryOptions = objectMapper.readValue(new File(filePath), Map.class);
        return productDeliveryOptions;
    }
}
