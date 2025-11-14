package com.wtcwebproject.wtc_precourse_web_project.lotto.service;

import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Lotto;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Lottos;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Purchase;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.WinnerResult;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.WinningLotto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class LottoService {

    // createPurchase() : Purchase 객체 생성 (Controller가 구매 금액을 파라메터로 전달)
    public Lottos createPurchase(int purchasePrice) {
        Purchase purchase = new Purchase(purchasePrice);

        return Lottos.generateRandomLottos(purchase.getPurchaseAmount());
    }

    // createWinningLotto() : 당첨 번호 유효성 검증 및 WinningLotto 객체 생성 (Controller가 당첨 번호와 보너스 번호를 파라메터로 전달)
    public WinningLotto createWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        Lotto winningLotto = new Lotto(winningNumbers);
        validateWinningBonusNumber(winningLotto, bonusNumber);

        return new WinningLotto(winningLotto, bonusNumber);
    }

    // calculateResult() : 당첨 결과 계산 (Controller가 구매 로또와 WinningLotto를 파라메터로 전달)
    public WinnerResult calculateResult(Lottos purchaseLottos, WinningLotto winningLotto) {
        WinnerResult winnerResult = purchaseLottos.calculateWinningLottosResult(winningLotto);

        return winnerResult;
    }


    // WinningLotto 생성자에서 검증해도 괜찮을듯. 이후에 리팩토링.
    private void validateWinningBonusNumber(Lotto winningLotto, int bonusNumber) {
        Lotto.validateNumber(bonusNumber);

        List<Integer> lottoNumbers = new ArrayList<>(winningLotto.getNumbers());
        lottoNumbers.add(bonusNumber);
        Lotto.validateDuplicate(lottoNumbers);
    }
}