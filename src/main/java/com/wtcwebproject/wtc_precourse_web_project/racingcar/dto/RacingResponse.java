package com.wtcwebproject.wtc_precourse_web_project.racingcar.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class RacingResponse {
    private final List<List<CarStatus>> allRoundResults;
    private final List<String> winnerNames;
    private final int totalRounds;
}
