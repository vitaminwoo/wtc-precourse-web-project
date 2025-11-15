package com.wtcwebproject.wtc_precourse_web_project.lotto.dto;

import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Winner;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.WinnerResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

@Getter
@RequiredArgsConstructor
public class WinnerResultResponse {
    private final Map<String, Integer> winningStatistics; // 등수별 당첨 횟수 (ex : "FIFTH": 3)
    private final String incomeRate;

    // 도메인 객체(WinnerResult)를 DTO로 변환.
    public static WinnerResultResponse from(WinnerResult winnerResult, int purchasePrice) {

        Map<String, Integer> statistics = winnerResult.getSortedWinningCounts().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().name(), // Winner Enum 이름을 String 키로 변환
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));

        String rate = winnerResult.calculateEarnRate(purchasePrice);

        return new WinnerResultResponse(statistics, rate);
    }
}
