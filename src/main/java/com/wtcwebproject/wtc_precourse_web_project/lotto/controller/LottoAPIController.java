package com.wtcwebproject.wtc_precourse_web_project.lotto.controller;

import com.wtcwebproject.wtc_precourse_web_project.lotto.dto.LottoPurchaseRequest;
import com.wtcwebproject.wtc_precourse_web_project.lotto.dto.WinnerResultResponse;
import com.wtcwebproject.wtc_precourse_web_project.lotto.dto.LottosResponse;
import com.wtcwebproject.wtc_precourse_web_project.lotto.dto.WinningLottoRequest;
import com.wtcwebproject.wtc_precourse_web_project.lotto.dto.WinningConstantResponse;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Lottos;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.WinningLotto;
import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.WinnerResult;
import com.wtcwebproject.wtc_precourse_web_project.lotto.service.LottoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lotto")
public class LottoAPIController {

    private final LottoService lottoService;

    public LottoAPIController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    // POST /api/lotto/buy
    // 로또 구매 금액을 받아 로또를 발행하고 결과를 반환.
    @PostMapping("/buy")
    public ResponseEntity<LottosResponse> buyLottos(@RequestBody LottoPurchaseRequest request) {
        Lottos purchasedLottos = lottoService.createPurchase(request.getPurchasePrice());

        LottosResponse response = LottosResponse.from(purchasedLottos);
        return ResponseEntity.ok(response);
    }


    //GET /api/lotto/constants
    // 당첨 등수별 상수(상금, 매치 조건) 정보를 반환.
    @GetMapping("/constants")
    public ResponseEntity<Map<String, WinningConstantResponse>> getLottoConstants() {
        Map<String, WinningConstantResponse> constants = lottoService.getWinnerConstants();
        return ResponseEntity.ok(constants);
    }

    //POST /api/lotto/check
    // 구매 로또 목록과 당첨 번호를 받아 최종 당첨 통계를 계산.
    @PostMapping("/check")
    public ResponseEntity<WinnerResultResponse> checkWinningResult(@RequestBody WinningLottoRequest request) {
        WinningLotto winningLotto = lottoService.createWinningLotto(
                request.getWinningNumbers(),
                request.getWinningBonusNumber()
        );
        Lottos purchasedLottos = lottoService.createLottosFromNumbers(request.getPurchasedLottoNumbers());

        int actualPurchasePrice = purchasedLottos.getLottosNumbers().size() * 1000;

        WinnerResult winnerResult = lottoService.calculateResult(purchasedLottos, winningLotto);

        WinnerResultResponse response = WinnerResultResponse.from(winnerResult, actualPurchasePrice);
        return ResponseEntity.ok(response);
    }
}
