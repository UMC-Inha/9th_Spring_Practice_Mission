package umc.domain.member.dto;

import umc.domain.member.enums.Status;

public record MissionListDto(int points, String description, Status status, Long storeId, String storeName) {}
