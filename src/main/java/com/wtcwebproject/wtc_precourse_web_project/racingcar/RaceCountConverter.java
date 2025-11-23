package com.wtcwebproject.wtc_precourse_web_project.racingcar;

public class RaceCountConverter {

    public static int convertInput(String raceCountString) {
        int raceCount;

        try {
            raceCount = validateMoveCount(Integer.parseInt(raceCountString));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("moveCount has to be Integer Value", e);
        }
        return raceCount;
    }

    public static int validateMoveCount(int raceCount) {
        if (raceCount <= 0) {
            throw new IllegalArgumentException("moveCount has to be positive");
        }
        return raceCount;
    }
}
