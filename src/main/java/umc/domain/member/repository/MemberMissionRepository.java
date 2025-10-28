package umc.domain.member.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.entity.mapping.MemberMission;

public interface MemberMissionRepository {

    @Query("""
        select new umc.domain.member.dto.MissionListDto(
            ms.points,
            ms.description,
            mm.status,
            s.id,
            s.name
        )
        from MemberMission mm
            join mm.mission ms
            join ms.store s
        where mm.member = :member_id
""")
    List<MissionListDto> findPendingOrCompletedMissionByMemberId(@Param("member_id") Long member_id);
}
