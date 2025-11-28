package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.converter.MemberMissionConverter;
import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Status;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public MemberMissionResDTO.MissionByStatusListDTO getMissionByStatus(Integer page) {
        // Member 찾기
        Member member = memberRepository.findById(1L).orElseThrow(()-> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<MemberMission> memberMission = memberMissionRepository.findMemberMissionByStatus(
                member.getId(),
                Status.IN_PROGRESS,
                pageRequest
        );
        return MemberMissionConverter.toMissionByStatusListDTO(memberMission);
    }

}
