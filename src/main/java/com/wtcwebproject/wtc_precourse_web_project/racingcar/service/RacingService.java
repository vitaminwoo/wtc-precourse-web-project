package com.wtcwebproject.wtc_precourse_web_project.racingcar.service;

import com.wtcwebproject.wtc_precourse_web_project.racingcar.domain.CarNameValidator;
import com.wtcwebproject.wtc_precourse_web_project.racingcar.domain.RaceCountValidator;
import com.wtcwebproject.wtc_precourse_web_project.racingcar.domain.RaceResult;
import com.wtcwebproject.wtc_precourse_web_project.racingcar.domain.WinnerSelector;
import com.wtcwebproject.wtc_precourse_web_project.racingcar.dto.RacingRequest;
import com.wtcwebproject.wtc_precourse_web_project.racingcar.dto.RacingResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RacingService {

    public RacingResponse runRacing(RacingRequest request) {
        List<String> validatedCarNames = validateCarNames(request.getCarNames());
        int validatedRaceCount = validateRaceCount(request.getRaceCount());

        // 경주 실행 및 모든 라운드 기록 저장
        List<Map<String, Integer>> allRoundResults = executeRace(validatedCarNames, validatedRaceCount);

        // 최종 결과 기반 우승자 선정
        Map<String, Integer> finalResult = allRoundResults.get(allRoundResults.size() - 1);
        List<String> winnerNames = WinnerSelector.winnerNames(finalResult);

        // DTO로 변환하여 반환
        return RacingResponse.builder()
                .allRoundResults(allRoundResults)
                .winnerNames(winnerNames)
                .totalRounds(validatedRaceCount)
                .build();
    }

    private List<String> validateCarNames(List<String> carNames) {
        List<String> validatedList = new ArrayList<>();

        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름 목록이 비어있습니다.");
        }

        for (String carName : carNames) {
            String trimmedCarName = carName.trim();
            CarNameValidator.validateCarName(trimmedCarName);
            validatedList.add(trimmedCarName);
        }

        CarNameValidator.validateDuplicateCarName(validatedList);
        return validatedList;
    }

    private int validateRaceCount(int raceCount) {
        return RaceCountValidator.validateMoveCount(raceCount);
    }

    private List<Map<String, Integer>> executeRace(List<String> carNamesList, int raceCount) {
        RaceResult raceResult = new RaceResult(carNamesList);
        List<Map<String, Integer>> allRoundResults = new ArrayList<>();

        for (int i = 0; i < raceCount; i++) {
            Map<String, Integer> currentRoundResult = raceResult.moveOneResult();
            allRoundResults.add(Map.copyOf(currentRoundResult));
        }

        return allRoundResults;
    }
}