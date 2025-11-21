package umc.domain.store.converter;

import umc.domain.food.entity.Food;
import umc.domain.region.entity.Region;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.entity.Store;

public class StoreConverter {
    // Entity -> DTO
    public static StoreResDTO.CreateResDTO toCreateDTO(
            Store store
    ){
        return StoreResDTO.CreateResDTO.builder()
                .storeId(store.getId())
                .build();
    }

    // DTO -> Entity
    public static Store toStore(
            StoreReqDTO.CreateRequestDTO dto,
            Region region,
            Food food
    ){
        return Store.builder()
                .name(dto.name())
                .info(dto.info())
                .region(region)
                .food(food)
                .build();
    }
}
