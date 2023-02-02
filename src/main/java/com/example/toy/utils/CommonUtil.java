package com.example.toy.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CommonUtil {

    public static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static Object makeBindingResultMessage(BindingResult bindingResult) {
        return new HashMap<>(){
            {
                put("error", bindingResult.getAllErrors()
                                          .stream()
                                          .collect(Collectors.groupingBy(error -> ((FieldError) error).getField()))
                                          .entrySet()
                                          .stream()
                                          .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue()
                                                                                                 .stream()
                                                                                                 .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                                                                                 .collect(Collectors.toList()))));
            }
        };
    }
}
