package com.example.quest.mapper;

import com.example.quest.model.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class QuestionMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Question> JsonToQuestionMap(String jsonPath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonPath);
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class);
        return objectMapper.readValue(inputStream, listType);

    }

}




