package com.example.quest.mapper;

import com.example.quest.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;





class QuestionMapperTest {

    @Test
    void testJsonToQuestionMap() throws IOException {
        // Создаем экземпляр маппера
        QuestionMapper questionMapper = new QuestionMapper();

        // JSON строка с вашими вопросами


        // Считываем вопросы из строки JSON
        List<Question> questions = questionMapper.JsonToQuestionMap("test.json");

        // Проверяем, что список вопросов не пустой и содержит ожидаемые значения
        Assertions.assertNotNull(questions);
        Assertions.assertFalse(questions.isEmpty());

        // Проверяем содержание первого вопроса
        Question question = questions.get(0);
        Assertions.assertEquals("LOST_MEMORY", question.getCurrentLevel().toString());
        Assertions.assertEquals("Ты потерял память", question.getQuestionText());

        // Проверяем количество ответов
        Assertions.assertEquals(3, question.getAnswers().size());

        // Проверяем содержание первого ответа
        Assertions.assertEquals("Принять вызов", question.getAnswers().get(0).getAnswerText());
        Assertions.assertEquals("WANT_UP_BRIDGE", question.getAnswers().get(0).getNextLevel().toString());
    }
}

