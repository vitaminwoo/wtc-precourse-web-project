package com.wtcwebproject.wtc_precourse_web_project.racingcar;

import java.util.List;
import java.util.Map;

public class StartRacing {

    public static Map<String, Integer> runRacing(List<String> carNamesList, int raceCount) {
        RaceResult raceResult = new RaceResult(carNamesList);
        for (int i = 0; i < raceCount; i++) {
            raceResult.moveOneResult();
        }
        return raceResult.getRaceResult();
    }

}
