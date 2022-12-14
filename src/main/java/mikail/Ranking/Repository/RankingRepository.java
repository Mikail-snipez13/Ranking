package mikail.Ranking.Repository;

import mikail.Ranking.Entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    @Query(value = "SELECT * FROM ranking WHERE user_id = ?1 AND title = ?2", nativeQuery = true)
    public Ranking findByUserIdAndTitle(Long userId, String title);
    public List<Ranking> findAllByUserId(Long userId);
}
