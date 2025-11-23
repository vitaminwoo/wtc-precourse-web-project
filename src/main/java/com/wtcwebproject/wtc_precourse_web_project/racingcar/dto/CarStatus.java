package com.wtcwebproject.wtc_precourse_web_project.racingcar.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarStatus {
    private final String name;
    private final int position;
}