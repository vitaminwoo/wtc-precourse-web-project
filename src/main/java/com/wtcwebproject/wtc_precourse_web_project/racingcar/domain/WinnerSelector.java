package com.wtcwebproject.wtc_precourse_web_project.racingcar.domain;

import com.wtcwebproject.wtc_precourse_web_project.racingcar.dto.CarStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WinnerSelector {

    public static List<String> winnerNames(List<CarStatus> finalResult) {
        List<String> winnerNamesList = new ArrayList<>();
        int winnerMoveCount = countWinnerMove(finalResult);
        for (CarStatus carStatus : finalResult) {
            if (winnerMoveCount == carStatus.getPosition()) {
                winnerNamesList.add(carStatus.getName());
            }
        }

        return winnerNamesList;
    }

    public static int countWinnerMove(List<CarStatus> finalResult) {
        if (finalResult.isEmpty()) {
            return 0;
        }

        return finalResult.stream()
                .mapToInt(CarStatus::getPosition)
                .max()
                .orElse(0);
    }

}
