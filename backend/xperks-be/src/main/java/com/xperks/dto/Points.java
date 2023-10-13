package com.xperks.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Points {

    _0(0),
    _50(50),
    _250(250),
    _100(100),
    _500(500);

    private final int points;
}
