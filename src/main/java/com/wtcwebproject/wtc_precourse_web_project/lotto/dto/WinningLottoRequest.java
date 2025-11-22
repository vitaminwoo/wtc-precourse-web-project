package com.wtcwebproject.wtc_precourse_web_project.lotto.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WinningLottoRequest {
    private List<List<Integer>> purchasedLottoNumbers;

    private List<Integer> winningNumbers;
    private int winningBonusNumber;
}
