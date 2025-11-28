package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MemberMission404_1",
            "해당 멤버미션을 찾지 못했습니다"),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MemberMission405_1",
            "이미 완료된 미션입니다.");

    private final HttpStatus status;
    private final String message;
    private final String code;
}
