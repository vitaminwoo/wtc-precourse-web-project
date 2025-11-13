package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

public enum Winner {
    FIFTH(3, 5_000) {
        public boolean isMatch(int matchCount, boolean bonusCheck) {
            return matchCount == 3;
        }
    },
    FORTH(4, 50_000) {
        public boolean isMatch(int matchCount, boolean bonusCheck) {
            return matchCount == 4;
        }
    },
    THIRD(5, 1_500_000) {
        public boolean isMatch(int matchCount, boolean bonusCheck) {
            return matchCount == 5 && !bonusCheck;
        }
    },
    SECOND(5, 30_000_000) {
        public boolean isMatch(int matchCount, boolean bonusCheck) {
            return matchCount == 5 && bonusCheck;
        }
    },
    FIRST(6, 2_000_000_000) {
        public boolean isMatch(int matchCount, boolean bonusCheck) {
            return matchCount == 6;
        }
    },
    LOSE(0, 0) {
        public boolean isMatch(int matchCount, boolean bonusCheck) {
            return false;
        }
    };

    private final int matchCount;
    private final long winnerPrice;

    Winner(int matchCount, long winnerPrice) {
        this.matchCount = matchCount;
        this.winnerPrice = winnerPrice;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getWinnerPrice() {
        return winnerPrice;
    }

    public abstract boolean isMatch(int matchCount, boolean bonusCheck);

    public static Winner findWinnerRank(int matchCount, boolean bonusCheck) {
        for (Winner winner : values()) {
            if (winner.isMatch(matchCount, bonusCheck)) {
                return winner;
            }
        }
        return LOSE;

    }

}
