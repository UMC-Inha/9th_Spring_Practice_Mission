package com.example.umc9th.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCreateReqDTO {
    String StoreName;
    Long managerNumber;
    String detailAddress;
    Long regionId;
}
