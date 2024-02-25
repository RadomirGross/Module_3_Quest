package com.example.quest.model;

import com.example.quest.enums.Level;
import lombok.Data;

@Data
public class Answer {
    private String answerText;
    private Level nextLevel;
}
