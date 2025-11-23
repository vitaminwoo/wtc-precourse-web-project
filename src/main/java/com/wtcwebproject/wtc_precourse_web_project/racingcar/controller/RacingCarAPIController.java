package com.wtcwebproject.wtc_precourse_web_project.racingcar.controller;

import com.wtcwebproject.wtc_precourse_web_project.racingcar.dto.RacingRequest;
import com.wtcwebproject.wtc_precourse_web_project.racingcar.dto.RacingResponse;
import com.wtcwebproject.wtc_precourse_web_project.racingcar.service.RacingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/racing") //
public class RacingCarAPIController {

    private final RacingService racingService;

    public RacingCarAPIController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/start")
    public ResponseEntity<RacingResponse> startRacing(@RequestBody RacingRequest request) {
        RacingResponse response = racingService.runRacing(request);
        return ResponseEntity.ok(response);
    }
}