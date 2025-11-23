package com.wtcwebproject.wtc_precourse_web_project.racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WinnerSelector {

    public static List<String> winnerNames(Map<String, Integer> resultMap) {
        List<String> winnerNamesList = new ArrayList<>();
        int winnerMoveCount = countWinnerMove(resultMap);
        resultMap.forEach((carName, moveCount) -> {
           if (winnerMoveCount == moveCount) {
               winnerNamesList.add(carName);
           }
        });

        return winnerNamesList;
    }

    public static int countWinnerMove(Map<String, Integer> resultMap) {
        return Collections.max(resultMap.values());
    }
}
