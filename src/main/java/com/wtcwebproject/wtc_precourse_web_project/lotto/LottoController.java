package com.wtcwebproject.wtc_precourse_web_project.lotto;

import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.*;
import com.wtcwebproject.wtc_precourse_web_project.lotto.util.InputConverter;
import com.wtcwebproject.wtc_precourse_web_project.lotto.util.Parser;
import com.wtcwebproject.wtc_precourse_web_project.lotto.view.InputView;
import com.wtcwebproject.wtc_precourse_web_project.lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Purchase purchase = createPurchase();
        Lottos purchaseLottos = createLottos(purchase.getPurchaseAmount());
        WinningLotto winningLotto = createWinningLotto();

        WinnerResult winnerResult = createWinnerResult(purchaseLottos, winningLotto);
        printWinnerResult(winnerResult, purchase.getPurchasePrice());
    }


    private Purchase createPurchase() {
        while (true) {
            try {
                int purchasePriceInput = InputConverter.convertInput(inputView.readPurchasePrice());
                return new Purchase(purchasePriceInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lottos createLottos(int purchaseAmount) {
        outputView.printPurchaseAmount(purchaseAmount);
        Lottos randomLottos = Lottos.generateRandomLottos(purchaseAmount);
        outputView.printRandomLottos(randomLottos);

        return randomLottos;
    }

    private WinningLotto createWinningLotto() {
        Lotto winningLotto = validateWinningLottoInput();
        int winningBonusNumber = validateWinningBonusNumberInput(winningLotto);

        return new WinningLotto(winningLotto, winningBonusNumber);
    }

    private Lotto validateWinningLottoInput() {
        while (true) {
            try {
                List<Integer> winningNumbersInput = Parser.splitInput(inputView.readWinningNumber()).stream()
                        .map(InputConverter::convertInput)
                        .toList();
                return new Lotto(winningNumbersInput);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int validateWinningBonusNumberInput(Lotto winningLotto) {
        while (true) {
            try {
                int winningBonusNumberInput = InputConverter.convertInput(inputView.readWinningBonusNumber());
                validateWinningBonusNumber(winningLotto, winningBonusNumberInput);

                return winningBonusNumberInput;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateWinningBonusNumber(Lotto winningLotto, int bonusNumber) {
        Lotto.validateNumber(bonusNumber);

        List<Integer> lottoNumbers = new ArrayList<>(winningLotto.getNumbers());
        lottoNumbers.add(bonusNumber);
        Lotto.validateDuplicate(lottoNumbers);
    }

    private WinnerResult createWinnerResult(Lottos purchaseLottos, WinningLotto winningLotto) {
        return purchaseLottos.calculateWinningLottosResult(winningLotto);
    }

    private void printWinnerResult(WinnerResult winnerResult, int purchasePrice) {
        outputView.printWinningResult(winnerResult);
        outputView.printIncomeRate(winnerResult, purchasePrice);
    }

}
