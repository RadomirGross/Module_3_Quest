package com.example.quest.model;

import com.example.quest.enums.Level;
import lombok.Data;

@Data
public class Player {
    private String name;
    private Level level;
    private boolean win;
    private boolean lose;

}


