package com.loja65.frentecaixa.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class Json {

    private ObjectMapper mapper;

    public Json(){
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

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
