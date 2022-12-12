package mikail.Ranking.Repository;

import mikail.Ranking.Entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    public Ranking findByTitle(String title);
    public List<Ranking> findAllByUserId(Long userId);
}
