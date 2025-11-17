package umc.domain.mission.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.QMission;
import umc.domain.store.entity.QLocation;
import umc.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> findByName(String name, Pageable pageable) {

        QMission m = QMission.mission;
        QStore s = QStore.store;
        QLocation l = QLocation.location;


        List<Mission> content = queryFactory
                .selectFrom(m)
                .join(m.store, s)
                .join(s.location, l)
                .where(l.name.eq(name))
                .orderBy(
                        m.deadline.asc(),
                        m.point.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long count = queryFactory
                .select(m.count())
                .from(m)
                .join(m.store, s)
                .join(s.location, l)
                .fetchOne();

        return PageableExecutionUtils.getPage(content, pageable, () -> count);
    }
}
