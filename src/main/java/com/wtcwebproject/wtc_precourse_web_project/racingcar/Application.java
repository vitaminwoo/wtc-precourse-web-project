package com.wtcwebproject.wtc_precourse_web_project.racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분)");
        List<String> carNamesList = CarNameSplitter.splitInput(Console.readLine());

        System.out.println("시도할 횟수는 몇 회인가요?");
        int raceCount = RaceCountConverter.convertInput(Console.readLine());

        System.out.println("실행 결과");
        Map<String, Integer> raceResult = StartRacing.runRacing(carNamesList, raceCount);

        System.out.println("최종 우승자 : " + WinnerSelector.winnerNames(raceResult));
    }
}
