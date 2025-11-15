package com.wtcwebproject.wtc_precourse_web_project.lotto.dto;

import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Winner;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WinningConstantResponse {
    private final int matchCount;
    private final long winnerPrice;
    private final boolean bonusRequired; // 2등 구분 용도
    //Winner Enum 객체로부터 DTO를 생성.
    public static WinningConstantResponse from(Winner winner) {
        return new WinningConstantResponse(
                winner.getMatchCount(),
                winner.getWinnerPrice(),
                // 2등일 때만 true (보너스 번호 일치 여부)
                winner == Winner.SECOND
        );
    }

}