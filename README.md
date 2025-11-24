# 우아한테크코스 오픈 미션 - 웹 서비스 마이그레이션

---
## 프로젝트 소개

이 프로젝트는 우테코 프리코스에서 진행한 2주차 ‘자동차 경주’, 3주차 ‘로또’ 과제에 대한 구현 코드를 웹 서비스화한 결과물입니다.
기존의 CLI(Command Line Interface) 기반 로직을 Spring Boot 환경으로 변경하고, JSON 기반의 Web API를 통해 웹 기반의 시뮬레이션 서비스를 제공하는 것이 목표입니다.

로또 및 레이싱카 시뮬레이션의 핵심 도메인 로직은 유지하되, 입출력(View)을 HTML/JavaScript 기반의 동적 웹 페이지로 전환하였습니다.

---
## 기능 명세

### 1. 로또

   | 기능 영역 | 사용자 입력 | 시스템 출력 | 특징 |
   | --- | --- | --- | --- |
   | 구매 및 생성 | 구매 금액 (숫자) | 구매한 로또 번호 목록 | 입력한 금액에 해당하는 로또를 자동으로 발급하여 제공합니다. |
   | 당첨 확인 | 당첨 번호 (6개), 보너스 번호 (1개) | 수익률, 각 등수별 당첨 개수 | 구매한 로또와 당첨 번호를 비교하여 통계를 산출하고 수익률을 계산하여 보여줍니다. |

### 2. 자동차 경주

| 기능 영역 | 사용자 입력 | 시스템 출력 | 특징 |
| --- | --- | --- | --- |
| 경주 설정 | 경주할 자동차 이름 (쉼표 구분), 시도 횟수 (숫자) | 없음 (경주 시작) | 자동차 이름은 5자 이하, 시도 횟수는 양수만 입력 가능하며, 경주 시작 버튼을 눌러 설정 값대로 시뮬레이션을 실행할 수 있습니다. |
| 경주 시뮬레이션 | 없음 (API 호출) | 매 라운드별 자동차 위치 정보 | 경주 과정을 웹 페이지에서 라운드 별로 시간차를 두고 동적으로 출력하여 애니메이션 효과를 제공합니다. |
| 결과 확인 | 없음 | 최종 우승자 이름 목록 | 경주가 종료된 후, 가장 멀리 이동한 자동차(들)의 이름을 입력 순으로 출력합니다. |


## 실행 방법

Main 메서드가 포함된 WtcPrecourseWebProjectApplication.java를 실행.

Spring Boot 내장 Tomcat에 의해 할당된 포트(기본 8080)로 접속하여 로또와 자동차 경주 시뮬레이션을 확인.


### 로또 URL
`http://localhost:8080/lotto/lottoApp.html`

### 자동차 경주 URL
`http://localhost:8080/racing/racingApp.html`


## 실행 결과 시연 영상

### 1. 로또 시연 영상
![로또 시뮬레이션](videos/lotto_simulation_video.gif)

### 2. 자동차 경주 시연 영상
![자동차 경주 시뮬레이션](videos/racingcar_simulation_video.gif)

---
## 프로젝트 구조
```java
├── main
│   ├── java
│   │   └── com
│   │       └── wtcwebproject
│   │           └── wtc_precourse_web_project
│   │               ├── lotto
│   │               │   ├── controller
│   │               │   │   └── LottoAPIController.java
│   │               │   ├── domain
│   │               │   │   ├── BonusNumber.java
│   │               │   │   ├── Lotto.java
│   │               │   │   ├── Lottos.java
│   │               │   │   ├── Purchase.java
│   │               │   │   ├── Winner.java
│   │               │   │   ├── WinnerResult.java
│   │               │   │   └── WinningLotto.java
│   │               │   ├── dto
│   │               │   │   ├── LottoPurchaseRequest.java
│   │               │   │   ├── LottoResponse.java
│   │               │   │   ├── LottosResponse.java
│   │               │   │   ├── WinnerResultResponse.java
│   │               │   │   ├── WinningConstantResponse.java
│   │               │   │   └── WinningLottoRequest.java
│   │               │   └── service
│   │               │       └── LottoService.java
│   │               ├── lotto-test.http
│   │               ├── racingcar
│   │               │   ├── controller
│   │               │   │   └── RacingCarAPIController.java
│   │               │   ├── domain
│   │               │   │   ├── CarNameValidator.java
│   │               │   │   ├── RaceCountValidator.java
│   │               │   │   ├── RaceResult.java
│   │               │   │   └── WinnerSelector.java
│   │               │   ├── dto
│   │               │   │   ├── CarStatus.java
│   │               │   │   ├── RacingRequest.java
│   │               │   │   └── RacingResponse.java
│   │               │   └── service
│   │               │       └── RacingService.java
│   │               └── WtcPrecourseWebProjectApplication.java
│   └── resources
│       ├── application.properties
│       ├── static
│       │   ├── lotto
│       │   │   └── lottoApp.html
│       │   └── racing
│       │       └── racingApp.html
│       └── templates
└── test
    └── java
        └── com
            └── wtcwebproject
                └── wtc_precourse_web_project
                    └── WtcPrecourseWebProjectApplicationTests.java
```