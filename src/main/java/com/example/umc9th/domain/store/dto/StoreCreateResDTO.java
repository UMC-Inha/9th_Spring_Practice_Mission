package com.example.umc9th.domain.store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreCreateResDTO {
    Long StoreId;
    String StoreName;
    Long ManagerNumber;
    String detailAddress;
    String regionName;
}
