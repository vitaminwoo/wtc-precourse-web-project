package com.wtcwebproject.wtc_precourse_web_project.lotto.dto;

import com.wtcwebproject.wtc_precourse_web_project.lotto.domain.Lotto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LottoResponse {
    private final List<Integer> numbers;

    // 도메인 객체(Lotto)를 DTO로 변환.
    public static LottoResponse from(Lotto lotto) {
        return new LottoResponse(lotto.getNumbers());
    }
}
