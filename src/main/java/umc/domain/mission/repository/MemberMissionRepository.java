package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.mission.entity.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Integer> , MemberMissionRepositoryCustom {
}