package umc.domain.mission.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.domain.mission.dto.res.CompletedMissionDto;
import umc.domain.mission.dto.res.OnGoingMissionDto;
import umc.domain.mission.entity.QMission;
import umc.domain.mission.entity.mapping.QMemberMission;
import umc.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl   implements MemberMissionRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    private final QMemberMission mm = QMemberMission.memberMission;
    private final QMission m = QMission.mission;
    private final QStore s = QStore.store;

    @Override
    public List<OnGoingMissionDto> findOnGoingMissions(Long memberId, Pageable pageable) {

        return queryFactory
                .select(
                        Projections.constructor(
                                OnGoingMissionDto.class,
                                s.name,
                                m.point,
                                m.deadline,
                                m.leastAmount
                        )
                )
                .from(mm)
                .join(mm.mission, m)
                .join(m.store, s)
                .where(
                        mm.isCompleted.isFalse(),
                        mm.member.id.eq(memberId)
                )
                .orderBy(
                        m.deadline.asc(),
                        mm.updatedAt.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<CompletedMissionDto> findCompletedMissions(Long memberId, Pageable pageable) {

        return queryFactory
                .select(
                        Projections.constructor(
                                CompletedMissionDto.class,
                                m.id,
                                s.name,
                                m.point,
                                m.leastAmount
                        )
                )
                .from(mm)
                .join(mm.mission, m)
                .join(m.store, s)
                .where(
                        mm.isCompleted.isTrue(),
                        mm.member.id.eq(memberId)
                )
                .orderBy(
                        m.createdAt.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
