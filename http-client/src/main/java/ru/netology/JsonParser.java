package ru.netology;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonParser {

    @JsonFormat(shape= JsonFormat.Shape.ARRAY)
    public Cat[] parseJson(String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Cat[] cat = objectMapper.readValue(json, Cat[].class);

        return cat;
    }
}
