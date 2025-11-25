package umc.domain.mission.service.query;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.converter.MemberMissionConverter;
import umc.domain.mission.dto.MemberMissionResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.dto.GetMemberMissionResponse;
import umc.domain.mission.entity.MemberMission;
import umc.domain.mission.entity.MissionStatus;
import umc.domain.mission.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<GetMemberMissionResponse> getUserMissions(
            Long userId,
            List<String> statuses,
            LocalDateTime cursorAcceptedAt,
            Long cursorMissionId,
            int limit
    ) {
        return memberMissionRepository.findUserMissionsPage(
                        userId, statuses, cursorAcceptedAt, cursorMissionId, limit
                ).stream()
                .map(p -> new GetMemberMissionResponse(
                        p.getMissionId(),
                        p.getStatus(),
                        p.getPoint(),
                        p.getContent(),
                        p.getStoreName()
                ))
                .toList();
    }

    public MemberMissionResDTO.SearchListDTO getMemberMissions(Long memberId, MissionStatus status, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("acceptedAt").descending());

        Page<MemberMission> missions;

        if (status == null) {
            missions = memberMissionRepository.findByMember(member, pageable);
        } else {
            missions = memberMissionRepository.findByMemberAndStatus(member, status, pageable);
        }

        return MemberMissionConverter.toSearchListDTO(missions);
    }


}
