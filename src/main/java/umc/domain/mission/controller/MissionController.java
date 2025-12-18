package umc.domain.mission.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.CreateMissionDto;
import umc.domain.mission.dto.MissionDto;
import umc.domain.mission.dto.myInProgressMissionDto;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.service.MissionService;
import umc.global.annotation.PageLimit;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs{

    private MissionService missionService;

    @PostMapping("/{memberId}/{missionId}")
    public ApiResponse<?> createMemberMission(
            @NotBlank @PathVariable Long memberId,
            @NotBlank @PathVariable Long missionId
    ){

        Long memberMissionId = missionService.createMemberMission(memberId, missionId);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, memberMissionId
        );
    }

    @GetMapping("/{memberId}")
    public ApiResponse<List<MissionDto>> findMyInProgressMission(
            @NotNull(message = "필수 값입니다") Long memberId,
            @PageLimit @PageableDefault(page=0, size=10) Pageable pageable
    ){


        Page<Mission> myInProgressMission = missionService.findMyInProgressMission(memberId, pageable);

        Page<MissionDto> missionDtos = myInProgressMission.map(MissionConverter::toMissionDto);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, missionDtos.getContent()
        );


    }




    @PostMapping("/{memberId}/{storeId}")
    public ApiResponse<?> createMission(
            @NotEmpty @PathVariable Long memberId,
            @NotBlank @PathVariable Long storeId,
            @Valid @RequestBody CreateMissionDto createMissionDto
            ){

        Long missionId = missionService.createMission(storeId, createMissionDto);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, missionId
        );
    }


    @GetMapping("/{memberId}/{storeId}")
    public ApiResponse<List<MissionDto>> findStoreMission(
            @NotNull(message = "필수 값입니다") Long memberId,
            @NotNull(message = "필수 값입니다") Long storeId,
            @PageLimit @PageableDefault(page=0, size=10) Pageable pageable
    ){

        Page<Mission> storeMission = missionService.findStoreMission(storeId, pageable);


        Page<MissionDto> missionDtos = storeMission.map(MissionConverter::toMissionDto);


        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, missionDtos.getContent()
        );


    }



}
