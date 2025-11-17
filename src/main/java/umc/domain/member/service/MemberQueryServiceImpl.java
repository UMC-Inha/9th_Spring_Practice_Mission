package umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.converter.MypageConverter;
import umc.domain.member.dto.res.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.global.apiPayload.Exception.GeneralException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResDTO.Mypage getById(Long id) {
        Member member = memberRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MypageConverter.toMypage(member);
    }
}
