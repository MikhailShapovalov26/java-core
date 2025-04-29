package ru.netology;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseJson {
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    public StructureJsonResponse parseJson(String json) throws JsonProcessingException {

        StructureJsonResponse structureJsonResponse;
        ObjectMapper objectMapper = new ObjectMapper();
        structureJsonResponse = objectMapper.readValue(json, StructureJsonResponse.class);

        return structureJsonResponse;
    }

}
