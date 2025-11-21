package umc.domain.store.converter;

import umc.domain.food.entity.Food;
import umc.domain.region.entity.Region;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.entity.BusinessTime;
import umc.domain.store.entity.Store;
import umc.domain.user.dto.req.UserReqDTO;
import umc.domain.user.dto.res.UserResDTO;
import umc.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    //Entity -> DTO
    //입력으로 DB에 저장된 Store 엔티티와 영업시간 리스트를 받음
    //영업시간 변환. businessTime(Entity 리스트)를 stream()을 이용해 하나씩 꺼내서
    //DTO 리스트로 바꿈.
    //최종 조립 -> Store정보와 영업시간 리스트를 합쳐 최종 응답 객체 생성
    public static StoreResDTO.AddStoreResDTO toAddStoreResDTO(Store store, List<BusinessTime> businessTimes){
        List<StoreResDTO.BusinessTimeResDTO> timeDTOs = businessTimes.stream()
                .map(bt -> StoreResDTO.BusinessTimeResDTO.builder()
                        .day(bt.getDay())
                        .startTime(bt.getStartTime())
                        .endTime(bt.getEndTime())
                        .isClosed(bt.isClosed())
                        .build())
                .collect(Collectors.toList());

        return StoreResDTO.AddStoreResDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .foodId(store.getFood().getId())
                .regionId(store.getRegion().getId())
                .businessTimes(timeDTOs)
                .createdAt(store.getCreatedAt())
                .build();
    }

    //DTO -> Entity
    public static Store toStore(StoreReqDTO.AddStoreDTO req, Region region, Food food) {
        return Store.builder()
                .name(req.name())
                .ownerCode(req.ownerCode())
                .region(region)
                .food(food)
                .build();
    }

    //DTO리스트 List -> BusinessTime Entity List
    //사용자가 보낸 영업시간 리스트와, 이 영업시간이 속할 가게를 받음
    //리스트 안에 있는 DTO를 하나씩 꺼냄
    //BusinessTime 엔티티 생성

    public static List<BusinessTime> toBusinessTimeList(List<StoreReqDTO.BusinessTimeDTO> dtoList, Store store){
        return dtoList.stream()
                .map(dto -> BusinessTime.builder()
                        .store(store)//이 영업시간은 이 가게의 것이라고 주인 지정
                        .day(dto.day())
                        .startTime(dto.startTime())
                        .endTime(dto.endTime())
                        .isClosed(dto.isClosed())
                        .breakStartTime(dto.breakStartTime())
                        .breakEndTime(dto.breakEndTime())
                        .lastOrderTime(dto.lastOrderTime())
                        .build()
                ).collect(Collectors.toList());
    }
}
