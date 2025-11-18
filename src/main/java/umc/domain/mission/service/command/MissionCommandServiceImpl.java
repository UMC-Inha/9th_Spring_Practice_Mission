package umc.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    @Override
    public MissionResDTO.AddMissionResDTO addMission(MissionReqDTO.AddMissionReqDTO req){

        Store store = storeRepository.findById(req.storeId()).orElseThrow(()-> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(req,store);
        missionRepository.save(mission);

        return MissionConverter.toAddMissionResDTO(mission);
    }
}
