package com.wtcwebproject.wtc_precourse_web_project.lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String MESSAGE_PURCHASE_PRICE = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_WINNING_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public InputView() {}

    public String readPurchasePrice() {
        System.out.println(MESSAGE_PURCHASE_PRICE);
        return Console.readLine();
    }

    public String readWinningNumber() {
        System.out.println(MESSAGE_WINNING_NUMBER);
        return Console.readLine();
    }

    public String readWinningBonusNumber() {
        System.out.println(MESSAGE_WINNING_BONUS_NUMBER);
        return Console.readLine();
    }

}
