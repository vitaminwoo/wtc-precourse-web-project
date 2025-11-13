package com.wtcwebproject.wtc_precourse_web_project.lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static final String SPLIT_DELIMITER = ",";

    public static List<String> splitInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력 값이 없습니다.");
        }

        return Arrays.stream(input.split(SPLIT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

}
