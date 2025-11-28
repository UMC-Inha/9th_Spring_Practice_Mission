package umc.domain.member.repository.membermission;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.entity.mapping.QMemberMission;
import umc.domain.misson.entity.Mission;

@Repository
@RequiredArgsConstructor
public class MemberMissionQueryDslImpl implements MemberMissionQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MemberMission> findMemberMissionByMemberAndMission(Member member, Mission mission) {
        QMemberMission memberMission = QMemberMission.memberMission;

        return Optional.ofNullable(
                queryFactory.selectFrom(memberMission)
                        .where(
                                memberMission.member.eq(member),
                                memberMission.mission.eq(mission)
                        )
                        .fetchOne()
        );
    }
}
