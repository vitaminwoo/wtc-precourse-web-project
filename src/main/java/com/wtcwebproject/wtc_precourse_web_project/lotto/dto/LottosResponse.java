package com.wtcwebproject.wtc_precourse_web_project.lotto.dto;

import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Lottos;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class LottosResponse {
    private final int purchaseAmount;
    private final List<LottoResponse> lottos;

    // 도메인 객체(Lottos)를 DTO로 변환.
    public static LottosResponse from(Lottos lottos) {

        List<LottoResponse> lottoResponses = lottos.getLottosNumbers().stream()
                .map(LottoResponse::from)
                .collect(Collectors.toList());

        return new LottosResponse(lottoResponses.size(), lottoResponses);
    }
}