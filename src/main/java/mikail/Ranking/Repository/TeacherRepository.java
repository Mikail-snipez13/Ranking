package mikail.Ranking.Repository;

import mikail.Ranking.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public List<Teacher> findAllByRankingId(Long rankingId);

    @Query(
            value = "SELECT * FROM teacher WHERE name = :name AND ranking_id = :rankingId",
            nativeQuery = true)
    public Teacher findExact(
            @Param("name") String name,
            @Param("rankingId") Long rankingId);
}
