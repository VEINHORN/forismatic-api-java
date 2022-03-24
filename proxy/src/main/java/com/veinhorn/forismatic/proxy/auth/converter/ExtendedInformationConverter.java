package com.veinhorn.forismatic.proxy.auth.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veinhorn.forismatic.proxy.exception.NotWritableExtendedInformationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ExtendedInformationConverter {
    private final Map<String, String> extendedInformation;

    public ExtendedInformationConverter(String extendedInfo) {
        try {
            extendedInformation = new ObjectMapper().readValue(extendedInfo, new TypeReference<Map<String, String>>() {});
        } catch (JsonProcessingException e) {
            throw new NotWritableExtendedInformationException("Cannot convert extended information to map");
        }
    }

    public String convert() {
        try {
            return new ObjectMapper().writeValueAsString(extendedInformation);
        } catch (JsonProcessingException e) {
            throw new NotWritableExtendedInformationException("Cannot convert extended information to string");
        }
    }
}
