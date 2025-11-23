package com.wtcwebproject.wtc_precourse_web_project.racingcar.domain;

public class RaceCountValidator {

    public static int validateMoveCount(int raceCount) {
        if (raceCount <= 0) {
            throw new IllegalArgumentException("moveCount has to be positive");
        }
        return raceCount;
    }
}
