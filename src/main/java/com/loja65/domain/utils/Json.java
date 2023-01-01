package com.loja65.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class Json {

    private ObjectMapper mapper = new ObjectMapper();

    public String toJson(Object object)  {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public HashMap<String, Object> toMap(Object object){
        return mapper.convertValue(object, new TypeReference<HashMap<String, Object>>() {});
    }

}
