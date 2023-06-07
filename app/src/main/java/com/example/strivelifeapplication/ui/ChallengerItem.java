package com.example.strivelifeapplication.ui;

public class ChallengerItem {
    private String name;
    private int score;

    public ChallengerItem(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
