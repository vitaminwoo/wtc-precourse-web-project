package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.LinkedHashMap;

public class WinnerResult {

    private final List<Winner> winnerResult;

    public WinnerResult(List<Winner> winnerResult) {
        this.winnerResult = winnerResult;
    }

    public int getCountOfRank(Winner rank) {
        int count = 0;
        for (Winner winner : winnerResult) {
            if (winner == rank) {
                count++;
            }
        }
        return count;
    }

    public Map<Winner, Integer> getSortedWinningCounts() {
        // 5등부터 1등까지 순서대로 정렬 (Winner Enum에 역순 정렬 Comparator 사용)
        return Stream.of(Winner.values())
                .filter(winner -> winner != Winner.LOSE)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toMap(
                        winner -> winner,
                        this::getCountOfRank,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));
    }

    public String calculateEarnRate(int purchasePrice) {
        long earnSum = 0L;
        for (Winner winner : winnerResult) {
            earnSum += winner.getWinnerPrice();
        }

        double incomeRate = ((double) earnSum / purchasePrice) * 100;
        double roundedRate = Math.round(incomeRate * 10.0) / 10.0;

        return String.format("%.1f", roundedRate);
    }
}
