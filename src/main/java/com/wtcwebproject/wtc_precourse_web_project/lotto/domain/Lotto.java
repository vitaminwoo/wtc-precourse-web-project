package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        List<Integer> numbersCopy = new ArrayList<>(numbers);
        numbersCopy.sort(null);
        validate(numbersCopy);
        validateDuplicate(numbersCopy);
        for (int number : numbersCopy) {
            validateNumber(number);
        }
        this.numbers = numbersCopy;
    }

    public static void validateNumber(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public static void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 서로 다른 숫자여야 합니다.");
        }
    }

    public static Lotto createRandomLotto() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(
                LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_SIZE
        );
        return new Lotto(randomNumbers);
    }

    public Winner matchWinningLotto(WinningLotto winningLotto) {
        int matchCount = checkWinningLottoNumbers(winningLotto.getWinningLotto());
        boolean bonusCheck = checkWinningLottoBonusNumber(winningLotto.getWinningBonusNumber());

        return Winner.findWinnerRank(matchCount, bonusCheck);
    }

    public int checkWinningLottoNumbers(Lotto winningLottoNumbers) {
        int count = 0;
        for (int winningNumber : winningLottoNumbers.getNumbers()) {
            if (this.numbers.contains(winningNumber)) {
                count++;
            }
        }
        return count;
    }

    public boolean checkWinningLottoBonusNumber(BonusNumber winningLottoBonusNumber) {
        return this.numbers.contains(winningLottoBonusNumber.getNumber());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
