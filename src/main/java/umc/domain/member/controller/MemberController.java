package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.dto.res.MemberResDTO;
import umc.domain.member.service.MemberQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberQueryService memberService;

    @GetMapping("/{id}")
    public MemberResDTO.Mypage getMember(@PathVariable Long id) {
        return memberService.getById(id);
    }

}
