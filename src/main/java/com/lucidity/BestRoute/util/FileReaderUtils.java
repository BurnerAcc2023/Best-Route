package com.lucidity.BestRoute.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class FileReaderUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> read(String fileName,  TypeReference<List<T>> typeReference) {
        try (InputStream inputStream = FileReaderUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}
