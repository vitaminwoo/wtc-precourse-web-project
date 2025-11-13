package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos generateRandomLottos(int amount) {
        List<Lotto> randomLottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Lotto randomLotto = Lotto.createRandomLotto();
            randomLottos.add(randomLotto);
        }
        return new Lottos(randomLottos);
    }

    public List<Lotto> getLottosNumbers() {
        return lottos;
    }

    public WinnerResult calculateWinningLottosResult(WinningLotto winningLotto) {
        List<Winner> winnerResult = new ArrayList<>();
        for (Lotto lotto : lottos) {
            Winner winner = lotto.matchWinningLotto(winningLotto);
            winnerResult.add(winner);
        }
        return new WinnerResult(winnerResult);
    }

}