package com.example.quest.mapper;

import com.example.quest.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;





class QuestionMapperTest {

    @Test
    void testJsonToQuestionMap() throws IOException {

        QuestionMapper questionMapper = new QuestionMapper();


        List<Question> questions = questionMapper.JsonToQuestionMap("test.json");


        Assertions.assertNotNull(questions);
        Assertions.assertFalse(questions.isEmpty());


        Question question = questions.get(0);
        Assertions.assertEquals("LOST_MEMORY", question.getCurrentLevel().toString());
        Assertions.assertEquals("Ты потерял память", question.getQuestionText());


        Assertions.assertEquals(3, question.getAnswers().size());


        Assertions.assertEquals("Принять вызов", question.getAnswers().get(0).getAnswerText());
        Assertions.assertEquals("WANT_UP_BRIDGE", question.getAnswers().get(0).getNextLevel().toString());
    }
}

