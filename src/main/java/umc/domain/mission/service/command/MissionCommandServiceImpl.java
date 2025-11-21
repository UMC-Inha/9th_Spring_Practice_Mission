package umc.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResDTO.CreateResDTO createMission(
            MissionReqDTO.CreateReqDTO dto
    ){
        // DTO에서 이미 @ExistStore 검증됨 → 서비스에서는 바로 Mission 생성 가능
        Mission mission = Mission.builder()
                .content(dto.content())
                .authCode(dto.authCode())
                .period(dto.period())
                .point(dto.point())
                .store(storeRepository.getReferenceById(dto.storeId()))
                .build();

        missionRepository.save(mission);

        return MissionConverter.toCreateResDTO(mission);
    }
}
