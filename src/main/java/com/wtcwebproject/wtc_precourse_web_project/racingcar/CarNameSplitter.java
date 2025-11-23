package com.wtcwebproject.wtc_precourse_web_project.racingcar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CarNameSplitter {

    private static final int CAR_NAME_MAX_LENGTH = 5;

    public static List<String> splitInput(String carNames) {
        nullCheckInput(carNames);

        List<String> carNamesList = new ArrayList<>();
        for (String carName : carNames.split(",")) {
            carNamesList.add(validateCarName(carName));
        }
        validateDuplicateCarName(carNamesList);
        return carNamesList;
    }

    public static String validateCarName(String carName) {
        if (carName.length() > CAR_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("carName only contains 5 characters");
        } else if (carName.isEmpty()) {
            throw new IllegalArgumentException("carName is empty");
        }
        return carName;
    }

    public static void nullCheckInput(String carNames) {
        if (carNames == null) {
            throw new IllegalArgumentException("carNames is null");
        }
    }

    public static void validateDuplicateCarName(List<String> carNames) {
        if (carNames.size() != new HashSet<>(carNames).size()) {
            throw new IllegalArgumentException("duplicate carName");
        }
    }

}
