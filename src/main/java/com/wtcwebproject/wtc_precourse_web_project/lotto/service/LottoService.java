package com.wtcwebproject.wtc_precourse_web_project.lotto.service;

import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.*;

import com.wtcwebproject.wtc_precourse_web_project.lotto.dto.WinningConstantResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    // createLottosFromNumbers() : List<List<Integer>>를 List<Lotto>로 변환
    public Lottos createLottosFromNumbers(List<List<Integer>> numbers) {
        List<Lotto> purchasedLottos = numbers.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());

        return new Lottos(purchasedLottos);
    }

    // WinningLotto 생성자에서 검증해도 괜찮을듯. 이후에 리팩토링.
    private void validateWinningBonusNumber(Lotto winningLotto, int bonusNumber) {
        Lotto.validateNumber(bonusNumber);

        List<Integer> lottoNumbers = new ArrayList<>(winningLotto.getNumbers());
        lottoNumbers.add(bonusNumber);
        Lotto.validateDuplicate(lottoNumbers);
    }

    public Map<String, WinningConstantResponse> getWinnerConstants() {
        return Stream.of(Winner.values())
                .filter(winner -> winner != Winner.LOSE)
                .collect(Collectors.toMap(
                        Winner::name,
                        WinningConstantResponse::from
                ));
    }
}