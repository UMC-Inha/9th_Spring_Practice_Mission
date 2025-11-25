package umc.domain.misson.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.QMemberMission;
import umc.domain.member.enums.Status;
import umc.domain.misson.entity.Mission;

@Repository
@RequiredArgsConstructor
public class MissionQueryDslImpl implements MissionQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> findOngoingMissionsByMember(Member member, Pageable pageable) {
        QMemberMission memberMission = QMemberMission.memberMission;

        List<Mission> missions = queryFactory.select(memberMission.mission)
                .from(memberMission)
                .where(
                        memberMission.member.eq(member),
                        memberMission.status.eq(Status.PENDING)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .where(
                        memberMission.member.eq(member),
                        memberMission.status.eq(Status.PENDING)
                )
                .fetchOne();
        
        if (count == null) {
            count = 0L;
        }

        return new PageImpl<>(missions, pageable, count);
    }
}
