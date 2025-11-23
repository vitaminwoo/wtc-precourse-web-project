package com.wtcwebproject.wtc_precourse_web_project.racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;

public class RaceResult {
    private static final int START_INCLUSIVE = 0;
    private static final int END_INCLUSIVE = 9;
    private static final int RANDOM_VALUE_REQUIRE = 4;

    private final Map<String, Integer> carMoveMap;

    public RaceResult(List<String> carNamesList) {
        this.carMoveMap = carNamesList.stream()
                .collect(Collectors.toMap(
                        carName -> carName,
                        carName -> 0,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public Map<String, Integer> moveOneResult() {
        this.carMoveMap.forEach((carName, moveCount) -> {
            if(moveRequirement()){
                carMoveMap.put(carName, moveCount + 1);
            }
        });

        return Collections.unmodifiableMap(carMoveMap);
    }

    public boolean moveRequirement() {
        return Randoms.pickNumberInRange(START_INCLUSIVE, END_INCLUSIVE) >= RANDOM_VALUE_REQUIRE;
    }

    public Map<String, Integer> getRaceResult() {
        return this.carMoveMap;
    }
}