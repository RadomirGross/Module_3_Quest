package com.example.quest.model;

import com.example.quest.enums.Level;
import lombok.Data;

import java.util.List;
@Data
public class Question {
    private Level currentLevel;
    private String questionText;
    private List<Answer> answers;

}
