package umc.domain.store.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.entity.Review;
import umc.domain.store.dto.CreateStoreDto;
import umc.domain.store.entity.Store;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

    private StoreRepository storeRepository;
    private MemberRepository memberRepository;


    public Long createStore(CreateStoreDto createStoreDto, Long memberId){


        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));



        Store store = Store.builder()
                .name(createStoreDto.getName())
                .address(createStoreDto.getAddress())
                .category(createStoreDto.getCategory())
                .rate(createStoreDto.getRate())
                .build();


        return storeRepository.save(store).getId();
    }
}
