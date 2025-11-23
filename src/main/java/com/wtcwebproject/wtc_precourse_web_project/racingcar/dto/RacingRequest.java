package com.wtcwebproject.wtc_precourse_web_project.racingcar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RacingRequest {
    private List<String> carNames;
    private int raceCount;
}