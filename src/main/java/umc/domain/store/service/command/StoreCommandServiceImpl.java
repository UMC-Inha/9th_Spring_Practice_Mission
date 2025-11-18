package umc.domain.store.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.food.entity.Food;
import umc.domain.food.exception.FoodException;
import umc.domain.food.exception.code.FoodErrorCode;
import umc.domain.food.repository.FoodRepository;
import umc.domain.region.entity.Region;
import umc.domain.region.repository.RegionRepository;
import umc.domain.store.converter.StoreConverter;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.entity.BusinessTime;
import umc.domain.store.entity.Store;
import umc.domain.store.repository.BusinessTimeRepository;
import umc.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final BusinessTimeRepository businessTimeRepository;
    private final FoodRepository foodRepository;

    @Override
    public StoreResDTO.AddStoreResDTO addStoreToRegion(Long regionId, StoreReqDTO.AddStoreDTO req){
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RuntimeException("Region not found"));

        Food food = foodRepository.findById(req.foodId())
                .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

        Store store = StoreConverter.toStore(req,region,food);
        storeRepository.save(store);

        List<BusinessTime> businessTimeList = StoreConverter.toBusinessTimeList(req.businessTimes(),store);
        businessTimeRepository.saveAll(businessTimeList);

        return StoreConverter.toAddStoreResDTO(store,businessTimeList);
    }
}
