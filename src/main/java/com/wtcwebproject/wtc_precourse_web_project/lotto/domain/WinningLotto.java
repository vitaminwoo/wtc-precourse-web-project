package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int winningBonusNumber;

    public WinningLotto(Lotto winningLotto, int winningBonusNumber) {
        this.winningLotto = winningLotto;

        validateBonusNumber(winningLotto, winningBonusNumber);
        this.winningBonusNumber = winningBonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        Lotto.validateNumber(bonusNumber);

        List<Integer> lottoNumbers = new ArrayList<>(lotto.getNumbers());
        lottoNumbers.add(bonusNumber);
        Lotto.validateDuplicate(lottoNumbers);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public int getWinningBonusNumber() {
        return winningBonusNumber;
    }

}
