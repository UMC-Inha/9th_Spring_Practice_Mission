package umc.domain.store.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.MemberType;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.District;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.DistrictRepository;
import umc.domain.store.repository.StoreRepository;

@RequiredArgsConstructor
@Service

public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final DistrictRepository districtRepository;

    @Transactional
    @Override
    public StoreResDTO.CreateDTO createStore(Long ownerId, StoreReqDTO.CreateDTO request) {

        Member member = memberRepository.findById(ownerId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        if (member.getMemberType() != MemberType.OWNER) {
            throw new MemberException(MemberErrorCode.NOT_OWNER);
        }

        District district = districtRepository.findById(request.districtId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND_DISTRICT));

        if (storeRepository.existsByOwnerId(ownerId)) {
            throw new StoreException(StoreErrorCode.ALREADY_REGISTERED_STORE);
        }

        Store store = Store.builder()
                .type(request.type())
                .name(request.name())
                .address(request.address())
                .owner(member)
                .district(district)
                .build();

        Store saved = storeRepository.save(store);

        return StoreResDTO.CreateDTO.from(saved);
    }
}
