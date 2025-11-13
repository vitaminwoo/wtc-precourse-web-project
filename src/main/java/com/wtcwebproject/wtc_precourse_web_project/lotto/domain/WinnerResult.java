package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

import java.util.List;

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
