package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final BonusNumber winningBonusNumber;

    public WinningLotto(Lotto winningLotto, int winningBonusNumber) {
        this.winningLotto = winningLotto;

        BonusNumber bonusNumber = new BonusNumber(winningBonusNumber);
        validateBonusDuplicate(winningLotto, bonusNumber);
        this.winningBonusNumber = bonusNumber;
    }

    private void validateBonusDuplicate(Lotto lotto, BonusNumber bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber.getNumber())) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public BonusNumber getWinningBonusNumber() {
        return winningBonusNumber;
    }

}
