package com.wtcwebproject.wtc_precourse_web_project.racingcar.service;

import com.wtcwebproject.wtc_precourse_web_project.racingcar.domain.RaceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RacingService {

    public List<Map<String, Integer>> runRacing(List<String> carNamesList, int raceCount) {
        RaceResult raceResult = new RaceResult(carNamesList);
        // 모든 라운드의 결과를 담을 리스트
        List<Map<String, Integer>> allRoundResults = new ArrayList<>();

        for (int i = 0; i < raceCount; i++) {
            Map<String, Integer> currentRoundResult = raceResult.moveOneResult();
            allRoundResults.add(currentRoundResult);
        }

        return allRoundResults;
    }
}
