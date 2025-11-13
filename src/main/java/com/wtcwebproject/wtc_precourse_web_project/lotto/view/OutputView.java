package com.wtcwebproject.wtc_precourse_web_project.lotto.view;

import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Lotto;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Lottos;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Winner;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.WinnerResult;

import java.util.*;

public class OutputView {
    private static final String MESSAGE_PURCHASE_AMOUNT = "개를 구매했습니다.";
    private static final String MESSAGE_WINNING_RESULT = "당첨 통계\n---";
    private static final String MESSAGE_INCOME_RATE_FRONT = "총 수익률은 ";
    private static final String MESSAGE_INCOME_RATE_BACK = "%입니다.";

    public OutputView() {}

    public void printPurchaseAmount(int purchaseAmount) {
        System.out.println(purchaseAmount + MESSAGE_PURCHASE_AMOUNT);
    }

    public void printRandomLottos(Lottos randomLottos) {
        List<Lotto> randomLotto = randomLottos.getLottosNumbers();
        for (Lotto lotto : randomLotto) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningResult(WinnerResult winnerResult) {
        System.out.println(MESSAGE_WINNING_RESULT);
        List<Winner> winnerResultOutput = new ArrayList<>(Arrays.asList(Winner.values()));
        winnerResultOutput.remove(Winner.LOSE);
        
        for (Winner winner : winnerResultOutput) {
            int winnerCount = winnerResult.getCountOfRank(winner);
            String msgMatchCount = matchCountStringConstructor(winner);
            String msgWinnerPrice = winnerPriceStringConstructor(winner);
            String msgWinnerCount = winnerCountStringConstructor(winnerCount);

            System.out.println(msgMatchCount + msgWinnerPrice + msgWinnerCount);
        }
    }

    private String matchCountStringConstructor(Winner winner) {
        String matchCountMsg = winner.getMatchCount() + "개 일치";
        if (winner.equals(Winner.SECOND)) {
            return matchCountMsg + ", 보너스 볼 일치";
        }
        return matchCountMsg;
    }

    private String winnerPriceStringConstructor(Winner winner) {
        String winnerPriceUSFormat = String.format(Locale.US, "%,d", winner.getWinnerPrice());
        return " (" + winnerPriceUSFormat + "원)";
    }

    private String winnerCountStringConstructor(int winnerCount) {
        return " - " + winnerCount + "개";
    }

    public void printIncomeRate(WinnerResult winnerResult, int purchasePrice) {
        String earnRateOutput = winnerResult.calculateEarnRate(purchasePrice);
        System.out.println(MESSAGE_INCOME_RATE_FRONT + earnRateOutput + MESSAGE_INCOME_RATE_BACK);
    }

}
