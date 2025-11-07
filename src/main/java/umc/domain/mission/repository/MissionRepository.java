package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
select  m
from  Mission  m
join  m.store s
join s.location l
where l.name = :name
order by m.deadline asc, m.point desc 
""")
    Page<Mission> findByName(@Param("name") String name, Pageable pageable);

}
