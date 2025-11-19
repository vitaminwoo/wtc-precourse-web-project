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
        // (임시) 구매 금액 5000원으로 가정하여 로또 생성 및 결과 계산 (실제는 상태 유지 필요)
        int assumedPurchasePrice = 5000;
        Lottos purchasedLottos = lottoService.createPurchase(assumedPurchasePrice);

        WinningLotto winningLotto = lottoService.createWinningLotto(
                request.getWinningNumbers(),
                request.getWinningBonusNumber()
        );

        WinnerResult winnerResult = lottoService.calculateResult(purchasedLottos, winningLotto);

        WinnerResultResponse response = WinnerResultResponse.from(winnerResult, assumedPurchasePrice);
        return ResponseEntity.ok(response);
    }
}
