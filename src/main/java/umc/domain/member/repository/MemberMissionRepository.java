package umc.domain.member.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.enums.Status;

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
        where mm.member.id = :member_id
""")
    List<MissionListDto> findPendingOrCompletedMissionByMemberId(@Param("member_id") Long member_id);

    @Query("""
        select count(mm)
        from MemberMission mm
            join mm.mission ms
            join ms.store s
            join s.region r
        where r.name = :region_name
            and mm.status = :status
            and mm.member.id = :member_id
""")
    long getMissionCountByMemberIdAndRegion(@Param("member_id")  Long member_id,
                                           @Param("region_name") String region_name,
                                            @Param("status") Status status);

    @Query("""
        select new umc.domain.member.dto.MissionChallengeListDto(s.name, s.type
                , ms.description
                , ms.points
                , function('datediff',ms.endDate, current_date())
                , ms.id)
        from Mission ms
            join ms.store s
            join s.region r
        where function('datediff',ms.endDate, current_date()) >= 0
            and r.name = :region_name
            and not exists (
                select 1
                from MemberMission mm
                where mm.mission.id = ms.id
                    and mm.member.id = :member_id
            )
""")
    List<MissionChallengeListDto> findAvailableMissionsByMemberIdAndRegion(@Param("region_name") String region_name,
                                                                           @Param("member_id") Long member_id);
}
