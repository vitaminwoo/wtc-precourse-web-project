package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        Lotto.validateNumber(number);
    }

    public int getNumber() {
        return number;
    }
}