package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MissionCreateDTO {
    Long storeId;
    String conditional; // 미션 조건 및 내용
    Integer point;
    LocalDateTime deadline;
}
