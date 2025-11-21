package umc.domain.store.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.food.entity.Food;
import umc.domain.food.repository.FoodRepository;
import umc.domain.region.entity.Region;
import umc.domain.region.repository.RegionRepository;
import umc.domain.store.converter.StoreConverter;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final FoodRepository foodRepository;

    @Override
    public StoreResDTO.CreateResDTO create(
            StoreReqDTO.CreateRequestDTO dto
    ){
        Region region = regionRepository.findById(dto.regionId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.REGION_NOT_FOUND));
        Food food = foodRepository.findById(dto.foodId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.FOOD_CATEGORY_NOT_FOUND));

        Store store = StoreConverter.toStore(dto, region, food);

        storeRepository.save(store);

        return StoreConverter.toCreateDTO(store);
    }

}
